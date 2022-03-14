package ir.man.spring.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.man.spring.security.appannotations.AppValidEmail;
import ir.man.spring.security.appannotations.AppValidPasswordMatches;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
    private String matchingPassword;
    @NotNull
    @NotEmpty
    @AppValidEmail
    private String email;

//    public boolean allFieldSets() {
//        if(blankOrNullField(username) && blankOrNullField(firstname) && blankOrNullField(lastname)
//                && blankOrNullField(password) && )
//    }
//    public boolean blankOrNullField(String field) {
//        return !"".equals(field) && field != null;
//    }

}
