package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final DataSource dataSource;
    
    // Inject the data source using the @Autowired and @Qualifier annotations
    @Autowired
    public CustomUserDetailsService(@Qualifier("productDataSource") DataSource secondDataSource) {
        this.dataSource = secondDataSource;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String query = "SELECT username, password, enabled, authorities FROM users WHERE username = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String fetchedUsername = resultSet.getString("username");
                String fetchedPassword = resultSet.getString("password");
                boolean enabled = resultSet.getBoolean("enabled");
                String authoritiesString = resultSet.getString("authorities");

                // Convert the authorities string to a list of roles
                List<String> authorities = new ArrayList<>();
                if (authoritiesString != null) {
                    String[] roles = authoritiesString.split(","); // Assuming comma-separated authorities
                    for (String role : roles) {
                        authorities.add(role.trim());
                    }
                }

                // Create and return the UserDetails object
                return User.withUsername(fetchedUsername)
                        .password(fetchedPassword)
                        .roles(authorities.toArray(new String[0]))
                        .disabled(!enabled)
                        .build();
            } else {
               
                throw new UsernameNotFoundException("User not found with username: " + username);
            }
        } catch (SQLException e) {
            // Handle any potential exceptions
            throw new RuntimeException("Error retrieving user details from the database", e);
        }
    }
    

}
