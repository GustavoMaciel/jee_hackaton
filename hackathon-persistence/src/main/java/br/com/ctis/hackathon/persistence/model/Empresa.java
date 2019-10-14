package br.com.ctis.hackathon.persistence.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_empresa", schema = "hackaton")
public class Empresa extends EntidadeBase<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "cnpj", nullable = false, length = 16)
    private String cnpj;

    @Column(name = "nomeFantasia", nullable = false, length = 150)
    private String nomeFantasia;

    @Column(name = "missao", nullable = false, length = 255)
    private String missao;

    @Column(name = "visao", nullable = false, length = 16)
    private String visao;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Endereco> enderecos;

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getMissao() {
        return missao;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public String getVisao() {
        return visao;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public void setMissao(String missao) {
        this.missao = missao;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public void setVisao(String visao) {
        this.visao = visao;
    }
}
