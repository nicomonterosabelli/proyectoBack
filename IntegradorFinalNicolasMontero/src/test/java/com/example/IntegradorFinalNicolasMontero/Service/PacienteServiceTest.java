package com.example.IntegradorFinalNicolasMontero.Service;

import com.example.IntegradorFinalNicolasMontero.Entity.Domicilio;
import com.example.IntegradorFinalNicolasMontero.Entity.Paciente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PacienteServiceTest {
    @Autowired
    PacienteService pacienteService;
    @Test
    public void PacienteTest() throws Exception{
        LocalDate localDate = LocalDate.of(2019, 2, 20);
        Domicilio domicilio1 = new Domicilio("calle1", 345, "La Plata", "Buenos Aires");
        Domicilio domicilio2 = new Domicilio("calle2", 543, "Berisso", "Buenos Aires");
        Paciente paciente1 = new Paciente("Montero", "Nicolas", 40345282, localDate, domicilio1, "nico@gmail.com");
        Paciente paciente2 = new Paciente("Sabelli", "Nicolas", 43456367, localDate, domicilio2, "mati@gmail.com");

        //Testeando metodo guardar
        Paciente pacienteGuardar1 = pacienteService.guardar(paciente1);
        Paciente pacienteGuardar2 = pacienteService.guardar(paciente2);
        assertEquals(1, pacienteGuardar1.getId());
        assertEquals(1, pacienteGuardar1.getDomicilio().getId());
        assertEquals(2, pacienteGuardar2.getId());
        assertEquals(2, pacienteGuardar2.getDomicilio().getId());

        //Testeando metodo buscar
        Paciente pacienteBuscar = pacienteService.buscar(1L);
        assertEquals(40345282, pacienteBuscar.getDni());

        //Testeando metodo listar todos
        List<Paciente> listaO = pacienteService.buscarTodos();
        assertEquals(2, listaO.size());

        //Testeamos metodo eliminar
        pacienteService.eliminar(1L);
        List<Paciente> lista1 = pacienteService.buscarTodos();
        assertEquals(1, lista1.size());

        //Testeamos metodo actualizar
        Domicilio domicilio3 = new Domicilio(2L,"calle3", 222, "Berisso", "Buenos Aires");
        Paciente paciente3 = new Paciente(2L,"Montero", "Barbara", 40345281, localDate, domicilio3, "barbi@gmail.com");
        pacienteService.actualizar(paciente3);
        Paciente pacienteBuscar2= pacienteService.buscar(2L);
        assertEquals(40345281, pacienteBuscar2.getDni());
        assertEquals(222, pacienteBuscar2.getDomicilio().getNumero());
    }
}