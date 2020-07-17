package facebook.repository;

import facebook.entity.Role;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository {

    Optional<Role> findFirstByAuthority(String authority);
}
