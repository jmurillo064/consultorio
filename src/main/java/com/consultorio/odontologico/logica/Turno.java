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
public class Turno {
    private int id_turno;
    private Date fecha_turno;
    private String hora_turno;
    private String afeccion;
}
