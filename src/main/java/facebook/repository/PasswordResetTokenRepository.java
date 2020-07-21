package facebook.repository;

import facebook.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    Boolean existsByToken(String token);
    PasswordResetToken findFirstByToken(String token);
}
