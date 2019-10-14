package br.com.ctis.hackathon.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class EnderecoDTO extends BaseDTO{
    @Schema(description = "Id do Endereço", required = false)
    private Long id;

    @NotNull(message = "Cidade não pode ser nulo")
    @NotBlank(message = "Cidade não pode ser vazio")
    @Length(max = 30, message = "Cidade deve possuir até 30 caracteres")
    @Schema(description= "Cidade do Endereço", required = true)
    private String cidade;

    @NotNull(message = "Estado não pode ser nulo")
    @NotBlank(message = "Estado não pode ser vazio")
    @Length(max = 30, message = "Estado deve possuir até 30 caracteres")
    @Schema(description= "Estado do Endereço", required = true)
    private String estado;

    @NotNull(message = "Rua não pode ser nulo")
    @NotBlank(message = "Rua não pode ser vazio")
    @Length(max = 100, message = "Rua deve possuir até 100 caracteres")
    @Schema(description= "Rua do Endereço", required = true)
    private String rua;

    @NotNull(message = "Número não pode ser nulo")
    @NotBlank(message = "Número não pode ser vazio")
    @Length(max = 30, message = "Número deve possuir até 30 caracteres")
    @Schema(description= "Número do Endereço", required = true)
    private String numero;

    @NotNull(message = "Bairro não pode ser nulo")
    @NotBlank(message = "Bairro não pode ser vazio")
    @Length(max = 30, message = "Bairro deve possuir até 30 caracteres")
    @Schema(description= "Bairro do Endereço", required = true)
    private String bairro;

    public String getRua() {
        return rua;
    }

    public String getEstado() {
        return estado;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getNumero() {
        return numero;
    }

    public Long getId() {
        return id;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
