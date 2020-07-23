package facebook.controller;

import facebook.dto.CommentDTO;
import facebook.exception.UserByEmailNotFoundException;
import facebook.service.contract.CommentService;
import facebook.service.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class CommentController extends BaseController {

    private final CommentService commentService;
    private final UserService userService;

    @Autowired
    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/createComment")
    public ModelAndView createComment(@ModelAttribute CommentDTO commentDTO, Principal principal) throws UserByEmailNotFoundException {
        commentService.createComment(commentDTO, userService.getAuthUser(principal.getName()));
        return redirect("/");
    }

}
