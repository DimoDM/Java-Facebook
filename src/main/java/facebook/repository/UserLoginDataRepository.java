package facebook.repository;

import facebook.entity.UserLoginData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLoginDataRepository extends JpaRepository<UserLoginData, Long> {

    Optional<UserLoginData> getFirstByUsernameOrPhoneNumberOrEmail(String username, String phoneNumber, String email);//username - can be email or phoneNumber

    Optional<UserLoginData> findFirstByUsername(String username);

    Boolean existsByEmail(String email);
    UserLoginData findFirstByEmail(String email);
}
