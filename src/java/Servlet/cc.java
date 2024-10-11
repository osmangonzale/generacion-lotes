package Servlet;

import Controladores.ControlConsecutivosJpaController;
import Controladores.ProcesosJpaController;
import Controladores.PruebasJpaController;
import Controladores.RolJpaController;
import Controladores.UsuarioJpaController;
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

@WebServlet(name = "cc", urlPatterns = {"/cc"})
public class cc extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //<editor-fold defaultstate="collapsed" desc="VARIABLES/SESION">
            ReferenciasInv refinv = new ReferenciasInv();
            HttpSession sesion = request.getSession();
            String rol_usuario = sesion.getAttribute("Rol/Nombres").toString();
            String UserRol = sesion.getAttribute("Id_rol").toString();
            int id_usuario = Integer.parseInt(sesion.getAttribute("Id_usuario").toString());
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="JPA">
            UsuarioJpaController jpacusa = new UsuarioJpaController();
            ControlConsecutivosJpaController jpacons = new ControlConsecutivosJpaController();
            PruebasJpaController jpac_pruebas = new PruebasJpaController();
            ProcesosJpaController jpac_procesos = new ProcesosJpaController();
            RolJpaController jpac_rol = new RolJpaController();
            Calendar calendario = Calendar.getInstance();
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="VARIABLES">
            int opc = Integer.parseInt(request.getParameter("opc").toString());
            String nombre_proceso = null, formula, tipo = "", proceso, Id_proceso = "", pruebas = "", tipoproceso, cadena = "";
            String file_name = "", observacion = "", tipo_registro, lote, fecha, obs, nombre;
            int year = 0, id_ctrcons = 0, id, entregaPen, temp, id_order = 0;
            int aplica, id_anexos, filtro, cons, id_add_anexo, id_registro, idRol = 0;
            List lst_lotes = null;
            boolean result = false;
            int id_rol, id_rol_permission = 0;
            boolean entrega = true, ultimo_act = false, registro = true;
            int id_m_anexo = 0;
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="TRY">
            try {
                nombre_proceso = request.getParameter("Nombre_proceso");
            } catch (Exception e) {
                nombre_proceso = null;
            }
            try {
                Id_proceso = request.getParameter("Flt_Id_proceso");
            } catch (Exception e) {
                Id_proceso = null;
            }
            try {
                proceso = request.getParameter("slc_proceso");
            } catch (Exception e) {
                proceso = null;
            }
            try {
                id_ctrcons = Integer.parseInt(request.getParameter("id_ctrcons"));
            } catch (Exception e) {
                id_ctrcons = 0;
            }
            try {
                filtro = Integer.parseInt(request.getParameter("id_registro"));
            } catch (Exception e) {
                filtro = 0;
            }
            try {
                formula = request.getParameter("Formula");
            } catch (Exception e) {
                formula = "";
            }
            try {
                year = Integer.parseInt(request.getParameter("year"));
            } catch (Exception e) {
                year = calendario.get(Calendar.YEAR);
            }
            try {
                id_anexos = Integer.parseInt(request.getAttribute("id_m_anexo").toString());
            } catch (Exception e) {
                id_anexos = 0;
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

                    tipo = "controlconsecutivos";
                    formula = request.getParameter("Formula");
                    String yearParam = request.getParameter("year");
                    if (yearParam != null && !yearParam.isEmpty()) {
                        year = Integer.parseInt(yearParam);
                    }
                    if (proceso != null) {
                        if (proceso.equals("empty")) {
                            request.setAttribute("year", year);
                        } else {
                            String[] jj = proceso.split("/");
                            nombre_proceso = jj[1];
                            Id_proceso = jj[0];
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
                    request.setAttribute("Formula", formula);
                    request.setAttribute("id_ctrcons", id_ctrcons);
                    request.setAttribute("id_registro", filtro);
                    request.setAttribute("Modulo", tipo);
                    request.setAttribute("year", year);
                    request.setAttribute("id_add_anexo", id_add_anexo);
                    request.setAttribute("id_m_anexo", id_m_anexo);
                    request.setAttribute("temp", temp);
                    request.setAttribute("id_order", id_order);
                    request.setAttribute("Id_rol", UserRol);
                    request.setAttribute("id_rol_permission", id_rol_permission);
                    request.getRequestDispatcher("Controlconsecutivos.jsp").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 2 MODIFICAR/REGISTRAR">
                case 2:
                    formula = request.getParameter("Formula");
                    if (formula != null) {
                        //<editor-fold defaultstate="collapsed" desc="REGISTRO">
                        nombre = request.getParameter("Nombre");
                        pruebas = request.getParameter("slc_prueba");
                        int f_id_proceso = Integer.parseInt(request.getParameter("Flt_Id_proceso"));
                        String lote_2 = request.getParameter("lote_2");
                        lote = formula + "-" + lote_2;
                        fecha = request.getParameter("fecha");
                        obs = request.getParameter("obs");
                        aplica = 0;
                        List lst_consecutivos = jpacons.Consecutivo_proceso(lote, Id_proceso);
                        Object[] obj_cc2 = (Object[]) lst_consecutivos.get(0);
                        if (obj_cc2[0] != null) {
                            cons = Integer.parseInt(obj_cc2[1].toString());
                        } else {
                            cons = Integer.parseInt(obj_cc2[2].toString());
                        }
                        String consecutivo = "" + ((cons > 999) ? cons : (cons > 99) ? "0" + cons : (cons > 9) ? "00" + cons : (cons <= 9) ? "000" + cons : "0000");
                        result = jpacons.Registrar_lote(consecutivo, nombre, lote, fecha, obs, rol_usuario, f_id_proceso, aplica, pruebas);
                        if (result) {
                            request.setAttribute("Registrar_Cc", result);
                            request.getRequestDispatcher("cc?opc=1").forward(request, response);
                        } else {
                            request.setAttribute("Alerta", "Error_lote");
                            request.getRequestDispatcher("cc?opc=1").forward(request, response);
                        }
//</editor-fold>
                    } else {
                        //<editor-fold defaultstate="collapsed" desc="MODIFICAR">
                        id_ctrcons = Integer.parseInt(request.getParameter("id_ctrcons"));
                        String lote1 = request.getParameter("lote_1");
                        String lote2 = request.getParameter("lote_2");
                        lote = lote1 + "-" + lote2;
                        fecha = request.getParameter("fecha");
                        obs = request.getParameter("obs");
                        pruebas = request.getParameter("pruebas");
                        aplica = 0;
                        result = jpacons.Modificar_Controlconsecutivos(lote, fecha, obs, id_ctrcons, pruebas, aplica);
                        if (result) {
                            request.setAttribute("Modificar_Cc", result);
                            request.getRequestDispatcher("cc?opc=1&id_ctrcons=0").forward(request, response);
                        } else {
                            request.setAttribute("Alerta", "Errorentrega_lote");
                            request.getRequestDispatcher("cc?opc=1").forward(request, response);
                        }
//</editor-fold>
                    }
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 3 ENTREGADOS">
                case 3:
                    pruebas = request.getParameter("slc_prueba");
                    id = Integer.parseInt(request.getParameter("idpro"));
                    try {
                        entregaPen = Integer.parseInt(request.getParameter("ent"));
                    } catch (Exception e) {
                        entregaPen = 0;
                    }
                    if (pruebas != null) {
                        //<editor-fold defaultstate="collapsed" desc="SELECIONAR PRUEBA">
                        String[] prueba = pruebas.split("/");
                        aplica = Integer.parseInt(prueba[0]);
                        tipoproceso = prueba[1];
                        entrega = jpacons.Modificar_entregado(id, tipoproceso);
//</editor-fold>
                    } else if (entregaPen == 2) {
                        result = jpacons.Modificar_entregadopendiente(id, entregaPen);
                    } else {
                        result = jpacons.Modificar_noentregado(id);
                    }
                    if (result) {
                        request.setAttribute("Entregado_Cc", result);
                    } else {
                        request.setAttribute("Alerta", "Errorentrega_lote");
                    }
                    request.setAttribute("id_ctrcons", id);
                    request.getRequestDispatcher("cc?opc=1").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 4 ANEXOS">
                case 4:
                    id = Integer.parseInt(request.getParameter("id"));
                    observacion = request.getParameter("Txt_descripcion");
                    file_name = request.getParameter("file_name");
                    try {
                        id_anexos = Integer.parseInt(request.getParameter("id_m_anexo"));
                    } catch (NumberFormatException e) {
                        id_anexos = 0;
                    }
                    tipo_registro = "R-GC-059";
                    if (id_anexos == 0) {
                        result = jpacons.Registrar_anexo(id, tipo_registro, file_name, observacion, rol_usuario);
                        if (result) {
                            request.setAttribute("Registrar_Anexo", result);
                        } else {
                            request.setAttribute("Alerta", "Error_Registro_Anexo");
                        }
                    } else {
                        result = jpacons.Modificar_Anexos_control_concecutivo(file_name, observacion, id_anexos);
                        if (result) {
                            request.setAttribute("Modificar_Anexo", result);
                        } else {
                            request.setAttribute("Alerta", "Error_Modificar_Anexo");
                        }
                    }
                    request.setAttribute("id", id);
                    request.setAttribute("id_m_anexo", id_anexos);
                    request.getRequestDispatcher("cc?opc=1&id_add_anexo=" + id + "").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 5 ESTADO">
                case 5:
                    id = Integer.parseInt(request.getParameter("id_cons"));
                    result = jpacons.Estado_finalizado(id);
                    if (result) {
                        request.setAttribute("Estado_Cc", result);
                        request.getRequestDispatcher("cc?opc=1").forward(request, response);
                    } else {
                        request.setAttribute("Alerta", "Error_finaliza");
                        request.getRequestDispatcher("cc?opc=1").forward(request, response);
                    }
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 6 FILTRO AÑO">
                case 6:
                    tipo = "Inyeccion";
                    proceso = request.getParameter("slc_proceso");
                    String yearString = request.getParameter("year");
                    year = Integer.parseInt(yearString);
                    request.setAttribute("Modulo", tipo);
                    request.setAttribute("Flt_Id_proceso", proceso);
                    request.setAttribute("year", year);
                    request.setAttribute("Id_rol", UserRol);
                    request.getRequestDispatcher("Controlconsecutivos.jsp").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 7 INACTIVAR MASIVO">
                case 7:
                    String idsConsecutivos = request.getParameter("id_cons");
                    int est = Integer.parseInt(request.getParameter("est"));

                    result = jpacons.Modificar_estado_consecutivo_masivo(est, idsConsecutivos);
                    if (result) {
                        request.setAttribute("finalizar_masivo_consecutivo", result);
                        request.getRequestDispatcher("cc?opc=1").forward(request, response);
                    } else {
                        request.setAttribute("Error_app", result);
                        request.getRequestDispatcher("cc?opc=1").forward(request, response);
                    }
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 8 ELIMINAR CONSECUTIVO">
                case 8:
                    try {
                        id_registro = Integer.parseInt(request.getParameter("idRegistro"));
                    } catch (Exception e) {
                        id_registro = 0;
                    }
                    result = jpacons.EliminarRegistro(id_registro);
                    if (result) {
                        request.setAttribute("eliminar_registro_consecutivo", result);
                        request.getRequestDispatcher("cc?opc=1").forward(request, response);
                    } else {
                        request.setAttribute("Error_app", result);
                        request.getRequestDispatcher("cc?opc=1").forward(request, response);
                    }
                    request.getRequestDispatcher("Controlconsecutivos?opc=1&idRegistro=0").forward(request, response);
                    break;
//</editor-fold>
            }
        } catch (Exception e) {
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
