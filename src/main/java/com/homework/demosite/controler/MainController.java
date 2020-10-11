package com.homework.demosite.controler;

import com.homework.demosite.model.User;
import com.homework.demosite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.time.format.DateTimeFormatter;

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value="/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = user.getLastLogin().format(formatter);

        modelAndView.addObject("username", user.getUsername());
        modelAndView.addObject("lastLogin", formattedDateTime);
        modelAndView.setViewName("home");
        return modelAndView;
    }

}
