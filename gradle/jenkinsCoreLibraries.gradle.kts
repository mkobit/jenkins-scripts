extra["jenkinsCoreVersion"] = "2.67"

val jenkinsCoreVersion: String by extra

extra["jenkinsCoreLibraries"] = mapOf(
  "jenkins-core" to "org.jenkins-ci.main:jenkins-core:$jenkinsCoreVersion",
  "ssh-cli-auth" to "org.jenkins-ci.modules:ssh-cli-auth:1.4",
  "sshd" to "org.jenkins-ci.modules:sshd:1.11"
)
