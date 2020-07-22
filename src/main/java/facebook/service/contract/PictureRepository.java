package facebook.service.contract;

import facebook.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture,Long> {



}
