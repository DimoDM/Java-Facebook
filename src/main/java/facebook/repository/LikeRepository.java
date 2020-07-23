package facebook.repository;

import facebook.entity.Like;
import facebook.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
    boolean existsByPost(Post post);

    Like findFirstByPost(Post post);
}
