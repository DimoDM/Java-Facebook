package facebook.service.implementation;

import facebook.dto.LoginDTO;
import facebook.dto.RegisterDTO;
import facebook.entity.User;
import facebook.entity.UserLoginData;
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

        if(registerDTO.getPassword().equals(registerDTO.getPasswordTest()) && !registerDTO.getPassword().isEmpty()) {

            if(!registerDTO.getEmail().contains("@")) {

            }

            if(registerDTO.getUsername().isEmpty()) {

            }

            if(registerDTO.getPhone().isEmpty()) {

            }

            UserLoginData newUser = new UserLoginData();
            User user = new User();



            newUser.setEmail(registerDTO.getEmail());
            newUser.setUsername(registerDTO.getUsername());
            newUser.setPhoneNumber(registerDTO.getPhone());
            newUser.setPassword(registerDTO.getPassword());

            userRepository.save(newUser);
        }

    }

    @Override
    public void loginAuthentication(LoginDTO loginDTO) throws InvalidLoginException {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserLoginData user = userRepository.findFirstByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found; with username: " + username));

        return user;
    }
}
