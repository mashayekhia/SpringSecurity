package ir.man.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping(value = {"/", "/home", "/home.*", "index", "/index*"})
    public String home() {
        System.out.println("APP->DefaultController->home");
        return "home";
    }

    @PostMapping("perform_login")
    public String toString() {
        return "";
    }
}
