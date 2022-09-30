// shadow sbt-scalajs' crossProject(JSPlatform, JVMPlatform) and CrossType from Scala.js 0.6.x
import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

lazy val `domains-cats` =
  crossProject(JSPlatform, JVMPlatform)
    .crossType(CrossType.Pure)

lazy val `domains-catsJVM` = `domains-cats`.jvm
lazy val `domains-catsJS` = `domains-cats`.js

ThisBuild / organization := "com.thoughtworks.dsl"

ThisBuild / scalacOptions ++= {
  if (scalaBinaryVersion.value == "2.11") {
    Some("-Ybackend:GenBCode")
  } else {
    None
  }
}

publish / skip := true

Global / parallelExecution := {
  import Ordering.Implicits._
  VersionNumber(scalaVersion.value).numbers >= Seq(2L, 12L)
}
