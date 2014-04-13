package io.crowdcode.jboss.security;

import java.security.acl.Group;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;

import org.jboss.security.PicketBoxLogger;
import org.jboss.security.auth.spi.LdapLoginModule;

public class LdapDefaultRoleLoginModule extends LdapLoginModule {

    private static final String DEFAULT_ROLE = "defaultRole";

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        addValidOptions(new String[] { DEFAULT_ROLE });
        super.initialize(subject, callbackHandler, sharedState, options);
    }

    @Override
    protected Group[] getRoleSets() throws LoginException {
        Group[] groups = super.getRoleSets();

        String defaultRole = (String) options.get(DEFAULT_ROLE);

        if (defaultRole != null && !defaultRole.trim().isEmpty()) {
            for (Group group : groups) {
                if (group.getName() == "Roles") {
                    try {
                        group.addMember(super.createIdentity("user"));
                    } catch (Exception e) {
                        PicketBoxLogger.LOGGER.debugFailureToCreatePrincipal("user", e);
                    }
                }
            }
        }
        return groups;
    }

}
