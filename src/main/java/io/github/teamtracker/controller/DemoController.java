package io.github.teamtracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/demo")
public class DemoController {

    @RequestMapping(path = "/hello")
    public String hello() {
        return "Hello World!";
    }

    @RequestMapping(path = "/goodbye")
    public String goodbye() {
        return "Goodbye World!";
    }
}