package ru.text.nastya.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.text.nastya.domain.services.SecurityService;

import static ru.text.nastya.web.controllers.UserManageController.PATH_PREFIX;

@Controller
@RequestMapping(PATH_PREFIX)
public class UserManageController {

    public static final String PATH_PREFIX = "user";

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login() {
        return PATH_PREFIX + (SecurityService.isUserAuthenticated()
                ? "/hello"
                : "/login");
    }

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String register() {
        return PATH_PREFIX + "/register";
    }

    @RequestMapping(path = "/denied", method = RequestMethod.GET)
    public String denied() {
        return PATH_PREFIX + "/denied";
    }
}
