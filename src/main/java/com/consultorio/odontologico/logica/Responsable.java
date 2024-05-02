package com.consultorio.odontologico.logica;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Responsable extends Persona{
    private int id_responsable;
    private String tipo_rest;

    public Responsable(int id_responsable, String tipo_rest, String dni, String nombre, String apellido, String telefono, String direccion, Date fecha_nac) {
        super(dni, nombre, apellido, telefono, direccion, fecha_nac);
        this.id_responsable = id_responsable;
        this.tipo_rest = tipo_rest;
    }
    
}
