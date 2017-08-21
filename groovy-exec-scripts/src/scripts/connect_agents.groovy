import hudson.plugins.sshslaves.SSHLauncher
import hudson.slaves.DumbSlave
import hudson.slaves.RetentionStrategy
import jenkins.model.Jenkins
import hudson.model.Node

void addNode(
  String agentName
) {
  final jenkins = Jenkins.instance
  final agentHostname = agentName
  final launcher = new SSHLauncher(
    agentHostname,
    22,
    'Localhost-SshPrivateKey-AgentProvisioning',
    '-Djava.io.tmpdir=/home/jenkins/tmp -XX:+UseG1GC -Dfile.encoding=UTF-8',
    null,
    null,
    null,
    180,
    6,
    30
  )
  final slave = new DumbSlave(
    agentName,
    "$agentName Docker",
    '/home/jenkins',
    '1',
    Node.Mode.NORMAL,
    '',
    launcher,
    RetentionStrategy.Always.INSTANCE,
    []
  )

  jenkins.addNode(slave)
}
