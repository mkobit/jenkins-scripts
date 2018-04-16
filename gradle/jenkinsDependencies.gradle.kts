extra["jenkinsGroovyVersion"] = "2.4.11"

extra["jenkinsGroovyArtifact"] = "org.codehaus.groovy:groovy-all:${extra["jenkinsGroovyVersion"]}"

extra["jenkinsCoreVersion"] = "2.107.2"

extra["jenkinsCoreArtifact"] = "org.jenkins-ci.main:jenkins-core:${extra["jenkinsCoreVersion"]}@jar"

extra["jenkinsCoreLibraries"] = mapOf(
  "instance-identity" to "org.jenkins-ci.modules:instance-identity:2.1",
  "ssh-cli-auth" to "org.jenkins-ci.modules:ssh-cli-auth:1.4",
  "sshd" to "org.jenkins-ci.modules:sshd:2.4"
)

extra["jenkinsTestDependencies"] = mapOf(
  "jenkins-test-harness" to "org.jenkins-ci.main:jenkins-test-harness:2.24",
  "jenkins-war" to "org.jenkins-ci.main:jenkins-war:${extra["jenkinsCoreVersion"]}@war"
)

extra["jenkinsPluginArtifacts"] = mapOf(
  "ace-editor"                             to "org.jenkins-ci.ui:ace-editor:1.1",
  "antisamy-markup-formatter"              to "org.jenkins-ci.plugins:antisamy-markup-formatter:1.5",
  "apache-httpcomponents-client-4-api"     to "org.jenkins-ci.plugins:apache-httpcomponents-client-4-api:4.5.3-2.1",
  "authentication-tokens"                  to "org.jenkins-ci.plugins:authentication-tokens:1.3",
  "blueocean"                              to "io.jenkins.blueocean:blueocean:1.5.0",
  "blueocean-autofavorite"                 to "org.jenkins-ci.plugins:blueocean-autofavorite:1.2.2",
  "blueocean-bitbucket-pipeline"           to "io.jenkins.blueocean:blueocean-bitbucket-pipeline:1.5.0",
  "blueocean-commons"                      to "io.jenkins.blueocean:blueocean-commons:1.5.0",
  "blueocean-config"                       to "io.jenkins.blueocean:blueocean-config:1.5.0",
  "blueocean-core-js"                      to "io.jenkins.blueocean:blueocean-core-js:1.5.0",
  "blueocean-dashboard"                    to "io.jenkins.blueocean:blueocean-dashboard:1.5.0",
  "blueocean-display-url"                  to "org.jenkins-ci.plugins:blueocean-display-url:2.2.0",
  "blueocean-events"                       to "io.jenkins.blueocean:blueocean-events:1.5.0",
  "blueocean-git-pipeline"                 to "io.jenkins.blueocean:blueocean-git-pipeline:1.5.0",
  "blueocean-github-pipeline"              to "io.jenkins.blueocean:blueocean-github-pipeline:1.5.0",
  "blueocean-i18n"                         to "io.jenkins.blueocean:blueocean-i18n:1.5.0",
  "blueocean-jira"                         to "io.jenkins.blueocean:blueocean-jira:1.5.0",
  "blueocean-jwt"                          to "io.jenkins.blueocean:blueocean-jwt:1.5.0",
  "blueocean-personalization"              to "io.jenkins.blueocean:blueocean-personalization:1.5.0",
  "blueocean-pipeline-api-impl"            to "io.jenkins.blueocean:blueocean-pipeline-api-impl:1.5.0",
  "blueocean-pipeline-editor"              to "io.jenkins.blueocean:blueocean-pipeline-editor:1.5.0",
  "blueocean-pipeline-scm-api"             to "io.jenkins.blueocean:blueocean-pipeline-scm-api:1.5.0",
  "blueocean-rest"                         to "io.jenkins.blueocean:blueocean-rest:1.5.0",
  "blueocean-rest-impl"                    to "io.jenkins.blueocean:blueocean-rest-impl:1.5.0",
  "blueocean-web"                          to "io.jenkins.blueocean:blueocean-web:1.5.0",
  "bouncycastle-api"                       to "org.jenkins-ci.plugins:bouncycastle-api:2.16.2",
  "branch-api"                             to "org.jenkins-ci.plugins:branch-api:2.0.19",
  "build-timeout"                          to "org.jenkins-ci.plugins:build-timeout:1.19",
  "cloudbees-bitbucket-branch-source"      to "org.jenkins-ci.plugins:cloudbees-bitbucket-branch-source:2.2.10",
  "cloudbees-folder"                       to "org.jenkins-ci.plugins:cloudbees-folder:6.4",
  "command-launcher"                       to "org.jenkins-ci.plugins:command-launcher:1.2",
  "config-file-provider"                   to "org.jenkins-ci.plugins:config-file-provider:2.18",
  "credentials"                            to "org.jenkins-ci.plugins:credentials:2.1.16",
  "credentials-binding"                    to "org.jenkins-ci.plugins:credentials-binding:1.16",
  "display-url-api"                        to "org.jenkins-ci.plugins:display-url-api:2.2.0",
  "docker-commons"                         to "org.jenkins-ci.plugins:docker-commons:1.11",
  "docker-workflow"                        to "org.jenkins-ci.plugins:docker-workflow:1.15.1",
  "durable-task"                           to "org.jenkins-ci.plugins:durable-task:1.22",
  "email-ext"                              to "org.jenkins-ci.plugins:email-ext:2.62",
  "external-monitor-job"                   to "org.jenkins-ci.plugins:external-monitor-job:1.7",
  "favorite"                               to "org.jvnet.hudson.plugins:favorite:2.3.1",
  "git"                                    to "org.jenkins-ci.plugins:git:3.8.0",
  "git-client"                             to "org.jenkins-ci.plugins:git-client:2.7.1",
  "git-server"                             to "org.jenkins-ci.plugins:git-server:1.7",
  "github"                                 to "com.coravy.hudson.plugins.github:github:1.29.0",
  "github-api"                             to "org.jenkins-ci.plugins:github-api:1.90",
  "github-branch-source"                   to "org.jenkins-ci.plugins:github-branch-source:2.3.3",
  "handlebars"                             to "org.jenkins-ci.ui:handlebars:1.1.1",
  "handy-uri-templates-2-api"              to "org.jenkins-ci.plugins:handy-uri-templates-2-api:2.1.6-1.0",
  "htmlpublisher"                          to "org.jenkins-ci.plugins:htmlpublisher:1.16",
  "jackson2-api"                           to "org.jenkins-ci.plugins:jackson2-api:2.8.11.1",
  "jenkins-design-language"                to "io.jenkins.blueocean:jenkins-design-language:1.5.0",
  "jira"                                   to "org.jenkins-ci.plugins:jira:2.5",
  "job-dsl"                                to "org.jenkins-ci.plugins:job-dsl:1.69",
  "jquery-detached"                        to "org.jenkins-ci.ui:jquery-detached:1.2.1",
  "jsch"                                   to "org.jenkins-ci.plugins:jsch:0.1.54.2",
  "junit"                                  to "org.jenkins-ci.plugins:junit:1.24",
  "kubernetes"                             to "org.csanchez.jenkins.plugins:kubernetes:1.5.2",
  "kubernetes-credentials"                 to "org.jenkinsci.plugins:kubernetes-credentials:0.3.1",
  "ldap"                                   to "org.jenkins-ci.plugins:ldap:1.20",
  "lockable-resources"                     to "org.6wind.jenkins:lockable-resources:2.2",
  "mailer"                                 to "org.jenkins-ci.plugins:mailer:1.21",
  "mapdb-api"                              to "org.jenkins-ci.plugins:mapdb-api:1.0.9.0",
  "matrix-auth"                            to "org.jenkins-ci.plugins:matrix-auth:2.2",
  "matrix-project"                         to "org.jenkins-ci.plugins:matrix-project:1.13",
  "mercurial"                              to "org.jenkins-ci.plugins:mercurial:2.3",
  "momentjs"                               to "org.jenkins-ci.ui:momentjs:1.1.1",
  "pam-auth"                               to "org.jenkins-ci.plugins:pam-auth:1.3",
  "pipeline-build-step"                    to "org.jenkins-ci.plugins:pipeline-build-step:2.7",
  "pipeline-github-lib"                    to "org.jenkins-ci.plugins:pipeline-github-lib:1.0",
  "pipeline-graph-analysis"                to "org.jenkins-ci.plugins:pipeline-graph-analysis:1.6",
  "pipeline-input-step"                    to "org.jenkins-ci.plugins:pipeline-input-step:2.8",
  "pipeline-milestone-step"                to "org.jenkins-ci.plugins:pipeline-milestone-step:1.3.1",
  "pipeline-model-api"                     to "org.jenkinsci.plugins:pipeline-model-api:1.2.8",
  "pipeline-model-declarative-agent"       to "org.jenkinsci.plugins:pipeline-model-declarative-agent:1.1.1",
  "pipeline-model-definition"              to "org.jenkinsci.plugins:pipeline-model-definition:1.2.8",
  "pipeline-model-extensions"              to "org.jenkinsci.plugins:pipeline-model-extensions:1.2.8",
  "pipeline-rest-api"                      to "org.jenkins-ci.plugins.pipeline-stage-view:pipeline-rest-api:2.10",
  "pipeline-stage-step"                    to "org.jenkins-ci.plugins:pipeline-stage-step:2.3",
  "pipeline-stage-tags-metadata"           to "org.jenkinsci.plugins:pipeline-stage-tags-metadata:1.2.8",
  "pipeline-stage-view"                    to "org.jenkins-ci.plugins.pipeline-stage-view:pipeline-stage-view:2.10",
  "pipeline-utility-steps"                 to "org.jenkins-ci.plugins:pipeline-utility-steps:2.0.2",
  "plain-credentials"                      to "org.jenkins-ci.plugins:plain-credentials:1.4",
  "pubsub-light"                           to "org.jenkins-ci.plugins:pubsub-light:1.12",
  "resource-disposer"                      to "org.jenkins-ci.plugins:resource-disposer:0.8",
  "scm-api"                                to "org.jenkins-ci.plugins:scm-api:2.2.6",
  "script-security"                        to "org.jenkins-ci.plugins:script-security:1.43",
  "sse-gateway"                            to "org.jenkins-ci.plugins:sse-gateway:1.15",
  "ssh-agent"                              to "org.jenkins-ci.plugins:ssh-agent:1.15",
  "ssh-credentials"                        to "org.jenkins-ci.plugins:ssh-credentials:1.13",
  "ssh-slaves"                             to "org.jenkins-ci.plugins:ssh-slaves:1.26",
  "structs"                                to "org.jenkins-ci.plugins:structs:1.14",
  "subversion"                             to "org.jenkins-ci.plugins:subversion:2.10.5",
  "timestamper"                            to "org.jenkins-ci.plugins:timestamper:1.8.9",
  "token-macro"                            to "org.jenkins-ci.plugins:token-macro:2.5",
  "variant"                                to "org.jenkins-ci.plugins:variant:1.1",
  "workflow-aggregator"                    to "org.jenkins-ci.plugins.workflow:workflow-aggregator:2.5",
  "workflow-api"                           to "org.jenkins-ci.plugins.workflow:workflow-api:2.27",
  "workflow-basic-steps"                   to "org.jenkins-ci.plugins.workflow:workflow-basic-steps:2.6",
  "workflow-cps"                           to "org.jenkins-ci.plugins.workflow:workflow-cps:2.48",
  "workflow-cps-global-lib"                to "org.jenkins-ci.plugins.workflow:workflow-cps-global-lib:2.9",
  "workflow-durable-task-step"             to "org.jenkins-ci.plugins.workflow:workflow-durable-task-step:2.19",
  "workflow-job"                           to "org.jenkins-ci.plugins.workflow:workflow-job:2.19",
  "workflow-multibranch"                   to "org.jenkins-ci.plugins.workflow:workflow-multibranch:2.17",
  "workflow-scm-step"                      to "org.jenkins-ci.plugins.workflow:workflow-scm-step:2.6",
  "workflow-step-api"                      to "org.jenkins-ci.plugins.workflow:workflow-step-api:2.14",
  "workflow-support"                       to "org.jenkins-ci.plugins.workflow:workflow-support:2.18",
  "ws-cleanup"                             to "org.jenkins-ci.plugins:ws-cleanup:0.34"
)

extra["jenkinsPluginDependencies"] = mapOf(
  "ace-editor" to setOf(),
  "antisamy-markup-formatter" to setOf(),
  "apache-httpcomponents-client-4-api" to setOf(),
  "authentication-tokens" to setOf(
    "credentials"
  ),
  "blueocean" to setOf(
    "blueocean-bitbucket-pipeline",
    "blueocean-commons",
    "blueocean-config",
    "blueocean-core-js",
    "blueocean-dashboard",
    "blueocean-events",
    "blueocean-git-pipeline",
    "blueocean-github-pipeline",
    "blueocean-i18n",
    "blueocean-jira",
    "blueocean-jwt",
    "blueocean-personalization",
    "blueocean-pipeline-api-impl",
    "blueocean-pipeline-editor",
    "blueocean-rest-impl",
    "blueocean-rest",
    "blueocean-web",
    "jenkins-design-language",
    "blueocean-autofavorite",
    "blueocean-display-url",
    "pipeline-build-step",
    "pipeline-milestone-step"
  ),
  "blueocean-autofavorite" to setOf(
    "workflow-api",
    "workflow-job",
    "branch-api",
    "git",
    "junit",
    "favorite"
  ),
  "blueocean-bitbucket-pipeline" to setOf(
    "blueocean-pipeline-api-impl",
    "apache-httpcomponents-client-4-api",
    "cloudbees-bitbucket-branch-source",
    "pubsub-light"
  ),
  "blueocean-commons" to setOf(),
  "blueocean-config" to setOf(
    "blueocean-commons",
    "blueocean-jwt",
    "blueocean-rest",
    "blueocean-web"
  ),
  "blueocean-core-js" to setOf(
    "jenkins-design-language"
  ),
  "blueocean-dashboard" to setOf(
    "blueocean-web"
  ),
  "blueocean-display-url" to setOf(
    "workflow-job",
    "workflow-multibranch",
    "scm-api",
    "display-url-api",
    "blueocean-rest"
  ),
  "blueocean-events" to setOf(
    "blueocean-pipeline-api-impl",
    "pubsub-light",
    "sse-gateway"
  ),
  "blueocean-git-pipeline" to setOf(
    "blueocean-pipeline-api-impl",
    "blueocean-pipeline-scm-api",
    "git"
  ),
  "blueocean-github-pipeline" to setOf(
    "blueocean-pipeline-api-impl",
    "apache-httpcomponents-client-4-api",
    "github-api",
    "github-branch-source",
    "jackson2-api",
    "pubsub-light"
  ),
  "blueocean-i18n" to setOf(
    "blueocean-rest"
  ),
  "blueocean-jira" to setOf(
    "blueocean-rest",
    "apache-httpcomponents-client-4-api",
    "jira"
  ),
  "blueocean-jwt" to setOf(
    "blueocean-commons",
    "mailer"
  ),
  "blueocean-personalization" to setOf(
    "blueocean-dashboard"
  ),
  "blueocean-pipeline-api-impl" to setOf(
    "blueocean-pipeline-scm-api",
    "blueocean-rest-impl",
    "workflow-api",
    "workflow-cps",
    "workflow-durable-task-step",
    "workflow-job",
    "workflow-multibranch",
    "workflow-step-api",
    "workflow-support",
    "branch-api",
    "credentials",
    "git",
    "github-branch-source",
    "htmlpublisher",
    "pipeline-build-step",
    "pipeline-graph-analysis",
    "pipeline-input-step",
    "pipeline-stage-step",
    "plain-credentials",
    "scm-api",
    "pipeline-model-definition"
  ),
  "blueocean-pipeline-editor" to setOf(
    "blueocean-commons",
    "blueocean-dashboard",
    "blueocean-pipeline-api-impl",
    "blueocean-rest",
    "pipeline-model-definition"
  ),
  "blueocean-pipeline-scm-api" to setOf(
    "blueocean-rest",
    "workflow-multibranch",
    "branch-api",
    "credentials",
    "pubsub-light"
  ),
  "blueocean-rest" to setOf(
    "blueocean-commons"
  ),
  "blueocean-rest-impl" to setOf(
    "blueocean-jwt",
    "blueocean-rest",
    "blueocean-web",
    "cloudbees-folder",
    "credentials",
    "display-url-api",
    "junit",
    "mailer",
    "favorite"
  ),
  "blueocean-web" to setOf(
    "blueocean-core-js",
    "blueocean-rest",
    "jenkins-design-language",
    "variant"
  ),
  "bouncycastle-api" to setOf(),
  "branch-api" to setOf(
    "cloudbees-folder",
    "scm-api",
    "structs"
  ),
  "build-timeout" to setOf(
    "naginator",
    "token-macro"
  ),
  "cloudbees-bitbucket-branch-source" to setOf(
    "apache-httpcomponents-client-4-api",
    "display-url-api",
    "git",
    "handy-uri-templates-2-api",
    "mercurial",
    "scm-api",
    "structs"
  ),
  "cloudbees-folder" to setOf(
    "credentials"
  ),
  "command-launcher" to setOf(
    "script-security"
  ),
  "config-file-provider" to setOf(
    "cloudbees-folder",
    "credentials",
    "ssh-credentials",
    "structs",
    "token-macro"
  ),
  "credentials" to setOf(
    "structs"
  ),
  "credentials-binding" to setOf(
    "workflow-step-api",
    "credentials",
    "plain-credentials",
    "ssh-credentials",
    "structs"
  ),
  "display-url-api" to setOf(),
  "docker-commons" to setOf(
    "authentication-tokens",
    "credentials-binding",
    "credentials"
  ),
  "docker-workflow" to setOf(
    "workflow-cps",
    "workflow-durable-task-step",
    "docker-commons"
  ),
  "durable-task" to setOf(),
  "email-ext" to setOf(
    "workflow-job",
    "workflow-step-api",
    "config-file-provider",
    "junit",
    "mailer",
    "matrix-project",
    "script-security",
    "structs",
    "token-macro",
    "analysis-core"
  ),
  "external-monitor-job" to setOf(),
  "favorite" to setOf(
    "mailer",
    "matrix-project",
    "token-macro"
  ),
  "git" to setOf(
    "workflow-scm-step",
    "credentials",
    "git-client",
    "mailer",
    "matrix-project",
    "parameterized-trigger",
    "promoted-builds",
    "scm-api",
    "ssh-credentials",
    "structs",
    "token-macro"
  ),
  "git-client" to setOf(
    "apache-httpcomponents-client-4-api",
    "credentials",
    "jsch",
    "ssh-credentials",
    "structs"
  ),
  "git-server" to setOf(
    "git-client"
  ),
  "github" to setOf(
    "credentials",
    "display-url-api",
    "git",
    "github-api",
    "plain-credentials",
    "scm-api",
    "structs",
    "token-macro"
  ),
  "github-api" to setOf(
    "jackson2-api"
  ),
  "github-branch-source" to setOf(
    "github",
    "credentials",
    "display-url-api",
    "git",
    "github-api",
    "scm-api",
    "structs"
  ),
  "handlebars" to setOf(),
  "handy-uri-templates-2-api" to setOf(),
  "htmlpublisher" to setOf(
    "workflow-step-api",
    "matrix-project"
  ),
  "jackson2-api" to setOf(),
  "jenkins-design-language" to setOf(),
  "jira" to setOf(
    "workflow-job",
    "workflow-step-api",
    "branch-api",
    "junit",
    "mailer",
    "matrix-project",
    "p4",
    "script-security",
    "structs",
    "perforce"
  ),
  "job-dsl" to setOf(
    "structs",
    "script-security",
    "vsphere-cloud",
    "config-file-provider",
    "managed-scripts"
  ),
  "jquery-detached" to setOf(),
  "jsch" to setOf(
    "ssh-credentials"
  ),
  "junit" to setOf(
    "workflow-api",
    "workflow-step-api",
    "script-security",
    "structs"
  ),
  "kubernetes" to setOf(
    "workflow-step-api",
    "durable-task",
    "variant",
    "kubernetes-credentials",
    "pipeline-model-extensions"
  ),
  "kubernetes-credentials" to setOf(
    "credentials",
    "plain-credentials"
  ),
  "ldap" to setOf(
    "mailer"
  ),
  "lockable-resources" to setOf(
    "workflow-step-api",
    "workflow-support",
    "mailer",
    "matrix-project",
    "script-security"
  ),
  "mailer" to setOf(
    "display-url-api"
  ),
  "mapdb-api" to setOf(),
  "matrix-auth" to setOf(
    "cloudbees-folder"
  ),
  "matrix-project" to setOf(
    "junit",
    "script-security"
  ),
  "mercurial" to setOf(
    "credentials",
    "jsch",
    "matrix-project",
    "multiple-scms",
    "scm-api",
    "ssh-credentials",
    "structs"
  ),
  "momentjs" to setOf(),
  "pam-auth" to setOf(),
  "pipeline-build-step" to setOf(
    "workflow-api",
    "workflow-step-api",
    "workflow-support",
    "script-security"
  ),
  "pipeline-github-lib" to setOf(
    "workflow-cps-global-lib",
    "git"
  ),
  "pipeline-graph-analysis" to setOf(
    "workflow-api",
    "workflow-cps",
    "workflow-job",
    "workflow-step-api",
    "workflow-support",
    "pipeline-input-step",
    "pipeline-stage-step",
    "structs"
  ),
  "pipeline-input-step" to setOf(
    "workflow-api",
    "workflow-step-api",
    "workflow-support"
  ),
  "pipeline-milestone-step" to setOf(
    "workflow-api",
    "workflow-step-api"
  ),
  "pipeline-model-api" to setOf(
    "workflow-step-api",
    "structs"
  ),
  "pipeline-model-declarative-agent" to setOf(
    "pipeline-model-extensions"
  ),
  "pipeline-model-definition" to setOf(
    "workflow-api",
    "workflow-basic-steps",
    "workflow-cps-global-lib",
    "workflow-cps",
    "workflow-durable-task-step",
    "workflow-multibranch",
    "workflow-scm-step",
    "workflow-support",
    "credentials-binding",
    "credentials",
    "docker-workflow",
    "git-client",
    "mailer",
    "pipeline-input-step",
    "pipeline-stage-step",
    "scm-api",
    "pipeline-model-api",
    "pipeline-model-declarative-agent",
    "pipeline-model-extensions",
    "pipeline-stage-tags-metadata"
  ),
  "pipeline-model-extensions" to setOf(
    "workflow-cps",
    "workflow-job",
    "credentials-binding",
    "credentials",
    "pipeline-model-api"
  ),
  "pipeline-rest-api" to setOf(
    "workflow-api",
    "workflow-job",
    "workflow-step-api",
    "workflow-support",
    "jackson2-api",
    "pipeline-graph-analysis",
    "pipeline-input-step",
    "pipeline-stage-step"
  ),
  "pipeline-stage-step" to setOf(
    "workflow-api",
    "workflow-step-api",
    "scm-api",
    "structs"
  ),
  "pipeline-stage-tags-metadata" to setOf(),
  "pipeline-stage-view" to setOf(
    "pipeline-rest-api",
    "workflow-job",
    "handlebars",
    "jquery-detached",
    "momentjs"
  ),
  "pipeline-utility-steps" to setOf(
    "workflow-api",
    "workflow-cps",
    "workflow-step-api",
    "scm-api",
    "script-security",
    "structs"
  ),
  "plain-credentials" to setOf(
    "credentials"
  ),
  "pubsub-light" to setOf(),
  "resource-disposer" to setOf(),
  "scm-api" to setOf(
    "structs"
  ),
  "script-security" to setOf(),
  "sse-gateway" to setOf(
    "pubsub-light"
  ),
  "ssh-agent" to setOf(
    "workflow-step-api",
    "bouncycastle-api",
    "credentials",
    "ssh-credentials"
  ),
  "ssh-credentials" to setOf(
    "credentials"
  ),
  "ssh-slaves" to setOf(
    "credentials",
    "ssh-credentials"
  ),
  "structs" to setOf(),
  "subversion" to setOf(
    "workflow-scm-step",
    "credentials",
    "mapdb-api",
    "scm-api",
    "ssh-credentials"
  ),
  "timestamper" to setOf(
    "workflow-step-api"
  ),
  "token-macro" to setOf(
    "workflow-step-api"
  ),
  "variant" to setOf(),
  "workflow-aggregator" to setOf(
    "pipeline-input-step",
    "workflow-job",
    "workflow-basic-steps",
    "workflow-durable-task-step",
    "workflow-api",
    "workflow-cps",
    "workflow-support",
    "workflow-cps-global-lib",
    "workflow-multibranch",
    "pipeline-stage-view",
    "workflow-scm-step",
    "workflow-step-api",
    "pipeline-model-definition",
    "pipeline-stage-step",
    "pipeline-build-step",
    "pipeline-milestone-step"
  ),
  "workflow-api" to setOf(
    "workflow-step-api",
    "scm-api",
    "structs"
  ),
  "workflow-basic-steps" to setOf(
    "workflow-api",
    "workflow-step-api",
    "mailer",
    "structs"
  ),
  "workflow-cps" to setOf(
    "workflow-api",
    "workflow-scm-step",
    "workflow-step-api",
    "workflow-support",
    "scm-api",
    "script-security",
    "structs",
    "support-core",
    "ace-editor",
    "jquery-detached"
  ),
  "workflow-cps-global-lib" to setOf(
    "workflow-cps",
    "workflow-scm-step",
    "cloudbees-folder",
    "git-client",
    "git-server",
    "scm-api",
    "script-security"
  ),
  "workflow-durable-task-step" to setOf(
    "workflow-api",
    "workflow-step-api",
    "workflow-support",
    "durable-task",
    "script-security",
    "structs"
  ),
  "workflow-job" to setOf(
    "workflow-api",
    "workflow-step-api",
    "workflow-support"
  ),
  "workflow-multibranch" to setOf(
    "workflow-api",
    "workflow-cps",
    "workflow-job",
    "workflow-scm-step",
    "workflow-step-api",
    "workflow-support",
    "branch-api",
    "cloudbees-folder",
    "scm-api",
    "script-security",
    "structs"
  ),
  "workflow-scm-step" to setOf(
    "workflow-step-api"
  ),
  "workflow-step-api" to setOf(
    "structs"
  ),
  "workflow-support" to setOf(
    "workflow-api",
    "workflow-step-api",
    "scm-api",
    "script-security"
  ),
  "ws-cleanup" to setOf(
    "workflow-durable-task-step",
    "matrix-project",
    "resource-disposer",
    "structs"
  )
)
