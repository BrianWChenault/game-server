name := "game-server"
scalaVersion := "2.13.2"
assemblyJarName in assembly := "game-server.jar"

libraryDependencies ++= Seq(
  "com.amazonaws" % "aws-lambda-java-events" % "3.1.0",
  "com.amazonaws" % "aws-lambda-java-core" % "1.2.1",
  "com.github.seratch" %% "awscala-dynamodb" % "0.8.4",
  "net.liftweb" %% "lift-json" % "3.4.1"
)

version := "0.1"

scalacOptions ++= Seq(
  "-deprecation",         // Emit warning and location for usages of deprecated APIs.
  "-encoding", "utf-8",   // Specify character encoding used by source files.
  "-explaintypes",        // Explain type errors in more detail.
  "-feature",             // Emit warning and location for usages of features that should be imported explicitly.
  "-unchecked",           // Enable additional warnings where generated code depends on assumptions.
)
