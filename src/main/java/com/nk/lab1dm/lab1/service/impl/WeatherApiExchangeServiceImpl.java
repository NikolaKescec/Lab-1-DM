package com.nk.lab1dm.lab1.service.impl;

import com.nk.lab1dm.lab1.config.ApiConfig;
import com.nk.lab1dm.lab1.service.WeatherApiExchangeService;
import com.nk.lab1dm.lab1.service.dto.weatherapi.WeatherApiExchangeResponse;
import com.nk.lab1dm.lab1.service.dto.weatherapi.city.Coordinates;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherApiExchangeServiceImpl implements WeatherApiExchangeService {

    private static final String WEATHERAPI = "weatherapi";

    private static final String MEASUREMENT = "metric";

    private final RestTemplate restTemplate;

    private final ApiConfig apiConfig;

    public WeatherApiExchangeServiceImpl(RestTemplateBuilder restTemplateBuilder, ApiConfig apiConfig) {
        this.restTemplate = restTemplateBuilder.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE).build();
        this.apiConfig = apiConfig;
    }

    @Override
    public WeatherApiExchangeResponse fetchWeatherByGeoPosition(Coordinates coordinates) {
        final String url = UriComponentsBuilder.fromHttpUrl(apiConfig.getUrls().get(WEATHERAPI))
                .queryParam("appid", apiConfig.getKeys().get(WEATHERAPI))
                .queryParam("units", MEASUREMENT)
                .queryParam("lat", coordinates.getLat())
                .queryParam("lon", coordinates.getLon())
                .encode()
                .toUriString();

        HttpEntity<WeatherApiExchangeResponse> exchangeResponse = restTemplate.getForEntity(url, WeatherApiExchangeResponse.class);

        return exchangeResponse.getBody();
    }

}
