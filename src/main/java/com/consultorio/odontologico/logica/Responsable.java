package com.consultorio.odontologico.logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Responsable extends Persona implements Serializable{
    //private int id_responsable;
    private String tipo_rest;

    public Responsable(String tipo_rest, int id, String dni, String nombre, String apellido, String telefono, String direccion, Date fecha_nac) {
        super(id, dni, nombre, apellido, telefono, direccion, fecha_nac);
        this.tipo_rest = tipo_rest;
    }
    
}
