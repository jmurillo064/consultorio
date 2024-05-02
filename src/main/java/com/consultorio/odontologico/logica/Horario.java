package com.consultorio.odontologico.logica;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Horario {
    private int id_horario;
    private String horario_inicio;
    private String horario_fin;
}
