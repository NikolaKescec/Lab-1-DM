package com.nk.lab1dm.lab1.service.impl;

import com.nk.lab1dm.lab1.entity.User;
import com.nk.lab1dm.lab1.entity.WeatherForecast;
import com.nk.lab1dm.lab1.service.WeatherForecastSagaService;
import com.nk.lab1dm.lab1.service.WebApiExchangeService;
import com.nk.lab1dm.lab1.service.dto.city.Coordinates;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WeatherForecastSagaServiceImpl implements WeatherForecastSagaService {

    private WebApiExchangeService webApiExchangeService;

    @Override
    public WeatherForecast getWeatherForecast(User user, String city) {
        return null;
    }

    @Override
    public WeatherForecast getWeatherForecast(User user, Coordinates coordinates) {
        return null;
    }
}
