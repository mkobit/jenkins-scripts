import hudson.security.csrf.DefaultCrumbIssuer
import java.util.logging.Logger
import jenkins.model.Jenkins

final Logger logger = Logger.getLogger('scripts.enable_csrf_protection')
final Jenkins jenkins = Jenkins.instance

// https://wiki.jenkins.io/display/JENKINS/CSRF+Protection
if (!jenkins.crumbIssuer) {
  logger.info { 'Crumb user is not already set - using default' }
  jenkins.crumbIssuer = new DefaultCrumbIssuer(true)
  jenkins.save()
} else {
  logger.info { 'Crumb user is already set, skpping configuration '}
}

