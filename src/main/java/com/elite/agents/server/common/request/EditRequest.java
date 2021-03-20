package com.elite.agents.server.common.request;

public interface EditRequest<T> {
    default T createEntityAndApplyTo() {
        T entity = createEntity();
        applyTo(entity);
        return entity;
    }

    public void applyTo(T entity);
    public T createEntity();
}
