package com.demo.demouserregist.validator;

import com.demo.demouserregist.dto.UsuarioDTO;
import com.demo.demouserregist.exception.UserException;

public interface Validator {
    void validateEmail(UsuarioDTO usuarioDto) throws UserException;

    void valildateObject(UsuarioDTO usuarioDto) throws UserException;

    void validatePassword(UsuarioDTO usuarioDto) throws UserException;
}
