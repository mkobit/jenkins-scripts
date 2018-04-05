import hudson.ExtensionList
import java.util.logging.Logger
import jenkins.AgentProtocol
import jenkins.model.Jenkins

final Logger logger = Logger.getLogger('scripts.disable_deprecated_jnlp_protocols')
final Jenkins jenkins = Jenkins.instance

// > This Jenkins instance uses deprecated protocols: JNLP-connect,JNLP2-connect.
// > It may impact stability of the instance. If newer protocol versions are supported by all system
// > components (agents, CLI and other clients), it is highly recommended to disable the
// > deprecated protocols. See Protocol Configuration.

final ExtensionList<AgentProtocol> allProtocols = AgentProtocol.all()
logger.info {
  "All available protocols: ${(allProtocols.collect { "${it.name} (${it.displayName})}" } as List<String>)}".toString()
}

logger.info { "Currently enabled protocols: ${jenkins.agentProtocols}".toString() }

final Set<String> nonDeprecatedProtocols = allProtocols.findAll {
  !it.deprecated
}.findAll {
  it.name != null
}.collect {
  it.name
}.toSet()

if (jenkins.agentProtocols != nonDeprecatedProtocols) {
  logger.info { "Setting enabled agent protocols to ${nonDeprecatedProtocols}".toString() }
  jenkins.agentProtocols = nonDeprecatedProtocols
} else {
  logger.info { "Currently enabled agent protocols are already set correctly" }
}
