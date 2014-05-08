name := """scala-rdf"""

version := "0.1"

scalaVersion := "2.11.0"

scalacOptions ++= Seq("-unchecked", "-deprecation")

libraryDependencies += "org.scala-lang" % "scala-library" % "2.11.0"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.1.5" % "test"

libraryDependencies += "org.openrdf.sesame" % "sesame-runtime" % "2.7.10"

libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.6"

libraryDependencies += "org.slf4j" % "slf4j-log4j12" % "1.7.6"
