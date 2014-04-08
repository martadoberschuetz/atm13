import play.Project._

name := "atm"

version := "1.0"

libraryDependencies ++= Seq(
  "org.scalaj" %% "scalaj-http" % "0.3.14"
)

playScalaSettings
