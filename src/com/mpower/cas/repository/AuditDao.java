package com.mpower.cas.repository;

import com.mpower.cas.domain.Activity;

/**
 * DAO interface used for persisting Audit informatoin
 * @version 1.0
 */
public interface AuditDao {

    public void saveActivity(Activity activity);
}
