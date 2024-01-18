package net.svishch.template.web.menu;


import net.svishch.template.config.locale.TranslatorMessages;
import net.svishch.template.config.security.RoleListApp;
import net.svishch.template.config.web.UrlAndModelPath;
import net.svishch.template.web.element.menu.MenuNav;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class MenuNavFactory {
    public static final String ID_LOAD_PAGE = "row-Page";

    private TranslatorMessages translator;

    private MenuNav root;
    private MenuNav doc;                    // Документы
    private MenuNav docList;                // Список документов

    private List<String> userRoles;

    public MenuNavFactory(List<String> userRoles, TranslatorMessages translator) {
        this.userRoles = userRoles;
        this.translator = translator;
        init();
    }

    private void init() {

        // MENU_ROOT
        root = new MenuNav(userRoles);

        doc = new MenuNav(userRoles)
                .setIcon("icon-document-text")
                .setName(translator.getText("menu.doc.name"))
                .accessAdd(RoleListApp.ROLE_USER)
                .accessAdd(RoleListApp.ROLE_ADMIN);

        docList = new MenuNav(userRoles)
                .setName(translator.getText("menu.docList.name"))
                .setIcon("icon-android-home")
                .setPage(UrlAndModelPath.URL_BLANK)
                .setIdLoadPage(ID_LOAD_PAGE)
                .accessAdd(RoleListApp.ROLE_USER)
                .accessAdd(RoleListApp.ROLE_ADMIN);


    }

    public MenuNav getRoot() {
        return root;
    }

    public MenuNav getDoc() {
        return doc;
    }

    public MenuNav getDocList() {
        return docList;
    }

}
