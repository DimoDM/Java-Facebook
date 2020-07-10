package facebook.controller;

import facebook.entity.PictureForPosts;
import facebook.entity.Post;
import facebook.entity.User;
import facebook.service.contract.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;


@Controller
public class FacebookProfileController extends BaseController{

    private final ProfileService profileService;


    @Autowired
    public FacebookProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{id}")
    public ModelAndView profile(@PathVariable("id") Long id, ModelAndView modelAndView){
        User userProfile = profileService.goToProfile(id);
        Set<Post> userPosts = profileService.getUserPosts(userProfile);
        modelAndView.setViewName("profile.html");
        modelAndView.addObject("user",userProfile);
        modelAndView.addObject("posts",userPosts);
        return modelAndView;
    }
}
