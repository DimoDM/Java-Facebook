package facebook.service.contract;

import facebook.entity.FriendRequest;
import facebook.entity.Post;
import facebook.entity.User;
import facebook.exception.UserByIdNotFoundException;
import facebook.repository.FriendRequestRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Set;

@Service
public interface ProfileService{

    User getProfile(Long id) throws UserByIdNotFoundException;

    Set<Post> getUserPosts(User user);

    FriendRequest getFriendRequestWithId(Long requesterId) throws UserByIdNotFoundException;

    public ModelAndView returnModelAndViewForProfile(Long id, Principal principal, ModelAndView modelAndView) throws UserByIdNotFoundException;
}
