package com.nk.lab1dm.lab1.service.dto.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Rain {

    @JsonProperty("3h")
    private Double ThreeHour;

}
