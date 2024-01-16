package net.svishch.template.web.element.select;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SelectMenu {
    private String name = "";
    public Map<String,SelectOption> selectOptions = new LinkedHashMap();

    public List<SelectOption> getSelectList() {
        List<SelectOption> selectOptionList = new ArrayList<>();
        for (Map.Entry<String, SelectOption> entry : selectOptions.entrySet()) {
            selectOptionList.add(entry.getValue());
        }
        return selectOptionList;
    }

    public SelectMenu() {

    }

    public SelectMenu(String selectKey, Map<String, String> list) {

        for (Map.Entry<String, String> entry : list.entrySet()) {
            add(entry.getKey(), entry.getValue());
        }

        if (containsKey(selectKey)) {
            selected(selectKey);
        }

    }

    public void addSelect(String key, String text) {
        SelectOption selectOption = new SelectOption(key, text);
        selectOption.setSelected(true);
        selectOptions.put(key,selectOption);
    }

    public void add(String key, String text) {
        selectOptions.put(key,new SelectOption(key, text));
    }

    /**
     * Элемент который будет выбран
     */
    public void selected(String key, String text) {
        SelectOption selectOption = new SelectOption(key, text);
        selectOption.setSelected(true);
        selectOptions.put(key,selectOption);
    }

    /**
     * Элемент который будет выбран
     */
    public void selected(String key) {
        if (containsKey(key)) {
            SelectOption selectOption = getSelected(key);
            selectOption.setSelected(true);
        }
    }

    private SelectOption getSelected(String key) {
        if (containsKey(key)) {
            return selectOptions.get(key);
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean containsKey(String key){
        return selectOptions.containsKey(key);
    }
}
