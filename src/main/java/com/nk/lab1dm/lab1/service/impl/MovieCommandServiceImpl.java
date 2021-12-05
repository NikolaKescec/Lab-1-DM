package com.nk.lab1dm.lab1.service.impl;

import com.nk.lab1dm.lab1.entity.Movie;
import com.nk.lab1dm.lab1.repository.MovieRepository;
import com.nk.lab1dm.lab1.service.MovieCommandService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return movieRepository.saveAll(movies);
    }

}
