package facebook.service.implementation;

import facebook.entity.FriendRequest;
import facebook.entity.Post;
import facebook.entity.User;
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
    public User goToProfile(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public Set<Post> getUserPosts(User user) {
        return postRepository.findAllByPoster(user);
    }

    @Override
    public FriendRequest getAuthUserReq(Long id) {
        User user = userRepository.findById(id).get();
        return friendRequestRepository.findByRequesterOrReceiver(user, user);
    }

}
