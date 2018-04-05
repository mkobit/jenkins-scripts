// Modified version of https://github.com/SpencerMalone/JenkinsPipelineIntegration/blob/master/generate-gradle-build.groovy
// Run this in the script console and paste the output in the gradle/jenkinsDependencies.gradle.kts

import java.util.stream.Collector
import java.util.stream.Collectors
import jenkins.model.Jenkins

Collector<CharSequence, ?, String> kotlinExtCollector(String collectionMethod, String extKeyword) {
  final sep = System.lineSeparator()
  return Collectors.joining(
    ',' + sep,
    """extra["${extKeyword}"] = $collectionMethod($sep""",
    sep + ')')
}

Collector<CharSequence, ?, String> kotlinExtMapOfCollector(String extKeyword) {
  return kotlinExtCollector('mapOf', extKeyword)
}

Collector<CharSequence, ?, String> kotlinExtListOfCollector(String extKeyword) {
  return kotlinExtCollector('listOf', extKeyword)
}

String formatPluginDependencies(final List<String> pluginDependencies) {
  String setOfOutput
  if (pluginDependencies.isEmpty()) {
    setOfOutput = 'setOf()'
  } else {
    setOfOutput = pluginDependencies.stream()
                                    .map { "      \"$it\""}
                                    .collect(Collectors.joining(',' + System.lineSeparator(), 'setOf(' + System.lineSeparator(), System.lineSeparator() + '  )'))
  }

  return setOfOutput
}

final List<Map<String, Object>> pluginArtifacts = Jenkins.instance.pluginManager.plugins.collect {
  it.manifest.mainAttributes
}.collect {
  final List<String> pluginDependencies = it.getValue('Plugin-Dependencies')?.split(',')?.collect {
    it.split(':')[0]
  } ?: []
  return [
    group: it.getValue('Group-Id'),
    name: it.getValue('Short-Name'),
    version: it.getValue('Plugin-Version'),
    pluginDependencies: pluginDependencies
  ]
}
final String extJenkinsPluginArtifacts = pluginArtifacts.stream()
                                                 .sorted(Comparator.comparing({ it.name }))
                                                 .map { String.format('''  %-40s to "%s:%s:%s"''', "\"${it.name}\"", it.group, it.name, it.version) }
                                                 .collect(kotlinExtMapOfCollector('jenkinsPluginArtifacts'))


final String extPluginDependencies = pluginArtifacts.stream().sorted(Comparator.comparing({ it.name }))
                                                 .map { String.format('  %s to %s', "\"${it.name}\"", formatPluginDependencies(it.pluginDependencies as List<String>)) }
                                                 .collect(kotlinExtMapOfCollector('jenkinsPluginDependencies'))

final String jenkinsVersion = Jenkins.instance.VERSION
final String groovyVersion = GroovySystem.version

final String extJenkinsCoreVersion = """extra["jenkinsCoreVersion"] = "$jenkinsVersion\""""
final String extJenkinsGroovyVersion = """extra["jenkinsGroovyVersion"] = "$groovyVersion\""""

final String extJenkinsCoreArtifact = '''extra["jenkinsCoreArtifact"] = "org.jenkins-ci.main:jenkins-core:${extra["jenkinsCoreVersion"]}@jar"'''
final String extJenkinsGroovyArtifact = '''extra["jenkinsGroovyArtifact"] = "org.codehaus.groovy:groovy-all:${extra["jenkinsGroovyVersion"]}"'''

final String extJenkinsCoreLibraries = '''extra["jenkinsCoreLibraries"] = mapOf(
  "instance-identity" to "org.jenkins-ci.modules:instance-identity:2.1",
  "ssh-cli-auth" to "org.jenkins-ci.modules:ssh-cli-auth:1.4",
  "sshd" to "org.jenkins-ci.modules:sshd:2.4"
)'''

final String extJenkinsTestDependencies = '''extra["jenkinsTestDependencies"] = mapOf(
  "jenkins-test-harness" to "org.jenkins-ci.main:jenkins-test-harness:2.24",
  "jenkins-war" to "org.jenkins-ci.main:jenkins-war:${extra["jenkinsCoreVersion"]}@war"
)'''

[
  extJenkinsGroovyVersion,
  extJenkinsGroovyArtifact,
  extJenkinsCoreVersion,
  extJenkinsCoreArtifact,
  extJenkinsCoreLibraries,
  extJenkinsTestDependencies,
  extJenkinsPluginArtifacts,
  extPluginDependencies
].forEach {
  println(it)
  println()
}
