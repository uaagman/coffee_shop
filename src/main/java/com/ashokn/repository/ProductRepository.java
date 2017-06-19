package com.ashokn.repository;

import java.io.Serializable;
import java.util.List;

import com.ashokn.model.Product;
import com.ashokn.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Serializable> {

	public List<Product> findByProductNameLikeOrDescriptionLikeAllIgnoreCase(String productName, String description);
	public List<Product> findByProductType(ProductType productType);
	public List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
}
