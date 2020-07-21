package facebook.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl {

    @Qualifier("getJavaMailSender")
    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(
            String to, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreplyjnoreplyj98@gmail.com");
        message.setTo(to);
        message.setSubject("Facebook(notPhP) Change your password!");
        message.setText(text);
        emailSender.send(message);
    }


}
