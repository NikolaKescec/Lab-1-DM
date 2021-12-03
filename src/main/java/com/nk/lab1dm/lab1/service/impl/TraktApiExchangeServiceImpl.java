package com.nk.lab1dm.lab1.service.impl;

import com.nk.lab1dm.lab1.config.ApiConfig;
import com.nk.lab1dm.lab1.service.TraktApiExchangeService;
import com.nk.lab1dm.lab1.service.dto.TraktApiExchangeResponse;
import com.nk.lab1dm.lab1.service.dto.WeatherApiExchangeResponse;
import com.nk.lab1dm.lab1.service.dto.city.Coordinates;
import lombok.AllArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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
    public TraktApiExchangeResponse fetchTraktByTitle(String title) {
        return null;
    }

    @Override
    public TraktApiExchangeResponse fetchTraktById(String title) {
        return null;
    }
}
