package Servlet;

import Controladores.ControlConsecutivosJpaController;
import Controladores.ProcesosJpaController;
import Controladores.PruebasJpaController;
import Controladores.RolJpaController;
import Controladores.UsuarioJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Factory.ReferenciasInv;
import java.util.Calendar;

public class ControlConsecutivo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        try {
            //<editor-fold defaultstate="collapsed" desc="VARIBLES">
            ReferenciasInv refinv = new ReferenciasInv();
            HttpSession sesion = request.getSession();
            String rol_usuario = sesion.getAttribute("Rol/Nombres").toString();
            int id_usuario = Integer.parseInt(sesion.getAttribute("Id_usuario").toString());
            String UserRol = sesion.getAttribute("Id_rol").toString();
            //JPAS
            UsuarioJpaController jpacusa = new UsuarioJpaController();
            RolJpaController jpac_rol = new RolJpaController();
            ControlConsecutivosJpaController jpacons = new ControlConsecutivosJpaController();
            PruebasJpaController jpac_pruebas = new PruebasJpaController();
            ProcesosJpaController jpac_procesos = new ProcesosJpaController();
            //Variables Globales
            int opc = Integer.parseInt(request.getParameter("opc").toString());
            List lst_lotes = null;
//            List lst_usuario = null;
            List lst_pruebas = null;
            List lst_consecutivos = null;
            boolean registro = true;
            boolean ultimo_act = false;
            boolean entrega = true;
            int rdaplica;
            String tipo = "";
            String nombre, codnom, lote, obs, fecha, filtro, nombre_proceso, pruebas;
            int id_ctrcons;
            int id;
            int id_proceso;
            int cons = 0;
            int actual = 0;
            int aplica;
            int id_registro = 0;
            int year;
            int id_anexos=0;
            String file_name = "";
            String tipo_registro = "";
            String observacion = "";
            String consecutivo = "";
            String tipoproceso;
            String cadena = "";
            Object[] arrayObj;
            int id_rol, id_rol_permission = 0;
            Calendar calendario = Calendar.getInstance();
//</editor-fold>
            switch (opc) {
                case 1:
                    tipo = "controlconsecutivos";
                    try {
                        id_ctrcons = Integer.parseInt((String) request.getAttribute("Flt_Id_proceso"));
                    } catch (Exception e) {
                        id_ctrcons = 0;
                    }
                    try {
                        id_rol_permission = Integer.parseInt(request.getParameter("id_rol_permission"));
                    } catch (Exception e) {
                        id_rol_permission = 0;
                    }
                    if (id_ctrcons != 0) {
                        filtro = (String) request.getAttribute("Flt_Id_proceso");
                        nombre_proceso = (String) request.getAttribute("Nombre_proceso");
                        request.setAttribute("Flt_Id_proceso", filtro);
                        request.setAttribute("Nombre_proceso", nombre_proceso);
                        year = (Integer) request.getAttribute("year");
                        request.setAttribute("year", year);
                    }
                    if (request.getAttribute("year") != null) {
                        year = (Integer) request.getAttribute("year");
                        request.setAttribute("year", year);
                    }
                    
                    request.setAttribute("Modulo", tipo);
                    request.setAttribute("id_ctrcons", id_ctrcons);
                    request.setAttribute("Id_rol", UserRol);
                    request.setAttribute("id_rol_permission", id_rol_permission);
                    request.getRequestDispatcher("Controlconsecutivos.jsp").forward(request, response);
                    break;
                case 2:
                    String formula = request.getParameter("txtlotefn");
                    lst_lotes = refinv.Productos(formula);
                    if (!lst_lotes.isEmpty()) {
                        out.print(cadena);
                    } else {
                        out.print("vacia");
                    }
                    break;
                case 3:
                    id_proceso = Integer.parseInt(request.getParameter("id_proceso"));
                    consecutivo = request.getParameter("txtconsecutivo");
                    codnom = request.getParameter("slclotes");
                    pruebas = request.getParameter("slprueba");
                    String[] process = pruebas.split("/");
                    aplica = Integer.parseInt(process[0]);
                    tipoproceso = process[1];
                    lote = request.getParameter("txtlotecm");
                    obs = request.getParameter("obs");
                    fecha = request.getParameter("txtfecha");
//                    lst_consecutivos = jpacons.Consecutivo_proceso(lote, id_proceso);
                    Object[] obj_cc = (Object[]) lst_consecutivos.get(0);
                    if (obj_cc[0] != null) {
                        cons = Integer.parseInt(obj_cc[1].toString());
                    } else {
                        cons = Integer.parseInt(obj_cc[2].toString());
                    }
                    consecutivo = "" + ((cons > 999) ? cons : (cons > 99) ? "0" + cons : (cons > 9) ? "00" + cons : (cons <= 9) ? "000" + cons : "0000");
//                    registro = jpacons.Registrar_lote(consecutivo, codnom, aplica, lote, fecha, obs, rol_usuario, id_proceso, id_usuario, tipoproceso);
                    if (registro) {
                        request.setAttribute("Alerta", "Registro_lote");
                        request.getRequestDispatcher("ControlConsecutivos?opc=1").forward(request, response);
                    } else {
                        request.setAttribute("Alerta", "Error_lote");
                        request.getRequestDispatcher("ControlConsecutivos?opc=1").forward(request, response);
                    }
                    break;
                case 4:
                    filtro = request.getParameter("flt");
                    nombre_proceso = request.getParameter("nombre_pro");
                    year = Integer.parseInt(request.getParameter("year"));
                    pruebas = request.getParameter("slc_prueba");
                    String[] prueba = pruebas.split("/");
                    aplica = Integer.parseInt(prueba[0]);
                    tipoproceso = prueba[1];
                    request.setAttribute("Flt_Id_proceso", filtro);
                    request.setAttribute("Nombre_proceso", nombre_proceso);
                    request.setAttribute("year", year);
                    id = Integer.parseInt(request.getParameter("idpro"));
                    entrega = jpacons.Modificar_entregado(id, tipoproceso);
                    ultimo_act = jpacons.Ultimo_actualizado(id_usuario, id);
                    if (entrega) {
                        request.setAttribute("Alerta", "Entrega_lote");
                        request.getRequestDispatcher("ControlConsecutivos?opc=1").forward(request, response);
                    } else {
                        request.setAttribute("Alerta", "Errorentrega_lote");
                        request.getRequestDispatcher("ControlConsecutivos?opc=1").forward(request, response);
                    }
                    break;
                case 5:
                    filtro = request.getParameter("flt");
                    nombre_proceso = request.getParameter("nombre_pro");
                    year = Integer.parseInt(request.getParameter("year"));
                    request.setAttribute("Flt_Id_proceso", filtro);
                    request.setAttribute("Nombre_proceso", nombre_proceso);
                    request.setAttribute("year", year);
                    id = Integer.parseInt(request.getParameter("idpro"));
                    request.setAttribute("id_ctrcons", id);
                    entrega = jpacons.Modificar_noentregado(id);
                    ultimo_act = jpacons.Ultimo_actualizado(id_usuario, id);
                    if (entrega) {
                        request.setAttribute("Alerta", "Noentrega_lote");
                        request.getRequestDispatcher("ControlConsecutivos?opc=1").forward(request, response);
                    } else {
                        request.setAttribute("Alerta", "Errorentrega_lote");
                        request.getRequestDispatcher("ControlConsecutivos?opc=1").forward(request, response);
                    }
                    break;
                case 6:
                    filtro = request.getParameter("flt");
                    nombre_proceso = request.getParameter("nombre_pro");
                    request.setAttribute("Flt_Id_proceso", filtro);
                    request.setAttribute("Nombre_proceso", nombre_proceso);
                    String lote1 = request.getParameter("txtloteon");
                    String lote2 = request.getParameter("txtlote");
                    lote = lote1 + "-" + lote2;
                    fecha = request.getParameter("txtfecha");
                    obs = request.getParameter("obs");
                    pruebas = request.getParameter("slc_prueba");
                    if (pruebas != null) {
                        if (!pruebas.equals("1") && !pruebas.equals("1/No aplica")) {
                            process = pruebas.split("/");
                            aplica = Integer.parseInt(process[0]);
                            tipoproceso = process[1];
                        } else {
                            tipoproceso = "No aplica";
                            aplica = 1;
                        }
                    } else {
                        rdaplica = Integer.parseInt(request.getParameter("radioapl").toString());
                        aplica = rdaplica;
                        tipoproceso = "Pendiente";
                    }
                    id = Integer.parseInt(request.getParameter("idmod"));
                    registro = jpacons.Modificar_Controlconsecutivos(lote, fecha, obs, id, tipoproceso, aplica);
                    ultimo_act = jpacons.Ultimo_actualizado(id_usuario, id);
                    if (registro) {
                        year = calendario.get(Calendar.YEAR);
                        request.setAttribute("year", year);
                        request.setAttribute("Alerta", "Entrega_lote");
                        request.getRequestDispatcher("ControlConsecutivos?opc=1").forward(request, response);
                    } else {
                        request.setAttribute("Alerta", "Errorentrega_lote");
                        request.getRequestDispatcher("ControlConsecutivos?opc=1").forward(request, response);
                    }
                    break;
                case 7:
                    String proceso = request.getParameter("slc_proceso");
                    year = calendario.get(Calendar.YEAR);
                    if (proceso.equals("empty")) {
                        request.setAttribute("year", year);
                        request.getRequestDispatcher("ControlConsecutivos?opc=1").forward(request, response);
                    } else {
                        String[] jj = proceso.split("/");
                        nombre_proceso = jj[1];
                        String Id_proceso = jj[0];
                        request.setAttribute("year", year);
                        request.setAttribute("Flt_Id_proceso", Id_proceso);
                        request.setAttribute("Nombre_proceso", nombre_proceso);
                        request.getRequestDispatcher("ControlConsecutivos?opc=1").forward(request, response);
                    }
                    break;
                case 8:
//                    year = Integer.parseInt(request.getParameter("year"));
                    year = calendario.get(Calendar.YEAR);
                    filtro = request.getParameter("flt");
                    pruebas = request.getParameter("slc_prueba");
                    if (pruebas != null) {
                        process = pruebas.split("/");
                        aplica = Integer.parseInt(process[0]);
                        tipoproceso = process[1];
                    } else {
                        rdaplica = Integer.parseInt(request.getParameter("radioapl").toString());
                        aplica = rdaplica;
                        tipoproceso = "Pendiente";
                    }
                    nombre_proceso = request.getParameter("nombre_pro");
                    request.setAttribute("year", year);
                    request.setAttribute("Flt_Id_proceso", filtro);
                    request.setAttribute("Nombre_proceso", nombre_proceso);
                    //<editor-fold defaultstate="collapsed" desc="comment">
//                    lst_consecutivos = jpacons.Control_consecutivos(Integer.parseInt(filtro), year);
//                    if(lst_consecutivos != null){
//                        Object[] obj_consecutivo = (Object[]) lst_consecutivos.get(0);
//                        cons = Integer.parseInt(obj_consecutivo[1].toString());
//                        actual = Integer.parseInt(obj_consecutivo[15].toString());
//                    }else{
//                        List procesos = jpac_procesos.Consulta_idProceso(Integer.parseInt(filtro));
//                        Object[] obj_Consecutivopro = (Object[]) procesos.get(0);
//                        cons = 0;
//                        actual = Integer.parseInt(obj_Consecutivopro[4].toString());
//                    }
//                    if (cons < actual) {
//                        if (cons == actual) {
//                            cons = actual + 1;
//                        } else {
//                            cons = actual;
//                        }
//                    } else {
//                        cons = cons + 1;
//                    }
//</editor-fold>
                    codnom = request.getParameter("slc_lotes");
                    lote = request.getParameter("txtlotecm");
                    obs = request.getParameter("obs");
                    fecha = request.getParameter("txtfecha");
                    id_proceso = Integer.parseInt(request.getParameter("id_proceso"));
//                    lst_consecutivos = jpacons.Consecutivo_proceso(lote, id_proceso);
                    Object[] obj_cc2 = (Object[]) lst_consecutivos.get(0);
                    if (obj_cc2[0] != null) {
                        cons = Integer.parseInt(obj_cc2[1].toString());
                    } else {
                        cons = Integer.parseInt(obj_cc2[2].toString());
                    }
                    consecutivo = "" + ((cons > 999) ? cons : (cons > 99) ? "0" + cons : (cons > 9) ? "00" + cons : (cons <= 9) ? "000" + cons : "0000");
//                    registro = jpacons.Registrar_lote(consecutivo, codnom, aplica, lote, fecha, obs, rol_usuario, id_proceso, id_usuario, tipoproceso);
                    if (registro) {
                        request.setAttribute("Alerta", "Registro_lote");
                        request.getRequestDispatcher("ControlConsecutivos?opc=1").forward(request, response);
                    } else {
                        request.setAttribute("Alerta", "Error_lote");
                        request.getRequestDispatcher("ControlConsecutivos?opc=1").forward(request, response);
                    }
                    break;
                case 9:
                    filtro = request.getParameter("flt");
                    nombre_proceso = request.getParameter("nombre_pro");
                    year = calendario.get(Calendar.YEAR);
                    request.setAttribute("year", year);
                    request.setAttribute("Flt_Id_proceso", filtro);
                    request.setAttribute("Nombre_proceso", nombre_proceso);
                    id_registro = Integer.parseInt(request.getParameter("id_registro"));
                    tipo_registro = request.getParameter("registro");
                    file_name = request.getParameter("file_name");
                    observacion = request.getParameter("observacion");
                    registro = jpacons.Registrar_anexo(id_registro, tipo_registro, file_name, observacion, rol_usuario);
                    ultimo_act = jpacons.Ultimo_actualizado(id_usuario, id_registro);
                    if (registro) {
                        request.setAttribute("Alerta", "Registro_anexo");
                        request.getRequestDispatcher("ControlConsecutivos?opc=1").forward(request, response);
                    } else {
                        request.setAttribute("Alerta", "Error_anexo");
                        request.getRequestDispatcher("ControlConsecutivos?opc=1").forward(request, response);
                    }
                    break;
                case 10:
//                    filtro = request.getParameter("flt");
//                    nombre_proceso = request.getParameter("nombre_pro");
//                    year = calendario.get(Calendar.YEAR);
//                    request.setAttribute("year", year);
//                    request.setAttribute("Flt_Id_proceso", filtro);
//                    request.setAttribute("Nombre_proceso", nombre_proceso);
//                    id = Integer.parseInt(request.getParameter("idpro"));
//                    request.setAttribute("id_ctrcons", id);
//                    entrega = jpacons.Modificar_entregadopendiente(id);
//                    ultimo_act = jpacons.Ultimo_actualizado(id_usuario, id);
//                    if (entrega) {
//                        request.setAttribute("Alerta", "Noentrega_lote");
//                        request.getRequestDispatcher("ControlConsecutivos?opc=1").forward(request, response);
//                    } else {
//                        request.setAttribute("Alerta", "Errorentrega_lote");
//                        request.getRequestDispatcher("ControlConsecutivos?opc=1").forward(request, response);
//                    }
                    break;
                case 11:
                    filtro = request.getParameter("flt");
                    nombre_proceso = request.getParameter("nombre_pro");
                    year = calendario.get(Calendar.YEAR);
                    request.setAttribute("year", year);
                    request.setAttribute("Flt_Id_proceso", filtro);
                    request.setAttribute("Nombre_proceso", nombre_proceso);
                    id = Integer.parseInt(request.getParameter("id_cons"));
                    registro = jpacons.Estado_finalizado(id);
                    ultimo_act = jpacons.Ultimo_actualizado(id_usuario, id);
                    if (registro) {
                        request.setAttribute("Alerta", "Finaliza_lote");
                        request.getRequestDispatcher("ControlConsecutivos?opc=1").forward(request, response);
                    } else {
                        request.setAttribute("Alerta", "Error_finaliza");
                        request.getRequestDispatcher("ControlConsecutivos?opc=1").forward(request, response);
                    }
                    break;
                case 12:
                    //<editor-fold defaultstate="collapsed" desc="SETEAR PRUEBAS">
                    lst_pruebas = jpac_pruebas.Pruebas();
                    out.print(cadena);
//</editor-fold>
                    break;
                case 13:
                    //<editor-fold defaultstate="collapsed" desc="TRAER ULTIMO LOTE">
                    int idfiltro = Integer.parseInt(request.getParameter("filtro"));
                    formula = request.getParameter("formula");
                    lst_lotes = jpacons.Consulta_ultlote(formula, idfiltro);
                    if (lst_lotes != null) {
                        Object[] obj_ultimolote = (Object[]) lst_lotes.get(0);
                        if (obj_ultimolote[1] == null) {
                            out.print(" ");
                        } else {
                            out.print(obj_ultimolote[1].toString());
                        }
                    } else {
                        out.print(" ");
                    }
//</editor-fold>
                    break;
                case 14:
                    //<editor-fold defaultstate="collapsed" desc="TRAER REGISTRO">
                    id_ctrcons = Integer.parseInt(request.getParameter("idRegistro"));
                    List lst_registro = jpacons.Traer_Controlconsecutivos(id_ctrcons);
                    out.print(cadena);
//</editor-fold>
                    break;
                case 15:
                    //<editor-fold defaultstate="collapsed" desc="FILTRAR POR AÑO">
                    String proceso1 = request.getParameter("proceso");
                    year = Integer.parseInt(request.getParameter("year"));
                    if (proceso1.equals("empty")) {
                        request.setAttribute("year", year);
                        request.getRequestDispatcher("ControlConsecutivos?opc=1").forward(request, response);
                    } else {
                        String[] jj = proceso1.split("/");
                        nombre_proceso = jj[1];
                        id_proceso = Integer.parseInt(jj[0]);
                        request.setAttribute("Flt_Id_proceso", id_proceso);
                        request.setAttribute("Nombre_proceso", nombre_proceso);
                        request.setAttribute("year", year);
                        request.getRequestDispatcher("ControlConsecutivos?opc=1").forward(request, response);
                    }
//</editor-fold>
                    break;
                case 16:
                    //<editor-fold defaultstate="collapsed" desc="CARGAR MODAL ANEXOS">
                    id = Integer.parseInt(request.getParameter("id"));
                    year = Integer.parseInt(request.getParameter("year"));
                    nombre_proceso = request.getParameter("nombre_proceso");
                    id_ctrcons = Integer.parseInt(request.getParameter("filtro"));
                    if (!nombre_proceso.equals("empty")) {
                        request.setAttribute("Nombre_proceso", nombre_proceso);
                    }                  
                    request.setAttribute("Flt_Id_proceso", id_ctrcons);
                    request.setAttribute("year", year);
                    request.setAttribute("id", id);
                    request.getRequestDispatcher("ControlConsecutivos?opc=1").forward(request, response);
//</editor-fold>
                    break;
                case 17:
                    //<editor-fold defaultstate="collapsed" desc="REGISTRAR ANEXO">
                    id_ctrcons = Integer.parseInt(request.getParameter("filtro"));
                    id = Integer.parseInt(request.getParameter("idRegistro_anexo"));
                    year = Integer.parseInt(request.getParameter("year"));
                    nombre_proceso = request.getParameter("nombre_proceso");
                    cadena = request.getParameter("Txt_descripcion");
                    String[] pruebaArray = cadena.split("<hr />");
                    file_name = pruebaArray[0];
                    observacion = pruebaArray[1];
                    file_name = file_name.replace("<h3>Anexo:</h3>", "").replace("<div id=\"div_anexo\">", "").replace("</div>", "");
                    observacion = observacion.replace("<h3>Observaciones:</h3>", "").replace("<div id=\"div_obs\">", "").replace("</div>", "");
                    tipo_registro = "R-GC-059";
                    if (jpacons.Registrar_anexo(id, tipo_registro, file_name, observacion, rol_usuario)) {
                        ultimo_act = jpacons.Ultimo_actualizado(id_usuario, id);
                        request.setAttribute("Alerta", "Registro_Anexo");
                    } else {
                        request.setAttribute("Alerta", "Error_Registro_Anexo");
                    }
                    if (!nombre_proceso.equals("empty")) {
                        request.setAttribute("Nombre_proceso", nombre_proceso);
                    }
                    request.setAttribute("Flt_Id_proceso", id_ctrcons);
                    request.setAttribute("year", year);
                    request.setAttribute("id", id);
                    request.getRequestDispatcher("ControlConsecutivos?opc=1").forward(request, response);
//</editor-fold>
                    break;
                case 18:
                  
                    break;
            }
        } catch (Exception ex) {
            // Logger.getLogger(Orden.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("Alerta", "Error_sesion");
            request.getRequestDispatcher("index.jsp").forward(request, response);
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
