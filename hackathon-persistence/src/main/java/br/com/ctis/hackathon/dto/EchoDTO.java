package br.com.ctis.hackathon.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import io.swagger.v3.oas.annotations.media.Schema;

public class EchoDTO extends BaseDTO {

	private static final long serialVersionUID = 4538327150278203065L;

	@NotNull
	@Schema(description= "Código do recurso", required = true)
	private Long codigo;
	
	@NotNull(message = "Nome não pode ser nulo")
	@NotBlank(message = "Nome não pode ser vazio")
	@Length(max = 255, message = "Nome deve possuir até 255 caracteres")
	@Schema(description= "Nome do recurso", required = false)
	private String nome;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}

