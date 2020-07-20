package facebook.repository;

import facebook.entity.FriendRequest;
import facebook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest,Long> {

    FriendRequest findByRequesterOrReceiver(User requester, User receiver);

    Set<FriendRequest> findByReceiver(User receiver);

    FriendRequest findByRequesterAndReceiver(User requester, User receiver);

    void deleteFriendRequestByRequesterAndReceiver(User requester, User receiver);
}
