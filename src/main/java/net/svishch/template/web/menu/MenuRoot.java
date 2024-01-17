package net.svishch.template.web.menu;



import net.svishch.template.web.element.menu.MenuNav;
import org.springframework.context.MessageSource;

import java.util.List;

public class MenuRoot extends MenuNav {


    public MenuRoot(List<String> userRoles, MessageSource messageSource) {
        super(0, userRoles);
        init(userRoles,messageSource);
    }

    private void init(List<String> userRoles, MessageSource messageSource) {

        MenuNavFactory menu = new MenuNavFactory(userRoles,messageSource);

        MenuNav doc = menu.getDoc()                         // Документы
                .addItem(menu.getDocList());                // Список документов

        addItem(doc);                                       // Документы
    }

}
