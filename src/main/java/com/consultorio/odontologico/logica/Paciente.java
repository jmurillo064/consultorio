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
public class Paciente extends Persona implements Serializable{
    //private int id_paciente;
    private boolean tiene_os;
    private String tipo_sangre;
    @OneToOne
    private Responsable responsable;
    @OneToMany(mappedBy = "paciente")
    private List<Turno> lista_turno;

    public Paciente(boolean tiene_os, String tipo_sangre, Responsable responsable, List<Turno> lista_turno, int id, String dni, String nombre, String apellido, String telefono, String direccion, Date fecha_nac) {
        super(id, dni, nombre, apellido, telefono, direccion, fecha_nac);
        this.tiene_os = tiene_os;
        this.tipo_sangre = tipo_sangre;
        this.responsable = responsable;
        this.lista_turno = lista_turno;
    }
    
}
