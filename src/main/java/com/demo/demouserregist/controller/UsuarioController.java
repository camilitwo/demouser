package com.demo.demouserregist.controller;
import com.demo.demouserregist.dto.UsuarioDTO;
import com.demo.demouserregist.dto.response.GenericResponse;
import com.demo.demouserregist.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse> registrarUsuario(@RequestBody UsuarioDTO usuarioDto) {
        return new ResponseEntity<>(usuarioService.registrarUsuario(usuarioDto), HttpStatus.OK);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse> listarUsuarios() {
        GenericResponse response = usuarioService.listarUsuarios();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/phones", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse> listarPhones() {
        GenericResponse response = usuarioService.listarPhones();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
