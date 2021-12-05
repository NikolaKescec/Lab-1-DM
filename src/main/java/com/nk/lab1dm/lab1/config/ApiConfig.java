package com.nk.lab1dm.lab1.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "api")
@AllArgsConstructor
@Data
public class ApiConfig {

    private final Map<String, String> keys;

    private final Map<String, String> urls;

    private final Integer pageSize = 20;

}
