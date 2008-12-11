package com.mpower.cas;

import org.jasig.cas.adaptors.jdbc.AbstractJdbcUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;
import org.jasig.cas.authentication.handler.AuthenticationException;

/**
 * Implementation of an AuthenticationHandler that knows how to talk to validate credentials
 * against the MPower store
 * @version 1.0
 */
public class MpowerUsernamePasswordAuthenticationHandler extends AbstractJdbcUsernamePasswordAuthenticationHandler{


    protected boolean authenticateUsernamePasswordInternal(UsernamePasswordCredentials credentials) throws AuthenticationException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
