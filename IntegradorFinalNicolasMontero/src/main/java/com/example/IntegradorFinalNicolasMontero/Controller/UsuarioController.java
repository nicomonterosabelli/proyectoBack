package com.example.IntegradorFinalNicolasMontero.Controller;

import com.example.IntegradorFinalNicolasMontero.Entity.Paciente;
import com.example.IntegradorFinalNicolasMontero.Entity.RolUsuario;
import com.example.IntegradorFinalNicolasMontero.Entity.Usuario;
import com.example.IntegradorFinalNicolasMontero.Exceptions.AlreadyExistException;
import com.example.IntegradorFinalNicolasMontero.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @PostMapping
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) throws AlreadyExistException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passHash = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passHash);
        usuario.setUsuarioRol(RolUsuario.ROLE_USER);
        ResponseEntity<Usuario> respuesta;
        respuesta=ResponseEntity.ok(usuarioService.guardar(usuario));
        return respuesta;
    }
}
