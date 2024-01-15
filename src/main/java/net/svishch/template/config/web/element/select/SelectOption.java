package net.svishch.template.config.web.element.select;

public class SelectOption {
    public String value = "";
    public String text ="";

    public String assembly ="";
    public String numberGtd = "";

    private boolean selected = false;
    private boolean disabled = false;

    public SelectOption(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
