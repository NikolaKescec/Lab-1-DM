package com.nk.lab1dm.lab1.service;

import com.nk.lab1dm.lab1.service.dto.traktapi.TraktApiMovieQueryExchangeResponse;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface TraktApiExchangeService {

    Page<TraktApiMovieQueryExchangeResponse> fetchTraktByTitle(String title, Integer page);

    Optional<TraktApiMovieQueryExchangeResponse> fetchTraktByImdbId(String title);

}
