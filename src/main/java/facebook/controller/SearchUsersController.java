package facebook.controller;

import facebook.dto.UserSearchDTO;
import facebook.entity.User;
import facebook.service.implementation.UserSearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SearchUsersController extends BaseController {

    private final UserSearchServiceImpl userSearchService;

    @Autowired
    public SearchUsersController(UserSearchServiceImpl userSearchService) {
        this.userSearchService = userSearchService;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/searchUsers")
    public ModelAndView searchUsers(@ModelAttribute UserSearchDTO userSearchDTO, ModelAndView modelAndView) {


        if (userSearchDTO.getName().contains(" ")) {
            List<User> usersFoundByBothNames = userSearchService.findByTwoNames(userSearchDTO);
            List<User> usersFoundByFirstName = userSearchService.findByFirstName(userSearchDTO);
            List<User> usersFoundBySecondName = userSearchService.findBySecondName(userSearchDTO);
            List<User> usersFoundByAnyOfNames = null;


            modelAndView.setViewName("searchTest.html");
            modelAndView.addObject("fullMatchUsers", usersFoundByBothNames);
            modelAndView.addObject("firstNameMatchUsers", usersFoundByFirstName);
            modelAndView.addObject("secondNameMatchUsers", usersFoundBySecondName);
            modelAndView.addObject("matchByAnyOfNames", usersFoundByAnyOfNames);
        } else{
            List<User> usersFoundByBothNames = null;
            List<User> usersFoundByFirstName = null;
            List<User> usersFoundBySecondName = null;
            List<User> usersFoundByAnyOfNames = userSearchService.findByAnyOfNames(userSearchDTO);

            modelAndView.setViewName("searchTest.html");
            modelAndView.addObject("fullMatchUsers", usersFoundByBothNames);
            modelAndView.addObject("firstNameMatchUsers", usersFoundByFirstName);
            modelAndView.addObject("secondNameMatchUsers", usersFoundBySecondName);
            modelAndView.addObject("matchByAnyOfNames", usersFoundByAnyOfNames);
        }


        return modelAndView;
    }

}



