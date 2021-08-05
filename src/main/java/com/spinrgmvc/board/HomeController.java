package com.spinrgmvc.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @RequestMapping(value = "/")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "Hello, World!");
        mav.setViewName("hello");
        return mav;
    }

    @RequestMapping(value = "/show")
    @ResponseBody
    public String show() {
        return "<h1>show</h1>";
    }
}
