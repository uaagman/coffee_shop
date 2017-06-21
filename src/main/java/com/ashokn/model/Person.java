package com.ashokn.model;

import com.ashokn.validator.NotNullPassword;
import com.ashokn.validator.UniqueEmail;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
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

    @NotNullPassword
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	private boolean enabled=true;

	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Authority> authorities;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
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

	@Override
	public String toString() {
		return "Person{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", address=" + address +
				", phone='" + phone + '\'' +
				'}';
	}
}
