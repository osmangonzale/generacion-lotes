package Controladores;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.Persistence;

public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController() {
        emf = Persistence.createEntityManagerFactory("GeneracionLotesPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Integer ObtenerIdUsuarioPorUser(String user) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        Integer idUsuario = null;
        try {
            Query query = etm.createNativeQuery("CALL sp_c_ObtenerIdUsuario('" + user + "')");
            query.setParameter(1, user);
            idUsuario = (Integer) query.getSingleResult();

            etm.getTransaction().commit();
        } catch (Exception ex) {
            if (etm.getTransaction().isActive()) {
                etm.getTransaction().rollback();
            }
            ex.printStackTrace();
        } finally {
            etm.clear();
            etm.close();
        }
        return idUsuario;
    }

    public boolean ActualizarIntentos(String p_user, int p_intentos) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL sp_a_actualizar_intentos_usuario('" + p_user + "', '" + p_intentos + "')");
            q.setParameter(1, p_user);
            q.setParameter(2, p_intentos);
            int exitoso = q.executeUpdate();
            etm.getTransaction().commit();
            etm.clear();
            etm.close();
            return exitoso > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public int obtenerIntentos(String p_user) {
        EntityManager etm = getEntityManager();
        try {
            Query q = etm.createNativeQuery("CALL sp_a_obtener_intentos_usuario('" + p_user + "')");
            q.setParameter(1, p_user);
            Object result = q.getSingleResult();
            if (result != null) {
                return ((Number) result).intValue();
            } else {
                return -1;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        } finally {
            etm.close();
        }
    }

    public boolean ActualizarEstadoIntentosYBloqueo(int idUsuario, int nuevoIntentos) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query query = etm.createNativeQuery("CALL ActualizarEstadoIntentosYBloqueo('" + idUsuario + "', '" + nuevoIntentos + "')");
            query.setParameter(1, idUsuario);
            query.setParameter(2, nuevoIntentos);
            query.executeUpdate();
            etm.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            if (etm.getTransaction().isActive()) {
                etm.getTransaction().rollback();
            }
            ex.printStackTrace();
            return false;
        } finally {
            etm.clear();
            etm.close();
        }
    }

    public List CalcularTiempoBloqueoUsuario(int idUsuario) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL sp_Calcular_TiempoBloqueo_usuario('" + idUsuario + "')");
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

    public boolean DesbloquearUsuario(int idUsuario) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query query = etm.createNativeQuery("CALL sp_a_desbloqueo_usuario(" + idUsuario + ")");
            query.executeUpdate();
            etm.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            if (etm.getTransaction().isActive()) {
                etm.getTransaction().rollback();
            }
            ex.printStackTrace();
            return false;
        } finally {
            etm.clear();
            etm.close();
        }
    }

    public List Usuario_sesion(String usa, String pwd) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL sp_usu_login('" + usa + "','" + pwd + "')");
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

    public List Usuarios() {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_usu_c_usuarios`()");
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

    public List Usuarios_filtro(String fto) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_usa_t_usuarios_filtro`('" + fto + "')");
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

    public List Traer_usuario(int ius) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_usu_t_usuario`('" + ius + "')");
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

    public boolean Registrar_usuario(String nbe, String apl, int dcm, int cdg, String usr, int irl, String urg) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_usu_r_usuario`('" + nbe + "','" + apl + "','" + dcm + "','" + cdg + "','" + usr + "','" + irl + "','" + urg + "')");
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

    public boolean Modificar_usuario(int ius, String nbe, String apl, int dcm, int cdg, String usu, int irl, String usu_r) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_usu_m_usuario`('" + ius + "','" + nbe + "','" + apl + "','" + dcm + "','" + cdg + "','" + usu + "','" + irl + "','" + usu_r + "')");
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

    public boolean Activar_usuario(int ius) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_usu_m_activar`('" + ius + "')");
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

    public boolean Desactivar_usuario(int ius) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_usu_m_desactivar`('" + ius + "')");
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

    public boolean Restablecer_password(int ius) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("UPDATE usuario SET password = YEAR(CURDATE()) WHERE id_usuario = " + ius);
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

    public boolean Cambiar_password(int ius, String pwd) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("UPDATE usuario SET password = '" + pwd + "'WHERE id_usuario = " + ius);
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

    public boolean Modificar_proceso_usu(int idpro, int idusu) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_usu_m_proceso`('" + idpro + "', '" + idusu + "')");
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
