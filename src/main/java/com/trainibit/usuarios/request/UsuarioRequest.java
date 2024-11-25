package com.trainibit.usuarios.request;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioRequest {

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password; // Si necesitas recibirla

    @NotNull
    private LocalDate birthDate;

    @NotNull
    private String lastName;
}
