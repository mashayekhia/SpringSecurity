package ir.man.spring.security.controller;

import ir.man.spring.security.component.JwtTokenUtil;
import ir.man.spring.security.model.JwtRequest;
import ir.man.spring.security.model.JwtResponse;
import ir.man.spring.security.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> generateAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        System.out.println("-6--generateAuthenticationToken(@RequestBody JwtRequest authenticationRequest)");
        //From Database
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        System.out.println("Username: " + userDetails.getUsername());
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword()));
            System.out.println("->"+authenticate);
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        System.out.println("-----------------------------------------------");
        final String newToken = jwtTokenUtil.generateToken(userDetails);
        System.out.println("token: " + newToken + " Generated");
        return ResponseEntity.ok(new JwtResponse(newToken));
    }
}
