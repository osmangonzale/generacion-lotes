package Servlet;

import Controladores.RecepcionMaterialJpaController;
import Controladores.RolJpaController;
import Controladores.VerificacionRecepcionJpaController;
import Factory.ReferenciasInv;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RecepcionMaterial extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //<editor-fold defaultstate="collapsed" desc="JPAS">
            ReferenciasInv inventario = new ReferenciasInv();
            RolJpaController jpac_rol = new RolJpaController();
            RecepcionMaterialJpaController jpac_recepcion = new RecepcionMaterialJpaController();
            VerificacionRecepcionJpaController jpac_verificacion = new VerificacionRecepcionJpaController();
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="SESION">
            HttpSession sesion = request.getSession();
            String rol_usuario = sesion.getAttribute("Rol/Nombres").toString();
            String UserRol = sesion.getAttribute("Id_rol").toString();
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="VARIABLES">
            int opc = Integer.parseInt(request.getParameter("opc").toString());
            String codigo, referencia, fecha, llegada, descargue, lote, proveedor, refproveedor, cadena, unidad1, unidad2, unidad, nombre, tipo_registro, file_name = "";
            String justificacion, arreglo, observacion, umPesaje, repesaje, finalizaDescargue, obs1, obs2, obs3, obs4, obs5, obs6, obs7, obs9, obs10, obs11, obs12 = "";
            int clasificacion, tipo, rgc, consecutivo, cantidad, cantidad1, cantidad2, idRegistro, temp, resultado, estado2, estado3, cedula, estado4, id_add_anexo = 0, id_m_anexo = 0, tempF = 0;
            int estadoEmpaques, estadoNucleos, fueraHorario, radio1, radio2, radio3, radio4, radio5, radio6, radio7, radio8, radio9, radio10, radio11, radio12, radio13, radio14, pesaje, id_anexos = 0;
            int id, id_order = 0, tempp = 0;
            String[] rango = null;
            String rangoParam = "";
            int id_rol, id_rol_permission = 0;
            int id_registro = 0;
            boolean result = false;
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="VARIABLES DE CONTEXTO">
            try {
                codigo = request.getParameter("codigo").toString();
            } catch (Exception e) {
                codigo = "";
            }
            try {
                referencia = request.getParameter("referencia").toString();
            } catch (Exception e) {
                referencia = "";
            }
            try {
                fecha = request.getParameter("fecha").toString();
            } catch (Exception e) {
                fecha = "";
            }
            try {
                llegada = request.getParameter("llegada").toString();
            } catch (Exception e) {
                llegada = "";
            }
            try {
                descargue = request.getParameter("descargue").toString();
            } catch (Exception e) {
                descargue = "";
            }
            try {
                clasificacion = Integer.parseInt(request.getParameter("clasificacion"));
            } catch (Exception e) {
                clasificacion = 0;
            }
            try {
                lote = request.getParameter("lote").toString();
            } catch (Exception e) {
                lote = "";
            }
            try {
                tipo = Integer.parseInt(request.getParameter("tipo"));
            } catch (Exception e) {
                tipo = 0;
            }
            try {
                rgc = Integer.parseInt(request.getParameter("rgc"));
            } catch (Exception e) {
                rgc = 0;
            }
            try {
                consecutivo = Integer.parseInt(request.getParameter("consecutivo"));
            } catch (Exception e) {
                consecutivo = 0;
            }
            try {
                proveedor = request.getParameter("proveedor").toString();
            } catch (Exception e) {
                proveedor = "";
            }
            try {
                refproveedor = request.getParameter("refproveedor").toString();
            } catch (Exception e) {
                refproveedor = "";
            }
            try {
                cadena = request.getParameter("cadena").toString();
            } catch (Exception e) {
                cadena = "";
            }
            try {
                cantidad = Integer.parseInt(request.getParameter("cantidad"));
            } catch (Exception e) {
                cantidad = 0;
            }
            try {
                cantidad1 = Integer.parseInt(request.getParameter("cantidad1"));
            } catch (Exception e) {
                cantidad1 = 0;
            }
            try {
                cantidad2 = Integer.parseInt(request.getParameter("cantidad2"));
            } catch (Exception e) {
                cantidad2 = 0;
            }
            try {
                unidad = request.getParameter("unidad2").toString();
            } catch (Exception e) {
                unidad = "";
            }
            try {
                unidad1 = request.getParameter("unidad1").toString();
            } catch (Exception e) {
                unidad1 = "";
            }
            try {
                unidad2 = request.getParameter("unidad2").toString();
            } catch (Exception e) {
                unidad2 = "";
            }
            try {
                idRegistro = Integer.parseInt(request.getParameter("idRegistro"));
            } catch (Exception e) {
                idRegistro = 0;
            }
            try {
                temp = Integer.parseInt(request.getParameter("temp"));
            } catch (Exception e) {
                temp = 0;
            }
            try {
                estado2 = Integer.parseInt(request.getParameter("estado2"));
            } catch (Exception e) {
                estado2 = 0;
            }
            try {
                arreglo = request.getParameter("arreglo").toString();
            } catch (Exception e) {
                arreglo = "";
            }
//</editor-fold>
            switch (opc) {
                //<editor-fold defaultstate="collapsed" desc="CASO 1 ATRIBUTOS">
                case 1:
                    try {
                        id_add_anexo = Integer.parseInt(request.getParameter("id_add_anexo"));
                    } catch (Exception e) {
                        id_add_anexo = 0;
                    }
                    try {
                        id_m_anexo = Integer.parseInt(request.getParameter("id_m_anexo"));
                    } catch (Exception e) {
                        id_m_anexo = 0;
                    }
                    try {
                        tempF = Integer.parseInt(request.getParameter("tempF"));
                    } catch (Exception e) {
                        tempF = 0;
                    }
                    try {
                        temp = Integer.parseInt(request.getParameter("temp"));
                    } catch (Exception e) {
                        temp = 0;
                    }
                    try {
                        tempp = Integer.parseInt(request.getParameter("tempp"));
                    } catch (Exception e) {
                        tempp = 0;
                    }
                    try {
                        id_order = Integer.parseInt(request.getParameter("id_order"));
                    } catch (Exception e) {
                        id_order = 0;
                    }
                    try {
                        rangoParam = request.getParameter("rango");
                    } catch (Exception e) {
                        rangoParam = null;
                    }
                    try {
                        id_rol_permission = Integer.parseInt(request.getParameter("id_rol_permission"));
                    } catch (Exception e) {
                        id_rol_permission = 0;
                    }
                    request.setAttribute("opc", opc);
                    request.setAttribute("codigo", codigo);
                    request.setAttribute("referencia", referencia);
                    request.setAttribute("idRegistro", idRegistro);
                    request.setAttribute("temp", temp);
                    request.setAttribute("id_add_anexo", id_add_anexo);
                    request.setAttribute("id_m_anexo", id_m_anexo);
                    request.setAttribute("rango", rangoParam);
                    request.setAttribute("Rol/Nombres", rol_usuario);
                    request.setAttribute("tempF", tempF);
                    request.setAttribute("tempp", tempp);
                    request.setAttribute("id_order", id_order);
                    request.setAttribute("Id_rol", UserRol);
                    request.setAttribute("id_rol_permission", id_rol_permission);
                    request.getRequestDispatcher("RecepcionMaterial.jsp").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 2 REGISTRAR RECEPCION">
                case 2:
                    cadena = cantidad1 + " " + unidad1 + " * " + cantidad2 + " " + unidad2;
                    result = jpac_recepcion.Registrar_Recepcion(codigo, referencia, fecha, llegada, descargue, clasificacion, tipo, rgc, lote, consecutivo, proveedor, refproveedor, cadena, cantidad, unidad, rol_usuario);
                    if (result) {
                        request.setAttribute("Registrar_Recepcion", result);
                        request.getRequestDispatcher("RecepcionMaterial?opc=1&codigo=").forward(request, response);
                    } else {
                        request.setAttribute("Alerta", "Error_usuario");
                    }
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 3 MODIFICAR RECEPCION">
                case 3:
                    request.setAttribute("fecha", fecha);
                    request.setAttribute("llegada", llegada);
                    request.setAttribute("descargue", descargue);
                    request.setAttribute("clasificacion", clasificacion);
                    request.setAttribute("lote", lote);
                    request.setAttribute("consecutivo", consecutivo);
                    request.setAttribute("proveedor", proveedor);
                    request.setAttribute("refproveedor", refproveedor);
                    request.setAttribute("cantidad", cantidad);
                    request.setAttribute("cantidad1", cantidad1);
                    request.setAttribute("unidad1", unidad1);
                    request.setAttribute("cantidad2", cantidad2);
                    request.setAttribute("unidad2", unidad2);
                    request.setAttribute("unidad2", unidad);
                    request.setAttribute("usuario", rol_usuario);
                    cadena = cantidad1 + " " + unidad1 + " * " + cantidad2 + " " + unidad2;
                    result = jpac_recepcion.Modificar_Recepcion(idRegistro, fecha, llegada, descargue, clasificacion, lote, consecutivo, proveedor, refproveedor, cadena, cantidad, unidad, rol_usuario);
                    if (result) {
                        request.setAttribute("Modificar_Recepcion", result);
                        request.getRequestDispatcher("RecepcionMaterial?opc=1&idRegistro=0").forward(request, response);
                    } else {
                        request.setAttribute("Alerta", "Error_usuario");
                        request.getRequestDispatcher("Error.jsp").forward(request, response);
                    }
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 4 ELIMINAR RECEPCION">
                case 4:
                    idRegistro = Integer.parseInt(request.getParameter("idRegistro"));
                    justificacion = request.getParameter("Txt_justificacion");
                    rol_usuario = sesion.getAttribute("Rol/Nombres").toString();
                    result = jpac_recepcion.EliminarRecepcionEstado(idRegistro, justificacion, rol_usuario);
                    if (result) {
                        request.setAttribute("eliminar_registro_recepcion", result);
                        request.getRequestDispatcher("RecepcionMaterial?opc=1&idRegistro=0").forward(request, response);
                    } else {
                        request.setAttribute("eliminar_registro_recepcion_denegado", result);
                        request.getRequestDispatcher("Error.jsp").forward(request, response);
                    }
                    request.getRequestDispatcher("RecepcionMaterial?opc=1&idRegistro=0").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 5 OBSERVACION">
                case 5:
                    idRegistro = Integer.parseInt(request.getParameter("idRegistro"));
                    observacion = request.getParameter("Txt_observacion");
                    result = jpac_recepcion.ActualizacionObservacion(idRegistro, observacion);
                    if (result) {
                        request.setAttribute("registrar_observacion", result);
                        request.getRequestDispatcher("RecepcionMaterial?opc=1&idRegistro=0").forward(request, response);
                    } else {
                        request.setAttribute("Alerta", "Error_usuario");
                        request.getRequestDispatcher("Error.jsp").forward(request, response);
                    }
                    request.getRequestDispatcher("RecepcionMaterial?opc=1&idRegistro=0").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 6 REGISTRAR PRESTAMO"> 
                case 6:
                    idRegistro = Integer.parseInt(request.getParameter("idRegistro"));
                    justificacion = request.getParameter("Txt_justificacion");
                    unidad = request.getParameter("und");
                    cantidad = (int) Double.parseDouble(request.getParameter("Txt_cantidad"));
                    rol_usuario = sesion.getAttribute("Rol/Nombres").toString();
                    sesion.setAttribute("Modulo", "");
                    result = jpac_recepcion.PrestamoRecepcion(idRegistro, "CANT : " + cantidad + unidad + " OBS : " + justificacion, rol_usuario);
                    if (result) {
                        request.setAttribute("registrar_prestamo", result);
                        request.getRequestDispatcher("RecepcionMaterial?opc=1&idRegistro=0").forward(request, response);
                    } else {
                        request.setAttribute("Alerta", "Error_usuario");
                        request.getRequestDispatcher("Error.jsp").forward(request, response);
                    }
                    request.getRequestDispatcher("RecepcionMaterial?opc=1&idRegistro=0").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 7 ACTUALIZAR VERIFICACION">
                case 7:
                    //<editor-fold defaultstate="collapsed" desc="OBS">
                    try {
                        obs1 = request.getParameter("obs1").toString();
                    } catch (Exception e) {
                        obs1 = "";
                    }
                    try {
                        obs2 = request.getParameter("obs2").toString();
                    } catch (Exception e) {
                        obs2 = "";
                    }
                    try {
                        obs3 = request.getParameter("obs3").toString();
                    } catch (Exception e) {
                        obs3 = "";
                    }
                    try {
                        obs4 = request.getParameter("obs4").toString();
                    } catch (Exception e) {
                        obs4 = "";
                    }
                    try {
                        obs5 = request.getParameter("obs5").toString();
                    } catch (Exception e) {
                        obs5 = "";
                    }
                    try {
                        obs6 = request.getParameter("obs6").toString();
                    } catch (Exception e) {
                        obs6 = "";
                    }
                    try {
                        obs7 = request.getParameter("obs7").toString();
                    } catch (Exception e) {
                        obs7 = "";
                    }
                    try {
                        obs9 = request.getParameter("obs9").toString();
                    } catch (Exception e) {
                        obs9 = "";
                    }
                    try {
                        obs10 = request.getParameter("obs10").toString();
                    } catch (Exception e) {
                        obs10 = "";
                    }
                    try {
                        obs11 = request.getParameter("obs11").toString();
                    } catch (Exception e) {
                        obs11 = "";
                    }
                    try {
                        obs12 = request.getParameter("obs12").toString();
                    } catch (Exception e) {
                        obs12 = "";
                    }
//</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="RADIO">
                    try {
                        radio1 = Integer.parseInt(request.getParameter("radio1"));
                    } catch (Exception e) {
                        radio1 = 0;
                    }
                    try {
                        radio2 = Integer.parseInt(request.getParameter("radio2"));
                    } catch (Exception e) {
                        radio2 = 0;
                    }
                    try {
                        radio3 = Integer.parseInt(request.getParameter("radio3"));
                    } catch (Exception e) {
                        radio3 = 0;
                    }
                    try {
                        radio4 = Integer.parseInt(request.getParameter("radio4"));
                    } catch (Exception e) {
                        radio4 = 0;
                    }
                    try {
                        radio5 = Integer.parseInt(request.getParameter("radio5"));
                    } catch (Exception e) {
                        radio5 = 0;
                    }
                    try {
                        radio6 = Integer.parseInt(request.getParameter("radio6"));
                    } catch (Exception e) {
                        radio6 = 0;
                    }
                    try {
                        radio7 = Integer.parseInt(request.getParameter("radio7"));
                    } catch (Exception e) {
                        radio7 = 0;
                    }
                    try {
                        radio8 = Integer.parseInt(request.getParameter("radio8"));
                    } catch (Exception e) {
                        radio8 = 0;
                    }
                    try {
                        radio9 = Integer.parseInt(request.getParameter("radio9"));
                    } catch (Exception e) {
                        radio9 = 0;
                    }
                    try {
                        radio10 = Integer.parseInt(request.getParameter("radio10"));
                    } catch (Exception e) {
                        radio10 = 0;
                    }
                    try {
                        radio11 = Integer.parseInt(request.getParameter("radio11"));
                    } catch (Exception e) {
                        radio11 = 0;
                    }
                    try {
                        radio12 = Integer.parseInt(request.getParameter("radio12"));
                    } catch (Exception e) {
                        radio12 = 0;
                    }
                    try {
                        radio13 = Integer.parseInt(request.getParameter("radio13"));
                    } catch (Exception e) {
                        radio13 = 0;
                    }
                    try {
                        radio14 = Integer.parseInt(request.getParameter("radio14"));
                    } catch (Exception e) {
                        radio14 = 0;
                    }
//</editor-fold>
                    String obs1Checked = (obs1 == null || obs1.isEmpty()) ? "N/A" : obs1;
                    String obs2Checked = (obs2 == null || obs2.isEmpty()) ? "N/A" : obs2;
                    String obs3Checked = (obs3 == null || obs3.isEmpty()) ? "N/A" : obs3;
                    String obs4Checked = (obs4 == null || obs4.isEmpty()) ? "N/A" : obs4;
                    String obs5Checked = (obs5 == null || obs5.isEmpty()) ? "N/A" : obs5;
                    String obs6Checked = (obs6 == null || obs6.isEmpty()) ? "N/A" : obs6;
                    String obs7Checked = (obs7 == null || obs7.isEmpty()) ? "N/A" : obs7;
                    String obs9Checked = (obs9 == null || obs9.isEmpty()) ? "N/A" : obs9;
                    String obs10Checked = (obs10 == null || obs10.isEmpty()) ? "N/A" : obs10;
                    String obs11Checked = (obs11 == null || obs11.isEmpty()) ? "N/A" : obs11;

                    arreglo = "[[" + 1 + "," + radio1 + "," + obs1Checked + "],"
                            + "[" + 2 + "," + radio2 + "," + obs2Checked + "],"
                            + "[" + 3 + "," + radio3 + "," + obs3Checked + "],"
                            + "[" + 4 + "," + radio4 + "," + obs4Checked + "],"
                            + "[" + 5 + "," + radio5 + "," + obs5Checked + "],"
                            + "[" + 6 + "," + radio6 + "," + obs6Checked + "],"
                            + "[" + 7 + "," + radio7 + "," + obs7Checked + "],"
                            + "[" + 8 + "," + radio8 + "," + radio12 + "],"
                            + "[" + 9 + "," + radio9 + "," + obs9Checked + "],"
                            + "[" + 10 + "," + radio10 + "," + obs10Checked + "],"
                            + "[" + 11 + "," + radio11 + "," + obs11Checked + "]],"
                            + "[" + 12 + "," + radio13 + "," + obs12 + "," + radio14 + "]]";
                    result = jpac_recepcion.ActualizarVerificacion(idRegistro, arreglo, estado2);
                    if (result) {
                        request.setAttribute("Actualizar_Verificacion", result);
                        request.getRequestDispatcher("RecepcionMaterial?opc=1&temp=1&idRegistro=" + idRegistro + "").forward(request, response);
                    } else {
                        request.setAttribute("Alerta", "Error_usuario");
                        request.getRequestDispatcher("Error.jsp").forward(request, response);
                    }
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 8 ACTUALIZAR CONTROL VERIFICACION">
                case 8:
                    //<editor-fold defaultstate="collapsed" desc="VARIABLES DE CONTEXTO">
                    try {
                        umPesaje = request.getParameter("umPesaje").toString();
                    } catch (Exception e) {
                        umPesaje = "";
                    }
                    try {
                        repesaje = request.getParameter("repesaje").toString();
                    } catch (Exception e) {
                        repesaje = "";
                    }
                    try {
                        finalizaDescargue = request.getParameter("finalizaDescargue").toString();
                    } catch (Exception e) {
                        finalizaDescargue = "";
                    }
                    try {
                        resultado = Integer.parseInt(request.getParameter("resultado"));
                    } catch (Exception e) {
                        resultado = 0;
                    }
                    try {
                        estadoEmpaques = Integer.parseInt(request.getParameter("estadoEmpaques"));
                    } catch (Exception e) {
                        estadoEmpaques = 0;
                    }
                    try {
                        estadoNucleos = Integer.parseInt(request.getParameter("estadoNucleos"));
                    } catch (Exception e) {
                        estadoNucleos = 0;
                    }
                    try {
                        pesaje = Integer.parseInt(request.getParameter("pesaje"));
                    } catch (Exception e) {
                        pesaje = 0;
                    }
                    try {
                        fueraHorario = Integer.parseInt(request.getParameter("fueraHorario"));
                    } catch (Exception e) {
                        fueraHorario = 0;
                    }
                    try {
                        estado3 = Integer.parseInt(request.getParameter("estado3"));
                    } catch (Exception e) {
                        estado3 = 0;
                    }
//</editor-fold>
                    result = jpac_recepcion.ActualizarControlVerificacion(idRegistro, resultado, estadoEmpaques, estadoNucleos, pesaje, umPesaje, repesaje, finalizaDescargue, fueraHorario, estado3);
                    if (result) {
                        request.setAttribute("Actualizar_Control_Verificacion", result);
                        request.getRequestDispatcher("RecepcionMaterial?opc=1&temp=1&idRegistro=" + idRegistro + "").forward(request, response);
                    } else {
                        request.setAttribute("Alerta", "Error_usuario");
                        request.getRequestDispatcher("Error.jsp").forward(request, response);
                    }
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 9 FIRMAS R-GC-070">
                case 9:
                    //<editor-fold defaultstate="collapsed" desc="VARIABLES DE CONTEXTO">
                    try {
                        nombre = request.getParameter("nombre").toString();
                    } catch (Exception e) {
                        nombre = "";
                    }
                    try {
                        cedula = Integer.parseInt(request.getParameter("cedula"));
                    } catch (Exception e) {
                        cedula = 0;
                    }
                    try {
                        estado4 = Integer.parseInt(request.getParameter("estado4"));
                    } catch (Exception e) {
                        estado4 = 0;
                    }

                    int valor = 0;
//</editor-fold>
                    try {
                        valor = Integer.parseInt(request.getParameter("cedula"));
                    } catch (Exception e) {
                        try {
                            valor = Integer.parseInt(request.getParameter("txtValue1"));
                        } catch (Exception ex) {
                            try {
                                valor = Integer.parseInt(request.getParameter("txtValue2"));
                            } catch (Exception ez) {
                                valor = Integer.parseInt(request.getParameter("txtValue3"));
                            }
                        }
                    }
                    if (valor > 3) {
                        cadena = "[" + nombre + "/" + cedula + "]";
                        result = jpac_recepcion.ActualizarFirma(valor, idRegistro, cadena, estado4);
                        if (result) {
                            request.setAttribute("Actualizar_Firmas", result);
                            request.getRequestDispatcher("RecepcionMaterial?opc=1&temp=1&idRegistro=" + idRegistro + "").forward(request, response);
                        } else {
                            request.setAttribute("Alerta", "Error_usuario");
                            request.getRequestDispatcher("Error.jsp").forward(request, response);
                        }
                    } else {
                        cadena = rol_usuario;
                        result = jpac_recepcion.ActualizarFirma(valor, idRegistro, cadena, estado4);
                        if (result) {
                            request.setAttribute("Actualizar_Firmas", result);
                            request.getRequestDispatcher("RecepcionMaterial?opc=1&temp=1&idRegistro=" + idRegistro + "").forward(request, response);
                        } else {
                            request.setAttribute("Alerta", "Error_usuario");
                            request.getRequestDispatcher("Error.jsp").forward(request, response);
                        }
                    }
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 10 ANEXOS">
                case 10:
                    //<editor-fold defaultstate="collapsed" desc="VARIABLES DE CONTEXTO">
                    try {
                        id = Integer.parseInt(request.getParameter("id"));
                    } catch (NumberFormatException e) {
                        id = 0;
                    }
                    try {
                        observacion = request.getParameter("Txt_descripcion").toString();
                    } catch (Exception e) {
                        observacion = "";
                    }
                    try {
                        file_name = request.getParameter("file_name").toString();
                    } catch (Exception e) {
                        file_name = "";
                    }
                    try {
                        id_anexos = Integer.parseInt(request.getParameter("id_m_anexo"));
                    } catch (NumberFormatException e) {
                        id_anexos = 0;
                    }
                    tipo_registro = "R-GC-059";
//</editor-fold>
                    if (id_anexos == 0) {
                        result = jpac_recepcion.Registrar_anexo(id, tipo_registro, file_name, observacion, rol_usuario);
                        if (result) {
                            request.setAttribute("Registrar_Anexo", result);
                        } else {
                            request.setAttribute("Alerta", "Error_Registro_Anexo");
                        }
                    } else {
                        result = jpac_recepcion.Modificar_Anexos_control_concecutivo(file_name, observacion, id_anexos);
                        if (result) {
                            request.setAttribute("Modificar_Anexo", result);
                        } else {
                            request.setAttribute("Alerta", "Error_Modificar_Anexo");
                        }
                    }
                    request.setAttribute("id", id);
                    request.setAttribute("id_m_anexo", id_anexos);
                    request.getRequestDispatcher("RecepcionMaterial?opc=1&id_add_anexo=" + id + "").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 11 MAESTRO DE RECEPCIONES">
                case 11:
                    request.setAttribute("opc", opc);
                    request.setAttribute("codigo", codigo);
                    request.setAttribute("referencia", referencia);
                    request.setAttribute("idRegistro", idRegistro);
                    request.setAttribute("temp", temp);
                    request.setAttribute("id_add_anexo", id_add_anexo);
                    request.setAttribute("id_m_anexo", id_m_anexo);
                    request.setAttribute("tempF", tempF);
                    request.setAttribute("tempp", tempp);
                    request.setAttribute("id_order", id_order);
                    request.setAttribute("Id_rol", UserRol);
                    request.setAttribute("id_rol_permission", id_rol_permission);
                    request.getRequestDispatcher("RecepcionMaterial.jsp").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 12 FIRMAR MASIVO">
                case 12:
                    String idsrecepcion = request.getParameter("idrecep");
                    rol_usuario = request.getParameter("firma");

                    result = jpac_recepcion.Modificar_firma_masivo(rol_usuario, idsrecepcion);
                    if (result) {
                        request.setAttribute("Actualizar_Firmas_Masivo", result);
                        request.getRequestDispatcher("RecepcionMaterial?opc=1&tempF=1").forward(request, response);
                    } else {
                        request.setAttribute("Error_app", result);
                        request.getRequestDispatcher("RecepcionMaterial?opc=1&tempF=1").forward(request, response);
                    }
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 13 ELIMINAR">
                case 13:
                    try {
                        id_registro = Integer.parseInt(request.getParameter("idRegistro"));
                    } catch (Exception e) {
                        id_registro = 0;
                    }

                    result = jpac_recepcion.EliminarRegistro(id_registro);
                    if (result) {
                        request.setAttribute("eliminar_registro_recepcion", result);
                        request.getRequestDispatcher("RecepcionMaterial?opc=1").forward(request, response);
                    } else {
                        request.setAttribute("Error_app", result);
                        request.getRequestDispatcher("RecepcionMaterial?opc=1").forward(request, response);
                    }
                    request.getRequestDispatcher("RecepcionMaterial?opc=1&idRegistro=0").forward(request, response);
                    break;
//</editor-fold>
            }
        } catch (NumberFormatException e) {
            request.getRequestDispatcher("RecepcionMaterial.jsp").forward(request, response);
        }
    }
// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (Exception ex) {
            Logger.getLogger(RecepcionMaterial.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (Exception ex) {
            Logger.getLogger(RecepcionMaterial.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
