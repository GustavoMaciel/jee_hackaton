package br.com.ctis.hackathon.persistence.dao.impl;

import br.com.ctis.hackathon.exception.DAOException;
import br.com.ctis.hackathon.exception.RegistroNaoEncontradoException;
import br.com.ctis.hackathon.persistence.dao.TelefoneDAO;
import br.com.ctis.hackathon.persistence.model.Telefone;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class TelefoneDAOImpl extends GenericDAOImpl<Long, Telefone> implements TelefoneDAO {
    @Override
    public List<Telefone> listarTelefonesDePessoaComId(Long id) throws DAOException {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(" SELECT e FROM Telefone e").append(" WHERE e.pessoaId =:id");
        TypedQuery<Telefone> query = getEntityManager().createQuery(strBuilder.toString(), Telefone.class);
        query.setParameter("id", id);
        try {
            return query.getResultList();
        } catch (PersistenceException e){
            throw new DAOException();
        }
    }

    @Override
    public Telefone buscarPorId(Long id) throws RegistroNaoEncontradoException, DAOException {

        TypedQuery<Telefone> query = getEntityManager().createQuery(" SELECT e FROM Telefone e" + " WHERE e.id =:id ", Telefone.class);
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
