package facebook.repository;

import facebook.entity.User;
import facebook.entity.UserFriends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFriendsRepository extends JpaRepository<UserFriends, Long> {

    boolean existsByFriendIdAndUser(Long id, User user);

    List<UserFriends> findAllByUser(User user);
}
