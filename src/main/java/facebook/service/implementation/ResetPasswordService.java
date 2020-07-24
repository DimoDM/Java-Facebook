package facebook.service.implementation;

import facebook.dto.ResetPasswordDTO;
import facebook.entity.PasswordResetToken;
import facebook.entity.User;
import facebook.entity.UserLoginData;
import facebook.repository.PasswordResetTokenRepository;
import facebook.repository.UserLoginDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ResetPasswordService {

    private final UserLoginDataRepository userLoginDataRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final EmailServiceImpl emailService;
    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public ResetPasswordService(UserLoginDataRepository userLoginDataRepository, BCryptPasswordEncoder passwordEncoder, EmailServiceImpl emailService, PasswordResetTokenRepository passwordResetTokenRepository) {
        this.userLoginDataRepository = userLoginDataRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    public void sendMail(String email) {
        String token = java.util.UUID.randomUUID().toString().toUpperCase();
        saveToken(email, token);
        String link = generateLink(email, token);

        emailService.sendSimpleMessage(email, link);
    }

    private void createToken(String token, UserLoginData userLoginData) {
        PasswordResetToken newPasswordResetToken = new PasswordResetToken();
        newPasswordResetToken.setToken(token);
        newPasswordResetToken.setUserLoginData(userLoginData);
        passwordResetTokenRepository.save(newPasswordResetToken);
    }

    @Transactional
    public void replaceToken(String token, UserLoginData userLoginData) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findFirstByUserLoginData(userLoginData);
        passwordResetToken.setToken(token);
        passwordResetTokenRepository.save(passwordResetToken);
    }

    private String generateLink(String email, String token) {
        return "localhost:8080/changePassword?token=" + token + "&email=" + email;
    }

    private void saveToken(String email, String token) {

        if (userLoginDataRepository.existsByEmail(email)) {
            UserLoginData userLoginData = userLoginDataRepository.findFirstByEmail(email);
            if (passwordResetTokenRepository.existsByUserLoginData(userLoginData)) {
                replaceToken(token, userLoginData);
            } else {
                createToken(token, userLoginData);
            }
        }
    }

    @Transactional
    public void saveNewPasswordInDatabase(ResetPasswordDTO resetPasswordDTO, String email) {
        System.out.println(resetPasswordDTO.getPassword() + "  " + email);
        checkIfPasswordsAreSame(resetPasswordDTO);
        UserLoginData user = userLoginDataRepository.findFirstByEmail(email);
        user.setPassword(passwordEncoder.encode(resetPasswordDTO.getPassword()));
        userLoginDataRepository.save(user);
    }

    public boolean checkIfLinkIsValid(String token, String email) {
        if (userLoginDataRepository.existsByEmail(email)) {
            if (passwordResetTokenRepository.existsByUserLoginData(userLoginDataRepository.findFirstByEmail(email))) {
                if (passwordResetTokenRepository.findFirstByUserLoginData(userLoginDataRepository.findFirstByEmail(email)).getToken().equals(token)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void checkIfPasswordsAreSame(ResetPasswordDTO resetPasswordDTO){
        if (!resetPasswordDTO.getPassword().equals(resetPasswordDTO.getPasswordRepeat())){
            //throw exception
        }
    }
}