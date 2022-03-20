package ir.man.spring.security.configuration;

import ir.man.spring.security.component.JwtRequestFilter;
import ir.man.spring.security.configuration.entry_point.WebJwtAuthenticationEntryPoint;
import ir.man.spring.security.handler.AppAccessDeniedHandler;
import ir.man.spring.security.handler.AppAuthenticationFailureHandler;
import ir.man.spring.security.handler.AppLogoutSuccessHandler;
import ir.man.spring.security.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
// By adding @EnableWebSecurity, we get Spring Security and MVC integration support
// The @EnableWebSecurity annotation is crucial if we disable the default security configuration (SecurityAutoConfiguration)
@EnableWebSecurity
@Profile("home")
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    //@Autowired
    //private WebBasicAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private AppAuthenticationProvider appAuthenticationProvider;
    @Autowired
    private WebJwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Autowired
    private AppUserDetailsService appUserDetailsService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("-1--AppSecurityConfiguration->configure(AuthenticationManagerBuilder auth)--");
//        auth.inMemoryAuthentication()
//                .withUser("user1").password(passwordEncoder().encode("pass1")).roles("USER").and()
//                .withUser("user2").password(passwordEncoder().encode("pass2")).roles("USER").and()
//                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").and()
//                .withUser("useradmin").password(passwordEncoder().encode("useradmin")).roles("USER", "ADMIN");
        auth.authenticationProvider(appAuthenticationProvider);
        // بهش می گوییم هنگام مقایسه جهت یافتن از بانک ابتدا از این طریق پسورد را مطابق با پسورد بانک انکود کن
        auth.userDetailsService(appUserDetailsService).passwordEncoder(passwordEncoder);
    }

    // Configuration to Authorize Requests
    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        System.out.println("-2--AppSecurityConfiguration->configure(final HttpSecurity httpSecurity)--");
        httpSecurity.csrf().disable() // ?
                .authorizeRequests()
                // 1:
                .antMatchers("/admin/**").hasRole("ADMIN")
                // 2: /login دسترسی به همگان روی
                // بنابراین تمامی نقش کاربران  مجوز دسترسی به لاگین و در نهایت اعتبارسنجی می شوند
                .antMatchers("/anonymous*").anonymous() // بدون نیاز به لاگین
                .antMatchers("/auth","/login*").permitAll()
                // 3: all other requests need to be authenticated
                .anyRequest().authenticated() // بقیه نیاز به لاگین
                // 4:
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/home.html", true)
                .failureUrl("/login.html?error=true")
                .failureHandler(new AppAuthenticationFailureHandler())
                // 5:
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(new AppLogoutSuccessHandler())
                .and()
                .exceptionHandling()
                //.accessDeniedPage("/access_denied") Or
                .accessDeniedHandler(new AppAccessDeniedHandler())
                //.and()
                //.httpBasic()
                //.authenticationEntryPoint(authenticationEntryPoint);
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterAfter(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    AppAuthenticationFailureHandler appAuthenticationFailureHandler() {
        return new AppAuthenticationFailureHandler();
    }

    @Bean
    AppLogoutSuccessHandler appLogoutSuccessHandler() {
        return new AppLogoutSuccessHandler();
    }

    @Bean
    AppAccessDeniedHandler appAccessDeniedHandler() {
        return new AppAccessDeniedHandler();
    }
}
