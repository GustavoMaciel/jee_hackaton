package br.com.ctis.hackathon.endpoint.impl;

import br.com.ctis.hackathon.dto.EmpresaDTO;
import br.com.ctis.hackathon.dto.EnderecoDTO;
import br.com.ctis.hackathon.dto.MensagemRetornoDTO;
import br.com.ctis.hackathon.dto.PaginacaoDTO;
import br.com.ctis.hackathon.endpoint.EmpresaEndPoint;
import br.com.ctis.hackathon.exception.NegocioException;
import br.com.ctis.hackathon.persistence.model.Empresa;
import br.com.ctis.hackathon.persistence.model.Endereco;
import br.com.ctis.hackathon.service.EmpresaService;
import br.com.ctis.hackathon.service.EnderecoService;
import br.com.ctis.hackathon.util.PageDict;

import javax.ejb.EJB;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class EmpresaEndPointImpl implements EmpresaEndPoint {
    @EJB
    private EmpresaService empresaService;
    @EJB
    private EnderecoService enderecoService;

    @Override
    public Response listar(int pageNumber, int pageSize, String searchName) {
        List<EmpresaDTO> listaDeDTOS = new ArrayList<>();
        for(Empresa i: empresaService.listar(pageNumber, pageSize, searchName)){
            listaDeDTOS.add(this.toDTO(i));
        }

        PaginacaoDTO<EmpresaDTO> pagina = new PaginacaoDTO<>(pageNumber, pageSize);
        pagina.setItens(listaDeDTOS);
        pagina.setTotalResults(empresaService.getTotalItems(searchName));
        return Response.status(Response.Status.OK).entity(pagina).build();
    }

    private Response templatePostPut(EmpresaDTO empresaDTO, String action){
        StringBuilder stringBuilder = new StringBuilder();
        Empresa empresa = empresaService.cadastrar(empresaDTO);
        for(EnderecoDTO i: empresaDTO.getEnderecos()){
            try {
                this.enderecoService.cadastrar(i, empresa);
            } catch (Exception e){
                stringBuilder.append("\nErro ao cadastrar endereco: ").append(i.getRua()).append(" ")
                        .append(i.getEstado())
                        .append(" ")
                        .append(i.getNumero());
            }
        }

        String finalMessage = "Empresa " + action + " com ";
        if(stringBuilder.length() >= 1){
            finalMessage += "os seguintes erros:" + stringBuilder.toString();
        } else {
            finalMessage += "sucesso!";
        }
        return Response.status(Response.Status.CREATED).entity(new MensagemRetornoDTO(finalMessage)).build();
    }

    @Override
    public Response cadastrar(EmpresaDTO empresaDTO) {
        return this.templatePostPut(empresaDTO, "cadastrada");
    }

    private void deletarEnderecosForaDaNovaLista(List<EnderecoDTO> novosEnderecos, List<Endereco> enderecosAntigos){
        for(Endereco i: enderecosAntigos){
            boolean deletarAtual = true;
            for(EnderecoDTO j: novosEnderecos){
                if(i.getId().equals(j.getId())){
                    deletarAtual = false;
                    break;
                }
            }
            if(deletarAtual){
                enderecoService.deletar(i.getId());
            }
        }
    }
    private void enderecoChangesDealer(EmpresaDTO empresaDTO){
        List<Endereco> enderecosDaEmpresa = enderecoService.listarEnderecosDeEmpresaComId(empresaDTO.getId());
        for(EnderecoDTO end: empresaDTO.getEnderecos()){
            if(end.getId() != null) {
                Endereco endereco = enderecoService.buscarEnderecoPorId(end.getId());
                boolean estaNaLista = false;
                for(Endereco i: enderecosDaEmpresa){
                    if (i.getId().equals(endereco.getId())){
                        estaNaLista = true;
                        break;
                    }
                }
                if(!estaNaLista){
                    throw new NegocioException("Endereço na lista não pertence a empresa sendo atualizada");
                }
            }
        }
        this.deletarEnderecosForaDaNovaLista(empresaDTO.getEnderecos(), enderecosDaEmpresa);
    }

    @Override
    public Response atualizar(EmpresaDTO dto) {
        empresaService.buscarEmpresaPorId(dto.getId());
        this.enderecoChangesDealer(dto);
        return this.templatePostPut(dto, "atualizada");
    }

    @Override
    public Response buscarEmpresaPorId(Long id) {
        return Response.status(Response.Status.OK)
                .entity(toDTO(empresaService.buscarEmpresaPorId(id)))
                .build();
    }

    @Override
    public Response deletar(Long id) {
        empresaService.deletar(id);
        return Response.status(Response.Status.OK).entity(new MensagemRetornoDTO("Empresa deletada com sucesso!"))
                .build();
    }


    private EmpresaDTO toDTO(Empresa emp){
        EmpresaDTO dto = new EmpresaDTO();
        dto.setId(emp.getId());
        dto.setVisao(emp.getVisao());
        dto.setMissao(emp.getMissao());
        dto.setNomeFantasia(emp.getNomeFantasia());
        dto.setCnpj(emp.getCnpj());

        List<EnderecoDTO> enderecoDTOList = new ArrayList<>();
        for (Endereco i: emp.getEnderecos()) {
            enderecoDTOList.add(toDTO(i));
        }
        dto.setEnderecos(enderecoDTOList);
        return dto;
    }

    private EnderecoDTO toDTO(Endereco end){
        EnderecoDTO dto = new EnderecoDTO();
        dto.setId(end.getId());
        dto.setBairro(end.getBairro());
        dto.setCidade(end.getCidade());
        dto.setNumero(end.getNumero());
        dto.setEstado(end.getEstado());
        dto.setRua(end.getRua());
        return dto;
    }
}
