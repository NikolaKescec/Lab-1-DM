package com.nk.lab1dm.lab1.entity;

import com.nk.lab1dm.lab1.service.dto.ThreeHourWeather;
import com.nk.lab1dm.lab1.service.dto.city.City;
import lombok.Data;

import java.util.List;

@Data
public class WeatherForecast {

    private Integer cnt;

    private List<ThreeHourWeather> list;

    private City city;
    
}
