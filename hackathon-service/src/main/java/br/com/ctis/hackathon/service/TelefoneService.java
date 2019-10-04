package br.com.ctis.hackathon.service;

import br.com.ctis.hackathon.dto.PessoaDTO;
import br.com.ctis.hackathon.persistence.model.Telefone;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TelefoneService {
    /**
     * Listar todos os telefones ligados à uma Pessoa
     *
     * @param id - ID da Pessoa
     * @return {@link List <Telefone>} - Lista com telefones ligados ao id informado
     */
    List<Telefone> listarTelefonesDePessoaComId(Long id);

    /**
     * Cadastrar um novo Telefone no sistema
     *
     * @param telefone - Telefone a ser cadastrado
     */
    void cadastrar(Telefone telefone);

    /**
     * Recuperar Telefone pelo Id
     *
     * @param id - Identificado do Telefone na base de dados
     *
     * @return {@link Telefone} - Representa um Telefone cadastrado no sistema
     */
    Telefone buscarTelefonePorId(Long id);
}
