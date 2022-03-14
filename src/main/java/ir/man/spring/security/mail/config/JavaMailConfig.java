package ir.man.spring.security.mail.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class JavaMailConfig {

    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;

    @Bean
    public SimpleMailMessage getSimpleMailMessage() {
        SimpleMailMessage templateMessage = new SimpleMailMessage();
        templateMessage.setSubject("NoSubject");
        templateMessage.setText("NoText");
        return templateMessage;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties mailProps = mailSender.getJavaMailProperties();
        mailProps.put("mail.transport.protocol", "smtp");
        mailProps.put("mail.smtp.auth", true);
        mailProps.put("mail.smtp.starttls.enable", "true");
        mailProps.put("mail.debug", "true");

        return mailSender;
    }


}
