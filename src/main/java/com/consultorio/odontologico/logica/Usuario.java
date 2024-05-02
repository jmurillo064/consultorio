package com.consultorio.odontologico.logica;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {
    private int id_usuario;
    private String nombre_usuario;
    private String contrasenia;
    private String rol;
}
