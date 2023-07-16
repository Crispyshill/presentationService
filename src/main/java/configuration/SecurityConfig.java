package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeRequests()
                .requestMatchers("/css/**", "/js/**").permitAll() // Allow access to CSS and JS resources
                .requestMatchers("/").permitAll() // Allow access to the home page for everyone
                .requestMatchers("/login").permitAll() // Allow access to the login page for everyone
                .requestMatchers("/account").hasAnyRole("USER", "ADMIN") // Allow access to the account page only for authenticated users
                .anyRequest().authenticated() // Require authentication for any other request
                .and()
                .formLogin().loginPage("/login") // Set the custom login page URL
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
