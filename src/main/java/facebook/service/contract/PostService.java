package facebook.service.contract;

import com.dropbox.core.DbxException;
import facebook.dto.PostDTO;
import facebook.entity.User;
import facebook.exception.BlankPostException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface PostService {

    void createPost(PostDTO postDTO, User authUser) throws BlankPostException, IOException, DbxException;

}