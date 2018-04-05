import java.util.logging.Logger
import jenkins.CLI
import jenkins.model.Jenkins

final Logger logger = Logger.getLogger('scripts.disable_cli_remoting')
final Jenkins jenkins = Jenkins.instance

final CLI cli = jenkins.getDescriptorByType(CLI)
// Disable the remoting protocol - https://jenkins.io/doc/book/managing/cli/
logger.info { "Disabling CLI over 'remoting' protocol" }
cli.enabled = false
cli.save()
