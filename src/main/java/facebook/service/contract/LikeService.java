package facebook.service.contract;

import facebook.dto.LikePostDTO;
import facebook.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface LikeService {

    void likePost(LikePostDTO likeDTO, User authUser);

}
