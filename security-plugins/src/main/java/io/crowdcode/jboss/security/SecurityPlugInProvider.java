package io.crowdcode.jboss.security;

import org.jboss.as.domain.management.plugin.AuthenticationPlugIn;
import org.jboss.as.domain.management.plugin.AuthorizationPlugIn;
import org.jboss.as.domain.management.plugin.Credential;
import org.jboss.as.domain.management.plugin.PlugInProvider;
import org.jboss.logging.Logger;

/**
 * @author idueppe
 */
public class SecurityPlugInProvider implements PlugInProvider {
    
    private static final Logger LOG = Logger.getLogger(SecurityPlugInProvider.class.getName());
    
    public AuthenticationPlugIn<Credential> loadAuthenticationPlugIn(String name) {
        LOG.info("Looking for "+name+" plugin.");
        if (LdapAuthenticationPlugIn.NAME.equals(name)) {
            return new LdapAuthenticationPlugIn();
        }
        return null;
    }

    public AuthorizationPlugIn loadAuthorizationPlugIn(String name) {
        LOG.info("Looking for "+name+" plugin.");
        if (FixRolesAuthorizationPlugin.NAME.equals(name)) {
            return new FixRolesAuthorizationPlugin();
        }
        return null;
    }

}
