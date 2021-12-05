package com.nk.lab1dm.lab1.service;

import com.nk.lab1dm.lab1.entity.Movie;

import java.util.List;

public interface MovieQueryService {

    Movie findById(String id);

    Movie findByImdbId(String imdbId);

    List<Movie> findByTitle(String title);

    List<Movie> findAll();

}
