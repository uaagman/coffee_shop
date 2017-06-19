package com.ashokn.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class Person extends Model{

	@Column(unique = true)
	@NotEmpty(message = "Username can't be empty")
	private String username;
	@NotEmpty(message = "Password can't be empty")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	private boolean enabled=true;
	@NotEmpty(message = "First name can't be empty")
	private String firstName;
	@NotEmpty(message = "Last name can't be empty")
	private String lastName;
	@NotEmpty(message = "Email can't be empty")
	@Email(message = "Email should be valid")
	private String email;
	@OneToOne(cascade = CascadeType.ALL)
    @NotNull(message = "Address can't be null")
    private Address address;
	private String phone;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

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
