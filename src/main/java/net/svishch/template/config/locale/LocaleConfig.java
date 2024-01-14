package net.svishch.template.config.locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
public class LocaleConfig {


    @Bean
    public TranslatorMessages translatorMessages() {
        TranslatorMessages translator = new TranslatorMessages("locales/translate");
        translator.addBasenames("locales/head");
        translator.addBasenames("locales/menu");
        translator.addBasenames("locales/index");
        translator.addBasenames("locales/robust-lite");
        return translator;
    }

    @Bean
    public MessageSource messageSource () {
        return translatorMessages();
    }

    @Bean
    public LocaleResolver localeResolver() {

        CookieLocaleResolver lr = new CookieLocaleResolver();
        Locale defaultLocale = new Locale("ru");
        lr.setDefaultLocale(defaultLocale);
        lr.setCookieName("locale");
        lr.setCookiePath("/");
        return lr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }



}
