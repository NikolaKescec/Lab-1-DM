package com.nk.lab1dm.lab1.service.impl;

import com.nk.lab1dm.lab1.config.ApiConfig;
import com.nk.lab1dm.lab1.entity.Movie;
import com.nk.lab1dm.lab1.exception.ResourceNotFoundException;
import com.nk.lab1dm.lab1.mapper.TraktApiMovieQueryExchangeResponseMovieCreateMapper;
import com.nk.lab1dm.lab1.service.MovieCommandService;
import com.nk.lab1dm.lab1.service.MovieQueryService;
import com.nk.lab1dm.lab1.service.TraktApiExchangeService;
import com.nk.lab1dm.lab1.service.MovieSagaService;
import com.nk.lab1dm.lab1.service.dto.PaginatedExchangeResponse;
import com.nk.lab1dm.lab1.service.dto.traktapi.TraktApiMovieQueryExchangeResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieSagaServiceImpl implements MovieSagaService {

    private final MovieQueryService movieQueryService;

    private final MovieCommandService movieCommandService;

    private final TraktApiExchangeService traktApiExchangeService;

    private final TraktApiMovieQueryExchangeResponseMovieCreateMapper mapper;

    @Override
    public List<Movie> getTraktByTitle(String title) {
        final List<Movie> movies;

        try {
            final Page<TraktApiMovieQueryExchangeResponse> traktResponse = traktApiExchangeService.fetchTraktByTitle(title, 1);
            final List<Movie> newMovies = mapper.mapToList(traktResponse.getContent());

            movies = movieCommandService.saveAll(newMovies);
        } catch (RuntimeException ignored) {
            return null;
        }

        return movies;
    }

    @Override
    public Movie getTraktByImdbId(String imdbId) {
        Movie movie;

        try {
            movie = movieQueryService.findByImdbId(imdbId);
        } catch (ResourceNotFoundException resourceNotFoundException) {
            final Optional<TraktApiMovieQueryExchangeResponse> traktResponse = traktApiExchangeService.fetchTraktByImdbId(imdbId);

            final Movie newMovie = mapper.map(traktResponse.orElseThrow(NullPointerException::new));

            movie = movieCommandService.save(newMovie);
        }

        return movie;
    }

}
