package com.nk.lab1dm.lab1.controller;

import com.nk.lab1dm.lab1.security.CurrentUser;
import com.nk.lab1dm.lab1.security.UserPrincipal;
import com.nk.lab1dm.lab1.service.UserQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class MainController {

    private final UserQueryService userQueryService;

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

        modelAndView.addObject("user", userQueryService.findById(userPrincipal.getId()));
        modelAndView.addObject("source2", userQueryService.findById(userPrincipal.getId()));
        modelAndView.addObject("source3", userQueryService.findById(userPrincipal.getId()));


        return modelAndView;
    }

}
