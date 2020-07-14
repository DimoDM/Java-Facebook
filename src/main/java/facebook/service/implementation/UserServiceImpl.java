package facebook.service.implementation;

import facebook.dto.LoginDTO;
import facebook.dto.RegisterDTO;
import facebook.exception.InvalidLoginException;
import facebook.repository.UserLoginDataRepository;
import facebook.service.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {


    private final UserLoginDataRepository userRepository;

    @Autowired
    public UserServiceImpl(UserLoginDataRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(RegisterDTO registerDTO) {

    }

    @Override
    public void loginAuthentication(LoginDTO loginDTO) throws InvalidLoginException {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
