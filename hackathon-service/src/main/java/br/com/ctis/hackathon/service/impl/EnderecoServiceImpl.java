package br.com.ctis.hackathon.service.impl;

import br.com.ctis.hackathon.dto.EnderecoDTO;
import br.com.ctis.hackathon.enumeration.MensagemEnum;
import br.com.ctis.hackathon.exception.DAOException;
import br.com.ctis.hackathon.exception.NegocioException;
import br.com.ctis.hackathon.exception.RegistroNaoEncontradoException;
import br.com.ctis.hackathon.persistence.dao.EnderecoDAO;
import br.com.ctis.hackathon.persistence.model.Empresa;
import br.com.ctis.hackathon.persistence.model.Endereco;
import br.com.ctis.hackathon.service.EnderecoService;
import br.com.ctis.hackathon.util.MensagemUtil;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class EnderecoServiceImpl extends GenericServiceImpl<Long, Endereco> implements EnderecoService {
    @EJB
    private EnderecoDAO enderecoDAO;

    @Override
    public List<Endereco> listarEnderecosDeEmpresaComId(Long id) {
        try {
            return enderecoDAO.listarEnderecosDeEmpresaComId(id);
        } catch (DAOException e){
            throw new NegocioException(MensagemUtil.getMessage(MensagemEnum.MSG001));
        }
    }

    @Override
    public Endereco cadastrar(EnderecoDTO endereco, Empresa emp) {
        return this.enderecoDAO.gravar(toModel(endereco, emp));
    }

    @Override
    public Endereco buscarEnderecoPorId(Long id) {
        try {
            return enderecoDAO.buscarPorId(id);
        } catch (RegistroNaoEncontradoException e){
            throw new NegocioException("Endereço com id: " + id + " não encontrado.");
        } catch (DAOException e){
            throw new NegocioException(MensagemUtil.getMessage(MensagemEnum.MSG001));
        }
    }

    @Override
    public void deletar(Long id) {
        enderecoDAO.excluir(id);
    }

    private Endereco toModel(EnderecoDTO end, Empresa emp){
        Endereco i = new Endereco();
        if(end.getId() != null) {
            i.setId(end.getId());
        }
        i.setBairro(end.getBairro());
        i.setCidade(end.getCidade());
        i.setNumero(end.getNumero());
        i.setEstado(end.getEstado());
        i.setRua(end.getRua());
        i.setEmpresa(emp);
        i.setEmpresaId(emp.getId());
        return i;
    }
}
