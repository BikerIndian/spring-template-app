package net.svishch.template.web.pages.elemens;

import net.svishch.template.config.locale.TranslatorMessages;
import net.svishch.template.config.web.UrlAndModelPath;
import net.svishch.template.web.element.select.SelectMenu;
import net.svishch.template.web.pages.elemens.timelife.FilterElements;
import net.svishch.template.web.pages.elemens.timelife.ThElements;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;

public class PageElements {

    private TranslatorMessages translator;

    public PageElements(TranslatorMessages translatorMessages) {
    }

    public String getModel(Model model, User userAuth) {
        // Timelife
        ThElements dataPages = getDataPages();
        model.addAttribute("dataPages", dataPages);
        return UrlAndModelPath.MODEL_ELEMENTS_FORM;
    }

    private ThElements getDataPages() {
        ThElements elements = new ThElements();
        FilterElements filter = new FilterElements();

        filter.selectType = getSelect();

        elements.filter = filter;
        return elements;
    }

    private SelectMenu getSelect() {
        SelectMenu selectMenu = new SelectMenu();
        selectMenu.setName("InSelectMenu");


        for (int i = 0; i < 10; i++) {
            selectMenu.add(Integer.toString(i), Integer.toString(i));
        }

        return selectMenu;
    }
}
