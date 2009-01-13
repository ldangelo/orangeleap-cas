package com.mpower.cas.repository;

import com.mpower.cas.domain.User;
import com.mpower.cas.domain.Site;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Utility class containing the ParamaterizedRowMappers used for the CAS
 * application. This centralizes all the row mapping logic to make it easier
 * to change if the database changes.
 *
 * @version 1.0
 */
public final class RowMappers {

    private RowMappers() {
        // private consructor to prevent instantiation
    }


    /**
     * RowMapper to read a row for the Users table into an instance of User.class. SQL
     * should always use <code>SELECT * FROM users</code> and not specific columns so
     * that this row mapper can decide what fields get loaded.
     */
    public final static ParameterizedRowMapper<User> USER = new ParameterizedRowMapper<User>() {

        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setSiteId(rs.getInt("siteid"));
            user.setLoginName(rs.getString("loginid"));
            user.setFullName(rs.getString("name"));
            user.setEmailAddress(rs.getString("emailaddress"));
            user.setPasswordChangeDate(rs.getDate("pwdchangedt"));
            user.setPassword(rs.getString("password"));
            user.setPriorPassword(rs.getString("previouspwd"));
            user.setLastLoginDate(rs.getDate("lastlogin"));
            user.setLoginAttempts(rs.getInt("failedattempts"));
            user.setDateLockedDate(rs.getDate("lockeddttm"));
            user.setResetCode(rs.getString("resetcode"));
            user.setSiteAdmin(rs.getBoolean("adminuser"));
            user.setLocked(rs.getBoolean("locked"));
            user.setActive(rs.getBoolean("active"));

            return user;
        }
    };

    /**
     * RowMapper to read a row from the Sites table into an instance of Site.class. The
     * same restrictions apply to this as to the User mapper. Any SQL query operations
     * against the Sites table should be <code>SELECT * FROM sites</code> and not a named-column
     * select. Let this rowmapper determine what columns it cares about.
     */
    public final static ParameterizedRowMapper<Site> SITE = new ParameterizedRowMapper<Site>() {

        public Site mapRow(ResultSet rs, int rowNum) throws SQLException {

            Site site = new Site();
            site.setId(rs.getInt("id"));
            site.setLoginDomain(rs.getString("domain"));
            site.setCompanyName(rs.getString("name"));
            site.setPrimaryContact(rs.getString("primarycontact"));
            site.setContactPhone(rs.getString("contactphone"));
            site.setContactEmail(rs.getString("contactemail"));
            site.setLocked(rs.getBoolean("locked"));
            site.setActive(rs.getBoolean("active"));
            return site;
        }
    };

}
