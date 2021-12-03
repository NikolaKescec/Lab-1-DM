package com.nk.lab1dm.lab1.controller;

import com.nk.lab1dm.lab1.entity.User;
import com.nk.lab1dm.lab1.security.CurrentUser;
import com.nk.lab1dm.lab1.security.UserPrincipal;
import com.nk.lab1dm.lab1.service.UserQueryService;
import com.nk.lab1dm.lab1.service.WeatherForecastSagaService;
import com.nk.lab1dm.lab1.service.dto.city.Coordinates;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class JsonController {

    private final WeatherForecastSagaService weatherForecastSagaService;

    private final UserQueryService userQueryService;

    @GetMapping("/weather")
    public ResponseEntity<?> getWeather(@CurrentUser UserPrincipal userPrincipal, Coordinates coordinates) {
        try {
            return ResponseEntity.ok(weatherForecastSagaService.getWeatherForecast(userQueryService.findById(userPrincipal.getId()), coordinates));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
