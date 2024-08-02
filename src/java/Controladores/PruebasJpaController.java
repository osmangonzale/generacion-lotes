package Controladores;
import Controladores.exceptions.NonexistentEntityException;
import Entidad.Pruebas;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class PruebasJpaController implements Serializable {
    public PruebasJpaController() {
         emf = Persistence.createEntityManagerFactory("GeneracionLotesPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
  public List Pruebas() {
    EntityManager etm = getEntityManager();
    etm.getTransaction().begin();
    try {
        Query q = etm.createNativeQuery("CALL `sp_pru_c_pruebas`()");
        List consulta = q.getResultList();
        etm.getTransaction().commit();
        etm.clear();
        etm.close();
        if (consulta.isEmpty()) {
            return null;
        } else {
            return consulta;
        }
    } catch (Exception ex) {
        return null;
    }
}
}
