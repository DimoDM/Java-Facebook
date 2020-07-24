package facebook.service.contract;

import facebook.dto.LoginDTO;
import facebook.dto.RegisterDTO;
import facebook.entity.User;
import facebook.exception.InvalidLoginException;
import facebook.exception.UserByEmailNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    void register(RegisterDTO registerDTO);

    User getAuthUser(String username);

    UserDetails loadUserByUsername(String email);

    //void loginAuthentication(LoginDTO loginDTO) throws InvalidLoginException;
}
