import quark.project._, build._

import java.lang.Integer
import scala.{Predef, Seq, Some, sys}, Predef.assert

import sbt._, Keys._
import sbt.TestFrameworks.Specs2
import scoverage.ScoverageKeys

lazy val buildSettings = Seq(
  organization := "com.slamdata",
  scalaVersion := "2.11.8",
  scalaOrganization := "org.typelevel",
  initialize :=
    assert(
      Integer.parseInt(sys.props("java.specification.version").split("\\.")(1)) >= 8,
      "Java 8 or above required")
)

lazy val baseSettings = Seq(
  scalacOptions ++= BuildInfo.commonScalacOptions ++ Seq(
    "-target:jvm-1.8",
    "-Ybackend:GenBCode",
    "-Ydelambdafy:method",
    "-Ypartial-unification",
    "-Yliteral-types",
    "-Ywarn-unused-import"),
  scalacOptions in (Test, console) --= Seq(
    "-Yno-imports",
    "-Ywarn-unused-import"),
  scalacOptions in (Compile, doc) -= "-Xfatal-warnings",
  testOptions in (Test) += Tests.Argument(Specs2, "showtimes", "true"),
  console <<= console in Test,
  wartremoverErrors in (Compile, compile) ++= warts,
  resolvers ++= Seq(
    Resolver.sonatypeRepo("releases"),
    Resolver.sonatypeRepo("snapshots"),
    "MarkLogic" at "http://developer.marklogic.com/maven2"),
  addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.0"),
  addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full),
  ScoverageKeys.coverageHighlighting := true,
  exportJars := true
)

lazy val assemblySettings = Seq(
  test in assembly := {},

  assemblyMergeStrategy in assembly := {
    case PathList("META-INF", "io.netty.versions.properties") => MergeStrategy.last
    case PathList("org", "apache", "hadoop", "yarn", xs @ _*) => MergeStrategy.last
    case PathList("com", "google", "common", "base", xs @ _*) => MergeStrategy.last

    case other => (assemblyMergeStrategy in assembly).value apply other
  }
)

// TODO: sync up wth quasar
val warts = Warts.allBut(
  Wart.Any,
  Wart.AsInstanceOf,
  Wart.ExplicitImplicitTypes, //  - see puffnfresh/wartremover#226
  Wart.ImplicitConversion,    //  - see puffnfresh/wartremover#242
  Wart.IsInstanceOf,
  Wart.NoNeedForMonad,        //  - see puffnfresh/wartremover#159
  Wart.Nothing,
  Wart.Overloading,
  Wart.Product,               //  _ these two are highly correlated
  Wart.Serializable,          //  /
  Wart.ToString)

lazy val commonSettings = buildSettings ++ baseSettings ++ assemblySettings

lazy val core = (project in file("core"))
  .enablePlugins(BuildInfoPlugin)
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= dependencies.core,
    fork in run := true,
    connectInput in run := true,
    outputStrategy := Some(StdoutOutput),
    buildInfoKeys := Seq[BuildInfoKey](version, ScoverageKeys.coverageEnabled),
    buildInfoPackage := "quasar.advanced.build"
  )

lazy val root = (project in file("."))
  .aggregate(core)
  .dependsOn(core)
  .settings(commonSettings)
