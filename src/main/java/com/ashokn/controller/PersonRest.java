package com.ashokn.controller;

import com.ashokn.model.Person;
import com.ashokn.service.PersonService;
import org.hibernate.validator.constraints.Email;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.ws.rs.core.Response;

/**
 * Created by ashok on 6/18/17.
 */
@RestController
@RequestMapping("/rs/user")
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
        try {
            Person person1 = personService.savePerson(person);
            return Response.ok().entity(person1).build();
        }catch (Exception ex){
            return Response.status(500).entity("Can't create user check address fields").build();
        }
    }

    @PutMapping("/{id}")
    public Response updateUser(@Validated @RequestBody Person person,@PathVariable("id") int id){
        try{
            person.setId(id);
            personService.savePerson(person);
            return Response.ok().entity(person).build();
        }catch (Exception ex){
            return Response.status(500).entity("Can't update user").build();
        }

    }

    @DeleteMapping("/{id}")
    public Response deleteUser(@PathVariable("id") int id){
        try{
            Person person = personService.findById(id);
            person.setEnabled(false);
            personService.savePerson(person);
            return Response.ok().entity(person).build();
        }catch (Exception ex){
            return Response.status(500).entity("Can't delete user").build();
        }
    }

}
