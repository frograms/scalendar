organization := "com.frograms"

name := "scalendar"

version := "0.1.5-FROGRAMS"

scalaVersion := "2.12.1"

crossScalaVersions := Seq (
  "2.12.1",
  "2.11.8", "2.11.0",
  "2.10.3"
)

def isSvHigh(sv: String): Boolean = {
  (sv startsWith "2.11") || (sv startsWith "2.12")
}

scalacOptions <++= scalaVersion map {
  case sv if isSvHigh(sv) => 
    Seq("-feature", "-language:implicitConversions")
  case _ => Nil
}

libraryDependencies <++= scalaVersion {
  case sv if isSvHigh(sv) => Seq(
    "org.scala-lang.modules" %% "scala-xml" % "1.0.6" % "test",
    "org.scalatest" %% "scalatest" % "3.0.1" % "test")
  case _ =>
    Seq("org.scalatest" %% "scalatest" % "1.9" % "test")
}

publishTo <<= version { v =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

publishMavenStyle := true

publishArtifact in Test := false

unmanagedClasspath in Compile += Attributed.blank(new java.io.File("doesnotexist"))

pomIncludeRepository := { x => false }

pomExtra := (
  <url>https://github.com/frograms/scalendar</url>
  <licenses>
    <license>
      <name>The MIT License</name>
      <url>http://www.opensource.org/licenses/mit-license.php</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:frograms/scalendar.git</url>
    <connection>scm:git:git@github.com:frograms/scalendar.git</connection>
  </scm>
  <developers>
    <developer>
      <id>frograms</id>
      <name>Frograms inc.</name>
      <url>http://www.frograms.com/</url>
    </developer>
  </developers>
)
