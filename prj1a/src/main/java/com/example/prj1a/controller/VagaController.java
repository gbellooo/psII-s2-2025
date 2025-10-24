package com.example.prj1a.controller;

import com.example.prj1a.model.Vaga;
import com.example.prj1a.repo.VagaRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vagas")
public class VagaController {

    private final VagaRepo repo;
    public VagaController(VagaRepo repo) { this.repo = repo; }

    @GetMapping public List<Vaga> all() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Vaga> one(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping public Vaga create(@RequestBody Vaga v) { return repo.save(v); }

    @PutMapping("/{id}")
    public ResponseEntity<Vaga> update(@PathVariable Long id, @RequestBody Vaga v) {
        return repo.findById(id).map(db -> {
            db.setTitulo(v.getTitulo());
            db.setDescricao(v.getDescricao());
            db.setDataPublicacao(v.getDataPublicacao());
            db.setAtivo(v.getAtivo());
            if (v.getEmpresa() != null) db.setEmpresa(v.getEmpresa());
            if (v.getArea() != null) db.setArea(v.getArea());
            return ResponseEntity.ok(repo.save(db));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
