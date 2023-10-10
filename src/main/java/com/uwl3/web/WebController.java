package com.uwl3.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public interface WebController {
    @RequestMapping("")
    public default String index(){
        return "index";
    }
}
