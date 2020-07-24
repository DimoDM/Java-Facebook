package facebook.repository;

import facebook.entity.Picture;
import facebook.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture,Long> {

    Picture getFirstByImageURL(String url);
}
