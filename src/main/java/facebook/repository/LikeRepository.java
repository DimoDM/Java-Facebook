package facebook.repository;

import facebook.entity.Comment;
import facebook.entity.Like;
import facebook.entity.Post;
import facebook.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
    boolean existsByPost(Post post);

    boolean existsByComment(Comment comment);

    Like findFirstByPostAndLiker(Post post, User authUser);

    Like findFirstByCommentAndLiker(Comment comment, User authUser);
}
