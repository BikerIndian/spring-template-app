package net.svishch.template.config.security.component;

import lombok.RequiredArgsConstructor;
import net.svishch.template.services.report.ReportingCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final ReportingCenterService report;
    private final AuthenticationDB dbUserService;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        UserDetails user = getUser(authentication, dbUserService);

        if (user == null) {
            String mes = String.format("User %s not found: ", authentication.getName());
            throw new BadCredentialsException(mes);
        }

        return new UsernamePasswordAuthenticationToken(
                user, user.getPassword(), user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private UserDetails getUser(Authentication authentication, AuthenticationDB dataBase) {
        UserDetails user = null;

        String login = authentication.getName();
        String password = authentication.getCredentials().toString();

        try {
            user = dataBase.getUser(login, password);
        } catch (Exception e) {
            report.sendLogError(this, e);
        }

        return user;
    }

}
