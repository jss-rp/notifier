ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.2"

val AkkaVersion = "2.8.2"

lazy val root = (project in file("."))
  .settings(
    name := "notifier",
    idePackagePrefix := Some("com.jss.notifier"),
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
      "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
      "com.typesafe.akka" %% "akka-slf4j" % AkkaVersion,
      "com.typesafe.akka" %% "akka-http" % "10.5.2",
      "io.spray" %%  "spray-json" % "1.3.6",
      "ch.qos.logback" % "logback-classic" % "1.4.6"
    )
  )
