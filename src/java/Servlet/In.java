package Servlet;

import Controladores.InyeccionJpaController;
import Controladores.ProcesosJpaController;
import Controladores.RolJpaController;
import Factory.ReferenciasInv;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "in", urlPatterns = {"/in"})
public class In extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        //<editor-fold defaultstate="collapsed" desc="SESION">
        HttpSession sesion = request.getSession();
        String rol_usuario = sesion.getAttribute("Rol/Nombres").toString();
        String UserRol = sesion.getAttribute("Id_rol").toString();
        int id_usuario = Integer.parseInt(sesion.getAttribute("Id_usuario").toString());
//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="JPA">
        InyeccionJpaController jpainyeccion = new InyeccionJpaController();
        ProcesosJpaController jpac_procesos = new ProcesosJpaController();
        RolJpaController jpac_rol = new RolJpaController();
        Calendar calendario = Calendar.getInstance();
        ReferenciasInv refinv = new ReferenciasInv();
//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="VARIABLES">
        int filtro = 0;
        int id_historial = 0;
        int id_iny_historial, id_registro = 0;
        int molde, proceso_r, id_iny, est, id_iny_m, conse, temp, id_order = 0;
        int idlote_c = 0;
        String id_lote_c = null;
        String formula = null, proceso = null, Id_proceso = null, lote_c_fin = null, con = null;
        String lote_p_codigo, lote_p_año, lote_p, lote_c = null, fecha, obs, consecutivo, codpro, loteC, lote_prn = null;
        String nombre_proceso = "", lote_p_final, n_rec, r_rec;
        String[] lotecc;
        String inicio_lote, tipo;
        String fin_lote;
        String rpr, dpr, rdv, fdv, ddv;
        boolean historial_pre, historial_dev;
        boolean result = false;
        List lst_lotesC;
        List lst_inyeccion, lst_consecutivos = null;
        int year = 0;
        int id_rol, id_rol_permission = 0;
        int opc = Integer.parseInt(request.getParameter("opc").toString());
        //</editor-fold>
        try {
            //<editor-fold defaultstate="collapsed" desc="TRY">
            try {
                year = Integer.parseInt(request.getParameter("year"));
            } catch (Exception e) {
                year = calendario.get(Calendar.YEAR);
            }
            try {
                Id_proceso = request.getParameter("Flt_Id_proceso");
            } catch (Exception e) {
                Id_proceso = null;
            }
            try {
                filtro = Integer.parseInt(request.getParameter("id_registro"));
            } catch (Exception e) {
                filtro = 0;
            }
            try {
                formula = request.getParameter("Formula");
            } catch (Exception e) {
                formula = null;
            }
            try {
                nombre_proceso = request.getParameter("Nombre_proceso");
            } catch (Exception e) {
                nombre_proceso = "";
            }
            try {
                id_iny = Integer.parseInt(request.getParameter("id_iny"));
            } catch (Exception e) {
                id_iny = 0;
            }
            try {
                id_iny_m = Integer.parseInt(request.getParameter("id_iny_m"));
            } catch (Exception e) {
                id_iny_m = 0;
            }
            try {
                id_iny_historial = Integer.parseInt(request.getParameter("iih").toString());
            } catch (Exception e) {
                id_iny_historial = 0;
            }
            try {
                id_historial = Integer.parseInt(request.getParameter("id_historial").toString());
            } catch (Exception e) {
                id_historial = 0;
            }
//</editor-fold>
            switch (opc) {
                //<editor-fold defaultstate="collapsed" desc="CASO 1 ATRIBUTOS">
                case 1:
                    tipo = "Inyeccion";
                    proceso = request.getParameter("slc_proceso");
                    if (proceso != null) {
                        if (proceso.equals("empty")) {
                            request.setAttribute("year", year);
                        } else {
                            String[] jj = proceso.split("/");
                            nombre_proceso = jj[1];
                            Id_proceso = jj[0];
                        }
                    }
                    if (formula != null) {
                        lst_inyeccion = jpac_procesos.Consulta_idProceso(Integer.parseInt(Id_proceso));
                        for (int i = 0; i < lst_inyeccion.size(); i++) {
                            Object[] obj_tipo_pro = (Object[]) lst_inyeccion.get(i);
                            if (obj_tipo_pro[7].equals("MATERIA_PRIMA")) {
                                lst_lotesC = jpainyeccion.Lotesciny(filtro);
                                if (lst_lotesC == null) {
                                    filtro = 0;
                                    formula = null;
                                    request.setAttribute("id_registro", filtro);
                                    request.setAttribute("Formula", formula);
                                }
                            }
                        }
                    }
                    try {
                        temp = Integer.parseInt(request.getParameter("temp"));
                    } catch (Exception e) {
                        temp = 0;
                    }
                    try {
                        id_order = Integer.parseInt(request.getParameter("id_order"));
                    } catch (Exception e) {
                        id_order = 0;
                    }
                    try {
                        id_rol_permission = Integer.parseInt(request.getParameter("id_rol_permission"));
                    } catch (Exception e) {
                        id_rol_permission = 0;
                    }
                    request.setAttribute("Nombre_proceso", nombre_proceso);
                    request.setAttribute("Flt_Id_proceso", Id_proceso);
                    request.setAttribute("iih", id_iny_historial);
                    request.setAttribute("id_registro", filtro);
                    request.setAttribute("id_iny_m", id_iny_m);
                    request.setAttribute("Formula", formula);
                    request.setAttribute("id_iny", id_iny);
                    request.setAttribute("Modulo", tipo);
                    request.setAttribute("year", year);
                    request.setAttribute("Rol/Nombres", rol_usuario);
                    request.setAttribute("temp", temp);
                    request.setAttribute("id_order", id_order);
                    request.setAttribute("Id_rol", UserRol);
                    request.setAttribute("id_rol_permission", id_rol_permission);
                    request.getRequestDispatcher("Inyeccion.jsp").forward(request, response);
                    break;
                //</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 2 REGISTRAR Y MODIFICAR">
                case 2:
                    if (formula != null) {
                        //<editor-fold defaultstate="collapsed" desc="REGISTRAR">
                        proceso_r = Integer.parseInt(request.getParameter("Flt_Id_proceso"));
                        codpro = request.getParameter("codigoProducto");
                        loteC = request.getParameter("lote_c");
                        lote_p_codigo = request.getParameter("lote_1");
                        lote_p_año = request.getParameter("lote_2");
                        lote_p = lote_p_codigo + "-" + lote_p_año;
                        molde = Integer.parseInt(request.getParameter("linea").toString());
                        fecha = request.getParameter("fecha");
                        obs = request.getParameter("obs");
                        if (loteC.contains("N/A") || loteC.contains(" / ")) {
                            if (loteC.contains(" / ")) {
                                String c[] = loteC.split(" / ");
                                lote_c_fin = c[0];
                                id_lote_c = c[1];
                                lote_c = id_lote_c + "/" + lote_c_fin;
                                lotecc = lote_c.split("-");
                                inicio_lote = lotecc[0];
                                fin_lote = lotecc[1];
                                lote_prn = lote_p_codigo + "-" + fin_lote;
                                lst_consecutivos = jpainyeccion.Validar_consecutivo_materiaprima(lote_c_fin);
                            } else {
                                lote_c = "N/A";
                                lote_prn = lote_p;
                                idlote_c = 0;
                                lst_consecutivos = jpac_procesos.Consulta_id_Proceso(proceso_r);
                            }
                            Object[] obj_cc2 = (Object[]) lst_consecutivos.get(0);
                            if (loteC.equals("N/A")) {
                                conse = (int) Double.parseDouble(obj_cc2[2].toString());
                            } else {
                                conse = Integer.parseInt(obj_cc2[1].toString());
                            }
                            consecutivo = "" + ((conse > 999) ? conse : (conse > 99) ? "0" + conse : (conse > 9) ? "00" + conse : (conse <= 9) ? "000" + conse : "0000");//trae el dato del ultimoi consecutivo
                            result = jpainyeccion.Registrar_inyeccion(consecutivo, codpro, lote_c, lote_p, lote_prn, fecha, obs, molde, 0, rol_usuario, "DISPONIBLE", "", 0, proceso_r, rol_usuario);
                            if (result) {
                                request.setAttribute("Registrar_Inyeccion", result);
                            } else {
                                request.setAttribute("Error_app", result);
                            }
                        } else {
                            request.setAttribute("Error_app", result);
                        }
                        request.getRequestDispatcher("in?opc=1").forward(request, response);
                        //</editor-fold>
                    } else {
                        //<editor-fold defaultstate="collapsed" desc="MODIFICAR">
                        id_iny = Integer.parseInt(request.getParameter("id_iny"));
                        String lote_p_1 = request.getParameter("lote_1");
                        String lote_p_2 = request.getParameter("lote_2");
                        lote_p_final = lote_p_1 + "-" + lote_p_2;
                        loteC = request.getParameter("lote_c");
                        if (loteC != null) {
                            String c[] = loteC.split("/");
                            lote_c_fin = c[0];
                            id_lote_c = c[1];
                        }
                        molde = Integer.parseInt(request.getParameter("linea"));
                        fecha = request.getParameter("fecha");
                        obs = request.getParameter("obs");
                        if (loteC == null) {
                            lote_c = "N/A";
                            lote_prn = lote_p_final;
                            idlote_c = 0;
                            con = request.getParameter("conin");
                        } else {
                            lote_c = id_lote_c + "/" + lote_c_fin;
                            lotecc = lote_c.split("-");
                            inicio_lote = lotecc[0];
                            fin_lote = lotecc[1];
                            lote_prn = lote_p_1 + "-" + fin_lote;
                        }
                        if (loteC == null) {
                            result = jpainyeccion.Modificar_inyeccion_concecutivo(con, lote_c, lote_p_final, lote_prn, fecha, obs, molde, id_iny);
                        } else {
                            result = jpainyeccion.Modificar_inyeccion(id_lote_c, lote_c, lote_p_final, lote_prn, fecha, obs, molde, id_iny);
                        }
                        if (result) {
                            request.setAttribute("Modificar_Inyeccion", result);
                        } else {
                            request.setAttribute("Error_app", result);
                        }
                        request.getRequestDispatcher("in?opc=1").forward(request, response);
                        //</editor-fold>
                    }
                    break;
                //</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 3 FINALIZAR">
                case 3:
                    id_iny = Integer.parseInt(request.getParameter("id_iny"));
                    est = Integer.parseInt(request.getParameter("est"));
                    result = jpainyeccion.Modificar_estado_inyeccion(est, id_iny);
                    if (result) {
                        request.setAttribute("finalizar_Inyeccion", result);
                        request.getRequestDispatcher("in?opc=1").forward(request, response);
                    } else {
                        request.setAttribute("Error_app", result);
                        request.getRequestDispatcher("in?opc=1").forward(request, response);
                    }
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 4 ESTADO CONTRAMUESTRAS">
                case 4:
                    id_iny = Integer.parseInt(request.getParameter("id_iny").toString());
                    int contrmuestra = Integer.parseInt(request.getParameter("cntm").toString());
                    r_rec = request.getParameter("res_recibido");
                    n_rec = request.getParameter("no_recibio");
                    result = jpainyeccion.Modificar_estado_contramuestra(contrmuestra, id_iny, r_rec, n_rec);
                    if (result) {
                        request.setAttribute("Estado_actualizado", result);
                        request.getRequestDispatcher("in?opc=1").forward(request, response);
                    } else {
                        request.setAttribute("Error_app", result);
                        request.getRequestDispatcher("in?opc=1").forward(request, response);
                    }
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 5 PRESTAMO">
                case 5:
                    try {
                        id_iny = Integer.parseInt(request.getParameter("id_iny"));
                    } catch (NumberFormatException e) {
                        id_iny = 0;
                    }
                    if (id_historial == 0) {
                        rpr = request.getParameter("txtprestamo");
                        dpr = request.getParameter("com_prestamo");
                        result = jpainyeccion.Registrar_historial_prestamo_inyeccion(id_iny, rpr, dpr);
                        if (result) {
                            request.setAttribute("prestamo_inyeccion", result);
                        } else {
                            request.setAttribute("Alerta", "ErrorModificar_inyeccion_prs");
                        }
                    } else {
                        rdv = request.getParameter("c_devolucion");
                        fdv = request.getParameter("txtfechadv");
                        ddv = request.getParameter("com_devolucion");
                        result = jpainyeccion.Registrar_Historial_Devolucion_inyeccion(rdv, fdv, ddv, id_historial);
                        if (result) {
                            request.setAttribute("devolucion_inyeccion", result);
                        } else {
                            request.setAttribute("Alerta", "ErrorModificar_inyeccion_dv");
                        }
                    }
                    request.getRequestDispatcher("in?opc=1").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 6 FILTRO AÑO">
                case 6:
                    tipo = "Inyeccion";
                    proceso = request.getParameter("slc_proceso");
                    year = Integer.parseInt(request.getParameter("year"));
                    request.setAttribute("Modulo", tipo);
                    request.setAttribute("Flt_Id_proceso", proceso);
                    request.setAttribute("year", year);
                    request.getRequestDispatcher("Inyeccion.jsp").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 7 INACTIVAR MASIVO">
                case 7:
                    String idsInyeccion = request.getParameter("id_iny");
                    est = Integer.parseInt(request.getParameter("est"));

                    result = jpainyeccion.Modificar_estado_inyeccion_masivo(est, idsInyeccion);
                    if (result) {
                        request.setAttribute("inactivar_masivo", result);
                        request.getRequestDispatcher("in?opc=1").forward(request, response);
                    } else {
                        request.setAttribute("Error_app", result);
                        request.getRequestDispatcher("in?opc=1").forward(request, response);
                    }
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 8 ELIMINAR REGISTRO">
                case 8:
                    try {
                        id_registro = Integer.parseInt(request.getParameter("idRegistro"));
                    } catch (Exception e) {
                        id_registro = 0;
                    }
                    result = jpainyeccion.EliminarRegistro(id_registro);
                    if (result) {
                        request.setAttribute("eliminar_registro_inyeccion", result);
                        request.getRequestDispatcher("in?opc=1").forward(request, response);
                    } else {
                        request.setAttribute("Error_app", result);
                        request.getRequestDispatcher("in?opc=1").forward(request, response);
                    }
                    request.getRequestDispatcher("in?opc=1&idRegistro=0").forward(request, response);
                    break;
//</editor-fold>
            }
        } catch (NumberFormatException e) {

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
        processRequest(request, response);
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
        processRequest(request, response);
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
