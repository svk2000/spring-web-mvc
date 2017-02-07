package com.theironyard.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.stereotype.Service;

import com.theironyard.models.Person;

@Service
public class PersonDaoImpl implements PersonDao {
	private EntityManagerFactory factory;

	public PersonDaoImpl(EntityManagerFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public List<Person> getPeople() {
		EntityManager manager = factory.createEntityManager();
		
		return manager
				.createQuery("from Person", Person.class)
				.getResultList();
	}

	@Override
	public Person insert(Person person) {
		EntityManager manager = factory.createEntityManager();
		
		manager.getTransaction().begin();
		person = manager.merge(person);
		manager.getTransaction().commit();
		
		return person;
	}

	@Override
	public Person get(Integer id) {
		EntityManager manager = factory.createEntityManager();
		
		return manager.find(Person.class, id);
				//.createQuery("from Person where id = " + id, Person.class).getSingleResult();
	}

	@Override
	public void update(Person person) {
		insert(person);
	}
}













