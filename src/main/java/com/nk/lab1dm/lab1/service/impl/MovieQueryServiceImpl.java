package com.nk.lab1dm.lab1.service.impl;

import com.nk.lab1dm.lab1.entity.Movie;
import com.nk.lab1dm.lab1.entity.User;
import com.nk.lab1dm.lab1.exception.ResourceNotFoundException;
import com.nk.lab1dm.lab1.repository.MovieRepository;
import com.nk.lab1dm.lab1.service.MovieQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MovieQueryServiceImpl implements MovieQueryService {

    private final MovieRepository movieRepository;

    @Override
    public Movie findById(String id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id));
    }

    @Override
    public Movie findByImdbId(String imdbId) {
        return movieRepository.findByImdbId(imdbId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", imdbId));
    }

    @Override
    public List<Movie> findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }
}

