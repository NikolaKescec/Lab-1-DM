package com.nk.lab1dm.lab1.service.dto.city;

import lombok.Data;

@Data
public class City {

    private Long id;

    private String name;

    private Coordinates coord;

    private String country;

}
