package com.nk.lab1dm.lab1.repository;

import com.nk.lab1dm.lab1.entity.Movie;
import com.nk.lab1dm.lab1.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {

    Optional<Movie> findByImdbId(String imdbId);

    @Query(value = "{'title': {$regex : ?0, $options: 'i'}}")
    List<Movie> findByTitle(String title);

}
