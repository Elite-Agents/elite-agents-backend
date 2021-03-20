package com.elite.agents.server.common.model;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PaginatedList<Item> {
    private Integer pageSize;

    private Integer pageNumber;

    private Integer totalPages;
    private Long totalCount;

    private List<Item> items;

    public PaginatedList() {

    }

    public PaginatedList(Page<Item> page) {
        pageSize = page.getPageable().getPageSize();
        pageNumber = page.getNumber();
        totalPages = page.getTotalPages();
        totalCount = page.getTotalElements();
        items = page.getContent();
    }
}
