package facebook.controller;

import facebook.dto.LikeDTO;
import facebook.exception.UserByEmailNotFoundException;
import facebook.service.contract.LikeService;
import facebook.service.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class LikeController extends BaseController {

    private final LikeService likeService;
    private final UserService userService;

    @Autowired
    public LikeController(LikeService likeService, UserService userService) {
        this.likeService = likeService;
        this.userService = userService;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("likePost")
    public ModelAndView likePost(@ModelAttribute LikeDTO likeDTO, Principal principal) throws UserByEmailNotFoundException {
        likeService.likePost(likeDTO,userService.getAuthUser(principal.getName()));
        return redirect("/");
    }


}
