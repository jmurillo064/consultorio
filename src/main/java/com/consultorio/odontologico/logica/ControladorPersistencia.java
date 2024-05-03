package com.consultorio.odontologico.logica;

import com.consultorio.odontologico.persistencia.HorarioJpaController;
import com.consultorio.odontologico.persistencia.OdontologoJpaController;
import com.consultorio.odontologico.persistencia.PacienteJpaController;
import com.consultorio.odontologico.persistencia.PersonaJpaController;
import com.consultorio.odontologico.persistencia.ResponsableJpaController;
import com.consultorio.odontologico.persistencia.SecretarioJpaController;
import com.consultorio.odontologico.persistencia.TurnoJpaController;
import com.consultorio.odontologico.persistencia.UsuarioJpaController;

public class ControladorPersistencia {
    
    HorarioJpaController horarioJpaController = new HorarioJpaController();
    OdontologoJpaController odontologoJpaController = new OdontologoJpaController();
    PacienteJpaController pacienteJpaController = new PacienteJpaController();
    PersonaJpaController personaJpaController = new PersonaJpaController();
    ResponsableJpaController responsableJpaController = new ResponsableJpaController();
    SecretarioJpaController secretarioJpaController = new SecretarioJpaController();
    TurnoJpaController turnoJpaController = new TurnoJpaController();
    UsuarioJpaController usuarioJpaController = new UsuarioJpaController();
    
}
