package com.nk.lab1dm.lab1.service;

import com.nk.lab1dm.lab1.service.dto.weatherapi.WeatherApiExchangeResponse;
import com.nk.lab1dm.lab1.service.dto.weatherapi.city.Coordinates;

public interface WeatherApiExchangeService {

    WeatherApiExchangeResponse fetchWeatherByGeoPosition(Coordinates coordinates);

}
