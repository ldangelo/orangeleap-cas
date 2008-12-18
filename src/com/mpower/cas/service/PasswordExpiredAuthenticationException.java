package com.mpower.cas.service;

import org.jasig.cas.authentication.handler.UncategorizedAuthenticationException;

/**
 * AuthenticationException used when the Uer's password has expired and they
 * need to change it.
 * @version 1.0
 */
public class PasswordExpiredAuthenticationException extends UncategorizedAuthenticationException {

    /* Static instance. Follows pattern of other CAS exceptions */
    public final static PasswordExpiredAuthenticationException ERROR = new PasswordExpiredAuthenticationException();

    /** Unique ID for serialization. */
    private static final long serialVersionUID = 3544669598642420017L;

    /** The default code for this exception used for message resolving. */
    private static final String CODE = "error.authentication.credentials.expired";

    public PasswordExpiredAuthenticationException() {
        super(CODE);
    }

    public PasswordExpiredAuthenticationException(Throwable throwable) {
        super(CODE, throwable);
    }
}
