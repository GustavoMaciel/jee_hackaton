package br.com.ctis.hackathon.persistence.dao;

import br.com.ctis.hackathon.exception.DAOException;
import br.com.ctis.hackathon.exception.RegistroNaoEncontradoException;
import br.com.ctis.hackathon.persistence.model.Endereco;

import javax.ejb.Local;
import java.util.List;

@Local
public interface EnderecoDAO extends GenericDAO<Long, Endereco>{

    List<Endereco> listarEnderecosDeEmpresaComId(Long id) throws DAOException;
    Endereco buscarPorId(Long id) throws RegistroNaoEncontradoException, DAOException;
}
