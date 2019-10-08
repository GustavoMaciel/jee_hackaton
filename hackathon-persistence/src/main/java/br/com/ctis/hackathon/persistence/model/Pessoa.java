package br.com.ctis.hackathon.persistence.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_pessoa", schema = "hackaton")
public class Pessoa extends EntidadeBase<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "sobrenome", nullable = false, length = 150)
    private String sobrenome;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "cpf", nullable = false, length = 16)
    private String cpf;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<Telefone> telefones;

    @Override
    public Long getId() {
        return this.id;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }
}
