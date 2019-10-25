package br.com.ctis.hackathon.service;

import br.com.ctis.hackathon.dto.EnderecoDTO;
import br.com.ctis.hackathon.persistence.model.Empresa;
import br.com.ctis.hackathon.persistence.model.Endereco;

import javax.ejb.Local;
import java.util.List;

@Local
public interface EnderecoService {
    /**
     * Listar todos os enderecos ligados à uma Empresa
     *
     * @param id - ID da Empresa
     * @return {@link List <Endereco>} - Lista com telefones ligados ao id informado
     */
    List<Endereco> listarEnderecosDePessoaComId(Long id);

    /**
     * Cadastrar um novo Endereco no sistema
     *
     * @param telefone - Endereco a ser cadastrado
     */
    Endereco cadastrar(EnderecoDTO telefone, Empresa emp);

    /**
     * Recuperar Endereco pelo Id
     *
     * @param id - Identificado do Endereco na base de dados
     *
     * @return {@link Endereco} - Representa um Endereco cadastrado no sistema
     */
    Endereco buscarEnderecoPorId(Long id);

    /**
     * Deletar um Endereco
     *
     * @param id - ID do Endereco à ser deletado
     */
    void deletar(Long id);
}
