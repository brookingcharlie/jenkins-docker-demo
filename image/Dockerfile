FROM jenkins/jenkins:2.138

ENV JAVA_OPTS='-Djenkins.install.runSetupWizard=false'

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

COPY custom.groovy /usr/share/jenkins/ref/init.groovy.d/custom.groovy
