package Controladores;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.Persistence;

public class InyeccionJpaController implements Serializable {

    public InyeccionJpaController() {
        emf = Persistence.createEntityManagerFactory("GeneracionLotesPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List Traer_Inyeccion_id(int id_proceso, int year) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_inyc_c_inyeccion_id`('" + id_proceso + "','" + year + "')");
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
    
    public List Traer_registro_id(int id_proceso) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_c_inyeccion_id`('" + id_proceso + "')");
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
    
    public List Lista_inyeccion_responsable(String rol_usuario) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_c_inyeccion_responsable`('" + rol_usuario + "')");
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
    
    public List top3_ultimos6_meses() {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `Top3ProductosUsadosUltimos6Meses`()");
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

    public List Rango_Años() {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_range_inyeccion`()");
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

    public List Validar_consecutivo_materiaprima(String c_mpr) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_iny_c_consecutivo`('" + c_mpr + "')");
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

    public List Traer_id_inyeccion(int id_inyeccion) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_iny_c_id_iny`('" + id_inyeccion + "')");
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

    public List Traer_inyecciones(int year) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_inyc_c_inyecciones`(" + year + ")");
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

    public List Traer_datosprestamo(int id) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_c_inyc_datosprestamo`('" + id + "')");
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

    public List Lotesciny(int id_proceso) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("select c.id_control_consecutivos,c.consecutivo ,c.lote , c.id_proceso , p.nombre from control_consecutivos c inner join procesos p on c.id_proceso = p.id_procesos where p.id_procesos = " + id_proceso + " order by c.fecha_registro desc ");
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

    public List Lotesc() {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("select c.id_control_consecutivos,c.consecutivo ,c.lote , c.id_proceso , p.nombre from control_consecutivos c inner join procesos p on c.id_proceso = p.id_procesos  order by c.fecha_registro desc ");
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

    public boolean Registrar_inyeccion(String cons, String nombref, String lotec, String lotep, String loteprn, String fch, String obs, int molde, int ctr_muestras, String responsables, String prestamo, String devolucion, int estado, int id_pro, String usurgt) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_inyc_r_inyeccion`('" + cons + "', '" + nombref + "','" + lotec + "', '" + lotep + "', '" + loteprn + "', '" + fch + "', '" + obs + "','" + molde + "','" + ctr_muestras + "','" + responsables + "','" + prestamo + "','" + devolucion + "','" + estado + "','" + id_pro + "','" + usurgt + "')");
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

    public boolean Modificar_inyeccion(String id_lote_c, String lotec, String lotep, String loteprn, String fch, String obs, int molde, int id_iny) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_iny_m_inyeccion`('" + id_iny + "','" + id_lote_c + "','" + lotec + "', '" + lotep + "', '" + loteprn + "', '" + fch + "','" + obs + "','" + molde + "')");
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

    public boolean Modificar_inyeccion_concecutivo(String con, String lotec, String lotep, String loteprn, String fch, String obs, int molde, int id_iny) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_iny_m_concecutivo_inyeccion`('" + id_iny + "','" + con + "','" + lotec + "', '" + lotep + "', '" + loteprn + "', '" + fch + "','" + obs + "','" + molde + "')");
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

    public boolean Modificar_estado_inyeccion(int estado, int id_inyeccion) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_inyc_m_estado`('" + estado + "', '" + id_inyeccion + "')");
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

    public boolean Modificar_estado_contramuestra(int estado, int id_inyeccion, String r_rec, String n_rec) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_m_inyc_contrmuestra`('" + estado + "', '" + id_inyeccion + "','" + r_rec + "','" + n_rec + "')");
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

    public boolean Modificar_prestamo(String nombre, int id_inyeccion, String datos_prestamo) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_inyc_m_prestamo`('" + nombre + "', '" + id_inyeccion + "', '" + datos_prestamo + "')");
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
            Query q = etm.createNativeQuery("CALL `sp_inyc_m_devolucion`('" + devolucion + "', '" + id_inyeccion + "')");
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

    public boolean Registrar_historial_prestamo_inyeccion(int id_o, String rpr, String dpr) {

        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_hst_r_prestamo_iny`('" + id_o + "', '" + rpr + "', '" + dpr + "')");
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

    public boolean Registrar_Historial_Devolucion_inyeccion(String rdv, String fdv, String ddv, int id_o) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_hst_r_devolucion_iny`('" + rdv + "', '" + fdv + "', '" + ddv + "', '" + id_o + "')");
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

    public List Traer_Historial_Prestamos_inyeccion(int id_o) {

        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_hst_l_prestamos_iny`('" + id_o + "')");
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

    public List Consulta_ultlote_inyeccion(String lote, int idpro) {

        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_ult_inyeccion`('" + lote + "', '" + idpro + "')");
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

    public List Traer_siglas_proceseso(int id) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_iny_siglas_consecutivos`('" + id + "')");
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

    public boolean Modificar_estado_inyeccion_masivo(int estado, String ids_inyeccion) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            String masivo = ids_inyeccion.replace("][", ",").replace("[", "").replace("]", "");
            String query = "update inyeccion i \n"
                    + "SET i.estado = " + estado + "\n"
                    + "WHERE i.id_control_inyeccion IN (" + masivo + ")";
            Query q = etm.createNativeQuery(query);
            int exitoso = q.executeUpdate();
            etm.getTransaction().commit();
            etm.clear();
            etm.close();
            return exitoso > 0;
        } catch (Exception ex) {
            etm.getTransaction().rollback();
            etm.clear();
            etm.close();
            return false;
        }
    }
    
    public boolean EliminarRegistro(int idRegistro) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("DELETE FROM inyeccion  WHERE id_control_inyeccion = " + idRegistro + "");
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
}
