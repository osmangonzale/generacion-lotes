package Controladores;

import Controladores.exceptions.NonexistentEntityException;
import Entidad.Anexos;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class AnexosJpaController implements Serializable {

    public AnexosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Anexos anexos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(anexos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Anexos anexos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            anexos = em.merge(anexos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = anexos.getIdAnexos();
                if (findAnexos(id) == null) {
                    throw new NonexistentEntityException("The anexos with id " + id + " no longer exists.");
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
            Anexos anexos;
            try {
                anexos = em.getReference(Anexos.class, id);
                anexos.getIdAnexos();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The anexos with id " + id + " no longer exists.", enfe);
            }
            em.remove(anexos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Anexos> findAnexosEntities() {
        return findAnexosEntities(true, -1, -1);
    }

    public List<Anexos> findAnexosEntities(int maxResults, int firstResult) {
        return findAnexosEntities(false, maxResults, firstResult);
    }

    private List<Anexos> findAnexosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Anexos.class));
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

    public Anexos findAnexos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Anexos.class, id);
        } finally {
            em.close();
        }
    }

    public int getAnexosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Anexos> rt = cq.from(Anexos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
