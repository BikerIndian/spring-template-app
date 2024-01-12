package net.svishch.template.web.pages;


import net.svishch.template.config.web.UrlAndModelPath;
import org.springframework.ui.Model;


public class Login {

    private String appVersion;

    public Login(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getModel(Model model) {
        model.addAttribute("version", appVersion);
        return UrlAndModelPath.MODEL_LOGIN;
    }
}
