import hudson.PluginManager
import hudson.model.UpdateCenter
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit
import java.util.logging.Logger
import jenkins.model.Jenkins

// Example list of plugins by Id to install
//
// println('[')
// Jenkins.instance.pluginManager.activePlugins.collect {
//   it.shortName
// }.sort().each {
//   print('  ')
//   println("'$it',")
// }
// println(']')
final plugins = [
    'ace-editor',
    'antisamy-markup-formatter',
    'apache-httpcomponents-client-4-api',
    'authentication-tokens',
    'blueocean',
    'blueocean-autofavorite',
    'blueocean-bitbucket-pipeline',
    'blueocean-commons',
    'blueocean-config',
    'blueocean-core-js',
    'blueocean-dashboard',
    'blueocean-display-url',
    'blueocean-events',
    'blueocean-git-pipeline',
    'blueocean-github-pipeline',
    'blueocean-i18n',
    'blueocean-jira',
    'blueocean-jwt',
    'blueocean-personalization',
    'blueocean-pipeline-api-impl',
    'blueocean-pipeline-editor',
    'blueocean-pipeline-scm-api',
    'blueocean-rest',
    'blueocean-rest-impl',
    'blueocean-web',
    'bouncycastle-api',
    'branch-api',
    'build-timeout',
    'cloudbees-bitbucket-branch-source',
    'cloudbees-folder',
    'command-launcher',
    'config-file-provider',
    'credentials',
    'credentials-binding',
    'display-url-api',
    'docker-commons',
    'docker-workflow',
    'durable-task',
    'email-ext',
    'external-monitor-job',
    'favorite',
    'git',
    'git-client',
    'git-server',
    'github',
    'github-api',
    'github-branch-source',
    'handlebars',
    'handy-uri-templates-2-api',
    'htmlpublisher',
    'jackson2-api',
    'jenkins-design-language',
    'jira',
    'job-dsl',
    'jquery-detached',
    'jsch',
    'junit',
    'kubernetes',
    'kubernetes-credentials',
    'ldap',
    'lockable-resources',
    'mailer',
    'mapdb-api',
    'matrix-auth',
    'matrix-project',
    'mercurial',
    'momentjs',
    'pam-auth',
    'pipeline-build-step',
    'pipeline-github-lib',
    'pipeline-graph-analysis',
    'pipeline-input-step',
    'pipeline-milestone-step',
    'pipeline-model-api',
    'pipeline-model-declarative-agent',
    'pipeline-model-definition',
    'pipeline-model-extensions',
    'pipeline-rest-api',
    'pipeline-stage-step',
    'pipeline-stage-tags-metadata',
    'pipeline-stage-view',
    'plain-credentials',
    'pubsub-light',
    'resource-disposer',
    'scm-api',
    'script-security',
    'sse-gateway',
    'ssh-agent',
    'ssh-credentials',
    'ssh-slaves',
    'structs',
    'subversion',
    'timestamper',
    'token-macro',
    'variant',
    'workflow-aggregator',
    'workflow-api',
    'workflow-basic-steps',
    'workflow-cps',
    'workflow-cps-global-lib',
    'workflow-durable-task-step',
    'workflow-job',
    'workflow-multibranch',
    'workflow-scm-step',
    'workflow-step-api',
    'workflow-support',
    'ws-cleanup',
]

final Logger logger = Logger.getLogger(this.getClass().name)
final Jenkins jenkins = Jenkins.instance
final PluginManager pluginManager = jenkins.pluginManager
final UpdateCenter updateCenter = jenkins.updateCenter
updateCenter.updateAllSites()

final List<Future<UpdateCenter.UpdateCenterJob>> updateCenterJobs = plugins.findAll {
  logger.info("Plugin: $it")
  return !pluginManager.getPlugin(it)
}.collect {
  updateCenter.getPlugin(it)
}.findAll {
  it != null
}.collect {
  it.deploy()
}

plugins.each {
  logger.info("Plugin: $it")
  if (!pluginManager.getPlugin(it)) {
    logger.info("Checking UpdateCenter for $it")
    final plugin = updateCenter.getPlugin(it)
    if (plugin) {
      logger.info("Installing $it")
      plugin.deploy()
    }
  }
}

if (!updateCenterJobs.empty) {
  logger.info("${updateCenterJobs.size()} plugins were installed, wait on each to complete installation.")
  for (job in updateCenterJobs) {
    job.get(6, TimeUnit.MINUTES)
  }
  jenkins.restart()
}
