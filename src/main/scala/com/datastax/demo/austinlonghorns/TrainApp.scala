package com.datastax.demo.austinlonghorns

import com.datastax.spark.connector._
import org.apache.spark.mllib.classification.NaiveBayes
import org.apache.spark.mllib.linalg.{Vector, Vectors}
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.{SparkConf, SparkContext}


/**
 * Created by patrick on 15/01/15.
 */
object TrainApp {


  val keyspace = "austin_longhorns_ks"


  def main(args: Array[String]) {

    val conf = new SparkConf()
      .setAppName(getClass.getSimpleName)
      .setMaster("spark://" + Settings.SparkMaster)
      .set("spark.executor.memory", Settings.SparkExecutorMemory)
      .set("spark.cores.max", Settings.SparkCoresMax)
      .set("spark.cassandra.connection.host", Settings.SparkCasssandraHost)
      .setJars(Settings.DeployJars)

    val sc = new SparkContext(conf)

    // load dictionnary of meaningfull words
    val dictionary = sc.cassandraTable[Word](keyspace, "dictionnary").filter(_.value != null).toArray()

    // load tweets and map to labeled points
    val tweetRDD = sc.cassandraTable[Tweet](keyspace, "tweets2").filter(_.sentiment != null)
    val labelledPointRDD = tweetRDD.map(tweetToLabeledPoint(_, dictionary))

    // train model
    val model = NaiveBayes.train(labelledPointRDD, lambda = 1.0)

    var tweet:Tweet = new Tweet(null, null, null, "I like going to the cinema, it's great.", null, null, 0, 0, null)
    println("Predicting, expecting good :")
    println(model.predict(tweetToLabeledPoint(tweet, dictionary).features))

    tweet = new Tweet(null, null, null, "I hate going to the cinema, it's awfull.", null, null, 0, 0, null)
    println("Predicting, expecting bad :")
    println(model.predict(tweetToLabeledPoint(tweet, dictionary).features))
  }


  def tweetToLabeledPoint(tweet:Tweet, dictionary: Array[Word]):LabeledPoint = {

    val tweetTerms: Seq[String] = TextUtils.getTerms(tweet.text)
    val tweetTermCount = tweetTerms.length
    println("tweet terms: " + tweetTerms)

    var termFrequencyChain: String = ""

    for (dictionaryTerm <- dictionary) {
      var termCount: Int = 0
      for (tweetTerm <- tweetTerms) {
        if (dictionaryTerm.word == tweetTerm) {
          termCount += 1
          print(tweetTerm + ",")
        }
      }
      val termFrequency: Double = termCount.toDouble / tweetTermCount.toDouble
      termFrequencyChain += termFrequency.toString + ";"
    }

    val v:Vector = Vectors.dense(termFrequencyChain.split(';').map(_.toDouble))
    println(tweet.sentiment + ":" + v)
    new LabeledPoint(tweet.sentiment.toDouble, v)
  }


}
