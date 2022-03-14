package ir.man.spring.security.listener;

import ir.man.spring.security.event.OnRegistrationCompleteEvent;
import ir.man.spring.security.mail.EmailService;
import ir.man.spring.security.model.User;
import ir.man.spring.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener {

    @Autowired
    private IUserService userService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private MessageSource messageSource;

    @EventListener
    public void onRegistration(OnRegistrationCompleteEvent event) {
        System.out.println("send email " + event.toString());
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.assignVerificationToken(user, token);

        String confirmationUrl = event.getAppUrl() + "/user/registrationConfirm.html?token=" + token;
        //String message = messageSource.getMessage("message.regSucc", null, event.getLocale());
        emailService.sendSimpleMessage(user.getEmail(), "Registration Confirm", "\r\n" + "http://localhost:8081" + confirmationUrl);
    }

}
