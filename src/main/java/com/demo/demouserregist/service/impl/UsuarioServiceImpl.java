package com.demo.demouserregist.service.impl;

import com.demo.demouserregist.dto.UsuarioDTO;
import com.demo.demouserregist.dto.response.GenericResponse;
import com.demo.demouserregist.dto.response.UserResponse;
import com.demo.demouserregist.exception.UserException;
import com.demo.demouserregist.model.Usuario;
import com.demo.demouserregist.repository.TelefonoRepository;
import com.demo.demouserregist.repository.UsuarioRepository;
import com.demo.demouserregist.service.UsuarioService;
import com.demo.demouserregist.util.ConstantsUtils;
import com.demo.demouserregist.validator.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TelefonoRepository telefonoRepository;

    @Autowired
    Validator validator;

    @Override
    public GenericResponse registrarUsuario(UsuarioDTO usuarioDto) {
        GenericResponse response = new GenericResponse();
        try{
            validator.valildateObject(usuarioDto);
            Usuario usuario = new Usuario();
            BeanUtils.copyProperties(usuarioDto, usuario);
            Optional.of(usuarioRepository.save(usuario)).ifPresent(user -> {
                UserResponse userResponse = new UserResponse();
                userResponse.setId(user.getUuid().toString());
                userResponse.setCreated(new Date());
                userResponse.setModified(new Date());
                userResponse.setLastLogin(new Date());
                userResponse.setActive(Boolean.TRUE);
                response.setData(userResponse);
                response.setMensaje(ConstantsUtils.USER_CREATED);
            });

        }catch (UserException e){
            log.error("Error al registrar usuario", e);
            return new GenericResponse(e.getMessage(), null);
        }catch (Exception e){
            log.error("Error al registrar usuario", e);
            return new GenericResponse("Error al Registar Usuario", null);
        }
        return response;
    }

    @Override
    public GenericResponse listarUsuarios() {
        GenericResponse response = new GenericResponse();
        response.setData(usuarioRepository.findAll());
        return response;
    }

    @Override
    public GenericResponse listarPhones() {
        GenericResponse response = new GenericResponse();
        response.setData(telefonoRepository.findAll());
        return response;
    }
}
