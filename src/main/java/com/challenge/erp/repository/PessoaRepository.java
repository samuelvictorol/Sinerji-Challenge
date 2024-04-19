package com.challenge.erp.repository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


import com.challenge.erp.models.Pessoa;
import com.challenge.erp.util.Transacional;

public class PessoaRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public PessoaRepository() {

	}

	public PessoaRepository(EntityManager manager) {
		this.manager = manager;
	}

	public Pessoa porId(Long id) {
		return manager.find(Pessoa.class, id);
	}

	public List<Pessoa> pesquisar(String nome) {
		String jpql = "from Pessoa where nome like :nome";
		
		TypedQuery<Pessoa> query = manager
				.createQuery(jpql, Pessoa.class);
		
		query.setParameter("nome", nome + "%");
		
		return query.getResultList();
	}
	
	public List<Pessoa> todas() {
         return manager.createQuery("from Pessoa", Pessoa.class).getResultList();
    }
	@Transacional
	public Pessoa guardar(Pessoa pessoa) {
		return manager.merge(pessoa);
	}
	@Transacional
	public void remover(Pessoa pessoa) {
		pessoa = porId(pessoa.getId());
		manager.remove(pessoa);
	}
	public List<Pessoa> consultarPorFiltros(String nome, String cidade, String estado) {
        StringBuilder jpql = new StringBuilder("SELECT p FROM Pessoa p WHERE 1 = 1");
        List<String> params = new ArrayList<>();

        if (nome != null && !nome.isEmpty()) {
            jpql.append(" AND p.nome LIKE :nome");
            params.add("nome");
        }

        if (cidade != null && !cidade.isEmpty()) {
            jpql.append(" AND p.endereco.cidade LIKE :cidade");
            params.add("cidade");
        }

        if (estado != null && !estado.isEmpty()) {
            jpql.append(" AND p.endereco.estado LIKE :estado");
            params.add("estado");
        }

        TypedQuery<Pessoa> query = manager.createQuery(jpql.toString(), Pessoa.class);

        for (String param : params) {
            switch (param) {
                case "nome":
                    query.setParameter("nome", "%" + nome + "%");
                    break;
                case "cidade":
                    query.setParameter("cidade", "%" + cidade + "%");
                    break;
                case "estado":
                    query.setParameter("estado", "%" + estado + "%");
                    break;
            }
        }

        return query.getResultList();
    }
}