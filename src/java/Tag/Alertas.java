package Tag;

import Controladores.UsuarioJpaController;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Alertas extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        UsuarioJpaController jpacusa = new UsuarioJpaController();
        List lst_tiempo_restante = null;
//        int id_usuario = 0;
        int Id_usuario = 0;
        try {
            //<editor-fold defaultstate="collapsed" desc="PASSWORD">
            if (pageContext.getRequest().getAttribute("Cambio_contraseña") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("Cambio_contraseña").toString());
                String id_usuario = "";
                try {
                    id_usuario = pageContext.getRequest().getAttribute("idUsuario").toString();
                } catch (Exception e) {
                    id_usuario = "";
                }
                out.print("<link rel=\"stylesheet\" href=\"Interfaz/Contenido/Validacion/StyleSheetLiveValidation.css\">");
                out.print("<script src=\"Interfaz/Contenido/Validacion/LiveValidation.js\"></script>");
                out.print("<div class='sweet-local' style='opacity: 1.03; display: flex; margin:auto;'>");
                out.print("<fieldset class='cont_reg'>");
                out.print("<div>");

                out.print("<div class='form_pass' style='justify-content: space-between;'>");

                out.print("<div>");
                out.print("<h2>Cambiar Contraseña </h2>");
                out.print("</div>");
                out.print("<div>");
                out.print("<a href='index.jsp' class='btn btn-outline-secondary' style='height: 31px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></a>");
                out.print("</div>");

                out.print("</div>");

                out.print("<div>");
                out.print("</div>");
                out.print("<p style='text-align: center;'>Recordar que la protección de datos, usuario y contraseña, ayuda a evitar fraudes o alteraciones en la Organización (Plastitec) y en este Aplicativo.</p>");
                out.print("<div style='width:100%' class='camb_body'>");
                out.print("<form action='Sesion?opc=3' method='post'>");
                out.print("<div class='form_pass'>");
                out.print("<input type='hidden' name='Id_usuario' id='usuario' value='" + id_usuario + "'>");
                out.print("<input class='form-control' type='password' id='pass-input' placeholder='Nueva Contraseña' style='margin-right: 4%;'>");
                out.print("<script>");
                out.print("var validatedObj = new LiveValidation('pass-input');");
                out.print("validatedObj.add(Validate.Password);");
                out.print("validatedObj.add(Validate.Presence);");
                out.print("</script>");
                out.print("<input class='form-control' type='password' name='Txt_password' id='confpass-input' placeholder='Confirmar Contraseña' >");
                out.print("<script>");
                out.print("var validatedObj = new LiveValidation('confpass-input');");
                out.print("validatedObj.add(Validate.Password);");
                out.print("validatedObj.add(Validate.Confirmation, { match: 'pass-input' });");
                out.print("</script>");
                out.print("</div>");
                out.print("<div style='display: flex; width: 100%; margin-top: 15px;'>");
                out.print("<div style='width: 72%;margin-left: 3%; text-align: initial;'>");
                out.print("<label style='color:#00281b'>El cambio de Contraseña debe contener:<br />"
                        + "                        -Minimo 8 caracteres<br/>"
                        + "                        -Maximo 15 caracteres<br/>"
                        + "                        -Al menos una letra mayúscula<br/>"
                        + "                        -Al menos una letra minúscula<br/>"
                        + "                        -Al menos un dígito ( Numero )<br/>"
                        + "                        -No espacios en blanco<br/>"
                        + "                        -Al menos 1 caracter especial ( $@$!%*?&#- )</label>");
                out.print("</div>");
                out.print("<div style='float:right;'><img src='Interfaz/Contenido/images/spy.gif' alt='Logo' width='200' height='150' style='margin-right: 40px;' /></div>");
                out.print("</div>");
                out.print("<div style='text-align:center;'>");
                out.print("<button class='btn' style=\"box-shadow: 1px 2px 5px 0px #959595;\">Cambiar</button>");
                out.print("</div>");
                out.print("</div>");
                out.print("</form>");
                out.print("</fieldset>");
                out.print("</div>");
                out.print("</div>");
            }
            if (pageContext.getRequest().getAttribute("Password_actualizado") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("Password_actualizado").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'Se ha actualizado la contraseña.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problmea en la actualizacion.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="USUARIO">
            if (pageContext.getRequest().getAttribute("User_register") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("User_register").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: '¡El usuario se ha registrado correctamente!',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("User_update") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("User_update").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'Se ha registrado la información correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("Usuario_no_existe") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("Usuario_no_existe").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'El usuario o contraseña estan erroneas .',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
//            if (pageContext.getRequest().getAttribute("Usuario_no_existe") != null) {
//                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("Usuario_no_existe").toString());
//                int intentosRestantes = Integer.parseInt(pageContext.getRequest().getAttribute("intentosRestantes").toString());
//                out.print("<script type='text/javascript'>");
//                out.print("$(\"#toastr-4\").ready(function() {\n"
//                        + "  iziToast.warning({\n"
//                        + "    title: 'Precaucion',\n"
//                        + "    message: '¡El usuario ingresado no existe! Te quedan " + intentosRestantes + " intentos.',\n"
//                        + "    position: 'bottomRight'\n"
//                        + "  });\n"
//                        + "});");
//                out.print("</script>");
//            }
//            try {
//                Id_usuario = (int) pageContext.getRequest().getAttribute("idUsuario");
//            } catch (Exception e) {
//                Id_usuario = 0;
//            }
//
//            lst_tiempo_restante = jpacusa.CalcularTiempoBloqueoUsuario(Id_usuario);
//            String tiempoRestanteStr = "0";
//            if (lst_tiempo_restante != null && !lst_tiempo_restante.isEmpty()) {
//                Object[] obj_usuarios = (Object[]) lst_tiempo_restante.get(0);
//                if (obj_usuarios.length == 2) {
//                    if (obj_usuarios[0] != null) {
//                        tiempoRestanteStr = obj_usuarios[0].toString();
//                    }
//                }
//            }
//
//            if (pageContext.getRequest().getAttribute("Usuario_bloqueado") != null && !tiempoRestanteStr.equals("0")) {
//                out.print("<script type='text/javascript'>");
//                out.print("$(document).ready(function() {\n"
//                        + "  var tiempoRestante = parseInt('" + tiempoRestanteStr + "', 10);\n"
//                        + "  var timerInterval;\n"
//                        + "  Swal.fire({\n"
//                        + "    icon: 'error',\n"
//                        + "    iconHtml: '<i class=\"fas fa-lock swal2-custom-icon\"></i>',\n"
//                        + "    title: '<div class=\"swal2-custom-title\">Lo sentimos...</div>',\n"
//                        + "    html: '<div class=\"swal2-custom-content\"><p style=\"font-size:14px;\">Tu usuario ha sido bloqueado temporalmente! Tiempo restante: <b>' + tiempoRestante + '</b> segundos.</p></div>',\n"
//                        + "    showConfirmButton: false,\n"
//                        + "    allowOutsideClick: true,\n"
//                        + "    timerProgressBar: true,\n"
//                        + "    customClass: {\n"
//                        + "      popup: 'swal2-custom-popup',\n"
//                        + "    },\n"
//                        + "    didOpen: () => {\n"
//                        + "      Swal.showLoading();\n"
//                        + "      timerInterval = setInterval(() => {\n"
//                        + "        tiempoRestante--;\n"
//                        + "        Swal.getHtmlContainer().querySelector('b').textContent = tiempoRestante;\n"
//                        + "        if (tiempoRestante <= 0) {\n"
//                        + "          clearInterval(timerInterval);\n"
//                        + "          Swal.fire({\n"
//                        + "            title: 'Desbloqueo completado',\n"
//                        + "            text: 'Tu usuario ahora está desbloqueado.',\n"
//                        + "            icon: 'success',\n"
//                        + "            showConfirmButton: true,\n"
//                        + "          });\n"
//                        + "        }\n"
//                        + "      }, 1000);\n"
//                        + "    },\n"
//                        + "    willClose: () => {\n"
//                        + "      clearInterval(timerInterval);\n"
//                        + "    }\n"
//                        + "  });\n"
//                        + "});\n");
//                out.print("</script>");
//            }

            if (pageContext.getRequest().getAttribute("Usuario_desactivado") != null) {
                boolean result = false;
                try {
                    result = Boolean.valueOf(pageContext.getRequest().getAttribute("Usuario_desactivado").toString());
                } catch (Exception e) {
                    result = false;
                }
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'El usuario se inactivo correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Fallo por favor comunicarse con T.I.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("Usuario_activado") != null) {
                boolean result = false;
                try {
                    result = Boolean.valueOf(pageContext.getRequest().getAttribute("Usuario_activado").toString());
                } catch (Exception e) {
                    result = false;
                }
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'El usuario se activo correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Fallo por favor comunicarse con T.I.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="PROCESO">
            if (pageContext.getRequest().getAttribute("Proceso_registro") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("Proceso_registro").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: '¡El proceso se ha registrado correctamente!',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("Proceso_activo") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("Proceso_activo").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-3\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'El proceso se ha activado correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("Proceso_inactivo") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("Proceso_inactivo").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-3\").ready(function() {\n"
                            + "  iziToast.warning({\n"
                            + "    title: 'Atención',\n"
                            + "    message: 'El proceso se ha desactivado correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("proceso_modificado") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("proceso_modificado").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'Se ha modificado la información correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="PERMISOS">
            if (pageContext.getRequest().getAttribute("estado_permiso") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("estado_permiso").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'El estado del permiso se cambio correctamnete.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problmea en la actualizacion.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("registro_permiso") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("registro_permiso").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'El permiso se registro correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problmea en la actualizacion.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("modificar_permiso") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("modificar_permiso").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'El permiso se modifico correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problmea en la actualizacion.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="ROLES">
            if (pageContext.getRequest().getAttribute("estado_rol") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("estado_rol").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'El estado del rol se cambio correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problmea en la actualizacion.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("registrar_rol") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("registrar_rol").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'El rol se registro correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problmea en la actualizacion.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("modificar_rol") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("modificar_rol").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'El rol se modifico correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problmea en la actualizacion.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("actualizar_permisos") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("actualizar_permisos").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'Los permisos se actualizaron correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problmea en la actualizacion.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="INYECCION">
            if (pageContext.getRequest().getAttribute("Registrar_Inyeccion") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("Registrar_Inyeccion").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'Se ha registrado una nueva inyeccion.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("Modificar_Inyeccion") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("Modificar_Inyeccion").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'Se ha modificado la inyeccion.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("prestamo_inyeccion") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("prestamo_inyeccion").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'Se ha hecho el prestamo correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("devolucion_inyeccion") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("devolucion_inyeccion").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'Se ha hecho la devolucion correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("finalizar_Inyeccion") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("finalizar_Inyeccion").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-3\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'La inyeccion se ha finalizado correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("Estado_Inyeccion_Masivo") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("Estado_Inyeccion_Masivo").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-3\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'El estado de las inyecciones ha sido cambiado.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("eliminar_registro_inyeccion") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("eliminar_registro_inyeccion").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'El registro se ha eliminado correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("inactivar_masivo") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("inactivar_masivo").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'La(s) inyeccion(es) se han finalizado correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="ESTADO CONTRAMUESTRA">
            if (pageContext.getRequest().getAttribute("Estado_actualizado") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("Estado_actualizado").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'Se especifico el estado de la contramuestra.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problmea en la actualizacion.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="C.CONSECUTIVO">
            if (pageContext.getRequest().getAttribute("Registrar_Cc") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("Registrar_Cc").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'Se ha registrado el C.consecutivo correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("Modificar_Cc") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("Modificar_Cc").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'Se ha modificado el consecutivo.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("Estado_Cc") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("Estado_Cc").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-3\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'El lote se ha finalizado correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("Entregado_Cc") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("Entregado_Cc").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'Se actualizo el lote correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("eliminar_registro_consecutivo") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("eliminar_registro_consecutivo").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'El registro se ha eliminado correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("finalizar_masivo_consecutivo") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("finalizar_masivo_consecutivo").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'los consecutivos se han finalizado correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="ANEXOS C.CONSECUTIVO">
            if (pageContext.getRequest().getAttribute("Registrar_Anexo") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("Registrar_Anexo").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'Se ha cargado el anexo correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("Modificar_Anexo") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("Modificar_Anexo").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'Se ha cargado el nuevo anexo correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="RECEPCION MATERIAL">
            if (pageContext.getRequest().getAttribute("Registrar_Recepcion") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("Registrar_Recepcion").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'La recepcion se ha registrado correcatamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("Modificar_Recepcion") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("Modificar_Recepcion").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'Se ha modificado la recepcion correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("Actualizar_Verificacion") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("Actualizar_Verificacion").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'Se ha modificado la verificacion correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("Actualizar_Control_Verificacion") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("Actualizar_Control_Verificacion").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'Se ha modificado el resumen de la verificacion correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("Actualizar_Firmas") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("Actualizar_Firmas").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'Se ha registrado la firma correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("Actualizar_Firmas_Masivo") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("Actualizar_Firmas_Masivo").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'Se ha registrado la(s) firma(s) correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("eliminar_registro_recepcion") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("eliminar_registro_recepcion").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'El registro se ha eliminado correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("eliminar_registro_recepcion_denegado") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("eliminar_registro_recepcion_denegado").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Debe ingresar una justificaion para que se elimine la recepcion.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("registrar_observacion") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("registrar_observacion").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'Se ha registrado la observacion correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("registrar_prestamo") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("registrar_prestamo").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'Se ha registrado el prestamo correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="SOPERTE">
            if (pageContext.getRequest().getAttribute("RegisterCase") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("RegisterCase").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-2\").ready(function() {\n"
                            + "  iziToast.success({\n"
                            + "    title: 'Correcto',\n"
                            + "    message: 'Se ha registrado el caso correctamente.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("$(\"#toastr-4\").ready(function() {\n"
                            + "  iziToast.error({\n"
                            + "    title: 'Error',\n"
                            + "    message: 'Ha ocurrido un problema en el registro.',\n"
                            + "    position: 'bottomRight'\n"
                            + "  });\n"
                            + "});");
                    out.print("</script>");
                }
            }
//</editor-fold>
        } catch (IOException ex) {
            Logger.getLogger(Alertas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.doStartTag();
    }
}
