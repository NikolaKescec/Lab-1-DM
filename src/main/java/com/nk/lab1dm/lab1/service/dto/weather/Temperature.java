package com.nk.lab1dm.lab1.service.dto.weather;

import lombok.Data;

@Data
public class Temperature {

    private Double temp;

    private Double feels_like;

    private Double temp_min;

    private Double temp_max;

    private Double pressure;

    private Double sea_level;

    private Double grnd_level;

    private Double humidity;

    private Double temp_kf;

}
