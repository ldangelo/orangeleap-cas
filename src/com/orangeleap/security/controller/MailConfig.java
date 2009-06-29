package com.orangeleap.security.controller;

import org.springframework.beans.factory.annotation.Required;

/**
 * Holder object for containing the email parameters allowing them to be
 * set from the context configuration file
 */
public class MailConfig {

    private String fromAddress;
    private String[] toAddress;
    private String subject;

    public String getFromAddress() {
        return fromAddress;
    }

    @Required
    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String[] getToAddress() {
        return toAddress;
    }

    @Required
    public void setToAddress(String[] toAddress) {
        this.toAddress = toAddress;
    }

    public String getSubject() {
        return subject;
    }

    @Required
    public void setSubject(String subject) {
        this.subject = subject;
    }
}
