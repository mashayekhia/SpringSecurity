package ir.man.spring.security.service;

import ir.man.spring.security.model.User;
import ir.man.spring.security.model.UserDto;

public interface IUserService {
    User registerNewUser(UserDto userDto);
    void assignVerificationToken(User user,String token);
}
