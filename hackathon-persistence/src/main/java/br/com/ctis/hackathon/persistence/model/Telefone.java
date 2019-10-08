package br.com.ctis.hackathon.persistence.model;

import javax.persistence.*;

@Entity
@Table(name = "tb_telefone", schema = "hackaton")
public class Telefone extends EntidadeBase<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "codigoPais", nullable = false, length = 5)
    private String codigoPais;

    @Column(name = "ddd", nullable = false, length = 5)
    private String ddd;

    @Column(name = "numero", nullable = false, length = 10)
    private String numero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoaId")
    private Pessoa pessoa;

    @Column(name = "pessoaId", updatable = false, nullable = false, insertable = false)
    private Long pessoaId;

    @Override
    public Long getId() {
        return id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public String getDdd() {
        return ddd;
    }

    public String getNumero() {
        return numero;
    }

    public Long getPessoaId() {
        return pessoaId;
    }

    public void setId(long id) {
        this.id = id;
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

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }
}
