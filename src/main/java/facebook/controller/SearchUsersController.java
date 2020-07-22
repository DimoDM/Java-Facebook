package facebook.controller;

import facebook.dto.UserSearchDTO;
import facebook.entity.User;
import facebook.service.implementation.UserSearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SearchUsersController extends BaseController{

    private final UserSearchServiceImpl userSearchService;

     @Autowired
    public SearchUsersController(UserSearchServiceImpl userSearchService) {
        this.userSearchService = userSearchService;
    }

    @PostMapping("/searchUsers")
    public ModelAndView searchUsers(@ModelAttribute UserSearchDTO userSearchDTO, ModelAndView modelAndView){

        List<User> usersFoundByBothNames = userSearchService.findByTwoNames(userSearchDTO);
        List<User> usersFoundByFirstName = userSearchService.findByFirstName(userSearchDTO);
        List<User> usersFoundBySecondName = userSearchService.findBySecondName(userSearchDTO);

        modelAndView.setViewName("searchTest.html");
        modelAndView.addObject("fullMatchUsers", usersFoundByBothNames);
        modelAndView.addObject("firstNameMatchUsers", usersFoundByFirstName);
        modelAndView.addObject("secondNameMatchUsers", usersFoundBySecondName);

        return modelAndView;
    }

}
