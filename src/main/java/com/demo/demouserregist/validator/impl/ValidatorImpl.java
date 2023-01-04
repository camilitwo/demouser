package com.demo.demouserregist.validator.impl;

import com.demo.demouserregist.dto.UsuarioDTO;
import com.demo.demouserregist.exception.UserException;
import com.demo.demouserregist.repository.UsuarioRepository;
import com.demo.demouserregist.util.ConstantsUtils;
import com.demo.demouserregist.util.EmailValidator;
import com.demo.demouserregist.validator.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
@Slf4j
public class ValidatorImpl implements Validator {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Override
    public void validateEmail(UsuarioDTO usuarioDto) throws UserException {
        if (!EmailValidator.validateEmail(usuarioDto.getEmail())) {
            log.error("El email ingresado no es válido");
            throw new UserException(ConstantsUtils.EMAIL_INVALID);
        }

        if(Objects.nonNull(usuarioRepository.findByEmail(usuarioDto.getEmail()))){
            log.error("El email ingresado ya existe");
            throw new UserException(ConstantsUtils.USUARIO_EXIST);
        }
    }

    @Override
    public void valildateObject(UsuarioDTO usuarioDto) throws UserException {
        if(Objects.isNull(usuarioDto)){
            log.error("body cannot be null");
            throw new UserException(ConstantsUtils.BODY_NULL);
        }

        Optional.of(usuarioDto).filter(u -> Objects.nonNull(u.getName()) && !u.getName().isEmpty())
                .orElseThrow(() -> new UserException(ConstantsUtils.NAME_NULL));

        Optional.of(usuarioDto).filter(u -> Objects.nonNull(u.getEmail()) && !u.getEmail().isEmpty())
                .orElseThrow(() -> new UserException(ConstantsUtils.EMAIL_NULL));

        Optional.of(usuarioDto).filter(u -> Objects.nonNull(u.getPassword()) && !u.getPassword().isEmpty())
                .orElseThrow(() -> new UserException(ConstantsUtils.PASSWORD_NULL));

        this.validateEmail(usuarioDto);
        this.validatePassword(usuarioDto);
    }

    @Override
    public void validatePassword(UsuarioDTO usuarioDto) throws UserException {
        Optional.ofNullable(usuarioDto).filter(u -> u.getPassword().matches("^(?=.*\\d)(?=.*[@$!%*?&]).{8,}$"))
                .orElseThrow(() -> new UserException(ConstantsUtils.PASSWORD_INVALID));
    }
}
