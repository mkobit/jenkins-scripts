import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.gradle.api.internal.HasConvention

buildscript {
  repositories {
    mavenCentral()
  }
  dependencies {
    // TODO: load from properties or script plugin
    classpath("org.junit.platform:junit-platform-gradle-plugin:1.0.1")
  }
}

plugins {
  id("com.github.ben-manes.versions") version "0.17.0"
  kotlin("jvm") version "1.1.51" apply false
}

apply {
  from("gradle/jenkinsDependencies.gradle.kts")
  from("gradle/dependencyUpdatesResolutionStrategy.gradle")
  from("gradle/junit5.gradle.kts")
}

group = "com.mkobit.jenkins"
version = "0.1.0"
description = "Scripts to be used with the automation and configuration of Jenkins"

tasks {
  "wrapper"(Wrapper::class) {
    gradleVersion = "4.3"
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

subprojects {
  pluginManager.withPlugin("java") {
    configure<JavaPluginConvention> {
      sourceCompatibility = JavaVersion.VERSION_1_9
      targetCompatibility = JavaVersion.VERSION_1_9
    }
  }
}
