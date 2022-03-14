package ir.man.spring.security.service;

import ir.man.spring.security.model.Role;
import ir.man.spring.security.model.User;
import ir.man.spring.security.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Load Username and Password from database
@Service
@Transactional
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("-7--JwtUserDetailsService->loadUserByUsername(username)");
        final User user = userService.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found with username: " + username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword()
                , true, true, true, true, getAuthorities(user.getRoles()));
    }

    public List<GrantedAuthority> getAuthorities(List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return authorities;
    }
}
