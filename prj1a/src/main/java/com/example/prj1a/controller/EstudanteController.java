package com.example.prj1a.controller;

import com.example.prj1a.model.Estudante;
import com.example.prj1a.repo.EstudanteRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/estudantes")
public class EstudanteController {

    private final EstudanteRepo repo;

    public EstudanteController(EstudanteRepo repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Estudante> list() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudante> get(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Estudante> create(@RequestBody Estudante s) {
        Estudante saved = repo.save(s);
        return ResponseEntity.created(URI.create("/estudantes/" + saved.getId()))
                .body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudante> update(@PathVariable Long id, @RequestBody Estudante s) {
        return repo.findById(id).map(orig -> {
            orig.setNome(s.getNome());
            orig.setEmail(s.getEmail());
            orig.setNascimento(s.getNascimento());
            orig.setAnoIngresso(s.getAnoIngresso());
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
