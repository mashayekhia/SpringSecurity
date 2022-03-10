package ir.man.spring.security.service;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private final static long serialVersionID = -8091879091924046844L;
    private final String jwttoken;

    public JwtResponse(String jwttoken) {
        System.out.println("--JwtResponse");
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }
}
