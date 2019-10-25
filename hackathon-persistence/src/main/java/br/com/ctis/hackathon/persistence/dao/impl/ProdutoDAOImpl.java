package br.com.ctis.hackathon.persistence.dao.impl;

import br.com.ctis.hackathon.persistence.dao.ProdutoDAO;
import br.com.ctis.hackathon.persistence.model.Produto;

import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class ProdutoDAOImpl extends GenericDAOImpl<Long, Produto> implements ProdutoDAO {
    @Override
    protected StringBuilder getSearchedStringBuilder(String searchName, String quickStart){
        StringBuilder strBuilder = new StringBuilder(quickStart);
        if(searchName != null){
            strBuilder.append(" WHERE e.nome LIKE CONCAT('%', :nome, '%') ");
        }
        return strBuilder;
    }
    @Override
    protected Query criarListarTodosQuery(String search) {
        StringBuilder strBuilder = getSearchedStringBuilder(search, "SELECT e FROM Produto e");
        TypedQuery<Produto> query = getEntityManager().createQuery(strBuilder.toString(), Produto.class);

        if(search != null){
            query.setParameter("nome", search);
        }
        return query;
    }

    @Override
    protected Query criarBuscarPorIdQuery(Long id) {
        TypedQuery<Produto> query = getEntityManager().createQuery(" SELECT e FROM Produto e" + " WHERE e.id =:id ",
                Produto.class);
        query.setParameter("id", id);
        return  query;
    }

    @Override
    public Long getTotalItems(String searchName) {
        StringBuilder stringBuilder = getSearchedStringBuilder(searchName, "SELECT count(e.id) FROM Produto e");
        Query queryTotal = getEntityManager().createQuery(stringBuilder.toString());
        if(searchName != null){
            queryTotal.setParameter("nome", searchName);
        }
        return (long) queryTotal.getSingleResult();
    }
}
