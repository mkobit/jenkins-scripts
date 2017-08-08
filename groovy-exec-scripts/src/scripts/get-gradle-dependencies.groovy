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
                                                 .map { """  "${it.name}" to mapOf("group" to "${it.group}", "name" to "${it.name}", "version" to "${it.version}")""" }
                                                 .collect(kotlinExtMapOfCollector('jenkinsPluginArtifacts'))


final String extPluginDependencies = pluginArtifacts.stream().sorted(Comparator.comparing({ it.name }))
                                                 .map {"\"${it.name}\" to ${(it.pluginDependencies as List<String>).stream().map { "\"$it\""}.collect(Collectors.joining(', ', 'setOf(', ')'))}"}
                                                 .map { "  $it"}
                                                 .collect(kotlinExtMapOfCollector('jenkinsPluginDependencies'))

final String jenkinsVersion = Jenkins.instance.VERSION
final String groovyVersion = GroovySystem.version

final String extJenkinsCoreVersion = """extra["jenkinsCoreVersion"] = "$jenkinsVersion\""""
final String extJenkinsGroovyVersion = """extra["jenkinsGroovyVersion"] = "$groovyVersion\""""

final String extJenkinsCoreArtifact = '''extra["jenkinsCoreArtifact"] = mapOf("group" to "org.jenkins-ci.main", "name" to "jenkins-core", "version" to "${extra["jenkinsCoreVersion"]}", "ext" to "jar")'''
final String extJenkinsGroovyArtifact = '''extra["jenkinsGroovyArtifact"] = mapOf("group" to "org.codehaus.groovy", "name" to "groovy", "version" to "${extra["jenkinsGroovyVersion"]}")'''

final String extJenkinsCoreLibraries = '''extra["jenkinsCoreLibraries"] = mapOf(
  "ssh-cli-auth" to mapOf("group" to "org.jenkins-ci.modules", "name" to "ssh-cli-auth", "version" to "1.4"),
  "sshd" to mapOf("group" to "org.jenkins-ci.modules", "name" to "sshd", "version" to "1.11")
)'''

final String extJenkinsTestDependencies = '''extra["jenkinsTestDependencies"] = mapOf(
  "jenkins-test-harness" to mapOf("group" to "org.jenkins-ci.main", "name" to "jenkins-test-harness", "version" to "2.24"),
  "jenkins-war" to mapOf("group" to "org.jenkins-ci.main", "name" to "jenkins-war", "version" to "${extra["jenkinsCoreVersion"]}"),
  "jenkins-war-for-test" to mapOf("group" to "org.jenkins-ci.main", "name" to "jenkins-war", "version" to "${extra["jenkinsCoreVersion"]}:war-for-test@jar")
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
