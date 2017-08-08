import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.gradle.api.internal.HasConvention

plugins {
  id("com.github.ben-manes.versions") version "0.15.0"
}

apply {
  from("gradle/jenkinsDependencies.gradle.kts")
  from("gradle/dependencyUpdatesResolutionStrategy.groovy")
}

group = "com.mkobit.jenkins"
version = "0.1.0"
description = "Scripts to be used with the automation and configuration of Jenkins"

tasks {
  "wrapper"(Wrapper::class) {
    gradleVersion = "4.1"
  }
  "dependencyUpdates"(DependencyUpdatesTask::class) {
    val dependencyUpdatesResolutionStrategy: groovy.lang.Closure<Any?> by project.extra
    resolutionStrategy = dependencyUpdatesResolutionStrategy
  }
}

allprojects {
  repositories {
    jcenter()
    maven {
      setUrl("https://repo.jenkins-ci.org/releases")
    }
  }
}
