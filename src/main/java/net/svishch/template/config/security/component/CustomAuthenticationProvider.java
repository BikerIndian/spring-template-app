package net.svishch.template.config.security.component;


import net.svishch.template.config.security.RoleListApp;
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
public class CustomAuthenticationProvider implements AuthenticationProvider {

   // @Autowired
   // AuthenticationManager authenticationManager;
/*
    @Autowired
    private LdapTemplate ldapTemplate;

    @Autowired
    private BmjErpService apiLk;
 */

    @Autowired
    private ReportingCenterService report;


    @Autowired
    AuthenticationDB authDB;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        UserDetails user = getApiUser(authentication);

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

    private UserDetails getApiUser(Authentication authentication) {
        UserDetails user = null;

        String login = authentication.getName();
        String password = authentication.getCredentials().toString();

        try {
            user = authDB.getUser(login,password);
        }catch (Exception e){
            report.sendLogError(this,e);
        }

        return user;
    }

}
