package com.ashokn.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.ashokn.model.Authority;
import com.ashokn.model.Person;
import com.ashokn.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class PersonService {

	private final PersonRepository personRepository;

	@Resource
    private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public Person savePerson(Person person) {
        Authority authority = new Authority(person,"USER");
        person.setAuthorities(Collections.singletonList(authority));
        person.setPassword(passwordEncoder.encode(person.getPassword()));
		return personRepository.save(person);
	}

    public Person savePersonAdmin(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        return personRepository.save(person);
    }

	public List<Person> findAll(){
		return personRepository.findAll();
	}

	public Person findByEmail(String email) {
		return personRepository.findByEmail(email);
	}

	public Person findById(int id) {
		return personRepository.findById(id);
	}

	public void removePerson(Person person) {
		personRepository.delete(person);
	}

	public Person updatePerson(Person person){
		Person person1 = findById(person.getId());
		person.setCreatedAt(person1.getCreatedAt());
		person.setUpdatedAt(new Date());
		person.getAddress().setCreatedAt(person1.getAddress().getCreatedAt());
		person.getAddress().setUpdatedAt(new Date());
		person.getAddress().setId(person1.getAddress().getId());
		if(person.getPassword() == null || person.getPassword().equals("")){
            person.setPassword(passwordEncoder.encode(person1.getPassword()));
        }
        person.setAuthorities(person1.getAuthorities());
		return personRepository.save(person);
	}

}
