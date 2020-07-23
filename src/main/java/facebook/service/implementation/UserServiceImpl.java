package facebook.service.implementation;

import facebook.dto.RegisterDTO;
import facebook.entity.Picture;
import facebook.entity.Role;
import facebook.entity.User;
import facebook.entity.UserLoginData;
import facebook.exception.UserNotFoundException;
import facebook.repository.PictureRepository;
import facebook.repository.UserLoginDataRepository;
import facebook.repository.UserRepository;
import facebook.service.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {


    private final UserLoginDataRepository userLoginDataRepository;
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final PictureRepository pictureRepository;


    @Autowired
    public UserServiceImpl(UserLoginDataRepository userLoginDataRepository, PictureRepository pictureRepository, UserRepository userRepository, RoleService roleService, BCryptPasswordEncoder passwordEncoder) {
        this.userLoginDataRepository = userLoginDataRepository;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.pictureRepository = pictureRepository;
    }

    @Transactional
    public void setUserToUserLoginData(UserLoginData userLoginData, User user) {
        user.setUserLoginData(userLoginData);
        userRepository.save(user);
    }

    @Override
    public void register(RegisterDTO registerDTO) {

        //validRegister(registerDTO);

        User user = new User();
        UserLoginData userLoginData = new UserLoginData();

        user.setFirstName(registerDTO.getFirstName());
        user.setDateOfBirth(registerDTO.getDateOfBirth());
        user.setSecondName(registerDTO.getLastName());
        user.setGender(registerDTO.getGender());
        Picture picture = new Picture();
        picture.setImageURL("src/main/resources/static/images/avatar.png");
        pictureRepository.save(picture);
        user.setProfilePicture(picture);
        userLoginData.setEmail(registerDTO.getEmail());
        userLoginData.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getUserRole());
        userLoginData.setRoles(roles);

        userRepository.save(user);
        userLoginData.setUser(user);
        userLoginDataRepository.save(userLoginData);
        setUserToUserLoginData(userLoginData, user);


    }

/*
        UserLoginData newUser = new UserLoginData();
        User user = new User();

    }


/*
    @Override
    public void loginAuthentication(LoginDTO loginDTO) throws InvalidLoginException {

    }*/

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        if (userLoginDataRepository.existsByEmail(email)) {

            UserLoginData user = userLoginDataRepository.findFirstByEmail(email);
            return user;
        } else {
            throw new IllegalArgumentException("User not found with email: " + email);
        }
    }

    public void validRegister(RegisterDTO registerDTO) {
        if (registerDTO.getPassword().equals(registerDTO.getRepeatPassword()) && !registerDTO.getPassword().isEmpty()) {

            if (!registerDTO.getEmail().contains("@")) {

            }

            if (registerDTO.getEmail().isEmpty()) {

            }
        }
    }

    @Override
    public User getAuthUser(String email) {
        if (userLoginDataRepository.existsByEmail(email)) {

            UserLoginData user = userLoginDataRepository.findFirstByEmail(email);
            return user.getUser();
        } else {
            throw new IllegalArgumentException("User not found with email: " + email);
        }

    }

}
