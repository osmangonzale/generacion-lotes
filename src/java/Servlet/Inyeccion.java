package Servlet;

import Controladores.ControlConsecutivosJpaController;
import Controladores.InyeccionJpaController;
import Controladores.ProcesosJpaController;
import Controladores.UsuarioJpaController;
import Factory.ReferenciasInv;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Inyeccion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //<editor-fold defaultstate="collapsed" desc="JPAS">
            UsuarioJpaController jpacusa = new UsuarioJpaController();
            ControlConsecutivosJpaController jpacons = new ControlConsecutivosJpaController();
            InyeccionJpaController jpainyeccion = new InyeccionJpaController();
            ProcesosJpaController jpac_procesos = new ProcesosJpaController();
            ReferenciasInv refinv = new ReferenciasInv();
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="SESION">
            HttpSession sesion = request.getSession();
            String rol_usuario = sesion.getAttribute("Rol/Nombres").toString();
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="VARIABLES">
            int opc = Integer.parseInt(request.getParameter("opc").toString());
            int id_usuario = Integer.parseInt(sesion.getAttribute("Id_usuario").toString());
            Boolean registro = false;
            int id_rgt_inyeccion = 0;
            int id_iny = 0;
            int filtro = 0;
            int id_historial = 0;
            int id_iny_historial = 0;
            int molde;
            int idlote_c = 0;
            int con = 0;
            String id_c;
            int conse = 0;
            String r_rec;
            String n_rec;
            String txtlotec;
            String v_cprima;
            String lotep1 = "";
            String lotep2, lote_p, codpro, lotec, fecha, obs, consecutivo, lote_prn, loteC, cons;
            String nombre_proceso = "";
            String[] lotecc;
            String inicio_lote;
            String fin_lote;
            String nombreF;
            String rpr, dpr, rdv, fdv, ddv;
            String lotefn;
            String cadena;
            boolean historial_pre, historial_dev;
            List lst_lotes, lst_ult_lote, lst_procesos, lst_lotesC, lst_contC;
            int id_proceso;
            Object[] obj_ultlote, obj_procesos, lotesC;
            Object[] obj_Inyeccion;
            String ultimo_lote;
            List lst_inyeccion;
            int cns = 0;
            int actual = 0;
            int year;
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="CASOS">
            switch (opc) {
                    //<editor-fold defaultstate="collapsed" desc="CASO 1">
                case 1:
                    try {
                        year = Integer.parseInt(request.getParameter("year"));
                        request.setAttribute("year", year);
                    } catch (Exception e) {
                        year = 0;
                    }
                    try {
                        filtro = Integer.parseInt(request.getParameter("flt").toString());
                        nombre_proceso = request.getParameter("nombre_pro");
                        request.setAttribute("Flt_Id_proceso", filtro);
                        request.setAttribute("Nombre_proceso", nombre_proceso);
                    } catch (Exception e) {
                        id_iny = 0;
                    }
                    try {
                        id_iny = Integer.parseInt(request.getParameter("id").toString());
                        filtro = Integer.parseInt(request.getParameter("flt").toString());
                        nombre_proceso = request.getParameter("nombre_pro");
                        request.setAttribute("Flt_Id_proceso", filtro);
                        request.setAttribute("Nombre_proceso", nombre_proceso);
                    } catch (Exception e) {
                        id_iny = 0;
                    }

                    try {
                        id_iny_historial = Integer.parseInt(request.getParameter("iih").toString());
                        filtro = Integer.parseInt(request.getParameter("flt").toString());
                        nombre_proceso = request.getParameter("nombre_pro");
                        request.setAttribute("Flt_Id_proceso", filtro);
                        request.setAttribute("Nombre_proceso", nombre_proceso);
                    } catch (Exception e) {
                        id_iny_historial = 0;
                    }

                    if (id_iny != 0) {
                        filtro = Integer.parseInt(request.getParameter("flt").toString());
                        nombre_proceso = request.getParameter("nombre_pro");
                        request.setAttribute("Flt_Id_proceso", filtro);
                        request.setAttribute("Nombre_proceso", nombre_proceso);
                    }
                    request.setAttribute("id_iny", id_iny);
                    request.setAttribute("iih", id_iny_historial);
                    request.getRequestDispatcher("Inyeccion.jsp").forward(request, response);
                    break;
                    //</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="CASO 2">
                case 2:
                    String proceso = request.getParameter("slc_proceso");
                    if (proceso.equals("empty")) {
                        request.getRequestDispatcher("Inyeccion?opc=1").forward(request, response);
                    } else {
                        String[] jj = proceso.split("/");
                        nombre_proceso = jj[1];
                        String Id_proceso = jj[0];
                        request.setAttribute("Flt_Id_proceso", Id_proceso);
                        request.setAttribute("Nombre_proceso", nombre_proceso);
                        request.getRequestDispatcher("Inyeccion.jsp").forward(request, response);
                    }
                    break;
                    //</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="CASO 3">
                case 3:
                    lotefn = request.getParameter("txtlotefn");
                    filtro = Integer.parseInt(request.getParameter("flt"));
                    nombre_proceso = request.getParameter("nombre_pro");
                    request.setAttribute("Flt_Id_proceso", filtro);
                    request.setAttribute("Nombre_proceso", nombre_proceso);
                    request.setAttribute("lotefn", lotefn);
                    lst_lotes = refinv.Productos(lotefn);
                    if (!lst_lotes.isEmpty()) {
                        request.setAttribute("lotes", lst_lotes);
                        request.getRequestDispatcher("Inyeccion?opc=1").forward(request, response);
                    } else {
                        request.setAttribute("lotes", lst_lotes);
                        request.getRequestDispatcher("Inyeccion?opc=1").forward(request, response);
                    }
                    break;
                    //</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="CASO 4">
                case 4:
                    consecutivo = "";
                    filtro = Integer.parseInt(request.getParameter("flt"));
                    nombre_proceso = request.getParameter("nombre_pro");
                    nombreF = request.getParameter("slclotes");
                    request.setAttribute("Flt_Id_proceso", filtro);
                    request.setAttribute("Nombre_proceso", nombre_proceso);
                    codpro = request.getParameter("slclotes");
                    txtlotec = request.getParameter("txtlotec");
                    lotep1 = request.getParameter("txtlotep1");
                    lotep2 = request.getParameter("txtlotep2");
                    lote_p = lotep1 + "-" + lotep2;
                    if (txtlotec.equals("N/A")) {
                        lotec = "N/A";
                        lote_prn = lote_p;
//                        idlote_c = Integer.parseInt(request.getParameter("loteid"));
                        idlote_c = 0;
                    } else {
                        idlote_c = Integer.parseInt(request.getParameter("loteid"));
                        lotec = idlote_c + "/" + txtlotec;
                        lotecc = lotec.split("-");
                        inicio_lote = lotecc[0];
                        fin_lote = lotecc[1];
                        lote_prn = lotep1 + "-" + fin_lote;
                    }

                    molde = Integer.parseInt(request.getParameter("txtmolde").toString());
                    fecha = request.getParameter("txtfecha");

                    List validar_mpr = jpainyeccion.Validar_consecutivo_materiaprima(txtlotec);
                    if (validar_mpr != null) {
                        Object[] v_cons = (Object[]) validar_mpr.get(0);
                        consecutivo = v_cons[1].toString();
                    } else {
                        consecutivo = request.getParameter("txtconsecutivo");
                    }

                    obs = request.getParameter("obs");
                    registro = jpainyeccion.Registrar_inyeccion(consecutivo, nombreF, lotec, lote_p, lote_prn, fecha, obs, molde, 0, rol_usuario, "DISPONIBLE", "", 0, filtro, rol_usuario);
                    if (registro) {
                        request.setAttribute("Alerta", "Registro_inyeccion");
                        request.getRequestDispatcher("Inyeccion?opc=1").forward(request, response);
                    } else {
                        request.setAttribute("Alerta", "Errorregistro_inyeccion");
                        request.getRequestDispatcher("Inyeccion?opc=1").forward(request, response);
                    }
                    break;
                    //</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="CASO 5">
                case 5:
                    id_iny = Integer.parseInt(request.getParameter("id"));
                    filtro = Integer.parseInt(request.getParameter("flt"));
                    nombre_proceso = request.getParameter("nombre_pro");
                    request.setAttribute("Flt_Id_proceso", filtro);
                    request.setAttribute("Nombre_proceso", nombre_proceso);

                    lotec = request.getParameter("txtlotec");
                    lotep1 = request.getParameter("txtlotep1");
                    lotep2 = request.getParameter("txtlotep2");
                    lote_p = lotep1 + "-" + lotep2;
                    if (lotec.equals("N/A")) {
//                        lotec = "";
                        lote_prn = lote_p;
                    } else {
                        idlote_c = Integer.parseInt(request.getParameter("loteid"));
                        lotec = idlote_c + "/" + lotec;
                        lotecc = lotec.split("-");
                        inicio_lote = lotecc[0];
                        fin_lote = lotecc[1];
                        lote_prn = lotep1 + "-" + fin_lote;
                    }
//                    lst_contC=jpacons.Control_consecutivos_id(con);
//                    Object[] obj_Cont_cons = (Object[]) lst_contC.get(0);
//                    id_c =(String) obj_Cont_cons[1].toString(); 
//                    cons =(String) id_c;
                    molde = Integer.parseInt(request.getParameter("txtmolde").toString());
                    fecha = request.getParameter("txtfecha");
                    obs = request.getParameter("obs");
                    Boolean modificar = jpainyeccion.Modificar_inyeccion(idlote_c + "", lotec, lote_p, lote_prn, fecha, obs, molde, id_iny);
                    if (modificar) {
                        request.setAttribute("Alerta", "Modificar_inyeccion");
                        request.getRequestDispatcher("Inyeccion?opc=1").forward(request, response);
                    } else {
                        request.setAttribute("Alerta", "ErrorModificar_inyeccion");
                        request.getRequestDispatcher("Inyeccion?opc=1").forward(request, response);
                    }
                    break;
                    //</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="CASO 6">
                case 6:
//                    id_iny = Integer.parseInt(request.getParameter("id_iny").toString());
//                    int est = Integer.parseInt(request.getParameter("est").toString());
                    id_iny = Integer.parseInt(request.getParameter("id_iny"));
                    int est = Integer.parseInt(request.getParameter("est"));
                    filtro = Integer.parseInt(request.getParameter("flt"));
                    nombre_proceso = request.getParameter("nombre_pro");
                    request.setAttribute("Flt_Id_proceso", filtro);
                    request.setAttribute("Nombre_proceso", nombre_proceso);
                    modificar = jpainyeccion.Modificar_estado_inyeccion(est, id_iny);
                    if (modificar) {
                        request.setAttribute("Alerta", "Modificar_inyeccion");
                        request.getRequestDispatcher("Inyeccion?opc=1").forward(request, response);
                    } else {
                        request.setAttribute("Alerta", "ErrorModificar_inyeccion");
                        request.getRequestDispatcher("Inyeccion?opc=1").forward(request, response);
                    }
                    break;
                    //</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="CASO 7">
                case 7:
                    id_iny = Integer.parseInt(request.getParameter("id_iny").toString());
                    int contrmuestra = Integer.parseInt(request.getParameter("cntm").toString());
                    filtro = Integer.parseInt(request.getParameter("flt"));
                    nombre_proceso = request.getParameter("nombre_pro");
                    request.setAttribute("Flt_Id_proceso", filtro);
                    request.setAttribute("Nombre_proceso", nombre_proceso);
                    r_rec = request.getParameter("res_recibido");
                    n_rec = request.getParameter("no_recibio");
                    modificar = jpainyeccion.Modificar_estado_contramuestra(contrmuestra, id_iny, r_rec, n_rec);
                    if (modificar) {
                        request.setAttribute("Alerta", "Modificar_inyeccion_ctm");
                        request.getRequestDispatcher("Inyeccion?opc=1").forward(request, response);
                    } else {
                        request.setAttribute("Alerta", "ErrorModificar_inyeccion_ctm");
                        request.getRequestDispatcher("Inyeccion?opc=1").forward(request, response);
                    }
                    break;
                    //</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="CASO 8">
                case 8:
                    id_iny = Integer.parseInt(request.getParameter("id_iny").toString());
                    fecha = request.getParameter("txtfechapr");
                    String nombre_prestamo = request.getParameter("txtprestamo");
                    filtro = Integer.parseInt(request.getParameter("flt"));
                    nombre_proceso = request.getParameter("nombre_pro");
                    request.setAttribute("Flt_Id_proceso", filtro);
                    request.setAttribute("Nombre_proceso", nombre_proceso);
                    String datos_pr = nombre_prestamo + "/" + fecha;
                    modificar = jpainyeccion.Modificar_prestamo(nombre_prestamo, id_iny, datos_pr);
                    if (modificar) {
                        request.setAttribute("Alerta", "Modificar_inyeccion_prs");
                        request.getRequestDispatcher("Inyeccion?opc=1").forward(request, response);
                    } else {
                        request.setAttribute("Alerta", "ErrorModificar_inyeccion_prs");
                        request.getRequestDispatcher("Inyeccion?opc=1").forward(request, response);
                    }
                    break;
                    //</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="CASO 9">
                case 9:
                    id_iny = Integer.parseInt(request.getParameter("id_iny").toString());
                    filtro = Integer.parseInt(request.getParameter("flt"));
                    nombre_proceso = request.getParameter("nombre_pro");
                    fecha = request.getParameter("txtfechadv");
                    List datospr = jpainyeccion.Traer_datosprestamo(id_iny);
                    Object[] obj_iny = (Object[]) datospr.get(0);
                    datos_pr = obj_iny[0].toString();
                    request.setAttribute("Flt_Id_proceso", filtro);
                    request.setAttribute("Nombre_proceso", nombre_proceso);
                    modificar = jpainyeccion.Modificar_devolucion("DISPONIBLE", id_iny);
                    nombre_prestamo = request.getParameter("txtdevolucion");
                    String datos = datos_pr + "_" + nombre_prestamo + "/" + fecha + "";
                    Boolean modifica = jpainyeccion.Modificar_prestamo("DISPONIBLE", id_iny, datos);
                    if (modificar) {
                        request.setAttribute("Alerta", "Modificar_inyeccion_dv");
                        request.getRequestDispatcher("Inyeccion?opc=1").forward(request, response);
                    } else {
                        request.setAttribute("Alerta", "ErrorModificar_inyeccion_dv");
                        request.getRequestDispatcher("Inyeccion?opc=1").forward(request, response);
                    }
                    break;
                    //</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="CASO 10">
                case 10:
                    id_iny = Integer.parseInt(request.getParameter("id_iny").toString());
                    filtro = Integer.parseInt(request.getParameter("flt"));
                    nombre_proceso = request.getParameter("nombre_pro");
                    request.setAttribute("Flt_Id_proceso", filtro);
                    request.setAttribute("Nombre_proceso", nombre_proceso);
                    rpr = request.getParameter("txtprestamo");
                    dpr = request.getParameter("com_prestamo");
                    historial_pre = jpainyeccion.Registrar_historial_prestamo_inyeccion(id_iny, rpr, dpr);
                    if (historial_pre) {
                        request.setAttribute("Alerta", "Modificar_inyeccion_prs");
                    } else {
                        request.setAttribute("Alerta", "ErrorModificar_inyeccion_prs");
                    }
                    request.getRequestDispatcher("Inyeccion?opc=1").forward(request, response);
                    break;
                    //</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="CASO 11">
                case 11:
                    id_iny = Integer.parseInt(request.getParameter("id_iny").toString());
                    filtro = Integer.parseInt(request.getParameter("flt"));
                    nombre_proceso = request.getParameter("nombre_pro");
                    request.setAttribute("Flt_Id_proceso", filtro);
                    request.setAttribute("Nombre_proceso", nombre_proceso);
                    id_historial = Integer.parseInt(request.getParameter("id_historial").toString());
                    rdv = request.getParameter("c_devolucion");
                    fdv = request.getParameter("txtfechadv");
                    ddv = request.getParameter("com_devolucion");
                    historial_dev = jpainyeccion.Registrar_Historial_Devolucion_inyeccion(rdv, fdv, ddv, id_historial);
                    if (historial_dev) {
                        request.setAttribute("Alerta", "Modificar_inyeccion_dv");
                    } else {
                        request.setAttribute("Alerta", "ErrorModificar_inyeccion_dv");
                    }
                    request.getRequestDispatcher("Inyeccion?opc=1").forward(request, response);
                    break;
                    //</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="CASO 12">
                case 12:
                    lotefn = request.getParameter("filtro");
                    lst_lotes = refinv.Productos(lotefn);
                    /*if(lst_lotes == null || lst_lotes.get(0) == null){
                        out.print("null");
                    }*/
                    if (lst_lotes.size() == 0) {
                        out.print("null");
                    } else {
                        cadena = lst_lotes.toString();
                        cadena = cadena.replace("[", "").replace("]", "");
                        if (lst_lotes.size() != 1) {
                            String[] prueba = cadena.split(", ");
//                            cadena = obj_gson.toJson(prueba);
                        }/*else{
                            cadena = obj_gson.toJson(cadena);
                        }*/
                        out.print(cadena);
                    }
                    break;
                    //</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="CASO 13">
                case 13:
                    lotefn = request.getParameter("lote");
                    id_proceso = Integer.parseInt(request.getParameter("id_proceso"));
                    nombre_proceso = request.getParameter("nombre_proceso");
                    lst_ult_lote = jpainyeccion.Consulta_ultlote_inyeccion(lotefn, id_proceso);
                    if (lst_ult_lote != null) {
                        obj_ultlote = (Object[]) lst_ult_lote.get(0);
                        if (obj_ultlote[1] == null) {
                            ultimo_lote = " ";
                        } else {
                            ultimo_lote = obj_ultlote[1].toString();
                        }
                    } else {
                        ultimo_lote = " ";
                    }
                    out.print(ultimo_lote);
                    break;
                    //</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="CASO 14">
                case 14:
                    cadena = "";
                    int i = 0;
                    id_proceso = Integer.parseInt(request.getParameter("id_proceso"));
                    lst_procesos = jpac_procesos.Procesos_tipo("Materia_Prima");
                    for (i = 0; i < lst_procesos.size(); i++) {
                        obj_procesos = (Object[]) lst_procesos.get(i);
                        if (id_proceso == Integer.parseInt(obj_procesos[0].toString())) {
                            cadena = "<input autocomplete='off' type='text' name='txtlotec' id='txtlotec' "
                                    + "onchange='LoteC_autocompletar(this.value);validarlote_inyeccion()' required list='txtdatalist' style='margin-bottom: 0;'>";
                            lst_lotesC = jpainyeccion.Lotesciny(id_proceso);
                            if (lst_lotesC == null || lst_lotesC.size() == 0) {
                                cadena = "PAILA";
                            } else {
                                cadena += "<datalist id='txtdatalist'><label><select name='txtdatalist'>";
                                for (int x = 0; x < lst_lotesC.size(); x++) {
                                    lotesC = (Object[]) lst_lotesC.get(x);
                                    cadena += "<option value='" + lotesC[2] + " / " + lotesC[0] + "' >" + lotesC[1] + "" + lotesC[4] + "</option>";
                                }
                                cadena += "</select></label></datalist>";
                                cadena += "<script type='text/javascript'>";
                                cadena += "var validation = new LiveValidation('txtlotec');";
                                cadena += "validation.add( Validate.Presence );";
                                cadena += "function LoteC_autocompletar(lote) {";
                                cadena += "if(lote.includes(' / ') == true){";
                                cadena += "var lotes = lote.split(' / ');";
                                cadena += "var lote_select = lotes[0];";
                                cadena += "var lote_id = lotes[1];";
                                cadena += "document.getElementById(\"txtlotec\").value = lote_select;";
                                //                            cadena += "$('#txtlotec').val(txtlotec);";
                                //                            cadena += "$('#loteid').val(lote_id);";
                                cadena += "document.getElementById(\"loteid\").value = lote_id;";
                                cadena += "}}";
                                cadena += "</script>";
                                //                            cadena += "<input type='hidden' id='txt_lote_principal' value=''>";
                                //                            cadena += "<script type='text/javascript'>";
                                //                            cadena += "var validation = new LiveValidation('txtlotec');";
                                //                            cadena += "validation.add(Validate.Presence);";
                                //                            cadena += "</script>";
                            }
                        }
                    }
                    lst_procesos = jpac_procesos.Procesos_tipo("Inyeccion");
                    for (i = 0; i < lst_procesos.size(); i++) {
                        obj_procesos = (Object[]) lst_procesos.get(i);
                        if (id_proceso == Integer.parseInt(obj_procesos[0].toString())) {
//                            cadena = "<input autocomplete='off' type='text' id='txtlotec' name='txtlotec' "
//                                    + "onkeyup='fecha_inyeccion()' "
//                                    + "onchange='javascript:this.value=this.value.toUpperCase();fecha_inyeccion()' style='margin-bottom: 0;'>";
                            cadena = "<input type='text' readonly value='N/A' id='txtlotec' name='txtlotec'"
                                    + "style='margin-bottom: 0px;'>";
                        }
                    }
                    if (!cadena.equals("PAILA")) {
                        cadena += "<input type='hidden' name='txtlotec1' id='txtlotec1' class='txtlotec' onchange='LoteC_autocompletar(this.value);validarlote_inyeccion()' list='txtdatalist'/>";
                        cadena += "<input type='hidden' name='loteid' id='loteid' value='N/A'/>";
                        cadena += "<input type='hidden' name='txtconsecutivo' id='txtconsecutivo' value=''/>";
                        cadena += "<input type='hidden' id='txt_lote_principal' value=''>";
                    }
                    out.print(cadena);
                    break;
                    //</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="CASO 15">
                case 15:
                    year = Integer.parseInt(request.getParameter("year"));
                    id_proceso = Integer.parseInt(request.getParameter("id_proceso"));
                    lst_inyeccion = jpainyeccion.Traer_Inyeccion_id(id_proceso, year);
                    if (lst_inyeccion != null) {
                        obj_Inyeccion = (Object[]) lst_inyeccion.get(0);
                        cns = Integer.parseInt(obj_Inyeccion[1].toString());
                        actual = Integer.parseInt(obj_Inyeccion[16].toString());
                    } else {
                        List procesos = jpac_procesos.Consulta_idProceso(id_proceso);
                        obj_Inyeccion = (Object[]) procesos.get(0);
                        cns = 0;
                        actual = Integer.parseInt(obj_Inyeccion[4].toString());
                    }
                    if (cns < actual) {
                        if (cns == actual) {
                            cns = actual + 1;
                        } else {
                            cns = actual;
                        }
                    } else {
                        cns = cns + 1;
                    }
                    cadena = "" + ((cns > 999) ? cns : (cns > 99) ? "0" + cns : (cns > 9) ? "00" + cns : (cns <= 9) ? "000" + cns : "0000");
                    out.print(cadena);
                    break;
                    //</editor-fold>                    
                    //<editor-fold defaultstate="collapsed" desc="CASO 16">
                case 16:
                    id_proceso = Integer.parseInt(request.getParameter("id_proceso"));
                    id_iny = Integer.parseInt(request.getParameter("id_iny"));
                    lst_inyeccion = jpainyeccion.Traer_id_inyeccion(id_iny);
                    obj_Inyeccion = (Object[]) lst_inyeccion.get(0);
                    String[] arrayLoteP = obj_Inyeccion[3].toString().split("-");
                    lotep1 = arrayLoteP[0];
                    lotep2 = arrayLoteP[1];
                    lst_ult_lote = jpainyeccion.Consulta_ultlote_inyeccion(lotep1, id_proceso);
                    if (lst_ult_lote != null) {
                        obj_ultlote = (Object[]) lst_ult_lote.get(0);
                        if (obj_ultlote[1] == null) {
                            ultimo_lote = " ";
                        } else {
                            ultimo_lote = obj_ultlote[1].toString();
                        }
                    } else {
                        ultimo_lote = " ";
                    }
                    loteC = obj_Inyeccion[2].toString();
//                    String[] arrayLoteC = loteC.split("/");
//                    loteC = arrayLoteC[1];
                    molde = (Integer) obj_Inyeccion[7];
                    fecha = obj_Inyeccion[5].toString();
                    obs = (String) obj_Inyeccion[6];
                    nombreF = (String) obj_Inyeccion[17];

                    /**
                     * ***********************************
                     */
                    String pruebaString = "";
                    lst_procesos = jpac_procesos.Procesos_tipo("Materia_Prima");
                    for (i = 0; i < lst_procesos.size(); i++) {
                        obj_procesos = (Object[]) lst_procesos.get(i);
                        if (id_proceso == Integer.parseInt(obj_procesos[0].toString())) {
                            pruebaString = "LoteC anterior : " + loteC + "<br/>"
                                    + "<input autocomplete='off' type='hidden' name='txtlotec' id='txtlotec' "
                                    + "onchange='LoteC_autocompletar(this.value);"
                                    + "validarlote_inyeccion()' list='txtdatalist' "
                                    + "style='margin-bottom: 0;display:block' value='' required>";
                            lst_lotesC = jpainyeccion.Lotesciny(id_proceso);
                            pruebaString += "<datalist id='txtdatalist'><label><select name='txtdatalist'>";
//                            for(int x = 0; x < lst_lotesC.size(); x++){
//                                lotesC = (Object[]) lst_lotesC.get(i);
//                                pruebaString += "<option value='" + lotesC[2] + " / " 
//                                        + lotesC[0] + "' >" + lotesC[1] + "" + lotesC[4] + "</option>";
//                            }
                            for (int x = 0; x < lst_lotesC.size(); x++) {
                                Object[] obj_lotesC = (Object[]) lst_lotesC.get(x);
                                pruebaString += "<option value='" + obj_lotesC[2] + " / "
                                        + obj_lotesC[0] + "' >" + obj_lotesC[1] + " " + obj_lotesC[4] + "</option>";
                            }

                            pruebaString += "</select></label></datalist>";
                            pruebaString += "<script type='text/javascript'>";
                            pruebaString += "var validation = new LiveValidation('txtlotec');";
                            pruebaString += "validation.add( Validate.LoteCID );";
                            pruebaString += "validation.add( Validate.Presence );";
                            pruebaString += "function LoteC_autocompletar(lote) {";
                            pruebaString += "if(lote.includes(' / ') == true){";
                            pruebaString += "var lotes = lote.split(' / ');";
                            pruebaString += "var lote_select = lotes[0];";
                            pruebaString += "var lote_id = lotes[1];";
//                            pruebaString += "document.getElementById(\"txtlotec\").value = lote_select;";
//                            pruebaString += "document.getElementById(\"loteid\").value = lote_id;";
                            pruebaString += "$('#txtlotec').val(lote_select);";
                            pruebaString += "$('#loteid').val(lote_id);";
                            pruebaString += "}}";
                            pruebaString += "</script>";
//                            pruebaString += "<input type='hidden' id='txt_lote_principal' value=''>";
//                            pruebaString += "<script type='text/javascript'>";
//                            pruebaString += "var validation = new LiveValidation('txtlotec');";
//                            pruebaString += "validation.add(Validate.Presence);";
//                            pruebaString += "</script>";
                        }
                    }
                    lst_procesos = jpac_procesos.Procesos_tipo("Inyeccion");
                    for (i = 0; i < lst_procesos.size(); i++) {
                        obj_procesos = (Object[]) lst_procesos.get(i);
                        if (id_proceso == Integer.parseInt(obj_procesos[0].toString())) {
                            pruebaString = "<input autocomplete='off' type='text' id='txtlotec' name='txtlotec' "
                                    + "style='margin-bottom: 0;' value='" + loteC + "' readonly='true'>";
                        }
                    }
                    pruebaString += "<input type='text' name='txtlotec1' id='txtlotec1' class='txtlotec' required='' onchange='LoteC_autocompletar(this.value);validarlote_inyeccion()' list='txtdatalist'/>";
                    pruebaString += "<input type='hidden' name='loteid' id='loteid' />";
                    pruebaString += "<input type='hidden' id='txt_lote_principal' value=''>";
                    /**
                     * ***********************************
                     */
                    cadena = "" + lotep1 + "--" + lotep2 + "--" + ultimo_lote + "--" + loteC + "--" + molde + "--"
                            + fecha + "--" + obs + "--" + nombreF + "--" + pruebaString;
                    String[] output = cadena.split("--");
//                    cadena = obj_gson.toJson(output);
                    out.print(cadena);
                    break;
                    //</editor-fold>                    
            }
            //</editor-fold>
        } catch (Exception ex) {
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
