ARG core_version
FROM jenkins/jenkins:$core_version

COPY build/init.groovy.d/* /usr/share/jenkins/ref/init.groovy.d/
