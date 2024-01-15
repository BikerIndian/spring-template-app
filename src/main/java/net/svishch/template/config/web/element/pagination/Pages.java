package net.svishch.template.config.web.element.pagination;

// Переход по страницам
public class Pages {

    private String formatOnclick;
    public String classCss = "";
    public boolean active = false;
    public int pageNum = 1;
    public int sizePage = 1;
    public boolean previous = false;
    public boolean next = false;

    public Pages(String formatOnclick) {
        this.formatOnclick = formatOnclick;
    }

    public String getOnclick() {
        String onClick = String.format(formatOnclick, pageNum, sizePage);
        return onClick;
    }

    public String getActive() {
        if (active) {
            return " active";
        }
        return "";
    }

    public String getClassCss() {
        return classCss;
    }

    public void setClassCss(String classCss) {
        this.classCss = classCss;
    }

    public void setFormatOnclick(String formatOnclick) {
        this.formatOnclick = formatOnclick;
    }
}
