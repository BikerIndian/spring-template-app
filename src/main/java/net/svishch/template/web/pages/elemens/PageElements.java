package net.svishch.template.web.pages.elemens;

import net.svishch.template.config.locale.TranslatorMessages;
import net.svishch.template.config.web.UrlAndModelPath;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;

public class PageElements {

    private TranslatorMessages translator;

    public PageElements(TranslatorMessages translatorMessages) {
    }

    public String getModel(Model model, User userAuth) {
        return UrlAndModelPath.MODEL_ELEMENTS_FORM;
    }
}
