package com.nk.lab1dm.lab1.service;

import com.nk.lab1dm.lab1.entity.Movie;
import com.nk.lab1dm.lab1.service.dto.PaginatedExchangeResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MovieSagaService {

    List<Movie> getTraktByTitle(String title);

    Movie getTraktByImdbId(String imdbId);

}
