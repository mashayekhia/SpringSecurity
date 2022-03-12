package ir.man.spring.security.controller;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@EnableGlobalMethodSecurity(jsr250Enabled = true)
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping("/admin")
    public ModelAndView admin() {
        System.out.println("in admin controller");
        return new ModelAndView("admin");
    }
}
