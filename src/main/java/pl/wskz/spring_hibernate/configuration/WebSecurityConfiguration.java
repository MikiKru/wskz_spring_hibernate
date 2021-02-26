package pl.wskz.spring_hibernate.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;


@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/posts&**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/post").hasAnyAuthority("ROLE_USER")
                .anyRequest().permitAll()
                .and()
                    .csrf().disable()
                .formLogin()
                    .loginPage("/login")
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .loginProcessingUrl("/login_process")
                    .defaultSuccessUrl("/")
                    .failureForwardUrl("/login?error=true")
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/");
    }
    @Autowired
    private DataSource dataSource;
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .usersByUsernameQuery(
                        "SELECT u.email, u.password, u.status FROM users u WHERE u.email =?"
                )
                .authoritiesByUsernameQuery(
                        "SELECT u.email, p.name FROM users u JOIN users_to_roles ur " +
                                "ON (u.user_id = ur.user_id) JOIN permissions p " +
                                "ON (p.permission_id = ur.permission_id) WHERE u.email =?"
                )
                .dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
