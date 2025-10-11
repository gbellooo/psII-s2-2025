package com.example.prj1a.controller;

import com.example.prj1a.model.Vaga;
import com.example.prj1a.store.DataStore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vagas")
public class VagaController {

    private final DataStore store;

    public VagaController(DataStore store) {
        this.store = store;
    }

    @GetMapping
    public List<Vaga> listar() {
        return store.getVagas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vaga> obter(@PathVariable Long id) {
        return store.findVaga(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Vaga> criar(@RequestBody Vaga v) {
        Vaga nova = store.addVaga(v);
        return ResponseEntity.ok(nova);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vaga> atualizar(@PathVariable Long id, @RequestBody Vaga v) {
        return store.updateVaga(id, v).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        boolean removed = store.deleteVaga(id);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
