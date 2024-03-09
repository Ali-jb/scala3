val scala3Version = "3.3.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "scalaproject",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies +=
       "org.scalatest" %% "scalatest" % "3.2.9" % Test
       
  )

//adding a custom setting
val isAwesome = settingKey[Boolean]("Some boolean setting")
isAwesome := true

val totally = settingKey[String]("rating of totalness of the statement")
totally := "100% totally"

val totallyAwesome = settingKey[String]("how awesome is this project")
totallyAwesome := totally.value + {
  println("checking project awesomness")
  if(isAwesome.value) "awesome." else "not awesome."
}

//add a custom task
lazy val checkAwesome = taskKey[Unit]("check project awesomeness....")

checkAwesome := {
  println("inside task")
  val _ = (compile in Compile).value //force test:compile first, and it should pass
  println("the project is " + totallyAwesome.value)
}

