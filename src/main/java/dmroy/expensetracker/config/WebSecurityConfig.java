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
                    .antMatchers(
                            "/login",
                            "/registration/**",
                            "/new-registration",
                            "/api/**",
                            "/image/**",
                            "/logout",
                            "/img/**",
                            "/css/**",
                            "/scss/**",
                            "/js/**",
                            "/vendor/**",
                            "/403"
                    ).permitAll()
                    .antMatchers("/index","/",
                            "/profile-view",
                            "/profile-edit",
                            "/profile-update",
                            "/profile-update",
                            "/expenses",
                            "/expense",
                            "/expense-new",
                            "/expense-add",
                            "/expense-edit",
                            "/expense-update"
                            )
                        .access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
                    .antMatchers("/custom-users",
                        "/custom-user",
                        "/custom-user-edit",
                        "/custom-user-add",
                        "/custom-user-update",
                        "/custom-user-new")
                        .access("hasAnyRole('ROLE_ADMIN')")
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
