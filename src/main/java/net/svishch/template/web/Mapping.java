package net.svishch.template.web;

import net.svishch.template.config.locale.TranslatorMessages;
import net.svishch.template.config.web.UrlAndModelPath;
import net.svishch.template.web.pages.Login;
import net.svishch.template.web.pages.PageIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Mapping {

    @Autowired
    TranslatorMessages translatorMessages;

    @Value("${app.project.version}")
    private String appVersion;

    @RequestMapping(value = "/")
    public String getIndex(Model model, @AuthenticationPrincipal User userAuth) {
        return new PageIndex().getModel(model, userAuth,translatorMessages);
    }

    @GetMapping(UrlAndModelPath.URL_LOGIN)
    public String get(Model model) {
        return new Login(appVersion).getModel(model);
    }

    @GetMapping(UrlAndModelPath.URL_BLANK)
    public String getBlank() {
        return UrlAndModelPath.MODEL_BLANK;
    }
}
