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

// *** 1 *** -> *** 2:UserRepository ***
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    @Column(name = "enable")
    private Boolean isEnable;
    @Column(name = "account_non_expired")
    private Boolean accountIsExpired;
    @Column(name = "credentials_non_expired")
    private Boolean credentialsIsExpired;
    @Column(name = "account_non_locked")
    private Boolean isLocked;

    @ManyToMany
    @JoinTable(name = "users_roles"
            , joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private List<Role> roles;
}
