package com.nk.lab1dm.lab1.service;

import com.nk.lab1dm.lab1.entity.Movie;
import com.nk.lab1dm.lab1.entity.User;

import java.util.List;

public interface MovieCommandService {

    Movie save(Movie movie);

    List<Movie> saveAll(List<Movie> movies);

}
