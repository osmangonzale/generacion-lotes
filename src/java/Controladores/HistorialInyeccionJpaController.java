package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Entidad.HistorialInyeccion;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class HistorialInyeccionJpaController implements Serializable {

    public HistorialInyeccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HistorialInyeccion historialInyeccion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(historialInyeccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HistorialInyeccion historialInyeccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            historialInyeccion = em.merge(historialInyeccion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = historialInyeccion.getIdHistorialIny();
                if (findHistorialInyeccion(id) == null) {
                    throw new NonexistentEntityException("The historialInyeccion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HistorialInyeccion historialInyeccion;
            try {
                historialInyeccion = em.getReference(HistorialInyeccion.class, id);
                historialInyeccion.getIdHistorialIny();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historialInyeccion with id " + id + " no longer exists.", enfe);
            }
            em.remove(historialInyeccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HistorialInyeccion> findHistorialInyeccionEntities() {
        return findHistorialInyeccionEntities(true, -1, -1);
    }

    public List<HistorialInyeccion> findHistorialInyeccionEntities(int maxResults, int firstResult) {
        return findHistorialInyeccionEntities(false, maxResults, firstResult);
    }

    private List<HistorialInyeccion> findHistorialInyeccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HistorialInyeccion.class));
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

    public HistorialInyeccion findHistorialInyeccion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HistorialInyeccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistorialInyeccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HistorialInyeccion> rt = cq.from(HistorialInyeccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
