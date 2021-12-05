package com.nk.lab1dm.lab1.service.impl;

import com.nk.lab1dm.lab1.entity.Movie;
import com.nk.lab1dm.lab1.repository.MovieRepository;
import com.nk.lab1dm.lab1.service.MovieCommandService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieCommandServiceImpl implements MovieCommandService {

    private final MovieRepository movieRepository;

    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> saveAll(List<Movie> movies) {
        movies = filterById(movies);

        return movieRepository.saveAll(movies);
    }

    private List<Movie> filterById(List<Movie> movies) {
        final Set<Long> traktSet = new HashSet<>();
        final Set<String> imdbSet = new HashSet<>();
        final Set<Long> tmdbSet = new HashSet<>();
        final Set<String> slugSet = new HashSet<>();


        return movies.stream().filter(movie -> {
            if (traktSet.contains(movie.getTraktId())) {
                return false;
            } else if (imdbSet.contains(movie.getImdbId())) {
                return false;
            } else if (tmdbSet.contains(movie.getTmdbId())) {
                return false;
            } else if (slugSet.contains(movie.getSlug())) {
                return false;
            } else {
                traktSet.add(movie.getTraktId());
                imdbSet.add(movie.getImdbId());
                tmdbSet.add(movie.getTmdbId());
                slugSet.add(movie.getSlug());
                return true;
            }
        }).collect(Collectors.toList());
    }

}
