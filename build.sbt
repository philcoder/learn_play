name := """learn-play"""

version := "0.0.1"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

resolvers += Resolver.sonatypeRepo("snapshots")

scalaVersion := "2.13.1"

libraryDependencies += guice
libraryDependencies += jdbc
libraryDependencies += evolutions

libraryDependencies += "org.postgresql" % "postgresql" % "42.2.6"

libraryDependencies += "org.playframework.anorm" %% "anorm" % "2.6.4"
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % Test

ThisBuild / scalacOptions ++= List("-encoding", "utf8", "-deprecation", "-feature", "-unchecked", "-Xfatal-warnings")
ThisBuild / javacOptions ++= List("-Xlint:unchecked", "-Xlint:deprecation", "-Werror")
