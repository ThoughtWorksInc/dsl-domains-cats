enablePlugins(Example)

import Ordering.Implicits._

libraryDependencies += "org.typelevel" %%% "cats-effect" % "2.0.0" % Optional

Test / sourceGenerators := {
  (sourceGenerators in Test).value.filterNot { sourceGenerator =>
    VersionNumber(scalaVersion.value).numbers >= Seq(2L, 13L) &&
    sourceGenerator.info
      .get(taskDefinitionKey)
      .exists { scopedKey: ScopedKey[_] =>
        scopedKey.key == generateExample.key
      }
  }
}
