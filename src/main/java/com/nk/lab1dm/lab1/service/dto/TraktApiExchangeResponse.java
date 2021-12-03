package com.nk.lab1dm.lab1.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
public class TraktApiExchangeResponse {

    private String title;

    private Integer year;

    private Map<String, String> ids;

    private String tagline;

    private String overview;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate released;

    private Integer runtime;

    private String country;

    private String trailer;

    private String homepage;

    private String status;

    private Double rating;

    private Integer votes;

    @JsonProperty("comment_count")
    private Integer commentCount;

    private String language;

    @JsonProperty("available_translations")
    private List<String> availableTranslations;

    private List<String> genres;

    private String certification;

}
