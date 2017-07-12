import jenkins.model.ProjectNamingStrategy
import jenkins.model.Jenkins

// Set the project naming convention for all projects
final Jenkins jenkins = Jenkins.instance

final String namingPattern = /[a-z0-9_.-]+/
final String namingDescription = "Project name must consists of only lower case characters, digits, underscores, period, and hyphens. The naming pattern is ${namingPattern}"
final forceExistingJobs = true

final ProjectNamingStrategy.PatternProjectNamingStrategy patternStrategy = new ProjectNamingStrategy.PatternProjectNamingStrategy(namingPattern, namingDescription, forceExistingJobs)

jenkins.projectNamingStrategy = patternStrategy
jenkins.save()
