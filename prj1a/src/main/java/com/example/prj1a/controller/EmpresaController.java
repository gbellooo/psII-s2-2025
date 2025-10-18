package com.example.prj1a.controller;

import com.example.prj1a.model.Empresa;
import com.example.prj1a.repo.EmpresaRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaRepo repo;

    public EmpresaController(EmpresaRepo repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Empresa> list() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> get(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Empresa> create(@RequestBody Empresa e) {
        Empresa saved = repo.save(e);
        return ResponseEntity.created(URI.create("/empresas/" + saved.getId()))
                .body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> update(@PathVariable Long id, @RequestBody Empresa e) {
        return repo.findById(id).map(orig -> {
            orig.setNome(e.getNome());
            orig.setCnpj(e.getCnpj());
            orig.setEmailContato(e.getEmailContato());
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
