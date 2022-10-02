package com.example.IntegradorFinalNicolasMontero.Controller;
import com.example.IntegradorFinalNicolasMontero.Entity.Odontologo;
import com.example.IntegradorFinalNicolasMontero.Entity.Turno;
import com.example.IntegradorFinalNicolasMontero.Exceptions.AlreadyExistException;
import com.example.IntegradorFinalNicolasMontero.Exceptions.BadRequestException;
import com.example.IntegradorFinalNicolasMontero.Exceptions.ResourceNotFoundException;
import com.example.IntegradorFinalNicolasMontero.Service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private final OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {

        this.odontologoService = odontologoService;
    }
    @PostMapping
    public ResponseEntity<Odontologo> registarOdontologo (@RequestBody Odontologo odontologo) throws AlreadyExistException {
        ResponseEntity<Odontologo> respuesta;
        respuesta = ResponseEntity.ok(odontologoService.guardar(odontologo));
        return respuesta;
    }
    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarAllOdontologos(){
        ResponseEntity<List<Odontologo>> respuesta;
        respuesta=ResponseEntity.ok(odontologoService.buscarTodos());
        return respuesta;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable Long id) {
        ResponseEntity<Odontologo> respuesta;
        Odontologo odontologo = odontologoService.buscar(id);
        if (odontologo.getId() == null){
            respuesta=ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            respuesta=ResponseEntity.ok(odontologo);
        }
        return respuesta;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.eliminar(id);
        return ResponseEntity.ok("Se eliminó el turno con id="+id);
    }

    @PutMapping
    public ResponseEntity<Odontologo> actualizarOdontologo(@RequestBody Odontologo odontologo) {
        ResponseEntity<Odontologo> respuesta;
        if (odontologoService.buscar(odontologo.getId()) != null) {
            respuesta = ResponseEntity.ok(odontologoService.actualizar(odontologo));
        }
        else {
            respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return respuesta;
    }
}
