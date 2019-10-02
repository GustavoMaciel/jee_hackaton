package br.com.ctis.hackathon.endpoint;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.com.ctis.hackathon.dto.EchoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Endpoint apenas com o intuito de testes da aplicação
 *  
 * @author alysson.cordeiro@ctis.com.br
 *
 */
@Path("echos")
@Tag(name = "Echo", description = "Recursos para testes da aplicação")
public interface EchoEndPoint {

	@GET
	@Produces("application/json")
	@Operation(description = "Lista os Echo's cadastrados no sistema", summary = "Listar Echo's", 
			   responses = {
					   @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json")),
					   @ApiResponse(responseCode = "400", description = "Bad Request"),
					   @ApiResponse(responseCode = "404", description = "Not found"),
					   @ApiResponse(responseCode = "500", description = "Internal Server Error") 
			    }
			  )
	Response listar();
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Operation(description = "Recurso para cadastro de um novo Echo no sistema", summary = "Adicionar novo Echo", 
	   responses = {
			   @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json")),
			   @ApiResponse(responseCode = "400", description = "Bad Request"),
			   @ApiResponse(responseCode = "404", description = "Not found"),
			   @ApiResponse(responseCode = "500", description = "Internal Server Error") 
	    }
	  )
	Response cadastrar(@RequestBody(description = "Objeto Echo que será adicionado", required = true, content = @Content(schema = @Schema(implementation = EchoDTO.class))) @Valid EchoDTO echoDTO);
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	@Operation(description = "Recupera Echo pelo Id", summary = "Recuperar Echos", 
			   responses = {
					   @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json")),
					   @ApiResponse(responseCode = "400", description = "Bad Request"),
					   @ApiResponse(responseCode = "404", description = "Not found"),
					   @ApiResponse(responseCode = "500", description = "Internal Server Error") 
			    }
			  )
	Response buscarEchoPorId(@PathParam(value="id") Long id);
		
}
