package br.com.ctis.hackathon.service.impl;

import br.com.ctis.hackathon.dto.EmpresaDTO;
import br.com.ctis.hackathon.enumeration.MensagemEnum;
import br.com.ctis.hackathon.exception.DAOException;
import br.com.ctis.hackathon.exception.NegocioException;
import br.com.ctis.hackathon.exception.RegistroNaoEncontradoException;
import br.com.ctis.hackathon.persistence.dao.EmpresaDAO;
import br.com.ctis.hackathon.persistence.model.Empresa;
import br.com.ctis.hackathon.service.EmpresaService;
import br.com.ctis.hackathon.util.MensagemUtil;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class EmpresaServiceImpl extends GenericServiceImpl<Long, Empresa> implements EmpresaService {
    @EJB
    private EmpresaDAO empresaDAO;

    @Override
    public List<Empresa> listar(int pageNumber, int pageSize, String search) {
        try {
            return empresaDAO.listarObjetosPageados(pageNumber, pageSize, search);
        } catch (DAOException e){
            throw new NegocioException(MensagemUtil.getMessage(MensagemEnum.MSG001));
        }
    }

    @Override
    public Empresa cadastrar(EmpresaDTO empresaDTO) {
        return this.empresaDAO.gravar(toModel(empresaDTO));
    }

    @Override
    public Empresa buscarEmpresaPorId(Long id) {
        try {
            return empresaDAO.buscarPorId(id);
        } catch (RegistroNaoEncontradoException e){
            throw new NegocioException("Empresa com id: " + id + " não encontrada.");
        } catch (DAOException e){
            throw new NegocioException(MensagemUtil.getMessage(MensagemEnum.MSG001));
        }
    }

    @Override
    public void deletar(Long id) {
        empresaDAO.excluir(id);
    }

    @Override
    public Long getTotalItems(String searchName) {
        return empresaDAO.getTotalItems(searchName);
    }

    /**
     * Converter EmpresaDTO para entidade Empresa
     *
     * @param empresaDTO - DTO que será convertido
     *
     * @return {@link Empresa} - Entidade Empresa
     */
    private Empresa toModel(EmpresaDTO empresaDTO) {

        Empresa empresa = new Empresa();

        if(empresaDTO.getId() != null) {
            empresa.setId(empresaDTO.getId());
        }
        empresa.setCnpj(empresaDTO.getCnpj());
        empresa.setNomeFantasia(empresaDTO.getNomeFantasia());
        empresa.setMissao(empresaDTO.getMissao());
        empresa.setVisao(empresaDTO.getVisao());

        return empresa;

    }
}
