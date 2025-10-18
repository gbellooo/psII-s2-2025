package com.example.prj1a.repo;

import com.example.prj1a.model.Estudante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudanteRepo extends JpaRepository<Estudante, Long> {
}
