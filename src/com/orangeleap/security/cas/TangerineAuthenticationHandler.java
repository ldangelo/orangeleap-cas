package com.orangeleap.security.cas;

import org.jasig.cas.adaptors.ldap.AbstractLdapUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;
import org.jasig.cas.authentication.handler.AuthenticationException;
import org.jasig.cas.authentication.handler.BadCredentialsAuthenticationException;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.springframework.security.userdetails.ldap.LdapUserDetailsMapper;
import org.springframework.security.userdetails.ldap.LdapUserDetailsImpl;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.Filter;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.core.DistinguishedName;

import java.text.MessageFormat;

/**
 *  Custom LDAP Authentication Handler that knows what to do with user names
 *  in the format user@site. Will split them out correctly for the LDAP search
 */
public class TangerineAuthenticationHandler extends AbstractLdapUsernamePasswordAuthenticationHandler {

    private static final Log logger = LogFactory.getLog(TangerineAuthenticationHandler.class);

    @Override
    protected boolean authenticateUsernamePasswordInternal(UsernamePasswordCredentials credentials) throws AuthenticationException {

        String userName = credentials.getUsername();
        String password = credentials.getPassword();
        logger.info("Authenticating User: " + userName);

        // split the username in the userid and site components
        String[] args = userName.split("@");

        // if we don't have both a username and a site, assume an error
        if(args.length < 2) {
            logger.warn("Authentication attempted without a site parameter: " + userName);
            throw new BadCredentialsAuthenticationException("Username must be in the format of user@site");
        }

        Filter filter = new EqualsFilter("uid", args[0]);

        try {
            return getLdapTemplate().authenticate( String.format("o=%s",args[1]), filter.toString(), password );
        } catch (Exception e) {
        	logger.debug(e);
        	return false;
        }

    }

}
