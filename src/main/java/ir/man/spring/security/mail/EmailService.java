package ir.man.spring.security.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    public void sendMessageWithAttachment(String to, String subject, String text, String attachmentPath) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(message,true);
        messageHelper.setFrom(from);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(text);
        FileSystemResource attachment = new FileSystemResource(new File(attachmentPath));
        messageHelper.addAttachment(attachment.getFilename(), attachment);

        mailSender.send(message);
    }
}
