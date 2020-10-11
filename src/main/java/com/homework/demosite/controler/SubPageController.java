package com.homework.demosite.controler;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SubPageController {

    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN, USER')")
    @RequestMapping(value="/user", method = RequestMethod.GET)
    public ModelAndView user(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("subpages/user");
        return modelAndView;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ADMIN, EDITOR')")
    @RequestMapping(value="/editor", method = RequestMethod.GET)
    public ModelAndView editor(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("subpages/editor");
        return modelAndView;
    }

}
