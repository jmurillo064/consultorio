package com.consultorio.odontologico.logica;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Odontologo extends Persona{
    private int id_odontologo;
    private String especialidad;
    private List<Turno> lista_turno;
    private Usuario usuario;
    private Horario horario;

    public Odontologo(int id_odontologo, String especialidad, List<Turno> lista_turno, Usuario usuario, Horario horario, String dni, String nombre, String apellido, String telefono, String direccion, Date fecha_nac) {
        super(dni, nombre, apellido, telefono, direccion, fecha_nac);
        this.id_odontologo = id_odontologo;
        this.especialidad = especialidad;
        this.lista_turno = lista_turno;
        this.usuario = usuario;
        this.horario = horario;
    }
    
}
