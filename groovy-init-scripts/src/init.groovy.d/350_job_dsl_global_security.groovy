import javaposse.jobdsl.plugin.GlobalJobDslSecurityConfiguration
import jenkins.model.Jenkins

final Jenkins jenkins = Jenkins.instance

final GlobalJobDslSecurityConfiguration jobDslSecurity = jenkins.getDescriptorByType(GlobalJobDslSecurityConfiguration)
// Because we use custom built helper classes that are classloaded with the Job DSL, we need to disable script security.
jobDslSecurity.useScriptSecurity = false
jobDslSecurity.save()
