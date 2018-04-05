import java.util.logging.Logger
import jenkins.model.Jenkins
import org.jenkinsci.main.modules.sshd.SSHD

final Logger logger = Logger.getLogger('scripts.enable_cli_ssh')
final Jenkins jenkins = Jenkins.instance

final SSHD sshd = jenkins.getDescriptorByType(SSHD)
// Enable SSH for CLI commands - https://wiki.jenkins.io/display/JENKINS/Jenkins+CLI
if (sshd.port != 0) {
  logger.info { "Setting up CLI for 'SSH' protocol" }
  if (sshd.port == -1) {
    logger.info { 'Enabling previously disabled SSHD server' }
  } else {
    logger.info { "Changing SSH from ${sshd.port} to a random one".toString() }
  }
  sshd.port = 0
  sshd.save()
}
logger.info { "SSHD server running at port ${sshd.actualPort}" }
