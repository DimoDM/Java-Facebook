package facebook.repository;

import facebook.entity.Post;
import facebook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    Set<Post> findAllByPoster(User user);

    Post findFirstById(Long id);


}
