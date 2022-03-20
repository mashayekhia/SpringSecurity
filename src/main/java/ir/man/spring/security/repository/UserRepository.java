package ir.man.spring.security.repository;

import ir.man.spring.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

// *** 2 *** -> *** 3:UserDao ***
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
     User findUserByEmail(String email);
}
