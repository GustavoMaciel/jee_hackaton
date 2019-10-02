package br.com.ctis.hackathon.persistence.dao;

import br.com.ctis.hackathon.exception.DAOException;
import br.com.ctis.hackathon.exception.RegistroNaoEncontradoException;
import br.com.ctis.hackathon.persistence.model.Telefone;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TelefoneDAO extends GenericDAO<Long, Telefone> {
    List<Telefone> listarTelefonesDePessoaComId(Long id) throws DAOException;
    Telefone buscarPorId(Long id) throws RegistroNaoEncontradoException, DAOException;
}
