package br.com.ctis.hackathon.service;

import java.util.List;

import javax.ejb.Local;

import br.com.ctis.hackathon.dto.EchoDTO;
import br.com.ctis.hackathon.persistence.model.Echo;

@Local
public interface EchoService extends GenericService<Long, Echo> {
	
	/**
	 * Listar todos os Echos cadastrados no sistema
	 * 
	 * @return {@link List<Echo>} - Lista com Echos cadastrados
	 */
	List<Echo> listarTodos();

	/**
	 * Cadastrar um novo Echo no sistema
	 * 
	 * @param echoDTO - Parâmetro de entrada para cadastro do Echo
	 */
	void cadastrar(EchoDTO echoDTO);
	
	/**
	 * Recuperar Echo pelo Id
	 * 
	 * @param id - Identificado do Echo na base de dados
	 * 
	 * @return {@link Echo} - Representa um Echo cadastrado no sistema
	 */
	Echo buscarEchoPorId(Long id);
}
