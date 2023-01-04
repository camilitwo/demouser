package com.demo.demouserregist.service;

import com.demo.demouserregist.dto.UsuarioDTO;
import com.demo.demouserregist.dto.response.GenericResponse;

public interface UsuarioService {
    GenericResponse registrarUsuario(UsuarioDTO usuarioDto);

    GenericResponse listarUsuarios();

    GenericResponse listarPhones();
}
