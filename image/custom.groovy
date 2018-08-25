#!groovy

import hudson.security.*
import hudson.security.csrf.DefaultCrumbIssuer
import jenkins.model.*
import jenkins.security.s2m.AdminWhitelistRule

def instance = Jenkins.getInstance()

// Disable remoting
instance.getDescriptor("jenkins.CLI").get().setEnabled(false)

// Enable Agent to master security subsystem
instance.getInjector().getInstance(AdminWhitelistRule.class).setMasterKillSwitch(false)

// CSRF Protection
instance.setCrumbIssuer(new DefaultCrumbIssuer(false))

// Admin authentication
def hudsonRealm = new HudsonPrivateSecurityRealm(false)
hudsonRealm.createAccount("admin", "admin")
instance.setSecurityRealm(hudsonRealm)

// Admin authorisation
def strategy = new FullControlOnceLoggedInAuthorizationStrategy()
strategy.setAllowAnonymousRead(false)
instance.setAuthorizationStrategy(strategy)
instance.save()

// Jenkins root URL
def locationConfig = JenkinsLocationConfiguration.get()
locationConfig.setUrl("http://localhost:8080/")
println(locationConfig.getUrl())

instance.save()
