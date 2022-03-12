package ir.man.spring.security.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

@Data
@Entity
public class VerificationToken {
    @Id
    private Long tid;
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "uid" , nullable = false)
    private User user;

    private Date expireDate;

    private Date calculateExpireDate(int expireTimeInMinute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));
        calendar.add(Calendar.MINUTE,expireTimeInMinute);
        return new Date(calendar.getTime().getTime());
    }
}
