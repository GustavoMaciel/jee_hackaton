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
    protected StringBuilder getSearchedStringBuilder(String searchName, String quickStart){
        StringBuilder strBuilder = new StringBuilder(quickStart);
        if(searchName != null){
            strBuilder.append(" WHERE e.nome LIKE CONCAT('%', :nome, '%') OR e.sobrenome LIKE CONCAT('%', :nome, '%') ");
        }
        return strBuilder;
    }
    @Override
    protected Query criarListarTodosQuery(String search) {
        StringBuilder strBuilder = getSearchedStringBuilder(search, "SELECT e FROM Pessoa e");
        TypedQuery<Pessoa> query = getEntityManager().createQuery(strBuilder.toString(), Pessoa.class);

        if(search != null){
            query.setParameter("nome", search);
        }
        return query;
    }

    @Override
    protected Query criarBuscarPorIdQuery(Long id) {
        TypedQuery<Pessoa> query = getEntityManager().createQuery(" SELECT e FROM Pessoa e" + " WHERE e.id =:id ", Pessoa.class);
        query.setParameter("id", id);
        return  query;
    }

    @Override
    public Long getTotalItems(String searchName) {
        StringBuilder stringBuilder = getSearchedStringBuilder(searchName, "SELECT count(e.id) FROM Pessoa e");
        Query queryTotal = getEntityManager().createQuery(stringBuilder.toString());
        if(searchName != null){
            queryTotal.setParameter("nome", searchName);
        }
        return (long) queryTotal.getSingleResult();
    }
}
