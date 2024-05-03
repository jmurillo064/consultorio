package com.consultorio.odontologico.persistencia;

import com.consultorio.odontologico.logica.Paciente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.consultorio.odontologico.logica.Turno;
import com.consultorio.odontologico.persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PacienteJpaController implements Serializable {

    public PacienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public PacienteJpaController() {
        emf = Persistence.createEntityManagerFactory("ConsultorioOdontologico_PU");
    }

    public void create(Paciente paciente) {
        if (paciente.getLista_turno() == null) {
            paciente.setLista_turno(new ArrayList<Turno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Turno> attachedLista_turno = new ArrayList<Turno>();
            for (Turno lista_turnoTurnoToAttach : paciente.getLista_turno()) {
                lista_turnoTurnoToAttach = em.getReference(lista_turnoTurnoToAttach.getClass(), lista_turnoTurnoToAttach.getId_turno());
                attachedLista_turno.add(lista_turnoTurnoToAttach);
            }
            paciente.setLista_turno(attachedLista_turno);
            em.persist(paciente);
            for (Turno lista_turnoTurno : paciente.getLista_turno()) {
                Paciente oldPacienteOfLista_turnoTurno = lista_turnoTurno.getPaciente();
                lista_turnoTurno.setPaciente(paciente);
                lista_turnoTurno = em.merge(lista_turnoTurno);
                if (oldPacienteOfLista_turnoTurno != null) {
                    oldPacienteOfLista_turnoTurno.getLista_turno().remove(lista_turnoTurno);
                    oldPacienteOfLista_turnoTurno = em.merge(oldPacienteOfLista_turnoTurno);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Paciente paciente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paciente persistentPaciente = em.find(Paciente.class, paciente.getId());
            List<Turno> lista_turnoOld = persistentPaciente.getLista_turno();
            List<Turno> lista_turnoNew = paciente.getLista_turno();
            List<Turno> attachedLista_turnoNew = new ArrayList<Turno>();
            for (Turno lista_turnoNewTurnoToAttach : lista_turnoNew) {
                lista_turnoNewTurnoToAttach = em.getReference(lista_turnoNewTurnoToAttach.getClass(), lista_turnoNewTurnoToAttach.getId_turno());
                attachedLista_turnoNew.add(lista_turnoNewTurnoToAttach);
            }
            lista_turnoNew = attachedLista_turnoNew;
            paciente.setLista_turno(lista_turnoNew);
            paciente = em.merge(paciente);
            for (Turno lista_turnoOldTurno : lista_turnoOld) {
                if (!lista_turnoNew.contains(lista_turnoOldTurno)) {
                    lista_turnoOldTurno.setPaciente(null);
                    lista_turnoOldTurno = em.merge(lista_turnoOldTurno);
                }
            }
            for (Turno lista_turnoNewTurno : lista_turnoNew) {
                if (!lista_turnoOld.contains(lista_turnoNewTurno)) {
                    Paciente oldPacienteOfLista_turnoNewTurno = lista_turnoNewTurno.getPaciente();
                    lista_turnoNewTurno.setPaciente(paciente);
                    lista_turnoNewTurno = em.merge(lista_turnoNewTurno);
                    if (oldPacienteOfLista_turnoNewTurno != null && !oldPacienteOfLista_turnoNewTurno.equals(paciente)) {
                        oldPacienteOfLista_turnoNewTurno.getLista_turno().remove(lista_turnoNewTurno);
                        oldPacienteOfLista_turnoNewTurno = em.merge(oldPacienteOfLista_turnoNewTurno);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = paciente.getId();
                if (findPaciente(id) == null) {
                    throw new NonexistentEntityException("The paciente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paciente paciente;
            try {
                paciente = em.getReference(Paciente.class, id);
                paciente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paciente with id " + id + " no longer exists.", enfe);
            }
            List<Turno> lista_turno = paciente.getLista_turno();
            for (Turno lista_turnoTurno : lista_turno) {
                lista_turnoTurno.setPaciente(null);
                lista_turnoTurno = em.merge(lista_turnoTurno);
            }
            em.remove(paciente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Paciente> findPacienteEntities() {
        return findPacienteEntities(true, -1, -1);
    }

    public List<Paciente> findPacienteEntities(int maxResults, int firstResult) {
        return findPacienteEntities(false, maxResults, firstResult);
    }

    private List<Paciente> findPacienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Paciente.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Paciente findPaciente(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Paciente.class, id);
        } finally {
            em.close();
        }
    }

    public int getPacienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Paciente> rt = cq.from(Paciente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
