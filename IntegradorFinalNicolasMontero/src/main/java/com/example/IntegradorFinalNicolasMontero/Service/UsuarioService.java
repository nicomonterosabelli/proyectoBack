package com.example.IntegradorFinalNicolasMontero.Service;

import com.example.IntegradorFinalNicolasMontero.Entity.Paciente;
import com.example.IntegradorFinalNicolasMontero.Entity.Usuario;
import com.example.IntegradorFinalNicolasMontero.Exceptions.AlreadyExistException;
import com.example.IntegradorFinalNicolasMontero.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {
    private UsuarioRepository usuarioRepository;
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioBuscado = usuarioRepository.findUsuarioByMail(username);
        if (usuarioBuscado.isPresent()) {
            return usuarioBuscado.get();
        }
        else {
            throw new UsernameNotFoundException("El correo ingresado no esta vinculado a ningun usuario");
        }
    }
    public Usuario guardar (Usuario usuario) throws AlreadyExistException {
        List<Usuario> usuarios=buscarTodos();
        for (Usuario usu:usuarios) {
            if (usu.getUserName() == usuario.getUserName()){
                throw new AlreadyExistException("El usuario ya existe en la base de datos");
            }
        }
        return usuarioRepository.save(usuario);
    }
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }
}
