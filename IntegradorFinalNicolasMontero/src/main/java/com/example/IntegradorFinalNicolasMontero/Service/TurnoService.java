package com.example.IntegradorFinalNicolasMontero.Service;
import com.example.IntegradorFinalNicolasMontero.Entity.Turno;
import com.example.IntegradorFinalNicolasMontero.Exceptions.BadRequestException;
import com.example.IntegradorFinalNicolasMontero.Exceptions.ResourceNotFoundException;
import com.example.IntegradorFinalNicolasMontero.Repository.OdontologoRepository;
import com.example.IntegradorFinalNicolasMontero.Repository.PacienteRepository;
import com.example.IntegradorFinalNicolasMontero.Repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    private final TurnoRepository turnoRepository;
    private final PacienteService pacienteService;
    private final OdontologoService odontologoService;
    @Autowired
    public TurnoService(TurnoRepository turnoRepository, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }


    public Turno guardar (Turno turno) throws BadRequestException {
        if(pacienteService.buscar(turno.getPaciente().getId()) != null || odontologoService.buscar(turno.getOdontologo().getId()) != null){
            return turnoRepository.save(turno);
        }
        else{
            throw new BadRequestException("El paciente u odontologo no existe");
        }
    }
    public Turno buscar (Long id) {
        Optional <Turno> turno = turnoRepository.findById(id);
        if (turno.isPresent()){
            return turno.get();
        }
        return null;
    }

    public List<Turno> buscarTodos() {
        return turnoRepository.findAll();
    }
    public Turno actualizar (Turno turno) {return turnoRepository.save(turno);}
    public void eliminar (Long id) throws ResourceNotFoundException {
        Turno turnoAEliminar= buscar(id);
        if (turnoAEliminar != null){
            turnoRepository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("No existe el id del turno ingresado - id="+id);
        }
    }
}
