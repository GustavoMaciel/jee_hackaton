package br.com.ctis.hackathon.persistence.dao;

import br.com.ctis.hackathon.exception.DAOException;
import br.com.ctis.hackathon.exception.RegistroNaoEncontradoException;
import br.com.ctis.hackathon.persistence.model.Pessoa;

import java.util.List;

public interface PessoaDAO extends GenericDAO<Long, Pessoa> {
    List<Pessoa> listarTodos() throws DAOException;
    Pessoa buscarPorId(Long id) throws RegistroNaoEncontradoException, DAOException;
}
