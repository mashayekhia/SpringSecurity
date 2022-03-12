package ir.man.spring.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.man.spring.security.appannotations.AppValidEmail;
import ir.man.spring.security.appannotations.AppValidPasswordMatches;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
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
    private String username;
    private String firstname;
    private String lastname;
    @JsonIgnore
    private String password;
    private String email;
    private Boolean enable;

//    private List<Role> roles;
//
//    @OneToMany(mappedBy = "user")
//    public List<Role> getRoles(){
//        return roles;
//    }
//    public void setRoles(List<Role> roles) {
//        this.roles = roles;
//    }

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
