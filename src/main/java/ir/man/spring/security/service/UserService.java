package ir.man.spring.security.service;

import ir.man.spring.security.exception.UserAlreadyExistException;
import ir.man.spring.security.model.User;
import ir.man.spring.security.model.UserDto;
import ir.man.spring.security.repository.UserRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User registerNewUser(UserDto userDto) throws UserAlreadyExistException {
        System.out.println("ddddddddddddddddd");
//        if (emailExist(userDto.getEmail()) || usernameExist(userDto.getUsername()))
//            throw new UserAlreadyExistException("User for this username/email already exist");
        System.out.println(userDto);
        return userRepository.save(User.builder().username(userDto.getUsername())
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .enable(false)
                .build());
    }

    @Override
    public void assignVerificationToken(User user,String token) {
       // userRepository.
    }

    public boolean emailExist(String email) {
        return userRepository.findUserByEmail(email);
    }

    public boolean usernameExist(String username) {
        return userRepository.findUserByUsername(username) != null;
    }


}
