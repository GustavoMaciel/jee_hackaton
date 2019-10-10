package br.com.ctis.hackathon.persistence.dao.impl;

import br.com.ctis.hackathon.exception.DAOException;
import br.com.ctis.hackathon.exception.RegistroNaoEncontradoException;
import br.com.ctis.hackathon.persistence.dao.PessoaDAO;
import br.com.ctis.hackathon.persistence.model.Pessoa;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class PessoaDAOImpl extends GenericDAOImpl<Long, Pessoa> implements PessoaDAO {

    @Override
    public List<Pessoa> listarTodos(int pageNumber, int pageSize, String search) throws DAOException {
        StringBuilder strBuilder = new StringBuilder("SELECT e FROM Pessoa e");
        if(search != null){
            strBuilder.append(" WHERE e.nome LIKE '%nome%' OR WHERE e.sobrenome LIKE '%nome%'");
        }
        TypedQuery<Pessoa> query = getEntityManager().createQuery(strBuilder.toString(), Pessoa.class);
        if(search != null){
            query.setParameter("nome", search);
        }

        query.setFirstResult((pageNumber-1) * pageSize);
        query.setMaxResults(pageSize);

        try {
            return query.getResultList();
        } catch (PersistenceException e) {
            throw new DAOException();
        }
    }

    @Override
    public Pessoa buscarPorId(Long id) throws RegistroNaoEncontradoException, DAOException {

        TypedQuery<Pessoa> query = getEntityManager().createQuery(" SELECT e FROM Pessoa e" + " WHERE e.id =:id ", Pessoa.class);
        query.setParameter("id", id);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new RegistroNaoEncontradoException();
        } catch (PersistenceException e) {
            throw new DAOException();
        }
    }

    @Override
    public Long getTotalItems() {
        Query queryTotal = getEntityManager().createQuery("SELECT count(e.id) FROM Pessoa e");
        return (long) queryTotal.getSingleResult();
    }
}
