// shadow sbt-scalajs' crossProject(JSPlatform, JVMPlatform) and CrossType from Scala.js 0.6.x
import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

lazy val `domains-cats` =
  crossProject(JSPlatform, JVMPlatform)
    .crossType(CrossType.Pure)

lazy val `domains-catsJVM` = `domains-cats`.jvm
lazy val `domains-catsJS` = `domains-cats`.js

organization in ThisBuild := "com.thoughtworks.dsl"

scalacOptions in ThisBuild ++= {
  if (scalaBinaryVersion.value == "2.11") {
    Some("-Ybackend:GenBCode")
  } else {
    None
  }
}

skip in publish := true

parallelExecution in Global := {
  import Ordering.Implicits._
  VersionNumber(scalaVersion.value).numbers >= Seq(2L, 12L)
}
