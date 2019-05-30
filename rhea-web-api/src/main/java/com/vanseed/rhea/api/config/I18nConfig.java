package com.vanseed.rhea.api.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * @author leon
 * 
 */

@Configuration
public class I18nConfig implements WebMvcConfigurer {
	@Value(value = "${spring.messages.basename}")
    private String basename;
	
	@Bean  
	public LocaleResolver localeResolver() {  
		SessionLocaleResolver slr = new SessionLocaleResolver();  
		slr.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);  
		return slr;  
	}  

	@Bean  
	public LocaleChangeInterceptor localeChangeInterceptor() {  
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();  
		lci.setParamName("lang");  
		return lci;  
	} 
	
	@Bean(name = "messageSource")
    public ResourceBundleMessageSource getMessageResource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames(basename.split(","));
        return messageSource;
    }

	@Override  
	public void addInterceptors(InterceptorRegistry registry) {  
		registry.addInterceptor(localeChangeInterceptor());  
	}  

}
