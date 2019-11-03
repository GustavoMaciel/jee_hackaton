package br.com.ctis.hackathon.service;

import br.com.ctis.hackathon.dto.ProdutoDTO;
import br.com.ctis.hackathon.persistence.model.Categoria;
import br.com.ctis.hackathon.persistence.model.Produto;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProdutoService {
    /**
     * Listar todas as Produtos cadastradas no sistema
     *
     * @return {@link List <Produto>} - Lista com Produtos cadastradas
     */
    List<Produto> listar(int pageNumber, int pageSize, String search);

    /**
     * Cadastrar um novo Produto no sistema
     *
     * @param produtoDTO - Parâmetro de entrada para cadastro do Produto
     * @param categorias - Lista com categorias a serem linkadas ao Produto
     */
    Produto cadastrar(ProdutoDTO produtoDTO, List<Categoria> categorias);

    /**
     * Recuperar Produto pelo Id
     *
     * @param id - Identificado do Produto na base de dados
     *
     * @return {@link Produto} - Representa uma Produto cadastrado no sistema
     */
    Produto buscarProdutoPorId(Long id);

    /**
     * Deleta Produto pelo Id
     *
     * @param id - Identificado do Produto na base de dados
     */
    void deletar(Long id);

    /**
     * Retorna o número de Produtos cadastradas
     * @return {@link Long} Número total de produtos cadastradas no sistema
     */
    Long getTotalItems(String searchName);
}
