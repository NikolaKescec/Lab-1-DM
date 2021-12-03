package com.nk.lab1dm.lab1.service.impl;

import com.nk.lab1dm.lab1.entity.User;
import com.nk.lab1dm.lab1.entity.WeatherForecast;
import com.nk.lab1dm.lab1.mapper.GenericCreateMapper;
import com.nk.lab1dm.lab1.service.UserCommandService;
import com.nk.lab1dm.lab1.service.WeatherForecastSagaService;
import com.nk.lab1dm.lab1.service.WeatherApiExchangeService;
import com.nk.lab1dm.lab1.service.dto.ThreeHourWeather;
import com.nk.lab1dm.lab1.service.dto.city.Coordinates;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class WeatherForecastSagaServiceImpl implements WeatherForecastSagaService {

    private WeatherApiExchangeService weatherApiExchangeService;

    private UserCommandService userCommandService;

    private GenericCreateMapper genericCreateMapper;

    @Override
    public WeatherForecast getWeatherForecast(User user, Coordinates coordinates) {
        final WeatherForecast weatherForecast = user.getWeatherForecast();

        if (Objects.isNull(weatherForecast) || timeout(weatherForecast.getList())) {
            final WeatherForecast freshWeatherForecast = genericCreateMapper.map(weatherApiExchangeService.fetchWeatherByGeoPosition(coordinates), WeatherForecast.class);
            user.setWeatherForecast(freshWeatherForecast);
            user = userCommandService.save(user);
        }

        return user.getWeatherForecast();
    }

    private boolean timeout(List<ThreeHourWeather> threeHourWeathers) {
        if (Objects.isNull(threeHourWeathers) || threeHourWeathers.isEmpty()) {
            return true;
        }

        return LocalDateTime.now().isAfter(threeHourWeathers.get(0).getWeatherTime().plusHours(3));
    }

}
