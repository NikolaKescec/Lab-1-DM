package com.nk.lab1dm.lab1.service.dto.weatherapi.weather;

import lombok.Data;

@Data
public class Weather {

    private Long id;

    private String main;

    private String description;

    private String icon;
}
