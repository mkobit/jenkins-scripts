import jenkins.model.JenkinsLocationConfiguration

final JenkinsLocationConfiguration configuration = JenkinsLocationConfiguration.get()

configuration.adminAddress = 'Jenkins Admin <jenkins@example.com>'
configuration.url = 'http://localhost:8080'

configuration.save()
