package facebook.service.contract;

import facebook.entity.User;
import facebook.exception.FriendRequestNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface FriendRequestService {

    void acceptFriendRequest(Long requesterId, Long receiverId) throws FriendRequestNotFoundException;
    void declineFriendRequest(Long requesterId, Long receiverId);

}
