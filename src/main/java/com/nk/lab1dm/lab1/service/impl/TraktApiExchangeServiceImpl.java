package com.nk.lab1dm.lab1.service.impl;

import com.nk.lab1dm.lab1.config.ApiConfig;
import com.nk.lab1dm.lab1.service.TraktApiExchangeService;
import com.nk.lab1dm.lab1.service.dto.traktapi.TraktApiMovieQueryExchangeResponse;
import com.nk.lab1dm.lab1.service.dto.PaginatedExchangeResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Service
public class TraktApiExchangeServiceImpl implements TraktApiExchangeService {

    private static final String TRAKT_API = "traktapi";

    private static final String TRAKT_API_KEY = "trakt-api-key";

    private static final String TRAKT_API_VERSION = "trakt-api-version";

    private final RestTemplate restTemplate;

    private final ApiConfig apiConfig;

    public TraktApiExchangeServiceImpl(RestTemplateBuilder restTemplateBuilder, ApiConfig apiConfig) {
        this.restTemplate = restTemplateBuilder
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(TRAKT_API_KEY, apiConfig.getKeys().get(TRAKT_API))
                .defaultHeader(TRAKT_API_VERSION, "2")
                .build();
        this.apiConfig = apiConfig;
    }

    @Override
    public PaginatedExchangeResponse<TraktApiMovieQueryExchangeResponse> fetchTraktByTitle(String title, Integer page) {
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("query", title);

        final URI uri = createUri("/search/movie", queryParams, Collections.emptyMap(), page);

        final PaginatedExchangeResponse<TraktApiMovieQueryExchangeResponse> paginatedResponse = this.getDataFromTraktApi(uri);

        return paginatedResponse;
    }


    @Override
    public Optional<TraktApiMovieQueryExchangeResponse> fetchTraktByImdbId(String id) {
        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("type", "movie");

        final Map<String, Object> pathVariables = new HashMap<>();
        pathVariables.put("id", id);

        final URI uri = createUri("/search/imdb/{id}", queryParams, pathVariables, null);

        final ResponseEntity<List<TraktApiMovieQueryExchangeResponse>> responseEntity =
                restTemplate.exchange(uri,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {
                        });

        if (Objects.isNull(responseEntity.getBody())) {
            return Optional.empty();
        }

        return Optional.ofNullable(responseEntity.getBody().isEmpty() ? null : responseEntity.getBody().get(0));
    }

    private PaginatedExchangeResponse<TraktApiMovieQueryExchangeResponse> getDataFromTraktApi(URI uri) {
        final ResponseEntity<List<TraktApiMovieQueryExchangeResponse>> responseEntity =
                restTemplate.exchange(uri,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {
                        });

        final PaginatedExchangeResponse<TraktApiMovieQueryExchangeResponse> paginatedResponse = new PaginatedExchangeResponse<>();
        paginatedResponse.setPage(extractNumber(responseEntity.getHeaders(), "x-pagination-page"));
        paginatedResponse.setTotalPages(extractNumber(responseEntity.getHeaders(), "x-pagination-page-count"));
        paginatedResponse.setTotalElements(extractNumber(responseEntity.getHeaders(), "x-pagination-item-count"));
        paginatedResponse.setPayload(responseEntity.getBody());

        return paginatedResponse;
    }

    private Integer extractNumber(HttpHeaders httpHeaders, String header) {
        final List<String> headerList = httpHeaders.getOrDefault(header, null);

        try {
            return Integer.parseInt(headerList.get(0));
        } catch (RuntimeException e) {
            return null;
        }
    }

    private URI createUri(String path, MultiValueMap<String, String> queryParams, Map<String, Object> pathVariables, Integer page) {
        final UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(apiConfig.getUrls().get(TRAKT_API))
                .path(path)
                .queryParam("extended", "full")
                .queryParams(queryParams);

        if (page != null) {
            uri.queryParam("limit", apiConfig.getPageSize()).queryParam("page", page);
        }

        return uri
                .build(pathVariables);
    }

}
