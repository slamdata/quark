addSbtPlugin("org.wartremover"  % "sbt-wartremover" % "1.1.1")
addSbtPlugin("com.eed3si9n"     % "sbt-assembly"    % "0.14.3")
addSbtPlugin("org.scoverage"    % "sbt-scoverage"   % "1.3.5")
addSbtPlugin("com.eed3si9n"     % "sbt-buildinfo"   % "0.6.1")

val commonScalacOptions = Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-unchecked",
  "-Xfatal-warnings",
  "-Xfuture",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard")

scalacOptions ++= commonScalacOptions

buildInfoKeys := Seq[BuildInfoKey](
  "commonScalacOptions" -> (commonScalacOptions :+ "-Yno-imports"))

buildInfoPackage := "quark.project.build"

lazy val meta = project.in(file(".")).enablePlugins(BuildInfoPlugin)
