package com.demo.demouserregist.dto;

import com.demo.demouserregist.model.Phones;
import lombok.Data;

import java.util.List;

@Data
public class UsuarioDTO {

    private String name;
    private String email;
    private String password;
    private List<Phones> phones;

}
