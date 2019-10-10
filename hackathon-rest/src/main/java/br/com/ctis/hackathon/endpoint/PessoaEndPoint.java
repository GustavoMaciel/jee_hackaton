package br.com.ctis.hackathon.endpoint;

import br.com.ctis.hackathon.dto.PessoaDTO;
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
 * Endpoint para acessar a base de Pessoas
 */
@Path("pessoas")
@Tag(name = "Pessoas", description = "Acesso à base de dados de Pessoas")
public interface PessoaEndPoint {

    @GET
    @Produces("application/json")
    @Operation(description = "Lista as Pessoas cadastradas no sistema", summary = "Listar Pessoas",
            responses = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    Response listar(@RequestBody(
            description = "Informações de tamanho de página e busca",
            required = true,
            content = @Content(schema = @Schema(implementation = PageDict.class))) @Valid PageDict pageDict
                    );

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Operation(description = "Recurso para cadastro de uma nova Pessoa no sistema", summary = "Adicionar nova Pessoa",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    Response cadastrar(@RequestBody(
            description = "Objeto Pessoa que será adicionado",
            required = true,
            content = @Content(schema = @Schema(implementation = PessoaDTO.class))) @Valid PessoaDTO pessoaDTO
    );

    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    @Operation(description = "Recurso para atualizar uma Pessoa do sistema",
            summary = "Atualizar Pessoa",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    Response atualizar(@RequestBody(
            description = "Objeto Pessoa que será atualizado",
            required = true,
            content = @Content(schema = @Schema(implementation = PessoaDTO.class))) @Valid PessoaDTO pessoaDTO);

    @GET
    @Path("{id}")
    @Produces("application/json")
    @Operation(description = "Recupera Pessoa pelo Id", summary = "Recuperar Pessoa",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    Response buscarPessoaPorId(@PathParam(value="id") Long id);

    @DELETE
    @Path("{id}")
    @Produces("application/json")
    @Operation(description = "Deleta Pessoa pelo Id", summary = "Deletar Pessoa",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    Response deletar(@PathParam(value="id") Long id);
}
