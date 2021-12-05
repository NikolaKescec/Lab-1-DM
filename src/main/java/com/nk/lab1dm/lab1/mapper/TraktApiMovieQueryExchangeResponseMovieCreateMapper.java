package com.nk.lab1dm.lab1.mapper;

import com.nk.lab1dm.lab1.entity.Movie;
import com.nk.lab1dm.lab1.repository.MovieRepository;
import com.nk.lab1dm.lab1.service.dto.traktapi.TraktApiMovieQueryExchangeResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class TraktApiMovieQueryExchangeResponseMovieCreateMapper {

    private final ModelMapper modelMapper;

    public Movie map(TraktApiMovieQueryExchangeResponse traktApiMovieQueryExchangeResponse) {
        final Movie movie = new Movie();

        modelMapper.map(traktApiMovieQueryExchangeResponse.getMovie(), movie);
        movie.setImdbId(traktApiMovieQueryExchangeResponse.getMovie().getIds().get("imdb"));
        movie.setTraktId(Long.parseLong(traktApiMovieQueryExchangeResponse.getMovie().getIds().get("trakt")));
        movie.setTmdbId(Long.parseLong(traktApiMovieQueryExchangeResponse.getMovie().getIds().get("tmdb")));
        movie.setSlug(traktApiMovieQueryExchangeResponse.getMovie().getIds().get("slug"));
        movie.setScore(traktApiMovieQueryExchangeResponse.getScore());

        return movie;
    }

    public List<Movie> mapToList(List<TraktApiMovieQueryExchangeResponse> traktApiMovieQueryExchangeResponseList) {
        return traktApiMovieQueryExchangeResponseList.stream().map(this::map).collect(Collectors.toList());
    }

}
