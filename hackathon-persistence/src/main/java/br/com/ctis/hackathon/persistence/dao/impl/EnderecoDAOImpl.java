package br.com.ctis.hackathon.persistence.dao.impl;

import br.com.ctis.hackathon.exception.DAOException;
import br.com.ctis.hackathon.exception.RegistroNaoEncontradoException;
import br.com.ctis.hackathon.persistence.dao.EnderecoDAO;
import br.com.ctis.hackathon.persistence.model.Endereco;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class EnderecoDAOImpl extends GenericDAOImpl<Long, Endereco> implements EnderecoDAO {
    @Override
    public List<Endereco> listarEnderecosDeEmpresaComId(Long id) throws DAOException {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(" SELECT e FROM Endereco e").append(" WHERE e.empresaId =:id");
        TypedQuery<Endereco> query = getEntityManager().createQuery(strBuilder.toString(), Endereco.class);
        query.setParameter("id", id);
        try {
            return query.getResultList();
        } catch (PersistenceException e){
            throw new DAOException();
        }
    }

    @Override
    public Endereco buscarPorId(Long id) throws RegistroNaoEncontradoException, DAOException {

        TypedQuery<Endereco> query = getEntityManager()
                .createQuery(" SELECT e FROM Endereco e" + " WHERE e.id =:id ", Endereco.class);
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
