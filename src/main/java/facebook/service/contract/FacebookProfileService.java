package facebook.service.contract;

import facebook.dto.ChangeInfoDTO;
import facebook.entity.FriendRequest;
import facebook.entity.Post;
import facebook.entity.User;
import facebook.exception.DateNotValidException;
import facebook.exception.UserByIdNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface FacebookProfileService {

    User getProfile(Long id) throws UserByIdNotFoundException;

    Set<Post> getUserPosts(User user);

    FriendRequest getFriendRequestWithId(Long requesterId) throws UserByIdNotFoundException;

    void changeUserInfo(User user, ChangeInfoDTO changeInfoDTO) throws  DateNotValidException;


}
