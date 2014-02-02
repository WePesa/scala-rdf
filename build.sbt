name := """scala-rdf"""

version := "0.1"

scalaVersion := "2.10.2"

scalacOptions ++= Seq("-unchecked", "-deprecation")

libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.1" % "test"

libraryDependencies += "org.openrdf.sesame" % "sesame-runtime" % "2.7.9"

libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.0"

libraryDependencies += "org.slf4j" % "slf4j-log4j12" % "1.7.0"
