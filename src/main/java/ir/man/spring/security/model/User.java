package ir.man.spring.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.man.spring.security.appannotations.AppValidEmail;
import ir.man.spring.security.appannotations.AppValidPasswordMatches;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@AppValidPasswordMatches
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;
    @NotNull
    @NotEmpty
    private String username;
    @NotNull
    @NotEmpty
    private String firstname;
    @NotNull
    @NotEmpty
    private String lastname;
    @NotNull
    @NotEmpty
    @JsonIgnore
    private String password;
    //private String matchingPassword;
    @NotNull
    @NotEmpty
    @AppValidEmail
    private String email;

    @Override
    public String toString() {
        return "User{" +
                "id=" + uid +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
