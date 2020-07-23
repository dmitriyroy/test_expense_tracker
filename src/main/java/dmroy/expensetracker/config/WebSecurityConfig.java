package dmroy.expensetracker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import dmroy.expensetracker.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/login",
                            "/new-registration",
                            "/registration/**",
                            "/wait-verification-mail/**",
                            "/send-verification-mail/**",
                            "/verification-mail/**",
                            "/forgot-password",
                            "/reset-password",
                            "/api/**",
                            "/image/**",
                            "/reset-password-form",
                            "/logout",
                            "/css/**",
                            "/scss/**",
                            "/js/**",
                            "/vendor/**",
                            "/403",
                            "/404").permitAll()
                    // прописываем все урлы, куда есть доступ верифицированным пользователям
                    // т.е. (прошедшим email-валидацию)
                    .antMatchers("/index","/")
                        .access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
//                    .antMatchers("/workplace/**",
//                        "/test/**",
//                        "/profile/**",
//                        "/workplace/**")
//                        .access("hasAnyRole('ROLE_ADMIN')")
                    .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login").permitAll()
                .and()
                    .exceptionHandling().accessDeniedPage("/403")
                .and()
                    .logout()
                    .logoutSuccessUrl("/login").permitAll()
                    .invalidateHttpSession(true);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

}
