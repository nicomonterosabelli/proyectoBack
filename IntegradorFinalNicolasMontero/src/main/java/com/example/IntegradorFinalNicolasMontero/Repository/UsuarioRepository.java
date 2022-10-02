package com.example.IntegradorFinalNicolasMontero.Repository;

import com.example.IntegradorFinalNicolasMontero.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findUsuarioByMail (String mail);
}
