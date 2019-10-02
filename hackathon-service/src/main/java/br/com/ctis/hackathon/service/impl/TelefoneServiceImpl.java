package br.com.ctis.hackathon.service.impl;

import br.com.ctis.hackathon.persistence.model.Telefone;
import br.com.ctis.hackathon.service.TelefoneService;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class TelefoneServiceImpl extends GenericServiceImpl<Long, Telefone> implements TelefoneService {

    @Override
    public List<Telefone> listarTelefonesDePessoaComId(Long id) {
        return null;
    }

    @Override
    public void cadastrar(Telefone telefone) {

    }

    @Override
    public Telefone buscarPessoaPorId(Long id) {
        return null;
    }
}
