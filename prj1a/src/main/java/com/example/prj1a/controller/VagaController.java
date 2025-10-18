package com.example.prj1a.controller;

import com.example.prj1a.model.Vaga;
import com.example.prj1a.repo.VagaRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/vagas")
public class VagaController {

    private final VagaRepo repo;

    public VagaController(VagaRepo repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Vaga> list() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vaga> get(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Vaga> create(@RequestBody Vaga v) {
        Vaga saved = repo.save(v);
        return ResponseEntity.created(URI.create("/vagas/" + saved.getId()))
                .body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vaga> update(@PathVariable Long id, @RequestBody Vaga v) {
        return repo.findById(id).map(orig -> {
            orig.setTitulo(v.getTitulo());
            orig.setDescricao(v.getDescricao());
            orig.setPublicacao(v.getPublicacao());
            orig.setAtivo(v.getAtivo());
            orig.setIdEmpresa(v.getIdEmpresa());
            return ResponseEntity.ok(repo.save(orig));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
