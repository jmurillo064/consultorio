package com.consultorio.odontologico.persistencia;

import com.consultorio.odontologico.logica.Odontologo;
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

public class OdontologoJpaController implements Serializable {

    public OdontologoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public OdontologoJpaController() {
        emf = Persistence.createEntityManagerFactory("ConsultorioOdontologico_PU");
    }

    public void create(Odontologo odontologo) {
        if (odontologo.getLista_turno() == null) {
            odontologo.setLista_turno(new ArrayList<Turno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Turno> attachedLista_turno = new ArrayList<Turno>();
            for (Turno lista_turnoTurnoToAttach : odontologo.getLista_turno()) {
                lista_turnoTurnoToAttach = em.getReference(lista_turnoTurnoToAttach.getClass(), lista_turnoTurnoToAttach.getId_turno());
                attachedLista_turno.add(lista_turnoTurnoToAttach);
            }
            odontologo.setLista_turno(attachedLista_turno);
            em.persist(odontologo);
            for (Turno lista_turnoTurno : odontologo.getLista_turno()) {
                Odontologo oldOdontologoOfLista_turnoTurno = lista_turnoTurno.getOdontologo();
                lista_turnoTurno.setOdontologo(odontologo);
                lista_turnoTurno = em.merge(lista_turnoTurno);
                if (oldOdontologoOfLista_turnoTurno != null) {
                    oldOdontologoOfLista_turnoTurno.getLista_turno().remove(lista_turnoTurno);
                    oldOdontologoOfLista_turnoTurno = em.merge(oldOdontologoOfLista_turnoTurno);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Odontologo odontologo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Odontologo persistentOdontologo = em.find(Odontologo.class, odontologo.getId());
            List<Turno> lista_turnoOld = persistentOdontologo.getLista_turno();
            List<Turno> lista_turnoNew = odontologo.getLista_turno();
            List<Turno> attachedLista_turnoNew = new ArrayList<Turno>();
            for (Turno lista_turnoNewTurnoToAttach : lista_turnoNew) {
                lista_turnoNewTurnoToAttach = em.getReference(lista_turnoNewTurnoToAttach.getClass(), lista_turnoNewTurnoToAttach.getId_turno());
                attachedLista_turnoNew.add(lista_turnoNewTurnoToAttach);
            }
            lista_turnoNew = attachedLista_turnoNew;
            odontologo.setLista_turno(lista_turnoNew);
            odontologo = em.merge(odontologo);
            for (Turno lista_turnoOldTurno : lista_turnoOld) {
                if (!lista_turnoNew.contains(lista_turnoOldTurno)) {
                    lista_turnoOldTurno.setOdontologo(null);
                    lista_turnoOldTurno = em.merge(lista_turnoOldTurno);
                }
            }
            for (Turno lista_turnoNewTurno : lista_turnoNew) {
                if (!lista_turnoOld.contains(lista_turnoNewTurno)) {
                    Odontologo oldOdontologoOfLista_turnoNewTurno = lista_turnoNewTurno.getOdontologo();
                    lista_turnoNewTurno.setOdontologo(odontologo);
                    lista_turnoNewTurno = em.merge(lista_turnoNewTurno);
                    if (oldOdontologoOfLista_turnoNewTurno != null && !oldOdontologoOfLista_turnoNewTurno.equals(odontologo)) {
                        oldOdontologoOfLista_turnoNewTurno.getLista_turno().remove(lista_turnoNewTurno);
                        oldOdontologoOfLista_turnoNewTurno = em.merge(oldOdontologoOfLista_turnoNewTurno);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = odontologo.getId();
                if (findOdontologo(id) == null) {
                    throw new NonexistentEntityException("The odontologo with id " + id + " no longer exists.");
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
            Odontologo odontologo;
            try {
                odontologo = em.getReference(Odontologo.class, id);
                odontologo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The odontologo with id " + id + " no longer exists.", enfe);
            }
            List<Turno> lista_turno = odontologo.getLista_turno();
            for (Turno lista_turnoTurno : lista_turno) {
                lista_turnoTurno.setOdontologo(null);
                lista_turnoTurno = em.merge(lista_turnoTurno);
            }
            em.remove(odontologo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Odontologo> findOdontologoEntities() {
        return findOdontologoEntities(true, -1, -1);
    }

    public List<Odontologo> findOdontologoEntities(int maxResults, int firstResult) {
        return findOdontologoEntities(false, maxResults, firstResult);
    }

    private List<Odontologo> findOdontologoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Odontologo.class));
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

    public Odontologo findOdontologo(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Odontologo.class, id);
        } finally {
            em.close();
        }
    }

    public int getOdontologoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Odontologo> rt = cq.from(Odontologo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
