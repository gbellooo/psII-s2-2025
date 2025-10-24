package com.example.prj1a.controller;

import com.example.prj1a.model.Area;
import com.example.prj1a.repo.AreaRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/areas")
public class AreaController {

    private final AreaRepo repo;
    public AreaController(AreaRepo repo) { this.repo = repo; }

    @GetMapping public List<Area> all() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Area> one(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping public Area create(@RequestBody Area a) { return repo.save(a); }

    @PutMapping("/{id}")
    public ResponseEntity<Area> update(@PathVariable Long id, @RequestBody Area a) {
        return repo.findById(id).map(db -> {
            db.setNome(a.getNome());
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
