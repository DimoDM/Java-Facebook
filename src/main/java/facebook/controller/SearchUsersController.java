package facebook.controller;

import facebook.dto.UserSearchDTO;
import facebook.entity.User;
import facebook.service.implementation.UserSearchServiceImpl;
import facebook.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class SearchUsersController extends BaseController {

    private final UserSearchServiceImpl userSearchService;
    private final UserServiceImpl userService;

    @Autowired
    public SearchUsersController(UserSearchServiceImpl userSearchService, UserServiceImpl userService) {
        this.userSearchService = userSearchService;
        this.userService = userService;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/searchUsers")
    public ModelAndView searchUsers(@ModelAttribute UserSearchDTO userSearchDTO, ModelAndView modelAndView, Principal principal) {



        User authUser = userService.getAuthUser(principal.getName());
        modelAndView.addObject("authUser", authUser);
        modelAndView.setViewName("searchTest.html");

        if (userSearchDTO.getName().contains(" ")) {
            return userSearchService.setModelAndViewForTwoNames(modelAndView, userSearchDTO);
        } else{
            return userSearchService.setModelAndViewForOneName(modelAndView, userSearchDTO);
        }
    }

}



