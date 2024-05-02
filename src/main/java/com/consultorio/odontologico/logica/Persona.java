package com.consultorio.odontologico.logica;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Persona {
    private String dni;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private Date fecha_nac;
}
