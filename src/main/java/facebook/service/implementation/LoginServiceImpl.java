package facebook.service.implementation;

import facebook.dto.LoginDTO;
import facebook.entity.UserLoginData;
import facebook.exception.InvalidLoginException;
import facebook.repository.UserLoginDataRepository;
import facebook.service.contract.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    private final UserLoginDataRepository userLoginDataRepository;

    @Autowired
    public LoginServiceImpl(UserLoginDataRepository userLoginDataRepository) {
        this.userLoginDataRepository = userLoginDataRepository;
    }

    @Override
    public void loginAuthentication(LoginDTO loginDTO) throws InvalidLoginException {
        Optional<UserLoginData> user = userLoginDataRepository.getFirstByUsernameOrPhoneNumberOrEmail(loginDTO.getUsername(), loginDTO.getUsername(), loginDTO.getUsername());

        if (!user.isPresent()){
            throw new InvalidLoginException("There is no such user");
        } else if (!user.get().getPassword().equals(loginDTO.getPassword())){
            throw new InvalidLoginException("Invalid password");
        }
    }
}