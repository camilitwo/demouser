package com.demo.demouserregist.service.impl;

import com.demo.demouserregist.dto.UsuarioDTO;
import com.demo.demouserregist.dto.response.GenericResponse;
import com.demo.demouserregist.exception.UserException;
import com.demo.demouserregist.model.Phones;
import com.demo.demouserregist.model.Usuario;
import com.demo.demouserregist.repository.TelefonoRepository;
import com.demo.demouserregist.repository.UsuarioRepository;
import com.demo.demouserregist.util.ConstantsUtils;
import com.demo.demouserregist.validator.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class UsuarioServiceImplTest {

    @Mock
    UsuarioRepository usuarioRepository;
    @Mock
    TelefonoRepository telefonoRepository;
    @Mock
    Validator validator;
    @InjectMocks
    UsuarioServiceImpl usuarioService;
    @BeforeEach
    public void setUp() {MockitoAnnotations.initMocks(this);}
    @Test
    void registrarUsuario() throws UserException {
        UsuarioDTO usuarioDTO = setUsuarioDto();
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuario);
        usuario.setUuid(UUID.randomUUID());
        doNothing().when(validator).valildateObject(any());
        doNothing().when(validator).validateEmail(any());
        when(usuarioRepository.save(any())).thenReturn(usuario);
        GenericResponse response = usuarioService.registrarUsuario(usuarioDTO);
        Assertions.assertEquals(ConstantsUtils.USER_CREATED, response.getMensaje());
    }

    @Test
    void registrarUsuario_NOK() throws UserException {
        doThrow(new UserException(ConstantsUtils.BODY_NULL)).when(validator).valildateObject(null);
        GenericResponse response = usuarioService.registrarUsuario(null);
        Assertions.assertEquals(ConstantsUtils.BODY_NULL, response.getMensaje());
    }

    @Test
    void registrarUsuario_NOK2() throws UserException {
        doThrow(new NullPointerException("Error")).when(validator).valildateObject(null);
        GenericResponse response = usuarioService.registrarUsuario(null);
        Assertions.assertEquals("Error al Registar Usuario", response.getMensaje());
    }

    @Test
    void registrarUsuario_ExceptionName() throws UserException {
        doThrow(new UserException(ConstantsUtils.NAME_NULL)).when(validator).valildateObject(any());
        GenericResponse response = usuarioService.registrarUsuario(new UsuarioDTO());
        Assertions.assertEquals(ConstantsUtils.NAME_NULL, response.getMensaje());
    }

    @Test
    void registrarUsuario_ExceptionEmailNull() throws UserException {
        UsuarioDTO usuarioDTO = setUsuarioDto();
        usuarioDTO.setEmail(null);
        doThrow(new UserException(ConstantsUtils.EMAIL_NULL)).when(validator).valildateObject(any());
        GenericResponse response = usuarioService.registrarUsuario(usuarioDTO);
        Assertions.assertEquals(ConstantsUtils.EMAIL_NULL, response.getMensaje());
    }

    @Test
    void registrarUsuario_ExceptionPassword() throws UserException {
        doThrow(new UserException(ConstantsUtils.PASSWORD_NULL)).when(validator).valildateObject(any());
        GenericResponse response = usuarioService.registrarUsuario(new UsuarioDTO());
        Assertions.assertEquals(ConstantsUtils.PASSWORD_NULL, response.getMensaje());
    }

    @Test
    void registrarUsuario_ExceptionPasswordFormat() throws UserException {
        doThrow(new UserException(ConstantsUtils.PASSWORD_INVALID)).when(validator).valildateObject(any());
        GenericResponse response = usuarioService.registrarUsuario(new UsuarioDTO());
        Assertions.assertEquals(ConstantsUtils.PASSWORD_INVALID, response.getMensaje());
    }



    @Test
    void registrarUsuario_ExceptionEmailFormat() throws UserException {
        doThrow(new UserException(ConstantsUtils.EMAIL_INVALID)).when(validator).validateEmail(any());
        GenericResponse response = usuarioService.registrarUsuario(new UsuarioDTO());
        Assertions.assertEquals(ConstantsUtils.EMAIL_INVALID, response.getMensaje());
    }

    @Test
    void registrarUsuario_ExceptionEmailExist() throws UserException {
        when(usuarioRepository.findByEmail(any())).thenReturn(new Usuario());
        doThrow(new UserException(ConstantsUtils.USUARIO_EXIST)).when(validator).validateEmail(any());
        GenericResponse response = usuarioService.registrarUsuario(new UsuarioDTO());
        Assertions.assertEquals(ConstantsUtils.USUARIO_EXIST, response.getMensaje());
    }

    private static UsuarioDTO setUsuarioDto() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setEmail("a@a.cl");
        usuarioDTO.setName("a");
        usuarioDTO.setPassword("a");
        Phones phones = new Phones();
        phones.setCitycode("1");
        phones.setContrycode("1");
        phones.setNumber("1");
        usuarioDTO.setPhones(Arrays.asList(phones));
        return usuarioDTO;
    }

    @Test
    void listarUsuarios() {
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(new Usuario()));
        GenericResponse response = usuarioService.listarUsuarios();
        Assertions.assertNotNull(response.getData());
    }

    @Test
    void listarPhones() {
        when(telefonoRepository.findAll()).thenReturn(Arrays.asList(new Phones()));
        GenericResponse response = usuarioService.listarPhones();
        Assertions.assertNotNull(response.getData());
    }
}