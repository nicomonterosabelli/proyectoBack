package com.example.IntegradorFinalNicolasMontero.Repository;

import com.example.IntegradorFinalNicolasMontero.Entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente,Long> {
}
