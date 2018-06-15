import buildsrc.DependencyInfo
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  java
  kotlin("jvm")
}

val jenkinsTestDependencies: Map<String, String> by rootProject.extra
val jenkinsGroovyArtifact: String by rootProject.extra
val jenkinsCoreArtifact: String by rootProject.extra
val jenkinsCoreLibraries: Map<String, String> by rootProject.extra
val jenkinsPluginArtifacts: Map<String, String> by rootProject.extra
val jenkinsPluginDependencies: Map<String, Set<String>> by rootProject.extra

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
  testImplementation(DependencyInfo.assertJCore)
  testImplementation(DependencyInfo.mockitoKotlin)
  DependencyInfo.junitTestImplementationArtifacts.forEach {
    testImplementation(it)
  }
  DependencyInfo.junitTestRuntimeOnlyArtifacts.forEach {
    testRuntimeOnly(it)
  }
  jenkinsTestDependencies.values.forEach {
    testImplementation(it)
  }
//  TODO: fix dependencies stuff
//  jenkinsPluginArtifacts.values.forEach {
//    testImplementation(it) {
//      isForce = true
//      artifact {
//        name = this@testImplementation.name
//        type = "jar"
//      }
//    }
//    testRuntimeOnly(it) {
//      isForce = true
//      artifact {
//        name = this@testRuntimeOnly.name
//        type = "hpi"
//      }
//    }
//  }
}

tasks {
  "test"(Test::class) {
    // After fixing dependencies
    enabled = false
    systemProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager")
    useJUnitPlatform {
      includeEngines("junit-jupiter", "junit-vintage-engine")
    }
  }
}
