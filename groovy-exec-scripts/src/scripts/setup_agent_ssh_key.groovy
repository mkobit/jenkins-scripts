import com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey
import com.cloudbees.plugins.credentials.CredentialsScope
import com.cloudbees.plugins.credentials.SystemCredentialsProvider
import com.cloudbees.plugins.credentials.domains.Domain
import jenkins.model.Jenkins

final jenkins = Jenkins.instance
final globalDomain = Domain.global()
final credentialsStore = jenkins.getExtensionList(SystemCredentialsProvider)[0].store
final username = 'jenkins'
final credentialsId = 'Localhost-SshPrivateKey-AgentProvisioning'

final privateKey = new BasicSSHUserPrivateKey.DirectEntryPrivateKeySource('''
INSERT PRIVATE KEY
''')

final agentSshCredentials = new BasicSSHUserPrivateKey(
  CredentialsScope.GLOBAL,
  credentialsId,
  username,
  privateKey,
  '',
  'SSH key used for provisioning agents'
)

credentialsStore.addCredentials(globalDomain, agentSshCredentials)
