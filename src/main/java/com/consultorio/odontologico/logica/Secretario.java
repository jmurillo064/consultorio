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
public class Secretario extends Persona{
    private int id_secretario;
    private String sector;
    private Usuario usuario;

    public Secretario(int id_secretario, String sector, Usuario usuario, String dni, String nombre, String apellido, String telefono, String direccion, Date fecha_nac) {
        super(dni, nombre, apellido, telefono, direccion, fecha_nac);
        this.id_secretario = id_secretario;
        this.sector = sector;
        this.usuario = usuario;
    }
    
}
