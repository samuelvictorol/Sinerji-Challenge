package com.challenge.erp.controller;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.challenge.erp.models.Pessoa;

import com.challenge.erp.repository.PessoaRepository;

@Named
@ViewScoped
public class ConsultaPessoaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(ConsultaPessoaBean.class.getName());

	@Inject
	private PessoaRepository pessoaRepository;
	private List<Pessoa> listaPessoas;

	private Pessoa pessoa = new Pessoa();
	private String nomeConsulta;
	private String cidadeConsulta;
	private String estadoConsulta;

	public List<Pessoa> getListaPessoas() {
		return this.listaPessoas;
	}

	public void consultarPessoas() {
		this.listaPessoas = this.pessoaRepository.consultarPorFiltros(nomeConsulta, cidadeConsulta, estadoConsulta);
	}

	public void todasPessoas() {
		this.listaPessoas = this.pessoaRepository.todas();
	}

	public void limparFiltros() {
		this.nomeConsulta = null;
		this.cidadeConsulta = null;
		this.estadoConsulta = null;
		consultarPessoas(); // Atualiza a lista para exibir todos os registros
	}

	public void salvar() {
		LOGGER.info("Método salvar() foi chamado!");
		LOGGER.info("Nome: " + pessoa.getNome());
		LOGGER.info("Endereco: " + pessoa.getEndereco().getCidade());
		// Lógica para salvar a pessoa no banco de dados
	}

	// Getters e setters para os filtros de consulta
	public String getNomeConsulta() {
		return nomeConsulta;
	}

	public void setNomeConsulta(String nomeConsulta) {
		this.nomeConsulta = nomeConsulta;
	}

	public String getCidadeConsulta() {
		return cidadeConsulta;
	}

	public void setCidadeConsulta(String cidadeConsulta) {
		this.cidadeConsulta = cidadeConsulta;
	}

	public String getEstadoConsulta() {
		return estadoConsulta;
	}

	public void setEstadoConsulta(String estadoConsulta) {
		this.estadoConsulta = estadoConsulta;
	}
	public String calcularIdade(Date dataNascimento) {
		if (dataNascimento != null) {
			Calendar dataNasc = Calendar.getInstance();
			dataNasc.setTime(dataNascimento);

			Calendar hoje = Calendar.getInstance();

			int anos = hoje.get(Calendar.YEAR) - dataNasc.get(Calendar.YEAR);
			int mesAtual = hoje.get(Calendar.MONTH);
			int mesNascimento = dataNasc.get(Calendar.MONTH);

			if (mesNascimento > mesAtual || (mesNascimento == mesAtual
					&& hoje.get(Calendar.DAY_OF_MONTH) < dataNasc.get(Calendar.DAY_OF_MONTH))) {
				anos--;
			}

			return " - " + anos + " anos";
		}
		return "";
	}
	 public void removerPessoa(Pessoa pessoa) {

	        	LOGGER.info("Removendo pessoa: " + pessoa.getNome());
	        	pessoaRepository.remover(pessoa);
	        	
	        	this.listaPessoas.remove(pessoa);

	    }
	 public String redirectToEdit(Long pessoaId) {
		    String editPage = "UpdatePessoa.xhtml?faces-redirect=true&id=" + pessoaId;
		    return editPage;
		}
}
