package facebook.service.implementation;

import facebook.dto.RegisterDTO;
import facebook.entity.Role;
import facebook.entity.User;
import facebook.entity.UserLoginData;
import facebook.repository.UserLoginDataRepository;
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
public class UserServiceImpl implements UserService, UserDetailsService {


    private final UserLoginDataRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserLoginDataRepository userRepository, RoleService roleService, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(RegisterDTO registerDTO) {

        validRegister(registerDTO);

        UserLoginData newUser = new UserLoginData();
        User user = new User();


        newUser.setEmail(registerDTO.getEmail());
        newUser.setUsername(registerDTO.getUsername());
        newUser.setPhoneNumber(registerDTO.getPhone());
        newUser.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getUserRole());
        newUser.setRoles(roles);

        userRepository.save(newUser);
    }


/*
    @Override
    public void loginAuthentication(LoginDTO loginDTO) throws InvalidLoginException {

    }*/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserLoginData user = userRepository.findFirstByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found; with username: " + username));

        System.out.println(user.getUsername());
        return user;
    }

    public void validRegister(RegisterDTO registerDTO) {
        if (registerDTO.getPassword().equals(registerDTO.getPasswordTest()) && !registerDTO.getPassword().isEmpty()) {

            if (!registerDTO.getEmail().contains("@")) {

            }

            if (registerDTO.getUsername().isEmpty()) {

            }

            if (registerDTO.getPhone().isEmpty()) {

            }
        }
    }
}
