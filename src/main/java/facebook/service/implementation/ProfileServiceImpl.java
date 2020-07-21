package facebook.service.implementation;

import facebook.entity.FriendRequest;
import facebook.entity.Post;
import facebook.entity.User;
import facebook.exception.UserByIdNotFoundException;
import facebook.exception.UserNotFoundException;
import facebook.repository.FriendRequestRepository;
import facebook.repository.PostRepository;
import facebook.repository.UserRepository;
import facebook.service.contract.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final FriendRequestRepository friendRequestRepository;

    @Autowired
    public ProfileServiceImpl(UserRepository userRepository, PostRepository postRepository, FriendRequestRepository friendRequestRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.friendRequestRepository = friendRequestRepository;
    }

    @Override
    public User getProfile(Long id) throws UserByIdNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserByIdNotFoundException("User not found"));
    }

    @Override
    public Set<Post> getUserPosts(User user) {
        return postRepository.findAllByPoster(user);
    }

    @Override
    public FriendRequest getFriendRequestWithId(Long id) throws UserByIdNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserByIdNotFoundException("User not found"));
        return friendRequestRepository.findFriendRequestByRequesterOrReceiver(user, user);
    }

}
