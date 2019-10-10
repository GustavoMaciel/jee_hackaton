package br.com.ctis.hackathon.service;

import br.com.ctis.hackathon.dto.PessoaDTO;
import br.com.ctis.hackathon.persistence.model.Pessoa;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PessoaService extends GenericService<Long, Pessoa> {
    /**
     * Listar todas as Pessoas cadastradas no sistema
     *
     * @return {@link List <Pessoa>} - Lista com Pessoas cadastradas
     */
    List<Pessoa> listar(int pageNumber, int pageSize, String search);

    /**
     * Cadastrar uma nova Pessoa no sistema
     *
     * @param pessoaDTO - Parâmetro de entrada para cadastro da Pessoa
     */
    Pessoa cadastrar(PessoaDTO pessoaDTO);

    /**
     * Recuperar Pessoa pelo Id
     *
     * @param id - Identificado da Pessoa na base de dados
     *
     * @return {@link Pessoa} - Representa uma Pessoa cadastrada no sistema
     */
    Pessoa buscarPessoaPorId(Long id);

    /**
     * Deleta Pessoa pelo Id
     *
     * @param id - Identificado da Pessoa na base de dados
     */
    void deletar(Long id);

    /**
     * Retorna o número de Pessoas cadastradas
     * @return {@link Long} Número total de pessoas cadastradas no sistema
     */
    Long getTotalItems();
}
