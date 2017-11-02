plugins {
  groovy
}

val jenkinsGroovyVersion: String by rootProject.extra
val jenkinsCoreVersion: String by rootProject.extra
val jenkinsTestDependencies: Map<String, Map<String, String>> by rootProject.extra
val jenkinsGroovyArtifact: Map<String, String> by rootProject.extra
val jenkinsCoreArtifact: Map<String, String> by rootProject.extra
val jenkinsCoreLibraries: Map<String, Map<String, String>> by rootProject.extra
val jenkinsPluginArtifacts: Map<String, Map<String, String>> by rootProject.extra
val jenkinsPluginDependencies: Map<String, Set<String>> by rootProject.extra

dependencies {
  "compileOnly"(jenkinsGroovyArtifact)
  (listOf(jenkinsCoreArtifact) + jenkinsPluginArtifacts.values + jenkinsCoreLibraries.values).forEach { dependency ->
    "compileOnly"(dependency)
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
