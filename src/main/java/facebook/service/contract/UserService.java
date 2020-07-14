package facebook.service.contract;

import facebook.dto.LoginDTO;
import facebook.dto.RegisterDTO;
import facebook.exception.InvalidLoginException;

public interface UserService {

    void register(RegisterDTO registerDTO);

    void loginAuthentication(LoginDTO loginDTO) throws InvalidLoginException;
}
