package com.ashokn.config;

import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by ashok on 6/18/17.
 */
public class webMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public Validator getValidator() {
       /* LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;*/
       return null;
    }
}
