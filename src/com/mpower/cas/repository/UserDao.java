package com.mpower.cas.repository;

import com.mpower.cas.domain.User;

/**
 * The DAO used by the UserService class
 * @version 1.0
 */
public interface UserDao {

    /**
     * Returns the User for an ID, or null if no User
     * exists with the given ID
     * @param id the ID as an int
     * @return the User object
     */
    public User getUser(int id);

    /**
     * Returns the user with the specified ID associated with the site,
     * or null of there is no matching username for the site
     * @param loginName the login name for the user
     * @param siteId the ID of the site the user is associate with
     * @return the User object
     */
    public User getUser(String loginName, int siteId);

    /**
     * Saves a new User. This is NOT the same as update user. It should
     * only be used when creating a new user, as the User ID should be set
     * as part of this.
     * @param user the User to save
     */
    public void saveUser(User user);

    /**
     * Update the writable field on the User with the new values provided.
     * Changes to non-modifiable fields are ignored.
     * @param user the User with the updated value
     */
    public void updateUser(User user);

    /**
     * Updates the User record to indicate whether the user is active
     * or not. The desired state is read from the given User object
     * @param user the User to set the Active value for
     */
    public void setActive(User user);

    /**
     * Updates the User record to indicate whether the user is a
     * site admin or not. The desired state is read from the given User
     * object.
     * @param user the User to set the Site Admin value for
     */
    public void setAdmin(User user);

    /**
     * Update the User record to indicate whether the user is locked
     * out or not. Implementation must maintain a consistent relationship
     * between the locked-out flag and the timestamp, such that if lockedout
     * is false, timestamp is null, or if lockedout is true, timestamp is not null
     * @param user the User to update the Locked Out state for
     */
    public void setLockedOut(User user);

    /**
     * Gets the current password for the User record. This convenience method
     * is used in cases like a password change, where the service layer has to
     * be absolutely sure it is dealing with the most current version of the
     * password. The implementation should return the most current password and
     * update the provide User object, if necessary
     * @param user the User to get the current password for
     * @return the current password for the User
     */
    public String getCurrentPassword(User user);

    /**
     * Updates the password for a User record to the specified value. Used
     * for password changes. The service layer is responsible for obfuscating
     * the new password. The implementation should ensure the current password
     * in the supplied user object is updated too.
     * @param user the User to set the password for
     * @param password the new password
     */
    public void setCurrentPassword(User user, String password);

    /**
     * Update the login attempts counter for the user, which tracks how
     * many failed login attempts they have made. A value for attempts of zero
     * or less should reset the count. This method should ensure the getLoginAttempts()
     * method on the User returns the same value, or zero if attempts < 0.
     * @param user the User to update the login attempts for
     * @param attempts the number login attempts performed
     */
    public void setLoginAttempts(User user, int attempts);

    /**
     * Updates the User record to indicate a successful login attempt was made
     * @param user the User that logged in
     */
    public void successfulLogin(User user);


}
