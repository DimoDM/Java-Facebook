package facebook.repository;

import facebook.entity.User;
import facebook.entity.UserFriends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFriendsRepository extends JpaRepository<UserFriends, Long> {

    boolean existsByFriendIdAndUser(Long id, User user);
}
