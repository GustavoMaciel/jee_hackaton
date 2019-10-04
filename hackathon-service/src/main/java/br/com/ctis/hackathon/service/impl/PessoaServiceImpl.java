package br.com.ctis.hackathon.service.impl;

import br.com.ctis.hackathon.dto.PessoaDTO;
import br.com.ctis.hackathon.enumeration.MensagemEnum;
import br.com.ctis.hackathon.exception.DAOException;
import br.com.ctis.hackathon.exception.NegocioException;
import br.com.ctis.hackathon.exception.RegistroNaoEncontradoException;
import br.com.ctis.hackathon.persistence.dao.PessoaDAO;
import br.com.ctis.hackathon.persistence.model.Pessoa;
import br.com.ctis.hackathon.persistence.model.Telefone;
import br.com.ctis.hackathon.service.PessoaService;
import br.com.ctis.hackathon.service.TelefoneService;
import br.com.ctis.hackathon.util.MensagemUtil;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PessoaServiceImpl extends GenericServiceImpl<Long, Pessoa> implements PessoaService {

    @EJB
    private PessoaDAO pessoaDAO;
    @EJB
    private TelefoneService telefoneService;

    @Override
    public List<Pessoa> listar() {
        try {
            return pessoaDAO.listarTodos();
        } catch (DAOException e){
            throw new NegocioException(MensagemUtil.getMessage(MensagemEnum.MSG001));
        }
    }

    @Override
    public void cadastrar(PessoaDTO pessoaDTO) {
        Pessoa pessoa = mapper(pessoaDTO);
        this.pessoaDAO.gravar(pessoa);
        for(Telefone i: pessoaDTO.getTelefones()){
            i.setPessoa(pessoa);
            this.telefoneService.cadastrar(i);
        }
    }

    @Override
    public Pessoa buscarPessoaPorId(Long id) {
        try {
            return pessoaDAO.buscarPorId(id);
        } catch (RegistroNaoEncontradoException e){
            throw new NegocioException("Pessoa com id: " + id + " não encontrada.");
        } catch (DAOException e){
            throw new NegocioException(MensagemUtil.getMessage(MensagemEnum.MSG001));
        }
    }

    /**
     * Converter PessoaDTO para entidade Pessoa
     *
     * @param pessoaDTO - DTO que será convertido
     *
     * @return {@link Pessoa} - Entidade Pessoa
     */
    private Pessoa mapper(PessoaDTO pessoaDTO) {

        Pessoa pessoa = new Pessoa();
        pessoa.setCpf(pessoaDTO.getCpf());
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setSobrenome(pessoaDTO.getSobrenome());
        pessoa.setEmail(pessoaDTO.getEmail());

        return pessoa;

    }
}
