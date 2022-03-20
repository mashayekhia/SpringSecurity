package ir.man.spring.security.controller;

import ir.man.spring.security.event.OnRegistrationCompleteEvent;
import ir.man.spring.security.exception.UserAlreadyExistException;
import ir.man.spring.security.model.User;
import ir.man.spring.security.model.UserDto;
import ir.man.spring.security.model.VerificationToken;
import ir.man.spring.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;

@Controller
@RequestMapping("/user")
//@SessionAttributes("user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/registration.html")
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
        try {
            User registeredUser = userService.registerNewUser(userDao);

            String appUrl = request.getProtocol() + "//" + request.getServerName() + "//" + request.getServerPort() + ":" + request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registeredUser, request.getLocale(), appUrl));

        } catch (UserAlreadyExistException ex) {
            ModelAndView registrationMV = new ModelAndView("user/registration", "user", userDao);
            registrationMV.addObject("message", "An account for that username/email already exists.");
            return registrationMV;
        } catch (RuntimeException ex) {
            return new ModelAndView("emailError", "user", userDao);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return new ModelAndView("user/registration_success", "user", userDao);
    }

    @GetMapping("/registrationConfirm.html")
    public String registrationConfirm(HttpServletRequest request, @RequestParam("token") String token, Model model,
                                      HttpServletResponse response) {
        System.out.println("registrationConfirm with token: " + token);

        Locale locale = request.getLocale();

        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (token == null) {
            String message = messageSource.getMessage("auth.message.invalidToken", null, locale);
            model.addAttribute("message", message);
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }
        if (verificationToken.getExpire_date().getTime() - Calendar.getInstance().getTime().getTime() <= 0) {
            String message = messageSource.getMessage("auth.message.expiredToken", null, locale);
            model.addAttribute("message", message);
            return "redirect:/badUser.html?lang=" + request.getLocale().getLanguage();
        }

        User user = verificationToken.getUser();
        user.setIsEnable(true);
        userService.saveRegisteredUser(user);
        return "redirect:/login.html?lang=" + locale.getLanguage();
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
