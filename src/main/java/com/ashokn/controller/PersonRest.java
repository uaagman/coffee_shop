package com.ashokn.controller;

import com.ashokn.model.Authority;
import com.ashokn.model.Person;
import com.ashokn.service.PersonService;
import org.hibernate.validator.constraints.Email;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.Set;

/**
 * Created by ashok on 6/18/17.
 */
@RestController
@RequestMapping("/rs/person")
@Validated
public class PersonRest {
    @Resource
    private PersonService personService;

    @GetMapping("")
    public Response allUsers(){
        return Response.ok().entity(personService.findAll()).build();
    }

    @GetMapping("/email")
    public Response getUserByEmail(@Email(message = "Invalid Email format") @RequestParam("emailId") String emailId){
        return Response.ok().entity(personService.findByEmail(emailId)).build();
    }

    @PostMapping("")
    public Response saveUser(@Validated @RequestBody Person person){
//        try {
            /*Authority a = new Authority(person,"USER");
            person.setAuthorities(Collections.singletonList(a));*/
            Person person1 = personService.savePerson(person);
            return Response.ok().entity(person1).build();
       /* }catch (Exception ex){
            return Response.status(500).entity("Can't create user check input fields").build();
        }*/
    }

    @PutMapping("/{id}")
    public Response updateUser(@Validated @RequestBody Person person,@PathVariable("id") int id){
        try{
            person.setId(id);
            Person person1 = personService.updatePerson(person);
            return Response.ok().entity(person1).build();
        }catch (Exception ex){
            return Response.status(500).entity(ex.getMessage()).build();
        }

    }

    @DeleteMapping("/{id}")
    public Response deleteUser(@PathVariable("id") int id){
        try{
            Person person = personService.findById(id);
            personService.removePerson(person);
            return Response.ok().entity(person).build();
        }catch (Exception ex){
            return Response.status(500).entity("Can't delete user").build();
        }
    }

    @ExceptionHandler(value = { ConstraintViolationException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleResourceNotFoundException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : violations ) {
            strBuilder.append(violation.getMessage()).append("\n");
        }
        return strBuilder.toString();
    }

}
