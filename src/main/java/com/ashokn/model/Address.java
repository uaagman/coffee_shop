package com.ashokn.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "addresses")
public class Address extends Model{

	@NotEmpty(message = "City cann't be empty")
	private String city;
	@NotEmpty(message = "State can't be empty")
	private String state;
	@NotEmpty(message = "Country can't be empty")
	private String country;
	@NotEmpty(message = "Zip can't be empty")
	@Length(min = 5,max = 5,message = "Zip should be 5 digits")
	private String zipcode;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

}
