package com.nk.lab1dm.lab1.controller;

import com.nk.lab1dm.lab1.entity.User;
import com.nk.lab1dm.lab1.security.CurrentUser;
import com.nk.lab1dm.lab1.security.UserPrincipal;
import com.nk.lab1dm.lab1.service.MovieQueryService;
import com.nk.lab1dm.lab1.service.MovieSagaService;
import com.nk.lab1dm.lab1.service.UserQueryService;
import com.nk.lab1dm.lab1.service.WeatherForecastSagaService;
import com.nk.lab1dm.lab1.service.dto.weatherapi.city.Coordinates;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class MainController {

    private final UserQueryService userQueryService;

    private final WeatherForecastSagaService weatherForecastSagaService;

    private final MovieQueryService movieQueryService;

    private final MovieSagaService movieSagaService;

    @GetMapping("/")
    public ModelAndView index(@CurrentUser UserPrincipal userPrincipal) {
        final ModelAndView modelAndView = new ModelAndView("index");

        if (userPrincipal != null) {
            modelAndView.addObject("user", userQueryService.findById(userPrincipal.getId()));
        }

        return modelAndView;
    }

    @GetMapping("/private")
    public ModelAndView privat(@CurrentUser UserPrincipal userPrincipal, Coordinates coordinates) {
        final ModelAndView modelAndView = new ModelAndView("/private");

        final User user = userQueryService.findById(userPrincipal.getId());

        modelAndView.addObject("user", user);
        modelAndView.addObject("weather", weatherForecastSagaService.getWeatherForecast(user, coordinates));
        modelAndView.addObject("movies", movieQueryService.findAll());

        return modelAndView;
    }

    @PostMapping("/private")
    public ModelAndView privat(@CurrentUser UserPrincipal userPrincipal, @RequestParam String title) {
        final ModelAndView modelAndView = new ModelAndView("/private");

        final User user = userQueryService.findById(userPrincipal.getId());

        modelAndView.addObject("user", user);
        modelAndView.addObject("weather", weatherForecastSagaService.getWeatherForecast(user, null));
        modelAndView.addObject("movies", movieSagaService.getTraktByTitle(title));
        modelAndView.addObject("search", true);

        return modelAndView;
    }


}
