package com.example.IntegradorFinalNicolasMontero.Controller;

import com.example.IntegradorFinalNicolasMontero.Entity.Paciente;
import com.example.IntegradorFinalNicolasMontero.Exceptions.AlreadyExistException;
import com.example.IntegradorFinalNicolasMontero.Exceptions.ResourceNotFoundException;
import com.example.IntegradorFinalNicolasMontero.Service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private final PacienteService pacienteService;
    @Autowired
    public PacienteController(PacienteService pacienteService) {

        this.pacienteService = pacienteService;
    }
    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente) throws AlreadyExistException {
        ResponseEntity<Paciente> respuesta;
            respuesta=ResponseEntity.ok(pacienteService.guardar(paciente));
        return respuesta;
    }
    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos (){
        ResponseEntity<List<Paciente>> respuesta;
        respuesta=ResponseEntity.ok(pacienteService.buscarTodos());
        return respuesta;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable Long id){

        ResponseEntity<Paciente> respuesta;
        Paciente paciente = pacienteService.buscar(id);
        if (paciente.getId() == null){
            respuesta=ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            respuesta=ResponseEntity.ok(paciente);
        }
        return respuesta;
    }

    @PutMapping
    public ResponseEntity<Paciente> actualizarPaciente(@RequestBody Paciente paciente){
        ResponseEntity<Paciente> respuesta;
        if (pacienteService.buscar(paciente.getId()).getId() == null){
            respuesta=ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else{
            respuesta=ResponseEntity.ok(pacienteService.actualizar(paciente));
        }
        return respuesta;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminar(id);
        return ResponseEntity.ok("Se eliminó el turno con id="+id);

    }

}