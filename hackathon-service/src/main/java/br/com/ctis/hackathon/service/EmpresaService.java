package br.com.ctis.hackathon.service;

import br.com.ctis.hackathon.dto.EmpresaDTO;
import br.com.ctis.hackathon.persistence.model.Empresa;

import javax.ejb.Local;
import java.util.List;

@Local
public interface EmpresaService extends GenericService<Long, Empresa>{
    /**
     * Listar todas as Empresas cadastradas no sistema
     *
     * @return {@link List <Empresa>} - Lista com Empresas cadastradas
     */
    List<Empresa> listar(int pageNumber, int pageSize, String search);

    /**
     * Cadastrar uma nova Empresa no sistema
     *
     * @param empresaDTO - Parâmetro de entrada para cadastro da Empresa
     */
    Empresa cadastrar(EmpresaDTO empresaDTO);

    /**
     * Recuperar Empresa pelo Id
     *
     * @param id - Identificado da Empresa na base de dados
     *
     * @return {@link Empresa} - Representa uma Empresa cadastrada no sistema
     */
    Empresa buscarEmpresaPorId(Long id);

    /**
     * Deleta Empresa pelo Id
     *
     * @param id - Identificado da Empresa na base de dados
     */
    void deletar(Long id);

    /**
     * Retorna o número de Empresas cadastradas
     * @return {@link Long} Número total de empresas cadastradas no sistema
     */
    Long getTotalItems(String searchName);
}
