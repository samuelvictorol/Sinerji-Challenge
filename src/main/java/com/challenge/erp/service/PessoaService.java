package com.challenge.erp.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.challenge.erp.models.Pessoa;
import com.challenge.erp.repository.PessoaRepository;
import com.challenge.erp.util.Transacional;

public class PessoaService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private PessoaRepository pessoas;

    @Transacional
    public void salvar(Pessoa pessoa) {
        pessoas.guardar(pessoa);
    }

    @Transacional
    public void excluir(Pessoa pessoa) {
    	pessoas.remover(pessoa);
    }

}