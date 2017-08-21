import hudson.PluginManager
import hudson.model.UpdateCenter
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit
import java.util.logging.Logger
import jenkins.model.Jenkins

/*
 * Example list of plugins by Id to install
 *
 * println('[')
 * Jenkins.instance.pluginManager.activePlugins.collect {
 *   it.shortName
 * }.sort().each {
 *   print('  ')
 *   println("'$it',")
 * }
 * println(']')
 */
final plugins = [
  'ace-editor',
  'ansicolor',
  'ant',
  'antisamy-markup-formatter',
  'authentication-tokens',
  'blueocean',
  'blueocean-autofavorite',
  'blueocean-commons',
  'blueocean-config',
  'blueocean-dashboard',
  'blueocean-display-url',
  'blueocean-events',
  'blueocean-git-pipeline',
  'blueocean-github-pipeline',
  'blueocean-i18n',
  'blueocean-jwt',
  'blueocean-personalization',
  'blueocean-pipeline-api-impl',
  'blueocean-pipeline-editor',
  'blueocean-rest',
  'blueocean-rest-impl',
  'blueocean-web',
  'bootstrap',
  'bouncycastle-api',
  'branch-api',
  'build-blocker-plugin',
  'build-name-setter',
  'build-timeout',
  'cloudbees-bitbucket-branch-source',
  'cloudbees-folder',
  'conditional-buildstep',
  'config-file-provider',
  'copyartifact',
  'credentials',
  'credentials-binding',
  'display-url-api',
  'docker-build-publish',
  'docker-build-step',
  'docker-commons',
  'docker-custom-build-environment',
  'docker-workflow',
  'durable-task',
  'email-ext',
  'extended-choice-parameter',
  'external-monitor-job',
  'favorite',
  'flexible-publish',
  'gatling',
  'git',
  'git-client',
  'git-server',
  'github',
  'github-api',
  'github-branch-source',
  'github-organization-folder',
  'greenballs',
  'handlebars',
  'htmlpublisher',
  'icon-shim',
  'jackson2-api',
  'javadoc',
  'job-dsl',
  'job-restrictions',
  'jobConfigHistory',
  'jquery',
  'jquery-detached',
  'junit',
  'ldap',
  'lockable-resources',
  'mailer',
  'managed-scripts',
  'mapdb-api',
  'matrix-auth',
  'matrix-project',
  'maven-plugin',
  'mercurial',
  'metrics',
  'momentjs',
  'multiple-scms',
  'pam-auth',
  'parameterized-trigger',
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
  'pipeline-utility-steps',
  'plain-credentials',
  'promoted-builds',
  'pubsub-light',
  'python',
  'rebuild',
  'resource-disposer',
  'run-condition',
  'scm-api',
  'script-security',
  'sse-gateway',
  'ssh-agent',
  'ssh-credentials',
  'ssh-slaves',
  'stash-pullrequest-builder',
  'structs',
  'subversion',
  'throttle-concurrents',
  'timestamper',
  'token-macro',
  'variant',
  'windows-slaves',
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
    job.get(3, TimeUnit.MINUTES)
  }
  jenkins.restart()
}
