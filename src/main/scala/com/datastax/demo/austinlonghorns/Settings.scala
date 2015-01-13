package com.datastax.demo.austinlonghorns

import org.apache.spark.streaming.Seconds

/**
 * Created by patrick on 09/01/15.
 */
object Settings {

  val SparkMaster = "127.0.0.1:7077"
  val SparkCasssandraHost = "127.0.0.1"
  val TweetSourceServerIP = "127.0.0.1"
  val SparkExecutorMemory = "2g"
  val SparkCoresMax = "2"
  val DeployJars: Seq[String] = Seq("/Users/patrick/workspace_java/austin-longhorns/target/scala-2.10/austin-longhorns_2.10-1.0.jar")
  val StreamingBatchInterval = Seconds(5)
  val TweetSourceServerPort = 9999

}
