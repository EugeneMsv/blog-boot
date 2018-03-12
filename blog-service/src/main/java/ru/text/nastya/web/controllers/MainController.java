package ru.text.nastya.web.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    //todo не работает viewResolver
    @RequestMapping(path = "/root", method = RequestMethod.GET)
    public String index() {
        System.out.println("MainController.index");
        return "index";
    }


    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public String hello(@AuthenticationPrincipal User user) {
        System.out.println("MainController.hello " + user);
        return "hello";
    }

    @RequestMapping(path = {"/home", "/"}, method = RequestMethod.GET)
    public String home() {
        System.out.println("MainController.home");
        return "home";
    }
}
