package security.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1").password(passwordEncoder().encode("password")).roles("USER")
                .and()
                .withUser("user2").password(passwordEncoder().encode("password")).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Note that the order of the antMatchers() elements is significant – the more specific rules need to come first, followed by the more general ones.
        http
        .csrf()
        .disable()
        .authorizeRequests()
                .antMatchers("/","/h2-console/**").permitAll()
                .antMatchers(HttpMethod.GET, "/person/*").permitAll()
                .antMatchers(HttpMethod.GET, "/person/list-all").permitAll()
                .anyRequest().authenticated().and().httpBasic();

        http.headers().frameOptions().disable(); //For h2 console to work
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
