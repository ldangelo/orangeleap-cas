package com.mpower.cas.repository;

import com.mpower.cas.domain.Activity;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import javax.annotation.PostConstruct;

/**
 * JDBC implementation of the AuditDao which persists the activity data
 * to the mysql database. The expected table looks like this:
 * <code>
 * CREATE TABLE Activity
 *(
 *	id INTEGER NOT NULL AUTO_INCREMENT,
 *	affecteduser INTEGER,
 *	affectedsite INTEGER,
 *	performedby INTEGER NOT NULL,
 *	ipaddress VARCHAR(20) NOT NULL,
 *	activitycode INTEGER NOT NULL,
 *	message VARCHAR(255),
 *	activitydttm DATETIME NOT NULL,
 *	PRIMARY KEY (id),
 *	KEY (activitycode),
 *	KEY (affecteduser),
 *	KEY (performedby)
 * )
 * </code>
 *
 * The activitycode comes from the ActivityCode lookup table.
 * 
 * @version 1.0
 */
public class JdbcAuditDao extends SimpleJdbcDaoSupport implements AuditDao {

    protected final Log logger = LogFactory.getLog(getClass());

    private final static String SQL = "INSERT INTO Activity(activitycode,affecteduser," +
            "affectedsite,performedby,ipaddress,activitydttm,message) VALUES(?,?,?,?,?,?,?);";

    /**
     * Save the activity to the database
     * @param activity
     */
    @Override
    public void saveActivity(Activity activity) {

        SimpleJdbcTemplate jdbc = getSimpleJdbcTemplate();
        
        jdbc.update(SQL,
                activity.getCode().getCodeValue(),
                (activity.getAffecterUser() == null ? null : activity.getAffecterUser().getId()),
                (activity.getAffectedSite() == null ? null : activity.getAffectedSite().getId()),
                activity.getPerformedBy().getId(),
                activity.getClientIpAddress(),
                activity.getTimestamp(),
                activity.getMessage());

    }
}
