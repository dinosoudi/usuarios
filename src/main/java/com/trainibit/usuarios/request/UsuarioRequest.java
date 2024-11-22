package com.trainibit.usuarios.request;

import lombok.Data;

@Data
public class UsuarioRequest {
    private String name;
    private String email;
    private String password; // Si necesitas recibirla
    private String birthDate;
    private String lastName;

}
