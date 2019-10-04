package br.com.ctis.hackathon.endpoint.impl;

import br.com.ctis.hackathon.dto.MensagemRetornoDTO;
import br.com.ctis.hackathon.dto.PessoaDTO;
import br.com.ctis.hackathon.endpoint.PessoaEndPoint;
import br.com.ctis.hackathon.persistence.model.Pessoa;
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

    @Override
    public Response cadastrar(PessoaDTO pessoaDTO) {
        pessoaService.cadastrar(pessoaDTO);
        return Response.status(Response.Status.CREATED).entity(
                new MensagemRetornoDTO("Pessoa cadastrada com sucesso!")
        ).build();
    }

    @Override
    public Response buscarPessoaPorId(Long id) {
        return Response.status(Response.Status.OK).entity(pessoaService.buscarPessoaPorId(id)).build();
    }

    private PessoaDTO toDTO(Pessoa p){
        PessoaDTO dto = new PessoaDTO();
        dto.setCpf(p.getCpf());
        dto.setEmail(p.getEmail());
        dto.setNome(p.getNome());
        dto.setSobrenome(p.getSobrenome());
        dto.setTelefones(
                telefoneService.listarTelefonesDePessoaComId(p.getId())
        );
        return dto;
    }
}
