package facebook.controller;

import facebook.dto.UserIdDTO;
import facebook.entity.FriendRequest;
import facebook.entity.Post;
import facebook.entity.User;
import facebook.exception.FriendRequestNotFoundException;
import facebook.exception.UserByEmailNotFoundException;
import facebook.exception.UserByIdNotFoundException;
import facebook.repository.FriendRequestRepository;
import facebook.service.contract.FriendRequestService;
import facebook.service.contract.ProfileService;
import facebook.service.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Set;

@Controller
public class FriendRequestController extends BaseController {
    private final FriendRequestService friendRequestService;
    private final UserService userService;
    private final ProfileService profileService;
    private final FriendRequestRepository fReqRepo;

    @Autowired
    public FriendRequestController(FriendRequestService friendRequestService, FriendRequestRepository friendRequestRepository, ProfileService profileService, UserService userService) {
        this.friendRequestService = friendRequestService;
        this.userService = userService;
        this.profileService = profileService;
        this.fReqRepo = friendRequestRepository;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/friends")
    public ModelAndView friendsPage(ModelAndView modelAndView, Principal principal, UserIdDTO userIdDTO) throws UserByIdNotFoundException, UserByEmailNotFoundException {
        User userProfile = profileService.getProfile(userIdDTO.getUserId());
        System.out.println(userIdDTO.getUserId());
        User authUser = userService.getAuthUser(principal.getName());
        Set<Post> userPosts = profileService.getUserPosts(userProfile);
        Set<User> userFriends = userProfile.getUserFriends();
        FriendRequest profileFriendRequest = profileService.getFriendRequestWithId(userIdDTO.getUserId());
        Set<FriendRequest> friendRequests = fReqRepo.findAllByReceiverOrRequester(authUser, authUser);
        modelAndView.setViewName("RequestsAndFriendList.html");
        modelAndView.addObject("AuthUser", authUser);
        modelAndView.addObject("user", userProfile);
        modelAndView.addObject("authUserFriendRequests", friendRequests);
        modelAndView.addObject("friendRequest", profileFriendRequest);
        modelAndView.addObject("friends", userFriends);
        modelAndView.addObject("posts", userPosts);
        return modelAndView;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/acceptRequest")
    public ModelAndView acceptFriendRequest(@ModelAttribute UserIdDTO userIdDTO, Principal principal) throws FriendRequestNotFoundException, UserByIdNotFoundException, UserByEmailNotFoundException {
        friendRequestService.acceptFriendRequest(userIdDTO.getUserId(),userService.getAuthUser(principal.getName()));
        return send("RequestsAndFriendList");
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/denyRequest")
    public ModelAndView declineFriendRequest(@ModelAttribute UserIdDTO userIdDTO, Principal principal) throws UserByIdNotFoundException, UserByEmailNotFoundException {
        friendRequestService.declineFriendRequest(userIdDTO.getUserId(),userService.getAuthUser(principal.getName()));
        return send("RequestsAndFriendList");
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/cancelRequest")
    public ModelAndView cancelRequest(@ModelAttribute UserIdDTO userIdDTO, Principal principal) throws UserByIdNotFoundException, UserByEmailNotFoundException {
        friendRequestService.cancelFriendRequest(userService.getAuthUser(principal.getName()),userIdDTO.getUserId());
        return send("RequestsAndFriendList");
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/sendFriendRequest")
    public ModelAndView sendFriendRequest(@ModelAttribute UserIdDTO userIdDTO, Principal principal) throws UserByIdNotFoundException, UserByEmailNotFoundException {
        friendRequestService.sendFriendRequest(userService.getAuthUser(principal.getName()),userIdDTO.getUserId());
        return send("RequestsAndFriendList");
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/removeFriend")
    public ModelAndView removeFriend(@ModelAttribute UserIdDTO userIdDTO, Principal principal) throws UserByIdNotFoundException, UserByEmailNotFoundException {
        friendRequestService.removeFriend(userService.getAuthUser(principal.getName()),userIdDTO.getUserId());
        return send("RequestsAndFriendList");
    }


}
