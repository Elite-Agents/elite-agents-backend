package com.elite.agents.server.common.view.factory;

import com.elite.agents.server.common.model.PaginatedList;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractViewFactory<T, V> {
    public List<V> buildListView(List<T> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return Collections.emptyList();
        }
        return entities.stream()
                .filter(Objects::nonNull)
                .map(this::buildView)
                .collect(Collectors.toList());

    }

    public PaginatedList<V> buildPaginatedListView(Page<T> repositoryEntityPage) {
        return buildPaginatedListView(new PaginatedList<>(repositoryEntityPage));
    }

    public PaginatedList<V> buildPaginatedListView(PaginatedList<T> paginatedEntitiesList) {
        if (paginatedEntitiesList == null) {
            return null;
        }

        PaginatedList<V> result = new PaginatedList<>();
        result.setPageSize(paginatedEntitiesList.getPageSize());
        result.setPageNumber(paginatedEntitiesList.getPageNumber());
        result.setTotalPages(paginatedEntitiesList.getTotalPages());
        result.setTotalCount(paginatedEntitiesList.getTotalCount());
        result.setItems(buildListView(paginatedEntitiesList.getItems()));

        return result;
    }

    public abstract V buildView(T entity);
}
