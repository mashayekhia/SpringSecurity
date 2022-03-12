package ir.man.spring.security.service;

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
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(UserDto user) {
        return userRepository.save(User.builder().username(user.getUsername())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .password(user.getPassword())
                .email(user.getEmail())
                .build());
    }
}
