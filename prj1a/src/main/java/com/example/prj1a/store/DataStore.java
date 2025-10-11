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

    @PostConstruct
    public void init() {
        // Empresas
        empresas.add(new Empresa(1L, "Empresa Alfa LTDA", "12.345.678/0001-90", "contato@empresa-alfa.com"));
        empresas.add(new Empresa(2L, "Beta Comércio ME", "98.765.432/0001-10", "beta@comercio.com"));
        empresas.add(new Empresa(3L, "Gamma Serviços S.A.", "11.222.333/0001-44", "servicos@gamma.com"));
        empresas.add(new Empresa(4L, "Delta Engenharia", "22.333.444/0001-55", "contato@deltaeng.com"));
        empresas.add(new Empresa(5L, "Epsilon Digital", "33.444.555/0001-66", "email@epsilondigital.com"));

        // Estudantes
        estudantes.add(new Estudante(1L,"Ana Paula Souza","ana.souza@email.com",
                LocalDate.parse("2002-03-15"), 2020));
        estudantes.add(new Estudante(2L,"Carlos Henrique Lima","carlos.lima@email.com",
                LocalDate.parse("2001-10-22"), 2019));
        estudantes.add(new Estudante(3L,"Fernanda Oliveira","fernanda.oliveira@email.com",
                LocalDate.parse("2003-07-05"), 2021));
        estudantes.add(new Estudante(4L,"Lucas Pereira","lucas.pereira@email.com",
                LocalDate.parse("2002-04-11"), 2020));
        estudantes.add(new Estudante(5L,"Gabriela Martins","gabriela.martins@email.com",
                LocalDate.parse("2001-12-25"), 2019));
        estudantes.add(new Estudante(6L,"Rafael Costa","rafael.costa@email.com",
                LocalDate.parse("2000-09-13"), 2018));
        estudantes.add(new Estudante(7L,"Juliana Silva","juliana.silva@email.com",
                LocalDate.parse("2002-06-18"), 2020));
        estudantes.add(new Estudante(8L,"Marcos Vinícius","marcos.vinicius@email.com",
                LocalDate.parse("2003-01-30"), 2021));
        estudantes.add(new Estudante(9L,"Camila Azevedo","camila.azevedo@email.com",
                LocalDate.parse("2001-11-08"), 2019));
        estudantes.add(new Estudante(10L,"Felipe Cardoso","felipe.cardoso@email.com",
                LocalDate.parse("2000-08-27"), 2018));

        // Vagas
        vagas.add(new Vaga(1L,"Desenvolvedor Java",
                "Atuação em projetos backend com Java e Spring. Experiência desejada em APIs REST.",
                LocalDate.parse("2025-10-01"), true, 1L));
        vagas.add(new Vaga(2L,"Analista de Suporte Técnico",
                "Suporte a clientes, resolução de chamados e participação em treinamentos internos.",
                LocalDate.parse("2025-09-27"), true, 2L));
        vagas.add(new Vaga(3L,"Engenheiro de Software",
                "Desenvolvimento de soluções para sistemas corporativos, integração e automação.",
                LocalDate.parse("2025-10-03"), false, 3L));
        vagas.add(new Vaga(4L,"Analista de Dados",
                "Manipulação e análise de grandes volumes de dados. Conhecimentos de SQL e Python.",
                LocalDate.parse("2025-09-18"), true, 4L));
        vagas.add(new Vaga(5L,"Designer Digital",
                "Criação de materiais gráficos, UX/UI e participação em campanhas de marketing.",
                LocalDate.parse("2025-09-30"), false, 5L));
        vagas.add(new Vaga(6L,"Consultor de Projetos",
                "Elaboração e acompanhamento de projetos empresariais e treinamentos.",
                LocalDate.parse("2025-10-06"), true, 1L));
        vagas.add(new Vaga(7L,"Programador Full Stack",
                "Desenvolvimento de aplicações web frontend e backend com foco em automação.",
                LocalDate.parse("2025-10-04"), true, 2L));
    }

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
        v.setId(nextId(vagas.stream().map(Vaga::getId).toList()));
        vagas.add(v);
        return v;
    }
    public Optional<Vaga> updateVaga(Long id, Vaga v) {
        return findVaga(id).map(orig -> {
            orig.setTitulo(v.getTitulo());
            orig.setDescricao(v.getDescricao());
            orig.setPublicacao(v.getPublicacao());
            orig.setAtivo(v.getAtivo());
            orig.setIdEmpresa(v.getIdEmpresa());
            return orig;
        });
    }
    public boolean deleteVaga(Long id) {
        return vagas.removeIf(v -> v.getId().equals(id));
    }

    // Util para auto-incremento simples (in-memory)
    private Long nextId(List<Long> ids) {
        return ids.stream().mapToLong(l -> l).max().orElse(0L) + 1L;
    }
}
