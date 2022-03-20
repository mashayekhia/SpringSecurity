package ir.man.spring.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.annotation.Resources;

@Configuration
@EnableWebMvc
@Profile("home")
//@ComponentScan(basePackages = {"ir.man.spring.security.controller"})
public class WebConfiguration implements WebMvcConfigurer {

    //@Autowired
    //private MessageSource messageSource;

//    @Override
//    public Validator getValidator() {
//        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
//        localValidatorFactoryBean.setValidationMessageSource(messageSource);
//        eturn localValidatorFactoryBean;
//    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        System.out.println("-3--WebConfiguration-G1->addViewControllers(ViewControllerRegistry registry)--");
        registry.addViewController("/admin/admin.html");
        registry.addViewController("/anonymous.html");
        registry.addViewController("/login.html");
        registry.addViewController("/access_denied.html");
        registry.addViewController("/logout_success.html");
        registry.addViewController("/badUser.html");
    }

    @Bean
    public ViewResolver userViewResolver() {
        System.out.println("-4--WebConfiguration-G1->viewResolver()--");
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/css/");
        registry
                .addResourceHandler("/webjars/**")
                .addResourceLocations("/webjars/");
    }
}
