import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.junit.platform.console.options.Details
import org.junit.platform.gradle.plugin.JUnitPlatformExtension

plugins {
  `java`
}

apply {
  plugin("org.junit.platform.gradle.plugin")
  plugin("org.jetbrains.kotlin.jvm")
}

val jenkinsGroovyVersion: String by rootProject.extra
val jenkinsCoreVersion: String by rootProject.extra
val jenkinsTestDependencies: Map<String, Map<String, String>> by rootProject.extra
val jenkinsGroovyArtifact: Map<String, String> by rootProject.extra
val jenkinsCoreArtifact: Map<String, String> by rootProject.extra
val jenkinsCoreLibraries: Map<String, Map<String, String>> by rootProject.extra
val jenkinsPluginArtifacts: Map<String, Map<String, String>> by rootProject.extra
val jenkinsPluginDependencies: Map<String, Set<String>> by rootProject.extra

val junitPlatformVersion: String by rootProject.extra
val junitTestImplementationArtifacts: Map<String, Map<String, String>> by rootProject.extra
val junitTestRuntimeOnlyArtifacts: Map<String, Map<String, String>> by rootProject.extra

repositories {
  mavenCentral()
}

fun toArtifactString(artifactNotation: Map<String, String>): String ="${artifactNotation["group"]}:${artifactNotation["name"]}:${artifactNotation["version"]}"

tasks.withType(KotlinCompile::class.java) {
  kotlinOptions.jvmTarget = "1.8"
}

dependencies {
  testImplementation(kotlin("stdlib-jre8"))
  testImplementation(kotlin("reflect"))
  testImplementation("org.assertj:assertj-core:3.8.0")
  testImplementation("com.nhaarman:mockito-kotlin:1.5.0")
  junitTestImplementationArtifacts.values.forEach {
    testImplementation(it)
  }
  junitTestRuntimeOnlyArtifacts.values.forEach {
    testRuntimeOnly(it)
  }
  jenkinsTestDependencies.values.forEach {
    testImplementation(it)
  }
  jenkinsPluginArtifacts.values.map { toArtifactString(it) }.forEach {
    testImplementation(it) {
      isForce = true
      artifact {
        name = this@testImplementation.name
        type = "jar"
      }
    }
    testRuntimeOnly(it) {
      isForce = true
      artifact {
        name = this@testRuntimeOnly.name
        type = "hpi"
      }
    }
  }
}

extensions.getByType(JUnitPlatformExtension::class.java).apply {
  platformVersion = junitPlatformVersion
  filters {
    engines {
      include("junit-jupiter", "junit-vintage-engine")
    }
  }
  logManager = "org.apache.logging.log4j.jul.LogManager"
  details = Details.TREE
}
