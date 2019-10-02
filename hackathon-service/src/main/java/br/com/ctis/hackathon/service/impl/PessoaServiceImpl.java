package br.com.ctis.hackathon.service.impl;

import br.com.ctis.hackathon.dto.PessoaDTO;
import br.com.ctis.hackathon.persistence.dao.PessoaDAO;
import br.com.ctis.hackathon.persistence.model.Pessoa;
import br.com.ctis.hackathon.service.PessoaService;

import javax.ejb.EJB;

public class PessoaServiceImpl extends GenericServiceImpl<Long, Pessoa> implements PessoaService {

    @EJB
    private PessoaDAO pessoaDAO;

    @Override
    public void cadastrar(PessoaDTO pessoaDTO) {

    }

    @Override
    public Pessoa buscarPessoaPorId(Long id) {
        return null;
    }

    /**
     * Converter PessoaDTO para entidade Pessoa
     *
     * @param pessoaDTO - DTO que será convertido
     *
     * @return {@link Pessoa} - Entidade Pessoa
     */
    private Pessoa mapper(PessoaDTO pessoaDTO) {

        Pessoa pessoa = new Pessoa();
        pessoa.setCpf(pessoaDTO.getCpf());
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setSobrenome(pessoaDTO.getSobrenome());
        pessoa.setEmail(pessoaDTO.getEmail());

        return pessoa;

    }
}
