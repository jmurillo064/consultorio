package com.consultorio.odontologico.logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Odontologo extends Persona implements Serializable{
    //private int id_odontologo;
    private String especialidad;
    @OneToMany(mappedBy = "odontologo")
    private List<Turno> lista_turno;
    @OneToOne
    private Usuario usuario;
    @OneToOne
    private Horario horario;

    public Odontologo(String especialidad, List<Turno> lista_turno, Usuario usuario, Horario horario, int id, String dni, String nombre, String apellido, String telefono, String direccion, Date fecha_nac) {
        super(id, dni, nombre, apellido, telefono, direccion, fecha_nac);
        this.especialidad = especialidad;
        this.lista_turno = lista_turno;
        this.usuario = usuario;
        this.horario = horario;
    }
  
}
