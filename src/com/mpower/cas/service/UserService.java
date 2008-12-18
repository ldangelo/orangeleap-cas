package com.mpower.cas.service;

import com.mpower.cas.domain.User;

import java.util.List;

import org.jasig.cas.authentication.handler.AuthenticationException;

/**
 * The UserService exposes all the common operations for working with
 * Users.
 * @version 1.0
 */
public interface UserService {

    /**
     * Change the password for user. A User can only change their own password,
     * hence only a single User parameter. The old password should be compared to
     * the prior password and the new password should be checked against any
     * password security policy.
     * @param user the User changing their own password
     * @param oldPassword the user's old password
     * @param newPassword the user's new password
     */
    public void changePassword(User user, String oldPassword, String newPassword);

    /**
     * Create a new user. The user will be associated with the same site as the
     * requesting user. The requestingUser must be a site admin for their site.
     * @param requestorId the ID of the requesting user requesting user
     * @param user the new user being created
     */
    public void createUser(int requestorId, User user);

    /**
     * Updates the data on a User. Only an admin can update user data for a User.
     * The requesting user must belong to the same site as the updated user.
     * @param requestorId the ID of the user making the update
     * @param user the user being updated
     */
    public void updateUser(int requestorId, User user);

    /**
     * Return a list of all the users that match the search criteria and who
     * belong to the same site as the requesting user. The List will be
     * empty if there are no matching users found (not null).
     * @param requestorId the ID of the  user making the search
     * @param criteria the search criteria
     * @return the List of Users that match the criteria
     */
    public List<User> findUsers(int requestorId, String criteria);

    /**
     * Returns a user by ID. If being called by an admin user, the returned
     * User should be checked to ensure it belongs to the same site. Returns
     * null if no user with the ID exists.
     * @param id the ID of the user to get
     * @return the User object
     */
    public User getUser(int id);

    /**
     * Returns a user by their login name. The user must belong to the
     * same site as the requesting user. Returns null if no user with
     * the specified loginName exists.
     * @param requestorId the ID of the user requesting the other user
     * @param loginName the login name of the user to get
     * @return the User object
     */
    public User getUser(int requestorId, String loginName);

    /**
     * Given the site ID, login name and password, authenticate the user and
     * return their associated User object.
     * @param siteId the site the user belongs to
     * @param loginName the login name for the user
     * @param password the user's password
     * @return the User object
     * @throws AuthenticationException if the credentials are not valid
     */
    public User authenticateUser(int siteId, String loginName, String password) throws AuthenticationException;


    /**
     * Resets a user's password to the new specified value based on the parameters
     * @param siteId the ID of the Site the user belongs to
     * @param loginName the login name for the user
     * @param resetCode the current reset code
     * @param newPassword the new desired password
     */
    public void resetPassword(int siteId, String loginName, String resetCode, String newPassword);

    /**
     * Activate or deactivate the User based on the flag inside the object. The
     * requestor must belong to the same site as the User. A User cannot activate
     * or deactivate themself.
     * @param requestorId the ID of the admin user making the change
     * @param user the user to set to Active/Deactive
     */
    public void setActive(int requestorId, User user);

    /**
     * Set whether or not a User is a site admin. The user must belong
     * to the same site as the requestor. A user cannot change this value
     * for themself.
     * @param requestorId the ID of the admin user requesting the change
     * @param user the User to change the admin flag for
     */
    public void setAdmin(int requestorId, User user);


}
