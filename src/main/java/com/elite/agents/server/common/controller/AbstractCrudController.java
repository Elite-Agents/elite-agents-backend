package com.elite.agents.server.common.controller;

import com.elite.agents.server.common.controller.exception.ResourceNotFoundException;
import com.elite.agents.server.common.model.PaginatedList;
import com.elite.agents.server.common.model.PersistedObject;

import com.elite.agents.server.common.request.EditRequest;
import com.elite.agents.server.common.service.AbstractCrudService;
import com.elite.agents.server.common.view.factory.AbstractViewFactory;
import org.springframework.web.bind.annotation.*;

public abstract class AbstractCrudController<T extends PersistedObject<ID>, ID,
        View, Request extends EditRequest<T>> {

    protected final AbstractCrudService<T, ID> service;
    protected final AbstractViewFactory<T, View> viewFactory;
    private final Class<T> entityClazz;

    public AbstractCrudController(AbstractCrudService<T, ID> service,
                                  AbstractViewFactory<T, View> viewFactory,
                                  Class<T> entityClazz) {
        this.service = service;
        this.viewFactory = viewFactory;
        this.entityClazz = entityClazz;
    }

    @PostMapping
    @ResponseBody
    public View create(
            @RequestBody(required = true)
            Request editRequest) {
        T entity = editRequest.createEntityAndApplyTo();

        return viewFactory.buildView(service.create(entity));
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public View getById(
            @PathVariable(name = "id", required = true)
            ID id) {
        return viewFactory.buildView(
                service.getById(id).orElseThrow(() ->
                        new ResourceNotFoundException(" with id = [" + id + " ] not found !")));
    }

    @GetMapping
    @ResponseBody
    public PaginatedList<View> getAll(
            @RequestParam(name = "pageSize", required = false, defaultValue = "15")
            Integer pageSize,
            @RequestParam(name = "pageNumber", required = false, defaultValue = "0")
            Integer pageNumber) { // TODO filters
        return viewFactory.buildPaginatedListView(service.getAll(pageSize, pageNumber));
    }

    @PutMapping(path = "/{id}")
    @ResponseBody
    public View update(
            @PathVariable(name = "id", required = true)
            ID id,
            @RequestBody(required = true)
            Request editRequest) {

        T entity = service.getById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product with id = [" + id + " ] not found !"));
        editRequest.applyTo(entity);

        return viewFactory.buildView(service.update(entity));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseBody
    public boolean deleteById(
            @PathVariable(name = "id", required = true)
            ID id) {
        return service.deleteById(id);
    }
}
