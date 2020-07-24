package facebook.controller;

import facebook.entity.FriendRequest;
import facebook.entity.Post;
import facebook.entity.User;
import facebook.exception.UserByEmailNotFoundException;
import facebook.exception.UserByIdNotFoundException;
import facebook.repository.FriendRequestRepository;
import facebook.service.contract.FacebookProfileService;
import facebook.service.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Set;


@Controller
public class FacebookProfileController extends BaseController{

    private final FacebookProfileService facebookProfileService;
    private final UserService userService;
    private final FriendRequestRepository fReqRepo;

    @Autowired
    public FacebookProfileController(FacebookProfileService facebookProfileService, UserService userService, FriendRequestRepository fReqRepo) {
        this.facebookProfileService = facebookProfileService;
        this.userService = userService;
        this.fReqRepo = fReqRepo;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ModelAndView profile(@PathVariable("id") Long id, ModelAndView modelAndView, Principal principal) throws UserByEmailNotFoundException, UserByIdNotFoundException {
        User userProfile = facebookProfileService.getProfile(id);
        User authUser = userService.getAuthUser(principal.getName());
        Set<Post> userPosts = facebookProfileService.getUserPosts(userProfile);
        Set<User> userFriends = userProfile.getUserFriends();
        FriendRequest profileFriendRequest = facebookProfileService.getFriendRequestWithId(id);
        Set<FriendRequest> friendRequests = fReqRepo.findAllByRequesterOrReceiver(authUser, authUser);
        modelAndView.setViewName("profile.html");
        modelAndView.addObject("authUser", authUser);
        modelAndView.addObject("user", userProfile);
        modelAndView.addObject("authUserFriendRequests", friendRequests);
        modelAndView.addObject("friendRequest", profileFriendRequest);
        modelAndView.addObject("friends", userFriends);
        modelAndView.addObject("posts", userPosts);
        return modelAndView;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/changeInfo")
    public ModelAndView changeProfileInfo(Principal principal){
        User authUser = userService.getAuthUser(principal.getName());


    }
}
