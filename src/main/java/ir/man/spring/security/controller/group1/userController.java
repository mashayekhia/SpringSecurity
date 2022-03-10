package ir.man.spring.security.controller.group1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
//@SessionAttributes("user")
public class userController {

    //@Autowired
    //private UserService userService;

//    @GetMapping("/registration")
//    public ModelAndView registrationForm() {
//        ModelAndView modelAndView = new ModelAndView("registration");
//        modelAndView.addObject("user", new User());
//        return modelAndView;
//    }
//
//    @PostMapping("/registration")
//    public ModelAndView registrationUserAccount(@ModelAttribute("user") @Valid User user, Errors error) {
//
//        if (error.hasErrors()) {
//            for (ObjectError errorList : error.getFieldErrors()) {
//                System.out.println(errorList + " " + errorList.getDefaultMessage() + "\n");
//            }
//            return new ModelAndView("registration");
//        } else
//            //Check That the Account Doesn't Already Exist
//            //Check That the Account Doesn't Already Exist
//            userService.register(user);
//
//        return new ModelAndView("successRegister", "user", user);
//    }

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
