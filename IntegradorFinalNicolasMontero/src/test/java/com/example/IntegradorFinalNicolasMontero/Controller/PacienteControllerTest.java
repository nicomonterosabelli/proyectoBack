package com.example.IntegradorFinalNicolasMontero.Controller;

import com.example.IntegradorFinalNicolasMontero.Entity.Domicilio;
import com.example.IntegradorFinalNicolasMontero.Entity.Paciente;
import com.example.IntegradorFinalNicolasMontero.Service.OdontologoService;
import com.example.IntegradorFinalNicolasMontero.Service.PacienteService;
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

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class PacienteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    PacienteService pacienteService;

    @Test
    public void pacienteControllerTest() throws Exception{
        LocalDate localDate = LocalDate.of(2019, 2, 20);
        Domicilio domicilio1 = new Domicilio("calle1", 345, "La Plata", "Buenos Aires");
        Domicilio domicilio2 = new Domicilio("calle2", 543, "Berisso", "Buenos Aires");
        Paciente paciente1 = new Paciente("Montero", "Nicolas", 40345282, localDate, domicilio1, "nico@gmail.com");
        Paciente paciente2 = new Paciente("Sabelli", "Nicolas", 43456367, localDate, domicilio2, "mati@gmail.com");

        pacienteService.guardar(paciente1);

        //testeamos metodo listar todos
        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.get("/pacientes").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());

        //testeamos metodo get by id
        MvcResult respuesta1 = mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.dni").value(40345282))
                .andReturn();

        assertEquals("application/json", respuesta1.getResponse().getContentType());

        //testeamos metodo para subir un paciente

        String payloadJson = "{\n" + "  \"apellido\": \n" + " \"Sabelli\"\n" + ",\n" + "  \"nombre\": \n" + " \"Nicolas\"\n" + "  ,\n" + "  \"dni\": 43456367,\n" + "\"fecha\": \n" + " \"2022-09-23\",\n" + "\"domicilio\": {\n" + "    \"calle\": \"calle2\"\n," +"    \"numero\": 543\n," + "    \"localidad\": \"Berisso\",\n" +"    \"provincia\": \"Buenos Aires\"\n" +"  },\n" +"    \"mail\": \"mati@gmail.com\"\n" +"}";


        ResultActions respuesta2 = mockMvc.perform(MockMvcRequestBuilders.post("/pacientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.apellido").value("Sabelli"));

        //testeamos metodo para actualizar un paciente

        String payloadJson1 = "{\n" +"  \"id\": \n" + " 2\n" + ",\n" + "  \"apellido\": \n" + " \"Sabelli\"\n" + ",\n" + "  \"nombre\": \n" + " \"Barbara\"\n" + "  ,\n" + "  \"dni\": 43456367,\n" + "\"fecha\": \n" + " \"2022-09-23\",\n" + "\"domicilio\": {\n" + "    \"calle\": \"calle2\"\n," +"    \"numero\": 543\n," + "    \"localidad\": \"Berisso\",\n" +"    \"provincia\": \"Buenos Aires\"\n" +"  },\n" +"    \"mail\": \"mati@gmail.com\"\n" +"}";


        ResultActions respuesta3 = mockMvc.perform(MockMvcRequestBuilders.put("/pacientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson1))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Barbara"));

        //testeamos metodo para eliminar un paciente

        MvcResult respuesta4 = mockMvc.perform(MockMvcRequestBuilders.delete("/pacientes/2"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"))
                .andReturn();
    }
}