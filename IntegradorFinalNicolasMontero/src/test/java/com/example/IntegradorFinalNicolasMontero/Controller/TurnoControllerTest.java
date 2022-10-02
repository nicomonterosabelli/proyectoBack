package com.example.IntegradorFinalNicolasMontero.Controller;

import com.example.IntegradorFinalNicolasMontero.Entity.Domicilio;
import com.example.IntegradorFinalNicolasMontero.Entity.Odontologo;
import com.example.IntegradorFinalNicolasMontero.Entity.Paciente;
import com.example.IntegradorFinalNicolasMontero.Entity.Turno;
import com.example.IntegradorFinalNicolasMontero.Service.OdontologoService;
import com.example.IntegradorFinalNicolasMontero.Service.PacienteService;
import com.example.IntegradorFinalNicolasMontero.Service.TurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class TurnoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    TurnoService turnoService;
    @Autowired
    PacienteService pacienteService;
    @Autowired
    OdontologoService odontologoService;

    @Test
    public void turnoControllerTest () throws Exception {
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
        Turno turnoGuardado1 = turnoService.guardar(turno1);



        //testeamos metodo listar todos
        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.get("/turnos").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());

        //testeamos metodo get by id
        MvcResult respuesta1 = mockMvc.perform(MockMvcRequestBuilders.get("/turnos/1"))
                        .andDo(print()).andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.paciente.dni").value(40345282))
                        .andReturn();

        assertEquals("application/json", respuesta1.getResponse().getContentType());

        //testeamos metodo para subir un turno

        String payloadJson = "{\n" + "  \"odontologo\": {\n" + "    \"id\": 2\n" + "  },\n" + "  \"paciente\": {\n" + "    \"id\": 2\n" + "  },\n" + "  \"fechaYHora\": \"2022-09-23T13:32\"\n" + "}";


        ResultActions respuesta2 = mockMvc.perform(MockMvcRequestBuilders.post("/turnos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fechaYHora").value("2022-09-23T13:32:00"));

        //testeamos metodo para actualizar un turno

        String payloadJson2 = "{\n" +
                "    \"id\": \"2\",\n" +
                "    \"odontologo\": {\n" +
                "        \"id\": 1\n" +
                "    },\n" +
                "    \"paciente\": {\n" +
                "        \"id\": 1\n" +
                "    },\n" +
                "    \"fechaYHora\": \"2022-09-19T11:42:00\"\n" +
                "}";


        ResultActions respuesta3 = mockMvc.perform(MockMvcRequestBuilders.put("/turnos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson2))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fechaYHora").value("2022-09-19T11:42:00"));

        //testeamos metodo para eliminar un turno

        MvcResult respuesta4 = mockMvc.perform(MockMvcRequestBuilders.delete("/turnos/2"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"))
                .andReturn();
    }
}