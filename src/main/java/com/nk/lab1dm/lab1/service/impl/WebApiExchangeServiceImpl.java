package com.nk.lab1dm.lab1.service.impl;

import com.nk.lab1dm.lab1.config.ApiConfig;
import com.nk.lab1dm.lab1.service.WebApiExchangeService;
import com.nk.lab1dm.lab1.service.dto.WeatherApiExchangeResponse;
import com.nk.lab1dm.lab1.service.dto.city.Coordinates;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class WebApiExchangeServiceImpl implements WebApiExchangeService {

    private final RestTemplate restTemplate;

    private final ApiConfig apiConfig;

    @Override
    public WeatherApiExchangeResponse fetchWeatherWithGeoPosition(Coordinates coordinates) {
        return null;
    }

}
