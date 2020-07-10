package facebook.repository;

import facebook.entity.Post;
import facebook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PostRepository extends JpaRepository<Post,Long> {

    Set<Post> findAllByPoster(User user);

}
