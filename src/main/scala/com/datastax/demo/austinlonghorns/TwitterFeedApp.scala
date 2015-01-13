package com.datastax.demo.austinlonghorns

import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext

/**
 * Created by patrick on 09/01/15.
 */
object TwitterFeedApp {

  def main(args: Array[String]) {

    val conf = new SparkConf()
      .setAppName(getClass.getSimpleName)
      .setMaster("spark://" + Settings.SparkMaster)
      .set("spark.executor.memory", Settings.SparkExecutorMemory)
      .set("spark.cores.max", Settings.SparkCoresMax)
      .set("spark.cassandra.connection.host", Settings.SparkCasssandraHost)
      .setJars(Settings.DeployJars)

    val ssc = new StreamingContext(conf, Settings.StreamingBatchInterval)

    // create stream and start
    val stream = new TwitterSocketStream(ssc)
    stream.start()

  }

}
