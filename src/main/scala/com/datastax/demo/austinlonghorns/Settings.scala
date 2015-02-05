package com.datastax.demo.austinlonghorns

import java.io.File

import com.typesafe.config.ConfigFactory
import org.apache.spark.streaming.Seconds

/**
 * Created by patrick on 09/01/15.
 */
object Settings {

  protected val config = ConfigFactory.parseFile(new File(System.getenv("HOME") + "/twitter-feed.conf"))


  val SparkMaster = config.getString("spark.master")//"127.0.0.1:7077"
  val SparkCasssandraHost = config.getString("spark.cassandra_host") // "127.0.0.1"
  val TweetSourceServerIP = config.getString("twitter.source_ip") //"127.0.0.1"
  val SparkExecutorMemory = config.getString("spark.executor_memory") //"2g"
  val SparkCoresMax = config.getString("spark.cores_max") //"2"
  val DeployJars: Seq[String] = Seq(
      System.getenv("HOME") + "/austin-longhorns_2.10-1.0.jar"
    )
  val StreamingBatchInterval = Seconds(5)
  val TweetSourceServerPort = 9999

}
