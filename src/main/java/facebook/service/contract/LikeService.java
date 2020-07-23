package facebook.service.contract;

import facebook.dto.LikeDTO;
import facebook.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface LikeService {

    void likePost(LikeDTO likeDTO, User authUser);

}
