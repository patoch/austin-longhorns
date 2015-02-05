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
    val dayFormat = new java.text.SimpleDateFormat("ddMMyyyy")
    val tsFormat = new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss")

    val tweetRDD = stream
      .map(_.split(";;"))
      .map( x => Tweet(x(0), dayFormat.parse(x(1)).toString,tsFormat.parse(x(1)), x(2), x(3), x(4), x(5).toInt, x(6).toInt, null))

    // save tweets
    tweetRDD.saveToCassandra(
      "austin_longhorns_ks",
      "tweets2")

    tweetRDD.saveToCassandra(
      "austin_longhorns_ks",
      "tweets_by_user_id2")

    tweetRDD.saveToCassandra(
      "austin_longhorns_ks",
      "tweets_by_retweet_count2")

    tweetRDD.saveToCassandra(
      "austin_longhorns_ks",
      "tweets_by_favorite_count2")


    ssc.start()
    ssc.awaitTermination()

  }

}
