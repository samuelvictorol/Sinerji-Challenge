package com.challenge.erp.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.challenge.erp.models.Pessoa;
import com.challenge.erp.models.Endereco;
import com.challenge.erp.repository.EnderecoRepository;
import com.challenge.erp.repository.PessoaRepository;

@Named
@ViewScoped
public class CreatePessoaBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(CreatePessoaBean.class.getName());

    @Inject
    private PessoaRepository pessoaRepository;
    @Inject
    private EnderecoRepository enderecoRepository;

    private Pessoa pessoa;
    private Endereco endereco;
    private boolean modoEdicao;
    private Long pessoaId; // ID da pessoa a ser editada
    public void carregarPessoa() {
        if (pessoaId != null) {
            this.pessoa = pessoaRepository.porId(pessoaId); // Implemente este serviço
            this.endereco = enderecoRepository.porId(pessoa.getEndereco().getId());
        }
    }
    @PostConstruct
    public void init() {
        if (pessoaId != null) {
            // Carregar a pessoa com base no ID fornecido
            pessoa = pessoaRepository.porId(pessoaId);
            endereco = pessoa.getEndereco(); // Obter o endereço da pessoa
            modoEdicao = true;
        } else {
            pessoa = new Pessoa();
            endereco = new Endereco();
            modoEdicao = false;
        }
    }

    // Método para salvar ou atualizar a pessoa
    public void salvarPessoa() {
        try {
            // Persistir o endereço se necessário
            if (endereco.getId() == null) {
                endereco = enderecoRepository.guardar(endereco);
            }

            // Associar o endereço à pessoa
            pessoa.setEndereco(endereco);

            // Salvar ou atualizar a pessoa
            if (modoEdicao) {
                pessoaRepository.guardar(pessoa);
            } else {
                pessoaRepository.guardar(pessoa);
            }

            exibirMensagemSucesso("Pessoa salva com sucesso!");

            redirecionarParaHome();

        } catch (Exception e) {
            LOGGER.severe("Erro ao salvar pessoa: " + e.getMessage());
            exibirMensagemErro("Erro ao salvar pessoa. Tente novamente.");
        }
    }

    private void exibirMensagemSucesso(String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", mensagem));
    }

    private void exibirMensagemErro(String mensagem) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", mensagem));
    }

    private void redirecionarParaHome() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.getFlash().setKeepMessages(true);
        externalContext.redirect(externalContext.getRequestContextPath() + "/Home.xhtml");
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

    public boolean isModoEdicao() {
        return modoEdicao;
    }

    // Método para limpar os dados após a inclusão ou edição
    public void limparDados() {
        pessoa = new Pessoa();
        endereco = new Endereco();
        pessoaId = null;
        modoEdicao = false;
    }
}
