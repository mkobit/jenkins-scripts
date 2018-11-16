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
/*
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
*/

val groovyScriptSource by configurations.creating

val synchronizeScripts by tasks.creating(Sync::class) {
  val main by sourceSets.getting
  val scriptFiles = files(main.allSource)
    .filter { it.extension == "groovy" }
  from(scriptFiles)
  into(file("$buildDir/groovy-scripts"))
}

artifacts {
  add(groovyScriptSource.name, synchronizeScripts.destinationDir) {
    builtBy(synchronizeScripts)
  }
}
