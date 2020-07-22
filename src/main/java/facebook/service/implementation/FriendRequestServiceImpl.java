package facebook.service.implementation;

import facebook.entity.FriendRequest;
import facebook.entity.User;
import facebook.exception.FriendRequestNotFoundException;
import facebook.exception.UserByIdNotFoundException;
import facebook.repository.FriendRequestRepository;
import facebook.repository.UserRepository;
import facebook.service.contract.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class FriendRequestServiceImpl implements FriendRequestService {

    private final FriendRequestRepository friendRequestRepository;
    private final UserRepository userRepository;

    @Autowired
    public FriendRequestServiceImpl(FriendRequestRepository friendRequestRepository, UserRepository userRepository) {
        this.friendRequestRepository = friendRequestRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void acceptFriendRequest(Long requesterId, User receiver) throws FriendRequestNotFoundException, UserByIdNotFoundException {
        User requester = findUser(requesterId);
        accept(requester,receiver);
    }

    private void accept(User requester, User receiver) throws FriendRequestNotFoundException {
        if (friendRequestRepository.findByRequesterAndReceiver(requester, receiver) == null) {
            throw new FriendRequestNotFoundException("Friend Request not Found." +
                    " The requester must have cancelled his/her request or you could have accepted his/her request already");
        }

        friendRequestRepository.deleteFriendRequestByRequesterAndReceiver(requester, receiver);
        requester.getUserFriends().add(receiver);
        receiver.getUserFriends().add(requester);
        userRepository.save(requester);
        userRepository.save(receiver);
    }

    @Override
    public void declineFriendRequest(Long requesterId, User receiver) throws UserByIdNotFoundException {
        User requester = findUser(requesterId);
        friendRequestRepository.deleteFriendRequestByRequesterAndReceiver(requester, receiver);
    }

    private User findUser(Long id) throws UserByIdNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserByIdNotFoundException("User not found"));
    }

    @Override
    public void sendFriendRequest(User requester, Long receiverId) throws UserByIdNotFoundException {
        FriendRequest friendRequest = new FriendRequest();
        User receiver = findUser(receiverId);
        friendRequest.setRequester(requester);
        friendRequest.setReceiver(receiver);
        friendRequestRepository.save(friendRequest);
    }

    @Override
    public void cancelFriendRequest(User requester, Long receiverId) throws UserByIdNotFoundException {
        User receiver = findUser(receiverId);
        friendRequestRepository.deleteFriendRequestByRequesterAndReceiver(requester, receiver);
    }

    @Override
    public void removeFriend(User authUser, Long userId) throws UserByIdNotFoundException {
        User removedUser = findUser(userId);
        removedUser.getUserFriends().remove(authUser);
        authUser.getUserFriends().remove(removedUser);
        userRepository.save(authUser);
        userRepository.save(removedUser);
    }

}
