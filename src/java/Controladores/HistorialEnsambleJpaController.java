package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Entidad.HistorialEnsamble;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class HistorialEnsambleJpaController implements Serializable {

    public HistorialEnsambleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HistorialEnsamble historialEnsamble) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(historialEnsamble);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HistorialEnsamble historialEnsamble) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            historialEnsamble = em.merge(historialEnsamble);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = historialEnsamble.getIdHistorial();
                if (findHistorialEnsamble(id) == null) {
                    throw new NonexistentEntityException("The historialEnsamble with id " + id + " no longer exists.");
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
            HistorialEnsamble historialEnsamble;
            try {
                historialEnsamble = em.getReference(HistorialEnsamble.class, id);
                historialEnsamble.getIdHistorial();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historialEnsamble with id " + id + " no longer exists.", enfe);
            }
            em.remove(historialEnsamble);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HistorialEnsamble> findHistorialEnsambleEntities() {
        return findHistorialEnsambleEntities(true, -1, -1);
    }

    public List<HistorialEnsamble> findHistorialEnsambleEntities(int maxResults, int firstResult) {
        return findHistorialEnsambleEntities(false, maxResults, firstResult);
    }

    private List<HistorialEnsamble> findHistorialEnsambleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HistorialEnsamble.class));
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

    public HistorialEnsamble findHistorialEnsamble(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HistorialEnsamble.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistorialEnsambleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HistorialEnsamble> rt = cq.from(HistorialEnsamble.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
