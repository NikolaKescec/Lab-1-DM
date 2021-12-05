package com.nk.lab1dm.lab1.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@Document
public class Movie {

    @Id
    private String id;

    private String title;

    private Integer score;

    private Integer year;

    @Indexed(unique = true, sparse = true)
    private Long traktId;

    @Indexed(unique = true, sparse = true)
    private String imdbId;

    @Indexed(unique = true, sparse = true)
    private Long tmdbId;

    @Indexed(unique = true, sparse = true)
    private String slug;

    private String tagline;

    private String overview;

    private LocalDate released;

    private Integer runtime;

    private String country;

    private String trailer;

    private String homepage;

    private String status;

    private Double rating;

    private Integer votes;

    private Integer commentCount;

    private String language;

    private List<String> availableTranslations;

    private List<String> genres;

    private String certification;

}
