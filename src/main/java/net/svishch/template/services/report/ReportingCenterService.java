package net.svishch.template.services.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import static net.svishch.template.services.report.LogName.*;

@Service
public class ReportingCenterService {

    Logger logAuth = LoggerFactory.getLogger(AUTH_LOG);
    Logger logConsole = LoggerFactory.getLogger(CONSOLE_APP_LOG);
    Logger logPages = LoggerFactory.getLogger(PAGES_LOG);
    Logger logErrors = LoggerFactory.getLogger(ERRORS_LOG);

    /** ERRORS **/
    private void sendLogError(Object object, Exception e) {
        logErrors.error(new LogInformer().getErrorMessage(object, e));
    }

    /** PAGES **/
    public void sendLogPages(User user, HttpServletRequest request) {
        logPages.info(new LogInformer().getPagesMessage(user, request));
    }

    /** CONSOLE **/
    public void sendLogConsole(String msg) {
        logConsole.info(msg);
    }

    /** AUTHENTICATION **/
    public void sendLogAuth(Authentication auth, User user) {
        String ip = ((WebAuthenticationDetails) auth.getDetails()).getRemoteAddress();
        String login = auth.getName();
        String userName = user.getUsername();
        String msg = String.format("ACCESS ALLOWED IP: %1$-15s LOGIN: %2$-30s USER: %3$-30s ", ip,login, user);
        logAuth.info(msg);
        //sendConsole(msg);
        println(msg);
    }

    private void println(String msg) {
        System.out.println(msg);
    }
}
