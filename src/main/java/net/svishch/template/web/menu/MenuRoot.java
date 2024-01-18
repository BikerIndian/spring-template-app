package net.svishch.template.web.menu;

import net.svishch.template.config.locale.TranslatorMessages;
import net.svishch.template.web.element.menu.MenuNav;

import java.util.List;

public class MenuRoot extends MenuNav {


    public MenuRoot(List<String> userRoles, TranslatorMessages translator) {
        super(userRoles);
        init(userRoles,translator);
    }

    private void init(List<String> userRoles, TranslatorMessages translator) {

        MenuNavFactory menu = new MenuNavFactory(userRoles,translator);

        MenuNav doc = menu.getDoc()                         // Документы
                .addItem(menu.getDocList());                // Список документов

        addItem(doc);                                       // Документы
    }

}
