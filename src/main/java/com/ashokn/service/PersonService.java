package com.ashokn.service;

import java.util.List;

import com.ashokn.model.Person;
import com.ashokn.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonService {

	private final PersonRepository personRepository;

	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public Person savePerson(Person person) {
		return personRepository.save(person);
	}

	public List<Person> findAll(){
		return personRepository.findAll();
	}

	public List<Person> findByEmail(String email) {
		return personRepository.findByEmail(email);
	}

	public Person findById(int id) {
		return personRepository.findOne((long) id);
	}

	public void removePerson(Person person) {
		personRepository.delete(person);
	}

}
