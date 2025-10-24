package com.example.prj1a.controller;

import com.example.prj1a.model.Inscricao;
import com.example.prj1a.repo.InscricaoRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscricoes")
public class InscricaoController {

    private final InscricaoRepo repo;
    public InscricaoController(InscricaoRepo repo) { this.repo = repo; }

    @GetMapping public List<Inscricao> all() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Inscricao> one(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping public Inscricao create(@RequestBody Inscricao i) { return repo.save(i); }

    @PutMapping("/{id}")
    public ResponseEntity<Inscricao> update(@PathVariable Long id, @RequestBody Inscricao i) {
        return repo.findById(id).map(db -> {
            db.setDataInscricao(i.getDataInscricao());
            db.setStatus(i.getStatus());
            db.setMensagemApresentacao(i.getMensagemApresentacao());
            if (i.getEstudante() != null) db.setEstudante(i.getEstudante());
            if (i.getVaga() != null) db.setVaga(i.getVaga());
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
