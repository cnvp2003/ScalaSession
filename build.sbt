name := """scalaSessions"""

version := "1.0"

scalaVersion := "2.11.12"

lazy val akkaVersion = "2.4.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  //"com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  //"com.typesafe.akka" %% "akka-persistence-typed" % akkaVersion,
  "org.scalatest" %% "scalatest" % "2.2.4" % "test"
  //"junit" % "junit" % "4.12" % "test"
)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")
