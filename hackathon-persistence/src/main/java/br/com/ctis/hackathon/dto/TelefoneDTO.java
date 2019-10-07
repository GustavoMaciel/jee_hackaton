package br.com.ctis.hackathon.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class TelefoneDTO extends BaseDTO{
    @NotNull(message = "Código do país não pode ser nulo")
    @NotBlank(message = "Código do país não pode ser vazio")
    @Length(max = 5, message = "Código do país deve possuir até 5 caracteres")
    @Schema(description= "Código do país do telefone", required = true)
    private String codigoPais;

    @NotNull(message = "DDD não pode ser nulo")
    @NotBlank(message = "DDD não pode ser vazio")
    @Length(max = 5, message = "DDD deve possuir até 5 caracteres")
    @Schema(description= "DDD do telefone", required = true)
    private String ddd;

    @NotNull(message = "Número não pode ser nulo")
    @NotBlank(message = "Número não pode ser vazio")
    @Length(max = 10, message = "Número deve possuir até 10 caracteres")
    @Schema(description= "Número do telefone", required = true)
    private String numero;

    public String getCodigoPais() {
        return codigoPais;
    }

    public String getDdd() {
        return ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
