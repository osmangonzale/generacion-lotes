package Tag;

import Controladores.ControlConsecutivosJpaController;
import Controladores.InyeccionJpaController;
import Controladores.ProcesosJpaController;
import Controladores.RolJpaController;
import Factory.ReferenciasInv;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Inyeccion extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            //<editor-fold defaultstate="collapsed" desc="JPAS">
            ControlConsecutivosJpaController ctrlcons = new ControlConsecutivosJpaController();
            ProcesosJpaController jpapro = new ProcesosJpaController();
            InyeccionJpaController jpainyeccion = new InyeccionJpaController();
            ReferenciasInv refinv = new ReferenciasInv();
            List lst_factory = null, lst_lotesC = null, lis_procesos;
            RolJpaController jpacrol = new RolJpaController();
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="VARIABLES">
            int cont, id_registro, id_iny_m, temp, id_order = 0;
            int id_historial_iny = 0;
            int id_iny_historial = 0;
            int estado = 0;
            int filtro = 0;
            int id_iny = 0;
            String rol_usugrt;
            String clase = "";
            String nombre_proceso;
            String lote = "";
            String ultimolote;
            int cns = 0;
            int actual = 0;
            List lst_inyec = null;
            String nombre_p = "", formula = "";
            String fecha_p = "";
            String hora_p = "";
            String nombre_d = "";
            String fecha_d = "";
            String hora_d = "";
            int rangeMax, rangeMin, year;
            List lista_rango;
            List lst_procesos = jpapro.Procesos_tipo("Inyeccion");
            List lst_procesos_materia_prima = jpapro.Procesos_tipo("Materia_Prima");
            Object obj_rango;
            Calendar c = Calendar.getInstance();
            Date fecha = new Date();
            List lst_rol_id = null;
            int id_rol, id_rol_permission, idRol = 0;
            String txtPermisos = "";
            double hora_minuto = Double.parseDouble(fecha.getHours() + "." + fecha.getMinutes());
            String anio = (fecha.getYear() + 1900) + "";
            String mes = fecha.getMonth() + 1 + "";
            String dia = (fecha.getDate() < 10 ? "0" : "") + "" + fecha.getDate() + "";
            String fecha_actual = anio + "-" + mes + "-" + dia;
            String nombre_usuario = pageContext.getSession().getAttribute("Nombres").toString();
            String rol = pageContext.getSession().getAttribute("Nombre_rol").toString();
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="VARIABLES DE CONTEXTO">
            try {
                filtro = Integer.parseInt(pageContext.getRequest().getAttribute("Flt_Id_proceso").toString());
            } catch (Exception e) {
                filtro = 0;
            }
            try {
                year = Integer.parseInt(pageContext.getRequest().getAttribute("year").toString());
            } catch (Exception e) {
                year = c.get(Calendar.YEAR);
            }
            try {
                nombre_proceso = pageContext.getRequest().getAttribute("Nombre_proceso").toString();
            } catch (Exception e) {
                nombre_proceso = "";
            }
            try {
                lote = pageContext.getRequest().getAttribute("lotefn").toString();
            } catch (Exception e) {
                lote = "";
            }
            try {
                id_iny = Integer.parseInt(pageContext.getRequest().getAttribute("id_iny").toString());
            } catch (Exception e) {
                id_iny = 0;
            }
            try {
                id_iny_m = Integer.parseInt(pageContext.getRequest().getAttribute("id_iny_m").toString());
            } catch (Exception e) {
                id_iny_m = 0;
            }
            try {
                id_iny_historial = Integer.parseInt(pageContext.getRequest().getAttribute("iih").toString());
            } catch (Exception e) {
                id_iny_historial = 0;
            }
            try {
                id_registro = Integer.parseInt(pageContext.getRequest().getAttribute("id_registro").toString());
            } catch (Exception e) {
                id_registro = 0;
            }
            try {
                formula = pageContext.getRequest().getAttribute("Formula").toString();
            } catch (Exception e) {
                formula = "";
            }
            try {
                temp = Integer.parseInt(pageContext.getRequest().getAttribute("temp").toString());
            } catch (Exception e) {
                temp = 0;
            }
            try {
                id_order = Integer.parseInt(pageContext.getRequest().getAttribute("id_order").toString());
            } catch (Exception e) {
                id_order = 0;
            }
            try {
                idRol = Integer.parseInt(pageContext.getRequest().getAttribute("Id_rol").toString());
                lst_rol_id = jpacrol.Consult_role_id(idRol);
                Object[] obj_permi = (Object[]) lst_rol_id.get(0);
                txtPermisos = obj_permi[2].toString();
            } catch (Exception e) {
                id_rol = 0;
                txtPermisos = "";
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="TABLA">
            out.print("<section class='section'>");
            out.print("<div class='section-header'>");
            out.print("<h1>Modulo Iny/Ext</h1>");
            out.print("</div>");
            out.print("<div class='section-body'>");
            out.print("<div class='row'>");
            out.print("<div class='col-12'>");
            out.print("<div class='card'>");
            out.print("<div class='card-header' style='justify-content: space-between;'>");
            out.print("<h4>Tabla Iny/Ext</h4>");
            out.print("</div>");
            out.print("<div class='card-body'>");
            out.print("<div class='table-responsive'>");
            //<editor-fold defaultstate="collapsed" desc="FILTRO PROCESO">
            if (filtro == 0) {
                lst_inyec = jpainyeccion.Traer_inyecciones(year);
            } else {
                lst_inyec = jpainyeccion.Traer_Inyeccion_id(filtro, year);
            }

            out.print("<div style='display: flex; align-items: center; justify-content: space-between;'>");

            out.print("<div style='display: flex; align-items: center;'>");

            out.print("<div style='margin-right: 20px;'>");
            out.print("<form action='In?opc=1' method='post' name='filtro'>");
            out.print("<select class='form-control select2' name='slc_proceso' id='slc_proceso' title='Procesos' onchange='document.filtro.submit();' style='width: 180px'>");
            out.print("<option value='empty'>Seleccionar Proceso</option>");

            out.print("<option disabled='disabled'>SIN PELETIZAR</option>");
            for (int i = 0; i < lst_procesos.size(); i++) {
                Object[] obj_pro = (Object[]) lst_procesos.get(i);
                if (Integer.parseInt(obj_pro[3].toString()) == 0) {
                    if (filtro == Integer.parseInt(obj_pro[0].toString())) {
                        out.print("<option selected value='" + obj_pro[0] + "/" + obj_pro[1] + "'>" + obj_pro[1] + "</option>");
                    } else {
                        out.print("<option value='" + obj_pro[0] + "/" + obj_pro[1] + "'>" + obj_pro[1] + "</option>");
                    }
                }
            }

            out.print("<option disabled='disabled'>PELETIZADOS</option>");
            for (int i = 0; i < lst_procesos_materia_prima.size(); i++) {
                Object[] obj_pro_materia_prima = (Object[]) lst_procesos_materia_prima.get(i);
                if (Integer.parseInt(obj_pro_materia_prima[3].toString()) == 0) {
                    if (filtro == Integer.parseInt(obj_pro_materia_prima[0].toString())) {
                        out.print("<option selected value='" + obj_pro_materia_prima[0] + "/" + obj_pro_materia_prima[1] + "'>" + obj_pro_materia_prima[1] + "</option>");
                    } else {
                        out.print("<option value='" + obj_pro_materia_prima[0] + "/" + obj_pro_materia_prima[1] + "'>" + obj_pro_materia_prima[1] + "</option>");
                    }
                }
            }

            out.print("</select>");
            out.print("</form>");
            out.print("</div>");

            if (!rol.equals("Consulta")) {
                if (filtro != 0) {
                    out.print("<div style='margin-right: 20px;'>");
                    if (txtPermisos.contains("[28]")) {
                        out.print("<a class='btn btn-green' style='border-radius: 4px;' href='In?opc=1&id_registro=" + filtro + "&Flt_Id_proceso=" + filtro + "&Nombre_proceso=" + nombre_proceso + "&year=" + year + "' data-toggle='tooltip' data-placement='top' title='Registrar Inyeccion'><i class='fas fa-plus'></i></a>");
                    } else {
                        out.print("<button class='btn btn-green' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-plus'></i></button>");
                    }
                    out.print("</div>");
                }
            }
            out.print("</div>");
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="FINALIZAR MASIVO">
            out.print("<form action='in?opc=7' method='post' id='form_masivo'>");
            out.print("<input type='hidden' id='id_iny' name='id_iny'>");
            out.print("<input type='hidden' id='cc_iny' name='cc_iny'>");
            out.print("<input type='hidden' value='1' name='est'>");
            if (txtPermisos.contains("[35]")) {
                out.print("<button type='button' onclick='enviar_masivo()' id='inactivarBtn' class='btn btn-warning' data-toggle='tooltip' data-placement='top' title='Finalizar Masivo'><i class='fas fa-user-lock'></i></button>");
            } else {
                out.print("<button class='btn btn-green' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-user-lock'></i></button>");
            }
            out.print("</form>");

            out.print("<style>");
            out.print(".selected-row {");
            out.print("background-color: #c2f6d173;");
            out.print("}");
            out.print("</style>");
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="FILTRO AÑO">
            lista_rango = ctrlcons.Rango_Años();
            out.print("<div style='margin-left: auto;'>");
            out.print("<form action='in?opc=6' method='post' id='FormAnio' onsubmit='updateYear()'>");
            out.print("<input type='text' id='nproceso' name='slc_proceso' value='" + filtro + "' hidden>");
            out.print("<input type='text' id='anioF' name='year' value='' hidden>");
            out.print("<select id='select_range' class='form-control select2' onchange='Changeyear()'>");
            out.print("<option value='empty'>Seleccionar Año</option>");
            for (int x = 0; x < lista_rango.size(); x++) {
                obj_rango = (Object) lista_rango.get(x);
                out.print("<option value='" + obj_rango.toString() + "'>" + obj_rango.toString() + "</option>");
            }
            out.print("</select>");
            out.print("</form>");
            out.print("</div>");

            out.print("</div>");
            out.print("<br>");
//</editor-fold>
            out.print("<table class='table table-sm table-bordered border-primary' style='width: 100%'>");
            out.print("<thead>");
            out.print("<tr>");
            out.print("<th colspan='3' class='header-cell' style='text-align: center; background-color: #c1c1c1'>COPIA NO CONTROLADA</th>");
            out.print("</tr>");
            out.print("</thead>");
            out.print("<tbody class='body-table'>");
            out.print("<tr>");
            out.print("<td rowspan='2'class='logo-cell' style='width: 25%; text-align: center'>");
            out.print("<img src=\"Interfaz/Contenido/images/Logo.png\" alt=\"Logo\" class='logo-img' style='width: 200px'>");
            out.print("</td>");
            out.print("<td class='text-center title-cell' style='width: 50%; font-weight: bold;'> REGISTRO CONTROL DE CONSECUTIVOS PRODUCTOS</td>");
            out.print("<td class='text-center code-version-cell' style='width: 25%; text-align: center; font-weight: bold;'>CÓDIGO R-GC-171</td>");
            out.print("</tr>");
            out.print("<tr>");
            out.print("<td style='width: 50%; text-align: center; font-weight: bold;'>TODOS</td>");
            out.print("<td style='width: 25%; text-align: center; font-weight: bold;'>VERSIÓN 000</td>");
            out.print("</tr>");
            out.print("</tbody>");
            out.print("</table>");

            out.print("<table class='table table-bordered table-hover' id='table-1'>");
            out.print("<thead>");
            out.print("<tr>");
            out.print("<th>ID</th>");
            out.print("<th>Consecutivo</th>");
            out.print("<th>Lote C</th>");
            out.print("<th>Lote P</th>");
            out.print("<th>Lote Principal</th>");
            out.print("<th>Molde/Linea</th>");
            out.print("<th>Contramuestras</th>");
            out.print("<th>Prestamo</th>");
            out.print("<th>Observaciones</th>");
            out.print("<th>Responsable</th>");
            if (!rol.equals("Calidad") && !rol.equals("Consulta")) {
                out.print("<th>Estado</th>");
            }
            if (!rol.equals("Consulta")) {
                if (filtro != 0) {
                    out.print("<th>Modificar</th>");
                }
            }
            if (txtPermisos.contains("[89]")) {
                out.print("<th>Eliminar</th>");
            }
            out.print("</tr>");
            out.print("</thead>");
            out.print("<tbody>");
            if (temp == 4) {
                lst_inyec = jpainyeccion.Traer_registro_id(id_order);
            }
            if (lst_inyec != null) {
                //<editor-fold defaultstate="collapsed" desc="MODAL NO RECIBIO">
                out.print("<div class='sweet-local' tabindex='-1' id='Ventana4' style='opacity: 1.03; display:none;'>");
                out.print("<div class='cont_reg2'>");
                out.print("<div style='display: flex; justify-content: space-between'>");
                out.print("<h2>Justificacion de no recibido</h2>");
                out.print("<button class='btn btn-outline-secondary' onclick='mostrarmodalIdiny(4)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
                out.print("</div>");
                out.print("<div class='cont_form_user'>");
                out.print("<form action='in?opc=4&cntm=2&Flt_Id_proceso=" + filtro + "&Nombre_proceso=" + nombre_proceso + "&res_recibido=" + nombre_usuario + "' method='post' name='prueba' class='needs-validation' novalidate=''>");

                out.print("<input type='hidden' name='id_iny' id='idIny' value='1'>");

                out.print("<div class='col-12' style='margin-top:2.5%; margin-left:2.7%; width:94%;'>");
                out.print("<textarea type='text' class='form-control' name='no_recibio' placeholder='Observaciones:' required data-toggle='tooltip' data-placemente='top' title='Observacion'></textarea>");
                out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                out.print("</div>");
                out.print("</div>");
                out.print("<br>");

                out.print("<div class='' style='width: 100%; text-align:center;'>");
                out.print("<button class='btn btn-green btn-lg'>Guardar</button>");
                out.print("</div>");

                out.print("</form>");
                out.print("</div>");
                out.print("</div>");
                out.print("</div>");
//</editor-fold>
                for (int i = 0; i < lst_inyec.size(); i++) {
                    Object[] obj_iny = (Object[]) lst_inyec.get(i);
                    //<editor-fold defaultstate="collapsed" desc="Estilos Clase">
                    int contramuestra = Integer.parseInt(obj_iny[8].toString());
                    if (rol.equals("Calidad")) {
                        if (contramuestra == 0) {
                            clase = "estadoentrega2";
                        } else {
                            clase = "";
                        }
                    }
                    out.print("<tr class='" + clase + "'>");
//</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="ID">
                    if (obj_iny[12] != null && obj_iny[12] instanceof Integer) {
                        int estadoRegistro = (Integer) obj_iny[12];

                        out.print("<td align='center'>");

                        if (estadoRegistro == 0) {
                            out.print("<input type='checkbox' id='cbx_" + obj_iny[0] + "' onclick='Masivo(" + obj_iny[0] + ", \"" + obj_iny[1] + "\")'>");
                        } else if (estadoRegistro == 1) {
                            out.print("<input type='checkbox' id='cbx_" + obj_iny[0] + "' onclick='Masivo(" + obj_iny[0] + ", \"" + obj_iny[1] + "\")' disabled>");
                        }

                        out.print("<input type='hidden' value='" + obj_iny[0] + "'>");
                        out.print("</td>");
                    } else {
                        out.print("<td align='center'>Error en el estado del registro</td>");
                    }
//</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="CONSECUTIVO">
                    out.print("<td align='center'><b>" + obj_iny[5] + "</b></br>" + obj_iny[19] + obj_iny[1] + "</td>");
//</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="LOTE C">
                    if (!obj_iny[2].equals("N/A")) {
                        String[] vlotec = obj_iny[2].toString().split("/");
                        int longitudd = vlotec.length;
                        int a = 2;
                        if (a <= longitudd) {
                            String lote_c = vlotec[1];
                            out.print("<td align='left'>" + lote_c + "</td>");
                        } else {
                            out.print("<td align='left'>" + obj_iny[2] + "</td>");
                        }
                    } else {
                        out.print("<td align='left'>" + obj_iny[2] + "</td>");
                    }
//</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="LOTE P">
                    out.print("<td align='left'>" + obj_iny[3] + "</td>");
//</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="LOTE PRINCIPAL">
                    out.print("<td align='center' title='" + obj_iny[17] + "'><b>" + obj_iny[4] + "<b></td>");
//</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="MOLDE">
                    out.print("<td align='center'>" + obj_iny[7] + "</td>");
//</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="CONTRAMUESTRAS">
                    if (!rol.equals("Calidad") && !rol.equals("Consulta")) {

                        if (Integer.parseInt(obj_iny[8].toString()) == 1) {
                            if (obj_iny[11].toString().contains("/")) {
                                out.print("<td align='center'><small style='color:#00A6ED' title='" + obj_iny[11].toString().split(" / ")[1] + "'>RECIBIDO POR:</small></br>" + obj_iny[11].toString().split(" / ")[0] + "</td>");
                            } else {
                                out.print("<td align='center'><small style='color:#00A6ED' title='" + obj_iny[11] + "'>RECIBIDO POR:</small></br>" + obj_iny[11] + "</td>");
                            }
                        } else if (Integer.parseInt(obj_iny[8].toString()) == 2) {
                            out.print("<td align='center'><small style='color:#F6511D' title='" + obj_iny[20].toString().split(" / ")[0] + "'>NO RECIBE:</small></br>" + obj_iny[11].toString().split(" / ")[0] + "</td>");
                        } else {
                            if (txtPermisos.contains("[32]") && txtPermisos.contains("[31]")) {
                                out.print("<td align='center'><b>APLICA</b></BR><a text-decoration:none;' href='in?opc=4&id_iny=" + obj_iny[0] + "&cntm=1&Flt_Id_proceso=" + filtro + "&Nombre_proceso=" + nombre_proceso + "&res_recibido=" + nombre_usuario + "'>"
                                        + "<small style='font-size:11px; font-weight:bold' class='btn btn-info btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Recibio contramuestra'>SI</small></a>");
                                out.print("&nbsp; &nbsp;&nbsp;<a onclick='mostrarmodalIdiny(4," + obj_iny[0] + ")'>"
                                        + "<small style='font-size:11px; font-weight:bold; cursor:pointer' class='btn btn-danger btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Recibio contramuestra'>NO</small></a></td>");
                            } else {
                                out.print("<td align='center'><b>APLICA</b></BR><a text-decoration:none;'>"
                                        + "<small style='font-size:11px; font-weight:bold' class='btn btn-secondary btn-sm' data-toggle='tooltip' data-placement='top' title='No tiene permisos'>SI</small></a>");
                                out.print("&nbsp; &nbsp;&nbsp;<a>"
                                        + "<small style='font-size:11px; font-weight:bold' class='btn btn-secondary btn-sm' data-toggle='tooltip' data-placement='top' title='No tiene permisos'>NO</small></a></td>");
                            }
                        }
                    } else if (Integer.parseInt(obj_iny[8].toString()) == 1) {
                        if (obj_iny[11].toString().contains("/")) {
                            out.print("<td align='center'><small style='color:#00A6ED' title='" + obj_iny[11].toString().split(" / ")[1] + "'>RECIBIDO POR:</small></br>" + obj_iny[11].toString().split(" / ")[0] + "</td>");
                        } else {
                            out.print("<td align='center'><small style='color:#00A6ED' title='" + obj_iny[11] + "'>RECIBIDO POR:</small></br>" + obj_iny[11] + "</td>");
                        }
                    } else if (obj_iny[19] == "") {
                        out.print("<td align='center'><b style='color:#F6511D'>PENDIENTE</b></td>");
                    } else if (obj_iny[20].toString().contains(" / ")) {
                        out.print("<div style='width: 400px; display:none' id='modal-two" + obj_iny[0] + "' class=\"iziModal\" data-izimodal-subtitle=\"Contramuestras\">");
                        out.print("<h3 align='center'>Observaciones</h3>");
                        out.print("<article  style='margin:-10px;width:382px'>");
                        out.print("<p style='position:relative;left:21px'><b>Fecha:</b> " + obj_iny[20].toString().split(" / ")[1].replace(" ", "&nbsp;&nbsp;<b>Hora:</b>") + "<br>");
                        out.print("<b>Responsable: </b>" + obj_iny[11].toString().split(" / ")[0] + "<br>");
                        out.print("<b>Observaciones:</b>" + obj_iny[20].toString().split(" / ")[0] + "</p>");
                        out.print("</article>");
                        out.print("</div>");
                        out.print("<td align='center'><a href='' data-iziModal-open='#modal-two" + obj_iny[0] + "' style='text-decoration: none;color:red'>SIN CONTRAMUESTRAS</a></td>");
                    } else {
                        out.print("<div style='width: 400px; display:none' id='modal-two" + obj_iny[0] + "' class=\"iziModal\" data-izimodal-subtitle=\"Contramuestras\">");
                        out.print("<h3 align='center'>Observaciones</h3>");
                        out.print("<article  style='margin:-10px;width:382px'>");
                        out.print("<p style='position:relative;left:21px'>");
                        out.print("<b>Responsable: </b>" + obj_iny[11].toString().split(" / ")[0] + "<br>");
                        out.print("<b>Observaciones:</b>" + obj_iny[20] + "</p>");
                        out.print("</article>");
                        out.print("</div>");
                        out.print("<td align='center'><b class='naranja'>PENDIENTE</b></td>");
                    }
                    //</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="PRESTAMO">
                    if (!rol.equals("Calidad") && !rol.equals("Consulta")) {
                        if (Integer.parseInt(obj_iny[8].toString()) == 1) {
                            if (obj_iny[10].toString().equals("DISPONIBLE")) {
                                List his_estado = jpainyeccion.Traer_Historial_Prestamos_inyeccion(Integer.parseInt(obj_iny[0].toString()));
                                if (his_estado != null) {
                                    Object[] obj_estado = (Object[]) his_estado.get(0);
                                    if (txtPermisos.contains("[33]")) {
                                        if (Integer.parseInt(obj_estado[9].toString()) == 0) {
                                            out.print("<td align='center'><a style='font-size:11px;' class='btn btn-success btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='' href='in?opc=1&iih=" + obj_iny[0] + "&cntm=1&Flt_Id_proceso=" + filtro + "&Nombre_proceso=" + nombre_proceso + "' >DISPONIBLE</a>");
                                        } else {
                                            if (txtPermisos.contains("[34]")) {
                                                out.print("<td align='center'><a style='font-size:11px;' class='btn btn-warning btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Devolver' href='in?opc=1&iih=" + obj_iny[0] + "&cntm=1&Flt_Id_proceso=" + filtro + "&Nombre_proceso=" + nombre_proceso + "' >PRESTADO</a>");
                                            } else {
                                                out.print("<td align='center'><a style='font-size:11px;' class='btn btn-secondary btn-sm' data-toggle='tooltip' data-placement='top' title='No tiene permisos'>PRESTADO</a>");
                                            }
                                        }
                                    } else {
                                        out.print("<td align='center'><a style='font-size:11px;' class='btn btn-secondary btn-sm' data-toggle='tooltip' data-placement='top' title='No tiene permisos'>PRESTADO</a>");
                                    }
                                } else {
                                    if (txtPermisos.contains("[33]")) {
                                        out.print("<td align='center'><a style='font-size:11px;' class='btn btn-success btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Prestar' href='in?opc=1&iih=" + obj_iny[0] + "&cntm=1&Flt_Id_proceso=" + filtro + "&Nombre_proceso=" + nombre_proceso + "' >DISPONIBLE</a>");
                                    } else {
                                        out.print("<td align='center'><a style='font-size:11px;' class='btn btn-secondary btn-sm' data-toggle='tooltip' data-placement='top' title='No tiene permisos'>DISPONIBLE</a>");
                                    }
                                }
                            } else {
                                out.print("<td align='center'> ossss" + obj_iny[10] + "");
                            }
                        } else {
                            out.print("<td align='center'><button style='cursor: not-allowed;' class='btn btn-secondary btn-sm btn-icon' data-toggle='tooltip' data-placement='top'>" + obj_iny[10] + "</button>");
                        }
                    } else if (Integer.parseInt(obj_iny[8].toString()) == 0) {
                        out.print("<td align='center'>---</td>");
                    } else if ((Integer.parseInt(obj_iny[8].toString()) == 2)) {
                        out.print("<td align='center'>---</td>");
                    } else {
                        out.print("<td align='center' ><a href='in?opc=1&iih=" + obj_iny[0] + "&cntm=1&Flt_Id_proceso=" + filtro + "&Nombre_proceso=" + nombre_proceso + "' class='btn btn-success btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Historial'><i class=\"fas fa-eye\"></i></a>");
                    }
//</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="OBSERVACIONES">
                    out.print("<td align='left'>" + obj_iny[6] + "</td>");
//</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="RESPONSABLE">
                    String[] responsable = obj_iny[9].toString().split("/");
                    rol_usugrt = responsable[0];
                    String nombre = responsable[1];
                    out.print("<td align='left'>" + nombre + "</td>");
//</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="ESTADO">
                    if (!rol.equals("Calidad") && !rol.equals("Consulta")) {
                        if (txtPermisos.contains("[30]")) {
                            if (Integer.parseInt(obj_iny[12].toString()) == 0) {
                                out.print("<td align='center'>");
                                out.print("<a href='#' onclick='FinalizarInyeccion(" + obj_iny[0] + "," + filtro + ",\"" + nombre_proceso + "\"," + obj_iny[12] + ")' class='btn btn-success btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Finalizar Lote'><i class='fas fa-check'></i></a>");
                                out.print("</td>");
                            } else {
                                out.print("<td align='center'>");
                                out.print("<a href='#' class='btn btn-secondary btn-sm btn-icon' style='cursor: not-allowed;' data-toggle='tooltip' data-placement='top' title='Registro Finalizado'><i class='fas fa-lock'></i></a>");
                                out.print("</td>");
                            }
                        } else {
                            out.print("<td align='center'>");
                            out.print("<button class='btn btn-green btn-sm' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-lock'></i></button>");
                            out.print("</td>");
                        }
                    }
                    //<editor-fold defaultstate="collapsed" desc="MODIFICAR">
                    if (!rol.equals("Consulta")) {
                        if (filtro != 0) {
                            if (txtPermisos.contains("[29]")) {
                                if (Integer.parseInt(obj_iny[12].toString()) == 0) {
                                    out.print("<td align='center'>");
                                    out.print("<a href='in?opc=1&id_iny_m=" + obj_iny[0] + "&Flt_Id_proceso=" + filtro + "&Nombre_proceso=" + nombre_proceso + "&year=" + year + "'><i class='btn btn-primary btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Modificar Inyeccion'><i class='fas fa-pencil-alt'></i></a>");
                                    out.print("</td>");
                                } else {
                                    out.print("<td align='center'>");
                                    out.print("<a href='#' class='btn btn-secondary btn-sm btn-icon' style='cursor: not-allowed;' data-toggle='tooltip' data-placement='top' title='Registro Finalizado'><i class='fas fa-pencil-alt'></i></a>");
                                    out.print("</td>");
                                }
                            } else {
                                out.print("<td align='center'>");
                                out.print("<button class='btn btn-green btn-sm' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-pencil-alt'></i></button>");
                                out.print("</td>");
                            }
                        }
                    }
//</editor-fold>
//</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="ELIMINAR">
                    if (txtPermisos.contains("[89]")) {
                        out.print("<td align='center'>");
                        out.print("<a onclick='EliminarRegistro(" + obj_iny[0] + ")' class='btn btn-danger btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Eliminar registro'><i class='far fa-trash-alt fa-size_small text-white'></i></a>");
                        out.print("</td>");
                    }
                }
//</editor-fold>
            } else {
                out.print("<tr>");
                out.print("<td colspan='100%' align='center'>");
                out.print("<h3><b style='color:#292929'>No se han encontrado resultados</b></h3");
                out.print("</td>");
                out.print("</tr>");
            }
            out.print("</tbody>");
            out.print("</table>");
            out.print("</div>");
            out.print("</section>");
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="HISTORIAL DE PRESTAMOS Y DEVOLUCIONES">
            if (!rol.equals("Calidad") && !rol.equals("Consulta")) {
                if (id_iny_historial > 0) {
                    out.print("<script type='text/javascript'>"
                            + "function solicita_prestamo" + id_iny_historial + "(){"
                            + "document.getElementById('prestamoo" + id_iny_historial + "').style.display ='block';}"
                            + "</script>");

                    out.print("<script type='text/javascript'>"
                            + "function oculta_prestamo" + id_iny_historial + "(){"
                            + "document.getElementById('prestamoo" + id_iny_historial + "').style.display ='none';}"
                            + "</script>");

                    out.print("<div class='sweet-local' tabindex='-1' id='Ventana1' style='opacity: 1.03; display:block;'>");
                    out.print("<div class='cont_reg'>");
                    out.print("<div style='display: flex; justify-content: space-between'>");
                    out.print("<h2>Control Inyección Historial</h2>");
                    out.print("<a href='In?opc=1&Flt_Id_proceso=" + filtro + "&Nombre_proceso=" + nombre_proceso + "'>");
                    out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(3)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button></a>");
                    out.print("</div>");
                    out.print("<div class='cont_form_user' style='display: flex;'>");

                    out.print("<div class='col-lg-4' style='border-right: 1px solid #00281b63;'>");
                    List lst_historial_inyeccion = jpainyeccion.Traer_Historial_Prestamos_inyeccion(id_iny_historial);

                    if (lst_historial_inyeccion == null) {
                        cont = 0;
                    } else {
                        Object[] obj_historial_inyeccion = (Object[]) lst_historial_inyeccion.get(0);
                        id_historial_iny = Integer.parseInt(obj_historial_inyeccion[0].toString());
                        if (Integer.parseInt(obj_historial_inyeccion[9].toString()) > 0) {
                            cont = 1;
                        } else {
                            cont = 0;
                        }
                    }
                    if (cont > 0) {
                        out.print("<form action='in?opc=5&Flt_Id_proceso=" + filtro + "&Nombre_proceso=" + nombre_proceso + "' method='post' class='needs-validation' novalidate=''>");
                        out.print("<h6 align='center'>Devolucion</h6>");
                        out.print("<input type='hidden' name='id_iny' value='" + id_iny_historial + "'>");
                        out.print("<input type='hidden' value='" + fecha_actual + "/" + hora_minuto + "'  name='txtfechadv' id='txtfechadv'>");
                        out.print("<input type='hidden' name='id_historial' value='" + id_historial_iny + "'>");

                        out.print("<div class='col-12'>");
                        out.print("<input type='text' class='form-control' name='c_devolucion' id='txtprestamo' placeholder='Nombre del solicitante' required='' data-toggle='tooltip' data-placement='top' title='Nombre'>");
                        out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                        out.print("</div>");

                        out.print("<div class='col-12' style='margin-top:8%; margin-left:4%; width:101%;'>");
                        out.print("<textarea type='text' class='form-control' name='com_devolucion' id='com_devolucion' placeholder='Observacion' required data-toggle='tooltip' data-placemente='top' title='Observacion'></textarea>");
                        out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                        out.print("</div>");
                        out.print("<br>");

                        out.print("<div class='' style='width: 100%; text-align:center;'>");
                        out.print("<button class='btn btn-green btn-lg'>Registrar</button>");
                        out.print("</div>");

                        out.print("</form>");
                    } else {
                        out.print("<form action='in?opc=5&Flt_Id_proceso=" + filtro + "&Nombre_proceso=" + nombre_proceso + "' method='post' class='needs-validation' novalidate='' style='width:300px'>");
                        out.print("<h6 align='center'>Prestamo</h6>");
                        out.print("<input type='hidden' name='id_iny' value='" + id_iny_historial + "'>");

                        out.print("<div class='col-12'>");
                        out.print("<input type='text' class='form-control' name='txtprestamo' id='txtprestamo' placeholder='Nombre del Responsable' required='' data-toggle='tooltip' data-placement='top' title='Nombre'>");
                        out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                        out.print("</div>");

                        out.print("<div class='col-12' style='margin-top:8%; margin-left:4%; width:101%;'>");
                        out.print("<textarea type='text' class='form-control' name='com_prestamo' id='com_prestamo' placeholder='Observacion' required data-toggle='tooltip' data-placemente='top' title='Observacion'></textarea>");
                        out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                        out.print("</div>");
                        out.print("<br>");

                        out.print("<div class='' style='width: 100%; text-align:center;'>");
                        out.print("<button class='btn btn-green btn-lg'>Registrar</button>");
                        out.print("</div>");

                        out.print("</form>");
                    }
                    out.print("</div>");

                    out.print("<div class='col-lg-8'>");
                    out.print("<h6 align='center'>Historial de prestamos y devoluciones de inyeccion</h6>");
                    out.print("<section class='section'>");
                    out.print("<div class='section-body'>");
                    out.print("<div class='row'>");
                    out.print("<div class='col-12'>");
                    out.print("<div class='card'>");
                    out.print("<div class='card-body'>");
                    out.print("<div class='table-responsive'>");
                    out.print("<table class='table table-bordered' id='table-2'>");
                    out.print("<thead>");
                    out.print("<tr>");
                    out.print("<th>PRÉSTAMOS</th>");
                    out.print("<th>DEVOLUCIONES</th>");
                    out.print("</tr>");
                    out.print("</thead>");
                    out.print("<tbody>");
                    if (lst_historial_inyeccion != null && !lst_historial_inyeccion.isEmpty()) {
                        for (int j = 0; j < lst_historial_inyeccion.size(); j++) {
                            Object[] obj_historial_inyeccion = (Object[]) lst_historial_inyeccion.get(j);
                            out.print("<tr>");
                            out.print("<td align='center' style='font-size: 11px;'><b>FECHA: </b>" + obj_historial_inyeccion[4].toString().replace(" ", "&nbsp;&nbsp;<b>HORA: </b>") + "<div style='margin-top: 5px; margin-bottom: 5px; border-top: 1px solid black;'></div><b>RESPONSABLE: </b>" + obj_historial_inyeccion[3] + "<div style='margin-top: 5px; margin-bottom: 5px; border-top: 1px solid black;'></div><b>OBSERVACIONES: </b>" + obj_historial_inyeccion[5] + "</td>");
                            if (obj_historial_inyeccion[6] != null) {
                                out.print("<td align='center' style='font-size: 11px;'><b>FECHA: </b>" + obj_historial_inyeccion[7].toString().replace(" ", "&nbsp;&nbsp;<b>HORA: </b>") + "<div style='margin-top: 5px; margin-bottom: 5px; border-top: 1px solid black;'></div><b>RESPONSABLE: </b>" + obj_historial_inyeccion[6] + "<div style='margin-top: 5px; margin-bottom: 5px; border-top: 1px solid black;'></div><b>OBSERVACIONES: </b>" + obj_historial_inyeccion[8] + "</td>");
                            } else {
                                out.print("<td align='center'><b style='color: orange; class='naranja'>PRÉSTADO</b></td>");
                            }
                            out.print("</tr>");
                        }
                    } else {
                        out.print("<tr><td style='text-align:center;' colspan='2'>No existen datos</td></tr>");
                    }
                    out.print("</tbody>");
                    out.print("</table>");
                    out.print("</div>");
                }
            } else {
                if (id_iny_historial > 0) {
                    out.print("<script type='text/javascript'>"
                            + "function solicita_prestamo" + id_iny_historial + "(){"
                            + "document.getElementById('prestamoo" + id_iny_historial + "').style.display ='block';}"
                            + "</script>");

                    out.print("<script type='text/javascript'>"
                            + "function oculta_prestamo" + id_iny_historial + "(){"
                            + "document.getElementById('prestamoo" + id_iny_historial + "').style.display ='none';}"
                            + "</script>");

                    out.print("<div class='sweet-local' tabindex='-1' id='Ventana1' style='opacity: 1.03; display:block;'>");
                    out.print("<div class='cont_reg'>");
                    out.print("<div style='display: flex; justify-content: space-between'>");
                    out.print("<h2>Control Inyección Historial</h2>");
                    out.print("<a href='In?opc=1&Flt_Id_proceso=" + filtro + "&Nombre_proceso=" + nombre_proceso + "'>");
                    out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(3)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button></a>");
                    out.print("</div>");
                    out.print("<div class='cont_form_user' style='display: flex;'>");
                    out.print("</div>");

                    out.print("<h6 align='center'>Historial de prestamos y devoluciones de inyeccion</h6>");
                    out.print("<section class='section'>");
                    out.print("<div class='section-body'>");
                    out.print("<div class='row'>");
                    out.print("<div class='col-12'>");
                    out.print("<div class='card'>");
                    out.print("<div class='card-body'>");
                    out.print("<div class='table-responsive'>");
                    out.print("<table class='table table-bordered' id='table-2'>");
                    out.print("<thead>");
                    out.print("<tr>");
                    out.print("<th>PRÉSTAMOS</th>");
                    out.print("<th>DEVOLUCIONES</th>");
                    out.print("</tr>");
                    out.print("</thead>");
                    out.print("<tbody>");
                    List lst_historial_inyeccion = jpainyeccion.Traer_Historial_Prestamos_inyeccion(id_iny_historial);
                    if (lst_historial_inyeccion != null && !lst_historial_inyeccion.isEmpty()) {
                        for (int j = 0; j < lst_historial_inyeccion.size(); j++) {
                            Object[] obj_historial_inyeccion = (Object[]) lst_historial_inyeccion.get(j);
                            out.print("<tr>");
                            out.print("<td align='center' style='font-size: 11px;'><b>FECHA: </b>" + obj_historial_inyeccion[4].toString().replace(" ", "&nbsp;&nbsp;<b>HORA: </b>") + "<div style='margin-top: 5px; margin-bottom: 5px; border-top: 1px solid black;'></div><b>RESPONSABLE: </b>" + obj_historial_inyeccion[3] + "<div style='margin-top: 5px; margin-bottom: 5px; border-top: 1px solid black;'></div><b>OBSERVACIONES: </b>" + obj_historial_inyeccion[5] + "</td>");
                            if (obj_historial_inyeccion[6] != null) {
                                out.print("<td align='center' style='font-size: 11px;'><b>FECHA: </b>" + obj_historial_inyeccion[7].toString().replace(" ", "&nbsp;&nbsp;<b>HORA: </b>") + "<div style='margin-top: 5px; margin-bottom: 5px; border-top: 1px solid black;'></div><b>RESPONSABLE: </b>" + obj_historial_inyeccion[6] + "<div style='margin-top: 5px; margin-bottom: 5px; border-top: 1px solid black;'></div><b>OBSERVACIONES: </b>" + obj_historial_inyeccion[8] + "</td>");
                            } else {
                                out.print("<td align='center'><b style='color: orange; class='naranja'>PRÉSTADO</b></td>");
                            }
                            out.print("</tr>");
                        }
                    } else {
                        out.print("<tr><td style='text-align:center;' colspan='2'>No existen datos</td></tr>");
                    }
                    out.print("</tbody>");
                    out.print("</table>");
                }
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="MODIFICAR">
            if (id_iny_m == 0) {
                out.print("<div class='sweet-local' tabindex='-1' id='Ventana3' style='opacity: 1.03; display:none;'>");
            } else {
                List lst_iny = jpainyeccion.Traer_id_inyeccion(id_iny_m);
                for (int i = 0; i < lst_iny.size(); i++) {
                    Object[] obj_iny = (Object[]) lst_iny.get(i);
                    out.print("<div class='sweet-local' tabindex='-1' id='Ventana3' style='opacity: 1.03; display:block;'>");
                    out.print("<div class='cont_reg'>");
                    out.print("<div style='display: flex; justify-content: space-between'>");
                    out.print("<h2>Modificar Inyeccion</h2>");
                    out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(3)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
                    out.print("</div>");
                    out.print("<div class='cont_form_user'>");
                    out.print("<form action='in?opc=2&id_iny=" + id_iny_m + "&Flt_Id_proceso=" + filtro + "&Nombre_proceso=" + nombre_proceso + "&year=" + year + "' id='form' method='post' class='needs-validation' novalidate=''>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='' placeholder='Referencia Producto' value='" + obj_iny[17] + "' required='' data-toggle='tooltip' data-placement='top' title='Referencia Producto' disabled='disabled'>");
                    out.print("<input type='hidden' name='conin'  value='" + obj_iny[1] + "'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    String lote_p = obj_iny[3].toString();
                    String[] jj = lote_p.split("-");
                    String lote1 = jj[0];
                    String lote2 = jj[1];

                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='lote_1' placeholder='lote 1' value=" + lote1 + " onchange='javascript:this.value=this.value.toUpperCase();' required='' data-toggle='tooltip' data-placement='top' title='lote 1'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");
                    out.print("</div>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='lote_2' placeholder='lote 2' value=" + lote2 + " onchange='javascript:this.value=this.value.toUpperCase();' required='' data-toggle='tooltip' data-placement='top' title='lote 2'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='' placeholder='Lote C anterior:' value='" + obj_iny[2] + "' required='' data-toggle='tooltip' data-placement='top' title='Lote C anterior' disabled='disabled'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");
                    out.print("</div>");

//                    if (obj_iny != null && obj_iny.length > 3) {
//                        System.out.println("Tamaño de obj_iny: " + obj_iny.length);
//                        out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
//                        out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-left:3%; margin-right:-3%;'>");
//                        out.print("<select class='select2' name='lote_c' id='txtlotec' class='form-control' data-toggle='tooltip' data-placement='top' title='lote c'>");
//                        out.print("<option value='" + obj_iny[2] + "'>" + obj_iny[2].toString() + "</option>");
//
//                        lst_lotesC = jpainyeccion.Lotesciny(filtro);
//
//                        if (lst_lotesC != null && !lst_lotesC.isEmpty()) {
//                            System.out.println("Tamaño de lst_lotesC: " + lst_lotesC.size());
//                            for (int x = 0; x < lst_lotesC.size(); x++) {
//                                Object[] lotesC = (Object[]) lst_lotesC.get(x);
//                                if (lotesC != null && lotesC.length > 4) {
//                                    System.out.println("Tamaño de lotesC[" + x + "]: " + lotesC.length);
//                                    out.print("<option value='" + lotesC[2] + " / " + lotesC[0] + "'>" + lotesC[2] + "/" + lotesC[0] + " - " + lotesC[1] + " " + lotesC[4] + "</option>");
//                                } else {
//                                    System.out.println("Datos incompletos en lotesC[" + x + "]");
//                                    out.print("<option value='0'>Datos incompletos en lotesC</option>");
//                                }
//                            }
//                        } else {
//                            System.out.println("lst_lotesC es nulo o vacío");
//                            out.print("<option value='0'>Se ha producido un error</option>");
//                        }
//                        out.print("</select>");
//                        out.print("</div>");
//                    } else {
//                        System.out.println("Datos incompletos en obj_iny");
//                        out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
//                        out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-left:3%; margin-right:-3%;'>");
//                        out.print("<select class='select2' name='lote_c' id='txtlotec' class='form-control' data-toggle='tooltip' data-placement='top' title='lote c'>");
//                        out.print("<option value='0'>Datos incompletos en obj_iny</option>");
//                    }

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-left:3%; margin-right:-3%;'>");
                    out.print("<select class='select2' name='lote_c' id='txtlotec' class='form-control' data-toggle='tooltip' data-placemente='top' title='lote c'>");
                    out.print("<option value='" + obj_iny[2] + "'>" + obj_iny[2].toString() + "</option>");
                    lst_lotesC = jpainyeccion.Lotesciny(filtro);
                    if (lst_lotesC != null || lst_lotesC.size() != 0) {
                        for (int x = 0; x < lst_lotesC.size(); x++) {
                            Object[] lotesC = (Object[]) lst_lotesC.get(x);
                            out.print("<option value='" + lotesC[2] + " / " + lotesC[0] + "'>" + lotesC[2] + "/" + lotesC[0] + " - " + lotesC[1] + " " + lotesC[4] + "</option>");
                        }
                    } else {
                        out.print("<option value='0'>Se ha producido un error</option>");
                    }
                    out.print("</select>");
                    out.print("</div>");
                    
                    out.print("<div class='col-12' style='margin-top: 3px;'>");
                    out.print("<input type='number' class='form-control' name='linea' id='' placeholder='Modulo/Linea' value='" + obj_iny[7] + "' required data-toggle='tooltip' data-placemente='top' title='Modulo/Linea' min='1'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");
                    out.print("</div>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-12'>");
                    out.print("<input type='date' class='form-control' name='fecha' id='datepicker' placeholder='Fecha' value='" + obj_iny[5] + "' required data-toggle='tooltip' data-placemente='top' title='Fecha'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='col-12' style='margin-top:2.5%; margin-left:2.7%; width:94%;'>");
                    out.print("<textarea type='text' class='form-control' name='obs' id='' placeholder='Observacion' required data-toggle='tooltip' data-placemente='top' title='Observacion'>" + obj_iny[6] + "</textarea>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");
                    out.print("</div>");
                    out.print("<br>");

                    out.print("<div class='' style='width: 100%; text-align:center;'>");
                    out.print("<button class='btn btn-green btn-lg'>Editar</button>");
                    out.print("</div>");

                    out.print("</form>");
                    out.print("</div>");
                    out.print("</div>");
                    out.print("</div>");
                }
            }
            out.print("</div>");
            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="REGISTRAR">
            if (id_registro == 0) {
                out.print("<div class='sweet-local' tabindex='-1' id='Ventana1' style='opacity: 1.03; display:none;'>");
            } else {
                out.print("<div class='sweet-local' tabindex='-1' id='Ventana1' style='opacity: 1.03; display:block;'>");
                out.print("<div class='cont_reg2'>");
                out.print("<div style='display: flex; justify-content: space-between'>");
                out.print("<h2>Ingresar Formula</h2>");
                out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(1)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
                out.print("</div>");
                out.print("<div class='cont_form_user'>");
                out.print("<form action='in?opc=1&id_registro=" + filtro + "&Flt_Id_proceso=" + filtro + "&Nombre_proceso=" + nombre_proceso + "&year=" + year + "' method='post' class='needs-validation' novalidate=''>");
                if (formula.equals("")) {
                    out.print("<div class='col-11'>");
                    out.print("<input type='text' class='form-control' name='Formula' id='Formula' placeholder='Formula' required='' data-toggle='tooltip' data-placemente='top' title='Formula'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");
                } else {
                    out.print("<div class='col-11'>");
                    out.print("<input type='text' class='form-control' name='Formula' id='Formula' placeholder='Formula' value='" + formula + "' required='' data-toggle='tooltip' data-placemente='top' title='Formula'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");
                }
                out.print("</form>");
                out.print("</div>");
                out.print("</div>");
                out.print("</div>");

                if (!formula.equals("")) {
                    out.print("<div class='sweet-local' tabindex='-1' id='Ventana2' style='opacity: 1.03; display:block;'>");
                    out.print("<div class='cont_reg'>");
                    out.print("<div style='display: flex; justify-content: space-between'>");
                    out.print("<h2>Registrar</h2>");
                    out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(2);CerrarModalFormula();' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
                    out.print("</div>");
                    out.print("<div class='cont_form_user'>");
                    out.print("<form action='in?opc=2&Formula=" + filtro + "&Flt_Id_proceso=" + filtro + "&Nombre_proceso=" + nombre_proceso + "&year=" + year + "' method='post' class='needs-validation' novalidate=''>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='Formula' id='Formula' placeholder='Formula' value='" + formula + "' required='' data-toggle='tooltip' data-placemente='top' title='Formula' disabled='disabled'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-left:3%; margin-right:-3%;'>");
                    out.print("<select class='select2' name='codigoProducto' data-toggle='tooltip' data-placement='top' style='width: 447px !important'>");
                    out.print("<option value='empty'>Seleccione una formula</option>");
                    lst_factory = refinv.Productos(formula);
                    if (lst_factory != null) {
                        for (int b = 0; b < lst_factory.size(); b++) {
                            out.print("<option value='" + lst_factory.get(b) + "' > " + lst_factory.get(b) + "</option>");
                        }
                    }
                    out.print("</select>");
                    out.print("</div>");
                    out.print("</div>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='lote_1' id='lote_1' placeholder='Lote P' nchange='javascript:this.value=this.value.toUpperCase();' required data-toggle='tooltip' data-placemente='top' title='lote 1'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='lote_2' id='lote_2' placeholder='Lote P' onchange='javascript:this.value=this.value.toUpperCase();' required data-toggle='tooltip' data-placemente='top' title='lote 2'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");
                    out.print("</div>");

                    lst_lotesC = jpainyeccion.Lotesciny(filtro);
                    if (lst_lotesC != null) {
                        out.print("<div class='col-12' style='margin-top:1%; margin-left:2.6%; margin-bottom:1%; width:94.3%'>");
                        out.print("<select class='select2' autocomplete='off' type='text' name='lote_c' class='form-control' placeholder='Seleccionar Control .....' onchange='LoteC_autocompletar(this.value);validar_boton()' onkeyup='validar_boton()' list='datalist'>");
                        out.print("<option value='empty'>Seleccionar Control ...</option>");
                        for (int x = 0; x < lst_lotesC.size(); x++) {
                            Object[] lotesC = (Object[]) lst_lotesC.get(x);
                            out.print("<option value='" + lotesC[2] + " / " + lotesC[0] + "'>" + lotesC[2] + "/" + lotesC[0] + " - " + lotesC[1] + " " + lotesC[4] + "</option>");
                        }
                        out.print("</select>");
                        out.print("</div>");
                    } else {
                        out.print("<input type='hidden' name='lote_c' value='N/A'>");
                    }

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");

                    out.print("<div class='col-12'>");
                    out.print("<input type='number' class='form-control' name='linea' id='linea' placeholder='Molde/Linea' min='1' required data-toggle='tooltip' data-placemente='top' title='Linea'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='col-12'>");
                    out.print("<input type='date' class='form-control' name='fecha' id='datepicker' placeholder='Fecha' value='" + fecha_actual + "' required data-toggle='tooltip' data-placemente='top' title='Codigo'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("</div>");

                    out.print("<div class='col-12' style='margin-top:1%; margin-left:2.6%; margin-bottom:1%; width:94.3%;'>");
                    out.print("<textarea class='form-control' name='obs' id='obs' placeholder='Observaciones' required data-toggle='tooltip' data-placemente='top' title='Observaciones'></textarea>");
                    out.print("<div class='invalid-feedback'><i class='fas fa-exclamation-circle'></i> Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='' style='width: 100%; text-align:center;'>");
                    out.print("<button class='btn btn-green btn-lg'>Registrar</button>");
                    out.print("</div>");

                    out.print("</form>");
                    out.print("</div>");
                    out.print("</div>");
                    out.print("</div>");
                }
            }
            //</editor-fold>
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.doStartTag();
    }
}
