package facebook.controller;

import facebook.exception.FriendRequestNotFoundException;
import facebook.service.contract.FriendRequestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FriendRequestController extends BaseController {
    private final FriendRequestService friendRequestService;

    public FriendRequestController(FriendRequestService friendRequestService) {
        this.friendRequestService = friendRequestService;
    }

    @PostMapping("/accept-request")
    public ModelAndView acceptFriendRequest() throws FriendRequestNotFoundException {
        //test
        friendRequestService.acceptFriendRequest(1L,2L);
        return redirect("/1");
    }

    @GetMapping("/friendReq")
    public ModelAndView fr(){
        return send("fr-req");
    }

}
