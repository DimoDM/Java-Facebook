package facebook.service.implementation;

import facebook.entity.FriendRequest;
import facebook.entity.Post;
import facebook.entity.User;
import facebook.exception.UserByIdNotFoundException;
import facebook.repository.FriendRequestRepository;
import facebook.repository.PostRepository;
import facebook.repository.UserRepository;
import facebook.service.contract.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Set;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final FriendRequestRepository friendRequestRepository;
    private final UserServiceImpl userService;

    @Autowired
    public ProfileServiceImpl(UserRepository userRepository, PostRepository postRepository, FriendRequestRepository friendRequestRepository, UserServiceImpl userService) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.friendRequestRepository = friendRequestRepository;
        this.userService = userService;
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
        return friendRequestRepository.findFirstByReceiver(user);
    }

    public ModelAndView returnModelAndViewForProfile(Long id, Principal principal, ModelAndView modelAndView) throws UserByIdNotFoundException {


        User userProfile = getProfile(id);
        User authUser = userService.getAuthUser(principal.getName());
        Set<Post> userPosts = getUserPosts(userProfile);
        Set<User> userFriends = userProfile.getUserFriends();
        FriendRequest profileFriendRequest = getFriendRequestWithId(id);
        Set<FriendRequest> friendRequests = friendRequestRepository.findAllByRequesterOrReceiver(authUser, authUser);
        modelAndView.setViewName("profile.html");
        modelAndView.addObject("authUser", authUser);
        modelAndView.addObject("user", userProfile);
        modelAndView.addObject("authUserFriendRequests", friendRequests);
        modelAndView.addObject("friendRequest", profileFriendRequest);
        modelAndView.addObject("friends", userFriends);
        modelAndView.addObject("posts", userPosts);

        return modelAndView;
    }

}
