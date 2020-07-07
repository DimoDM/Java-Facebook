package facebook.service.contract;


import facebook.dto.LoginDTO;
import facebook.exception.InvalidLoginException;

public interface LoginService {
    void loginAuthentication(LoginDTO loginDTO) throws InvalidLoginException;
}
