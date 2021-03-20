package com.elite.agents.server.common.service;

import com.elite.agents.server.common.model.PaginatedList;
import com.elite.agents.server.common.model.PersistedObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Validated
public abstract class AbstractCrudService <T extends PersistedObject<ID>, ID> {

    protected final PagingAndSortingRepository<T, ID> repository;

    private final Class<T> entityClazz;

    public AbstractCrudService(PagingAndSortingRepository<T, ID> repository, Class<T> entityClazz) {
        this.repository = repository;
        this.entityClazz = entityClazz;
    }

    public T create(@Valid T entity) {
        if (entity == null) {
            throw new IllegalArgumentException(entityClazz.getSimpleName() + " entity must not be null!");
        }
        if (entity.getId() != null) {
            throw new IllegalArgumentException("Entity " + entityClazz.getSimpleName() + ".id must be null!");
        }
        // TODO validate

        T createdEntity = repository.save(entity);

        return createdEntity;
    }

    public List<T> createAll(@Valid List<T> entities) {
        if (entities == null) {
            throw new IllegalArgumentException(entityClazz.getSimpleName() + " entities list must not be null!");
        }
        if (entities.isEmpty()) {
            return Collections.emptyList();
        }
        if (entities.stream()
                .map(T::getId)
                .anyMatch(Objects::nonNull)) {
            throw new IllegalArgumentException("Entity " + entityClazz.getSimpleName() + ".id must be null!");
        }
        // TODO validate

        List<T> createdEntities = StreamSupport.stream(repository.saveAll(entities).spliterator(), false)
                .collect(Collectors.toList());

        return createdEntities;
    }

    public Optional<T> getById(@NotNull ID id) {
        return repository.findById(id);
    }

    public List<T> getAllByIds(@NotNull Iterable<ID> ids) {
        return StreamSupport.stream(repository.findAllById(ids).spliterator(), false)
                .collect(Collectors.toList());
    }

    public PaginatedList<T> getAll(@NotNull @Min(1) @Max(100) Integer pageSize,
                                   @NotNull @Min(0) Integer pageNumber) {

        // TODO filtering

        Page<T> page = repository.findAll(PageRequest.of(pageNumber, pageSize));

        return new PaginatedList<>(page);
    }

    public T update(@Valid T entity) {

        // TODO real
        return repository.save(entity);
    }

    public Iterable<T> updateAll(@Valid Iterable<T> entities) {

        // TODO real
        return repository.saveAll(entities);
    }

    public boolean deleteById(@NotBlank ID id) {
        return repository.findById(id)
                .map(product -> {
                    repository.deleteById(id);
                    return true;
                }).orElse(Boolean.FALSE);
    }
}
