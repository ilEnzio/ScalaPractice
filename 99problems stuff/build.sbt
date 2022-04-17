//name := "99problems stuff"
//
//version := "0.1"
//
//scalaVersion := "2.13.7"
//
//libraryDependencies += "org.typelevel" %% "cats-core" % "2.7.0"
//
//scalacOptions ++= Seq(
//  "-Xfatal-warnings"
//)

name := "99problems"

version := "0.1"

scalaVersion := "2.13.7"

//
//libraryDependencies +=
//  "org.typelevel" %% "cats-core" % "1.0.0"

libraryDependencies +=
  "org.typelevel" %% "cats-core" % "2.7.0"

libraryDependencies +=
  "org.typelevel" %% "cats-effect" % "3.3.8"


scalacOptions ++= Seq(
  //  "-Xfatal-warnings",
  //  "-Ypartial-unification"
)
scalacOptions ++= List("-Xsource:3")