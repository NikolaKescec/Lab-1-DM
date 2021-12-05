package com.nk.lab1dm.lab1.service.dto.traktapi;

import lombok.Data;

@Data
public class TraktApiMovieQueryExchangeResponse {

    private String type;

    private Integer score;

    private TraktMovie movie;

}
