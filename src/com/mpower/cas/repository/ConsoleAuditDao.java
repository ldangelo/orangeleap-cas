package com.mpower.cas.repository;

import com.mpower.cas.domain.Activity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * This is a simple implementation of an AuditDao which writes the Activities
 * to a console, with each line being a unique activity. This is suitable for
 * testing purposes but is not recommended for production, as there is not
 * guarantee durability to the audit events using the console. This will write
 * to System.out
 * @version 1.0
 */
public class ConsoleAuditDao implements AuditDao {

    private final static DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // the delimiter to place between fields. default is an empty space
    private final static String DELIM  = " ";
    @Override
    public void saveActivity(Activity activity) {
        StringBuilder builder = new StringBuilder();
        builder.append(FORMAT.format(activity.getTimestamp())).append(DELIM);
        builder.append("user:").append(activity.getAffecterUser().getLoginName()).append(DELIM);
        builder.append("site:").append(activity.getAffectedSite().getLoginDomain()).append(DELIM);
        builder.append("by:").append(activity.getPerformedBy().getLoginName()).append(DELIM);
        builder.append("ip:").append(activity.getClientIpAddress()).append(DELIM);
        builder.append(activity.getCode()).append(DELIM);
        builder.append(activity.getMessage());

        System.out.println(builder.toString());

    }




}
