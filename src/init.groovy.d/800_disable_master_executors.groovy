import jenkins.model.Jenkins
// Disables executors on the master

final Jenkins jenkins = Jenkins.instance
jenkins.numExecutors = 0

jenkins.save()
