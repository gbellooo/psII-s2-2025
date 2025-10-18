package com.example.prj1a.model;

import jakarta.persistence.*;

@Entity
public class Empresa {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(length = 20)
    private String cnpj;

    private String emailContato;

    public Empresa() {}
    public Empresa(Long id, String nome, String cnpj, String emailContato) {
        this.id = id; this.nome = nome; this.cnpj = cnpj; this.emailContato = emailContato;
    }
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; } public void setNome(String nome) { this.nome = nome; }
    public String getCnpj() { return cnpj; } public void setCnpj(String cnpj) { this.cnpj = cnpj; }
    public String getEmailContato() { return emailContato; } public void setEmailContato(String emailContato) { this.emailContato = emailContato; }
}
