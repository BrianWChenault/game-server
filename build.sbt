name := "game-server"
scalaVersion := "2.13.2"
assemblyJarName in assembly := "game-server.jar"

libraryDependencies ++= Seq(
  "com.amazonaws" % "aws-lambda-java-events" % "2.2.6",
  "com.amazonaws" % "aws-lambda-java-core" % "1.2.0"
)

version := "0.1"

scalacOptions ++= Seq(
  "-deprecation",         // Emit warning and location for usages of deprecated APIs.
  "-encoding", "utf-8",   // Specify character encoding used by source files.
  "-explaintypes",        // Explain type errors in more detail.
  "-feature",             // Emit warning and location for usages of features that should be imported explicitly.
  "-unchecked",           // Enable additional warnings where generated code depends on assumptions.
)
