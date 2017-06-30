import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.gradle.api.internal.HasConvention

plugins {
  groovy
  id("com.github.ben-manes.versions") version "0.15.0"
}

apply {
  from("gradle/coreLibraries.gradle.kts")
  from("gradle/plugins.gradle.kts")
  from("gradle/dependencyUpdatesResolutionStrategy.groovy")
}

group = "com.mkobit.jenkins"
version = "0.1.0"
description = "Scripts to be used with initialization and other execution"

tasks {
  "wrapper"(Wrapper::class) {
    gradleVersion = "4.0"
  }
  "dependencyUpdates"(DependencyUpdatesTask::class) {
    val dependencyUpdatesResolutionStrategy: groovy.lang.Closure<Any?> by project.extra
    resolutionStrategy = dependencyUpdatesResolutionStrategy
  }
}

repositories {
  jcenter()
  maven {
    setUrl("https://repo.jenkins-ci.org/releases")
  }
}

val coreLibraries: Map<String, String> by extra
val plugins: Map<String, String> by extra

dependencies {
  compileOnly("org.codehaus.groovy:groovy-all:2.4.12")
  (coreLibraries + plugins).forEach { _, dependency ->
    compileOnly(dependency)
  }
}

val SourceSet.groovy: SourceDirectorySet
  get() = (this as HasConvention).convention.getPlugin(GroovySourceSet::class.java).groovy

configure<JavaPluginConvention> {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
  sourceSets {
    "main" {
      java.setSrcDirs(emptyList<Any>())
      resources.setSrcDirs(listOf("src/init.groovy.d"))
      groovy.setSrcDirs(emptyList<Any>())
    }
    "test" {
      java.setSrcDirs(emptyList<Any>())
      resources.setSrcDirs(emptyList<Any>())
      groovy.setSrcDirs(emptyList<Any>())
    }
  }
}
