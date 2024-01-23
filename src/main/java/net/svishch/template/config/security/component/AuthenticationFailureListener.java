package net.svishch.template.config.security.component;

import net.svishch.template.services.report.ReportingCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

// Регистрация ошибочных входов
@Component
public class AuthenticationFailureListener
        implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    @Autowired
    private ReportingCenterService reporting;

    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {

        WebAuthenticationDetails auth = (WebAuthenticationDetails)
                e.getAuthentication().getDetails();
        String user = e.getAuthentication().getName();
        String ip = auth.getRemoteAddress();

        sendLog(user, ip, e.getException());
    }

    private void sendLog(String user, String ip, AuthenticationException ex) {
        reporting.sendLogAuth(user,ip,ex);
    }
}