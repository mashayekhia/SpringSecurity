package ir.man.spring.security.service;

import ir.man.spring.security.model.User;
import ir.man.spring.security.model.UserDto;
import ir.man.spring.security.model.VerificationToken;

// *** 4 *** -> *** 5:UserService ***
public interface IUserService {
    User registerNewUser(UserDto userDto);
    void assignVerificationToken(User user,String token);
    VerificationToken getVerificationToken(String token);
    User getUser(String token);
}
