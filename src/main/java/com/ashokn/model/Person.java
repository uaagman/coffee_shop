package com.ashokn.model;

import com.ashokn.validator.UniqueEmail;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "persons")
public class Person extends Model{

	@NotEmpty(message = "First name can't be empty")
	private String firstName;

	@NotEmpty(message = "Last name can't be empty")
	private String lastName;

	@Column(unique = true)
	@NotEmpty(message = "Email can't be empty")
	@Email(message = "Email should be valid")
    @UniqueEmail
	private String email;

	@OneToOne(cascade = CascadeType.ALL)
    @NotNull(message = "Address can't be null")
	@Valid
    private Address address;

	@Pattern(regexp="\\d{10}",message = "Invalid phone format. should be 10 digits")
	private String phone;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
