package com.uwl3.web;

import com.uwl3.domain.dao.HealthNews;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Controller
@RestController
public interface WebController {
    @RequestMapping("")
    public default String index(){
        return "index";
    }

}
