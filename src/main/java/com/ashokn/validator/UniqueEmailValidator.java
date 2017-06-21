package com.ashokn.validator;

import com.ashokn.model.Person;
import com.ashokn.service.PersonService;
import org.springframework.web.servlet.HandlerMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Map;

/**
 * Created by ashok on 6/20/17.
 */


class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Resource
    private PersonService personService;
    @Resource
    private HttpServletRequest request;

    public void initialize(UniqueEmail constraint) {}

    public boolean isValid(String email, ConstraintValidatorContext context) {
        Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        if(pathVariables.get("id") != null) {
            System.out.println(pathVariables.get("id"));
            int id = Integer.parseInt(pathVariables.get("id"));
            return personService.findById(id).getEmail().equals(email) || personService.findByEmail(email) == null;
        }
        return personService.findByEmail(email) == null;
    }

}
