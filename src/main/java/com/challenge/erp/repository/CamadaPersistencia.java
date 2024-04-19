package com.challenge.erp.repository;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.challenge.erp.models.Endereco;
import com.challenge.erp.models.Pessoa;
import com.challenge.erp.models.SexoENUM;

public class CamadaPersistencia {
	public static void main(String[] args) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("SinerjiChallenge");
	    EntityManager em = emf.createEntityManager();

	    try {
	        em.getTransaction().begin();

	        Pessoa pessoa = new Pessoa();
	        pessoa.setNome("Samuel Victor");
	        pessoa.setIdade(new Date());
	        pessoa.setSexo(SexoENUM.MASC);

	        Endereco endereco = new Endereco();
	        endereco.setCep("76867523");
	        endereco.setEstado("Goiás");
	        endereco.setCidade("Valparaíso");
	        endereco.setLogradouro("Quadra 12 - Bairro dos Limoeiros");
	        endereco.setNumero(12);
	        endereco.setPessoa(pessoa); // Vincula o endereço à pessoa

	        pessoa.setEndereco(endereco); // Vincula a pessoa ao endereço

	        em.persist(pessoa); // Persiste a pessoa e, por cascata, o endereço

	        em.getTransaction().commit();

	        // Verificar a inserção
	        System.out.println("Pessoa ID: " + pessoa.getId());
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        em.close();
	        emf.close();
	    }
	}
}
