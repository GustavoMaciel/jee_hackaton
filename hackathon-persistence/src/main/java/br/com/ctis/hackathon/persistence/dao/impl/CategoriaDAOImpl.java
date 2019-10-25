package br.com.ctis.hackathon.persistence.dao.impl;

import br.com.ctis.hackathon.exception.DAOException;
import br.com.ctis.hackathon.exception.RegistroNaoEncontradoException;
import br.com.ctis.hackathon.persistence.dao.CategoriaDAO;
import br.com.ctis.hackathon.persistence.model.Categoria;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class CategoriaDAOImpl extends GenericDAOImpl<Long, Categoria> implements CategoriaDAO {
    @Override
    public Categoria buscarPorId(Long id) throws RegistroNaoEncontradoException, DAOException {
        TypedQuery<Categoria> query = getEntityManager().createQuery(" SELECT e FROM Categoria e" +
                " WHERE e.id =:id ", Categoria.class);
        query.setParameter("id", id);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new RegistroNaoEncontradoException();
        } catch (PersistenceException e) {
            throw new DAOException();
        }
    }
}
