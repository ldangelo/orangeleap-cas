package com.mpower.cas.service;

import org.jasig.cas.authentication.principal.UsernamePasswordCredentials;
import org.jasig.cas.authentication.handler.AuthenticationException;
import org.jasig.cas.authentication.handler.UnsupportedCredentialsException;
import org.jasig.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import com.mpower.cas.domain.User;

/**
 * Implementation of an AuthenticationHandler that knows how to talk to validate credentials
 * against the MPower store
 * @version 1.0
 */
public class MpowerUsernamePasswordAuthenticationHandler extends AbstractUsernamePasswordAuthenticationHandler {

    private UserService userService;


    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    protected boolean authenticateUsernamePasswordInternal(UsernamePasswordCredentials credentials) throws AuthenticationException {

        String username = credentials.getUsername();
        String password = credentials.getPassword();
        String[] halves = username.split("@");

        if(halves.length != 2) {
            throw UnsupportedCredentialsException.ERROR;
        }


        // hard-code for SiteID 1 for demo
        User user = userService.authenticateUser(1,halves[0],password);

        // the authenticateUser method on the UserService will either return the
        // User object if the credentials are valid, or pitch an exception, so if
        // we made it to here, the credentials are valid
        return true;


    }
}
