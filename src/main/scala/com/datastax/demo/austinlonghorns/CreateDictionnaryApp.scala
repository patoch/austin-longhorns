package com.datastax.demo.austinlonghorns

import com.datastax.spark.connector._
import org.apache.spark.SparkContext._
import org.apache.spark.{SparkConf, SparkContext}


/**
 * Created by patrick on 15/01/15.
 */
object CreateDictionnaryApp {

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

    val tweetRDD = sc.cassandraTable[Tweet](keyspace, "tweets2")
    val tokenRDD = tweetRDD.flatMap(tweet => TextUtils.getTerms(tweet.text))
    val wordCountRDD = tokenRDD.map(word => (word, 1)).reduceByKey(_+_)
    val insertRDD = wordCountRDD.map({case (word:String, count:Int) => ("default", word, count)})

    //val significantWordCountRDD = wordCountRDD.filter({case (word:String, count:Int) => count >3})
    insertRDD.saveToCassandra(keyspace, "dictionnary")

  }




}
