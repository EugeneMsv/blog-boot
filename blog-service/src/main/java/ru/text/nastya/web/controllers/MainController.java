package ru.text.nastya.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String index() {
        System.out.println("MainController.index");
        return "index";
    }
}
