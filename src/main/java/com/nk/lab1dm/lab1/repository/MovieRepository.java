package com.nk.lab1dm.lab1.repository;

import com.nk.lab1dm.lab1.entity.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

    Optional<Movie> findByImdbId(String imdbId);

    @Query(value = "{'title': {$regex : ?0, $options: 'i'}}")
    List<Movie> findByTitle(String title);

}
