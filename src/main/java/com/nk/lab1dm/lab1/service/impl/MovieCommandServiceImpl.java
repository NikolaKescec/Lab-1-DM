package com.nk.lab1dm.lab1.service.impl;

import com.nk.lab1dm.lab1.entity.Movie;
import com.nk.lab1dm.lab1.mapper.GenericUpdateMapper;
import com.nk.lab1dm.lab1.repository.MovieRepository;
import com.nk.lab1dm.lab1.service.MovieCommandService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MovieCommandServiceImpl implements MovieCommandService {

    private final MovieRepository movieRepository;

    private final GenericUpdateMapper genericUpdateMapper;

    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> saveAll(List<Movie> movies) {
        movies = filterById(movies);
        movies = updateExisting(movies);

        return movieRepository.saveAll(movies);
    }

    private List<Movie> updateExisting(List<Movie> movies) {
        return movies.stream().map(movie -> {
            final Optional<Movie> existingMovie = movieRepository
                    .findByIds(
                            movie.getTraktId() == null ? -1L : movie.getTraktId(),
                            movie.getImdbId() == null ? "" : movie.getImdbId(),
                            movie.getTmdbId() == null ? -1L : movie.getTraktId(),
                            movie.getSlug() == null ? "" : movie.getSlug());

            if (existingMovie.isPresent()) {
                final Movie existing = existingMovie.get();
                movie.setId(existing.getId());
                genericUpdateMapper.map(movie, existing);

                return existing;
            }

            return movie;
        }).collect(Collectors.toList());

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
