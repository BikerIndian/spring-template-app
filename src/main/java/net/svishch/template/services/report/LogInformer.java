package net.svishch.template.services.report;

import org.springframework.security.core.userdetails.User;
import javax.servlet.http.HttpServletRequest;

public class LogInformer {


    public String getErrorMessage(Object object, Exception e) {
        String className = object.getClass().getName();
        String initiator = "";
        for (int i = 0; i < e.getStackTrace().length; i++) {
            if (className.equals(e.getStackTrace()[i].getClassName())
                    && i+1 <e.getStackTrace().length
                    && !className.equals(e.getStackTrace()[i+1].getClassName()) ) {
                initiator = String.format("%s %s()",e.getStackTrace()[i+1].getClassName(),e.getStackTrace()[i+1].getMethodName());
            }
        }
        String message = String.format("%n Error class     : %s%n Initiator class : %s%n Message         : %s",className,initiator,e.getMessage());
        return message;
    }

    public String getPagesMessage(User user, HttpServletRequest request) {

        String ip = request.getRemoteAddr();
        String url = request.getRequestURI();
        String login = request.getRemoteUser();
        String name = user.getUsername();
        return String.format("IP: %1$-15s PAGES: %2$-30s LOGIN: %3$-30s USER: %4$-30s",ip,url,login,name);

    }
}
