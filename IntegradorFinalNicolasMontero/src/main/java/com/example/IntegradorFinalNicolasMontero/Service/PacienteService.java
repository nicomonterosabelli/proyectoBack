package com.example.IntegradorFinalNicolasMontero.Service;

import com.example.IntegradorFinalNicolasMontero.Entity.Odontologo;
import com.example.IntegradorFinalNicolasMontero.Entity.Paciente;
import com.example.IntegradorFinalNicolasMontero.Entity.Turno;
import com.example.IntegradorFinalNicolasMontero.Exceptions.AlreadyExistException;
import com.example.IntegradorFinalNicolasMontero.Exceptions.ResourceNotFoundException;
import com.example.IntegradorFinalNicolasMontero.Repository.DomicilioRepository;
import com.example.IntegradorFinalNicolasMontero.Repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {

        this.pacienteRepository = pacienteRepository;
    }


    public Paciente guardar (Paciente paciente) throws AlreadyExistException {
        List<Paciente> pacientes=buscarTodos();
        for (Paciente pac:pacientes) {
            if (pac.getDni() == paciente.getDni()){
                throw new AlreadyExistException("El paciente ya existe en la base de datos");
            }
        }
        return pacienteRepository.save(paciente);
    }
    public Paciente buscar (Long id) {
        Optional <Paciente> paciente = pacienteRepository.findById(id);
        if (paciente.isPresent()){
            return paciente.get();
        }
        return null;
    }
    public List<Paciente> buscarTodos() {
        return pacienteRepository.findAll();
    }
    public Paciente actualizar (Paciente paciente) {
        return pacienteRepository.save(paciente);
    }
    public void eliminar (Long id) throws ResourceNotFoundException {
        Paciente pacienteAEliminar= buscar(id);
        if (pacienteAEliminar != null){
            pacienteRepository.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("No existe el id del paciente ingresado - id="+id);
        }
    }
}
