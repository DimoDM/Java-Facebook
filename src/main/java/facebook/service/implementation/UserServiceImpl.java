package facebook.service.implementation;

import facebook.dto.RegisterDTO;
import facebook.entity.Role;
import facebook.entity.User;
import facebook.entity.UserLoginData;
import facebook.exception.UserNotFoundException;
import facebook.repository.UserLoginDataRepository;
import facebook.repository.UserRepository;
import facebook.service.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {


    private final UserLoginDataRepository userLoginDataRepository;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserLoginDataRepository userRepository, UserRepository userRepository1, RoleService roleService, BCryptPasswordEncoder passwordEncoder) {
        this.userLoginDataRepository = userRepository;
        this.userRepository = userRepository1;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
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

            UserLoginData newUserData = new UserLoginData();
            User user = new User();



            newUserData.setEmail(registerDTO.getEmail());
            newUserData.setUsername(registerDTO.getUsername());
            newUserData.setPhoneNumber(registerDTO.getPhone());
            newUserData.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
            user.setFirstName(registerDTO.getFirstName());
            user.setSecondName(registerDTO.getLastName());
            userRepository.save(user);

            Set<Role> roles = new HashSet<>();
            roles.add(roleService.getUserRole());
            newUserData.setRoles(roles);
            newUserData.setUser(user);
            userLoginDataRepository.save(newUserData);
        }

    }
/*
    @Override
    public void loginAuthentication(LoginDTO loginDTO) throws InvalidLoginException {

    }*/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserLoginData user = userLoginDataRepository.findFirstByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found; with username: " + username));
        return user;
    }

    @Override
    public User getAuthUser(String username){
        UserLoginData userLoginData = userLoginDataRepository.findFirstByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return userLoginData.getUser();
    }

}
