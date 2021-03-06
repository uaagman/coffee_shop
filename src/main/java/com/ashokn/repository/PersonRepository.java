package com.ashokn.repository;

import com.ashokn.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

	public Person findByEmail(String email);
	public Person findById(int id);
	
}
