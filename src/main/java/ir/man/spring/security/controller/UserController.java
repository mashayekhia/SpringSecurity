package ir.man.spring.security.controller;

import ir.man.spring.security.model.User;
import ir.man.spring.security.model.UserDto;
import ir.man.spring.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
//@SessionAttributes("user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @GetMapping("/registration")
    public ModelAndView registrationForm() {
        //TODO one time token for a submit form
        System.out.println("registrationForm");
        ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("user", new UserDto());
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView registrationUserAccount(/*@ModelAttribute("user")*/ @RequestBody @Valid UserDto user, Errors error) {

//        try {
//            UserDto registered =
//        }
        if (error.hasErrors()) {
            for (ObjectError errorList : error.getFieldErrors()) {
                System.out.println(errorList + " " + errorList.getDefaultMessage() + "\n");
            }
            return new ModelAndView("registration");
        } else {
            userService.save(user);
        }

        return new ModelAndView("successRegister", "user", user);
    }

//    @GetMapping("/login")
//    public ModelAndView login() {
//        System.out.println("hello");
//        return new ModelAndView("login");
//    }

//    @PostMapping("/performe_login")
//    public ModelAndView login() {
//        return new ModelAndView();
//    }

}
