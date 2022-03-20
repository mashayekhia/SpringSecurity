package ir.man.spring.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

@Data
@Entity
@Table(name = "Verification_token")
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long token_id;
    private String token;
    private Date expire_date;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;

    public VerificationToken(User user,String token) {
        this.user = user;
        this.token = token;
        this.expire_date = calculateExpireDate(60*24);
    }

    public VerificationToken() {

    }

    private Date calculateExpireDate(int expireTimeInMinute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));
        calendar.add(Calendar.MINUTE,expireTimeInMinute);
        return new Date(calendar.getTime().getTime());
    }


}
