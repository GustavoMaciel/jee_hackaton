package br.com.ctis.hackathon.persistence.dao;

import br.com.ctis.hackathon.exception.DAOException;
import br.com.ctis.hackathon.exception.RegistroNaoEncontradoException;
import br.com.ctis.hackathon.persistence.model.Empresa;

import javax.ejb.Local;
import java.util.List;

@Local
public interface EmpresaDAO extends GenericDAO<Long, Empresa> {
    List<Empresa> listarObjetosPageados(int pageNumber, int pageSize, String search) throws DAOException;
    Empresa buscarPorId(Long id) throws RegistroNaoEncontradoException, DAOException;
    Long getTotalItems(String searchName);
}
