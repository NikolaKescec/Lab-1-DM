package com.nk.lab1dm.lab1.service.impl;

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
    public PaginatedExchangeResponse<Movie> getTraktByTitle(String title, Integer page) {
        final PaginatedExchangeResponse<Movie> response = new PaginatedExchangeResponse<>();
        List<Movie> movies = movieQueryService.findByTitle(title);

        try {
            if (movies.isEmpty()) {
                final PaginatedExchangeResponse<TraktApiMovieQueryExchangeResponse> traktResponse = traktApiExchangeService.fetchTraktByTitle(title, page);
                final List<Movie> newMovies = mapper.mapToList(traktResponse.getPayload());

                response.setPage(traktResponse.getPage());
                response.setTotalPages(traktResponse.getTotalPages());

                movies = movieCommandService.saveAll(newMovies);

                response.setPayload(movies);
            }
        } catch (RuntimeException e) {
            return response;
        }

        response.setPayload(movies);
        response.setTotalElements(movies.size());
        response.setPage(1);
        response.setTotalPages(1);

        return response;
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
