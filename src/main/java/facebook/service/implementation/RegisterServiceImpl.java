package facebook.service.implementation;

import facebook.dto.*;
import facebook.entity.*;
import facebook.repository.UserLoginDataRepository;
import facebook.service.contract.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final UserLoginDataRepository userRepository;

    @Autowired
    public RegisterServiceImpl(UserLoginDataRepository userRepository) { this.userRepository = userRepository; }

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
            newUser.setEmail(registerDTO.getEmail());
            newUser.setUsername(registerDTO.getUsername());
            newUser.setPhoneNumber(registerDTO.getPhone());
            newUser.setPassword(registerDTO.getPassword());

            userRepository.save(newUser);
        }

    }
}
