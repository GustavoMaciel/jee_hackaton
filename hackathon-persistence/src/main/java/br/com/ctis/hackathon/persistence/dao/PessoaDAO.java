package br.com.ctis.hackathon.persistence.dao;

import br.com.ctis.hackathon.exception.DAOException;
import br.com.ctis.hackathon.exception.RegistroNaoEncontradoException;
import br.com.ctis.hackathon.persistence.model.Pessoa;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PessoaDAO extends GenericDAO<Long, Pessoa> {
    List<Pessoa> listarObjetosPageados(int pageNumber, int pageSize, String search) throws DAOException;
    Pessoa buscarPorId(Long id) throws RegistroNaoEncontradoException, DAOException;
    Long getTotalItems(String searchName);
}
