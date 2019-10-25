package br.com.ctis.hackathon.persistence.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_categoria", schema = "hackaton")
public class Categoria extends EntidadeBase<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "codigo", nullable = false, length = 50)
    private String codigo;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @ManyToMany
    @JoinTable(
            name = "produto_categorias",
            joinColumns = @JoinColumn(name = "categoriaId"),
            inverseJoinColumns = @JoinColumn(name = "produtoId")
    )
    private List<Produto> produtos;

    @Override
    public Long getId() {
        return id;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
