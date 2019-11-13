package br.com.ctis.hackathon.endpoint;

import br.com.ctis.hackathon.dto.ProdutoDTO;
import br.com.ctis.hackathon.util.PageDict;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Endpoint para acessar a base de Produtos
 */
@Path("produtos")
@Tag(name = "Produtos", description = "Acesso à base de dados de Produtos")
public interface ProdutoEndPoint {
    @GET
    @Consumes("application/json")
    @Produces("application/json")
    @Operation(description = "Lista os Produtos cadastrados no sistema", summary = "Listar Produtos",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    Response listar(@QueryParam("pageNumber") @Valid int pageNumber, @QueryParam("pageSize") @Valid int pageSize,
                    @QueryParam("searchName") @Valid String searchName);

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Operation(description = "Recurso para cadastro de um novo Produto no sistema", summary = "Adicionar novo Produto",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    Response cadastrar(@RequestBody(
            description = "Objeto Produto que será adicionado",
            required = true,
            content = @Content(schema = @Schema(implementation = ProdutoDTO.class))) @Valid ProdutoDTO produtoDTO
    );

    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    @Operation(description = "Recurso para atualizar uma Produto do sistema",
            summary = "Atualizar Produto",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    Response atualizar(@RequestBody(
            description = "Objeto Produto que será atualizado",
            required = true,
            content = @Content(schema = @Schema(implementation = ProdutoDTO.class))) @Valid ProdutoDTO produtoDTO);

    @GET
    @Path("{id}")
    @Produces("application/json")
    @Operation(description = "Recupera Produto pelo Id", summary = "Recuperar Produto",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    Response buscarProdutoPorId(@PathParam(value="id") Long id);

    @DELETE
    @Path("{id}")
    @Produces("application/json")
    @Operation(description = "Deleta Produto pelo Id", summary = "Deletar Produto",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    Response deletar(@PathParam(value="id") Long id);
}
