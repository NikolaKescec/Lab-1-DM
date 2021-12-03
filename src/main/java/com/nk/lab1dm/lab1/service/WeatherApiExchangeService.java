package com.nk.lab1dm.lab1.service;

import com.nk.lab1dm.lab1.service.dto.WeatherApiExchangeResponse;
import com.nk.lab1dm.lab1.service.dto.city.Coordinates;

public interface WeatherApiExchangeService {

    WeatherApiExchangeResponse fetchWeatherByGeoPosition(Coordinates coordinates);

}