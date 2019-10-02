package br.com.ctis.hackathon.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.ctis.hackathon.dto.EchoDTO;
import br.com.ctis.hackathon.enumeration.MensagemEnum;
import br.com.ctis.hackathon.exception.DAOException;
import br.com.ctis.hackathon.exception.NegocioException;
import br.com.ctis.hackathon.exception.RegistroNaoEncontradoException;
import br.com.ctis.hackathon.persistence.dao.EchoDAO;
import br.com.ctis.hackathon.persistence.model.Echo;
import br.com.ctis.hackathon.service.EchoService;
import br.com.ctis.hackathon.util.MensagemUtil;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class EchoServiceImpl extends GenericServiceImpl<Long, Echo> implements EchoService {

	private static final long serialVersionUID = -4974991595955674384L;

	@EJB
	private EchoDAO echoDAO;

	@Override
	public List<Echo> listarTodos() {

		try {
			return echoDAO.listarTodos();
		} catch (DAOException e) {
			throw new NegocioException(MensagemUtil.getMessage(MensagemEnum.MSG001));
		}
		
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public void cadastrar(EchoDTO echoDTO) {

		echoDAO.gravar(mapper(echoDTO));

	}

	@Override
	public Echo buscarEchoPorId(Long id) {

		try {
			return echoDAO.buscarEchoPorId(id);
		} catch (RegistroNaoEncontradoException e) {
			throw new NegocioException("Echo com Identificador " + id + " não encontrado");
		} catch (DAOException e) {
			throw new NegocioException(MensagemUtil.getMessage(MensagemEnum.MSG001));
		}

	}

	/**
	 * Converter EchoDTO para entidade Echo
	 * 
	 * @param echoDTO - DTO que será convertido
	 * 
	 * @return {@link Echo} - Entidade Echo
	 */
	private Echo mapper(EchoDTO echoDTO) {

		Echo echo = new Echo();
		echo.setCodigo(echoDTO.getCodigo());
		echo.setNome(echoDTO.getNome());

		return echo;

	}

}
