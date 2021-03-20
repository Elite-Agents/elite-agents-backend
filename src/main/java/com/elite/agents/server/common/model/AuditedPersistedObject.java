package com.elite.agents.server.common.model;

import java.util.Date;

public interface AuditedPersistedObject<ID> extends PersistedObject<ID> {
    public Date getCreatedDate();
    public void setCreatedDate(Date createdDate);
    public Date getLastModifiedDate();
    public void setLastModifiedDate(Date lastModifiedDate);
}
