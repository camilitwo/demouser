package com.demo.demouserregist.controller;

import com.demo.demouserregist.dto.UsuarioDTO;
import com.demo.demouserregist.dto.response.GenericResponse;
import com.demo.demouserregist.model.Phones;
import com.demo.demouserregist.model.Usuario;
import com.demo.demouserregist.repository.TelefonoRepository;
import com.demo.demouserregist.repository.UsuarioRepository;
import com.demo.demouserregist.service.UsuarioService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
@RunWith(SpringRunner.class)
@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UsuarioService usuarioService;

    @MockBean
    UsuarioRepository usuarioRepository;

    @MockBean
    TelefonoRepository telefonoRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void registrarUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setEmail("a@a.cl");
        usuario.setName("a");
        usuario.setPassword("a");
        Phones phones = new Phones();
        phones.setCitycode("1");
        phones.setContrycode("1");
        phones.setNumber("1");
        usuario.setPhones(Arrays.asList(phones));

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        BeanUtils.copyProperties(usuarioDTO, usuario);

        when(usuarioRepository.save(any())).thenReturn(usuario);
        when(usuarioService.registrarUsuario(any())).thenReturn(new GenericResponse(null ,usuarioDTO));

        MockHttpServletResponse response = mockMvc.perform(post("/usuarios/")
                        //.header("Authorization", "Bearer token")
                        .content("{\"cardID\": \"123123\"}").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void listarUsuarios() throws Exception {

        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(new Usuario()));
        MockHttpServletResponse response = mockMvc.perform(get("/usuarios/")
                        //.header("Authorization", "Bearer token")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }

    @Test
    void listarPhones() throws Exception {

        when(telefonoRepository.findAll()).thenReturn(Arrays.asList(new Phones()));
        MockHttpServletResponse response = mockMvc.perform(get("/usuarios/phones")
                        //.header("Authorization", "Bearer token")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }
}