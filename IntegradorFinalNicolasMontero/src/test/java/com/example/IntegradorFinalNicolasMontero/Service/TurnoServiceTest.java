package com.example.IntegradorFinalNicolasMontero.Service;
import com.example.IntegradorFinalNicolasMontero.Entity.Domicilio;
import com.example.IntegradorFinalNicolasMontero.Entity.Odontologo;
import com.example.IntegradorFinalNicolasMontero.Entity.Paciente;
import com.example.IntegradorFinalNicolasMontero.Entity.Turno;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TurnoServiceTest {
    @Autowired
    TurnoService turnoService;
    @Autowired
    PacienteService pacienteService;
    @Autowired
    OdontologoService odontologoService;
    @Test
    public void turnoTest() throws Exception{
        LocalDate localDate = LocalDate.of(2019, 2, 20);
        LocalDateTime localDateTime = LocalDateTime.now();
        Domicilio domicilio1 = new Domicilio("calle1", 345, "La Plata", "Buenos Aires");
        Domicilio domicilio2 = new Domicilio("calle2", 543, "Berisso", "Buenos Aires");
        Paciente paciente1 = new Paciente("Montero", "Nicolas", 40345282, localDate, domicilio1, "nico@gmail.com");
        Paciente paciente2 = new Paciente("Sabelli", "Nicolas", 43456367, localDate, domicilio2, "mati@gmail.com");
        Odontologo odontologo1 = new Odontologo(3456, "Nicolas", "Montero");
        Odontologo odontologo2 = new Odontologo(7890, "Barbara", "Sabelli");
        Paciente pacienteGuardar1 = pacienteService.guardar(paciente1);
        Paciente pacienteGuardar2 = pacienteService.guardar(paciente2);
        Odontologo odontologoGuardar1 = odontologoService.guardar(odontologo1);
        Odontologo odontologoGuardar2 = odontologoService.guardar(odontologo2);
        Turno turno1 = new Turno(odontologoGuardar1, pacienteGuardar1, localDateTime);
        Turno turno2 = new Turno(odontologoGuardar2, pacienteGuardar2, localDateTime);

        //Testeando metodo guardar
        Turno turnoGuardado1 = turnoService.guardar(turno1);
        Turno turnoGuardado2 = turnoService.guardar(turno2);
        assertEquals(1, turnoGuardado1.getId());
        assertEquals(2, turnoGuardado2.getId());

        //Testeando metodo buscar
        Turno turnoBuscar = turnoService.buscar(1L);
        assertEquals(40345282, turnoBuscar.getPaciente().getDni());

        //Testeando metodo listar todos
        List<Turno> listaO = turnoService.buscarTodos();
        assertEquals(2, listaO.size());

        //Testeamos metodo eliminar
        turnoService.eliminar(1L);
        List<Turno> lista1 = turnoService.buscarTodos();
        assertEquals(1, lista1.size());

        //Testeamos metodo actualizar
        Turno turno3 = new Turno(2L, odontologo1, paciente1, localDateTime);
        turnoService.actualizar(turno3);
        Turno turnoActualizado = turnoService.buscar(2L);
        assertEquals(40345282, turnoActualizado.getPaciente().getDni());
        assertEquals(3456, turnoActualizado.getOdontologo().getNumeroDeMatricula());
    }
}