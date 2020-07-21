package facebook.service.contract;

import facebook.entity.User;
import facebook.exception.FriendRequestNotFoundException;
import facebook.exception.UserByIdNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface FriendRequestService {

    void acceptFriendRequest(Long requesterId, User receiver) throws FriendRequestNotFoundException, UserByIdNotFoundException;
    void declineFriendRequest(Long requesterId, User receiver) throws UserByIdNotFoundException;
    void sendFriendRequest(User requester, Long receiverId) throws UserByIdNotFoundException;
    void cancelFriendRequest(User requester, Long receiverId) throws UserByIdNotFoundException;

    void removeFriend(User authUser, Long userId) throws UserByIdNotFoundException;
}
