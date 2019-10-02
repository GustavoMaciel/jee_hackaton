package br.com.ctis.hackathon.persistence.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import br.com.ctis.hackathon.exception.DAOException;
import br.com.ctis.hackathon.exception.RegistroNaoEncontradoException;
import br.com.ctis.hackathon.persistence.dao.EchoDAO;
import br.com.ctis.hackathon.persistence.model.Echo;

@Stateless
public class EchoDAOImpl extends GenericDAOImpl<Long, Echo> implements EchoDAO {

	@Override
	public List<Echo> listarTodos() throws DAOException {

		TypedQuery<Echo> query = getEntityManager().createQuery("SELECT e FROM Echo e", Echo.class);

		try {
			return query.getResultList();
		} catch (PersistenceException e) {
			throw new DAOException();
		}

	}

	@Override
	public Echo buscarEchoPorId(Long id) throws RegistroNaoEncontradoException, DAOException {

		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT e FROM Echo e").append(" WHERE e.id =:id ");

		TypedQuery<Echo> query = getEntityManager().createQuery(builder.toString(), Echo.class);
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
