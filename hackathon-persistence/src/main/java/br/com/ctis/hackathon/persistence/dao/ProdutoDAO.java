package br.com.ctis.hackathon.persistence.dao;

import br.com.ctis.hackathon.exception.DAOException;
import br.com.ctis.hackathon.exception.RegistroNaoEncontradoException;
import br.com.ctis.hackathon.persistence.model.Produto;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProdutoDAO extends GenericDAO<Long, Produto> {
    List<Produto> listarObjetosPageados(int pageNumber, int pageSize, String search) throws DAOException;
    Produto buscarPorId(Long id) throws RegistroNaoEncontradoException, DAOException;
    Long getTotalItems(String searchName);
}
