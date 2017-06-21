package com.ashokn.validator;

import com.ashokn.service.PersonService;
import org.springframework.web.servlet.HandlerMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;

/**
 * Created by ashok on 6/21/17.
 */
public class NotNullPasswordValidator implements ConstraintValidator<NotNullPassword, String> {

    @Resource
    private PersonService personService;
    @Resource
    private HttpServletRequest request;

    @Override
    public void initialize(NotNullPassword notNullPassword) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        if(pathVariables.get("id") != null && s == null) {
            int id = Integer.parseInt(pathVariables.get("id"));
            return personService.findById(id).getPassword() != null;
        }
        return s != null;
    }
}
