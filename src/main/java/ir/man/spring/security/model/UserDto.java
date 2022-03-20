package ir.man.spring.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.man.spring.security.appannotations.AppValidEmail;
import ir.man.spring.security.appannotations.AppValidPasswordMatches;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

// *** 3 *** -> *** 4:IUserService ***
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@AppValidPasswordMatches
public class UserDto {
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
    private String confirmPassword;
    @NotNull
    @NotEmpty
    @AppValidEmail
    private String email;
    private Boolean enable;
}
