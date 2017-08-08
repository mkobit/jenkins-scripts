open class DockerCliBuild : DefaultTask() {

  @get:InputFile
  var dockerfile: File? = null

  @get:OutputFile
  var imageIdFile: File = this.project.file("${this.project.buildDir}/dockerImages/${this.name}")

  @TaskAction
  fun runBuild() {
    // Requires Docker 17.06.0-ce
    project.exec {
      commandLine("docker",
        "build",
        "--iidfile",
        imageIdFile,
        "--file",
        dockerfile!!.absolutePath,
        project.projectDir.absolutePath)
    }.rethrowFailure().assertNormalExitValue()
  }
}

apply {
  plugin<LifecycleBasePlugin>()
}

val initScriptConfiguration = "groovyInitScripts"
configurations {
  initScriptConfiguration()
}

dependencies {
  initScriptConfiguration(project(":groovy-init-scripts"))
}

tasks {
  val composeGroup = "Docker Compose Example"
  val buildAgent by creating(DockerCliBuild::class) {
    dockerfile = file("agent.dockerfile")
  }

  val copyScripts by creating(Copy::class) {
    val configuration = configurations[initScriptConfiguration]
    dependsOn(configuration)
    from(configuration.map { zipTree(it) }) {
      include("**/*.groovy")
      exclude("META-INF")
    }
    into("$buildDir/init.groovy.d")
  }

  val buildMaster by creating(DockerCliBuild::class) {
    dockerfile = file("master.dockerfile")
    dependsOn(copyScripts)
  }

  val buildNginx by creating(DockerCliBuild::class) {
    inputs.dir("nginx")
    dockerfile = file("nginx.dockerfile")
  }

  val buildImages by creating {
    description = "Builds Docker images for example"
    group = composeGroup
    dependsOn(buildAgent, buildMaster, buildNginx)
  }

  "dockerComposeUp"(Exec::class) {
    description = "Runs docker-compose up"
    group = composeGroup
    dependsOn(buildImages)
    commandLine("docker-compose", "up")
  }

  "dockerComposeDown"(Exec::class) {
    description = "Runs docker-compose down"
    group = composeGroup
    commandLine("docker-compose", "down")
  }

  "build" {
    dependsOn(buildImages)
  }
}
