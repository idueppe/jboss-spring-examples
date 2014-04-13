package io.crowdcode.jboss.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.jboss.as.domain.management.plugin.AbstractPlugIn;
import org.jboss.as.domain.management.plugin.AuthorizationPlugIn;
import org.jboss.logging.Logger;

/**
 * @author idueppe
 */
public class FixRolesAuthorizationPlugin extends AbstractPlugIn {

    private static final Logger LOG = Logger.getLogger(FixRolesAuthorizationPlugin.class);
    public static final String NAME = "FixRolesAuthorization";

    private Map<String, String> configuration;

    public void init(Map<String, String> config, Map<String, Object> sharedState) throws IOException {
        this.configuration = config;
        sharedState.put(AuthorizationPlugIn.class.getName(), this);
    }

    public String[] loadRoles(String userName, String realm) throws IOException {
        LOG.info("Loading roles for " + userName + ":" + realm);
        String[] split = configuration.get("roles").split(",");
        LOG.info("Returning roles "+Arrays.toString(split));
        return split;
    }

}
