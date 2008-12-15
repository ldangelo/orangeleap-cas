package com.mpower.cas.service;

import com.mpower.cas.domain.Activity;
import com.mpower.cas.repository.AuditDao;

/**
 * The AuditService is used to track all activities in the authentication
 * service, such as all login attempts and account changes.
 * @version 1.0
 */
public interface AuditService {

    public void auditActivity(Activity activity);

}
