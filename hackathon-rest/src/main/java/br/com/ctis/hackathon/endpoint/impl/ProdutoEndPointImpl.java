package br.com.ctis.hackathon.endpoint.impl;

import br.com.ctis.hackathon.dto.CategoriaDTO;
import br.com.ctis.hackathon.dto.MensagemRetornoDTO;
import br.com.ctis.hackathon.dto.PaginacaoDTO;
import br.com.ctis.hackathon.dto.ProdutoDTO;
import br.com.ctis.hackathon.endpoint.ProdutoEndPoint;
import br.com.ctis.hackathon.persistence.model.Categoria;
import br.com.ctis.hackathon.persistence.model.Produto;
import br.com.ctis.hackathon.service.CategoriaService;
import br.com.ctis.hackathon.service.ProdutoService;
import br.com.ctis.hackathon.util.PageDict;

import javax.ejb.EJB;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class ProdutoEndPointImpl implements ProdutoEndPoint {
    @EJB
    private ProdutoService produtoService;
    @EJB
    private CategoriaService categoriaService;

    @Override
    public Response listar(PageDict pageDict) {
        List<ProdutoDTO> listaDeDTOS = new ArrayList<>();
        for(Produto i: produtoService.listar(pageDict.getPageNumber(), pageDict.getPageSize(), pageDict.getSearchName())){
            listaDeDTOS.add(this.toDTO(i));
        }

        PaginacaoDTO<ProdutoDTO> pagina = new PaginacaoDTO<>(pageDict.getPageNumber(), pageDict.getPageSize());
        pagina.setItens(listaDeDTOS);
        pagina.setTotalResults(produtoService.getTotalItems(pageDict.getSearchName()));
        return Response.status(Response.Status.OK).entity(pagina).build();
    }

    private Response templatePostPut(ProdutoDTO produtoDTO, String action){
        StringBuilder stringBuilder = new StringBuilder();
        List<Categoria> novasCategorias = new ArrayList<>();
        for(CategoriaDTO i: produtoDTO.getCategorias()){
            try {
                novasCategorias.add(this.categoriaService.buscarCategoriaPorId(i.getId()));
            } catch (Exception e){
                stringBuilder.append("\nErro ao cadastrar categoria com código: ").append(i.getCodigo());
            }
        }
        Produto produto = produtoService.cadastrar(produtoDTO, novasCategorias);

        String finalMessage = "Produto " + action + " com ";
        if(stringBuilder.length() >= 1){
            finalMessage += "os seguintes erros:" + stringBuilder.toString();
        } else {
            finalMessage += "sucesso!";
        }
        return Response.status(Response.Status.CREATED).entity(new MensagemRetornoDTO(finalMessage)).build();
    }

    @Override
    public Response cadastrar(ProdutoDTO produtoDTO) {
        return this.templatePostPut(produtoDTO, "cadastrado");
    }

    @Override
    public Response atualizar(ProdutoDTO produtoDTO) {
        return this.templatePostPut(produtoDTO, "atualizado");
    }

    @Override
    public Response buscarProdutoPorId(Long id) {
        return Response.status(Response.Status.OK)
                .entity(toDTO(produtoService.buscarProdutoPorId(id)))
                .build();
    }

    @Override
    public Response deletar(Long id) {
        produtoService.deletar(id);
        return Response.status(Response.Status.OK).entity(new MensagemRetornoDTO("Produto deletado com sucesso!"))
                .build();
    }

    private ProdutoDTO toDTO(Produto p){
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(p.getId());
        dto.setDescricao(p.getDescricao());
        dto.setNome(p.getNome());
        dto.setValor(p.getValor());

        List<CategoriaDTO> categoriaDTOS = new ArrayList<>();
        for (Categoria i: p.getCategorias()) {
            categoriaDTOS.add(toDTO(i));
        }
        dto.setCategorias(categoriaDTOS);
        return dto;
    }

    private CategoriaDTO toDTO(Categoria t){
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(t.getId());
        dto.setCodigo(t.getCodigo());
        dto.setNome(t.getNome());
        return dto;
    }
}
