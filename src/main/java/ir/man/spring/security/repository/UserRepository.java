package ir.man.spring.security.repository;

import ir.man.spring.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
     User findUserByUsername(String username);
     boolean findUserByEmail(String email);
}
