name := """muscle-training-manager-api2"""
organization := "com.example"

version := "1.0-SNAPSHOT"
val AirFrameVersion = "20.12.1"
val CatsVersion = "2.2.0"
val CatsEffectVersion = "2.3.1"
val CirceVersion = "0.13.0"
val ConfigVersion = "1.4.1"
val DoobieVersion = "0.9.0"
val LogbackVersion = "1.2.3"
val PostgresVersion = "42.2.18"
val Specs2Version = "4.10.5"
val ZioVersion = "1.0.3"
lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.4"

libraryDependencies ++= Seq(
  guice, filters,
  "com.google.firebase" % "firebase-admin" % "6.8.1",
  "com.typesafe.play" %% "play-slick" % "5.0.0",
  "io.circe" %% "circe-generic" % CirceVersion,
  "io.circe" %% "circe-generic-extras" % CirceVersion,
  "io.circe" %% "circe-core" % CirceVersion,
  "io.circe" %% "circe-parser" % CirceVersion,
  "com.dripower" %% "play-circe" % "2712.0",
  "org.postgresql" % "postgresql" % PostgresVersion,
  "org.flywaydb" %% "flyway-play" % "6.0.0",
  //  "org.tpolecat" %% "doobie-core" % DoobieVersion,
  //  "org.tpolecat" %% "doobie-postgres" % DoobieVersion,
  "org.typelevel" %% "cats-core" % CatsVersion,
  "org.typelevel" %% "cats-effect" % CatsEffectVersion,
//  "dev.zio" %% "zio" % ZioVersion,
//  "dev.zio" %% "zio-streams" % ZioVersion,
//  "dev.zio" %% "zio-interop-cats" % "2.2.0.1",
  "com.typesafe" % "config" % ConfigVersion,
  "org.specs2" %% "specs2-core" % Specs2Version % "test",
  "ch.qos.logback" % "logback-classic" % LogbackVersion,
  "org.scalameta" %% "svm-subs" % "20.2.0",
  "javax.xml.bind" % "jaxb-api" % "2.3.0",
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
)