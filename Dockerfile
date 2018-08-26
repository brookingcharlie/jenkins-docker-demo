FROM jenkins/jenkins:2.138

ENV JAVA_OPTS='-Djenkins.install.runSetupWizard=false'

COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

COPY custom.groovy /usr/share/jenkins/ref/init.groovy.d/custom.groovy

USER root
# Install docker client (not daemon) for running docker commands via host socket.
RUN curl -fsSL 'https://download.docker.com/linux/static/stable/x86_64/docker-18.06.0-ce.tgz' \
  | tar xz --strip 1 -C /usr/local/bin docker/docker
# Add group to jenkins user to match socket permissions.
# Using root: not great but needed since socket on Docker for Mac's VM has root:root ownership
RUN usermod -a -G root jenkins
USER jenkins
