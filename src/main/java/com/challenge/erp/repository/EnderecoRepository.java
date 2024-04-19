package com.challenge.erp.repository;
import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.challenge.erp.models.Endereco;
import com.challenge.erp.util.Transacional;

public class EnderecoRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public EnderecoRepository() {

	}

	public EnderecoRepository(EntityManager manager) {
		this.manager = manager;
	}

	public Endereco porId(Long id) {
		return manager.find(Endereco.class, id);
	}

	public List<Endereco> pesquisar(String cidade) {
		String jpql = "from Endereco where cidade like :cidade";
		
		TypedQuery<Endereco> query = manager
				.createQuery(jpql, Endereco.class);
		
		query.setParameter("cidade", cidade + "%");
		
		return query.getResultList();
	}
	
	public List<Endereco> todas() {
         return manager.createQuery("from Endereco", Endereco.class).getResultList();
    }
	@Transacional
	public Endereco guardar(Endereco endereco) {
		return manager.merge(endereco);
	}
	@Transacional
	public void remover(Endereco endereco) {
		endereco = porId(endereco.getId());
		manager.remove(endereco);
	}
}