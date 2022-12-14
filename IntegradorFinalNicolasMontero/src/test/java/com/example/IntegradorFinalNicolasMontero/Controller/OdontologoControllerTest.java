package com.example.IntegradorFinalNicolasMontero.Controller;

import com.example.IntegradorFinalNicolasMontero.Entity.Odontologo;
import com.example.IntegradorFinalNicolasMontero.Service.OdontologoService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class OdontologoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    OdontologoService odontologoService;

    @Test
    public void odontologoControllerTest () throws Exception{
        Odontologo odontologo1 = new Odontologo(3456, "Nicolas", "Montero");
        Odontologo odontologo2 = new Odontologo(7890, "Barbara", "Sabelli");

        Odontologo odontologoGuardar1 = odontologoService.guardar(odontologo1);

        //testeamos metodo listar todos
        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.get("/odontologos").accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk()).andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());

        //testeamos metodo get by id
        MvcResult respuesta1 = mockMvc.perform(MockMvcRequestBuilders.get("/odontologos/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.numeroDeMatricula").value(3456))
                .andReturn();

        assertEquals("application/json", respuesta1.getResponse().getContentType());

        //testeamos metodo para subir un odontologo

        String payloadJson = "{\n" + "  \"numeroDeMatricula\": \n" + " 7890\n" + ",\n" + "  \"nombre\": \n" + " \"Barbara\"\n" + ",\n" + "  \"apellido\": \"Sabelli\"\n" + "}";

        ResultActions respuesta2 = mockMvc.perform(MockMvcRequestBuilders.post("/odontologos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.apellido").value("Sabelli"));

        //testeamos metodo para actualizar un odontologo

        String payloadJson1 = "{\n" + "    \"id\": \"2\",\n" + "  \"numeroDeMatricula\": \n" + " 7890\n" + ",\n" + "  \"nombre\": \n" + " \"Barbara\"\n" + ",\n" + "  \"apellido\": \"Martinez\"\n" + "}";

        ResultActions respuesta3 = mockMvc.perform(MockMvcRequestBuilders.put("/odontologos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson1))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.apellido").value("Martinez"));

        //testeamos metodo para eliminar un turno

        MvcResult respuesta4 = mockMvc.perform(MockMvcRequestBuilders.delete("/odontologos/2"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=UTF-8"))
                .andReturn();
    }



}