package Tag;

import Controladores.RolJpaController;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Menu extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        RolJpaController jpacrol = new RolJpaController();
        List lst_roll = null;
        HttpSession sesion = pageContext.getSession();
        String UserName = pageContext.getSession().getAttribute("Nombres").toString();
        String Userrol = pageContext.getSession().getAttribute("Nombre_rol").toString();
        String usuario = sesion.getAttribute("Id_usuario").toString();
        int UserRolId = Integer.parseInt(pageContext.getSession().getAttribute("Id_rol").toString());
        String txtPermisos = "";

        try {
            lst_roll = jpacrol.Consult_role_id(UserRolId);
            Object[] obj_permi = (Object[]) lst_roll.get(0);
            txtPermisos = obj_permi[2].toString();
        } catch (Exception e) {
            UserRolId = 0;
            txtPermisos = "";
        }
        try {
            //<editor-fold defaultstate="collapsed" desc="NAV">
            out.print("<div class=\"navbar-bg\"></div>");
            out.print("<nav class=\"navbar navbar-expand-lg main-navbar\">\n"
                    + "        <form class=\"form-inline mr-auto\">\n"
                    + "          <ul class=\"navbar-nav mr-3\">\n"
                    + "            <li><a href=\"#\" data-toggle=\"sidebar\" class=\"nav-link nav-link-lg\" onclick='toggleLogoSize()'><i class=\"fas fa-bars\"></i></a></li>\n"
                    + "            <li><a href=\"#\" data-toggle=\"search\" class=\"nav-link nav-link-lg d-sm-none\"><i class=\"fas fa-search\"></i></a></li>\n"
                    + "          </ul>\n"
                    + "          <div class=\"search-element\">\n"
                    //                    + "            <input class=\"form-control\" type=\"search\" placeholder=\"Search\" aria-label=\"Search\" data-width=\"250\">\n"
                    //                    + "            <button class=\"btn\" type=\"submit\"><i class=\"fas fa-search\"></i></button>\n"
                    + "            <div class=\"search-backdrop\"></div>\n"
                    + "            <div class=\"search-result\">\n"
                    + "              <div class=\"search-header\">\n"
                    + "                Histories\n"
                    + "              </div>\n"
                    + "              <div class=\"search-item\">\n"
                    + "                <a href=\"#\">How to hack NASA using CSS</a>\n"
                    + "                <a href=\"#\" class=\"search-close\"><i class=\"fas fa-times\"></i></a>\n"
                    + "              </div>\n"
                    + "              <div class=\"search-item\">\n"
                    + "                <a href=\"#\">Kodinger.com</a>\n"
                    + "                <a href=\"#\" class=\"search-close\"><i class=\"fas fa-times\"></i></a>\n"
                    + "              </div>\n"
                    + "              <div class=\"search-item\">\n"
                    + "                <a href=\"#\">#Stisla</a>\n"
                    + "                <a href=\"#\" class=\"search-close\"><i class=\"fas fa-times\"></i></a>\n"
                    + "              </div>\n"
                    + "              <div class=\"search-header\">\n"
                    + "                Result\n"
                    + "              </div>\n"
                    + "              <div class=\"search-header\">\n"
                    + "                Projects\n"
                    + "              </div>\n"
                    + "              <div class=\"search-item\">\n"
                    + "                <a href=\"#\">\n"
                    + "                  <div class=\"search-icon bg-danger text-white mr-3\">\n"
                    + "                    <i class=\"fas fa-code\"></i>\n"
                    + "                  </div>\n"
                    + "                  Stisla Admin Template\n"
                    + "                </a>\n"
                    + "              </div>\n"
                    + "              <div class=\"search-item\">\n"
                    + "                <a href=\"#\">\n"
                    + "                  <div class=\"search-icon bg-primary text-white mr-3\">\n"
                    + "                    <i class=\"fas fa-laptop\"></i>\n"
                    + "                  </div>\n"
                    + "                  Create a new Homepage Design\n"
                    + "                </a>\n"
                    + "              </div>\n"
                    + "            </div>\n"
                    + "          </div>\n"
                    + "        </form>\n"
                    + "        <ul class=\"navbar-nav navbar-right\">\n"
                    + "          <li class=\"dropdown dropdown-list-toggle\"><a href=\"#\" data-toggle='dropdown' title='Reestablecer password' class=\"nav-link nav-link-lg message-toggle beep\"><i class='fas fa-key'></i></a>\n"
                    + "            <div class=\"dropdown-menu dropdown-list dropdown-menu-right\">\n"
                    + "              <div class=\"dropdown-header\" style='font-weight: 1000;'>Reestablecer Password!\n"
                    + "                <div class=\"float-right\">\n"
                    + "                  <a href=\"#\"></a>\n"
                    + "                </div>\n"
                    + "              </div>\n"
                    + "                <a href=\"#\" class=\"dropdown-item dropdown-item-unread\">\n"
                    + "                  <div class=\"dropdown-item-avatar\">\n"
                    + "                    <i class=\"fas fa-exclamation-circle\" style='font-size: 400%; color: orange;'></i>\n"
                    + "                  </div>\n"
                    + "                  <div class=\"dropdown-item-desc\">\n"
                    + "                    <p style='margin-top: 5%;'>Esta seguro de reestablecer su contraseña?</p>\n"
                    + "                  </div>\n"
                    + "                </a>\n"
                    + "              <div class=\"dropdown-footer text-center\">\n"
                    + "                <a href='#' class='btn btn-danger btn-sm btn-icon'>Cancelar</a>\n"
                    + "                <a href='#' onclick='RestablecerPassword(" + usuario + ")' class='btn btn-primary btn-sm btn-icon'>Confirmar</a>\n"
                    + "              </div>\n"
                    + "            </div>\n"
                    + "          </li>\n"
                    + "        <li class=\"dropdown dropdown-list-toggle\"><a href=\"#\" data-toggle='dropdown' title='Convenciones' class=\"nav-link notification-toggle nav-link-lg beep\"><i class='fas fa-info'></i></a>\n"
                    + "            <div class=\"dropdown-menu dropdown-list dropdown-menu-right\">\n"
                    + "              <div class=\"dropdown-header\">Convenciones\n"
                    + "              </div>\n"
                    + "              <div class=\"dropdown-list-content dropdown-list-icons\">\n"
                    + "                <a href=\"#\" class=\"dropdown-item\">\n"
                    + "                  <div class=\"dropdown-item-icon bg-success text-white\">\n"
                    + "                    <i class=\"fas fa-eye\"></i>\n"
                    + "                  </div>\n"
                    + "                  <div class=\"dropdown-item-desc\">\n"
                    + "                    <b>Ver R-GC-051, versión 008, a partir del 26 de agosto de 2024.</b>\n"
                    + "                  </div>\n"
                    + "                </a>\n"
                    + "                <a href=\"#\" class=\"dropdown-item dropdown-item-unread\">\n"
                    + "                  <div class=\"dropdown-item-icon bg-primary text-white\">\n"
                    + "                    <i class=\"fas fa-plus\"></i>\n"
                    + "                  </div>\n"
                    + "                  <div class=\"dropdown-item-desc\">\n"
                    + "                    <b>Crear un nuevo registro.</b>\n"
                    + "                  </div>\n"
                    + "                </a>\n"
                    + "                <a href=\"#\" class=\"dropdown-item\">\n"
                    + "                  <div class=\"dropdown-item-icon bg-success text-white\">\n"
                    + "                    <i class=\"fas fa-check\"></i>\n"
                    + "                  </div>\n"
                    + "                  <div class=\"dropdown-item-desc\">\n"
                    + "                    <b>Inactivar un registro.</b>\n"
                    + "                  </div>\n"
                    + "                </a>\n"
                    + "                <a href=\"#\" class=\"dropdown-item\">\n"
                    + "                  <div class=\"dropdown-item-icon bg-info text-white\">\n"
                    + "                    <i class=\"fas fa-pencil-alt\"></i>\n"
                    + "                  </div>\n"
                    + "                  <div class=\"dropdown-item-desc\">\n"
                    + "                    <b>Modificar un registro.</b>\n"
                    + "                  </div>\n"
                    + "                </a>\n"
                    + "                <a href=\"#\" class=\"dropdown-item\">\n"
                    + "                  <div class=\"dropdown-item-icon bg-danger text-white\">\n"
                    + "                    <i class=\"fas fa-times\"></i>\n"
                    + "                  </div>\n"
                    + "                  <div class=\"dropdown-item-desc\">\n"
                    + "                    <b>Activar un registro.</b>\n"
                    + "                  </div>\n"
                    + "                </a>\n"
                    + "                <a href=\"#\" class=\"dropdown-item\">\n"
                    + "                  <div class=\"dropdown-item-icon bg-info text-white\">\n"
                    + "                    <i class=\"fas fa-shield-alt\"></i>\n"
                    + "                  </div>\n"
                    + "                  <div class=\"dropdown-item-desc\">\n"
                    + "                    <b>Asignar permisos.</b>\n"
                    + "                  </div>\n"
                    + "                </a>\n"
                    + "                <a href=\"#\" class=\"dropdown-item\">\n"
                    + "                  <div class=\"dropdown-item-icon bg-primary text-white\">\n"
                    + "                    <i class=\"fas fa-user-edit\"></i>\n"
                    + "                  </div>\n"
                    + "                  <div class=\"dropdown-item-desc\">\n"
                    + "                    <b>Firmar registros masivamente.</b>\n"
                    + "                  </div>\n"
                    + "                </a>\n"
                    + "                <a href=\"#\" class=\"dropdown-item\">\n"
                    + "                  <div class=\"dropdown-item-icon bg-primary text-white\">\n"
                    + "                    <i class=\"fas fa-tasks\"></i>\n"
                    + "                  </div>\n"
                    + "                  <div class=\"dropdown-item-desc\">\n"
                    + "                    <b>Registros por firmar.</b>\n"
                    + "                  </div>\n"
                    + "                </a>\n"
                    + "                <a href=\"#\" class=\"dropdown-item\">\n"
                    + "                  <div class=\"dropdown-item-icon bg-primary text-white\">\n"
                    + "                    <i class=\"fas fa-info\"></i>\n"
                    + "                  </div>\n"
                    + "                  <div class=\"dropdown-item-desc\">\n"
                    + "                    <b>Convenciones.</b>\n"
                    + "                  </div>\n"
                    + "                </a>\n"
                    + "                <a href=\"#\" class=\"dropdown-item\">\n"
                    + "                  <div class=\"dropdown-item-icon bg-primary text-white\">\n"
                    + "                    <i class=\"fas fa-calendar\"></i>\n"
                    + "                  </div>\n"
                    + "                  <div class=\"dropdown-item-desc\">\n"
                    + "                    <b>Filtro por rango de fehas.</b>\n"
                    + "                  </div>\n"
                    + "                </a>\n"
                    + "                <a href=\"#\" class=\"dropdown-item\">\n"
                    + "                  <div class=\"dropdown-item-icon bg-dark text-white\">\n"
                    + "                    <i class=\"fas fa-paperclip\"></i>\n"
                    + "                  </div>\n"
                    + "                  <div class=\"dropdown-item-desc\">\n"
                    + "                    <b>Anexar documentos.</b>\n"
                    + "                  </div>\n"
                    + "                </a>\n"
                    + "                <a href=\"#\" class=\"dropdown-item\">\n"
                    + "                  <div class=\"dropdown-item-icon bg-info text-white\">\n"
                    + "                    <i class=\"fas fa-hands-helping\"></i>\n"
                    + "                  </div>\n"
                    + "                  <div class=\"dropdown-item-desc\">\n"
                    + "                    <b>Realizar un prestamo.</b>\n"
                    + "                  </div>\n"
                    + "                </a>\n"
                    + "                <a href=\"#\" class=\"dropdown-item\">\n"
                    + "                  <div class=\"dropdown-item-icon bg-info text-white\">\n"
                    + "                    <i class=\"fas fa-sticky-note\"></i>\n"
                    + "                  </div>\n"
                    + "                  <div class=\"dropdown-item-desc\">\n"
                    + "                    <b>Registrar una observacion.</b>\n"
                    + "                  </div>\n"
                    + "                </a>\n"
                    + "                <a href=\"#\" class=\"dropdown-item\">\n"
                    + "                  <div class=\"dropdown-item-icon bg-info text-white\">\n"
                    + "                    <i class=\"fas fa-trash-alt\"></i>\n"
                    + "                  </div>\n"
                    + "                  <div class=\"dropdown-item-desc\">\n"
                    + "                    <b>Eliminar un registro.</b>\n"
                    + "                  </div>\n"
                    + "                </a>\n"
                    + "                <a href=\"#\" class=\"dropdown-item\">\n"
                    + "                  <div class=\"dropdown-item-icon bg-success text-white\">\n"
                    + "                    <i class=\"fas fa-eye\"></i>\n"
                    + "                  </div>\n"
                    + "                  <div class=\"dropdown-item-desc\">\n"
                    + "                    <b>Ver R-GC-070</b>\n"
                    + "                  </div>\n"
                    + "                </a>\n"
                    + "                <a href=\"#\" class=\"dropdown-item\">\n"
                    + "                  <div class=\"dropdown-item-icon bg-warning text-white\">\n"
                    + "                    <i class=\"fas fa-user-lock\"></i>\n"
                    + "                  </div>\n"
                    + "                  <div class=\"dropdown-item-desc\">\n"
                    + "                    <b>Finalizar registros masivamente.</b>\n"
                    + "                  </div>\n"
                    + "                </a>\n"
                    + "                <a href=\"#\" class=\"dropdown-item\">\n"
                    + "                  <div class=\"dropdown-item-icon bg-info text-white\">\n"
                    + "                    <i class=\"fas fa-external-link-alt\"></i>\n"
                    + "                  </div>\n"
                    + "                  <div class=\"dropdown-item-desc\">\n"
                    + "                    <b>Enlace a los registros de control formulas.</b>\n"
                    + "                  </div>\n"
                    + "                </a>\n"
                    + "              </div>\n"
                    + "              <div class=\"dropdown-footer text-center\">\n"
                    + "                <p style='font-size: 13px; margin-top: 5px;'>Espero que te sea de mucha utilidad!</p>\n"
                    + "              </div>\n"
                    + "            </div>\n"
                    + "          </li>\n"
                    + "          <li class=\"dropdown\"><a href=\"#\" data-toggle=\"dropdown\" style='color:#00281b' class=\"nav-link dropdown-toggle nav-link-lg nav-link-user\">\n"
                    + "          <i class=\"fas fa-user-circle\"></i>"
                    + "            <div class=\"d-sm-none d-lg-inline-block\"><b style='color:#00281b' data-toggle='tooltip' data-placement='top' title='" + Userrol + "'>Hola, " + UserName + "</b></div></a>\n"
                    + "            <div class=\"dropdown-menu dropdown-menu-right\">\n"
                    + "              <div class=\"dropdown-title\">Rol: " + Userrol + "</div>\n"
                    + "              <a href=\"Support?opc=1\" class=\"dropdown-item has-icon\">\n"
                    + "                <i class=\"fas fa-wrench\"></i> Soporte\n"
                    + "              </a>\n"
                    + "              <div class=\"dropdown-divider\"></div>\n"
                    + "              <a href=\"index.jsp?logout=true&showAlert=true\" class=\"dropdown-item has-icon text-danger\">\n"
                    + "                <i class=\"fas fa-sign-out-alt\"></i> Salir\n"
                    + "              </a>\n"
                    + "            </div>\n"
                    + "          </li>\n"
                    + "        </ul>\n"
                    + "      </nav>"
            );
            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="MENU">
            out.print("<div  class=\"main-sidebar sidebar-style-2\"  tabindex=\"1\" overflow: hidden; outline: none;\">");
            out.print("<div style='height:90%;'>");
            out.print("<aside id=\"sidebar-wrapper\">");
            out.print("<div class=\"sidebar-brand\">");
            out.print("<a style='color:#fff' href=\"Inicio.jsp\">Generacion de Lotes</a>");
            out.print("</div>");
            out.print("<div class=\"sidebar-brand sidebar-brand-sm\">");
            out.print("<a style='color:#fff' href=\"Inicio.jsp\">GL</a>");
            out.print("</div>");

            out.print("<div class=\"mt-0 p-3 hide-sidebar-mini\">");
            out.print("<a style='color:black' href=\"Inicio.jsp\" class=\"btn btn-yellow btn-lg btn-block btn-icon-split\">");
            out.print("<i style='color:black' class=\"fas fa-home\"></i> Inicio");
            out.print("</a>");
            out.print("</div>");

            out.print("<ul class=\"sidebar-menu\">");
            out.print("</li>");
            out.print("<li class=\"menu-header\">Administracion</li>");
            out.print("<li class=\"dropdown\">");
            out.print("<a  href=\"#\" class=\"nav-link has-dropdown\" data-toggle=\"dropdown\"><i class=\"fas fa-users-cog\"></i> <span>Complementos</span></a>");
            out.print("<ul class=\"dropdown-menu\">");
            int cont = 0;
            if (txtPermisos.contains("[80]")) {
                out.print("<li><a class=\"nav-link\" href=\"Usuario?opc=1\"><i style='margin-right:4px' class=\"fas fa-users\"></i>Usuarios</a></li>");
            } else {
                cont++;
            }
            if (txtPermisos.contains("[81]")) {
                out.print("<li><a class=\"nav-link\" href=\"Procesos?opc=1\"><i style='margin-right:4px' class=\"fas fa-cogs\"></i>Procesos</a></li>");
            } else {
                cont++;
            }
            if (txtPermisos.contains("[82]")) {
                out.print("<li><a class=\"nav-link\" href=\"Permisos?opc=1\"><i style='margin-right:4px' class=\"fas fa-shield-alt\"></i>Permisos</a></li>");
            } else {
                cont++;
            }
            if (txtPermisos.contains("[83]")) {
                out.print("<li><a class=\"nav-link\" href=\"Rol?opc=1\"><i style='margin-right:4px' class=\"fas fa-id-card-alt\"></i>Roles</a></li>");
            } else {
                cont++;
            }
            if (cont >= 4) {
                out.print("<li><a class=\"nav-link\" href='#'><i style='margin-right:4px' class='fas fa-ban'></i>No tiene permisos</a></li>");
            }
            out.print("</ul>");
            out.print("</li>");
            out.print("<li class=\"menu-header\">Materia Prima</li>");
            out.print("<li class=\"dropdown\">");
            out.print("<a  href=\"#\" class=\"nav-link has-dropdown\" data-toggle=\"dropdown\"><i class=\"fas fa-dolly-flatbed\"></i> <span>Recepcion Material</span></a>");
            out.print("<ul class=\"dropdown-menu\">");
            if (txtPermisos.contains("[84]")) {
                out.print("<li><a class=\"nav-link\" href='RecepcionMaterial?opc=1'><i style='margin-right:4px' class=\"fas fa-people-carry\"></i><span>Recepcion Material</span></a></li>");
            } else {
                out.print("<li><a class=\"nav-link\" href='#'><i style='margin-right:4px' class='fas fa-ban'></i>No tiene permisos</a></li>");
            }
            if (txtPermisos.contains("[85]")) {
                out.print("<li><a class=\"nav-link\" href='RecepcionMaterial?opc=11'><i style='margin-right:4px' class=\"fas fa-file-alt\"></i><span>Maestro Recepcion</span></a></li>");
            } else {
                out.print("<li><a class=\"nav-link\" href='#'><i style='margin-right:4px' class='fas fa-ban'></i>No tiene permisos</a></li>");
            }
            out.print("</ul>");
            out.print("</li>");
            out.print("</li>");
            out.print("<li class=\"menu-header\">Generacion de Lotes</li>");
            out.print("<li class=\"dropdown\">");
            if (txtPermisos.contains("[87]")) {
                out.print("<a href=\"ControlConsecutivos?opc=1\" class=\"nav-link\"><i class=\"fas fa-boxes\"></i> <span>Control Consecutivos</span></a>");
            } else {
                out.print("<li><a class=\"nav-link\" href='#'><i style='margin-right:4px' class='fas fa-ban'></i>No tiene permisos</a></li>");
            }
            if (txtPermisos.contains("[86]")) {
                out.print("<a href=\"in?opc=1\" class=\"nav-link\"><i class=\"fab fa-hubspot\"></i> <span>Iny/Ext</span></a>");
            } else {
                out.print("<li><a class=\"nav-link\" href='#'><i style='margin-right:4px' class='fas fa-ban'></i>No tiene permisos</a></li>");
            }
            out.print("</li>");
            out.print("</ul>");
            out.print("</aside>");
            out.print("</div>");
            out.print("<div style='text-align: center;\n"
                    + "    margin-bottom: 0px; height:70px;\n"
                    + "    background-color: #34ace0;\n"
                    + "'>"
                    + "<div class='divLogo'><a href=\"Inicio.jsp\"><img class='img_logo' src='Interfaz/Contenido/images/Generacion_lotes3.png' alt='Logo'></a></div></div>");
            out.print("</div>");
//</editor-fold>
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.doStartTag();
    }
}
