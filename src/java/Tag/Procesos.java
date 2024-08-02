package Tag;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import Controladores.ProcesosJpaController;
import Controladores.RolJpaController;

public class Procesos extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        ProcesosJpaController jpapro = new ProcesosJpaController();
        RolJpaController jpacrol = new RolJpaController();
        List lst_pr = null;
        List lst_tpr = null;
        List lst_rol_id = null;
        int Id_proceso = 0;
        int estado, id_rol, id_rol_permission, idRol = 0;
        String txtPermisos = "";
        try {
            try {
                idRol = Integer.parseInt(pageContext.getRequest().getAttribute("Id_rol").toString());
                lst_rol_id = jpacrol.Consult_role_id(idRol);
                Object[] obj_permi = (Object[]) lst_rol_id.get(0);
                txtPermisos = obj_permi[2].toString();
            } catch (Exception e) {
                id_rol = 0;
                txtPermisos = "";
            }
            try {
                id_rol_permission = Integer.parseInt(pageContext.getRequest().getAttribute("id_rol_permission").toString());
            } catch (Exception e) {
                id_rol_permission = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="TABLA">
            try {
                Id_proceso = Integer.parseInt(pageContext.getRequest().getAttribute("Id_proceso").toString());
            } catch (NumberFormatException e) {
                Id_proceso = 0;
            }
            out.print("<section class='section'>");
            out.print("<div class='section-header'>");
            out.print("<h1>Modulo Procesos</h1>");
            out.print("</div>");
            out.print("<div class='section-body'>");
            out.print("<div class='row'>");
            out.print("<div class='col-12'>");
            out.print("<div class='card'>");
            out.print("<div class='card-header' style='justify-content: space-between;'>");
            out.print("<h4>Tabla de Procesos</h4>");
            if (txtPermisos.contains("[5]")) {
                out.print("<button class='btn btn-green' style='border-radius: 4px;' onclick='mostrarConvencion(1)' data-toggle='tooltip' data-placement='top' title='Registrar Proceso'><i class='fas fa-plus'></i></button>");
            } else {
                out.print("<button class='btn btn-green' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-plus'></i></button>");
            }
            out.print("</div>");
            out.print("<div class='card-body'>");
            out.print("<div class='table-responsive'>");
            out.print("<table class='table table-bordered' id='table-1'>");
            out.print("<thead>");
            out.print("<tr>");
            out.print("<th>Nombre</th>");
            out.print("<th>Sigla</th>");
            out.print("<th>Actual</th>");
            out.print("<th>Tipo de proceso</th>");
            out.print("<th>Estado</th>");
            out.print("<th>Modificar</th>");
            out.print("</tr>");
            out.print("</thead>");
            out.print("<tbody>");
            lst_pr = jpapro.Procesos();
            if (lst_pr != null) {
                for (int i = 0; i < lst_pr.size(); i++) {
                    Object[] obj_procesos = (Object[]) lst_pr.get(i);
                    if (Integer.parseInt(obj_procesos[3].toString()) == 0) {
                        out.print("<tr>");
                        out.print("<td>" + obj_procesos[1] + "</td>");
                        out.print("<td>" + obj_procesos[2] + "</td>");
                        out.print("<td>" + obj_procesos[4] + "</td>");
                        out.print("<td>" + obj_procesos[7] + "</td>");
                        out.print("<td align='center'>");
                        if (txtPermisos.contains("[7]")) {
                            out.print("<a href='#' onclick='DesactivarProceso(" + obj_procesos[0] + ")' class='btn btn-success btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Desactivar Proceso'><i class='fas fa-check'></i></a>");
                        } else {
                            out.print("<button class='btn btn-green btn-sm' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-check'></i></button>");
                        }
                        out.print("</td>");
                        out.print("<td align='center'>");
                        if (txtPermisos.contains("[6]")) {
                            out.print("<a href='Procesos?opc=1&Id_proceso=" + obj_procesos[0] + "' class='btn btn-primary btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Modificar Proceso'><i class='fas fa-pencil-alt'></i></a>");
                        } else {
                            out.print("<button class='btn btn-green btn-sm' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-pencil-alt'></i></button>");
                        }
                        out.print("</td>");
                        out.print("</tr>");
                    } else {
                        out.print("<tr class='rojo'>");
                        out.print("<td>" + obj_procesos[1] + "</td>");
                        out.print("<td>" + obj_procesos[2] + "</td>");
                        out.print("<td>" + obj_procesos[4] + "</td>");
                        out.print("<td>" + obj_procesos[7] + "</td>");
                        out.print("<td align='center'>");
                        out.print("<a href='#' onclick='ActivarProceso(" + obj_procesos[0] + ")' class='btn btn-danger btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Activar Proceso'><i class='fas fa-times'></i></a>");
                        out.print("</td>");
                        out.print("<td align='center'>");
                        out.print("<a href='#' onclick='No Tiene Permisos(" + obj_procesos[0] + ")' class='btn btn-secondary btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='No Tiene Permisos'><i class='fas fa-ban'></i></a>");
                        out.print("</td>");
                        out.print("</tr>");
                    }
                }
            }
            out.print("</tbody>");
            out.print("</table>");

//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="MODIFICAR PROCESO">
            if (Id_proceso > 0) {
                lst_pr = jpapro.Consulta_idProceso(Id_proceso);
                if (lst_pr != null) {
                    Object[] obj_procesos = (Object[]) lst_pr.get(0);
                    out.print("<div class='sweet-local' tabindex='-1' id='Ventana2' style='opacity: 1.03; display:block;'>");
                    out.print("<div class='cont_reg'>");
                    out.print("<div style='display: flex; justify-content: space-between'>");
                    out.print("<h2>Editar Proceso " + obj_procesos[1].toString() + "</h2>");
                    out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(2)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
                    out.print("</div>");
                    out.print("<div class='cont_form_user'>");
                    out.print("<form action='Procesos?opc=5&Id_proceso=" + obj_procesos[0] + "' method='post' class='needs-validation' novalidate=''>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='txtnombre' placeholder='Nombre' value='" + obj_procesos[1] + "' required='' data-toggle='tooltip' data-placement='top' title='Nombre'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='txtsigla' placeholder='Sigla' value='" + obj_procesos[2] + "' required='' data-toggle='tooltip' data-placement='top' title='Sigla'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");
                    out.print("</div>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='txtactual' placeholder='Actual' value='" + obj_procesos[4] + "' required data-toggle='tooltip' data-placement='top' title='Actual'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-left:3%; margin-right:-3%;'>");
                    out.print("<select class='select2' name='slc_proceso'>");
                    out.print("<option value='" + obj_procesos[7] + "'>" + obj_procesos[7].toString() + "</option>");
                    lst_tpr = jpapro.ConsultarParametros_xCategoria("TipoProceso");
                    if (lst_tpr != null || lst_tpr.size() != 0) {
                        for (int i = 0; i < lst_tpr.size(); i++) {
                            Object[] obj_proceso = (Object[]) lst_tpr.get(i);
                            out.print("<option value='" + obj_proceso[3] + "'>" + obj_proceso[3] + "</option>");
                        }
                    } else {
                        out.print("<option value='0'>Se ha producido un error</option>");
                    }
                    out.print("</select>");
                    out.print("</div>");
                    out.print("</div>");

                    out.print("<div class='' style='width: 100%; text-align:center;'>");
                    out.print("<button type='submit' class='btn btn-green btn-lg'>Editar</button>");
                    out.print("</div>");

                    out.print("</form>");
                    out.print("</div>");
                    out.print("</div>");
                    out.print("</div>");
                } else {

                }
            } else {

            }
            out.print("</div>");
            out.print("</section>");
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="REGISTRAR PROCESO">
            out.print("<div class='sweet-local' tabindex='-1' id='Ventana1' style='opacity: 1.03; display:none;'>");
            out.print("<div class='cont_reg'>");
            out.print("<div style='display: flex; justify-content: space-between'>");
            out.print("<h2>Registrar Proceso</h2>");
            out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(1)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
            out.print("</div>");
            out.print("<div class='cont_form_user'>");
            out.print("<form action='Procesos?opc=2' method='post' class='needs-validation' novalidate=''>");

            out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
            out.print("<div class='col-12'>");
            out.print("<input type='text' class='form-control' name='txtnombre' placeholder='Nombre' required='' data-toggle='tooltip' data-placemente='top' title='Nombre'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("<div class='col-12'>");
            out.print("<input type='text' class='form-control' name='txtsigla' placeholder='Sigla' required='' data-toggle='tooltip' data-placemente='top' title='Sigla'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
            out.print("<div class='col-12'>");
            out.print("<input type='number' class='form-control' name='txtactual' placeholder='Actual' required data-toggle='tooltip' data-placemente='top' title='Actual'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-left:3%; margin-right:-3%;'>");
            out.print("<select class='select2' name='slc_proceso' data-toggle='tooltip' data-placement='top'>");
            out.print("<option value='empty'>Seleccione Proceso</option>");
            lst_tpr = jpapro.ConsultarParametros_xCategoria("TipoProceso");
            if (lst_tpr != null || lst_tpr.size() != 0) {
                for (int i = 0; i < lst_tpr.size(); i++) {
                    Object[] obj_proceso = (Object[]) lst_tpr.get(i);
                    out.print("<option value='" + obj_proceso[3] + "'>" + obj_proceso[3] + "</option>");
                }
            } else {
                out.print("<option value='0'>Se ha producido un error</option>");
            }
            out.print("</select>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='' style='width: 100%; text-align:center;'>");
            out.print("<button class='btn btn-green btn-lg'>Registrar</button>");
            out.print("</div>");

            out.print("</form>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
//</editor-fold>
        } catch (IOException ex) {
            Logger.getLogger(Procesos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.doStartTag();
    }
}
