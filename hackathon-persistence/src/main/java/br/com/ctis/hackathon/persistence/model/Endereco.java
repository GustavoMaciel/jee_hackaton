package br.com.ctis.hackathon.persistence.model;


import javax.persistence.*;

@Entity
@Table(name = "tb_endereco", schema = "hackaton")
public class Endereco extends EntidadeBase<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "cidade", nullable = false, length = 30)
    private String cidade;

    @Column(name = "estado", nullable = false, length = 30)
    private String estado;

    @Column(name = "rua", nullable = false, length = 100)
    private String rua;

    @Column(name = "numero", nullable = false, length = 30)
    private String numero;

    @Column(name = "bairro", nullable = false, length = 30)
    private String bairro;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "empresa")
    private Empresa empresa;

    @Column(name = "empresaId", updatable = false, nullable = false, insertable = false)
    private Long empresaId;

    @Override
    public Long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public Long getEmpresaId() {
        return empresaId;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getRua() {
        return rua;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void setEmpresaId(Long empresaId) {
        this.empresaId = empresaId;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }
}
