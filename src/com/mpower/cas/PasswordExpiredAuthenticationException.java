package com.mpower.cas;

import org.jasig.cas.authentication.handler.AuthenticationException;

/**
 * @version 1.0
 */
public class PasswordExpiredAuthenticationException extends AuthenticationException{

    //private static final long serialVersionUID = 3977861752513837361L;
    


    public PasswordExpiredAuthenticationException(String code) {
        super(code);
    }

    public PasswordExpiredAuthenticationException(String code, Throwable throwable) {
        super(code, throwable);
    }
}
