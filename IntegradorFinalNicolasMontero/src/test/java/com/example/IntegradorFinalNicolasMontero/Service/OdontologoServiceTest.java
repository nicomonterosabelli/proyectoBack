package com.example.IntegradorFinalNicolasMontero.Service;

import com.example.IntegradorFinalNicolasMontero.Entity.Odontologo;
import com.example.IntegradorFinalNicolasMontero.Service.OdontologoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OdontologoServiceTest {
    @Autowired
    OdontologoService odontologoService;

    @Test
    public void OdontologoTest() throws Exception{
        Odontologo odontologo1 = new Odontologo(3456, "Nicolas", "Montero");
        Odontologo odontologo2 = new Odontologo(7890, "Barbara", "Sabelli");

        //Testeando metodo guardar
        Odontologo odontologoPrueba1 = odontologoService.guardar(odontologo1);
        Odontologo odontologoPrueba2 = odontologoService.guardar(odontologo2);
        assertEquals(1, odontologoPrueba1.getId());
        assertEquals(2, odontologoPrueba2.getId());

        //Testeando metodo listar todos
        List<Odontologo> listaOdontologos = odontologoService.buscarTodos();
        assertEquals(2, listaOdontologos.size());

        //Testeamos metodo eliminar
        odontologoService.eliminar(1L);
        List<Odontologo> listaOdontologos2 = odontologoService.buscarTodos();
        assertEquals(1, listaOdontologos2.size());

        //Testeamos metodo actualizar
        Odontologo odontologo3 = new Odontologo(2L,1212, "Barbara", "Sabelli");
        odontologoService.actualizar(odontologo3);

        //Testeando metodo buscar
        Odontologo odontologoAct = odontologoService.buscar(2L);
        assertEquals(1212, odontologoAct.getNumeroDeMatricula());

    }
}