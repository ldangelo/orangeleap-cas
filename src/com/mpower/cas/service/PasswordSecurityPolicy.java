package com.mpower.cas.service;

/**
 * The PasswordSecurityPolicy is checked whenever a password is created
 * or changed to ensure the choosen password meets any required
 * security constrants.
 * @version 1.0
 */
public interface PasswordSecurityPolicy {

    /**
     * Returns true if the password is secure according to the implementing policy     
     */
    public boolean isSecure(String password);

}
