package Tag;

import Controladores.PermisosJpaController;
import Controladores.RolJpaController;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Rol extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        RolJpaController jpacrol = new RolJpaController();
        PermisosJpaController jpapermisos = new PermisosJpaController();
        List lst_roles = null;
        List lst_rol_id = null;
        List lst_modulos = null;
        List lst_opciones_modulo = null;
        int estado, id_rol, id_rol_permission, idRol = 0;
        String txtPermisos = "";
        try {
            try {
                id_rol = Integer.parseInt(pageContext.getRequest().getAttribute("id_rol").toString());
            } catch (Exception e) {
                id_rol = 0;
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
            try {
                id_rol_permission = Integer.parseInt(pageContext.getRequest().getAttribute("id_rol_permission").toString());
            } catch (Exception e) {
                id_rol_permission = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="MODIFICAR USUARIO">
            if (id_rol > 0) {
                lst_roles = jpacrol.Consult_role_id(id_rol);
                if (lst_roles != null) {
                    Object[] obj_roles = (Object[]) lst_roles.get(0);
                    out.print("<div class='sweet-local' tabindex='-1' id='Ventana2' style='opacity: 1.03; display:block;'>");
                    out.print("<div class='cont_form_permi'>");
                    out.print("<div style='display: flex; justify-content: space-between'>");
                    out.print("<h2>Editar Rol</h2>");
                    out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(2)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
                    out.print("</div>");
                    out.print("<div class='cont_form_temp'>");
                    out.print("<form action='Rol?opc=3&id_rol=" + obj_roles[0] + "' method='post' class='needs-validation' novalidate=''>");
                    out.print("<div class=''>");

                    out.print("<div class='col-12' style='display: flex;margin-top: 10px;'>");
                    out.print("<div class='col-6'>");
                    out.print("<input type='text' class='form-control' name='nombre' id='opcion' placeholder='Nombre Rol' value='" + obj_roles[1] + "' required data-toggle='tooltip' data-placement='top' title='Nombre Rol'>");
                    out.print("<div class='invalid-feedback'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='col-4'>");
                    out.print("<label class='custom-switch mt-2'>");
                    estado = Integer.parseInt(obj_roles[3].toString());
                    out.print("<span class='custom-switch-description'>Estado del rol &nbsp;&nbsp;</span>");
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

////</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="REGISTRAR USUARIO">
            out.print("<div class='sweet-local' tabindex='-1' id='Ventana1' style='opacity: 1.03; display:none;'>");
            out.print("<div class='cont_reg2'>");
            out.print("<div style='display: flex; justify-content: space-between'>");
            out.print("<h2>Registrar Rol</h2>");
            out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(1)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
            out.print("</div>");
            out.print("<div class='cont_form_temp'>");
            out.print("<form action='Rol?opc=3' method='post' class='needs-validation' novalidate=''>");

            out.print("<div class='col-12' style='margin-bottom: 12px;'>");
            out.print("<input type='text' class='form-control' name='nombre' id='nombre' placeholder='Nombre Rol' required data-toggle='tooltip' data-placement='top' title='Nombre Rol'>");
            out.print("<div class='invalid-feedback'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("<div class='' style='width: 100%; text-align:center; margin-top: 12px;'>");
            out.print("<button class='btn btn-green btn-lg'>Registrar</button>");
            out.print("</div>");

            out.print("</form>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
////</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="PERMISOS">
            if (id_rol_permission > 0) {
                lst_rol_id = jpacrol.Consult_role_id(id_rol_permission);
                Object[] obj_rol_permission = (Object[]) lst_rol_id.get(0);
                out.print("<div class='sweet-local' tabindex='-1' id='Ventana3' style='opacity: 1.03; display:block;'>");
                out.print("<div class='cont_role_permission'>");
                out.print("<div style='display: flex; justify-content: space-between'>");
                out.print("<h4>Permisos</h4>");
                out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(3)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
                out.print("</div>");
                out.print("<div class='cont_form_user'>");
                out.print("<form action='Rol?opc=4' method='post'>");
                out.print("<input type='hidden' name='Cbx_permission' id='Cbx_permission' value='" + obj_rol_permission[2] + "'>");
                out.print("<input type='hidden' value='" + id_rol_permission + "' name='id_rol' id='id_rol'>");
                out.print("<div class=\"card-body\">");
                out.print("<div class=\"row\">");
                out.print("<div class='col-12 col-sm-12 col-md-4' style='max-height:318px;'>");
                out.print("<div class='scrollbar'>");
                out.print("<ul class=\"nav nav-pills flex-column\" id=\"myTab4\" role=\"tablist\">");
                //<editor-fold defaultstate="collapsed" desc="MODULOS">
                lst_modulos = jpapermisos.lista_modulos();
                String modules = "", cons_modules = "";
                if (lst_modulos != null) {
                    for (int i = 0; i < lst_modulos.size(); i++) {
                        Object[] Obj_module = (Object[]) lst_modulos.get(i);
                        String module = Obj_module[1].toString().replace(" ", "_").replace("-", "_");
                        out.print("<li class=\"nav-item\">");
                        out.print("<a class=\"nav-link " + ((i == 0) ? "active" : "") + " \" id=\"" + module + "-tab\" data-toggle=\"tab\" href=\"#" + module + "\" role=\"tab\" aria-controls=\"" + module + "\" aria-selected=\"true\">" + Obj_module[1] + "</a>");
                        out.print("</li>");
                        modules += "[" + module + "]";
                        cons_modules += "[" + Obj_module[1] + "]";
                    }
                } else {
                    out.print("<li class=\"nav-item\">");
                    out.print("<a class=\"nav-link active\" id=\"-tab\" data-toggle=\"tab\" href=\"#\" role=\"tab\" aria-controls=\"\" aria-selected=\"true\">Ha ocurrido un error, favor comunicarse a T.I</a>");
                    out.print("</li>");
                }
                out.print("</ul>");
                //</editor-fold>
                out.print("</div>");
                out.print("</div>");
                //<editor-fold defaultstate="collapsed" desc="LISTA PERMISOS">
                out.print("<div class=\"col-12 col-sm-12 col-md-8\">");
                out.print("<div class=\"tab-content no-padding\" id=\"myTab2Content\">");
                try {
                    String[] Arr_modules = modules.replace("][", "//").replace("[", "").replace("]", "").split("//");
                    String[] Arr_modules_cons = cons_modules.replace("][", "//").replace("[", "").replace("]", "").split("//");
                    for (int i = 0; i < Arr_modules.length; i++) {
                        out.print("<div class='tab-pane fade " + ((i == 0) ? "show active" : "") + "' id='" + Arr_modules[i] + "' role='tabpanel' aria-labelledby='" + Arr_modules[i] + "-tab'>");
                        lst_opciones_modulo = jpapermisos.lista_opciones_pormodulo(Arr_modules_cons[i]);
                        out.print("<h4>Permisos " + Arr_modules_cons[i] + "</h4>");

                        if (lst_opciones_modulo != null) {
                            out.print("<div class='module_permss'>");
                            for (int j = 0; j < lst_opciones_modulo.size(); j++) {
                                Object[] Obj_module = (Object[]) lst_opciones_modulo.get(j);
                                if (Obj_module[0] != null) {
                                    String permisoActual = "[" + Obj_module[0] + "]";
                                    System.out.println("Comparando con: " + permisoActual);

                                    if (obj_rol_permission[2].toString().contains(permisoActual)) {
                                        out.print("<input type='checkbox' name='#' id='' value='" + Obj_module[0] + "' onclick='Masivo(this.value);' checked><span>" + Obj_module[1] + "</span><br>");
                                    } else {
                                        out.print("<input type='checkbox' name='#' id='' value='" + Obj_module[0] + "' onclick='Masivo(this.value);'><span>" + Obj_module[1] + "</span><br>");
                                    }
                                }
                            }
                            out.print("</div>");
                        } else {
                            out.print("<div class='' style='text-align: center;'>");
                            out.print("<h4 style='margin-top: 5%;'>Se ha producido un error al cargar los permisos, favor comunicarse con T.I</h4>");
                            out.print("<i class=\"fas fa-sad-tear\" style='font-size: 80px;'></i>");
                            out.print("</div>");
                        }
                        out.print("</div>");
                    }
                } catch (Exception e) {
                    out.print("<div class='' style='text-align: center;'>");
                    out.print("<h4 style='margin-top: 5%;'>Se ha producido un error al cargar los permisos, favor comunicarse con T.I</h4>");
                    out.print("<i class=\"fas fa-sad-tear\" style='font-size: 80px;'></i>");
                    out.print("</div>");
                    out.print("</div>");
                    out.print("</div>");
                }
//</editor-fold>
                out.print("</div>");
                out.print("</div>");
                out.print("</div>");
                out.print("</div>");
                out.print("<div class='' style='width: 100%; text-align:center;'>");
                out.print("<button class='btn btn-green btn-lg'>Registrar</button>");
                out.print("</div>");
                out.print("</form>");
                out.print("</div>");
                out.print("</div>");
                out.print("</div>");
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="TABLA">
            out.print("<section class='section'>");
            out.print("<div class='section-header'>");
            out.print("<h1>Modulo Roles</h1>");
            out.print("</div>");
            out.print("<div class='row'>");
            out.print("<div class='col-12'>");
            out.print("<div class='card'>");
            out.print("<div class='card-header' style='justify-content: space-between;'>");
            out.print("<h4>Listado de Roles</h4>");
            if (txtPermisos.contains("[11]")) {
                out.print("<button class='btn btn-green' style='border-radius: 4px;' onclick='mostrarConvencion(1)' data-toggle='tooltip' data-placement='top' title='Registrar Rol'><i class='fas fa-plus'></i></button>");
            } else {
                out.print("<button class='btn btn-green' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-plus'></i></button>");
            }
            out.print("</div>");
            out.print("<div class='card-body'>");
            out.print("<div class='table-responsive'>");
            out.print("<table class='table table-bordered table-hover' id='table-1'>");
            out.print("<thead>");
            out.print("<tr>");
            out.print("<th>ID</th>");
            out.print("<th>Nombre</th>");
            out.print("<th>Estado</th>");
            out.print("<th>Permisos</th>");
            out.print("<th>Modificar</th>");
            out.print("<th>Opciones</th>");
            out.print("</tr>");
            out.print("</thead>");
            out.print("<tbody> ");
            lst_roles = jpacrol.lista_Roles();
            if (lst_roles != null || lst_roles.isEmpty()) {
                for (int i = 0; i < lst_roles.size(); i++) {
                    Object[] obj_rol = (Object[]) lst_roles.get(i);
                    out.print("<tr>");
                    out.print("<td align='center'>" + obj_rol[0] + "</td>");
                    out.print("<td>" + obj_rol[1] + "</td>");
                    estado = Integer.parseInt(obj_rol[3].toString());
                    out.print("<td class=\"text-center\">" + ((estado == 1) ? "<div class='badge badge-success'>Activo</div>" : "<div class='badge badge-danger'>Inactivo</div>") + "</td>");

                    out.print("<td class=\"text-center\">");
                    if (txtPermisos.contains("[14]")) {
                        out.print("<button type='button' onclick=\"javascript:location.href='Rol?opc=1&id_rol_permission=" + obj_rol[0] + "'\" class='btn btn-sm btn-info btn-icon' data-toggle='tooltip' data-placement='top' title='Asignar permisos'><i class='fas fa-shield-alt'></i></button>");
                    } else {
                        out.print("<a href='#' style='background: #00c396; opacity: 0.5;' class='btn btn-permission btn-icon' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-shield-alt'></i></a>");
                    }
                    out.print("</td>");

                    out.print("<td class=\"text-center\">");
                    if (txtPermisos.contains("[12]")) {
                        out.print("<button type='button' onclick=\"javascript:location.href='Rol?opc=1&id_rol=" + obj_rol[0] + "'\" class='btn btn-sm btn-warning btn-icon' data-toggle='tooltip' data-placement='top' title='Editar'><i class='fas fa-pencil-alt'></i></button>");
                    } else {
                        out.print("<a href='#' style='background: orange;opacity: 0.5;' class='btn btn-warning btn-icon' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-edit'></i></a> &nbsp;&nbsp;");
                    }
                    out.print("</td>");

                    out.print("<td class=\"text-center\">");
                    if (txtPermisos.contains("[13]")) {
                        out.print("<button type='button' onclick=\"javascript:location.href='Rol?opc=2&id_rol=" + obj_rol[0] + "&estado=" + estado + "'\" class='btn btn-sm btn-" + ((estado == 1) ? "success" : "danger") + "' data-toggle='tooltip' data-placement='top' title='Cambiar estado'><i class='" + ((estado == 1) ? "fas fa-check" : "fas fa-times") + "'></i></button>");
                    } else {
                        out.print("<a style='opacity: 0.5;' href='#' class='btn btn-" + ((estado == 1) ? "success" : "danger") + "' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='" + ((estado == 1) ? "fas fa-check-circle" : "fas fa-times-circle") + "'></i></a>");
                    }
                    out.print("</td>");

                    out.print("</tr>");
                }
            }
            out.print("</tbody>");
            out.print("</table>");
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
