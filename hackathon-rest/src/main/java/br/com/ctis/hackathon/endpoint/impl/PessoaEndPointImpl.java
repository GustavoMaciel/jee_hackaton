package br.com.ctis.hackathon.endpoint.impl;

import br.com.ctis.hackathon.dto.MensagemRetornoDTO;
import br.com.ctis.hackathon.dto.PessoaDTO;
import br.com.ctis.hackathon.dto.TelefoneDTO;
import br.com.ctis.hackathon.endpoint.PessoaEndPoint;
import br.com.ctis.hackathon.exception.DAOException;
import br.com.ctis.hackathon.persistence.model.Pessoa;
import br.com.ctis.hackathon.persistence.model.Telefone;
import br.com.ctis.hackathon.service.PessoaService;
import br.com.ctis.hackathon.service.TelefoneService;

import javax.ejb.EJB;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class PessoaEndPointImpl implements PessoaEndPoint {
    @EJB
    private PessoaService pessoaService;
    @EJB
    private TelefoneService telefoneService;

    @Override
    public Response listar() {
        List<PessoaDTO> listaDeDTOS = new ArrayList<>();
        for(Pessoa i: pessoaService.listar()){
            listaDeDTOS.add(this.toDTO(i));
        }
        return Response.status(Response.Status.OK).entity(listaDeDTOS).build();
    }

    private Response templatePostPut(PessoaDTO pessoaDTO, String action){
        StringBuilder stringBuilder = new StringBuilder();
        Pessoa pessoa = pessoaService.cadastrar(pessoaDTO);
        for(TelefoneDTO i: pessoaDTO.getTelefones()){
            try {
                this.telefoneService.cadastrar(i, pessoa);
            } catch (Exception e){
                stringBuilder.append("\nErro ao cadastrar telefone: ").append(i.getCodigoPais()).append(i.getDdd())
                        .append(i.getNumero());
            }
        }

        String finalMessage = "Pessoa " + action + " com ";
        if(stringBuilder.length() >= 1){
            finalMessage += "os seguintes erros:" + stringBuilder.toString();
        } else {
            finalMessage += "sucesso!";
        }
        return Response.status(Response.Status.CREATED).entity(new MensagemRetornoDTO(finalMessage)).build();
    }

    @Override
    public Response cadastrar(PessoaDTO pessoaDTO) {
        return this.templatePostPut(pessoaDTO, "cadastrada");
    }

    private void telefoneChangesDealer(PessoaDTO pessoaDTO){
        for(Telefone i: this.telefoneService.listarTelefonesDePessoaComId(pessoaDTO.getId())) {
            boolean deletarAtual = true;
            for(TelefoneDTO j: pessoaDTO.getTelefones()){
                if (i.getId().equals(j.getId())){
                    deletarAtual = false;
                    break;
                }
            }
            if (deletarAtual){
                telefoneService.deletar(i.getId());
            }
        }
    }

    @Override
    public Response atualizar(PessoaDTO pessoaDTO) {
        this.telefoneChangesDealer(pessoaDTO);
        return this.templatePostPut(pessoaDTO, "atualizada");
    }

    @Override
    public Response buscarPessoaPorId(Long id) {
        return Response.status(Response.Status.OK)
                .entity(toDTO(pessoaService.buscarPessoaPorId(id)))
                .build();
    }

    @Override
    public Response deletar(Long id) {
            pessoaService.deletar(id);
            return Response.status(Response.Status.OK).entity(new MensagemRetornoDTO("Pessoa deletada com sucesso!"))
                    .build();
    }


    private PessoaDTO toDTO(Pessoa p){
        PessoaDTO dto = new PessoaDTO();
        dto.setId(p.getId());
        dto.setCpf(p.getCpf());
        dto.setEmail(p.getEmail());
        dto.setNome(p.getNome());
        dto.setSobrenome(p.getSobrenome());

        List<TelefoneDTO> telefoneDTOList = new ArrayList<>();
        for (Telefone i: p.getTelefones()) {
            telefoneDTOList.add(toDTO(i));
        }
        dto.setTelefones(telefoneDTOList);
        return dto;
    }

    private TelefoneDTO toDTO(Telefone t){
        TelefoneDTO dto = new TelefoneDTO();
        dto.setId(t.getId());
        dto.setCodigoPais(t.getCodigoPais());
        dto.setDdd(t.getDdd());
        dto.setNumero(t.getNumero());
        return dto;
    }
}
