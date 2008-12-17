package com.mpower.cas.domain;

/**
 * This enum provides the codes for all the auditable events for
 * the system, such as logging in or changing a password.
 * @version 1.0
 */
public enum ActivityCode {

    // NOTE: these code values match the corresponding
    // column IDs in the database. If you want to change stuff around,
    // remember to keep the ActivityCode table in sync or you will cause
    // an asteroid to wipe out the planet
    LOGIN(0),
    AUTH_FAILURE(1),
    ACTIVATE_USER(2),
    DEACTIVATE_USER(3),
    ADD_USER(4),
    EDIT_USER(5),
    ADD_SITE(6),
    EDIT_SITE(7),
    PASSWORD_RESET(8),
    PASSWORD_CHANGE(9),
    LOCKOUT(10);

    private int dbCode;

    ActivityCode(int code) {
        this.dbCode = code;
    }

    public int getCodeValue() {
        return dbCode;
    }
}
