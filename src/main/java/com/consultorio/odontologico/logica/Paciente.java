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
public class Paciente extends Persona{
    private int id_paciente;
    private boolean tiene_os;
    private String tipo_sangre;
    private Responsable responsable;
    private List<Turno> lista_turno;

    public Paciente(int id_paciente, boolean tiene_os, String tipo_sangre, Responsable responsable, List<Turno> lista_turno, String dni, String nombre, String apellido, String telefono, String direccion, Date fecha_nac) {
        super(dni, nombre, apellido, telefono, direccion, fecha_nac);
        this.id_paciente = id_paciente;
        this.tiene_os = tiene_os;
        this.tipo_sangre = tipo_sangre;
        this.responsable = responsable;
        this.lista_turno = lista_turno;
    }
    
}
