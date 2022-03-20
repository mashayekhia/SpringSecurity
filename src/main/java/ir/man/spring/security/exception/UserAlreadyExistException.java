package ir.man.spring.security.exception;

public class UserAlreadyExistException extends RuntimeException {

    public UserAlreadyExistException(String s) {
        System.out.println(s);
    }

    public UserAlreadyExistException() {

    }
}
