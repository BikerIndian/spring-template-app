package net.svishch.template.web.pages;

import net.svishch.template.config.security.RoleListApp;
import net.svishch.template.web.menu.MenuRoot;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static net.svishch.template.config.web.UrlAndModelPath.URL_HOME;

public class PageIndex {
    public String getModel(Model model, User userAuth, MessageSource messageSource) {

        List<String> roles = new ArrayList<>();
        roles.add(RoleListApp.ROLE_USER);
        String userName = userAuth.getUsername();
        model.addAttribute("navItem", new MenuRoot(roles,messageSource));
        model.addAttribute("userName", userName);
        model.addAttribute("homepage", URL_HOME);

        return "index.mob";
    }
}
