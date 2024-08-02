package Controladores;

import java.io.Serializable;
import javax.persistence.Query;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
public class EnsamblesJpaController implements Serializable {

    public EnsamblesJpaController() {
        emf = Persistence.createEntityManagerFactory("GeneracionLotesPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public List Traer_Ensamble_id(int id_ensamble) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_ensmbl_c_ensamble_id`(" + id_ensamble + ")");
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
    public List Traer_Ensamble_id_proceso(int id_proceso) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_ensmbl_c_ensamble_id_proceso`(" + id_proceso + ")");
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
    public List Traer_Ensambles() {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_ensmbl_c_ensambles`()");
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
    public List Traer_lotesinyeccion() {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("select i.id_control_inyeccion, i.consecutivo , i.lote_principal, i.proceso, p.nombre from inyeccion i inner join procesos p on i.proceso = p.id_procesos ORDER BY i.fecha_registro asc  ");
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
    public List Traer_datosprestamo(int id_ensamble) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_ensmbl_c_datosprestamo`('" + id_ensamble + "')");
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
    public boolean Modificar_estado_contramuestra(int estado, int id_ensamble,String r_rec, String n_rec) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_ensmbl_m_contr`('" + estado + "','" + id_ensamble + "','"+r_rec+"','"+n_rec+"')");
            int exitoso = q.executeUpdate();
            etm.getTransaction().commit();
            etm.clear();
            etm.close();
            if (exitoso == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception ex) {
            return false;
        }
    }
    public boolean Modificar_prestamo(String nombre, int id_ensamble, String datos_prestamo) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_ensmbl_m_prestamo`('" + nombre + "', '" + id_ensamble + "', '" + datos_prestamo + "')");
            int exitoso = q.executeUpdate();
            etm.getTransaction().commit();
            etm.clear();
            etm.close();
            if (exitoso == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception ex) {
            return false;
        }
    }
    public boolean Modificar_devolucion(String devolucion, int id_inyeccion) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_ensmbl_m_devolucion`('" + devolucion + "', '" + id_inyeccion + "')");
            int exitoso = q.executeUpdate();
            etm.getTransaction().commit();
            etm.clear();
            etm.close();
            if (exitoso == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception ex) {
            return false;
        }
    }
    public boolean Modificar_estado(int estado, int id_ensamble) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_ensmbl_m_estado`('" + estado + "', '" + id_ensamble + "')");
            int exitoso = q.executeUpdate();
            etm.getTransaction().commit();
            etm.clear();
            etm.close();
            if (exitoso == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception ex) {
            return false;
        }
    }
    public boolean Eliminar_estado(int id_ensamble) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_eliminar_ensamble`('" + id_ensamble + "')");
            int exitoso = q.executeUpdate();
            etm.getTransaction().commit();
            etm.clear();
            etm.close();
            if (exitoso == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception ex) {
            return false;
        }
    }
    public boolean Registrar_Ensamble(String cons, String nomb, String lotesiny, String lotesmatp, String lotesimp, String loteens, String fch, int contr, String prestamo, String devolucion, int est, String obs, int proceso, String usurgt) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_ensmbl_r_ensamble`('" + cons + "', '" + nomb + "', '" + lotesiny + "', '" + lotesmatp + "', '" + lotesimp + "', '" + loteens + "', '" + fch + "', '" + contr + "', '" + prestamo + "', '" + devolucion + "', '" + est + "', '" + obs + "', '" + proceso + "', '" + usurgt + "')");
            int exitoso = q.executeUpdate();
            etm.getTransaction().commit();
            etm.clear();
            etm.close();
            if (exitoso == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception ex) {
            return false;
        }
    }
    public boolean Modificar_Ensamble(int id_ens, String fecha, String lens, String liny, String lmpr, String lipor, String obser) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNamedQuery("CALL `sp_modificar_ensamble`('" + id_ens + "', '" + fecha + "', '" + lens + "', '" + liny + "', '" + lmpr + "', '" + lipor + "', '" + obser + "')");
            int exitoso = q.executeUpdate();
            etm.getTransaction().commit();
            etm.clear();
            etm.close();
            if (exitoso == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception ex) {
            return false;
        }
    }
    public boolean Registrar_historial_prestamo(int id_o, String rpr, String dpr) {

        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_hst_r_prestamo`('" + id_o + "', '" + rpr + "', '" + dpr + "')");
            int exitoso = q.executeUpdate();
            etm.getTransaction().commit();
            etm.clear();
            etm.close();
            if (exitoso == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception ex) {
            return false;
        }
    }
    public boolean Registrar_Historial_Devolucion(String rdv, String fdv, String ddv, int id_o) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_hst_r_devolucion`('" + rdv + "', '" + fdv + "', '" + ddv + "', '" + id_o + "')");
            int exitoso = q.executeUpdate();
            etm.getTransaction().commit();
            etm.clear();
            etm.close();
            if (exitoso == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception ex) {
            return false;
        }

    }
    public List Traer_Historial_Prestamos(int id_o) {

        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_hst_l_prestamos`('" + id_o + "')");
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
    public List Validar_sublotes(String sub) {

        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("select e.lote_ensamble, e.consecutivo  from ensambles e where e.lote_ensamble='"+sub+"'");
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
        public List Consulta_ultlote_ensamble(String lote, int idpro) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_ensamble_u_lote`('"+lote+"', '"+idpro+"')");
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
