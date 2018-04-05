plugins {
  groovy
}

val jenkinsTestDependencies: Map<String, String> by rootProject.extra
val jenkinsGroovyArtifact: String by rootProject.extra
val jenkinsCoreArtifact: String by rootProject.extra
val jenkinsCoreLibraries: Map<String, String> by rootProject.extra
val jenkinsPluginArtifacts: Map<String, String> by rootProject.extra
val jenkinsPluginDependencies: Map<String, Set<String>> by rootProject.extra

dependencies {
  compileOnly(jenkinsGroovyArtifact)
  (listOf(jenkinsCoreArtifact) + jenkinsPluginArtifacts.values + jenkinsCoreLibraries.values).forEach { dependency ->
    compileOnly(dependency)
  }
}

configure<JavaPluginConvention> {
  sourceSets {
    "main" {
      java.setSrcDirs(emptyList<Any>())
      resources.setSrcDirs(listOf("src/scripts"))
      groovy.setSrcDirs(emptyList<Any>())
    }
    "test" {
      java.setSrcDirs(emptyList<Any>())
      resources.setSrcDirs(emptyList<Any>())
      groovy.setSrcDirs(emptyList<Any>())
    }
  }
}
