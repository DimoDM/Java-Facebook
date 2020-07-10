package facebook.service.implementation;

import facebook.entity.PictureForPosts;
import facebook.entity.Post;
import facebook.entity.User;
import facebook.repository.PostRepository;
import facebook.repository.UserRepository;
import facebook.service.contract.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public ProfileServiceImpl(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public User goToProfile(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public Set<Post> getUserPosts(User user) {
        return postRepository.findAllByPoster(user);
    }

}
