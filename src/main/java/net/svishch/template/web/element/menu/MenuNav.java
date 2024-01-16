package net.svishch.template.web.element.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuNav {

    private int id;
    private String page = "";
    private String idLoadPage ="";
    private String icon = "";
    private  String name = "";
    private String classItem = "nav-item";
    private String classItemIn = "nav-item has-sub";
    private String href="";
    private List<MenuNav> listItem;
    private List<String> accessUsers;
    private List<String> userRoles;
    private boolean bimbo = false;

    public MenuNav(boolean bimbo) {
    this.bimbo = bimbo;
    }

    public MenuNav(int id, List<String> userRoles) {
        this.userRoles = userRoles;
        this.id = id;
        this.listItem = new ArrayList<>();
        this.accessUsers = new ArrayList<>();

    }


    public boolean isNoEnd(){
        if (0 == listItem.size()) {
            return false;
        }
        return true;
    }

    public MenuNav setPage(String page) {
        this.page = page;
        return this;
    }

    public MenuNav setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public MenuNav setName(String name) {
        this.name = name;
        return this;
    }


    public MenuNav addItem(MenuNav item) {
        if (item != null && !item.isBimbo()) {
            this.listItem.add(item);
        }
        return this;
    }

    public MenuNav rootMenu(MenuNav item) {
        if (item != null && !item.isBimbo()) {
            this.listItem.add(item);
        }
        return item;
    }


    public String getClassItem() {
        if (0 < listItem.size()) {
            return classItemIn;
        }
        return classItem;
    }

    public List<MenuNav> getListItem() {
        return listItem;
    }

    public String getName() {
        return name;
    }

    public String getPage() {
        return page;
    }

    public String getIcon() {
        return icon;
    }

    public String getIdLoadPage() {
        return idLoadPage;
    }

    public MenuNav setIdLoadPage(String idLoadPage) {
        this.idLoadPage = idLoadPage;
        return this;
    }

    public int getId() {
        return id;
    }


    public MenuNav accessAdd(String role) {
        accessUsers.add(role);
        return this;
    }

    public boolean isAccess() {
        if (userRoles != null) {
            for (int i = 0; i < accessUsers.size(); i++) {
                if ( userRoles.contains(accessUsers.get(i))) {
                    return true;
                }

            }

        }
        return false;
    }

    public boolean isBimbo() {
        return bimbo;
    }
}
