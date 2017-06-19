package com.ashokn.model;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.validator.constraints.NotEmpty;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@SelectBeforeUpdate
@DynamicUpdate
@Table(name = "products")
public class Product extends Model{

    @NotEmpty(message = "Product Name can't be empty")
	private String productName;
    @NotEmpty(message = "Product description can't be empty")
	private String description;
    @Min(value = 1,message = "Price should be greater than 0")
	private double price;
    @NotNull(message = "Invalid product type")
    @Enumerated(EnumType.STRING)
	private ProductType productType;

	public Product() {
		super(); // default constructor
	}

	public Product(String productName, String description, double price, ProductType productType) {
		super();
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.productType = productType;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
