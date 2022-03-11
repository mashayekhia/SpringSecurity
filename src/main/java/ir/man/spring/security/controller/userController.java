package ir.man.spring.security.controller;

import ir.man.spring.security.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
//@SessionAttributes("user")
public class userController {

    //@Autowired
    //private UserService userService;

    @GetMapping("/register")
    public ModelAndView registrationForm() {
        //TODO one time token for a submit form
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView registrationUserAccount(/*@ModelAttribute("user")*/ @RequestBody @Valid User user, Errors error) {

        if (error.hasErrors()) {
            for (ObjectError errorList : error.getFieldErrors()) {
                System.out.println(errorList + " " + errorList.getDefaultMessage() + "\n");
            }
            return new ModelAndView("register");
        } else {
           // userService.register(user);
        }

        return new ModelAndView("successRegister", "user", user);
    }

//    @GetMapping("/login")
//    public String login() {
//        System.out.println("hello");
//        return "login";
//    }
//    @PostMapping("/performe_login")
//    public ModelAndView login() {
//        return new ModelAndView();
//    }

}
