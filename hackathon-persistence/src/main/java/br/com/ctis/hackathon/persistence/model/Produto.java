package br.com.ctis.hackathon.persistence.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "tb_produto", schema = "hackaton")
public class Produto extends EntidadeBase<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "descricao", nullable = false, length = 150)
    private String descricao;

    @Column(name = "valor", nullable = false, length = 255)
    private String valor;

    @ManyToMany(mappedBy = "produtos")
    private List<Categoria> categorias;

    @Override
    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return nome;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public String getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
