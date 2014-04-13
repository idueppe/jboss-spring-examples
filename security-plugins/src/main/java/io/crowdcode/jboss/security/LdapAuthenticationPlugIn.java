package io.crowdcode.jboss.security;

import java.io.IOException;
import java.util.Map;

import org.jboss.as.domain.management.plugin.AbstractPlugIn;
import org.jboss.as.domain.management.plugin.AuthorizationPlugIn;
import org.jboss.as.domain.management.plugin.Credential;
import org.jboss.as.domain.management.plugin.Identity;
import org.jboss.as.domain.management.plugin.ValidatePasswordCredential;
import org.jboss.logging.Logger;

/**
 * @author idueppe
 */
public class LdapAuthenticationPlugIn extends AbstractPlugIn {
    
    private static final Logger LOG = Logger.getLogger(LdapAuthenticationPlugIn.class);

    public static final String NAME = "LdapAuthentication";

    private Map<String, String> configuration;

    public void init(Map<String, String> config, Map<String, Object> sharedState) throws IOException {
        this.configuration = config;
        sharedState.put(AuthorizationPlugIn.class.getName(), this);
    }
    
    public Identity<Credential> loadIdentity(String username, String realm) throws IOException {
        LOG.info("Looking for identity of "+username+":"+realm);
        if (configuration.containsKey(username)) {
            return new UserPasswordIdentity(username, configuration.get(username));
        }
        throw new IOException("Identity " + username + " not found.");
    }

    private static class UserPasswordIdentity implements Identity<Credential> {

        private final String username;
        private final Credential credential;

        private UserPasswordIdentity(String username, String password) {
            this.username = username;
//            this.credential = new DigestCredential(password);
//            this.credential = new PasswordCredential(password.toCharArray());
            this.credential = new ValidatePasswordCredential() {
                public boolean validatePassword(char[] password) {
                    LOG.info("Validating password "+password.toString()+" as true!");
                    return true;
                }
            };
        }

        public String getUserName() {
            LOG.info("Returing username "+username);
            return username;
        }

        public Credential getCredential() {
            LOG.info("Returning credential "+credential.toString());
            return credential;
        }
    }

}
