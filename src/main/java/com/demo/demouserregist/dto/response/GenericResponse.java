package com.demo.demouserregist.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GenericResponse {

        private String mensaje;
        private Object data;

        public GenericResponse(String mensaje, Object data) {
                this.mensaje = mensaje;
                this.data = data;
        }
}
