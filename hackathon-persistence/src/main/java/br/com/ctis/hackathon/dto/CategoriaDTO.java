package br.com.ctis.hackathon.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class CategoriaDTO extends BaseDTO {

    @Schema(description = "Id da pessoa", required = false)
    private Long id;

    @NotNull(message = "Código não pode ser nulo")
    @NotBlank(message = "Código não pode ser vazio")
    @Length(max = 50, message = "Código deve possuir até 50 caracteres")
    @Schema(description= "Código da categoria", required = true)
    private String codigo;

    @NotNull(message = "Nome não pode ser nulo")
    @NotBlank(message = "Nome não pode ser vazio")
    @Length(max = 150, message = "Nome deve possuir até 150 caracteres")
    @Schema(description= "Nome da categoria", required = true)
    private String nome;

    public Long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
