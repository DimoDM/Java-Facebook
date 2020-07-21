package facebook.service.implementation;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

@Service
public class EmailServiceImpl {
    public void sendCustomEmail(String emailSubject, String emailReceiver, String message) throws IOException {
        Email from = new Email("noreplyjnoreplyj98@gmail.com");
        String subject = emailSubject;
        Email to = new Email(emailReceiver);
        Content content = new Content("text/plain", message);
        Mail mail = new Mail(from, subject, to, content);
        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }

}
