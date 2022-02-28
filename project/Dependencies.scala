import sbt._

object Dependencies {

  object Versions {
    val cats                  = "2.6.1"
    val catsEffect            = "3.2.8"
    val circe                 = "0.14.1"
    val CirceGenericExVersion = "0.14.1"
    val CirceConfigVersion    = "0.8.0"
    val doobie                = "1.0.0-RC1"
    val enumeratum            = "1.7.0"
    val flyway                = "8.3.0"
    val guava                 = "30.1.1-jre"
    val http4s                = "0.23.5"
    val mtl                   = "1.2.0"
    val prestoJDBC            = "0.254"
    val quillV                = "3.8.0"

    val scopt      = "4.0.1"
    val s3         = "0.6.0"
    val spark      = "3.1.2"
    val tapir      = "0.19.1"

    val slf4j          = "1.7.30"
    val logback        = "1.2.3"
    val logbackEncoder = "6.6"
    val log4Cats       = "2.1.1"

    // Compiler Plugins
    val kindProjector = "0.13.2"

    // Test
    val s3Mock    = "0.2.6"
    val scalaMock = "5.1.0"
    val scalaTest = "3.2.10"

    //Compiler
    val KindProjectorVersion = "0.13.1"
  }

  def library(group: String, artifact: String, version: String): ModuleID = group %% artifact % version

  def libraryNoScalaVersion(group: String, artifact: String, version: String): ModuleID = group % artifact % version

  lazy val circe      = library("io.circe", _, Versions.circe)
  lazy val doobie     = library("org.tpolecat", _, Versions.doobie)
  lazy val http4s     = library("org.http4s", _, Versions.http4s)
  lazy val quill      = library("io.getquill", _, _)
  lazy val typelevel  = library("org.typelevel", _, _)
  lazy val redis4Cats = library("dev.profunktor", _, _)
  lazy val spark      = library("org.apache.spark", _, _)
  lazy val tapir      = library("com.softwaremill.sttp.tapir", _, Versions.tapir)

  lazy val catsCore           = typelevel("cats-core", Versions.cats)
  lazy val catsEffect         = typelevel("cats-effect", Versions.catsEffect)
  lazy val catsMtl            = typelevel("cats-mtl", Versions.mtl)
  lazy val circeConfig        = "io.circe" %% "circe-config" % Versions.CirceConfigVersion
  lazy val circeCore          = circe("circe-core")
  lazy val circeGeneric       = circe("circe-generic")
  lazy val circeGenericExtras = circe("circe-generic-extras")
  lazy val circeLiteral       = circe("circe-literal")
  lazy val circeParser        = circe("circe-parser")
  lazy val doobieCore         = doobie("doobie-core")
  lazy val doobieHikari       = doobie("doobie-hikari")
  lazy val doobiePostgres     = doobie("doobie-postgres")
  lazy val doobieQuill        = doobie("doobie-quill")

  lazy val enumeratum      = "com.beachape" %% "enumeratum"       % Versions.enumeratum
  lazy val enumeratumCirce = "com.beachape" %% "enumeratum-circe" % Versions.enumeratum
  lazy val flyway          = "org.flywaydb"  % "flyway-core"      % Versions.flyway

  lazy val guava             = "com.google.guava" % "guava" % Versions.guava


  lazy val scopt              = "com.github.scopt" %% "scopt" % Versions.scopt
  lazy val sparkCore          = spark("spark-core", Versions.spark)
  lazy val sparkSql           = spark("spark-sql", Versions.spark)
  lazy val tapirCore          = tapir("tapir-core")
  lazy val tapirHttp4s        = tapir("tapir-http4s-server")
  lazy val tapirSwagger       = tapir("tapir-swagger-ui")
  lazy val tapirOpenAPI       = tapir("tapir-openapi-docs")
  lazy val tapirCirceYaml     = tapir("tapir-openapi-circe-yaml")
  lazy val tapirJsonCirce     = tapir("tapir-json-circe")
  lazy val tapirSwaggerBundle = tapir("tapir-swagger-ui-bundle")

  lazy val slf4jApi       = libraryNoScalaVersion("org.slf4j", "slf4j-api", Versions.slf4j)
  lazy val slf4jLog4j     = libraryNoScalaVersion("org.slf4j", "slf4j-log4j12", Versions.slf4j)
  lazy val log4catsCore   = typelevel("log4cats-core", Versions.log4Cats)
  lazy val log4Cats       = typelevel("log4cats-slf4j", Versions.log4Cats)
  lazy val natchezDatadog = "org.tpolecat" %% "natchez-datadog" % "0.1.5"
  lazy val natchezNoop    = "org.tpolecat" %% "natchez-noop"    % "0.1.5"
  lazy val natchezHttp4s  = "org.tpolecat" %% "natchez-http4s"  % "0.1.3"
  lazy val natchezJaeger  = "org.tpolecat" %% "natchez-jaeger"  % "0.1.5"

  // Compiler Plugins
  lazy val kindProjector = compilerPlugin(typelevel("kind-projector", Versions.kindProjector).cross(CrossVersion.full))

  // Test
  lazy val scalaMock = "org.scalamock" %% "scalamock" % Versions.scalaMock
  lazy val scalaTest = "org.scalatest" %% "scalatest" % Versions.scalaTest
  lazy val s3Mock    = "io.findify"    %% "s3mock"    % Versions.s3Mock % Test

  lazy val catsDeps        = Seq(catsCore, catsEffect, catsMtl)
  lazy val circeExtraDeps  = Seq(circeGenericExtras, circeConfig)
  lazy val circeCommonDeps = Seq(circeCore, circeGeneric, circeLiteral, circeParser)

  lazy val doobieDeps     = Seq(doobieCore, doobieHikari, doobiePostgres, doobieQuill)
  lazy val enumeratumDeps = Seq(enumeratum, enumeratumCirce)
  lazy val loggingDeps    = Seq(slf4jApi, slf4jLog4j, log4Cats, log4catsCore)
  lazy val natchezDeps    = Seq(natchezDatadog, natchezHttp4s, natchezJaeger, natchezNoop)

  lazy val sparkDeps      = Seq(sparkCore, sparkSql)
  lazy val tapirDeps      = Seq(tapirCore, tapirHttp4s, tapirSwagger, tapirOpenAPI, tapirCirceYaml, tapirJsonCirce, tapirSwaggerBundle)

  val apiDeps: Seq[ModuleID] = {
    Seq("com.amazonaws" % "aws-java-sdk" % "1.12.105") ++
      catsDeps ++
      circeCommonDeps ++
      circeExtraDeps ++
      loggingDeps ++
      natchezDeps ++
      Seq(s3Mock, scalaMock, scalaTest)
  }

  val commonDeps: Seq[ModuleID] = circeCommonDeps ++
    circeExtraDeps ++
    doobieDeps ++
    enumeratumDeps ++
    Seq(flyway) ++
    tapirDeps

  val sparkProjectDeps: Seq[ModuleID] = Seq("com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.12.2") ++ //needed to resolve cross build dependency conflict
    Seq("com.amazonaws" % "aws-java-sdk" % "1.11.1034") ++
    Seq(guava) ++
    sparkDeps ++
    Seq(s3Mock) ++
    Seq(scalaTest) ++
    Seq(scopt)
}
