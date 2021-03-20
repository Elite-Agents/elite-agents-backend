package com.elite.agents.server.common.model;

public interface PersistedObject<ID> {

    public ID getId();
    public void setId(ID id);
}
