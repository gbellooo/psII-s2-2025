package com.example.prj1a.controller;

import com.example.prj1a.model.Estudante;
import com.example.prj1a.store.DataStore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudantes")
public class EstudanteController {

    private final DataStore store;

    public EstudanteController(DataStore store) {
        this.store = store;
    }

    @GetMapping
    public List<Estudante> listar() {
        return store.getEstudantes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudante> obter(@PathVariable Long id) {
        return store.findEstudante(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Estudante> criar(@RequestBody Estudante s) {
        Estudante novo = store.addEstudante(s);
        return ResponseEntity.ok(novo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudante> atualizar(@PathVariable Long id, @RequestBody Estudante s) {
        return store.updateEstudante(id, s).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        boolean removed = store.deleteEstudante(id);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
