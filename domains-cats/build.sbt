libraryDependencies += "org.typelevel" %%% "cats-free" % "2.0.0" % Test

libraryDependencies += "org.typelevel" %%% "cats-free" % "2.0.0" % Optional // For Scaladoc

libraryDependencies += "org.typelevel" %%% "cats-core" % "2.0.0"

libraryDependencies += "org.scalatest" %%% "scalatest" % "3.2.19" % Test

libraryDependencies += "com.thoughtworks.dsl" %%% "keywords-catch" % "1.5.5"

libraryDependencies += "com.thoughtworks.dsl" %%% "keywords-monadic" % "1.5.5"

libraryDependencies += "com.thoughtworks.dsl" %%% "keywords-return" % "1.5.5"

libraryDependencies += "com.thoughtworks.dsl" %%% "keywords-shift" % "1.5.5" % Optional

libraryDependencies += "com.thoughtworks.dsl" %%% "keywords-yield" % "1.5.5" % Optional

addCompilerPlugin(
  "com.thoughtworks.dsl" %% "compilerplugins-bangnotation" % "1.5.5"
)

addCompilerPlugin(
  "com.thoughtworks.dsl" %% "compilerplugins-reseteverywhere" % "1.5.5"
)

scalacOptions ++= {
  import Ordering.Implicits._
  if (VersionNumber(scalaVersion.value).numbers < Seq(2L, 12L)) {
    // Enable SAM types for Scala 2.11
    Some("-Xexperimental")
  } else {
    None
  }
}
