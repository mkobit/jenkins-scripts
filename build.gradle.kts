import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
  id("com.github.ben-manes.versions") version "0.21.0"
  kotlin("jvm") version "1.3.31" apply false
  id("nebula.release") version "10.1.1"
}

apply {
  from("gradle/jenkinsDependencies.gradle.kts")
}

group = "com.mkobit.jenkins"
description = "Scripts to be used with the automation and configuration of Jenkins"

tasks {
  "wrapper"(Wrapper::class) {
    gradleVersion = "5.4.1"
  }
  dependencyUpdates {
    resolutionStrategy {
      componentSelection {
        all {
          val rejected = listOf("alpha", "beta", "rc", "cr", "m")
            .map { qualifier -> Regex("(?i).*[.-]$qualifier[.\\d-]*") }
            .any { it.matches(candidate.version) }
          if (rejected) {
            reject("Release candidate")
          }
        }
      }
    }
  }
}

allprojects {
  repositories {
    jcenter()
    maven {
      setUrl("https://repo.jenkins-ci.org/public")
    }
  }
}

subprojects {
  pluginManager.withPlugin("java") {
    configure<JavaPluginConvention> {
      sourceCompatibility = JavaVersion.VERSION_11
      targetCompatibility = JavaVersion.VERSION_11
    }
  }
}
