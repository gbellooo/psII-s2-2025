package com.example.prj1a.store;

import com.example.prj1a.model.Empresa;
import com.example.prj1a.model.Estudante;
import com.example.prj1a.model.Vaga;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DataStore {

    private final List<Empresa> empresas = new ArrayList<>();
    private final List<Estudante> estudantes = new ArrayList<>();
    private final List<Vaga> vagas = new ArrayList<>();

    // ===== EMPRESAS =====
    public List<Empresa> getEmpresas() { return empresas; }

    public Optional<Empresa> findEmpresa(Long id) {
        return empresas.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    public Empresa addEmpresa(Empresa e) {
        e.setId(nextId(empresas.stream().map(Empresa::getId).toList()));
        empresas.add(e);
        return e;
    }

    public Optional<Empresa> updateEmpresa(Long id, Empresa e) {
        return findEmpresa(id).map(orig -> {
            orig.setNome(e.getNome());
            orig.setCnpj(e.getCnpj());
            orig.setEmailContato(e.getEmailContato());
            return orig;
        });
    }

    public boolean deleteEmpresa(Long id) {
        return empresas.removeIf(e -> e.getId().equals(id));
    }

    // ===== ESTUDANTES =====
    public List<Estudante> getEstudantes() { return estudantes; }

    public Optional<Estudante> findEstudante(Long id) {
        return estudantes.stream().filter(s -> s.getId().equals(id)).findFirst();
    }

    public Estudante addEstudante(Estudante s) {
        s.setId(nextId(estudantes.stream().map(Estudante::getId).toList()));
        estudantes.add(s);
        return s;
    }

    public Optional<Estudante> updateEstudante(Long id, Estudante s) {
        return findEstudante(id).map(orig -> {
            orig.setNome(s.getNome());
            orig.setEmail(s.getEmail());
            orig.setNascimento(s.getNascimento());
            orig.setAnoIngresso(s.getAnoIngresso());
            return orig;
        });
    }

    public boolean deleteEstudante(Long id) {
        return estudantes.removeIf(s -> s.getId().equals(id));
    }

    // ===== VAGAS =====
    public List<Vaga> getVagas() { return vagas; }

    public Optional<Vaga> findVaga(Long id) {
        return vagas.stream().filter(v -> v.getId().equals(id)).findFirst();
    }

    public Vaga addVaga(Vaga v) {
        // id de Vaga agora é gerenciado pelo JPA; não há setId() na entidade.
        // Mantemos a lista apenas para compatibilidade com a semana anterior.
        vagas.add(v);
        return v;
    }

    public Optional<Vaga> updateVaga(Long id, Vaga v) {
        return findVaga(id).map(orig -> {
            orig.setTitulo(v.getTitulo());
            orig.setDescricao(v.getDescricao());
            // campo mudou: publicacao -> dataPublicacao
            orig.setDataPublicacao(v.getDataPublicacao());
            orig.setAtivo(v.getAtivo());
            // relacionamento ManyToOne: ao invés de setIdEmpresa(), usamos o objeto Empresa
            if (v.getEmpresa() != null) {
                orig.setEmpresa(v.getEmpresa());
            }
            // se desejar, pode atualizar também a área:
            if (v.getArea() != null) {
                orig.setArea(v.getArea());
            }
            return orig;
        });
    }

    public boolean deleteVaga(Long id) {
        return vagas.removeIf(v -> v.getId().equals(id));
    }

    // Util para auto-incremento simples (in-memory) — ainda usado por Empresa/Estudante
    private Long nextId(List<Long> ids) {
        return ids.stream().mapToLong(l -> l).max().orElse(0L) + 1L;
    }
}
