package facebook.controller;

import facebook.service.implementation.NewsFeedService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class NewsFeedController extends BaseController{


    private final NewsFeedService newsFeedService;

    public NewsFeedController(NewsFeedService newsFeedService) {
        this.newsFeedService = newsFeedService;
    }


    @GetMapping("/facebook")
    public ModelAndView newsFeed(Principal principal, ModelAndView modelAndView){

        modelAndView = newsFeedService.returnModelAndViewForNewsFeed(modelAndView, principal);
        return modelAndView;
    }

}
