package com.ashokn.service;

import java.util.List;

import com.ashokn.model.Product;
import com.ashokn.model.ProductType;
import com.ashokn.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService   {
	
	private final ProductRepository productRepository;

	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Product save(Product product) {
		return productRepository.saveAndFlush(product);
	}

	public void delete(Product product) {
		productRepository.delete(product);
	}

	public void delete(int id){
		productRepository.delete(id);
	}

	public Product getProduct(int productId) {
		return  productRepository.findOne(productId);
	}

	public List<Product> getAllProduct() {
		return  productRepository.findAll() ;
	}
	
	public List<Product> findByTextSearch(String criteria) {
		if (!criteria.contains("%")) {
			criteria = "%"+criteria+"%";
		}
		return productRepository.findByProductNameLikeOrDescriptionLikeAllIgnoreCase(criteria, criteria);
	}

	public List<Product> findByPrice(double minPrice, double maxPrice) {
		return  productRepository.findByPriceBetween(minPrice, maxPrice);
	}
	
	public List<Product> findByProductType(ProductType productType) {
		 return productRepository.findByProductType(productType);
	}
	
}
