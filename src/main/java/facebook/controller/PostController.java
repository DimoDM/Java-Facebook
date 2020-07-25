package facebook.controller;

import facebook.dto.PostDTO;
import facebook.entity.User;
import facebook.exception.BlankPostException;
import facebook.exception.UserByEmailNotFoundException;
import facebook.service.contract.PostService;
import facebook.service.contract.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.Principal;

@Controller
public class PostController extends BaseController{

    private final PostService postService;
    private final UserService userService;

    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PostMapping("/createPost")
    public ModelAndView createPost(@ModelAttribute PostDTO postDTO, Principal principal) throws BlankPostException, UserByEmailNotFoundException, IOException {
        User authUser = userService.getAuthUser(principal.getName());
        postService.createPost(postDTO,authUser);
        return redirect("/" + authUser.getId());
    }

}
