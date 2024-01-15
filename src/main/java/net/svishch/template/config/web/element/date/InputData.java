package net.svishch.template.config.web.element.date;

public class InputData {
    public String dataVal="true";
    public String name="dateStart";
    public String placeholder="дд.мм.гггг";
    public String type="date";
    public String value="";

    public InputData() {
    }

    public InputData(String name) {
        this.name = name;
    }

    public InputData setDataVal(String dataVal) {
        this.dataVal = dataVal;
        return this;
    }

    public InputData setName(String name) {
        this.name = name;
        return this;
    }

    public InputData setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        return this;
    }

    public InputData setType(String type) {
        this.type = type;
        return this;
    }

    public InputData setValue(String value) {
        this.value = value;
        return this;
    }
}
