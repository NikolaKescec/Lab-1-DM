package com.nk.lab1dm.lab1.service;

import com.nk.lab1dm.lab1.entity.Movie;
import com.nk.lab1dm.lab1.service.dto.PaginatedExchangeResponse;

public interface MovieSagaService {

    PaginatedExchangeResponse<Movie> getTraktByTitle(String title, Integer page);

    Movie getTraktByImdbId(String imdbId);

}
