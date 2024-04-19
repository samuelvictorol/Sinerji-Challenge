package com.challenge.erp.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.challenge.erp.models.Pessoa;

public class SchemaGeneration {
	
	public static void main(String[] args) {		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("SinerjiChallenge");
		
		EntityManager em = emf.createEntityManager();
		
		List<Pessoa> lista = em.createQuery("from Pessoa", Pessoa.class)
				.getResultList();
		
		System.out.println(lista);
		
		em.close();
		emf.close();
	}

}