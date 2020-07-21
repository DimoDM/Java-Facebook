package facebook.repository;

import facebook.entity.PasswordResetToken;
import facebook.entity.UserLoginData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    Boolean existsByToken(String token);
    Boolean existsByUserLoginData(UserLoginData userLoginData);
    PasswordResetToken findFirstByToken(String token);

    PasswordResetToken findFirstByUserLoginData(UserLoginData userLoginData);
}
