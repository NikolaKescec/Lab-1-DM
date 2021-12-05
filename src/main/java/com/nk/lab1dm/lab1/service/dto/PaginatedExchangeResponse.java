package com.nk.lab1dm.lab1.service.dto;

import lombok.Data;

import java.util.List;

@Data
public class PaginatedExchangeResponse<T> {

    private Integer page;

    private Integer totalPages;

    private Long totalElements;

    private List<T> payload;

}
