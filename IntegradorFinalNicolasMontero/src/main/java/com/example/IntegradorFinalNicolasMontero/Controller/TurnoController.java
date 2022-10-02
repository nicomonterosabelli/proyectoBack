package com.example.IntegradorFinalNicolasMontero.Controller;
import com.example.IntegradorFinalNicolasMontero.Entity.Turno;
import com.example.IntegradorFinalNicolasMontero.Exceptions.BadRequestException;
import com.example.IntegradorFinalNicolasMontero.Exceptions.ResourceNotFoundException;
import com.example.IntegradorFinalNicolasMontero.Service.OdontologoService;
import com.example.IntegradorFinalNicolasMontero.Service.PacienteService;
import com.example.IntegradorFinalNicolasMontero.Service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private final TurnoService turnoService;
    private final PacienteService pacienteService;
    private final OdontologoService odontologoService;

    @Autowired
    public TurnoController(TurnoService turnoService, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoService = turnoService;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @PostMapping
    public ResponseEntity<Turno> registrarTurno(@RequestBody Turno turno) throws BadRequestException {
        ResponseEntity<Turno> respuesta;
        respuesta=ResponseEntity.ok(turnoService.guardar(turno));
        return respuesta;
    }
    @PutMapping
    public ResponseEntity<Turno> actualizarTurno(@RequestBody Turno turno){
        ResponseEntity<Turno> respuesta;
        if (turnoService.buscar(turno.getId()).getId() == null){
            respuesta=ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        else{
            respuesta=ResponseEntity.ok(turnoService.actualizar(turno));
        }
        return respuesta;
    }

    @GetMapping()
    public ResponseEntity<List<Turno>> buscarAllTurnos(){
        ResponseEntity<List<Turno>> respuesta;
        respuesta=ResponseEntity.ok(turnoService.buscarTodos());
        return respuesta;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarTurno(@PathVariable Long id){
        ResponseEntity<Turno> respuesta;
        Turno turno = turnoService.buscar(id);
        if (turno.getId() == null){
            respuesta=ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else {
            respuesta=ResponseEntity.ok(turno);
        }
        return respuesta;
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminar(id);
        return ResponseEntity.ok("Se elimino el turno con id="+id);
    }
}
