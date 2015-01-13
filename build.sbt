name := "austin-longhorns"

version := "1.0"

scalaVersion := "2.10.4"

exportJars := true

unmanagedJars in Compile <++= baseDirectory map { base =>
  val DSE_HOME = "/Users/patrick/installs/dse-4.6.0"
  val finder: PathFinder = (
      file(DSE_HOME+"/lib") ** "*.jar" +++
      file(DSE_HOME+"/resources/dse/lib") ** "*.jar" +++
      file(DSE_HOME+"/resources/driver/lib") ** "*.jar" +++
      file(DSE_HOME+"/resources/cassandra/lib") ** "*.jar" +++
      file(DSE_HOME+"/resources/spark/lib") ** "*.jar" +++
      file(DSE_HOME+"/resources/shark/lib") ** "*.jar" +++
      file(DSE_HOME+"/resources/hadoop/") ** "*.jar" +++
      file(DSE_HOME+"/resources/hadoop/lib/") ** "*.jar"
  )
  finder.classpath
}

mainClass in (Compile, packageBin) := Some("com.datastax.demo.austinlonghorns.TwitterFeedApp")