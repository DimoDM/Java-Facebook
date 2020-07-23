package facebook.service.contract;

import facebook.dto.CommentDTO;
import facebook.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    void createComment(CommentDTO commentDTO, User authUser);
}
