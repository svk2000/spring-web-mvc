package com.theironyard.services;

import java.util.List;

import com.theironyard.models.Product;

public interface ProductsDao {
	List<Product> getProducts();
	
	Product insert(Product product);
	
	Product get(Integer id);

	void update(Product product);
	
	void deleteById(Integer id);
}
