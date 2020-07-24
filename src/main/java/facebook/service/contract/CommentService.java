package facebook.service.contract;

import com.dropbox.core.DbxException;
import facebook.dto.CommentDTO;
import facebook.entity.User;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface CommentService {
    void createComment(CommentDTO commentDTO, User authUser) throws IOException, DbxException;
}
