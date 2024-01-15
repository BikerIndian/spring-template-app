package net.svishch.template.config.web.element.input;

public class Input {
    public String inputClass="";
    public String id="";
    public String name="";
    public String placeholder="";
    public String type="text";
    public String value="";

    public Input() {
    }

    public Input(String name, String value) {
        this.name = stringCheck(name);
        this.value = stringCheck(value);
    }

    public Input(String name, String value, String placeholder) {
        this.name = name;
        this.placeholder = placeholder;
        this.value = value;
    }

    public Input setInputClass(String inputClass) {
        this.inputClass = inputClass;
        return this;
    }

    public Input setId(String id) {
        this.id = id;
        return this;
    }

    public Input setName(String name) {
        this.name = name;
        return this;
    }

    public Input setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        return this;
    }

    public Input setType(String type) {
        this.type = type;
        return this;
    }

    public Input setValue(String value) {
        this.value = value;
        return this;
    }

    private String stringCheck(String str){
        if (str == null) {
            return "";
        }
        return str;
    }
}
