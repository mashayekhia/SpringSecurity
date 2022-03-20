package ir.man.spring.security.configuration;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

// *** 7 *** -> *** 8:WebSecurityConfiguration ***
@Component
public class AppAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        System.out.println("AuthenticationProvider->authenticate");
        String username = auth.getName();
        String password = auth.getCredentials().toString();

        //if(shouldAuthenticateAgainstThirdPartySystem())
        return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
         // else return null;
    }

    @Override
    public boolean supports(Class<?> auth) {
        System.out.println("--AppAuthenticationProvider->support");
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}
