package com.example.prj1a.repo;

import com.example.prj1a.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepo extends JpaRepository<Empresa, Long> {
}
