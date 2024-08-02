package Tag;

import Controladores.RolJpaController;
import Controladores.UsuarioJpaController;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Usuario extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        RolJpaController jpacrol = new RolJpaController();
        UsuarioJpaController jpacusa = new UsuarioJpaController();
        List lst_usuarios = null;
        List lst_roles = null;
        List lst_rol_id = null;
        int Id_usuario = 0;
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
            //<editor-fold defaultstate="collapsed" desc="TABLA">
            try {
                Id_usuario = Integer.parseInt(pageContext.getRequest().getAttribute("Id_usuario").toString());
            } catch (NumberFormatException e) {
                Id_usuario = 0;
            }
            out.print("<section class='section'>");
            out.print("<div class='section-header'>");
            out.print("<h1>Modulo Usuarios</h1>");
            out.print("</div>");
            out.print("<div class='section-body'>");
            out.print("<div class='row'>");
            out.print("<div class='col-12'>");
            out.print("<div class='card'>");
            out.print("<div class='card-header' style='justify-content: space-between;'>");
            out.print("<h4>Tabla de Usuarios</h4>");
            if (txtPermisos.contains("[1]")) {
                out.print("<button class='btn btn-green' style='border-radius: 4px;' onclick='mostrarConvencion(1)' data-toggle='tooltip' data-placement='top' title='Registrar Usuario'><i class='fas fa-plus'></i></button>");
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
            out.print("<th>Documento</th>");
            out.print("<th>Código</th>");
            out.print("<th>Usuario</th>");
            out.print("<th>Rol</th>");
            out.print("<th>Estado</th>");
            out.print("<th>Modificar</th>");
            out.print("<th>Restablecer</th>");
            out.print("</tr>");
            out.print("</thead>");
            out.print("<tbody>");
            lst_usuarios = jpacusa.Usuarios();
            if (lst_usuarios != null) {
                for (int i = 0; i < lst_usuarios.size(); i++) {
                    Object[] obj_usuarios = (Object[]) lst_usuarios.get(i);
                    if (Integer.parseInt(obj_usuarios[6].toString()) == 1) {
                        out.print("<tr>");
                        out.print("<td>" + obj_usuarios[1] + "</td>");
                        out.print("<td>" + obj_usuarios[2] + "</td>");
                        out.print("<td>" + obj_usuarios[3] + "</td>");
                        out.print("<td>" + obj_usuarios[4] + "</td>");
                        out.print("<td>" + obj_usuarios[8] + "</td>");
                        out.print("<td align='center'>");
                        if (txtPermisos.contains("[3]")) {
                            out.print("<a href='#' onclick='DesactivarUsuario(" + obj_usuarios[0] + ")' class='btn btn-success btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Desactivar Usuario'><i class='fas fa-check'></i></a>");
                        } else {
                            out.print("<button class='btn btn-green btn-sm' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-check'></i></i></button>");
                        }
                        out.print("</td>");
                        out.print("<td align='center'>");
                        if (txtPermisos.contains("[2]")) {
                            out.print("<a href='Usuario?opc=1&Id_usuario=" + obj_usuarios[0] + "' class='btn btn-primary btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Modificar Usuario'><i class='fas fa-pencil-alt'></i></a>");
                        } else {
                            out.print("<button class='btn btn-green btn-sm' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-pencil-alt'></i></i></button>");
                        }
                        out.print("</td>");
                        out.print("<td align='center'>");
                        if (txtPermisos.contains("[4]")) {
                            out.print("<a href='#' onclick='RestablecerPassword(" + obj_usuarios[0] + ")' class='btn btn-warning btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Restablecer Password'><i class='fas fa-key'></i></a>");
                        } else {
                            out.print("<button class='btn btn-green btn-sm' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-key'></i></i></button>");
                        }
                        out.print("</td>");
                        out.print("</tr>");
                    } else {
                        out.print("<tr class='rojo'>");
                        out.print("<td>" + obj_usuarios[1] + "</td>");
                        out.print("<td>" + obj_usuarios[2] + "</td>");
                        out.print("<td>" + obj_usuarios[3] + "</td>");
                        out.print("<td>" + obj_usuarios[4] + "</td>");
                        out.print("<td>" + obj_usuarios[8] + "</td>");
                        out.print("<td align='center'>");
                        if (txtPermisos.contains("[91]")) {
                            out.print("<a href='#' onclick='ActivarUsuario(" + obj_usuarios[0] + ")' class='btn btn-danger btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Activar Usuario'><i class='fas fa-times'></i></a>");
                        } else {
                            out.print("<button class='btn btn-green btn-sm' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-times'></i></button>");
                        }
                        out.print("</td>");
                        out.print("<td align='center'>");
                        out.print("<a href='#' class='btn btn-secondary btn-sm btn-icon' style='cursor: not-allowed;' data-toggle='tooltip' data-placement='top'><i class='fas fa-pencil-alt'></i></a>");
                        out.print("</td>");
                        out.print("<td align='center'>");
                        out.print("<a href='#' class='btn btn-secondary btn-sm btn-icon' style='cursor: not-allowed;' data-toggle='tooltip' data-placement='top'><i class='fas fa-key'></i></a>");
                        out.print("</td>");
                        out.print("</tr>");
                    }
                }
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
            //<editor-fold defaultstate="collapsed" desc="MODIFICAR USUARIO">
            if (Id_usuario > 0) {
                lst_usuarios = jpacusa.Traer_usuario(Id_usuario);
                if (lst_usuarios != null) {
                    Object[] obj_usuarios = (Object[]) lst_usuarios.get(0);
                    out.print("<div class='sweet-local' tabindex='-1' id='Ventana2' style='opacity: 1.03; display:block;'>");
                    out.print("<div class='cont_reg'>");
                    out.print("<div style='display: flex; justify-content: space-between'>");
                    out.print("<h2>Editar Usuario " + obj_usuarios[1].toString() + "</h2>");
                    out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(2)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
                    out.print("</div>");
                    out.print("<div class='cont_form_user'>");
                    out.print("<form action='Usuario?opc=4&Id_usuario=" + obj_usuarios[0] + "' method='post' class='needs-validation' novalidate=''>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='Txt_nombre' placeholder='Nombre' value='" + obj_usuarios[1] + "' required='' data-toggle='tooltip' data-placement='top' title='Nombre'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='Txt_apellido' placeholder='Apellido' value='" + obj_usuarios[2] + "' required='' data-toggle='tooltip' data-placement='top' title='Apellido'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");
                    out.print("</div>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-12'>");
                    out.print("<input type='number' class='form-control' name='Txt_documento' placeholder='Documento' value='" + obj_usuarios[3] + "' required data-toggle='tooltip' data-placement='top' title='Documento'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='col-12'>");
                    out.print("<input type='number' class='form-control' name='Txt_codigo' placeholder='Codigo' value='" + obj_usuarios[4] + "' required data-toggle='tooltip' data-placement='top' title='Codigo'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");
                    out.print("</div>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='Txt_usuario' placeholder='Usuario' value='" + obj_usuarios[5] + "' required data-toggle='tooltip' data-placement='top' title='Usuario'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-left:3%; margin-right:-3%; text-align:left;'>");
                    out.print("<select class='select2' name='Cbx_rol'>");
                    lst_roles = jpacrol.lista_Roles();
                    if (lst_roles != null || lst_roles.size() != 0) {
                        for (int i = 0; i < lst_roles.size(); i++) {
                            Object[] obj_rol = (Object[]) lst_roles.get(i);
                            String selected = obj_rol[0].toString().equals(obj_usuarios[8].toString()) ? "selected" : "";
                            out.print("<option value='" + obj_rol[0] + "' " + selected + ">" + obj_rol[1] + "</option>");
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
                }
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="REGISTRAR USUARIO">
            out.print("<div class='sweet-local' tabindex='-1' id='Ventana1' style='opacity: 1.03; display:none;'>");
            out.print("<div class='cont_reg'>");
            out.print("<div style='display: flex; justify-content: space-between'>");
            out.print("<h2>Registrar Usuario</h2>");
            out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(1)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
            out.print("</div>");
            out.print("<div class='cont_form_user'>");
            out.print("<form action='Usuario?opc=2' method='post' class='needs-validation' novalidate=''>");

            out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
            out.print("<div class='col-12'>");
            out.print("<input type='text' class='form-control' name='Txt_nombre' placeholder='Nombre' required='' data-toggle='tooltip' data-placemente='top' title='Nombre'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("<div class='col-12'>");
            out.print("<input type='text' class='form-control' name='Txt_apellido' placeholder='Apellido' required='' data-toggle='tooltip' data-placemente='top' title='Apellido'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
            out.print("<div class='col-12'>");
            out.print("<input type='number' class='form-control' name='Txt_documento' placeholder='Documento' required data-toggle='tooltip' data-placemente='top' title='Documento'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("<div class='col-12'>");
            out.print("<input type='number' class='form-control' name='Txt_codigo' placeholder='Codigo' required data-toggle='tooltip' data-placemente='top' title='Codigo'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='col-lg-6 col-md-6' style='text-align: center; display: flex;'>");
            out.print("<div class='col-12'>");
            out.print("<input type='text' class='form-control' name='Txt_usuario' placeholder='Usuario' required='' data-toggle='tooltip' data-placemente='top' title='Usuario'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");

            lst_roles = jpacrol.lista_Roles();
            if (lst_roles != null) {
                out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-left:3%; margin-right:-3%; text-align:left;'>");
                out.print("<select class='select2' name='Cbx_rol' data-toggle='tooltip' data-placement='top'>");
                out.print("<option value='empty'>Seleccione un Rol</option>");
                for (int i = 0; i < lst_roles.size(); i++) {
                    Object[] obj_rol = (Object[]) lst_roles.get(i);
                    out.print("<option value='" + obj_rol[0] + "'>" + obj_rol[1] + "</option>");
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
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.doStartTag();
    }
}
