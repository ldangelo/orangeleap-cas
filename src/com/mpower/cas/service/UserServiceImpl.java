package com.mpower.cas.service;

import com.mpower.cas.domain.User;
import com.mpower.cas.domain.Activity;
import com.mpower.cas.domain.ActivityCode;
import com.mpower.cas.repository.UserDao;

import java.util.List;
import java.util.Date;

import org.springframework.security.providers.encoding.ShaPasswordEncoder;
import org.springframework.beans.factory.annotation.Required;
import org.jasig.cas.authentication.handler.*;

/**
 * UserService implementation that makes use of the UserDao and
 * enforces audit tracking via the AuditService.
 * @version 1.0
 */
public class UserServiceImpl implements UserService{

    //TODO externalize messages for general Security exceptions

    private final static int MILLIS_PER_DAY = 86400000;
    private AuditService auditService;
    private PasswordSecurityPolicy passwordPolicy;
    private UserDao userDao;
    private UserSearchService searchService;
    private int maxLoginAttempts = 5;
    private int passwordValidityDays = 90;

    // we default to using the ShaPasswordEncoder since it could
    // cause infinite grief if someone plugs in new encoder that does
    // not match how passwords are saved in the database
    private ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);

    public UserServiceImpl() {
        // make sure we concode as Base64, not Hex
        passwordEncoder.setEncodeHashAsBase64(true);

    }

    /**
     * Injected reference to the AuditService to use
     * @param audit the AuditService ref
     */
    @Required
    public void setAuditService(AuditService audit) {
        this.auditService = audit;
    }


    /**
     * Injected reference to the PasswordSecurityPolicy to use
     * @param passwordPolicy the PasswordSecurityPolicy ref
     */
    @Required
    public void setPasswordPolicy(PasswordSecurityPolicy passwordPolicy) {
        this.passwordPolicy = passwordPolicy;
    }

    /**
     * Injected reference to the UserDao to use
     * @param userDao the UserDao ref
     */
    @Required
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Injected referenve to the UserSearchService to use
     * @param searchService the UserSearchService ref
     */
    @Required
    public void setSearchService(UserSearchService searchService) {
        this.searchService = searchService;
    }

    /**
     * Injected property of the maximum number of login attempts that can be made
     * before the user is locked out. If the specified value is not > 0, the default
     * value of 5 will be used.
     * @param maxLoginAttempts the maximum attempts
     */
    public void setMaxLoginAttempts(int maxLoginAttempts) {

        this.maxLoginAttempts = (maxLoginAttempts > 0? maxLoginAttempts : 5);
    }

    /**
     * Injected property of the number of days a password is valid for before
     * it must be changed. The default value is 90 days and the minimal value is a
     * week (7 days).
     * @param passwordValidityDays the number of days as an int
     */
    public void setPasswordValidityDays(int passwordValidityDays) {
        this.passwordValidityDays = (passwordValidityDays < 7 ? 7 : passwordValidityDays);
    }

    /**
     * Change a User's password. This ensure it is secure per the policy and is
     * not the same as the prior password and the current password has been provided
     * for security purposes.
     * @param user the User to change the password for
     * @param oldPassword the prior password (security check)
     * @param newPassword the new, desired password
     */
    @Override
    public void changePassword(User user, String oldPassword, String newPassword) {
        String priorHash = userDao.getCurrentPassword(user);
        String oldHash = passwordEncoder.encodePassword(oldPassword, null);
        String newHash = passwordEncoder.encodePassword(newPassword, null);

        if(!passwordPolicy.isSecure(newPassword)) {
            throw new SecurityException("New password does not meet minimal security requirements");
        }

        if(!priorHash.equals(oldHash)) {
            throw new SecurityException("Old password is not correct");
        }

        if(newHash.equals(priorHash)) {
            throw new SecurityException("Password cannot be changed to same value");
        }

        userDao.setCurrentPassword(user, newHash);
    }

    /**
     * Create a new User. Enforces that the new User belongs to the same
     * site as the requestor and that the new User doesn't already exist.
     * @param requestorId the ID of the requesting user requesting user
     * @param user the new user being created
     */
    @Override
    public void createUser(int requestorId, User user) {

        User admin = getUser(requestorId);

        // make sure the user doesn't exisit. Simple check for now of ID null
        if(user.getId() >= 0) {
            User temp = getUser(user.getId());

            if(temp != null) {
                throw new SecurityException("User already exists");
            }
        }

        // admins can ONLY create users under their own site, so enforce it here
        user.setSiteId( admin.getSiteId() );

        userDao.saveUser(user);
    }

    /**
     * Update the data on a User. Ensures the requestor has the ability to
     * edit the User and they belong to the same Site.
     * @param requestorId the ID of the user making the update
     * @param user the user being updated
     */
    @Override
    public void updateUser(int requestorId, User user) {

        User admin = getUser(requestorId);
        User temp = getUser(user.getId()); // clean copy for verification

        if(temp == null) {
            throw new SecurityException("You cannot update a user that does not exist in the system");
        }

        // can only update Users in same site as admin
        if(!validateAdmin(requestorId, temp)) {
            throw new SecurityException("You cannot update a user who does not belong to your site");
        }

        userDao.updateUser(user);
    }

    /**
     * Uses the UserSearchService to find the users that match the criteria,
     * or an empty List if no matches are found
     * @param requestorId the ID of the  user making the search
     * @param criteria the search criteria
     * @return the List of Users matching the criteria
     */
    @Override
    public List<User> findUsers(int requestorId, String criteria) {

        User user = getUser(requestorId);
        return searchService.findUsers(criteria, user.getSiteId());
    }

    /**
     * Get the User associated with the specified ID. If no User exisits with
     * the specified ID, this method will return null
     * @param id the ID of the user to get
     * @return the User object
     */
    @Override
    public User getUser(int id) {

        return userDao.getUser(id);
    }

    /**
     * Get the Uer who belongs to the specified Site with the given loginName.
     * Returns null if the User does not exist
     * @param siteId the ID of the Site the User belongs to
     * @param loginName the login name of the user to get
     * @return the User objectg
     */
    @Override
    public User getUser(int siteId, String loginName) {

        return userDao.getUser(loginName, siteId);
    }

    /**
     * Resets a User's password. This can only be done if the users account is currently locked
     * out and there is a reset code available. Reseting the password will unlock the account.
     * @param siteId the ID of the Site the user belongs to
     * @param loginName the login name for the user
     * @param resetCode the current reset code
     * @param newPassword the new desired password
     */
    @Override
    public void resetPassword(int siteId, String loginName, String resetCode, String newPassword) {

        resetCode = (resetCode == null ? null : resetCode.trim());
        newPassword = (newPassword == null ? null : newPassword.trim());

        User user = getUser(siteId, loginName);
        if(user == null) {
            throw new SecurityException("Unknow user");

        }

        // make sure they are actually locked out, i.e. flag is set and reset code is present
        if(!user.isLocked() || user.getResetCode() == null) {
            throw new SecurityException("User is not locked out. Cannot reset password.");
        }

        if(!user.getResetCode().equalsIgnoreCase(resetCode)) {
            throw new SecurityException("Invalid reset code.");
        }

        if(!passwordPolicy.isSecure(newPassword)) {
            throw new SecurityException("New password specified does not meet minimal password " +
                    "security requirements. Please try again.");
        }

        userDao.setCurrentPassword(user, newPassword);
        // unlock the account too, now that password is reset
        user.setLocked(false);
        userDao.setLockedOut(user);
    }

    @Override
    public void setActive(int requestorId, User user) {
        if(!validateAdmin(requestorId, user)) {
            throw new SecurityException("You do not have the rights to modify the active status of this user");
        }

        userDao.setActive(user);
    }

    @Override
    public void setAdmin(int requestorId, User user) {

        if(!validateAdmin(requestorId, user)) {
            throw new SecurityException("You do not have the rights to modify admin permissions for this user");
        }

        userDao.setAdmin(user);
    }

    @Override
    public User authenticateUser(int siteId, String loginName, String password) throws AuthenticationException {

        User user = userDao.getUser(loginName, siteId);

        if(user == null) {
            throw UnknownUsernameAuthenticationException.ERROR;
        }

        if(user.isLocked()) {
            throw BlockedCredentialsAuthenticationException.ERROR;
        }

        String pwdHash = passwordEncoder.encodePassword(password, null);
        if(!user.getPassword().equals(pwdHash)) {
            userDao.setLoginAttempts(user, user.getLoginAttempts() + 1);

            if(user.getLoginAttempts() >= maxLoginAttempts) {
                user.setLocked(true);
                userDao.setLockedOut(user);
                throw BlockedCredentialsAuthenticationException.ERROR;
            } else {
                throw BadPasswordAuthenticationException.ERROR;
            }
        }

        // if password is correct, but expired, another exception
        int age = getPasswordAge(user.getPasswordChangeDate());
        if(age >= passwordValidityDays) {
            throw PasswordExpiredAuthenticationException.ERROR;
        }

        //TODO: add audit information to authentication process
        return user;
    }

    /**
     * Helper method to (somewhat) quickly build an Activity for audit purposes.
     */
    protected Activity buildActivity(User affected, User performedBy, ActivityCode code) {
        Activity activity = new Activity();
        activity.setAffecterUser(affected);
        activity.setPerformedBy(performedBy);
        activity.setCode(code);
        return activity;
    }

    /**
     * Very primative helper method that will calculate the number of days between two
     * dates. Note this is not a precise implementation, and can get screwed up by spans
     * crossing DST adjustements, so the calculated value could be off by a day or two,
     * in a worst-case scenario. Good enough to get us going though.
     * @param lastChangeDate the date the password was last changed
     * @return the number of days between now and the last change date
     */
    protected int getPasswordAge(Date lastChangeDate) {
        Date now = new Date();
        int days = (int) (now.getTime() - lastChangeDate.getTime())/MILLIS_PER_DAY;
        return days;
    }


    /**
     * Centralized validation login to ensure the action being requested is
     * being done by an admin in the organization as the affected User and that
     * the requestor is a site admin.
     * @param requestorId the ID of the User requesting the action
     * @param user the User the action is being performed on
     * @return
     */
    protected boolean validateAdmin(int requestorId, User user) {

        User admin = getUser(requestorId);

        // first, make sure the requestor is actually a site admin
        if(!admin.isSiteAdmin()) {
            return false;
        }

        // second, make sure they are an active, unlocked user
        if(!admin.isActive() && !admin.isActive()) {
            return false;
        }
        // finally, make sure they are in the same site as the affected user
        if(user.getSiteId() != admin.getSiteId()) {
            return false;
        }

        //looks good! return true
        return true;
    }

}
