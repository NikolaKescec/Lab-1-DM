package com.nk.lab1dm.lab1.service.dto.weatherapi.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Sys {

    @JsonProperty("pod")
    private String partOfDay;

}
