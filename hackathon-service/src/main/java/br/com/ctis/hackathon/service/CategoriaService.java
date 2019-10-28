package br.com.ctis.hackathon.service;

import br.com.ctis.hackathon.dto.CategoriaDTO;
import br.com.ctis.hackathon.persistence.model.Categoria;
import br.com.ctis.hackathon.persistence.model.Produto;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CategoriaService {

    /**
     * Cadastrar um novo Categoria no sistema
     *
     * @param categoria - Categoria a ser cadastrado
     */
    Categoria cadastrar(CategoriaDTO categoria);

    /**
     * Recuperar Categoria pelo Id
     *
     * @param id - Identificado do Categoria na base de dados
     *
     * @return {@link Categoria} - Representa um Categoria cadastrado no sistema
     */
    Categoria buscarCategoriaPorId(Long id);

    /**
     * Deletar um Categoria
     *
     * @param id - ID do Categoria à ser deletado
     */
    void deletar(Long id);
}
