package br.com.ctis.hackathon.endpoint;

import br.com.ctis.hackathon.dto.EmpresaDTO;
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
 * Endpoint para acessar a base de Empresas
 */
@Path("empresas")
@Tag(name = "Empresas", description = "Acesso à base de dados de Empresas")
public interface EmpresaEndPoint {
    @GET
    @Consumes("application/json")
    @Produces("application/json")
    @Operation(description = "Lista as Empresas cadastradas no sistema", summary = "Listar Empresas",
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
    @Operation(description = "Recurso para cadastro de uma nova Empresa no sistema", summary = "Adicionar nova Empresa",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    Response cadastrar(@RequestBody(
            description = "Objeto Empresa que será adicionado",
            required = true,
            content = @Content(schema = @Schema(implementation = EmpresaDTO.class))) @Valid EmpresaDTO empresaDTO
    );

    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    @Operation(description = "Recurso para atualizar uma Empresa do sistema",
            summary = "Atualizar Empresa",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    Response atualizar(@RequestBody(
            description = "Objeto Empresa que será atualizado",
            required = true,
            content = @Content(schema = @Schema(implementation = EmpresaDTO.class))) @Valid EmpresaDTO empresaDTO);

    @GET
    @Path("{id}")
    @Produces("application/json")
    @Operation(description = "Recupera Empresa pelo Id", summary = "Recuperar Empresa",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    Response buscarEmpresaPorId(@PathParam(value="id") Long id);

    @DELETE
    @Path("{id}")
    @Produces("application/json")
    @Operation(description = "Deleta Empresa pelo Id", summary = "Deletar Empresa",
            responses = {
                    @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "404", description = "Not found"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    Response deletar(@PathParam(value="id") Long id);
}
