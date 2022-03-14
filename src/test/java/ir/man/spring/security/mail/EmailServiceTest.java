package ir.man.spring.security.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;

    // Passed
    @Test
    public void sendSimpleMessage() {
        emailService.sendSimpleMessage("aghil.mashayekhi@gmail.com","aghil subject","aghil text");
    }

    // Passed
    @Test
    public void sendMessageWithAttachment() throws MessagingException {
        emailService.sendMessageWithAttachment("aghil.mashayekhiabccccccc@gmail.com", "aghil subject", "aghil text", "F:\\programming\\New Text Document (3).txt");
    }
}