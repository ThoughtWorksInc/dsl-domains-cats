libraryDependencies += "org.typelevel" %%% "cats-free" % "1.4.0" % Test

libraryDependencies += "org.typelevel" %%% "cats-free" % "1.4.0" % Optional // For Scaladoc

libraryDependencies += "org.typelevel" %%% "cats-core" % "1.4.0"

libraryDependencies += "org.scalatest" %%% "scalatest" % "3.0.6-SNAP2" % Test

libraryDependencies += "com.thoughtworks.dsl" %%% "keywords-catch" % "1.3.1"

libraryDependencies += "com.thoughtworks.dsl" %%% "keywords-monadic" % "1.3.1"

libraryDependencies += "com.thoughtworks.dsl" %%% "keywords-return" % "1.3.1"

libraryDependencies += "com.thoughtworks.dsl" %%% "keywords-shift" % "1.3.1" % Optional

libraryDependencies += "com.thoughtworks.dsl" %%% "keywords-yield" % "1.3.1" % Optional

addCompilerPlugin("com.thoughtworks.dsl" %% "compilerplugins-bangnotation" % "1.3.1")

addCompilerPlugin("com.thoughtworks.dsl" %% "compilerplugins-reseteverywhere" % "1.3.1")


scalacOptions ++= {
  import Ordering.Implicits._
  if (VersionNumber(scalaVersion.value).numbers < Seq(2L, 12L)) {
    // Enable SAM types for Scala 2.11
    Some("-Xexperimental")
  } else {
    None
  }
}
