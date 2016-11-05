name := """hello-akka"""

version := "1.0"

scalaVersion := "2.11.8"

mainClass in assembly := Some("com.example.ApplicationMain")

val akkaVersion = "2.4.12"
val scalatestVersion = "2.2.4"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
  "org.scalatest" %% "scalatest" % scalatestVersion % "test")
