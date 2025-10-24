package com.example.prj1a.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Inscricao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataInscricao;
    private String status;

    @Column(length = 4000)
    private String mensagemApresentacao;

    @ManyToOne(optional = false)
    private Estudante estudante;

    @ManyToOne(optional = false)
    private Vaga vaga;

    // getters/setters
    public Long getId() { return id; }
    public LocalDate getDataInscricao() { return dataInscricao; }
    public void setDataInscricao(LocalDate dataInscricao) { this.dataInscricao = dataInscricao; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getMensagemApresentacao() { return mensagemApresentacao; }
    public void setMensagemApresentacao(String mensagemApresentacao) { this.mensagemApresentacao = mensagemApresentacao; }
    public Estudante getEstudante() { return estudante; }
    public void setEstudante(Estudante estudante) { this.estudante = estudante; }
    public Vaga getVaga() { return vaga; }
    public void setVaga(Vaga vaga) { this.vaga = vaga; }
}
