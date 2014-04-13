package io.crowdcode.jboss.security;

import java.security.acl.Group;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;

import org.jboss.security.SimpleGroup;
import org.jboss.security.auth.spi.UsernamePasswordLoginModule;

public class FixedPasswordAndRoleLoginModule extends UsernamePasswordLoginModule {

    private static final String ROLE = "role";
    private static final String PASSWORD = "password";

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler,
                    Map<String, ?> sharedState, Map<String, ?> options) {
        addValidOptions(new String[] { ROLE });
        addValidOptions(new String[] { PASSWORD});
        super.initialize(subject, callbackHandler, sharedState, options);
    }

    @Override
    protected Group[] getRoleSets() throws LoginException {
        try {
            String roleName = (String) options.get(ROLE);
            SimpleGroup userRoles = new SimpleGroup("Roles");
            userRoles.addMember(super.createIdentity(roleName));
            return new Group[] {userRoles};
        } catch (Exception e) {
            e.printStackTrace();
            throw new LoginException(e.getMessage());
        }
    }

    @Override
    protected String getUsersPassword() throws LoginException {
        return "";
    }

    @Override
    protected boolean validatePassword(String inputPassword, String expectedPassword) {
        return true;
    }

}
