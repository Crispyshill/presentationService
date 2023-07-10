package com.transcendenttopicals.presentationService;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  {
	
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
			.setType(EmbeddedDatabaseType.H2)
			.addScript("classpath:schema.sql")
			.build();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/").permitAll()
				.requestMatchers("/contact").permitAll()
				.requestMatchers("/about").permitAll()
				.requestMatchers("/addproduct").hasAnyAuthority("ADMIN")
				.requestMatchers("/products").permitAll()
				.requestMatchers("/css/**").permitAll()
				.requestMatchers("/h2/**").hasAnyAuthority("ADMIN")
				.requestMatchers("/h2-console/**").hasAnyAuthority("ADMIN")
				.requestMatchers("/account").hasAnyAuthority("USER", "ADMIN")
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll());

		return http.build();
	}
	

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService jdbcUserDetailsService(DataSource dataSource) {
		String usersByUsernameQuery = "select username, password, enabled from users where username = ?";
		String authsByUserQuery = "select username, authority from authorities where username = ?";

		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
		UserDetails user = User.withUsername("user")
			.password(encoder().encode("password"))
			.authorities("USER")
			.build();
		UserDetails admin = User.withUsername("admin")
			.password(encoder().encode("admin"))
			.authorities("ADMIN")
			.build();
		
	

		users.setUsersByUsernameQuery(usersByUsernameQuery);
		users.setAuthoritiesByUsernameQuery(authsByUserQuery);
		users.createUser(user);
		users.createUser(admin);

		return users;
	}
	
	
}
