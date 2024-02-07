package com.mavenproject.erp.model.repository;

import com.mavenproject.erp.model.Empresa;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class SchemaGeneration {

	public static void main(String[] args) {		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jsf_prime_PU");

		EntityManager em = emf.createEntityManager();

		List<Empresa> lista = em.createQuery("from Empresa", Empresa.class)
				.getResultList();

		System.out.println(lista);

		em.close();
		emf.close();
	}

}