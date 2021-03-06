package facebook.repository;

import facebook.entity.FriendRequest;
import facebook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest,Long> {

    Set<FriendRequest> findAllByRequesterOrReceiver(User requester, User receiver);

    FriendRequest findFirstByReceiver(User user);

    boolean existsByReceiverAndRequester(User user, User requester);

    Set<FriendRequest> findAllByReceiverOrRequester(User receiver, User user);

    FriendRequest findFriendRequestByRequesterOrReceiver(User requester, User receiver);

    FriendRequest findByRequesterAndReceiver(User requester, User receiver);

    void deleteFriendRequestByRequesterAndReceiver(User requester, User receiver);
}
