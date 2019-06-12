package mail;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

@Stateless
public class EmailSender implements Serializable {

    @Resource(name="JavaMailKwetter")
    Session mailSession;

    public void sendMail(String subject, String body, String recipient, String name) throws MessagingException, UnsupportedEncodingException {
        Message message = new MimeMessage(mailSession);
        message.setSubject(subject);
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient,name));
        message.setText(body);
        Transport.send(message);
    }
}
