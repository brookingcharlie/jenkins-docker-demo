# Custom Jenkins server - Docker Image

## Obtained plugins.txt by outputting list from manually-configured Jenkins instance

From https://github.com/jenkinsci/docker#preinstalling-plugins

    JENKINS_HOST=username:password@myhost.com:port
    curl -sSL "http://$JENKINS_HOST/pluginManager/api/xml?depth=1&xpath=/*/*/shortName|/*/*/version&wrapper=plugins" \
    | perl -pe 's/.*?<shortName>([\w-]+).*?<version>([^<]+)()(<\/\w+>)+/\1 \2\n/g'|sed 's/ /:/'
