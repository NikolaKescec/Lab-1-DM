package com.nk.lab1dm.lab1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class User {

    @Id
    private String id;

    private String name;

    private String email;

    @JsonIgnore
    private String password;

    private String imageUrl;

    private Boolean emailVerified = false;

    private AuthProvider provider;

    private String providerId;

    private WeatherForecast weatherForecast;

}
