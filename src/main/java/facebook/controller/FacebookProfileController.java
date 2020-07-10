package facebook.controller;

import facebook.entity.User;
import facebook.service.contract.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class FacebookProfileController extends BaseController{

    private final ProfileService profileService;

    @Autowired
    public FacebookProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{id}")
    public ModelAndView profile(@PathVariable("id") Long id){
        User userProfile = profileService.goToProfile(id);
        return send("profile","user",userProfile);
    }


}
