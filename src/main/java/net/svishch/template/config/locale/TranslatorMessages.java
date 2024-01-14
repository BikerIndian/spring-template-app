package net.svishch.template.config.locale;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.*;

public class TranslatorMessages extends ResourceBundleMessageSource {

    private String encoding = "UTF-8";
    private String localeBaseNames;
    private Locale localeDefault;
    private Map<String,String> statusArr = new HashMap<>();

    public TranslatorMessages(String localeBaseNames_) {
        this.localeBaseNames = localeBaseNames_;
        this.localeDefault = new Locale("ru","RU");

        addBasenames(localeBaseNames);
        setDefaultEncoding(encoding);
        setDefaultLocale(this.localeDefault);
        initStatusArr();

    }

    public String getTranslate(String status) {
        status = status.trim();
        if (statusArr.containsKey(status)) {
            return getText(statusArr.get(status));
        } else {
            return status;
        }
    }



    private void initStatusArr() {
        Set<String> keyArr = new HashSet<>();

        try {
            ResourceBundle bundle = getResourceBundle(this.localeBaseNames, localeDefault);
            if (bundle != null) {
                keyArr =   bundle.keySet();
            }

        } catch (Exception e){
            System.out.println("Error: ");
            e.printStackTrace();
        }



        for (String key:  keyArr) {
            statusArr.put(getMessage(key.trim(),null,localeDefault),key);
        }

    }

    public String getText(String key) {
        return getMessage(key,null,LocaleContextHolder.getLocale());
    }
}
