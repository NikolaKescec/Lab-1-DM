package com.nk.lab1dm.lab1.service.dto;

import com.nk.lab1dm.lab1.service.dto.city.City;
import lombok.Data;

import java.util.List;

@Data
public class WeatherApiExchangeResponse {

    private String cod;

    private Double message;

    private Integer cnt;

    private List<ThreeHourWeather> list;

    private City city;

}
