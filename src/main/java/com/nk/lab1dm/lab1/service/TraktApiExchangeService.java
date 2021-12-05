package com.nk.lab1dm.lab1.service;

import com.nk.lab1dm.lab1.service.dto.traktapi.TraktApiMovieQueryExchangeResponse;
import com.nk.lab1dm.lab1.service.dto.PaginatedExchangeResponse;

import java.util.Optional;

public interface TraktApiExchangeService {

    PaginatedExchangeResponse<TraktApiMovieQueryExchangeResponse> fetchTraktByTitle(String title, Integer page);

    Optional<TraktApiMovieQueryExchangeResponse> fetchTraktByImdbId(String title);

}
