package Controladores;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.Persistence;

public class RecepcionMaterialJpaController implements Serializable {

    public RecepcionMaterialJpaController() {
        emf = Persistence.createEntityManagerFactory("GeneracionLotesPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List Lista_Recepciones() {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_c_recepcion`()");
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
    
    public List Lista_recepcion_responsable(String rol_usuario) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_c_recepcion_responsable`('" + rol_usuario + "')");
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
    
    public List recepciones_5dias() {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_c_recepciones5dias`()");
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
    
    public List mas_referencias_30_dias() {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_referencias_mas_entradas_ultimos_30dias`()");
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

    public List Lista_Recepciones_rango(String fechaInicio, String fechaFin) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_c_recepcion_rango`('" + fechaInicio + "', '" + fechaFin + "')");
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

    public List Lista_Recepciones_sin_firmas(String rol) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_c_recepcion_firmas`('" + rol + "')");
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

    public List Lista_Consecutivo(String codigo, String lote) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_recepcion_c_concecutivo`('" + codigo + "', '" + lote + "')");
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
    
    public List traer_recepcion_id(int id_registro) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_get_recepcion_material_by_id`('" + id_registro + "')");
            List consulta = q.getResultList();
            etm.getTransaction().commit();
            etm.clear();
            etm.close();
            if (consulta.isEmpty()) {
                return consulta;
            } else {
                return consulta;
            }
        } catch (Exception ex) {
            return null;
        }
    }
    
    public List Lista_Recepcion(int idRegsitro) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_c_recepcion_id`('" + idRegsitro + "')");
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

    public boolean Registrar_anexo(int id_rgt, String registro, String nomb, String obs, String usu) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_ctcons_r_anexo`('" + id_rgt + "', '" + registro + "', '" + nomb + "', '" + obs + "', '" + usu + "')");
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

    public List Consulta_anexo_id(int id_rgt, String registro) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_ctcons_c_anexo`('" + id_rgt + "', '" + registro + "')");
            List consulta = q.getResultList();
            etm.getTransaction().commit();
            etm.clear();
            etm.close();
            if (consulta.isEmpty()) {
                return consulta;
            } else {
                return consulta;
            }
        } catch (Exception ex) {
            return null;
        }
    }
    
    public List Consulta_anexo_id_new(int id_anexo) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_ctcons_c_anexo_id`('" + id_anexo + "')");
            List consulta = q.getResultList();
            etm.getTransaction().commit();
            etm.clear();
            etm.close();
            if (consulta.isEmpty()) {
                return consulta;
            } else {
                return consulta;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    public boolean Modificar_Anexos_control_concecutivo(String file_name, String observacion, int id_anexos) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_ctrcons_m_anexos`('" + file_name + "', '" + observacion + "', '" + id_anexos + "')");
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

    public List ConsultarParametros_xUnidad(String unidadMedida) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_prm_c_ConsultarParametrosxCategoria`('" + unidadMedida + "')");
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

    public List ConsultarParametros_xCategoria(String categoria) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_prm_c_ConsultarParametrosxCategoria`('" + categoria + "')");
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

    public List ConsultarParametros_xTipo(String tipo) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_prm_c_ConsultarParametrosxCategoria`('" + tipo + "')");
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

    public List ConsultarParametros_xRgc(String rgc) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_prm_c_ConsultarParametrosxCategoria`('" + rgc + "')");
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

    public List Maestro_Recepciones() {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_c_mestro_recepciones`()");
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

    public List Maestro_Recepciones_id(int idRegsitro) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_c_mr_id`('" + idRegsitro + "')");
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

    public List Lista_Proveedores(String codigo) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_c_proveedor`('" + codigo + "')");
            List consulta = q.getResultList();
            etm.getTransaction().commit();
            etm.clear();
            etm.close();

            for (Object result : consulta) {
                System.out.println("Resultado del query: " + Arrays.toString((Object[]) result));
            }

            if (consulta.isEmpty()) {
                return null;
            } else {
                return consulta;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List Lista_unidad_medida() {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("select * from vw_unidades;");
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

    public boolean Registrar_Recepcion(String codigo, String referencia, String fecha, String llegada, String descargue, int clasificacion, int tipo, int rgc, String lote, int consecutivo, String proveedor, String referencia_pro, String cadena, double cantidad,
            String unidad2, String usuario_registro) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_recepcion_r`('" + codigo + "', '" + referencia + "', '" + fecha + "', '" + llegada + "', '" + descargue + "', '" + clasificacion
                    + "', '" + tipo + "', '" + rgc + "', '" + lote + "', '" + consecutivo
                    + "', '" + proveedor + "', '" + referencia_pro + "', '" + cadena
                    + "', '" + cantidad + "', '" + unidad2 + "', '" + usuario_registro + "')");
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

    public boolean Firma_Directora_Calidad(int idRegistro, String firma) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_firma_dc_recepcion`('" + idRegistro + "', '" + firma + "')");
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

    public boolean Modificar_Recepcion(int idRegistro, String fecha, String llegada, String descargue, int clasificacion, String lote, int consecutivo, String proveedor, String refproveedor, String cadena, double cantidad, String unidad2, String usuario_registro) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_recepcion_m`('" + idRegistro + "', '" + fecha + "', '" + llegada + "', '" + descargue
                    + "', '" + clasificacion + "', '" + lote + "', '" + consecutivo + "', '" + proveedor + "', '" + refproveedor + "', '" + cadena
                    + "', '" + cantidad + "', '" + unidad2 + "', '" + usuario_registro + "')");
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

    public boolean Registrar_Proveedor(String codigo, String proveedor, String referencia) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_r_proveedor`('" + codigo + "', '" + proveedor + "', '" + referencia + "')");
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

    public boolean ActualizarVerificacion(int idRegistro, String verificacion, int estado2) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_a_verificacion_recepcion`('" + idRegistro + "', '" + verificacion + "', '" + estado2 + "')");
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

    public boolean ActualizarControlVerificacion(int idRegistro, int resultado,
            int estadoEmpaques, int estadoNucleos, int pesaje, String umPesaje,
            String repesaje, String finalizaDescargue, int fueraHorario, int estado3) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_a_control_verificacion`('" + idRegistro + "', '"
                    + resultado + "', '" + estadoEmpaques + "', '" + estadoNucleos + "', '"
                    + pesaje + "', '" + umPesaje + "', '" + repesaje + "', '" + finalizaDescargue + "', '" + fueraHorario + "', '" + estado3 + "')");
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

    public boolean ActualizarFirma(int atributo, int idRegistro, String sesion, int estado4) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            String queryFirma = "UPDATE recepcion_material SET ";
            switch (atributo) {
                case 1:
                    queryFirma += "firma_verifica = '" + sesion + "'";
                    break;
                case 2:
                    queryFirma += "firma_ja = '" + sesion + "'";
                    break;
                case 3:
                    queryFirma += "firma_dc = '" + sesion + "'";
                    if (estado4 != 0) {
                        queryFirma += ", estado_recepcion = " + estado4;
                    }
                    break;
                default:
                    queryFirma += "firma_transporte = '" + sesion + "'";
                    break;
            }
            queryFirma += " WHERE id_recepcion_material = " + idRegistro;

            Query q = etm.createNativeQuery(queryFirma);
            int exitoso = q.executeUpdate();
            etm.getTransaction().commit();
            etm.clear();
            etm.close();
            return exitoso != 0;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean ActualizarEstado(int idRegistro, int estado) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_a_estado_recepcion`('" + idRegistro + "', '" + estado + "')");
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

    public boolean EliminarRecepcion(int idRegistro) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("DELETE FROM recepcion_material  WHERE id_recepcion_material = " + idRegistro + "");
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

    public boolean EliminarRecepcionEstado(int idRegistro, String jtf, String urg) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("UPDATE recepcion_material SET estado_recepcion = 6 , observacion_eliminado = concat('" + jtf + " / ',now(),' / " + urg + "')   WHERE id_recepcion_material = " + idRegistro + "");
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

    public boolean PrestamoRecepcion(int idRegistro, String jtf, String urg) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("UPDATE recepcion_material SET prestamo = CONCAT('" + jtf + " / ',now(),' / " + urg + "') WHERE id_recepcion_material = " + idRegistro + "");
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

    public boolean Registrar_Anexo(int id, String cadena) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("CALL `sp_r_anexo`('" + id + "', '" + cadena + "')");
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

    public boolean ActualizacionObservacion(int id, String observacion) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            Query q = etm.createNativeQuery("UPDATE recepcion_material SET observacion = '" + observacion + "' WHERE id_recepcion_material = " + id + "");
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

    public boolean Modificar_firma_masivo(String firma, String ids_recepcion) {
        EntityManager etm = getEntityManager();
        etm.getTransaction().begin();
        try {
            String masivo = ids_recepcion.replace("][", ",").replace("[", "").replace("]", "");
            String columna;
//            if (firma.contains("Calidad_despachos")) {
            if (firma.contains("Calidad_despachos") || firma.contains("Administrador")) {
                columna = "firma_verifica";
            } else if (firma.contains("Jefe_almacen") || firma.contains("Administrador")) {
                columna = "firma_ja";
            } else if (firma.contains("Jefe aseguramiento calidad") || firma.contains("Administrador")) {
                columna = "firma_dc";
            } else {
                etm.getTransaction().rollback();
                etm.clear();
                etm.close();
                return false;
            }

            String query = "UPDATE recepcion_material rm \n"
                    + "SET rm." + columna + " = '" + firma + "' \n"
                    + "WHERE rm.id_recepcion_material IN (" + masivo + ")";

            Query q = etm.createNativeQuery(query);
            q.setParameter("firma", firma);
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
            Query q = etm.createNativeQuery("DELETE FROM recepcion_material  WHERE id_recepcion_material = " + idRegistro + "");
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
