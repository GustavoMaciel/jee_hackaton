package br.com.ctis.hackathon.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

public class EmpresaDTO extends BaseDTO {
    @Schema(description = "Id da Empresa", required = false)
    private Long id;

    @NotNull(message = "CNPJ não pode ser nulo")
    @NotBlank(message = "CNPJ não pode ser vazio")
    @Length(max = 16, message = "CNPJ deve possuir até 16 caracteres")
    @Schema(description= "CNPJ da Empresa", required = true)
    private String cnpj;

    @NotNull(message = "Nome fantasia não pode ser nulo")
    @NotBlank(message = "Nome fantasia não pode ser vazio")
    @Length(max = 150, message = "Nome fantasia deve possuir até 150 caracteres")
    @Schema(description= "Nome fantasia da Empresa", required = true)
    private String nomeFantasia;

    @NotNull(message = "Missão não pode ser nulo")
    @NotBlank(message = "Missão não pode ser vazio")
    @Length(max = 600, message = "Missão deve possuir até 600 caracteres")
    @Schema(description= "Missão da Empresa", required = true)
    private String missao;

    @NotNull(message = "Visão não pode ser nulo")
    @NotBlank(message = "Visão não pode ser vazio")
    @Length(max = 600, message = "Visão deve possuir até 600 caracteres")
    @Schema(description= "Visão da Empresa", required = true)
    private String visao;

    @Schema(description = "Lista de Endereços da Empresa", required = false)
    private List<EnderecoDTO> enderecos;

    public Long getId() {
        return id;
    }

    public String getVisao() {
        return visao;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public String getMissao() {
        return missao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public List<EnderecoDTO> getEnderecos() {
        return enderecos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setVisao(String visao) {
        this.visao = visao;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public void setMissao(String missao) {
        this.missao = missao;
    }

    public void setEnderecos(List<EnderecoDTO> enderecos) {
        this.enderecos = enderecos;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
