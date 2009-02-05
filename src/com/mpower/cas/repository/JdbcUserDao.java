package com.mpower.cas.repository;

import com.mpower.cas.domain.User;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.Date;
import java.util.Random;

/**
 * Implementation of the UserDao that uses JDBC to talk to the MySQL
 * database to access the user data. Makes use of the RowMappers for
 * mapping the database rows to User objects.
 * @version 1.0
 */
public class JdbcUserDao extends SimpleJdbcDaoSupport implements UserDao {

    private SimpleJdbcInsert insertUser;

    // used for generating reset codes
    private Random random = new Random();

    @Override
    protected void initTemplateConfig() {
        super.initTemplateConfig();
        insertUser = new SimpleJdbcInsert(getDataSource()).withTableName("users").usingGeneratedKeyColumns("id");
    }

    /**
     * Returns a User based on the id, or null if no user exisits
     * with the specified ID
     * @param id the ID of the user
     * @return the User object
     */
    @Override
    public User getUser(int id) {

        SimpleJdbcTemplate template = getSimpleJdbcTemplate();
        SqlParameterSource params = new MapSqlParameterSource("id", id);
        String sql = "SELECT * FROM users where id = :id";

        User user = template.queryForObject(sql, RowMappers.USER, params);

        return user;
    }

    /**
     * Returns the user with the specified login name, or null if no
     * user exists with the specified login name
     * @param loginName the login name of the user
     * @return the User object
     */
    @Override
    public User getUser(String loginName, int siteId) {

        SimpleJdbcTemplate template = getSimpleJdbcTemplate();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("loginid", loginName);
        params.addValue("siteid", siteId);
        String sql = "SELECT * FROM users where loginid = :loginid AND siteid = :siteid";

        User user = template.queryForObject(sql, RowMappers.USER, params);

        return user;
    }

    /**
     * Should be called when creating a new user for the first time. It
     * will populate ID with the generated key, ignoring any value ID
     * currently has. Since it is a new user, account will start in the
     * locked state and a reset code generated so they can login for
     * the first time.
     * @param user the User to save
     */
    @Override
    public void saveUser(User user) {

        // set some sane values for creating a new user, overriding any other values
        user.setLocked(true);
        user.setResetCode( generateResetCode() );
        user.setDateLockedDate(new Date());
        user.setLoginAttempts(0);

        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("siteid", user.getSiteId());
        map.addValue("loginid", user.getLoginName());
        map.addValue("name", user.getFullName());
        map.addValue("emailaddress", user.getEmailAddress());
        map.addValue("adminuser", user.isSiteAdmin());
        map.addValue("active", user.isActive());
        map.addValue("locked", user.isLocked());
        map.addValue("lockeddttm", user.getLockedDate());
        map.addValue("resetcode", user.getResetCode());
        map.addValue("failedattempts", user.getLoginAttempts());

        Number key = insertUser.executeAndReturnKey(map);
        user.setId(key.intValue());
    }

    /**
     * Updates the modifiable User data. Note that this implementation
     * will NOT change a password. That must happen via the setCurrentPassword()
     * method.
     * @param user the User with the updated value
     */
    @Override
    public void updateUser(User user) {

        // get the orig version
        User origUser = getUser(user.getId());

        SimpleJdbcTemplate template = getSimpleJdbcTemplate();
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", user.getId());
        map.addValue("name", user.getFullName());
        map.addValue("emailaddress", user.getEmailAddress());
        map.addValue("adminuser", user.isSiteAdmin());
        map.addValue("active", user.isActive());
        map.addValue("locked", user.isLocked());

        if(user.isLocked() && !origUser.isLocked()) {
            // locking
            user.setDateLockedDate(new Date());
            map.addValue("lockeddttm", user.getLockedDate());
            map.addValue("resetcode", generateResetCode());
        } else if(origUser.isLocked() && !user.isLocked()) {
            // unlocking
            map.addValue("lockeddttm", null);
            map.addValue("resetcode", null);
        } else {
            // Hack around my other hack. User is returning an empty
            // String for a null value to make the UI happy, but we need
            // a real null for the insert.
            String orig = origUser.getLockedDate();
            if(orig.length() == 0) {
                orig = null;
            }

            // nothing changed, so keep original values
            map.addValue("lockeddttm", orig);
            map.addValue("resetcode", origUser.getResetCode());
        }
       
        String sql = "UPDATE users SET name= :name, emailaddress= :emailaddress, adminuser= :adminuser," +
                "active= :active, locked= :locked, lockeddttm= :lockeddttm, resetcode= :resetcode WHERE id= :id";

        template.update(sql, map);
    }

    @Override
    public void setActive(User user) {
        SimpleJdbcTemplate template = getSimpleJdbcTemplate();
        template.update("UPDATE users SET active=? WHERE id=?",user.isActive(), user.getId() );
    }

    @Override
    public void setAdmin(User user) {
        SimpleJdbcTemplate template = getSimpleJdbcTemplate();
        template.update("UPDATE users SET adminuser=? WHERE id=?",user.isSiteAdmin(), user.getId() );
    }

    @Override
    public void setLockedOut(User user) {

        SimpleJdbcTemplate template = getSimpleJdbcTemplate();

        if(user.isLocked()) {
            //if User is locked, but LockedDate isn't set, create it
            if(user.getLockedDate() == null) {
                user.setDateLockedDate(new Date());
            }

            // add a reset code
            user.setResetCode( generateResetCode() );


        } else {
            // else if not locked, but LockedDate IS set, remove the date
            if(user.getLockedDate() != null) {
                user.setLockedDate(null);
            }
            // clear the reset code on unlock
            user.setResetCode(null);
        }

        // HACK - gotta sort out this date thing, need a null, not empty '' if null
        String date = (user.getLockedDate().length() == 0 ? null : user.getLockedDate());

        template.update("UPDATE users SET locked=?, lockeddttm=?, resetcode=? WHERE id=?",
                user.isLocked(), date, user.getResetCode(), user.getId());
    }

    @Override
    public String getCurrentPassword(User user) {
        SimpleJdbcTemplate template = getSimpleJdbcTemplate();
        String pwd =  template.queryForObject("SELECT password FROM users WHERE id=?", String.class, user.getId());
        user.setPassword(pwd);
        return pwd;
    }

    /**
     * This method writes to provide password to the column and ensures
     * the property on the User object is set correctly. This will NOT make
     * any decision on how the password will be obfuscated (hash, crypt, etc...).
     * That decision is made at the service layer. This is simply the messenger.
     * @param user the User to upate the password for
     * @param password the new password
     */
    @Override
    public void setCurrentPassword(User user, String password) {

        SimpleJdbcTemplate template = getSimpleJdbcTemplate();
        String prior = getCurrentPassword(user);
        Date changeDate = new Date();
        template.update("UPDATE users SET password=?, previouspwd=?, pwdchangedt=? WHERE id=?",
                password, prior, changeDate, user.getId());
        user.setPriorPassword(prior);
        user.setPassword(password);
        user.setPasswordChangeDate(changeDate);
    }

    /**
     * Update the login attempts for the user to the specified value. If
     * attempts is < 0, it is treated as zero, effectively resetting the counter
     * @param user
     * @param attempts
     */
    @Override
    public void setLoginAttempts(User user, int attempts) {
        // if attempts is <0, set to zero instead
        attempts = (attempts < 0) ? 0 : attempts;

        SimpleJdbcTemplate template = getSimpleJdbcTemplate();
        template.update("UPDATE users SET failedattempts=? WHERE id=?", attempts, user.getId());
        user.setLoginAttempts(attempts);
    }

    /**
     * Update the database to show the specified user succesfully logged in. Use
     * the default timestamp of now. Updates the lastLogin property of the User
     * with the same timestamp.
     * @param user the User that logged in
     */
    @Override
    public void successfulLogin(User user) {
        SimpleJdbcTemplate template = getSimpleJdbcTemplate();
        Date login = new Date();
        template.update("UPDATE users SET lastlogin = ?, failedattempts=0 WHERE id=?", login, user.getId());
        user.setLastLoginDate(login);
    }


    /* Helper method to generate a 4-digit reset code between 1000 and 9999. */
    protected String generateResetCode() {
        return Integer.toString(random.nextInt(8999) + 1000);
    }



}
