package com.example.IntegradorFinalNicolasMontero.Controller;

import com.example.IntegradorFinalNicolasMontero.Entity.RolUsuario;
import com.example.IntegradorFinalNicolasMontero.Entity.Usuario;
import com.example.IntegradorFinalNicolasMontero.Service.OdontologoService;
import com.example.IntegradorFinalNicolasMontero.Service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    UsuarioService usuarioService;

    @Test
    public void usuarioControllerTest() throws Exception{
        Usuario usuario1 = new Usuario("Nicolas", "nicoM", "nico.montero@gmail.com", "riverP", RolUsuario.ROLE_USER);

        //testeamos metodo para guardar usuario
        String payloadJson = "{\n" + "  \"nombre\": \n" + " \"Nicolas\"\n" + ",\n" + "  \"userName\": \n" + " \"nicoM\"\n" + ",\n" + "  \"mail\": \"nico.montero@gmail.com\",\n" + "  \"password\": \"riverP\"\n" +"}";

        ResultActions respuesta2 = mockMvc.perform(MockMvcRequestBuilders.post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payloadJson))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Nicolas"));
    }
}