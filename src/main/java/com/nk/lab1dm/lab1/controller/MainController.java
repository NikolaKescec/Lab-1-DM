package com.nk.lab1dm.lab1.controller;

import com.nk.lab1dm.lab1.entity.User;
import com.nk.lab1dm.lab1.security.CurrentUser;
import com.nk.lab1dm.lab1.security.UserPrincipal;
import com.nk.lab1dm.lab1.service.UserQueryService;
import com.nk.lab1dm.lab1.service.WeatherForecastSagaService;
import com.nk.lab1dm.lab1.service.dto.city.Coordinates;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class MainController {

    private final UserQueryService userQueryService;

    private final WeatherForecastSagaService weatherForecastSagaService;

    @GetMapping("/")
    public ModelAndView index(@CurrentUser UserPrincipal userPrincipal) {
        final ModelAndView modelAndView = new ModelAndView("index");

        if (userPrincipal != null) {
            modelAndView.addObject("user", userQueryService.findById(userPrincipal.getId()));
        }

        return modelAndView;
    }

    @GetMapping("/private")
    public ModelAndView privat(@CurrentUser UserPrincipal userPrincipal) {
        final ModelAndView modelAndView = new ModelAndView("/private");

        final User user = userQueryService.findById(userPrincipal.getId());


        modelAndView.addObject("user", user);
        modelAndView.addObject("weather", weatherForecastSagaService.getWeatherForecast(user, new Coordinates(45.848986, 15.810560)));
        modelAndView.addObject("movies", null);


        return modelAndView;
    }

}
