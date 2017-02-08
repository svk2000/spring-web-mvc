package com.theironyard.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Service;

import com.theironyard.models.Person;
import com.theironyard.models.Product;

@Service
public class ProductsDaoImpl implements ProductsDao {
	private EntityManagerFactory factory;
	
	public ProductsDaoImpl(EntityManagerFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public List<Product> getProducts() {
		EntityManager manager = factory.createEntityManager();
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Product> query = builder.createQuery(Product.class);
		query.from(Product.class);
		return manager
				.createQuery(query)
				.getResultList();
	}

	@Override
	public Product insert(Product product) {
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		product = manager.merge(product);
		manager.getTransaction().commit();
		
		return product;
	}

	@Override
	public Product get(Integer id) {
		EntityManager manager = factory.createEntityManager();
		return manager.find(Product.class, id);
	}

	@Override
	public void update(Product product) {
		insert(product);
		
	}
	
	@Override
	public void deleteById(Integer id) {
		EntityManager manager = factory.createEntityManager();

		manager.getTransaction().begin();
		Product product = manager.find(Product.class, id);
		manager.remove(product);
		manager.getTransaction().commit();
	}
}
