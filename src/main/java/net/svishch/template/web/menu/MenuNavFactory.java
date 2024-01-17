package net.svishch.template.web.menu;


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
    public static final String ID_LOAD_PAGE = "row-PageInfo";
    public static final int MENU_ROOT = 0;
    public static final int DOC = 100;
    public static final int DOC_LIST = 101;
    public static final int ALERT = 7;


    private MessageSource messageSource;
    private Locale locale;

    private MenuNav root;
    private MenuNav doc;                    // Документы
    private MenuNav docList;                // Список документов

    private static final Map<Integer, MenuNav> menu = new HashMap();
    private List<String> userRoles;

    public MenuNavFactory(List<String> userRoles, MessageSource messageSource) {
        this.userRoles = userRoles;
        this.messageSource = messageSource;
        this.locale = LocaleContextHolder.getLocale();
        init();
    }

    private void init() {

        // MENU_ROOT
        root = new MenuNav(MENU_ROOT, userRoles);

        doc = new MenuNav(DOC, userRoles)
                .setIcon("icon-document-text")
                .setName(getText("menu.doc.name"))
                .accessAdd(RoleListApp.ROLE_USER)
                .accessAdd(RoleListApp.ROLE_ADMIN);

        docList = new MenuNav(DOC_LIST, userRoles)
                .setName(getText("menu.docList.name"))
                .setIcon("icon-android-home")
                .setPage(UrlAndModelPath.URL_BLANK)
                .setIdLoadPage(ID_LOAD_PAGE)
                .accessAdd(RoleListApp.ROLE_USER)
                .accessAdd(RoleListApp.ROLE_ADMIN);


        put(root);
        put(doc);
        put(docList);

    }

    private String getText(String key) {
        return messageSource.getMessage(key,null,locale);
    }

    private MenuNav put(MenuNav menuNav) {
        menu.put(menuNav.getId(), menuNav);
        return menuNav;
    }

    public MenuNav get(int id) {
        if (menu.get(id).isAccess()) {
            return menu.get(id);
        }
        return new MenuNav(true);
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
