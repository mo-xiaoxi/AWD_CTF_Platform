package com.bupt.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "")
public class IndexController {

    @RequestMapping("/index.html")
    public String index(){
        return "index";
    }


}
