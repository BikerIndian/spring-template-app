package net.svishch.template.db;

import net.svishch.template.config.security.RoleListApp;
import net.svishch.template.config.security.component.AuthenticationDB;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AuthDB implements AuthenticationDB {

    Map<String,String> users = new HashMap<>();

    public AuthDB() {
        users.put("admin","111");
    }

    @Override
    public UserDetails getUser(String login, String password) {
        UserDetails user = null;

        if (users.containsKey(login) && users.get(login).equals(password)) {
            String[] role={RoleListApp.R_USER};
            user = User
                    .withUsername(login)
                    .password(passwordEncoder().encode(password))
                    .roles(role)
                    .build();
        }


        return user;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
