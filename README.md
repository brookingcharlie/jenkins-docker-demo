# Running Jenkins using a custom Docker image

Example of building a custom Docker image for running Jenkins.

This repo demonstrates use of Docker in two ways:

* **Running Jenkins in a Docker container**:
  We define a custom Docker image based on
  [jenkins/jenkins](https://hub.docker.com/r/jenkins/jenkins/),
  installing a list of plugins and configuring basic settings
  using a Groovy script against the Jenkins API.
* **Running other Docker containers from Jenkins**:
  Our `Dockerfile` also installs the docker client (not daemon) and
  configures permissions for it to access the host's socket.
  (We mount `/var/run/docker.sock` as a volume in `docker-compose.yml`.)

## Warning

These scripts are an example only and shouldn't be used on a public network.
They include a hard-coded admin password and several other design decisions
aimed at producing a compact demo rather than something production-grade.

## Usage

To build the custom Docker image:

    docker-compose build

To start/stop the Jenkins service:

    docker-compose up -d
    docker-compose down

Access Jenkins in your browser at <http://localhost:8080/>.

## Maintenance notes

### Jenkins plugins

Obtained `plugins.txt` by outputting list from manually-configured Jenkins instance.

From https://github.com/jenkinsci/docker#preinstalling-plugins

    JENKINS_HOST=username:password@myhost.com:port
    curl -sSL "http://$JENKINS_HOST/pluginManager/api/xml?depth=1&xpath=/*/*/shortName|/*/*/version&wrapper=plugins" \
    | perl -pe 's/.*?<shortName>([\w-]+).*?<version>([^<]+)()(<\/\w+>)+/\1 \2\n/g'|sed 's/ /:/'
