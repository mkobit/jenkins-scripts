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


tasks {
  val composeGroup = "Docker Compose Example"
  val buildAgent by creating(DockerCliBuild::class) {
    dockerfile = file("agent.dockerfile")
  }

  val buildMaster by creating(DockerCliBuild::class) {
    dockerfile = file("master.dockerfile")
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

  // TODO: use 'by getting {}' in next release of Gradle Kotlin
  val build by this
  build.dependsOn(buildImages)
}
