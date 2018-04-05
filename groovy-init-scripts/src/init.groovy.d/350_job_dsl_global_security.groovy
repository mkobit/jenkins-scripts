import javaposse.jobdsl.plugin.GlobalJobDslSecurityConfiguration
import jenkins.model.Jenkins

final Jenkins jenkins = Jenkins.instance

final GlobalJobDslSecurityConfiguration jobDslSecurity = jenkins.getDescriptorByType(GlobalJobDslSecurityConfiguration)
jobDslSecurity.useScriptSecurity = true
jobDslSecurity.save()
