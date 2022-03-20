package ir.man.spring.security.controller;


import ir.man.spring.security.event.OnRegistrationCompleteEvent;
import ir.man.spring.security.exception.UserAlreadyExistException;
import ir.man.spring.security.model.User;
import ir.man.spring.security.model.UserDto;
import ir.man.spring.security.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class RestfullController {

    public static final Logger logger = LoggerFactory.getLogger(RestfullController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @PostMapping("/user/registration")
    public GenericResponse registerUserAccount(@Valid UserDto userDto, HttpServletRequest request) {
        logger.debug("Register an user account with information: {}", userDto);
        User registeredUser = userService.registerNewUser(userDto);

        if (registeredUser == null)
            throw new UserAlreadyExistException();

        String appUrl = request.getProtocol() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + request.getContextPath();
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registeredUser, request.getLocale(), appUrl));

        return new GenericResponse();
    }
}
