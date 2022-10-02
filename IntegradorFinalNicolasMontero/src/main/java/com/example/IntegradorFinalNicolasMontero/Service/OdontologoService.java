package com.example.IntegradorFinalNicolasMontero.Service;

import com.example.IntegradorFinalNicolasMontero.Entity.Odontologo;
import com.example.IntegradorFinalNicolasMontero.Entity.Paciente;
import com.example.IntegradorFinalNicolasMontero.Exceptions.AlreadyExistException;
import com.example.IntegradorFinalNicolasMontero.Exceptions.BadRequestException;
import com.example.IntegradorFinalNicolasMontero.Exceptions.ResourceNotFoundException;
import com.example.IntegradorFinalNicolasMontero.Repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {
    private final OdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }


    public Odontologo guardar(Odontologo odontologo) throws AlreadyExistException {
        List<Odontologo> odontologos=buscarTodos();
        for (Odontologo odo:odontologos) {
            if (odo.getNumeroDeMatricula() == odontologo.getNumeroDeMatricula()){
                throw new AlreadyExistException("El odontologo ya existe en la base de datos");
            }
        }
        return odontologoRepository.save(odontologo);
    }

    public Odontologo buscar(Long id) {
        Optional <Odontologo> odontologo = odontologoRepository.findById(id);
        if (odontologo.isPresent()){
            return odontologo.get();
        }
        return null;
    }

    public List<Odontologo> buscarTodos() {
        return odontologoRepository.findAll();
    }

    public Odontologo actualizar(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    public void eliminar(Long id) throws ResourceNotFoundException {
        Odontologo odontologoAEliminar = buscar(id);
        if (odontologoAEliminar != null) {
            odontologoRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("No existe el id del odontologo ingresado - id=" + id);
        }
    }
}
