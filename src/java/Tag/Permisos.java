package Tag;

import Controladores.PermisosJpaController;
import Controladores.RolJpaController;
import Controladores.UsuarioJpaController;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Permisos extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        RolJpaController jpacrol = new RolJpaController();
        PermisosJpaController jpapermisos = new PermisosJpaController();
        UsuarioJpaController jpacusa = new UsuarioJpaController();
        List lst_permisos = null;
        List lst_modulos = null;
        List lst_rol_id = null;
        int id_rol, id_rol_permission, idRol = 0;
        String txtPermisos = "";
        int estado, id_permiso = 0;
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
                id_permiso = Integer.parseInt(pageContext.getRequest().getAttribute("id_permiso").toString());
            } catch (Exception e) {
                id_permiso = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="EDITAR PERMISO">
            if (id_permiso > 0) {
                lst_permisos = jpapermisos.trear_permiso_id(id_permiso);
                if (lst_permisos != null) {
                    Object[] obj_permisos = (Object[]) lst_permisos.get(0);
                    out.print("<div class='sweet-local' tabindex='-1' id='Ventana2' style='opacity: 1.03; display:block;'>");
                    out.print("<div class='cont_form_permi'>");
                    out.print("<div style='display: flex; justify-content: space-between'>");
                    out.print("<h2>Editar Permiso</h2>");
                    out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(2)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
                    out.print("</div>");
                    out.print("<div class='cont_form_temp'>");
                    out.print("<form action='Permisos?opc=3&id_permiso=" + obj_permisos[0] + "' method='post' class='needs-validation' novalidate=''>");
                    out.print("<div class=''>");
                    out.print("<div class='col-12' style='display: flex;margin-top: 10px;'>");
                    out.print("<div class='col-6' style='width: 100%;' id='select2' data-toggle='tooltip' data-placement='top' title='Modulo'>");
                    out.print("<input class='form-control' name='modulo' list='datalist' placeholder='Modulo'>");
                    out.print("<datalist id='datalist'>");
                    lst_modulos = jpapermisos.lista_modulos();
                    if (lst_modulos != null) {
                        for (Object modulo : lst_modulos) {
                            out.print("<option>" + modulo + "</option>");
                        }
                    }
                    out.print("</datalist>");
                    out.print("</div>");
                    out.print("&nbsp;&nbsp;&nbsp;");
                    out.print("<div class='col-6'>");
                    out.print("<input type='text' class='form-control' name='opcion' id='opcion' placeholder='Opcion' value='" + obj_permisos[2] + "' required data-toggle='tooltip' data-placement='top' title='Opcion'>");
                    out.print("<div class='invalid-feedback'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");
                    out.print("</div>");
                    out.print("<div class='col-12' style='display: flex;'>");
                    out.print("<div class='col-8'>");
                    out.print("<input type='text' class='form-control' name='descripcion' id='descripcion' placeholder='Descripcion' value='" + obj_permisos[3] + "' required data-toggle='tooltip' data-placement='top' title='Descripcion'>");
                    out.print("<div class='invalid-feedback'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");
                    out.print("<div class='col-4'>");
                    out.print("<label class='custom-switch mt-2'>");
                    estado = Integer.parseInt(obj_permisos[4].toString());
                    out.print("<span class='custom-switch-description'>Estado del permiso &nbsp;&nbsp;</span>");
                    out.print("<input type='checkbox' class='custom-switch-input' " + ((estado == 1) ? "checked" : "") + " onclick='SwitchValue()'>");
                    out.print("<span class='custom-switch-indicator'></span>");
                    out.print("</label>");
                    out.print("<input type='hidden' name='estado' id='Nmb_est' value='" + estado + "'>");
                    out.print("</div>");
                    out.print("</div>");

                    out.print("<div class='' style='width: 100%; text-align:center;'>");
                    out.print("<button class='btn btn-green btn-lg'>Editar</button>");
                    out.print("</div>");

                    out.print("</div>");
                    out.print("</form>");
                    out.print("</div>");
                    out.print("</div>");
                    out.print("</div>");
                }
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="REGISTRAR PERMISO">
            out.print("<div class='sweet-local' tabindex='-1' id='Ventana1' style='opacity: 1.03; display:none;'>");
            out.print("<div class='cont_form_permi'>");
            out.print("<div style='display: flex; justify-content: space-between'>");
            out.print("<h2>Registrar Permisos</h2>");
            out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(1)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
            out.print("</div>");
            out.print("<div class='cont_form_temp'>");
            out.print("<form action='Permisos?opc=3' method='post' class='needs-validation' novalidate=''>");

            out.print("<div class=''>");
            out.print("<div class='col-12' style='display: flex; justify-content:space-around;'>");
            out.print("<div style='width: 45%;' id='select2' data-toggle='tooltip' data-placement='top' title='Modulo'>");
            out.print("<input class='form-control' name='modulo' list='datalist' placeholder='Modulo'>");
            out.print("<datalist id='datalist'>");
            lst_modulos = jpapermisos.lista_modulos();
            if (lst_modulos != null) {
                for (Object modulo : lst_modulos) {
                    out.print("<option>" + modulo + "</option>");
                }
            }
            out.print("</datalist>");
            out.print("</div>");
            out.print("<div style='width: 45%;'>");
            out.print("<input type='text' class='form-control' name='opcion' id='Txt_option' placeholder='Opcion' style='margin-bottom: 12px;' required data-toggle='tooltip' data-placement='top' title='Opcion'>");
            out.print("<div class='invalid-feedback'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='col-12' style='margin-bottom: 12px;'>");
            out.print("<input type='text' class='form-control' name='descripcion' id='Txt_code' placeholder='Descripcion' required data-toggle='tooltip' data-placement='top' title='Descripcion'>");
            out.print("<div class='invalid-feedback'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("<div class='' style='width: 100%; text-align:center; margin-top: 12px;'>");
            out.print("<button class='btn btn-green btn-lg'>Registrar</button>");
            out.print("</div>");
            out.print("</div>");

            out.print("</form>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="TABLA">
            out.print("<section class='section'>");
            out.print("<div class='section-header'>");
            out.print("<h1>Modulo Permisos</h1>");
            out.print("</div>");
            out.print("<div class='section-body'>");
            out.print("<div class='row'>");
            out.print("<div class='col-12'>");
            out.print("<div class='card'>");
            out.print("<div class='card-header' style='justify-content: space-between;'>");
            out.print("<h4>Tabla de Permisos</h4>");
            if (txtPermisos.contains("[8]")) {
                out.print("<button class='btn btn-green' style='border-radius: 4px;' onclick='mostrarConvencion(1)' data-toggle='tooltip' data-placement='top' title='Registrar Permiso'><i class='fas fa-plus'></i></button>");
            } else {
                out.print("<button class='btn btn-green' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-plus'></i></button>");
            }
            out.print("</div>");
            out.print("<div class='card-body'>");
            out.print("<div class='table-responsive'>");
            out.print("<table class='table table-bordered table-hover' id='table-1'>");
            out.print("<thead>");
            out.print("<tr>");
            out.print("<th>Modulo</th>");
            out.print("<th>Opcion</th>");
            out.print("<th>Descripcion</th>");
            out.print("<th>Estado</th>");
            out.print("<th>Modificar</th>");
            out.print("<th>Opcion</th>");
            out.print("</tr>");
            out.print("</thead>");
            out.print("<tbody>");
            lst_permisos = jpapermisos.lista_permisos();
            if (lst_permisos != null || lst_permisos.size() != 0) {
                for (int i = 0; i < lst_permisos.size(); i++) {
                    Object[] obj_permisos = (Object[]) lst_permisos.get(i);
                    out.print("<tr>");
                    out.print("<td>" + obj_permisos[1] + "</td>");
                    out.print("<td>" + obj_permisos[2] + "</td>");
                    out.print("<td>" + obj_permisos[3] + "</td>");
                    estado = Integer.parseInt(obj_permisos[4].toString());
                    out.print("<td align='center'>" + ((estado == 1) ? "<div class='badge badge-success'>Activo</div>" : "<div class='badge badge-danger'>Inactivo</div>") + "</td>");

                    out.print("<td align='center'>");
                    if (txtPermisos.contains("[9]")) {
                        out.print("<button type='button' onclick=\"javascript:location.href='Permisos?opc=1&id_permiso=" + obj_permisos[0] + "'\" class='btn btn-sm btn-primary btn-icon' data-toggle='tooltip' data-placement='top' title='Modificar Permiso'><i class='fas fa-pencil-alt'></i></button>");
                    } else {
                        out.print("<button class='btn btn-green btn-sm' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-pencil-alt'></i></button>");
                    }
                    out.print("</td>");

                    out.print("<td align='center'>");
                    if (txtPermisos.contains("[10]")) {
                        out.print("<button type='button' onclick=\"javascript:location.href='Permisos?opc=2&id_permiso=" + obj_permisos[0] + "&estado=" + estado + "'\" class='btn btn-sm btn-" + ((estado == 1) ? "success" : "danger") + " btn-icon' data-toggle='tooltip' data-placement='top' title='Cambiar estado'><i class='" + ((estado == 1) ? "fas fa-check" : "fas fa-times") + "'></i></button>");
                    } else {
                        out.print("<button class='btn btn-green btn-sm' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-check'></i></i></button>");
                    }
                    out.print("</td>");

                    out.print("</td>");
                    out.print("</tr>");
                }
            } else {
                out.print("<tr>");
                out.print("<td colspan='6'>No se han encontrado datos</td>");
                out.print("</tr>");
            }
            out.print("</tbody>");
            out.print("</table>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("</section>");
            //</editor-fold>
        } catch (IOException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.doStartTag();
    }
}
