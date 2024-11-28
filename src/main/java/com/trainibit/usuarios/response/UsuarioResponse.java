package com.trainibit.usuarios.response;


import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class UsuarioResponse {

    private String name;
    private String lastName;
    private String email;
    private String password;
    private LocalDate birthDate;
    private int edad;
    private UUID uuid;
    private String nombrePlaneta;



}
