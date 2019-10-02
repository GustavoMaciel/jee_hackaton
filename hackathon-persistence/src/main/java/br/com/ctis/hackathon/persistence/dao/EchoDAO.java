package br.com.ctis.hackathon.persistence.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.ctis.hackathon.exception.DAOException;
import br.com.ctis.hackathon.exception.RegistroNaoEncontradoException;
import br.com.ctis.hackathon.persistence.model.Echo;

@Local
public interface EchoDAO extends GenericDAO<Long, Echo> {

	List<Echo> listarTodos() throws DAOException;
	
	Echo buscarEchoPorId(Long id) throws RegistroNaoEncontradoException, DAOException;
}
