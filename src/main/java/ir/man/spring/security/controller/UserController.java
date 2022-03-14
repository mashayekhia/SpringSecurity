package ir.man.spring.security.controller;

import ir.man.spring.security.event.OnRegistrationCompleteEvent;
import ir.man.spring.security.exception.UserAlreadyExistException;
import ir.man.spring.security.model.User;
import ir.man.spring.security.model.UserDto;
import ir.man.spring.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
    public String registrationForm(Model model) {
        //TODO one time token for a submit form
        System.out.println("registrationForm");
        model.addAttribute("user", new UserDto());
        return "user/registration";
    }

    @PostMapping("/registration")
    public ModelAndView registrationUserAccount(@ModelAttribute("user") @Valid UserDto userDao,
            HttpServletRequest request,
             Errors error) {
        System.out.println("registrationUserAccount");
//        try {
            User registeredUser = userService.registerNewUser(userDao);

            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registeredUser, request.getLocale(), appUrl));
        System.out.println("ssssssssssssssssssssss");
//        } catch (UserAlreadyExistException ex) {
//            ModelAndView registrationMV = new ModelAndView("user/registration", "user", userDao);
//            registrationMV.addObject("message", "An account for that username/email already exists.");
//            return registrationMV;
//        } catch (RuntimeException ex) {
//            return new ModelAndView("emailError", "user", userDao);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }

        return new ModelAndView("successRegister", "user", userDao);
    }

    @GetMapping("/registrationConfirm")
    public void registrationConfirm(@RequestParam String token) {
        System.out.println("registrationConfirm with token: " + token);
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
