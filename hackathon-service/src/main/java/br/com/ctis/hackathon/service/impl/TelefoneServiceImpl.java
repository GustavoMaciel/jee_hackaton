package br.com.ctis.hackathon.service.impl;

import br.com.ctis.hackathon.enumeration.MensagemEnum;
import br.com.ctis.hackathon.exception.DAOException;
import br.com.ctis.hackathon.exception.NegocioException;
import br.com.ctis.hackathon.exception.RegistroNaoEncontradoException;
import br.com.ctis.hackathon.persistence.dao.TelefoneDAO;
import br.com.ctis.hackathon.persistence.model.Telefone;
import br.com.ctis.hackathon.service.TelefoneService;
import br.com.ctis.hackathon.util.MensagemUtil;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class TelefoneServiceImpl extends GenericServiceImpl<Long, Telefone> implements TelefoneService {
    @EJB
    private TelefoneDAO telefoneDAO;

    @Override
    public List<Telefone> listarTelefonesDePessoaComId(Long id) {
        try {
            return telefoneDAO.listarTelefonesDePessoaComId(id);
        } catch (DAOException e){
            throw new NegocioException(MensagemUtil.getMessage(MensagemEnum.MSG001));
        }
    }

    @Override
    public void cadastrar(Telefone telefone) {
        this.telefoneDAO.gravar(telefone);
    }

    @Override
    public Telefone buscarTelefonePorId(Long id) {
        try {
            return telefoneDAO.buscarPorId(id);
        } catch (RegistroNaoEncontradoException e){
            throw new NegocioException("Telefone com id: " + id + " não encontrado.");
        } catch (DAOException e){
            throw new NegocioException(MensagemUtil.getMessage(MensagemEnum.MSG001));
        }
    }
}