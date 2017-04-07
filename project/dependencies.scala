package quark.project

import scala.collection.Seq

import sbt._, Keys._

object dependencies {
  private val argonautVersion   = "6.2-M3"
  private val monocleVersion    = "1.3.2"
  private val quasarVersion     = "14.5.7"
  private val pathyVersion      = "0.2.2"
  private val refinedVersion    = "0.5.0"
  private val scalacheckVersion = "1.12.5"
  private val scalazVersion     = "7.2.8"
  private val specs2Version     = "3.8.4-scalacheck-1.12.5"

  val core = Seq(
    "commons-codec"              %  "commons-codec"             % "1.10",
    "org.scalaz"                 %% "scalaz-core"               % scalazVersion,
    "org.quasar-analytics"       %% "quasar-foundation-internal" % quasarVersion,
    "org.quasar-analytics"       %% "quasar-foundation-internal" % quasarVersion     % "test" classifier "tests",
    "org.quasar-analytics"       %% "quasar-connector-internal" % quasarVersion,
    "org.quasar-analytics"       %% "quasar-connector-internal" % quasarVersion     % "test" classifier "tests",
    "org.quasar-analytics"       %% "quasar-core-internal"      % quasarVersion,
    "org.quasar-analytics"       %% "quasar-core-internal"      % quasarVersion     % "test" classifier "tests",
    "org.quasar-analytics"       %% "quasar-frontend-internal"  % quasarVersion,
    "org.quasar-analytics"       %% "quasar-frontend-internal"  % quasarVersion     % "test" classifier "tests",
    "com.github.julien-truffaut" %% "monocle-core"              % monocleVersion,
    "com.nimbusds"               %  "oauth2-oidc-sdk"           % "5.13",
    "com.slamdata"               %% "pathy-core"                % pathyVersion,
    "com.slamdata"               %% "pathy-argonaut"            % pathyVersion,
    "com.github.scopt"           %% "scopt"                     % "3.5.0",
    "eu.timepit"                 %% "refined"                   % refinedVersion,
    "eu.timepit"                 %% "refined-scalacheck"        % refinedVersion    % "test",
    "io.argonaut"                %% "argonaut"                  % argonautVersion,
    "io.argonaut"                %% "argonaut-monocle"          % argonautVersion,
    "io.argonaut"                %% "argonaut-scalaz"           % argonautVersion,
    "org.scalacheck"             %% "scalacheck"                % scalacheckVersion % "test",
    "org.specs2"                 %% "specs2-core"               % specs2Version     % "test",
    "org.specs2"                 %% "specs2-scalacheck"         % specs2Version     % "test",
    "org.scalaz"                 %% "scalaz-scalacheck-binding" % scalazVersion     % "test",
    "org.typelevel"              %% "scalaz-specs2"             % "0.4.0"           % "test"
  )
}
