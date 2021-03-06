package ir.man.spring.security.repository;

import ir.man.spring.security.model.User;
import ir.man.spring.security.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationRepository extends JpaRepository<VerificationToken,Long> {
    VerificationToken findByToken(String token);
    //VerificationToken findByUser(User user);
}
