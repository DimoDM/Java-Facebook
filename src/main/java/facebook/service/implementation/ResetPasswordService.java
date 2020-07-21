package facebook.service.implementation;

import facebook.dto.ResetPasswordDTO;
import facebook.entity.PasswordResetToken;
import facebook.entity.User;
import facebook.entity.UserLoginData;
import facebook.repository.PasswordResetTokenRepository;
import facebook.repository.UserLoginDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ResetPasswordService {

    private final UserLoginDataRepository userLoginDataRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final EmailServiceImpl emailService;


    @Autowired
    public ResetPasswordService(UserLoginDataRepository userLoginDataRepository, EmailServiceImpl emailService, PasswordResetTokenRepository passwordResetTokenRepository) {
        this.userLoginDataRepository = userLoginDataRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.emailService = emailService;
    }


    public void changePassword(String email, String token, ResetPasswordDTO resetPasswordDTO) {


    }

    @Transactional
    public void saveNewPasswordInDatabase(String password, String email) {
        UserLoginData user = userLoginDataRepository.findFirstByEmail(email);
        user.setPassword(password);
        userLoginDataRepository.save(user);
    }

    public void createToken(String token, UserLoginData userLoginData){
        PasswordResetToken newPasswordResetToken = new PasswordResetToken();
        newPasswordResetToken.setToken(token);
        newPasswordResetToken.setUserLoginData(userLoginData);
        passwordResetTokenRepository.save(newPasswordResetToken);
    }

    @Transactional
    public void replaceToken(String token, UserLoginData userLoginData){
            PasswordResetToken passwordResetToken = passwordResetTokenRepository.findFirstByUserLoginData(userLoginData);
            passwordResetToken.setToken(token);
            passwordResetTokenRepository.save(passwordResetToken);
    }

    public String generateLink(String email, String token) {
        return "localhost:8080/newPassword?token=" + token + "&email=" + email;
    }

    public void sendMail(String email) {
        String token = java.util.UUID.randomUUID().toString().toUpperCase();
        saveToken(email, token);
        String link = generateLink(email, token);

        emailService.sendSimpleMessage(email, link);
    }

    public void saveToken(String email, String token) {

        if (userLoginDataRepository.existsByEmail(email)) {
            UserLoginData userLoginData = userLoginDataRepository.findFirstByEmail(email);
            if (passwordResetTokenRepository.existsByUserLoginData(userLoginData)) {
                replaceToken(token, userLoginData);
            } else{
                createToken(token, userLoginData);
            }
        }
    }
}
