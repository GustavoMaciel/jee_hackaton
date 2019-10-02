package br.com.ctis.hackathon.endpoint.impl;

import br.com.ctis.hackathon.dto.MensagemRetornoDTO;
import br.com.ctis.hackathon.dto.PessoaDTO;
import br.com.ctis.hackathon.endpoint.PessoaEndPoint;
import br.com.ctis.hackathon.service.PessoaService;

import javax.ejb.EJB;
import javax.ws.rs.core.Response;

public class PessoaEndPointImpl implements PessoaEndPoint {
    @EJB
    private PessoaService pessoaService;

    @Override
    public Response listar() {
        return Response.status(Response.Status.OK).entity(pessoaService.listar()).build();
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
}
