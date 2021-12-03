package com.nk.lab1dm.lab1.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nk.lab1dm.lab1.service.dto.weather.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WeatherList {

    private Long dt;

    private Temperature main;

    private Weather weather;

    private Clouds clouds;

    private Wind wind;

    private Double visibility;

    @JsonProperty("pop")
    private Double probabilityOfPrecipitation;

    private Rain rain;

    private Snow snow;

    private Sys sys;

    @JsonProperty("dt_txt")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime weatherTime;

}
