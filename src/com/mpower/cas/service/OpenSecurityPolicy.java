package com.mpower.cas.service;

/**
 * A PasswordSecurityPolicy implementation that doesn't do anything. It will
 * always return true, regardless of the password given or even if it is null.
 * @version 1.0
 */
public class OpenSecurityPolicy implements PasswordSecurityPolicy {

    @Override
    public boolean isSecure(String password) {
        return true;
    }
}
