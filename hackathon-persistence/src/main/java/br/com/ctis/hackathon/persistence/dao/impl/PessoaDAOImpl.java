package br.com.ctis.hackathon.persistence.dao.impl;

import br.com.ctis.hackathon.exception.DAOException;
import br.com.ctis.hackathon.exception.RegistroNaoEncontradoException;
import br.com.ctis.hackathon.persistence.dao.PessoaDAO;
import br.com.ctis.hackathon.persistence.model.Pessoa;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

public class PessoaDAOImpl extends GenericDAOImpl<Long, Pessoa> implements PessoaDAO {

    @Override
    public List<Pessoa> listarTodos() throws DAOException {
        TypedQuery<Pessoa> query = getEntityManager().createQuery("SELECT e FROM Pessoa e", Pessoa.class);

        try {
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new DAOException();
        }
    }

    @Override
    public Pessoa buscarPorId(Long id) throws RegistroNaoEncontradoException, DAOException {
        StringBuilder builder = new StringBuilder();
        builder.append(" SELECT e FROM Pessoa e").append(" WHERE e.id =:id ");

        TypedQuery<Pessoa> query = getEntityManager().createQuery(builder.toString(), Pessoa.class);
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
