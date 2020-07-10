package facebook.service.contract;

import facebook.entity.PictureForPosts;
import facebook.entity.Post;
import facebook.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface ProfileService {

    User goToProfile(Long id);

    Set<Post> getUserPosts(User user);

}
