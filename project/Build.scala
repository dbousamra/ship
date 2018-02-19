import sbt._
import Keys._

object GameBuild extends Build {

  lazy val libgdxVersion = settingKey[String]("version of Libgdx library")

  lazy val core = Project(
    id = "core",
    base = file("."),
    settings = Settings.core
  )
}


object Settings {

  import GameBuild.libgdxVersion

  lazy val core = plugins.JvmPlugin.projectSettings ++ Seq(
    libraryDependencies ++= Seq(
      "com.esotericsoftware" % "kryonet" % "2.+",
      "com.badlogicgames.gdx" % "gdx" % libgdxVersion.value,
      "com.badlogicgames.gdx" % "gdx-box2d" % libgdxVersion.value,
      "com.badlogicgames.gdx" % "gdx-backend-lwjgl" % libgdxVersion.value,
      "com.badlogicgames.gdx" % "gdx-platform" % libgdxVersion.value classifier "natives-desktop",
      "com.badlogicgames.gdx" % "gdx-box2d-platform" % libgdxVersion.value classifier "natives-desktop"
    ),
    javacOptions ++= Seq(
      "-Xlint",
      "-encoding", "UTF-8",
      "-source", "1.8",
      "-target", "1.8"
    ),
    scalacOptions ++= Seq(
      "-Xlint",
      "-Ywarn-dead-code",
      "-Ywarn-value-discard",
      "-Ywarn-numeric-widen",
      "-Ywarn-unused",
      "-Ywarn-unused-import",
      "-unchecked",
      "-deprecation",
      "-feature",
      "-encoding", "UTF-8",
      "-target:jvm-1.8"
    ),
    cancelable := true,
    exportJars := true,
    fork in (Compile,run) := true
  )
}
