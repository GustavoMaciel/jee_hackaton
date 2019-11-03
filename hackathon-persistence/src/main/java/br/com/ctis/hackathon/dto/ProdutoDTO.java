package br.com.ctis.hackathon.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ProdutoDTO extends BaseDTO{

    @Schema(description = "Id do produto", required = false)
    private Long id;

    @NotNull(message = "Nome não pode ser nulo")
    @NotBlank(message = "Nome não pode ser vazio")
    @Length(max = 150, message = "Nome deve possuir até 150 caracteres")
    @Schema(description= "Nome do produto", required = true)
    private String nome;

    @NotNull(message = "Descrição não pode ser nulo")
    @NotBlank(message = "Descrição não pode ser vazio")
    @Length(max = 600, message = "Descrição deve possuir até 600 caracteres")
    @Schema(description= "Descrição do produto", required = true)
    private String descricao;

    @NotNull(message = "Valor não pode ser nulo")
    @NotBlank(message = "Valor não pode ser vazio")
    @Min(value = 1, message = "Valor deve ser de no mínimo 1")
    @Schema(description= "Valor do produto", required = true)
    private long valor;

    @Schema(description = "Lista de categorias do produto", required = false)
    private List<CategoriaDTO> categorias;

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<CategoriaDTO> getCategorias() {
        return categorias;
    }

    public long getValor() {
        return valor;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }

    public void setCategorias(List<CategoriaDTO> categorias) {
        this.categorias = categorias;
    }
}
