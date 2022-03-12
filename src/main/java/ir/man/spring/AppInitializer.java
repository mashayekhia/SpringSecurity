package ir.man.spring;

import ir.man.spring.security.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// --- default Security Configuration
// برنامه به صورت پیش فرض از این کلاس برای راه اندازی امنیت استفاده می کند
// که یا نام کاربری و کلمه عبور موجود در application.properties را استفاده می کند در صورت وجود
// وگرنه خودکار و رندومی در کنسول تولید می کند
//با غیرفعال کردن آن باید خودمان کانفیگ امنیتی را فراهم کنیم
// ---
// Or spring.autoconfigure.exclude = org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
// To use the above-defined Spring Security configuration, we need to attach it to the web application
public class AppInitializer {
    public static void main(String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(AppInitializer.class, args);
//        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
//        System.out.println(passwordEncoder.encode("123"));
    }
}
