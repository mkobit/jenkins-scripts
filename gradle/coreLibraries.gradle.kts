extra["coreVersion"] = "2.67"

val coreVersion: String by extra

extra["coreLibraries"] = mapOf(
  "jenkins-core" to "org.jenkins-ci.main:jenkins-core:$coreVersion",
  "ssh-cli-auth" to "org.jenkins-ci.modules:ssh-cli-auth:1.4",
  "sshd" to "org.jenkins-ci.modules:sshd:1.11"
)
