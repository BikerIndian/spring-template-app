package net.svishch.template.web;

import net.svishch.template.config.web.UrlAndModelPath;
import net.svishch.template.web.pages.Login;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Mapping {

    @Value("${app.project.version}")
    private String appVersion;

    @GetMapping(UrlAndModelPath.URL_LOGIN)
    public String get(Model model) {
        return new Login(appVersion).getModel(model);
    }
}
