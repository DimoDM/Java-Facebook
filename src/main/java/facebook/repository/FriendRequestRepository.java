package facebook.repository;

import facebook.entity.FriendRequest;
import facebook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest,Long> {

    Set<FriendRequest> findByRequesterOrReceiver(User user, User receiver);

}
