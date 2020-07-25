package facebook.controller;

import facebook.entity.Post;
import facebook.entity.User;
import facebook.entity.UserFriends;
import facebook.repository.PostRepository;
import facebook.repository.UserFriendsRepository;
import facebook.repository.UserRepository;
import facebook.service.implementation.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class NewsfeedController extends BaseController{

    private final UserServiceImpl userService;
    private final UserFriendsRepository userFriendsRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public NewsfeedController(UserServiceImpl userService, UserFriendsRepository userFriendsRepository, PostRepository postRepository, UserRepository userRepository) {
        this.userService = userService;
        this.userFriendsRepository = userFriendsRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/facebook")
    public ModelAndView newsfeed(Principal principal, ModelAndView modelAndView){

        User authUser = userService.getAuthUser(principal.getName());

        List<UserFriends> userFriends = userFriendsRepository.findAllByUser(authUser);

        List<Post> posts = new ArrayList<>();

        for (UserFriends userFriend : userFriends){
            posts.addAll(postRepository.findAllByPoster(userRepository.findById(userFriend.getFriendId()).get()));
        }


        for (int i = 0; i < posts.size(); i++) {
            for (int j = 0; j < i; j++) {
                if(posts.get(i).getId() > posts.get(j).getId()) {
                    Collections.swap(posts, i, j);
                }
            }
        }


        modelAndView.setViewName("facebook.html");
        modelAndView.addObject("authUser", authUser);
        modelAndView.addObject("posts", posts);

        return modelAndView;
    }

}
