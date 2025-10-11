package com.example.prj1a.controller;

import com.example.prj1a.model.Empresa;
import com.example.prj1a.store.DataStore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    private final DataStore store;

    public EmpresaController(DataStore store) {
        this.store = store;
    }

    @GetMapping
    public List<Empresa> listar() {
        return store.getEmpresas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> obter(@PathVariable Long id) {
        return store.findEmpresa(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Empresa> criar(@RequestBody Empresa e) {
        Empresa nova = store.addEmpresa(e);
        return ResponseEntity.ok(nova);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> atualizar(@PathVariable Long id, @RequestBody Empresa e) {
        return store.updateEmpresa(id, e).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        boolean removed = store.deleteEmpresa(id);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
