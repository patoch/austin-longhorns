package com.datastax.demo.austinlonghorns

import com.datastax.spark.connector.streaming._
import org.apache.spark.streaming.StreamingContext


/**
 * Created by patrick on 09/01/15.
 * Tweet format: data separated by semi-colon in the order of case class
 *
 */
class TwitterSocketStream(ssc: StreamingContext) extends Serializable {

  def start(): Unit = {

    // create stream
    val stream = ssc.socketTextStream(Settings.TweetSourceServerIP, Settings.TweetSourceServerPort)
    val format = new java.text.SimpleDateFormat("dd-MM-yyyy")

    val tweetRDD = stream.map(_.split(";")).map( x => Tweet(x(0), format.parse(x(1)), x(2), x(3), x(4), x(5), x(6), x(7).toInt, x(8).toInt))

    tweetRDD.saveToCassandra(
      "austin_longhorns_ks",
      "tweets")


    // count words
    /*val words = stream.flatMap(_.split(" "))
    val pairs = words.map(word => (word, 1))
    val wordCounts = pairs.reduceByKey(_ + _)
    wordCounts.print()
    wordCounts.foreachRDD(println(_))

    wordCounts.saveToCassandra(
      "austin_longhorns_ks",
      "tweets",
      SomeColumns("id", "retweet_count"))*/


    ssc.start()
    ssc.awaitTermination()

  }

}
