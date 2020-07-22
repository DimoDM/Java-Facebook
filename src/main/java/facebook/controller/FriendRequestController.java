package facebook.controller;

import facebook.dto.UserIdDTO;
import facebook.exception.FriendRequestNotFoundException;
import facebook.exception.UserByEmailNotFoundException;
import facebook.exception.UserByIdNotFoundException;
import facebook.service.contract.FriendRequestService;
import facebook.service.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class FriendRequestController extends BaseController {
    private final FriendRequestService friendRequestService;
    private final UserService userService;

    @Autowired
    public FriendRequestController(FriendRequestService friendRequestService, UserService userService) {
        this.friendRequestService = friendRequestService;
        this.userService = userService;
    }

    @PostMapping("/acceptRequest")
    public ModelAndView acceptFriendRequest(@ModelAttribute UserIdDTO userIdDTO, Principal principal) throws FriendRequestNotFoundException, UserByIdNotFoundException, UserByEmailNotFoundException {
        friendRequestService.acceptFriendRequest(userIdDTO.getUserId(),userService.getAuthUser(principal.getName()));
        return redirect("/" +userIdDTO.getUserId());
    }

    @PostMapping("/denyRequest")
    public ModelAndView declineFriendRequest(@ModelAttribute UserIdDTO userIdDTO, Principal principal) throws UserByIdNotFoundException, UserByEmailNotFoundException {
        friendRequestService.declineFriendRequest(userIdDTO.getUserId(),userService.getAuthUser(principal.getName()));
        return redirect("/"+userIdDTO);
    }

    @PostMapping("/cancelRequest")
    public ModelAndView cancelRequest(@ModelAttribute UserIdDTO userIdDTO, Principal principal) throws UserByIdNotFoundException, UserByEmailNotFoundException {
        friendRequestService.cancelFriendRequest(userService.getAuthUser(principal.getName()),userIdDTO.getUserId());
        return redirect("/" + userIdDTO.getUserId());
    }

    @PostMapping("/sendFriendRequest")
    public ModelAndView sendFriendRequest(@ModelAttribute UserIdDTO userIdDTO, Principal principal) throws UserByIdNotFoundException, UserByEmailNotFoundException {
        friendRequestService.sendFriendRequest(userService.getAuthUser(principal.getName()),userIdDTO.getUserId());
        return redirect("/"+userIdDTO.getUserId());
    }

    @PostMapping("/removeFriend")
    public ModelAndView removeFriend(@ModelAttribute UserIdDTO userIdDTO, Principal principal) throws UserByIdNotFoundException, UserByEmailNotFoundException {
        friendRequestService.removeFriend(userService.getAuthUser(principal.getName()),userIdDTO.getUserId());
        return redirect("/"+userIdDTO.getUserId());
    }


}
