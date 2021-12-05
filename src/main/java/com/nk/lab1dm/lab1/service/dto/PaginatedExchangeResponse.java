package com.nk.lab1dm.lab1.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
public class PaginatedExchangeResponse<T> {

    private Integer page;

    private Integer totalPages;

    private Integer totalElements;

    private List<T> payload;

    public PaginatedExchangeResponse() {
        this.page = 1;
        this.totalPages = 1;
        this.totalElements = 0;
        this.payload = Collections.emptyList();
    }

}
