package facebook.repository;

import facebook.entity.UserLoginData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLoginDataRepository extends JpaRepository<UserLoginData, Long> {

    Boolean existsByEmail(String email);
    UserLoginData findFirstByEmail(String email);
    Boolean existsByPassword(String password);
}
