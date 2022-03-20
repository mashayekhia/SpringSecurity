package ir.man.spring.security.service;

import ir.man.spring.security.exception.UserAlreadyExistException;
import ir.man.spring.security.model.Role;
import ir.man.spring.security.model.User;
import ir.man.spring.security.model.UserDto;
import ir.man.spring.security.model.VerificationToken;
import ir.man.spring.security.repository.UserRepository;
import ir.man.spring.security.repository.VerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// *** 5 *** -> *** 6:AppUserDetailsService ***
@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VerificationRepository verificationRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User registerNewUser(UserDto userDto) throws UserAlreadyExistException {
        if (emailExist(userDto.getEmail()))
            throw new UserAlreadyExistException("User for this username/email already exist");
        System.out.println(userDto);
        User user = new User();
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setIsEnable(false);
        user.setAccountIsExpired(true);
        user.setCredentialsIsExpired(true);
        user.setIsLocked(true);
        user.setRoles(List.of(new Role("ROLE_USER")));
        return userRepository.save(user);
    }

    public User saveRegisteredUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(String token) {
        return verificationRepository.findByToken(token).getUser();
    }

    @Override
    public void assignVerificationToken(User user, String token) {
        VerificationToken newToken = new VerificationToken(user, token);
        verificationRepository.save(newToken);
    }

    @Override
    public VerificationToken getVerificationToken(String token) {
        return verificationRepository.findByToken(token);
    }

    public boolean emailExist(String email) {
        return userRepository.findUserByEmail(email) != null;
    }



}
