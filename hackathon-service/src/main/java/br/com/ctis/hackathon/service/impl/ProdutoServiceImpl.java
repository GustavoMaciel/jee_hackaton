package br.com.ctis.hackathon.service.impl;

import br.com.ctis.hackathon.dto.ProdutoDTO;
import br.com.ctis.hackathon.enumeration.MensagemEnum;
import br.com.ctis.hackathon.exception.DAOException;
import br.com.ctis.hackathon.exception.NegocioException;
import br.com.ctis.hackathon.exception.RegistroNaoEncontradoException;
import br.com.ctis.hackathon.persistence.dao.ProdutoDAO;
import br.com.ctis.hackathon.persistence.model.Categoria;
import br.com.ctis.hackathon.persistence.model.Produto;
import br.com.ctis.hackathon.service.ProdutoService;
import br.com.ctis.hackathon.util.MensagemUtil;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ProdutoServiceImpl extends GenericServiceImpl<Long, Produto> implements ProdutoService {
    @EJB
    private ProdutoDAO produtoDAO;

    @Override
    public List<Produto> listar(int pageNumber, int pageSize, String search) {
        try {
            return produtoDAO.listarObjetosPageados(pageNumber, pageSize, search);
        } catch (DAOException e){
            throw new NegocioException(MensagemUtil.getMessage(MensagemEnum.MSG001));
        }
    }

    @Override
    public Produto cadastrar(ProdutoDTO produtoDTO, List<Categoria> categorias) {
        return this.produtoDAO.gravar(toModel(produtoDTO, categorias));
    }

    @Override
    public Produto buscarProdutoPorId(Long id) {
        try {
            return produtoDAO.buscarPorId(id);
        } catch (RegistroNaoEncontradoException e){
            throw new NegocioException("Produto com id: " + id + " não encontrado.");
        } catch (DAOException e){
            throw new NegocioException(MensagemUtil.getMessage(MensagemEnum.MSG001));
        }
    }

    @Override
    public void deletar(Long id) {
        produtoDAO.excluir(id);
    }

    @Override
    public Long getTotalItems(String searchName) {
        return produtoDAO.getTotalItems(searchName);
    }

    /**
     * Converter ProdutoDTO para entidade Pessoa
     *
     * @param produtoDTO - DTO que será convertido
     * @param categorias - Categorias já com ID do banco
     *
     * @return {@link Produto} - Entidade Pessoa
     */
    private Produto toModel(ProdutoDTO produtoDTO, List<Categoria> categorias) {

        Produto produto = new Produto();
        if(produtoDTO.getId() != null) {
            produto.setId(produtoDTO.getId());
        }
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setNome(produtoDTO.getNome());
        produto.setValor(produtoDTO.getValor());
        produto.setCategorias(categorias);

        return produto;

    }
}
