package facebook.controller;

import facebook.dto.UserIdDTO;
import facebook.exception.FriendRequestNotFoundException;
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

    @PostMapping("/accept-request")
    public ModelAndView acceptFriendRequest(@ModelAttribute UserIdDTO userIdDTO, Principal principal) throws FriendRequestNotFoundException, UserByIdNotFoundException {
        //test
        friendRequestService.acceptFriendRequest(1L,userService.getAuthUser(principal.getName()));
        return redirect("/1");
    }

    @PostMapping("/deny-request")
    public ModelAndView declineFriendRequest(@ModelAttribute UserIdDTO userIdDTO, Principal principal) throws UserByIdNotFoundException {
        //test
        friendRequestService.declineFriendRequest(2L,userService.getAuthUser(principal.getName()));
        return redirect("/"+userIdDTO);
    }

    @PostMapping("/cancel-request")
    public ModelAndView cancelRequest(@ModelAttribute UserIdDTO userIdDTO, Principal principal) throws UserByIdNotFoundException {
        friendRequestService.cancelFriendRequest(userService.getAuthUser(principal.getName()),userIdDTO.getUserId());
        return redirect("/" + userIdDTO.getUserId());
    }

    @PostMapping("/sendFriendRequest")
    public ModelAndView sendFriendRequest(@ModelAttribute UserIdDTO userIdDTO, Principal principal) throws UserByIdNotFoundException {
        friendRequestService.sendFriendRequest(userService.getAuthUser(principal.getName()),userIdDTO.getUserId());
        return redirect("/"+userIdDTO.getUserId());
    }


}
