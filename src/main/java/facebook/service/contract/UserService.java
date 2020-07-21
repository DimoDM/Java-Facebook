package facebook.service.contract;

import facebook.dto.LoginDTO;
import facebook.dto.RegisterDTO;
import facebook.entity.User;
import facebook.exception.InvalidLoginException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    void register(RegisterDTO registerDTO);

    User getAuthUser(String username);

    //void loginAuthentication(LoginDTO loginDTO) throws InvalidLoginException;
}
