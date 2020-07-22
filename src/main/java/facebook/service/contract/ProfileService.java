package facebook.service.contract;

import facebook.entity.FriendRequest;
import facebook.entity.Post;
import facebook.entity.User;
import facebook.exception.UserByIdNotFoundException;
import facebook.repository.FriendRequestRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface ProfileService{

    User getProfile(Long id) throws UserByIdNotFoundException;

    Set<Post> getUserPosts(User user);

    FriendRequest getFriendRequestWithId(Long requesterId) throws UserByIdNotFoundException;
}
