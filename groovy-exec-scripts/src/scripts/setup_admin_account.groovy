import hudson.model.User
import hudson.model.UserProperty
import hudson.security.FullControlOnceLoggedInAuthorizationStrategy
import hudson.security.HudsonPrivateSecurityRealm
import java.util.logging.Logger
import jenkins.model.Jenkins
import org.jenkinsci.main.modules.cli.auth.ssh.UserPropertyImpl

final logger = Logger.getLogger(this.getClass().name)
final jenkins = Jenkins.instance

// SSH is used for CLI authentication.
// This is here for simplification when using the CLI and other automation.
final String pubKey = '''ENTER PUB KEY'''

logger.info("creating local user 'admin'")

final hudsonRealm = new HudsonPrivateSecurityRealm(false)
User admin = hudsonRealm.createAccount('admin', 'password')
jenkins.securityRealm = hudsonRealm

// This is the actually the user property that controls CLI SSH authentication despite its name.
final UserProperty sshCliProperty = new UserPropertyImpl(pubKey)
admin.addProperty(sshCliProperty)
admin.save()

final strategy = new FullControlOnceLoggedInAuthorizationStrategy()
strategy.allowAnonymousRead = true
jenkins.authorizationStrategy = strategy
jenkins.save()
