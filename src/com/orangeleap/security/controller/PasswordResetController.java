package com.orangeleap.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Controller which handles the JSON request to reset a password.
 */
@Controller
public class PasswordResetController {

    private static final Log logger = LogFactory.getLog(PasswordResetController.class);

    private final String MSG = "%s has requested a password reset be sent to %s";

    @Autowired
    private MailSender mailSender;

    @Autowired
    private MailConfig config;

    @RequestMapping("/requestReset.json")
    @SuppressWarnings("unchecked")
    public ModelMap requestReset(String login, String email) {
        ModelMap map = new ModelMap();

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(config.getToAddress());
        msg.setFrom(config.getFromAddress());
        msg.setSubject(config.getSubject());
        msg.setText( String.format(MSG,login,email) );

        try {
            mailSender.send(msg);
            map.put("success", true);

        } catch(Exception ex) {
            logger.error("Password Reset email could not be sent", ex);
            map.put("success", false);
            map.put("error",ex.getMessage());
        }

        return map;
    }

}
