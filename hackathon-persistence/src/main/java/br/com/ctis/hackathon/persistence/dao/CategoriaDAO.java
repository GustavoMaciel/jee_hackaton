package br.com.ctis.hackathon.persistence.dao;

import br.com.ctis.hackathon.exception.DAOException;
import br.com.ctis.hackathon.exception.RegistroNaoEncontradoException;
import br.com.ctis.hackathon.persistence.model.Categoria;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CategoriaDAO extends GenericDAO<Long, Categoria> {
    Categoria buscarPorId(Long id) throws RegistroNaoEncontradoException, DAOException;
}
