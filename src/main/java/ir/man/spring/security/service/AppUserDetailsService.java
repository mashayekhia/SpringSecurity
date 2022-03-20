package ir.man.spring.security.service;

import ir.man.spring.security.model.Privilege;
import ir.man.spring.security.model.Role;
import ir.man.spring.security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

// *** 6 *** -> *** 7:AppAuthenticationProvider ***
//Load Username and Password from the persistence layer
//Loading User Details for Security Login
@Service
@Transactional
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("-7--JwtUserDetailsService->loadUserByUsername(username)");
        final User user = userService.findByEmail(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found with username: " + username);

        return new org.springframework.security.core.userdetails.User(
                user.getEmail()
                , user.getPassword()
                , user.getIsEnable()
                , user.getAccountIsExpired()
                , user.getCredentialsIsExpired()
                , user.getIsLocked()
                , getGrantedAuthorities(getRoles(user.getRoles()))
        );

    }

    public List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
        return authorities;
    }

    public List<String> getRoles(List<Role> roles) {

        List<String> roleList = new ArrayList<>();
        List<Privilege> privileges = new ArrayList<>();

        roles.forEach(role -> {
            roleList.add(role.getName());
            privileges.addAll(role.getPrivileges());
        });

        privileges.forEach(privilege -> roleList.add(privilege.getName()));

        return roleList;
    }

}
