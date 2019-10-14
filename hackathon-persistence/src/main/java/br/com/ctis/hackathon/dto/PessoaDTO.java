package br.com.ctis.hackathon.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

public class PessoaDTO extends BaseDTO {

    @Schema(description = "Id da pessoa", required = false)
    private Long id;

    @NotNull(message = "Nome não pode ser nulo")
    @NotBlank(message = "Nome não pode ser vazio")
    @Length(max = 50, message = "Nome deve possuir até 50 caracteres")
    @Schema(description= "Nome da pessoa", required = true)
    private String nome;

    @NotNull(message = "Sobrenome não pode ser nulo")
    @NotBlank(message = "Sobrenome não pode ser vazio")
    @Length(max = 150, message = "Sobreome deve possuir até 150 caracteres")
    @Schema(description= "Sobrenome da pessoa", required = true)
    private String sobrenome;

    @NotNull(message = "Email não pode ser nulo")
    @NotBlank(message = "Email não pode ser vazio")
    @Length(max = 255, message = "Email deve possuir até 255 caracteres")
    @Schema(description= "Email da pessoa", required = true)
    private String email;

    @NotNull(message = "CPF não pode ser nulo")
    @NotBlank(message = "CPF não pode ser vazio")
    @Length(max = 16, message = "CPF deve possuir até 16 caracteres")
    @Schema(description= "CPF da pessoa", required = true)
    private String cpf;

    @Schema(description = "Lista de telefones da pessoa", required = false)
    private List<TelefoneDTO> telefones;

    public Long getId() {
        return id;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public List<TelefoneDTO> getTelefones() {
        return telefones;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setTelefones(List<TelefoneDTO> telefones) {
        this.telefones = telefones;
    }
}
