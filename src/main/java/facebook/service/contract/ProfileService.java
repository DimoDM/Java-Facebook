package facebook.service.contract;

import facebook.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface ProfileService {

    User goToProfile(Long id);


}
