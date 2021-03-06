package facebook.config;

import facebook.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.security.Principal;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserServiceImpl userService;

    @Autowired
    public WebSecurityConfiguration(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                    .authorizeRequests()
                    .antMatchers("/", "/register").permitAll()
//                    .anyRequest().authenticated()
                .and()
                    .formLogin().loginPage("/login").permitAll()
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/1", true)
                .and()
                    .logout().logoutSuccessUrl("/login").permitAll()
                .and()
                    .exceptionHandling().accessDeniedPage("/error/unauthorized")
                .and()
                    .csrf().disable()
                    .rememberMe()
                    .rememberMeParameter("remember")
                    .key("a31239e9-afbd-4a0f-9bac-43f532c12f55")
                    .userDetailsService(userService)
                    .rememberMeCookieName("rememberMe")
                    .tokenValiditySeconds(10000);
    }

}
