package net.svishch.template.web;

import net.svishch.template.config.locale.TranslatorMessages;
import net.svishch.template.config.web.UrlAndModelPath;
import net.svishch.template.services.report.ReportingCenterService;
import net.svishch.template.web.pages.Login;
import net.svishch.template.web.pages.PageIndex;
import net.svishch.template.web.pages.elemens.PageElements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class Mapping {

    @Autowired
    TranslatorMessages translatorMessages;

    @Autowired
    private ReportingCenterService reporting;

    @Autowired
    private HttpServletRequest request;

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
    public String getBlank( @AuthenticationPrincipal User user) {
        pagesLog(user);
        return UrlAndModelPath.MODEL_BLANK;
    }

    @GetMapping(UrlAndModelPath.URL_ELEMENTS_FORM)
    public String getElements(Model model, @AuthenticationPrincipal User user) {
        pagesLog(user);
        return new PageElements(translatorMessages).getModel(model,  user );
    }

    private void pagesLog(User user) {
        reporting.sendLogPages(user,request);
    }
}
