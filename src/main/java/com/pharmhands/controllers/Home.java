package com.pharmhands.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
class Home {

    @GetMapping("/")
    @ResponseBody
    public String hello() {
        System.out.println("hi");
        return "Hello from Spring!";
    }
}