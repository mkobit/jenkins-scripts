rootProject.name = "jenkins-scripts"

include(
  "docker-compose-example",
  "groovy-exec-scripts",
  "groovy-init-scripts"
)

rootProject.children.forEach { project ->
  project.buildFileName = "${project.name}.gradle.kts"
}
