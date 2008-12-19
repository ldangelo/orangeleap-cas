package com.mpower.cas.service;

import com.mpower.cas.domain.Activity;
import com.mpower.cas.repository.AuditDao;

/**
 * @version 1.0
 */
public class AuditServiceImpl implements AuditService {

    private AuditDao auditDao;

    @Override
    public void auditActivity(Activity activity) {

        // No-op for now
        //auditDao.saveActivity(activity);
    }

    public void setAuditDao(AuditDao dao) {
        this.auditDao = dao;
    }
}
