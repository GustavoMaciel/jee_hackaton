package br.com.ctis.hackathon.persistence.dao.impl;

import br.com.ctis.hackathon.exception.DAOException;
import br.com.ctis.hackathon.exception.RegistroNaoEncontradoException;
import br.com.ctis.hackathon.persistence.dao.EmpresaDAO;
import br.com.ctis.hackathon.persistence.model.Empresa;

import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class EmpresaDAOImpl extends GenericDAOImpl<Long, Empresa> implements EmpresaDAO {

    @Override
    protected StringBuilder getSearchedStringBuilder(String searchName, String quickStart){
        StringBuilder strBuilder = new StringBuilder(quickStart);
        if(searchName != null){
            strBuilder.append(" WHERE e.nomeFantasia LIKE CONCAT('%', :nome, '%') ");
        }
        return strBuilder;
    }

    @Override
    protected Query criarListarTodosQuery(String search) {
        StringBuilder strBuilder = getSearchedStringBuilder(search, "SELECT e FROM Empresa e");
        TypedQuery<Empresa> query = getEntityManager().createQuery(strBuilder.toString(), Empresa.class);

        if(search != null){
            query.setParameter("nomeFantasia", search);
        }
        return query;
    }

    @Override
    protected Query criarBuscarPorIdQuery(Long id) {
        TypedQuery<Empresa> query = getEntityManager().createQuery(" SELECT e FROM Empresa e" + " WHERE e.id =:id ", Empresa.class);
        query.setParameter("id", id);
        return  query;
    }

    @Override
    public Long getTotalItems(String searchName) {
        return null;
    }
}
