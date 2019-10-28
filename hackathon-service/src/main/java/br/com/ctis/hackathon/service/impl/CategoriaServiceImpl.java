package br.com.ctis.hackathon.service.impl;

import br.com.ctis.hackathon.dto.CategoriaDTO;
import br.com.ctis.hackathon.enumeration.MensagemEnum;
import br.com.ctis.hackathon.exception.DAOException;
import br.com.ctis.hackathon.exception.NegocioException;
import br.com.ctis.hackathon.exception.RegistroNaoEncontradoException;
import br.com.ctis.hackathon.persistence.dao.CategoriaDAO;
import br.com.ctis.hackathon.persistence.model.Categoria;
import br.com.ctis.hackathon.persistence.model.Produto;
import br.com.ctis.hackathon.service.CategoriaService;
import br.com.ctis.hackathon.util.MensagemUtil;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CategoriaServiceImpl extends GenericServiceImpl<Long, Categoria> implements CategoriaService {
    @EJB
    private CategoriaDAO categoriaDAO;

    @Override
    public Categoria cadastrar(CategoriaDTO categoria) {
        return this.categoriaDAO.gravar(toModel(categoria));
    }

    @Override
    public Categoria buscarCategoriaPorId(Long id) {
        try {
            return categoriaDAO.buscarPorId(id);
        } catch (RegistroNaoEncontradoException e){
            throw new NegocioException("Categoria com id: " + id + " não encontrada.");
        } catch (DAOException e){
            throw new NegocioException(MensagemUtil.getMessage(MensagemEnum.MSG001));
        }
    }

    @Override
    public void deletar(Long id) {
        categoriaDAO.excluir(id);
    }

    private Categoria toModel(CategoriaDTO cat){
        Categoria i = new Categoria();
        if(cat.getId() != null) {
            i.setId(cat.getId());
        }
        i.setCodigo(cat.getCodigo());
        i.setNome(cat.getNome());
        return i;
    }
}
