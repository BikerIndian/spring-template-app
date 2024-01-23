package net.svishch.template.config.security.component;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationDB {
    UserDetails getUser(String login, String password);
}
