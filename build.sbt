name := """muscle-training-manager-api2"""
organization := "com.example"

version := "1.0-SNAPSHOT"
val CirceVersion = "0.13.0"
val MunitVersion = "0.7.20"
val LogbackVersion = "1.2.3"
val MunitCatsEffectVersion = "0.12.0"
val DoobieVersion = "0.9.0"
val PostgresVersion = "42.2.9"
val GuiceVersion = "4.2.3"
val AirFrameVersion = "20.12.1"
val circeVersion = "0.12.3"
val ZioVersion = "1.0.3"
val ConfigVersion = "1.4.1"
val Specs2Version = "4.10.5"
lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.4"

libraryDependencies ++= Seq(
  guice, filters,
  "com.google.firebase" % "firebase-admin" % "6.8.1",
  "org.postgresql" % "postgresql" % "42.2.18",
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
  "org.typelevel" %% "cats-core" % "2.1.1",
  "dev.zio" %% "zio" % ZioVersion,
  "dev.zio" %% "zio-streams" % ZioVersion,
  "dev.zio" %% "zio-interop-cats" % "2.2.0.1",
  "com.typesafe" % "config" % ConfigVersion,
  "org.specs2" %% "specs2-core" % Specs2Version % "test",
  "ch.qos.logback" % "logback-classic" % LogbackVersion,
  "org.scalameta" %% "svm-subs" % "20.2.0",
  "javax.xml.bind" % "jaxb-api" % "2.3.0",
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
)
javaOptions += "-Dakka.http.parsing.max-header-value-length=16k"