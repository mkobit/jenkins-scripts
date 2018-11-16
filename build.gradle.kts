import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.gradle.api.internal.HasConvention

plugins {
  id("com.github.ben-manes.versions") version "0.18.0"
  kotlin("jvm") version "1.2.50" apply false
}

apply {
  from("gradle/jenkinsDependencies.gradle.kts")
}

group = "com.mkobit.jenkins"
version = "0.1.0"
description = "Scripts to be used with the automation and configuration of Jenkins"

tasks {
  "wrapper"(Wrapper::class) {
    gradleVersion = "5.0-rc-3"
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
      sourceCompatibility = JavaVersion.VERSION_1_9
      targetCompatibility = JavaVersion.VERSION_1_9
    }
  }
}
