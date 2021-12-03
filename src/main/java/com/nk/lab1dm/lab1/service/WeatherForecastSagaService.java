package com.nk.lab1dm.lab1.service;

import com.nk.lab1dm.lab1.entity.User;
import com.nk.lab1dm.lab1.entity.WeatherForecast;
import com.nk.lab1dm.lab1.service.dto.city.Coordinates;

public interface WeatherForecastSagaService {

    WeatherForecast getWeatherForecast(User user, Coordinates coordinates);

}
