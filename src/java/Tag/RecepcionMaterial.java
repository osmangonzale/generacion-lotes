package Tag;

import Controladores.RecepcionMaterialJpaController;
import Controladores.RolJpaController;
import Factory.ReferenciasInv;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class RecepcionMaterial extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            // <editor-fold defaultstate="collapsed" desc="JPAS">
            RecepcionMaterialJpaController jpac_recepcion = new RecepcionMaterialJpaController();
            RolJpaController jpacrol = new RolJpaController();
            ReferenciasInv refinv = new ReferenciasInv();
            HttpSession sesion = pageContext.getSession();
            String rol_usuario = sesion.getAttribute("Rol/Nombres").toString();
            String rol = (String) sesion.getAttribute("Rol");
            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="VARIABLES">
            List Lista_recepcion;
            int idRegistro, fase, temp, opc, tempF, id_order, tempp, tempG = 0;
            int id_add_anexo = 0;
            int id_anexos = 0;
            String[] rango = null;
            String parametro = null;
            String range1 = null;
            String range2 = null;
            String codigo, referencia = "";
            List lst_rol_id = null;
            int id_rol, id_rol_permission, idRol, cantidad1 = 0;
            String txtPermisos = "";
            List lst_um, lst_cat, lst_tip, anexoNew, lst_rgc, lst_pro, lst_recep, lst_mr, lst_MR = null;
            try {
                opc = Integer.parseInt(pageContext.getRequest().getAttribute("opc").toString());
            } catch (NumberFormatException e) {
                opc = 0;
            }
            try {
                id_add_anexo = Integer.parseInt(pageContext.getRequest().getAttribute("id_add_anexo").toString());
            } catch (Exception e) {
                id_add_anexo = 0;
            }
            try {
                id_anexos = Integer.parseInt(pageContext.getRequest().getAttribute("id_m_anexo").toString());
            } catch (Exception e) {
                id_anexos = 0;
            }
            try {
                parametro = pageContext.getRequest().getAttribute("parametro").toString();
            } catch (Exception e) {
                parametro = null;
            }
            try {
                rol_usuario = pageContext.getRequest().getAttribute("Rol/Nombres").toString();
            } catch (Exception e) {
                rol_usuario = null;
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
            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="VARIABLES DE CONTEXTO">
            try {
                codigo = pageContext.getRequest().getAttribute("codigo").toString();
            } catch (Exception e) {
                codigo = "";
            }
            try {
                referencia = pageContext.getRequest().getAttribute("referencia").toString();
            } catch (Exception e) {
                referencia = "";
            }
            try {
                idRegistro = Integer.parseInt(pageContext.getRequest().getAttribute("idRegistro").toString());
            } catch (NumberFormatException e) {
                idRegistro = 0;
            }
            try {
                temp = Integer.parseInt(pageContext.getRequest().getAttribute("temp").toString());
            } catch (NumberFormatException e) {
                temp = 0;
            }
            try {
                tempF = Integer.parseInt(pageContext.getRequest().getAttribute("tempF").toString());
            } catch (NumberFormatException e) {
                tempF = 0;
            }
            try {
                tempp = Integer.parseInt(pageContext.getRequest().getAttribute("tempp").toString());
            } catch (NumberFormatException e) {
                tempp = 0;
            }
            try {
                id_order = Integer.parseInt(pageContext.getRequest().getAttribute("id_order").toString());
            } catch (NumberFormatException e) {
                id_order = 0;
            }
            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="MODIFICAR RECEPCION">
            if (temp == 0) {
                if (idRegistro > 0) {
                    lst_recep = jpac_recepcion.Lista_Recepcion(idRegistro);
                    if (lst_recep != null) {
                        Object[] obj_recepcion = (Object[]) lst_recep.get(0);
                        out.print(
                                "<div class='sweet-local' tabindex='-1' id='Ventana2' style='opacity: 1.03; display: block;'>");
                        out.print("<div class='cont_reg2' style='width: 40%'>");
                        out.print("<div style='display: flex; justify-content: space-between'>");
                        out.print("<h2>Modificar Recepcion</h2>");
                        out.print(
                                "<button class='btn btn-outline-secondary' onclick='mostrarConvencion(2)' style='height: 30px; padding: 3px; width: 30px;'><i class='fas fa-times'></i></button>");
                        out.print("</div>");
                        out.print("<div class='cont_form_user'>");
                        out.print("<form action='RecepcionMaterial?opc=3&idRegistro=" + obj_recepcion[0]
                                + "' method='post' class='needs-validation' novalidate>");

                        out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                        out.print("<div class='col-12'>");
                        out.print("<input type='text' class='form-control' name='codigo' value='" + obj_recepcion[2]
                                + "' placeholder='Codigo Factory' disabled data-toggle='tooltip' data-placement='top' title='codigo'>");
                        out.print(
                                "<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                        out.print("</div>");

                        out.print("<div class='col-12'>");
                        out.print(
                                "<input type='text' class='form-control' name='referencia' placeholder='Apellido' value='"
                                        + obj_recepcion[3]
                                        + "' disabled data-toggle='tooltip' data-placement='top' title='Apellido'>");
                        out.print(
                                "<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                        out.print("</div>");
                        out.print("</div>");

                        out.print("<input type='hidden' name='id_iny' id='idRegistro' value='" + idRegistro + "'>");

                        out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                        out.print("<div class='col-12'>");
                        out.print("<input type='date' class='form-control' name='fecha' value='" + obj_recepcion[10]
                                + "' data-toggle='tooltip' data-placement='top' title='Fecha'>");
                        out.print(
                                "<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                        out.print("</div>");

                        out.print("<div class='col-12'>");
                        out.print(
                                "<input type='time' class='form-control' name='llegada' placeholder='Apellido' value='"
                                        + obj_recepcion[11]
                                        + "' data-toggle='tooltip' data-placement='top' title='Llegada'>");
                        out.print(
                                "<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                        out.print("</div>");
                        out.print("</div>");

                        out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                        out.print("<div class='col-12'>");
                        out.print("<input type='time' class='form-control' name='descargue' value='" + obj_recepcion[12]
                                + "' data-toggle='tooltip' data-placement='top' title='Descargue'>");
                        out.print(
                                "<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                        out.print("</div>");

                        out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-right:-3%;'>");
                        out.print("<select class='form-control' name='clasificacion'>");
                        out.print("<option value='" + obj_recepcion[39] + "'>" + obj_recepcion[1].toString()
                                + "</option>");
                        lst_recep = jpac_recepcion.ConsultarParametros_xCategoria("categoria");
                        if (lst_recep != null && !lst_recep.isEmpty()) {
                            for (int i = 0; i < lst_recep.size(); i++) {
                                Object[] param = (Object[]) lst_recep.get(i);
                                if (!param[3].equals(obj_recepcion[1])) {
                                    out.print("<option value='" + param[2] + "'>" + param[3] + "</option>");
                                }
                            }
                        } else {
                            out.print("<option value='0'>Se ha producido un error</option>");
                        }
                        out.print("</select>");
                        out.print("</div>");
                        out.print("</div>");

                        out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                        out.print("<div class='col-12'>");
                        out.print("<input type='text' class='form-control' name='lote' value='" + obj_recepcion[4]
                                + "' data-toggle='tooltip' data-placement='top' title='Lote'>");
                        out.print(
                                "<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                        out.print("</div>");

                        out.print("<div class='col-12'>");
                        out.print(
                                "<input type='text' class='form-control' name='consecutivo' placeholder='Apellido' value='"
                                        + obj_recepcion[5]
                                        + "' data-toggle='tooltip' data-placement='top' title='Consecutivo'>");
                        out.print(
                                "<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                        out.print("</div>");
                        out.print("</div>");

                        out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                        out.print("<div class='col-lg-12'>");
                        out.print(
                                "<input list='proveedores' placeholder='Seleccione Proveedor o Ingrese Uno' class='form-control' name='proveedor' data-toggle='tooltip' data-placement='top' tittle='Nombre Proveedor' value='"
                                        + obj_recepcion[9] + "' required>");
                        out.print("<datalist id='proveedores'>");
                        out.print("<option value='" + obj_recepcion[9] + "' selected>" + obj_recepcion[9].toString()
                                + "</option>");
                        String[] arg_provedor = referencia.split("/");
                        try {
                            lst_pro = jpac_recepcion.Lista_Proveedores(arg_provedor[0].trim());
                            if (lst_pro != null && lst_pro.size() > 0) {
                                for (int i = 0; i < lst_pro.size(); i++) {
                                    Object[] obj_pro = (Object[]) lst_pro.get(i);
                                    if (!obj_pro[1].equals("") || !obj_pro[1].toString().isEmpty()) {
                                        out.print("<option value='" + obj_pro[1] + "'>" + obj_pro[1] + "</option>");
                                    }
                                }
                            }
                        } catch (IOException e) {
                            out.print("<option value='0'>Error al obtener proveedores</option>");
                        }
                        out.print("</datalist>");
                        out.print("</div>");

                        out.print("<div class='col-lg-12'>");
                        out.print(
                                "<input list='referencia' placeholder='Seleccione Referencia o Ingrese Una' class='form-control' name='refproveedor' data-toggle='tooltip' data-placement='top' tittle='Referencia Proveedor' value='"
                                        + obj_recepcion[28] + "' required>");
                        out.print("<datalist id='referencia'>");
                        out.print("<option value='" + obj_recepcion[28] + "' selected>" + obj_recepcion[28].toString()
                                + "</option>");
                        try {
                            lst_pro = jpac_recepcion.Lista_ref_Proveedores(arg_provedor[0].trim());
                            if (lst_pro != null && lst_pro.size() > 0) {
                                for (int i = 0; i < lst_pro.size(); i++) {
                                    Object[] obj_pro = (Object[]) lst_pro.get(i);
                                    if (!obj_pro[1].equals("") || !obj_pro[1].toString().isEmpty()) {
                                        out.print("<option value='" + obj_pro[1] + "'>" + obj_pro[1] + "</option>");
                                    }
                                }
                            } else {
                                out.print("<option value='0'>Se ha producido un error</option>");
                            }
                        } catch (IOException e) {
                            out.print("<option value='0'>Error al obtener proveedores</option>");
                        }

                        out.print("</datalist>");
                        out.print("</div>");
                        out.print("</div>");

                        if (obj_recepcion[6].toString().contains("x")) {
                            String cadena = obj_recepcion[6].toString();
                            String[] partes = cadena.replace(" x ", "///").replace(" ", "///").split("///");
                            double cantidadRemisionada = Double.parseDouble(obj_recepcion[7].toString());
                            String cantidadFormateada;
                            if (cantidadRemisionada % 1 == 0) {
                                cantidadFormateada = String.valueOf((int) cantidadRemisionada);
                            } else {
                                cantidadFormateada = String.format("%.2f", cantidadRemisionada);
                            }
                            out.print(
                                    "<label id='labelResultado' for='Cantidad' style='margin-bottom: 1px; margin-left: 230px; margin-top: 10px;'>Cantidad Total Remisionada: '"
                                            + cantidadFormateada + "'</label>");
                            out.print("<input type='hidden' name='cantidad' id='canres'>");
                            out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                            out.print("<div class='col-5'>");
                            out.print(
                                    "<input type='number' class='form-control' name='cantidad1' id='cantidad1' value='"
                                            + partes[0]
                                            + "' placeholder='Cantidad' onchange='multiplicarCampos()' required data-toggle='tooltip' data-placemente='top' title='Cantidad'>");
                            out.print(
                                    "<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                            out.print("</div>");

                            out.print(
                                    "<div class='col-lg-7' style='margin-top:4%; margin-left:3%; margin-right:-3%;'>");
                            out.print(
                                    "<select class='select2' name='unidad1' data-toggle='tooltip' data-placement='top'>");
                            out.print("<option value='empty'>Unidad de medida</option>");
                            lst_um = jpac_recepcion.ConsultarParametros_xUnidad("unidadMedida");
                            if (lst_um != null || lst_um.size() != 0) {
                                for (int i = 0; i < lst_um.size(); i++) {
                                    Object[] obj_unidad = (Object[]) lst_um.get(i);
                                    out.print("<option value='" + obj_unidad[3] + "' "
                                            + ((obj_unidad[3].equals(partes[1])) ? "selected" : "") + ">"
                                            + obj_unidad[3] + "</option>");
                                }
                            } else {
                                out.print("<option value='0'>Se ha producido un error</option>");
                            }
                            out.print("</select>");
                            out.print("</div>");

                            out.print("<div class='col-5' style='position: relative;'>");
                            out.print("<input type='number' class='form-control' name='cantidad2' value='" + partes[2]
                                    + "' id='cantidad2' placeholder='Cantidad' onchange='multiplicarCampos()' required data-toggle='tooltip' data-placemente='top' title='Cantidad'>");
                            out.print(
                                    "<span style='color:red; font-size: 40px; position: absolute; top: 13px; left: 3px;'>*</span>");
                            out.print(
                                    "<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                            out.print("</div>");

                            out.print(
                                    "<div class='col-lg-7' style='margin-top:4%; margin-left:3%; margin-right:-3%;'>");
                            out.print(
                                    "<select class='select2' name='unidad2' data-toggle='tooltip' data-placement='top'>");
                            out.print("<option value='empty'>Unidad de medida</option>");
                            lst_um = jpac_recepcion.ConsultarParametros_xUnidad("unidadMedida");
                            if (lst_um != null || lst_um.size() != 0) {
                                for (int i = 0; i < lst_um.size(); i++) {
                                    Object[] obj_unidad = (Object[]) lst_um.get(i);
                                    out.print("<option value='" + obj_unidad[3] + "' "
                                            + ((obj_unidad[3].equals(partes[3])) ? "selected" : "") + ">"
                                            + obj_unidad[3] + "</option>");
                                }
                            } else {
                                out.print("<option value='0'>Se ha producido un error</option>");
                            }
                            out.print("</select>");
                        }
                        out.print("</div>");
                        out.print("</div>");

                        out.print("<div class='' style='width: 100%; text-align:center;'>");
                        out.print(
                                "<button type='submit' class='btn btn-green btn-lg' onclick='multiplicarCampos()'>Editar</button>");
                        out.print("</div>");

                        out.print("</form>");
                        out.print("</div>");
                        out.print("</div>");
                        out.print("</div>");
                    }
                }
            }
            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="REGISTRAR RECEPCION">
            out.print("<div class='sweet-local' tabindex='-1' id='Ventana1' style='opacity: 1.03; display:"
                    + ((!codigo.equals("")) ? "block" : "none") + ";'>");
            out.print("<div class='cont_reg2' style='width: 40%'>");
            out.print("<div style='display: flex; justify-content: space-between'>");
            out.print("<h2>Ingresar Codigo</h2>");
            out.print(
                    "<button class='btn btn-outline-secondary' onclick='mostrarConvencion(1)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
            out.print("</div>");
            out.print("<div class='cont_form_user'>");
            out.print(
                    "<form action='RecepcionMaterial?opc=1' method='post' class='needs-validation' novalidate='' id='formcodigo'>");

            out.print("<div class='col-12'>");
            out.print(
                    "<input type='text' class='form-control' name='codigo' id='codigo' placeholder='Codigo Factory' value='"
                            + codigo
                            + "' onsearch='enviar()' required='' data-toggle='tooltip' data-placemente='top' title='codigo'>");
            out.print(
                    "<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");

            if (!codigo.equals("")) {
                out.print(
                        "<div class='col-lg-12' style='margin-top:2.9%; margin-left:1.5%; margin-right:-203%; margin-bottom: 1.8%;'>");
                out.print(
                        "<select class='select2' name='referencia' onchange='enviar()' data-toggle='tooltip' data-placement='top' title='Seleccione una referencia'>");
                List lst_factory = refinv.Productos(codigo);
                if (lst_factory != null) {
                    out.print("<option value=''>Seleccione una referencia</option>");
                    for (int b = 0; b < lst_factory.size(); b++) {
                        String optionValue = (String) lst_factory.get(b);
                        String selected = referencia.equals(optionValue) ? "selected" : "";
                        out.print("<option value='" + optionValue + "' " + selected + ">" + optionValue + "</option>");
                    }
                }
                out.print("</select>");
                out.print("</div>");
            }
            out.print("</form>");

            if (!codigo.equals("") && !referencia.equals("")) {
                out.print(
                        "<form action='RecepcionMaterial?opc=2' method='post' class='needs-validation' novalidate='' id='formcodigo'>");
                out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                out.print("<div class='col-12'>");
                out.print(
                        "<input type='date' class='form-control' name='fecha' placeholder='Fecha' onchange='javascript:this.value=this.value.toUpperCase();' required data-toggle='tooltip' data-placemente='top' title='Fecha'>");
                out.print(
                        "<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                out.print("</div>");

                out.print("<input type='hidden' name='referencia' value='" + referencia + "'>");

                out.print("<div class='col-12'>");
                out.print(
                        "<input type='time' class='form-control' name='llegada' placeholder='Llegada Vehiculo' required='' data-toggle='tooltip' data-placemente='top' title='Llegada Vehiculo'>");
                out.print(
                        "<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                out.print("</div>");
                out.print("</div>");

                out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                out.print("<div class='col-12'>");
                out.print(
                        "<input type='time' class='form-control' name='descargue' placeholder='Inicio descargue' required data-toggle='tooltip' data-placemente='top' title='Inicio descargue:'>");
                out.print(
                        "<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                out.print("</div>");

                out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-left:3%; margin-right:-3%;'>");
                out.print(
                        "<select class='select2 form-control' name='clasificacion' data-toggle='tooltip' data-placement='top' required>");
                out.print("<option value='' disabled selected>Seleccione Clasificación</option>");
                lst_cat = jpac_recepcion.ConsultarParametros_xCategoria("categoria");
                if (lst_cat != null && lst_cat.size() != 0) {
                    for (int i = 0; i < lst_cat.size(); i++) {
                        Object[] obj_clasificacion = (Object[]) lst_cat.get(i);
                        out.print("<option value='" + obj_clasificacion[2] + "'>" + obj_clasificacion[3] + "</option>");
                    }
                } else {
                    out.print("<option value='0'>Se ha producido un error</option>");
                }
                out.print("</select>");
                out.print(
                        "<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                out.print("</div>");
                out.print("</div>");

                out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                out.print(
                        "<div class='col-lg-12' style='margin-top:2.9%; margin-bottom: 2.9%; margin-left:3%; margin-right:-3%; margin-:2.9%;'>");
                out.print(
                        "<select class='select2 form-control' name='tipo' data-toggle='tooltip' data-placement='top' required>");
                out.print("<option value='' disabled selected>Seleccione Tipo</option>");
                lst_tip = jpac_recepcion.ConsultarParametros_xTipo("tipo");
                if (lst_tip != null || lst_tip.size() != 0) {
                    for (int i = 0; i < lst_tip.size(); i++) {
                        Object[] obj_tipo = (Object[]) lst_tip.get(i);
                        out.print("<option value='" + obj_tipo[2] + "'>" + obj_tipo[3] + "</option>");
                    }
                } else {
                    out.print("<option value='0'>Se ha producido un error</option>");
                }
                out.print("</select>");
                out.print(
                        "<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                out.print("</div>");

                out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-left:3%; margin-right:-3%;'>");
                out.print(
                        "<select class='select2 form-control' name='rgc' data-toggle='tooltip' data-placement='top' required>");
                out.print("<option value='' disabled selected>Aplica R-GC-070</option>");
                lst_rgc = jpac_recepcion.ConsultarParametros_xRgc("rgc");
                if (lst_rgc != null || lst_rgc.size() != 0) {
                    for (int i = 0; i < lst_rgc.size(); i++) {
                        Object[] obj_rgc = (Object[]) lst_rgc.get(i);
                        out.print("<option value='" + obj_rgc[2] + "'>" + obj_rgc[3] + "</option>");
                    }
                } else {
                    out.print("<option value='0'>Se ha producido un error</option>");
                }
                out.print("</select>");
                out.print(
                        "<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                out.print("</div>");
                out.print("</div>");

                out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                out.print("<div class='col-12'>");
                out.print(
                        "<input type='text' class='form-control' name='lote' placeholder='Lote' onchange='obtenerConsecutivo()' onkeyup='javascript:this.value=this.value.toUpperCase();' required data-toggle='tooltip' data-placemente='top' title='Lote'>");
                out.print(
                        "<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                out.print("</div>");

                out.print("<div class='col-12'>");
                out.print(
                        "<input type='number' class='form-control' name='consecutivo' placeholder='Consecutivo' required='' data-toggle='tooltip' data-placemente='top' title='Consecutivo'>");
                out.print(
                        "<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                out.print("</div>");
                out.print("</div>");

                out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                out.print("<div class='col-lg-12'>");
                out.print(
                        "<input list='proveedores' placeholder='Seleccione Proveedor o Ingrese Uno' class='form-control' name='proveedor' data-toggle='tooltip' data-placement='top' tittle='Nombre Proveedor' required>");
                out.print("<datalist id='proveedores'>");
                String[] arg_provedor = referencia.split("/");
                try {
                    lst_pro = jpac_recepcion.Lista_Proveedores(arg_provedor[0].trim());
                    if (lst_pro != null && lst_pro.size() > 0) {
                        for (int i = 0; i < lst_pro.size(); i++) {
                            Object[] obj_pro = (Object[]) lst_pro.get(i);
                            if (!obj_pro[1].equals("") || !obj_pro[1].toString().isEmpty()) {
                                out.print("<option value='" + obj_pro[1] + "'>" + obj_pro[1] + "</option>");
                            }
                        }
                    }
                } catch (IOException e) {
                    out.print("<option value='0'>Error al obtener proveedores</option>");
                }
                out.print("</datalist>");
                out.print(
                        "<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                out.print("</div>");

                out.print("<div class='col-lg-12'>");
                out.print(
                        "<input list='referencia' placeholder='Seleccione Referencia o Ingrese Uno' class='form-control' name='refproveedor' data-toggle='tooltip' data-placement='top' tittle='Referencia Proveedor' required>");
                out.print("<datalist id='referencia'>");
                try {
                    lst_pro = jpac_recepcion.Lista_ref_Proveedores(arg_provedor[0].trim());
                    if (lst_pro != null && lst_pro.size() > 0) {
                        for (int i = 0; i < lst_pro.size(); i++) {
                            Object[] obj_pro = (Object[]) lst_pro.get(i);
                            if (!obj_pro[1].equals("") || !obj_pro[1].toString().isEmpty()) {
                                out.print("<option value='" + obj_pro[1] + "'>" + obj_pro[1] + "</option>");
                            }
                        }
                    } else {
                        out.print("<option value='0'>Se ha producido un error</option>");
                    }
                } catch (IOException e) {
                    out.print("<option value='0'>Error al obtener proveedores</option>");
                }
                out.print("</datalist>");
                out.print(
                        "<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                out.print("</div>");
                out.print("</div>");

                out.print(
                        "<label id='labelResultado' for='Cantidad' style='margin-bottom: 1px; margin-left: 230px; margin-top: 10px;'>Cantidad Total Remisionada:</label>");
                out.print("<input type='hidden' name='cantidad' id='canres'>");
                out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                out.print("<div class='col-5'>");
                out.print(
                        "<input type='number' class='form-control' name='cantidad1' id='cantidad1' placeholder='Cantidad' onchange='multiplicarCampos()' required data-toggle='tooltip' data-placemente='top' title='Cantidad'>");
                out.print(
                        "<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                out.print("</div>");

                out.print("<div class='col-lg-7' style='margin-top:4%; margin-left:3%; margin-right:-3%;'>");
                out.print(
                        "<select class='select2 form-control' name='unidad1' data-toggle='tooltip' data-placement='top' required>");
                out.print("<option value='' disabled selected>Unidad de medida</option>");
                lst_um = jpac_recepcion.ConsultarParametros_xUnidad("unidadMedida");
                if (lst_um != null || lst_um.size() != 0) {
                    for (int i = 0; i < lst_um.size(); i++) {
                        Object[] obj_unidad = (Object[]) lst_um.get(i);
                        out.print("<option value='" + obj_unidad[3] + "'>" + obj_unidad[3] + "</option>");
                    }
                } else {
                    out.print("<option value='0'>Se ha producido un error</option>");
                }
                out.print("</select>");
                out.print(
                        "<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                out.print("</div>");

                out.print("<div class='col-5' style='position: relative;'>");
                out.print(
                        "<input type='number' class='form-control' name='cantidad2' id='cantidad2' placeholder='Cantidad' onchange='multiplicarCampos()' required data-toggle='tooltip' data-placemente='top' title='Cantidad'>");
                out.print(
                        "<span style='color:red; font-size: 40px; position: absolute; top: 13px; left: 3px;'>*</span>");
                out.print(
                        "<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                out.print("</div>");

                out.print("<div class='col-lg-7' style='margin-top:4%; margin-left:3%; margin-right:-3%;'>");
                out.print(
                        "<select class='select2 form-control' name='unidad2' data-toggle='tooltip' data-placement='top' required>");
                out.print("<option value='' disabled selected>Unidad de medida</option>");
                lst_um = jpac_recepcion.ConsultarParametros_xUnidad("unidadMedida");
                if (lst_um != null || lst_um.size() != 0) {
                    for (int i = 0; i < lst_um.size(); i++) {
                        Object[] obj_unidad = (Object[]) lst_um.get(i);
                        out.print("<option value='" + obj_unidad[3] + "'>" + obj_unidad[3] + "</option>");
                    }
                } else {
                    out.print("<option value='0'>Se ha producido un error</option>");
                }
                out.print("</select>");
                out.print(
                        "<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                out.print("</div>");
                out.print("</div>");

                out.print("<div class='' style='width: 100%; text-align:center;'>");
                out.print("<button class='btn btn-green btn-lg'>Registrar</button>");
                out.print("</div>");

            }
            out.print("</form>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");

            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="MODAL FASES">
            if (temp == 1) {
                if (idRegistro > 0) {
                    lst_recep = jpac_recepcion.Lista_Recepcion(idRegistro);
                    if (lst_recep != null) {
                        Object[] obj_recepcion = (Object[]) lst_recep.get(0);
                        out.print(
                                "<div class='sweet-local' tabindex='-1' id='Ventana3' style='opacity: 1.03; display: block;'>");
                        out.print("<div class='cont_reg4'>");
                        out.print("<div style='display: flex; justify-content: space-between'>");
                        out.print("<h2>FASES</h2>");
                        out.print(
                                "<button class='btn btn-outline-secondary' onclick='mostrarConvencion(3)' style='height: 30px; padding: 3px; width: 30px;'><i class='fas fa-times'></i></button>");
                        out.print("</div>");
                        out.print("<div class='cont_form_user'>");
                        out.print("<section class='section'>");
                        out.print("<div class='section-body'>");

                        out.print("<div class='card card card-info'>");
                        out.print("<div class='card-header'>");
                        out.print("<h4>Verificacion</h4>");
                        out.print("<div class='card-header-action'>");
                        out.print(
                                "<a data-collapse='#mycard-collapse1' class='btn btn-icon btn-info' href='#'><i class='fas fa-minus'></i></a>");
                        out.print("</div>");
                        out.print("</div>");
                        if (obj_recepcion[26] != null && (obj_recepcion[26].equals(2) || obj_recepcion[26].equals(3))) {
                            out.print("<div class='collapse' id='mycard-collapse1' style=''>");
                        } else {
                            out.print("<div class='collapse show' id='mycard-collapse1' style=''>");
                        }
                        out.print("<div class='card-body'>");

                        String[] dataArr = {};
                        if (obj_recepcion[14] != null) {
                            out.print("<form id='RegistrarM' action='RecepcionMaterial?opc=7&idRegistro="
                                    + obj_recepcion[0] + "' method='post' novalidate=''>");
                            out.print("<table class='table table-sm table-hover table-bordered'>");
                            // <editor-fold defaultstate="collapsed" desc="MODIFICAR">
                            out.print("<thead>");
                            out.print("<tr>");
                            out.print("<th style='width: auto; text-align: center'>Verificacion</th>");
                            out.print("<th style='width: 90px !important; text-align: center'>SI</th>");
                            out.print("<th style='width: 90px !important; text-align: center'>NO</th>");
                            out.print("<th style='width: 231px !important; text-align: center'>Observaciones</th>");
                            out.print("</tr>");
                            out.print("</thead>");
                            out.print("<tbody style='font-size: 11px;'>");
                            dataArr = obj_recepcion[14].toString().replace("],[", "///").replace("[", "")
                                    .replace("]", "").split("///");

                            String[] Certi = {};
                            Certi = dataArr[0].toString().split(",");
                            if ((Integer) obj_recepcion[26] != 4) {
                                out.print("<tr>");
                                out.print("<td>SE ANEXA CERTIFICADO DE CALIDAD</td>");
                                out.print("<td align='center'>");
                                out.print("<input type='radio' name='radio1' value='1' "
                                        + ((Integer.parseInt(Certi[1].toString()) == 1) ? "checked" : "") + ">");
                                out.print("</td>");
                                out.print("<td align='center'>");
                                out.print("<input type='radio' name='radio1' value='2' "
                                        + ((Integer.parseInt(Certi[1].toString()) == 2) ? "checked" : "") + ">");
                                out.print("</td>");
                                out.print(
                                        "<td align='center'><input type='text' name='obs1' style='width: 100px; border: none;' value='"
                                                + Certi[2]
                                                + "' placeholder='N/A' data-toggle='tooltip' data-placement='top' title='Observacion'></td>");
                                out.print("</tr>");
                            }

                            Certi = dataArr[1].toString().split(",");
                            if ((Integer) obj_recepcion[26] != 4) {
                                out.print("<tr>");
                                out.print("<td>LA CANTIDAD RECIBIDA ES LA REMISIONADA</td>");
                                out.print("<td align='center'>");
                                out.print("<input type='radio' name='radio2' value='1' "
                                        + ((Integer.parseInt(Certi[1].toString()) == 1) ? "checked" : "") + ">");
                                out.print("</td>");
                                out.print("<td align='center'>");
                                out.print("<input type='radio' name='radio2' value='2' "
                                        + ((Integer.parseInt(Certi[1].toString()) == 2) ? "checked" : "") + ">");
                                out.print("</td>");
                                out.print(
                                        "<td align='center'><input type='text' name='obs2' style='width: 100px; border: none;' value='"
                                                + Certi[2]
                                                + "' placeholder='N/A' data-toggle='tooltip' data-placement='top' title='Observacion'></td>");
                                out.print("</tr>");
                            }

                            Certi = dataArr[2].toString().split(",");
                            if ((Integer) obj_recepcion[26] != 4) {
                                out.print("<tr>");
                                out.print("<td>LA IDENTIFICACION DEL EMPAQUE ES ADECUADA</td>");
                                out.print("<td align='center'>");
                                out.print("<input type='radio' name='radio3' value='1' "
                                        + ((Integer.parseInt(Certi[1].toString()) == 1) ? "checked" : "") + ">");
                                out.print("</td>");
                                out.print("<td align='center'>");
                                out.print("<input type='radio' name='radio3' value='2' "
                                        + ((Integer.parseInt(Certi[1].toString()) == 2) ? "checked" : "") + ">");
                                out.print("</td>");
                                out.print(
                                        "<td align='center'><input type='text' name='obs3' style='width: 100px; border: none;' value='"
                                                + Certi[2]
                                                + "' placeholder='N/A' data-toggle='tooltip' data-placement='top' title='Observacion'></td>");
                                out.print("</tr>");
                            }

                            Certi = dataArr[3].toString().split(",");
                            if ((Integer) obj_recepcion[26] != 4) {
                                out.print("<tr>");
                                out.print("<td>EL MATERIAL RECEPCIONADO COINCIDE CON LA REQUISICION DE COMPRA</td>");
                                out.print("<td align='center'>");
                                out.print("<input type='radio' name='radio4' value='1' "
                                        + ((Integer.parseInt(Certi[1].toString()) == 1) ? "checked" : "") + ">");
                                out.print("</td>");
                                out.print("<td align='center'>");
                                out.print("<input type='radio' name='radio4' value='2' "
                                        + ((Integer.parseInt(Certi[1].toString()) == 2) ? "checked" : "") + ">");
                                out.print("</td>");
                                out.print(
                                        "<td align='center'><input type='text' name='obs4' style='width: 100px; border: none;' value='"
                                                + Certi[2]
                                                + "' placeholder='N/A' data-toggle='tooltip' data-placement='top' title='Observacion'></td>");
                                out.print("</tr>");
                            }

                            Certi = dataArr[4].toString().split(",");
                            if ((Integer) obj_recepcion[26] != 4) {
                                out.print("<tr>");
                                out.print("<td>LAS CONDICIONES DE TRANSPORTE Y DESCARGUE SON ACEPTABLES</td>");
                                out.print("<td align='center'>");
                                out.print("<input type='radio' name='radio5' value='1' "
                                        + ((Integer.parseInt(Certi[1].toString()) == 1) ? "checked" : "") + ">");
                                out.print("</td>");
                                out.print("<td align='center'>");
                                out.print("<input type='radio' name='radio5' value='2' "
                                        + ((Integer.parseInt(Certi[1].toString()) == 2) ? "checked" : "") + ">");
                                out.print("</td>");
                                out.print(
                                        "<td align='center'><input type='text' name='obs5' style='width: 100px; border: none;' value='"
                                                + Certi[2]
                                                + "' placeholder='N/A' data-toggle='tooltip' data-placement='top' title='Observacion'></td>");
                                out.print("</tr>");
                            }

                            Certi = dataArr[5].toString().split(",");
                            if ((Integer) obj_recepcion[26] != 4) {
                                out.print("<tr>");
                                out.print("<td>EL PESO TOTAL REMISIONADO COINCIDE CON EL RECEPCIONADO</td>");
                                out.print("<td align='center'>");
                                out.print("<input type='radio' name='radio6' value='1' "
                                        + ((Integer.parseInt(Certi[1].toString()) == 1) ? "checked" : "") + ">");
                                out.print("</td>");
                                out.print("<td align='center'>");
                                out.print("<input type='radio' name='radio6' value='2' "
                                        + ((Integer.parseInt(Certi[1].toString()) == 2) ? "checked" : "") + ">");
                                out.print("</td>");
                                out.print(
                                        "<td align='center'><input type='text' name='obs6' style='width: 100px; border: none;' value='"
                                                + Certi[2]
                                                + "' placeholder='N/A' data-toggle='tooltip' data-placement='top' title='Observacion'></td>");
                                out.print("</tr>");
                            }

                            Certi = dataArr[6].toString().split(",");
                            if ((Integer) obj_recepcion[26] != 4) {
                                out.print("<tr>");
                                out.print("<td>LA IDENTIFICACION DEL MATERIAL CONTIENE ADEMAS EL NUMERO DE LOTE</td>");
                                out.print("<td align='center'>");
                                out.print("<input type='radio' name='radio7' value='1' "
                                        + ((Integer.parseInt(Certi[1].toString()) == 1) ? "checked" : "") + ">");
                                out.print("</td>");
                                out.print("<td align='center'>");
                                out.print("<input type='radio' name='radio7' value='2' "
                                        + ((Integer.parseInt(Certi[1].toString()) == 2) ? "checked" : "") + ">");
                                out.print("</td>");
                                out.print(
                                        "<td align='center'><input type='text' name='obs7' style='width: 100px; border: none;' value='"
                                                + Certi[2]
                                                + "' placeholder='N/A' data-toggle='tooltip' data-placement='top' title='Observacion'></td>");
                                out.print("</tr>");
                            }

                            Certi = dataArr[7].toString().split(",");
                            String reempaque = Certi[2].toString().trim();
                            boolean reempaqueNo = reempaque.equals("REEMPAQUE: NO");
                            boolean reempaqueSi = reempaque.equals("REEMPAQUE: SI");

                            if ((Integer) obj_recepcion[26] != 4) {
                                out.print("<tr>");
                                out.print("<td>EL MATERIAL SE ENCUENTRA EN EL EMPAQUE ORIGINAL</td>");
                                out.print("<td align='center'>");
                                out.print("<input type='radio' name='radio8' value='1' "
                                        + ((Integer.parseInt(Certi[1].toString()) == 1) ? "checked" : "") + ">");
                                out.print("</td>");
                                out.print("<td align='center'>");
                                out.print("<input type='radio' name='radio8' value='2' "
                                        + ((Integer.parseInt(Certi[1].toString()) == 2) ? "checked" : "") + ">");
                                out.print("</td>");
                                out.print("<td align='center'>");
                                if (reempaqueSi || reempaqueNo) {
                                    out.print(
                                            "<label>Reempaque</label> <label>SI</label><input type='radio' name='radio12' value='1' "
                                                    + (reempaqueSi ? "checked" : "") + ">");
                                    out.print("<label>NO</label><input type='radio' name='radio12' value='2' "
                                            + (reempaqueNo ? "checked" : "") + ">");
                                } else {
                                    out.print(
                                            "<label>Reempaque</label> <label>SI</label><input type='radio' name='radio12' value='1' "
                                                    + ((Integer.parseInt(Certi[2].toString()) == 1) ? "checked" : "")
                                                    + ">");
                                    out.print("<label>NO</label><input type='radio' name='radio12' value='2' "
                                            + ((Integer.parseInt(Certi[2].toString()) == 2) ? "checked" : "") + ">");
                                }
                                out.print("</td>");
                                out.print("</tr>");
                            }

                            Certi = dataArr[8].toString().split(",");
                            if ((Integer) obj_recepcion[26] != 4) {
                                out.print("<tr>");
                                out.print("<td>NOVEDADES (OLOR NO CARACTERISTICO, PRESENCIA DE MATERIAL EXTRAÑO)</td>");
                                out.print("<td align='center'>");
                                out.print("<input type='radio' name='radio9' value='1' "
                                        + ((Integer.parseInt(Certi[1].toString()) == 1) ? "checked" : "") + ">");
                                out.print("</td>");
                                out.print("<td align='center'>");
                                out.print("<input type='radio' name='radio9' value='2' "
                                        + ((Integer.parseInt(Certi[1].toString()) == 2) ? "checked" : "") + ">");
                                out.print("</td>");
                                out.print(
                                        "<td align='center'><input type='text' name='obs9' style='width: 100px; border: none;' value='"
                                                + Certi[2]
                                                + "' placeholder='N/A' data-toggle='tooltip' data-placement='top' title='Observacion'></td>");
                                out.print("</tr>");
                            }

                            Certi = dataArr[9].toString().split(",");
                            if ((Integer) obj_recepcion[26] != 4) {
                                out.print("<tr>");
                                out.print("<td>SOBRANTES</td>");
                                out.print("<td align='center'>");
                                out.print("<input type='radio' name='radio10' value='1' "
                                        + ((Integer.parseInt(Certi[1].toString()) == 1) ? "checked" : "") + ">");
                                out.print("</td>");
                                out.print("<td align='center'>");
                                out.print("<input type='radio' name='radio10' value='2' "
                                        + ((Integer.parseInt(Certi[1].toString()) == 2) ? "checked" : "") + ">");
                                out.print("</td>");
                                out.print(
                                        "<td align='center'><input type='text' name='obs10' style='width: 100px; border: none;' value='"
                                                + Certi[2]
                                                + "' placeholder='N/A' data-toggle='tooltip' data-placement='top' title='Observacion'></td>");
                                out.print("</tr>");
                            }

                            Certi = dataArr[10].toString().split(",");
                            if ((Integer) obj_recepcion[26] != 4) {
                                out.print("<tr>");
                                out.print("<td>FALTANTES</td>");
                                out.print("<td align='center'>");
                                out.print("<input type='radio' name='radio11' value='1' "
                                        + ((Integer.parseInt(Certi[1].toString()) == 1) ? "checked" : "") + ">");
                                out.print("</td>");
                                out.print("<td align='center'>");
                                out.print("<input type='radio' name='radio11' value='2' "
                                        + ((Integer.parseInt(Certi[1].toString()) == 2) ? "checked" : "") + ">");
                                out.print("</td>");
                                out.print(
                                        "<td align='center'><input type='text' name='obs11' style='width: 100px; border: none;' value='"
                                                + Certi[2]
                                                + "' placeholder='N/A' data-toggle='tooltip' data-placement='top' title='Observacion'></td>");
                                out.print("</tr>");
                            }

                            if (dataArr.length > 11) {
                                Certi = dataArr[11].toString().split(",");
                                idRegistro = (Integer) obj_recepcion[0];
                                if (idRegistro >= 4677 && (Integer) obj_recepcion[26] != 4) {
                                    out.print("<tr>");
                                    out.print("<td>INDICE DE REFRACCION (APLICA PLASTIFICANTE)</td>");
                                    out.print("<td align='center'>");
                                    out.print(
                                            "<input type='radio' name='radio13' id='radio13_1M' onchange=\"cambioradioM()\" value='1' "
                                                    + ((Integer.parseInt(Certi[1].toString()) == 1) ? "checked" : "")
                                                    + ">");
                                    out.print("</td>");
                                    out.print("<td align='center'>");
                                    out.print(
                                            "<input type='radio' name='radio13' id='radio13_1M' onchange=\"cambioradioM()\" value='2' "
                                                    + ((Integer.parseInt(Certi[1].toString()) == 2) ? "checked" : "")
                                                    + ">");
                                    out.print("</td>");
                                    out.print("<td align='center'>");
                                    out.print("<label>Valor:</label>");
                                    out.print(
                                            "<input class='form-control' type='text' id='obser12' name='obs12' value='"
                                                    + Certi[2]
                                                    + "'placeholder='Valor' data-toggle='tooltip' data-placement='top' title='Solo se permiten números y puntos.' required pattern='^[0-9.]+$' style='display: initial; width: initial; height: initial; padding: initial; font-size: initial; line-height: initial; color: initial; background-color: initial; background-clip: initial; border-radius: initial; transition: initial;'>");
                                    out.print("<label>Cumple certificado calidad</label><br>");
                                    out.print(
                                            "<label>SI</label><input type='radio' name='radio14' id='radio14_1M' value='1' "
                                                    + ((Integer.parseInt(Certi[3].toString()) == 1) ? "checked" : "")
                                                    + ">");
                                    out.print(
                                            "<label>NO</label><input type='radio' name='radio14' id='radio14_2M' value='2' "
                                                    + ((Integer.parseInt(Certi[3].toString()) == 2) ? "checked" : "")
                                                    + ">");
                                    out.print("</td>");
                                    out.print("</tr>");
                                }
                            }

                            out.print("</tbody>");
                            // </editor-fold>
                            out.print("</form>");
                            out.print("</table>");
                            out.print("<div class='' style='width: 100%; text-align:center;'>");
                            out.print(
                                    "<button type='submit' class='btn btn-green btn-lg' onclick='return validateFormM(\"RegistrarM\")'>Enviar</button>");
                            out.print("</div>");
                        } else {
                            out.print("<form id='RegistrarV' action='RecepcionMaterial?opc=7&idRegistro="
                                    + obj_recepcion[0] + "' method='post' novalidate=''>");
                            out.print("<table class='table table-sm table-hover table-bordered'>");
                            out.print("<input type='hidden' name='estado2' value='2'>");
                            // <editor-fold defaultstate="collapsed" desc="REGISTRAR VERIFICACION">
                            out.print("<thead>");
                            out.print("<tr>");
                            out.print("<th style='width: auto; text-align: center'>Verificacion</th>");
                            out.print("<th style='width: 90px !important; text-align: center'>SI</th>");
                            out.print("<th style='width: 90px !important; text-align: center'>NO</th>");
                            out.print("<th style='width: 231px !important; text-align: center'>Observaciones</th>");
                            out.print("</tr>");
                            out.print("</thead>");
                            out.print("<tbody style='font-size: 11px;'>");
                            out.print("<tr>");
                            out.print("<td>SE ANEXA CERTIFICADO DE CALIDAD</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio1' value='1' required>");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio1' value='2' required>");
                            out.print("</td>");
                            out.print(
                                    "<td align='center'><input type='text' name='obs1' style='width: 100px; border: none;' placeholder='N/A' data-toggle='tooltip' data-placement='top' title='Observacion'></td>");
                            out.print("</tr>");

                            out.print("<tr>");
                            out.print("<td>LA CANTIDAD RECIBIDA ES LA REMISIONADA</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio2' value='1' required>");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio2' value='2' required>");
                            out.print("</td>");
                            out.print(
                                    "<td align='center'><input type='text' name='obs2' style='width: 100px; border: none;' placeholder='N/A' data-toggle='tooltip' data-placement='top' title='Observacion'></td>");
                            out.print("</tr>");

                            out.print("<tr>");
                            out.print("<td>LA IDENTIFICACION DEL EMPAQUE ES ADECUADA</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio3' value='1' required>");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio3' value='2' required>");
                            out.print("</td>");
                            out.print(
                                    "<td align='center'><input type='text' name='obs3' style='width: 100px; border: none;' placeholder='N/A' data-toggle='tooltip' data-placement='top' title='Observacion'></td>");
                            out.print("</tr>");

                            out.print("<tr>");
                            out.print("<td>EL MATERIAL RECEPCIONADO COINCIDE CON LA REQUISICION DE COMPRA</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio4' value='1' required>");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio4' value='2' required>");
                            out.print("</td>");
                            out.print(
                                    "<td align='center'><input type='text' name='obs4' style='width: 100px; border: none;' placeholder='N/A' data-toggle='tooltip' data-placement='top' title='Observacion'></td>");
                            out.print("</tr>");

                            out.print("<tr>");
                            out.print("<td>LAS CONDICIONES DE TRANSPORTE Y DESCARGUE SON ACEPTABLES</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio5' value='1' required>");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio5' value='2' required>");
                            out.print("</td>");
                            out.print(
                                    "<td align='center'><input type='text' name='obs5' style='width: 100px; border: none;' placeholder='N/A' data-toggle='tooltip' data-placement='top' title='Observacion'></td>");
                            out.print("</tr>");

                            out.print("<tr>");
                            out.print("<td>EL PESO TOTAL REMISIONADO COINCIDE CON EL RECEPCIONADO</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio6' value='1' required>");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio6' value='2' required>");
                            out.print("</td>");
                            out.print(
                                    "<td align='center'><input type='text' name='obs6' style='width: 100px; border: none;' placeholder='N/A' data-toggle='tooltip' data-placement='top' title='Observacion'></td>");
                            out.print("</tr>");

                            out.print("<tr>");
                            out.print("<td>LA IDENTIFICACION DEL MATERIAL CONTIENE ADEMAS EL NUMERO DE LOTE</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio7' value='1' required>");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio7' value='2' required>");
                            out.print("</td>");
                            out.print(
                                    "<td align='center'><input type='text' name='obs7' style='width: 100px; border: none;' placeholder='N/A' data-toggle='tooltip' data-placement='top' title='Observacion'></td>");
                            out.print("</tr>");

                            out.print("<tr>");
                            out.print("<td>EL MATERIAL SE ENCUENTRA EN EL EMPAQUE ORIGINAL</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio8' value='1' required>");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio8' value='2' required>");
                            out.print("</td>");
                            out.print(
                                    "<td align='center'><label>Reempaque </label> <label> SI</label><input type='radio' name='radio12' value='1' required><label>NO</label><input type='radio' name='radio12' value='2'></td>");
                            out.print("</tr>");

                            out.print("<tr>");
                            out.print("<td>NOVEDADES (OLOR NO CARACTERISTICO, PRESENCIA DE MATERIAL EXTRAÑO)</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio9' value='1' required>");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio9' value='2' required>");
                            out.print("</td>");
                            out.print(
                                    "<td align='center'><input type='text' name='obs9' style='width: 100px; border: none;' placeholder='N/A' data-toggle='tooltip' data-placement='top' title='Observacion'></td>");
                            out.print("</tr>");

                            out.print("<tr>");
                            out.print("<td>SOBRANTES</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio10' value='1' required>");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio10' value='2' required>");
                            out.print("</td>");
                            out.print(
                                    "<td align='center'><input type='text' name='obs10' style='width: 100px; border: none;' placeholder='N/A' data-toggle='tooltip' data-placement='top' title='Observacion'></td>");
                            out.print("</tr>");

                            out.print("<tr>");
                            out.print("<td>FALTANTES</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio11' value='1' required>");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio11' value='2' required>");
                            out.print("</td>");
                            out.print(
                                    "<td align='center'><input type='text' name='obs11' style='width: 100px; border: none;' placeholder='N/A' data-toggle='tooltip' data-placement='top' title='Observacion'></td>");
                            out.print("</tr>");

                            out.print("<tr>");
                            out.print("<td>INDICE DE REFRACCION (APLICA PLASTIFICANTE)</td>");
                            out.print("<td align='center'>");
                            out.print(
                                    "<input type='radio' name='radio13' id='radio13_1' onchange=\"cambioradioR()\" value='1' required>");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print(
                                    "<input type='radio' name='radio13' id='radio13_2' onchange=\"cambioradioR()\" value='2' required>");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<label>Valor:</label>");
                            out.print(
                                    "<input class='form-control' type='text' id='obs12' name='obs12' placeholder='Valor' data-toggle='tooltip' data-placement='top' title='Solo se permiten números y puntos.' required pattern='^[0-9.]+$' style='display: initial; width: initial; height: initial; padding: initial; font-size: initial; line-height: initial; color: initial; background-color: initial; background-clip: initial; border-radius: initial; transition: initial;'>");
                            out.print("<label>Cumple certificado calidad</label><br>");
                            out.print(
                                    "<label>SI</label><input type='radio' name='radio14' id='radio14_1' value='1' required>");
                            out.print(
                                    "<label>NO</label><input type='radio' name='radio14' id='radio14_2' value='2' required>");
                            out.print("</td>");
                            out.print("</tr>");

                            out.print("</tbody>");
                            // </editor-fold>
                            out.print("</form>");
                            out.print("</table>");
                            if ((Integer) obj_recepcion[26] != 4) {
                                out.print("<div class='' style='width: 100%; text-align:center;'>");
                                out.print(
                                        "<button type='submit' class='btn btn-green btn-lg' onclick='return validateFormR(\"RegistrarV\")'>Enviar</button>");
                                out.print("</div>");
                            }
                        }

                        out.print("</div>");
                        out.print("</div>");
                        out.print("</div>");
                        // <editor-fold defaultstate="collapsed" desc="TABLA RESUMEN">
                        out.print("<form id='RegistarR' action='RecepcionMaterial?opc=8&idRegistro=" + obj_recepcion[0]
                                + "' method='post' novalidate>");
                        out.print("<div class='card card card-info'>");
                        out.print("<div class='card-header'>");
                        out.print("<h4>Resumen</h4>");
                        out.print("<div class='card-header-action'>");
                        out.print(
                                "<a data-collapse='S' class='btn btn-icon btn-info' href='#'><i class='fas fa-minus'></i></a>");
                        out.print("</div>");
                        out.print("</div>");
                        if (obj_recepcion[26] != null && (obj_recepcion[26].equals(1) || obj_recepcion[26].equals(3))) {
                            out.print("<div class='collapse' id='mycard-collapse2' style=''>");
                        } else {
                            out.print("<div class='collapse show' id='mycard-collapse2' style=''>");
                        }
                        out.print("<div class='card-body'>");

                        out.print("<input type='hidden' name='estado3' value='3'>");

                        out.print("<table class='table table-sm table-hover table-bordered'>");
                        out.print("<thead>");
                        out.print("<tr>");
                        out.print("<th style='text-align: center'>Condicion a Evaluar</th>");
                        out.print("<th style='text-align: center'>SI o NO</th>");
                        out.print("</tr>");
                        out.print("</thead>");
                        out.print("<tbody style='font-size: 11px;'>");

                        String resultado = (obj_recepcion.length > 27 && obj_recepcion[27] != null)
                                ? obj_recepcion[27].toString()
                                : "";
                        out.print("<tr>");
                        out.print("<td>CUMPLE EL RESULTADO DE LA VERIFICACIÓN</td>");
                        out.print("<td style='text-align: center'>");
                        if ((Integer) obj_recepcion[26] != 4) {
                            if ("1".equals(resultado)) {
                                out.print("<input type='radio' name='resultado' value='1' checked>");
                                out.print("<input type='radio' name='resultado' value='2' >");
                            } else if ("2".equals(resultado)) {
                                out.print("<input type='radio' name='resultado' value='1' >");
                                out.print("<input type='radio' name='resultado' value='2' checked>");
                            } else {
                                out.print("<input type='radio' name='resultado' value='1' required>");
                                out.print("<input type='radio' name='resultado' value='2'>");
                            }
                        } else if ("1".equals(resultado)) {
                            out.print("<input type='radio' name='resultado' value='1' checked>");
                            out.print("<input type='radio' name='resultado' value='2' disabled>");
                        } else if ("2".equals(resultado)) {
                            out.print("<input type='radio' name='resultado' value='1' disabled>");
                            out.print("<input type='radio' name='resultado' value='2' checked>");
                        }
                        out.print("</td>");
                        out.print("</tr>");

                        String resultado15 = (obj_recepcion.length > 15 && obj_recepcion[15] != null)
                                ? obj_recepcion[15].toString()
                                : "";
                        out.print("<tr>");
                        out.print("<td>EMPAQUES ROTOS O EN MAL ESTADO</td>");
                        out.print("<td style='text-align: center'>");
                        if ((Integer) obj_recepcion[26] != 4) {
                            if ("1".equals(resultado15)) {
                                out.print("<input type='radio' name='estadoEmpaques' value='1' checked>");
                                out.print("<input type='radio' name='estadoEmpaques' value='2' >");
                            } else if ("2".equals(resultado15)) {
                                out.print("<input type='radio' name='estadoEmpaques' value='1' >");
                                out.print("<input type='radio' name='estadoEmpaques' value='2' checked>");
                            } else {
                                out.print("<input type='radio' name='estadoEmpaques' value='1' required>");
                                out.print("<input type='radio' name='estadoEmpaques' value='2'>");
                            }
                        } else if ("1".equals(resultado15)) {
                            out.print("<input type='radio' name='estadoEmpaques' value='1' checked>");
                            out.print("<input type='radio' name='estadoEmpaques' value='2' disabled>");
                        } else if ("2".equals(resultado15)) {
                            out.print("<input type='radio' name='estadoEmpaques' value='1' disabled>");
                            out.print("<input type='radio' name='estadoEmpaques' value='2' checked>");
                        }
                        out.print("</td>");
                        out.print("</tr>");

                        String resultado16 = (obj_recepcion.length > 16 && obj_recepcion[16] != null)
                                ? obj_recepcion[16].toString()
                                : "";
                        out.print("<tr>");
                        out.print("<td>NUCLEOS ROTOS O EN MAL ESTADO</td>");
                        out.print("<td style='text-align: center'>");
                        if ((Integer) obj_recepcion[26] != 4) {
                            if ("1".equals(resultado16)) {
                                out.print(
                                        "<label>SI</label><input type='radio' name='estadoNucleos' value='1' checked>");
                                out.print("<label>NO</label><input type='radio' name='estadoNucleos' value='2'>");
                                out.print("<label>N/A</label><input type='radio' name='estadoNucleos' value='3'>");
                            } else if ("2".equals(resultado16)) {
                                out.print("<label>SI</label><input type='radio' name='estadoNucleos' value='1'>");
                                out.print(
                                        "<label>NO</label><input type='radio' name='estadoNucleos' value='2' checked>");
                                out.print("<label>N/A</label><input type='radio' name='estadoNucleos' value='3'>");
                            } else if ("3".equals(resultado16)) {
                                out.print("<label>SI</label><input type='radio' name='estadoNucleos' value='1'>");
                                out.print("<label>NO</label><input type='radio' name='estadoNucleos' value='2'>");
                                out.print(
                                        "<label>N/A</label><input type='radio' name='estadoNucleos' value='3' checked>");
                            } else {
                                out.print(
                                        "<label style='margin-top: 26px;'>SI</label><input type='radio' name='estadoNucleos' value='1' required>");
                                out.print(
                                        "<label style='margin-top: 26px;'>NO</label><input type='radio' name='estadoNucleos' value='2'>");
                                out.print(
                                        "<label style='margin-top: 26px;'>N/A</label><input type='radio' name='estadoNucleos' value='3'>");
                            }
                        } else if ("1".equals(resultado16)) {
                            out.print("<label>SI</label><input type='radio' name='estadoNucleos' value='1' checked>");
                            out.print("<label>NO</label><input type='radio' name='estadoNucleos' value='2' disabled>");
                            out.print("<label>N/A</label><input type='radio' name='estadoNucleos' value='3' disabled>");
                        } else if ("2".equals(resultado16)) {
                            out.print("<label>SI</label><input type='radio' name='estadoNucleos' value='1' disabled>");
                            out.print("<label>NO</label><input type='radio' name='estadoNucleos' value='2' checked>");
                            out.print("<label>N/A</label><input type='radio' name='estadoNucleos' value='3' disabled>");
                        } else if ("3".equals(resultado16)) {
                            out.print("<label>SI</label><input type='radio' name='estadoNucleos' value='1' disabled>");
                            out.print("<label>NO</label><input type='radio' name='estadoNucleos' value='2' disabled>");
                            out.print("<label>N/A</label><input type='radio' name='estadoNucleos' value='3' checked>");
                        }
                        out.print("</td>");
                        out.print("</tr>");

                        String pesaje = (obj_recepcion.length > 17 && obj_recepcion[17] != null)
                                ? obj_recepcion[17].toString()
                                : "";
                        out.print("<tr>");
                        out.print("<td>PESAJE ALEATORIO:</td>");
                        out.print("<td style='text-align: center'>");
                        if ((Integer) obj_recepcion[26] != 4) {
                            if (pesaje.isEmpty()) {
                                out.print(
                                        "<input border: none;' type='number' name='pesaje' style='width: 100px; border: none;' placeholder='Pesaje' data-toggle='tooltip' data-placement='top' title='Pesaje' required>");
                            } else {
                                out.print("<input border: none;' type='number' name='pesaje' value='" + pesaje
                                        + "' style='width: 100px; border: none;' placeholder='Pesaje' data-toggle='tooltip' data-placement='top' title='Pesaje'>");
                            }
                        } else {
                            out.print("<span>" + pesaje + "</span>");
                        }
                        out.print("</td>");
                        out.print("</tr>");

                        String unidad = (obj_recepcion.length > 18 && obj_recepcion[18] != null)
                                ? obj_recepcion[18].toString()
                                : "";
                        out.print("<tr>");
                        out.print("<td>UNIDAD MEDIDA PESAJE:</td>");
                        out.print("<td align='center'>");
                        if ((Integer) obj_recepcion[26] != 4) {
                            if (unidad.isEmpty()) {
                                out.print(
                                        "<input border: none;' type='text' name='umPesaje' style='width: 100px; border: none;' placeholder='Unidad Medida' data-toggle='tooltip' data-placement='top' title='Unidad Medida' required>");
                            } else {
                                out.print("<input border: none;' type='text' name='umPesaje' value='" + unidad
                                        + "' style='width: 100px; border: none;' placeholder='Unidad Medida' data-toggle='tooltip' data-placement='top' title='Unidad Medida'>");
                            }
                        } else {
                            out.print("<span>" + unidad + "</span>");
                        }
                        out.print("</td>");
                        out.print("</tr>");

                        String repesaje = (obj_recepcion.length > 19 && obj_recepcion[19] != null)
                                ? obj_recepcion[19].toString()
                                : "";
                        out.print("<tr>");
                        out.print("<td>REPESAJE</td>");
                        out.print("<td align='center'>");
                        if ((Integer) obj_recepcion[26] != 4) {
                            if (repesaje.isEmpty()) {
                                out.print(
                                        "<input border: none;' type='number' name='repesaje' style='width: 100px; border: none;' placeholder='Repesaje' data-toggle='tooltip' data-placement='top' title='Repesaje' required>");
                            } else {
                                out.print("<input border: none;' type='number' name='repesaje' value='" + repesaje
                                        + "' style='width: 100px; border: none;' placeholder='Repesaje' data-toggle='tooltip' data-placement='top' title='Repesaje'>");
                            }
                        } else {
                            out.print("<span>" + repesaje + "</span>");
                        }
                        out.print("</td>");
                        out.print("</tr>");

                        String finalizaDes = (obj_recepcion.length > 13 && obj_recepcion[13] != null)
                                ? obj_recepcion[13].toString()
                                : "";
                        out.print("<tr>");
                        out.print("<td>FINALIZA DESCARGUE</td>");
                        out.print("<td align='center'>");
                        if ((Integer) obj_recepcion[26] != 4) {
                            if (finalizaDes.isEmpty()) {
                                out.print(
                                        "<input type='time' name='finalizaDescargue' style='width: 100px; border: none;' placeholder='Finaliza Descargue' data-toggle='tooltip' data-placement='top' title='Finaliza Descargue' required>");
                            } else {
                                out.print("<input type='time' name='finalizaDescargue' value='" + finalizaDes
                                        + "' style='width: 100px; border: none;' placeholder='Finaliza Descargue' data-toggle='tooltip' data-placement='top' title='Finaliza Descargue'>");
                            }
                        } else {
                            out.print("<span>" + finalizaDes + "</span>");
                        }
                        out.print("</td>");
                        out.print("</tr>");

                        String resultado20 = (obj_recepcion.length > 20 && obj_recepcion[20] != null)
                                ? obj_recepcion[20].toString()
                                : "";
                        out.print("<tr>");
                        out.print("<td>FINALIZA DESCARGUE FUERA DE HORARIO LABORAL</td>");
                        out.print("<td style='text-align: center'>");
                        if ((Integer) obj_recepcion[26] != 4) {
                            if ("1".equals(resultado20)) {
                                out.print("<input type='radio' name='fueraHorario' value='1' checked>");
                                out.print("<input type='radio' name='fueraHorario' value='2' >");
                            } else if ("2".equals(resultado20)) {
                                out.print("<input type='radio' name='fueraHorario' value='1' >");
                                out.print("<input type='radio' name='fueraHorario' value='2' checked>");
                            } else {
                                out.print("<input type='radio' name='fueraHorario' value='1' required>");
                                out.print("<input type='radio' name='fueraHorario' value='2'>");
                            }
                        } else if ("1".equals(resultado20)) {
                            out.print("<input type='radio' name='fueraHorario' value='1' checked>");
                            out.print("<input type='radio' name='fueraHorario' value='2' disabled>");
                        } else if ("2".equals(resultado20)) {
                            out.print("<input type='radio' name='fueraHorario' value='1' disabled>");
                            out.print("<input type='radio' name='fueraHorario' value='2' checked>");
                        }
                        out.print("</td>");
                        out.print("</tr>");

                        out.print("</tbody>");
                        out.print("</table>");

                        if ((Integer) obj_recepcion[26] != 4) {
                            out.print("<div class='' style='width: 100%; text-align:center;'>");
                            out.print(
                                    "<button type='submit' class='btn btn-green btn-lg' onclick='return validateForm(\"RegistarR\")'>Enviar</button>");
                            // out.print("<button class='btn btn-green btn-lg'>Enviar</button>");
                            out.print("</div>");
                        }
                        out.print("</form>");
                        out.print("</div>");
                        out.print("</div>");
                        out.print("</div>");
                        // </editor-fold>
                        // <editor-fold defaultstate="collapsed" desc="TABLA FIRMAS">
                        out.print("<form action='RecepcionMaterial?opc=9&idRegistro=" + obj_recepcion[0]
                                + "' method='post' class='needs-validation' novalidate>");

                        out.print("<div class='card card card-info'>");
                        out.print("<div class='card-header'>");
                        out.print("<h4>Firmas</h4>");
                        out.print("<div class='card-header-action'>");
                        out.print(
                                "<a data-collapse='#mycard-collapse3' class='btn btn-icon btn-info' href='#'><i class='fas fa-minus'></i></a>");
                        out.print("</div>");
                        out.print("</div>");
                        if (obj_recepcion[26] != null && (obj_recepcion[26].equals(1) || obj_recepcion[26].equals(2))) {
                            out.print("<div class='collapse' id='mycard-collapse3' style=''>");
                        } else {
                            out.print("<div class='collapse show' id='mycard-collapse3' style=''>");
                        }
                        out.print("<div class='card-body'>");

                        out.print("<table class='table table-sm table-hover table-bordered border-primary'>");
                        out.print("<thead>");
                        out.print("<tr>");
                        out.print("<th style='text-align: center'>Accion</th>");
                        out.print("<th style='text-align: center'>Responsable</th>");
                        out.print("</tr>");
                        out.print("</thead>");
                        out.print("<tbody style='font-size: 11px;'>");

                        String[] datos = {};
                        String strValue = "";

                        out.print("<tr>");
                        out.print("<td>TRANSPORTADOR</td>");
                        out.print("<td align='center'>");
                        if (obj_recepcion[21] == null) {
                            out.print(
                                    "<input class='form-control' type='text' name='nombre' placeholder='Nombre' data-toggle='tooltip' data-placement='top' title='Nombre' required style='display: initial; width: initial; height: initial; padding: initial; font-size: initial; line-height: initial; color: initial; background-color: initial; background-clip: initial; border-radius: initial; transition: initial;'>");
                            out.print(
                                    "<input class='form-control' type='number' name='cedula' placeholder='Cedula' data-toggle='tooltip' data-placement='top' title='Cedula' required style='display: initial; width: initial; height: initial; padding: initial; font-size: initial; line-height: initial; color: initial; background-color: initial; background-clip: initial; border-radius: initial; transition: initial;'>");
                        } else {
                            if (obj_recepcion[21] != null && obj_recepcion[21] instanceof String) {
                                strValue = obj_recepcion[21].toString();
                                if (strValue.contains("[")) {
                                    if (strValue.contains("/")) {
                                        datos = strValue.replace("[", "").replace("]", "").split("/");
                                    } else if (strValue.contains(",")) {
                                        datos = strValue.replace("[", "").replace("]", "").split(",");
                                    }
                                    out.print(
                                            "<span style='width: 100px; text-transform: uppercase;' data-toggle='tooltip' data-placement='top' title='Nombre'>"
                                                    + ((datos[0] == null) ? "" : datos[0]) + "</span><br>");
                                    out.print(
                                            "<span style='width: 100px;' data-toggle='tooltip' data-placement='top' title='Cedula'>"
                                                    + ((datos[1] == null) ? "" : datos[1]) + "</span>");
                                } else {
                                    out.print(
                                            "<span style='width: 100px; text-transform: uppercase;' data-toggle='tooltip' data-placement='top' title='Nombre'>"
                                                    + ((datos[0] == null) ? "" : datos[0]) + "</span><br>");
                                    out.print(
                                            "<span style='width: 100px;' data-toggle='tooltip' data-placement='top' title='Cedula'>"
                                                    + ((datos[1] == null) ? "" : datos[1]) + "</span>");
                                }
                            }
                        }
                        out.print("</td>");
                        out.print("</tr>");
                        out.print("<input type='hidden' name='estado4' value='4'>");
                        out.print("<input type='hidden' name='count' value='" + obj_recepcion[38] + "'>");
                        out.print("<tr>");
                        out.print("<td >");
                        out.print("VERIFICA");
                        out.print("</td>");
                        if ((Integer) obj_recepcion[26] != 4) {
                            if (obj_recepcion[22] == null || obj_recepcion[22].toString().equals("NULL")) {
                                out.print("<td align='center'>");
                                out.print("<!-- Rol: " + rol + " -->");
                                String rolNombres = sesion.getAttribute("Rol/Nombres") != null
                                        ? sesion.getAttribute("Rol/Nombres").toString()
                                        : "";
                                out.print("<!-- Rol/Nombres: " + rolNombres + " -->");
                                try {
                                    rol = rolNombres.toString().split("/")[0];
                                } catch (Exception e) {
                                    rol = "";
                                }
                                if ((rol.equals("Calidad_despachos") || rol.equals("Administrador"))) {
                                    rol_usuario = rolNombres;
                                    out.print(
                                            "<button type='button' id='verificaButton1' onclick=\"mostrarUsuario('verificaButton1', 'usuarioSesion1')\" class='btn btn-info btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Firmar'><i class=\"fas fa-file-signature\"></i></button><input type='hidden' name='txtValue1' value='1'>");
                                    out.print("<span id='usuarioSesion1' style='display: none;'>" + rol_usuario
                                            + "</span>");
                                } else {
                                    out.print(
                                            "<button class='btn btn-secondary btn-sm btn-icon' style='cursor: not-allowed;' data-toggle='tooltip' data-placement='top' disabled><i class='fas fa-file-signature fa-size_small'></i></button>");
                                }
                                out.print("</td>");

                            } else {
                                out.print("<td align='center'>");
                                out.print(
                                        "<span>" + ((obj_recepcion[22] == null) ? "" : obj_recepcion[22]) + "</span>");
                                out.print("</td>");
                            }
                        } else {
                            out.print("<td align='center'>");
                            out.print("<span>" + obj_recepcion[22].toString() + "</span>");
                            out.print("</td>");
                        }
                        out.print("</tr>");

                        out.print("<tr>");
                        out.print("<td >");
                        out.print("JEFE ALMACEN");
                        out.print("</td>");
                        if ((Integer) obj_recepcion[26] != 4) {
                            if (obj_recepcion[23] == null) {
                                out.print("<td align='center'>");
                                out.print("<!-- Rol: " + rol + " -->");
                                String rolNombres = sesion.getAttribute("Rol/Nombres") != null
                                        ? sesion.getAttribute("Rol/Nombres").toString()
                                        : "";
                                out.print("<!-- Rol/Nombres: " + rolNombres + " -->");
                                try {
                                    rol = rolNombres.toString().split("/")[0];
                                } catch (Exception e) {
                                    rol = "";
                                }
                                if ((rol.equals("Jefe_almacen") || rol.equals("Administrador"))) {
                                    rol_usuario = rolNombres;
                                    out.print(
                                            "<button type='button' id='verificaButton2' onclick=\"mostrarUsuario('verificaButton2', 'usuarioSesion2')\" class='btn btn-info btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Firmar'><i class=\"fas fa-file-signature\"></i></button><input type='hidden' name='txtValue2' value='2'>");
                                    out.print("<span id='usuarioSesion2' style='display: none;'>" + rol_usuario
                                            + "</span>");
                                } else {
                                    out.print(
                                            "<button class='btn btn-secondary btn-sm btn-icon' style='cursor: not-allowed;' data-toggle='tooltip' data-placement='top' disabled><i class='fas fa-file-signature fa-size_small'></i></button>");
                                }
                                out.print("</td>");

                            } else {
                                out.print("<td align='center'>");
                                out.print(
                                        "<span>" + ((obj_recepcion[23] == null) ? "" : obj_recepcion[23]) + "</span>");
                                out.print("</td>");
                            }
                        } else {
                            out.print("<td align='center'>");
                            out.print("<span>" + ((obj_recepcion[23] == null)
                                    ? "<button class='btn btn-secondary btn-sm btn-icon' style='cursor: not-allowed;' data-toggle='tooltip' data-placement='top' disabled><i class='fas fa-file-signature fa-size_small'></i></button>"
                                    : obj_recepcion[23]) + "</span>");
                            out.print("</td>");
                        }
                        out.print("</tr>");

                        out.print("<tr>");
                        out.print("<td >");
                        out.print("JEFE ASEGURAMIENTO DE CALIDAD:");
                        out.print("</td>");
                        if ((Integer) obj_recepcion[26] != 4) {
                            if (obj_recepcion[24] == null || obj_recepcion[24].toString().equals("NULL")) {
                                out.print("<td id='td_v' align='center'>");
                                out.print("<!-- Rol: " + rol + " -->");
                                String rolNombres = sesion.getAttribute("Rol/Nombres") != null
                                        ? sesion.getAttribute("Rol/Nombres").toString()
                                        : "";
                                out.print("<!-- Rol/Nombres: " + rolNombres + " -->");
                                try {
                                    rol = rolNombres.toString().split("/")[0];
                                } catch (Exception e) {
                                    rol = "";
                                }
                                if ((rol.equals("Jefe aseguramiento calidad") || rol.equals("Direccion_calidad")
                                        || rol.equals("Administrador"))) {
                                    rol_usuario = rolNombres;
                                    out.print(
                                            "<button type='button' id='verificaButton3' onclick=\"mostrarUsuario('verificaButton3', 'usuarioSesion3')\" class='btn btn-info btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Firmar'><i class=\"fas fa-file-signature\"></i></button><input type='hidden' name='txtValue3' value='3'>");
                                    out.print("<span id='usuarioSesion3' style='display: none;'>" + rol_usuario
                                            + "</span>");
                                } else {
                                    out.print(
                                            "<button class='btn btn-secondary btn-sm btn-icon' style='cursor: not-allowed;' data-toggle='tooltip' data-placement='top' disabled><i class='fas fa-file-signature fa-size_small'></i></button>");
                                }
                                out.print("</td>");

                            } else {
                                out.print("<td align='center'>");
                                out.print(
                                        "<span>" + ((obj_recepcion[24] == null) ? "" : obj_recepcion[24]) + "</span>");
                                out.print("</td>");
                            }
                        } else {
                            out.print("<td align='center'>");
                            out.print("<span>" + obj_recepcion[24].toString() + "</span>");
                            out.print("</td>");
                        }
                        out.print("</tr>");

                        out.print("</tbody>");
                        out.print("</table>");

                        if ((Integer) obj_recepcion[26] != 4) {
                            out.print("<div class='' style='width: 100%; text-align:center;'>");
                            out.print("<button class='btn btn-green btn-lg'>Enviar</button>");
                            out.print("</div>");
                        }

                        out.print("</form>");
                        out.print("</div>");
                        out.print("</div>");
                        out.print("</div>");
                        // </editor-fold>
                        out.print("</div>");
                        out.print("</section>");

                        out.print("</div>");
                        out.print("</div>");
                        out.print("</div>");
                    }
                }
            }
            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="MODAL ANEXOS">
            if (id_add_anexo > 0) {
                out.print(
                        "<div class='sweet-local' tabindex='-1' id='Ventana4' style='opacity: 1.03; display:block;'>");
                out.print("<div class='cont_reg3'>");
                out.print("<div style='display: flex; justify-content: space-between'>");
                out.print("<h2>ANEXOS</h2>");
                out.print(
                        "<button class='btn btn-outline-secondary' onclick='mostrarConvencion(4)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
                out.print("</div>");
                out.print("<div class='cont_form_user' style='display: flex;'>");
                out.print("<div class='col-lg-4' style='border-right: 1px solid #00281b63;'>");

                if (id_anexos == 0) {
                    // <editor-fold defaultstate="collapsed" desc="REGISTRAR ANEXO">
                    out.print(
                            "<form action='AnexosRecepcion.jsp' method='post' enctype='multipart/form-data' class='needs-validation' novalidate=''>");
                    out.print("<h6 align='center'>Registrar Anexos</h6>");

                    out.print("<input type='hidden' name='id_add_anexo' id='idRegistro_anexo' value='"
                            + (id_add_anexo != 0 ? id_add_anexo : "null") + "'>");
                    out.print("<input type='hidden' name='id_m_anexo' value='" + id_anexos + "'>");

                    out.print("<div class='col-12'>");
                    out.print("<div style='position: relative; width: 100%; height: 40px; margin-left:4%;'>");
                    out.print(
                            "<input type='file' class='custom-file-input' name='file_name' id='IdFile' style='position: absolute; width: 100%; height: 100%; margin: 0; opacity: 0; cursor: pointer;' placeholder='Seleccione archivo' required data-toggle='tooltip' data-placement='top' title='Seleccione archivo' onchange='updateFileName()'>");
                    out.print(
                            "<label class='custom-file-label' for='IdFile' style='position: absolute; top: 0; left: 0; width: 100%; height: 42px; line-height: 40px; padding: 0 15px; background-color: #fff; border: 1px solid #ced4da; border-radius: 4px; cursor: pointer;'>Seleccione un archivo</label>");
                    out.print(
                            "<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");
                    out.print("</div>");
                    out.print("<script>");
                    out.print("function updateFileName() {");
                    out.print("var input = document.getElementById('IdFile');");
                    out.print("var label = input.nextElementSibling;");
                    out.print("var fileName = input.files[0].name;");
                    out.print("label.innerText = fileName;");
                    out.print("}");
                    out.print("</script>");

                    out.print("<div class='col-12' style='margin-top:8%; margin-left:4%; width:101%;'>");
                    out.print(
                            "<textarea class='form-control' name='Txt_descripcion' id='' placeholder='Descripcion' required data-toggle='tooltip' data-placemente='top' title='Descripcion'></textarea>");
                    out.print(
                            "<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");
                    out.print("<br>");

                    out.print("<script>");
                    out.print("document.getElementById('IdFile').addEventListener('change', function() { ");
                    out.print("var input = this; ");
                    out.print("var NameFile = input.files[0].name; ");
                    out.print("var DownloadFile = document.getElementById('DownloadFile'); ");
                    out.print(
                            "DownloadFile.innerHTML = '<a class=\"btn btn-info\" href=\"' + URL.createObjectURL(input.files[0]) + '\" download=\"' + NameFile + '\">Ver archivo <i class=\"fas fa-download\"></i></a>'; ");
                    out.print("});");
                    out.print("</script>");

                    out.print("<div class='' style='width: 100%; text-align:center;'>");
                    out.print("<button class='btn btn-green btn-lg'>Registrar</button>");
                    out.print("</div>");

                    out.print("</form>");
                    // </editor-fold>
                } else {
                    // <editor-fold defaultstate="collapsed" desc="MODIFICAR ANEXO">
                    out.print(
                            "<form action='AnexosRecepcion.jsp' method='post' enctype='multipart/form-data' class='needs-validation' novalidate=''>");
                    out.print("<h6 align='center'>Modificar Anexos</h6>");

                    out.print("<input type='hidden' name='id_add_anexo' id='idRegistro_anexo' value='"
                            + (id_add_anexo != 0 ? id_add_anexo : "null") + "'>");
                    out.print("<input type='hidden' name='id_m_anexo' value='" + id_anexos + "'>");

                    anexoNew = jpac_recepcion.Consulta_anexo_id_new(id_anexos);
                    for (int n = 0; n < anexoNew.size(); n++) {
                        Object[] obje_anexos = (Object[]) anexoNew.get(n);

                        out.print("<div class='col-12'>");
                        out.print("<div style='position: relative; width: 100%; height: 40px; margin-left:4%;'>");
                        out.print("<input type='file' class='custom-file-input' name='file_name' id='IdFile' value='"
                                + obje_anexos[3]
                                + "' style='position: absolute; width: 100%; height: 100%; margin: 0; opacity: 0; cursor: pointer;' placeholder='Seleccione archivo' required data-toggle='tooltip' data-placement='top' title='Seleccione archivo' onchange='updateFileName()'>");
                        out.print(
                                "<label class='custom-file-label' for='IdFile' style='position: absolute; top: 0; left: 0; width: 100%; height: 42px; line-height: 40px; padding: 0 15px; background-color: #fff; border: 1px solid #ced4da; border-radius: 4px; cursor: pointer;'>Seleccione un archivo</label>");
                        out.print(
                                "<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                        out.print("</div>");
                        out.print("</div>");
                        out.print("<script>");
                        out.print("function updateFileName() {");
                        out.print("var input = document.getElementById('IdFile');");
                        out.print("var label = input.nextElementSibling;");
                        out.print("var fileName = input.files[0].name;");
                        out.print("label.innerText = fileName;");
                        out.print("}");
                        out.print("</script>");

                        out.print("<div class='col-12' style='margin-top:8%; margin-left:4%; width:101%;'>");
                        out.print(
                                "<textarea type='text' class='form-control' name='Txt_descripcion' id='' required data-toggle='tooltip' data-placemente='top' title='Descripcion'>"
                                        + obje_anexos[4].toString().trim() + "</textarea>");
                        out.print(
                                "<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                        out.print("</div>");
                        out.print("<br>");

                        out.print("<input type='hidden' name='conin' value='" + obje_anexos[3] + "'>");

                        out.print("<script>");
                        out.print("document.getElementById('IdFile').addEventListener('change', function() { ");
                        out.print("var input = this; ");
                        out.print("var NameFile = input.files[0].name; ");
                        out.print("var DownloadFile = document.getElementById('DownloadFile'); ");
                        out.print(
                                "DownloadFile.innerHTML = '<a class=\"btn btn-info\" href=\"' + URL.createObjectURL(input.files[0]) + '\" download=\"' + NameFile + '\">Ver archivo <i class=\"fas fa-download\"></i></a>'; ");
                        out.print("});");
                        out.print("</script>");

                        out.print("<div class='' style='width: 100%; text-align:center;'>");
                        out.print("<button class='btn btn-green btn-lg'>Modificar</button>");
                        out.print("</div>");
                    }
                    out.print("</form>");
                    // </editor-fold>
                }
                out.print("</div>");
                out.print("<div class='col-lg-8'>");
                out.print("<h6 align='center'>Historial de Adjuntos</h6>");
                out.print("<div class='card-body'>");
                out.print("<div class='table-responsive'>");
                out.print("<table class='table table-hover table-bordered' id='table-2'>");
                out.print("<thead>");
                out.print("<tr>");
                out.print("<th>Adjunto</th>");
                out.print("<th>Fecha</th>");
                out.print("<th>Observaciones</th>");
                out.print("<th>Modificar</th>");
                out.print("</tr>");
                out.print("</thead>");
                out.print("<tbody>");

                anexoNew = jpac_recepcion.Consulta_anexo_union(id_add_anexo, "R-GC-051");
                if (anexoNew == null || anexoNew.isEmpty()) {
                    out.print("<tr>");
                    out.print("<td colspan='4' align='center'>");
                    out.print("<b style='color: orange;'>No hay archivos adjuntos.</b>");
                    out.print("</td>");
                    out.print("</tr>");
                } else {
                    for (int i = 0; i < anexoNew.size(); i++) {
                        Object[] obj_anexos;
                        obj_anexos = (Object[]) anexoNew.get(i);
                        out.print("<tr>");
                        if ("Recepcion_Material".equals(obj_anexos[0])) {
                            out.print("<td align='center' colspan='4'>");
                            String cadena = (String) obj_anexos[2];
                            String anexo1 = cadena.replace("<br />", " ~ ");
                            String anexo2 = anexo1.replace("<hr />", "").replace("<a",
                                    "<a class='text-info text-uppercase' style='font-weight:bold;text-decoration:underline;'");
                            out.print(anexo2);
                            out.print("</td>");
                        } else {
                            out.print("<td align='center'>");
                            out.print("<button type='button' onclick='window.location.href=\"Download?File_name="
                                    + obj_anexos[2]
                                    + "\"' class='btn btn-primary btn-sm btn-icon'>Ver archivo <i class='fas fa-file-download'></i></button>");
                            out.print("</td>");
                            out.print("<td align='center'>" + obj_anexos[3] + "</td>");
                            out.print("<td align='center'><b>" + obj_anexos[4] + "</b><br /></td>");
                            out.print("<td align='center'>");
                            out.print("<a href='RecepcionMaterial?opc=1&id_add_anexo=" + id_add_anexo + "&id_m_anexo="
                                    + obj_anexos[1]
                                    + "'><i class='btn btn-primary btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Modificar Anexo'><i class='fas fa-pencil-alt'></i></a>");
                            out.print("</td>");
                        }
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
                out.print("</div>");
            }
            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="MODAL R-GC-O70">
            if (temp == 3) {
                if (idRegistro > 0) {
                    lst_mr = jpac_recepcion.Maestro_Recepciones_id(idRegistro);
                    if (lst_mr != null) {
                        Object[] obj_mr = (Object[]) lst_mr.get(0);
                        out.print(
                                "<div class='sweet-local' tabindex='-1' id='Ventana4' style='opacity: 1.03; display: block;'>");
                        out.print("<div class='cont_reg5'>");
                        out.print("<div style='display: flex; justify-content: space-between'>");
                        out.print(
                                "<button class='btn btn-warning' onclick='Imprimir()' style='height: 30px; padding: 3px; width: 30px;' data-toggle='tooltip' data-placement='top' title='Imprimir'><i class='fas fa-print'></i></button>");
                        out.print(
                                "<button class='btn btn-outline-secondary' onclick='mostrarConvencion(4)' style='height: 30px; padding: 3px; width: 30px; margin-bottom: 15px'><i class='fas fa-times'></i></button>");
                        out.print("</div>");
                        out.print("<div class='cont_form_user'>");

                        out.print("<section class='section'>");
                        out.print("<div class='section-body'>");

                        out.print("<div id='Imprimir'>");
                        out.print(
                                "<table class='table table-sm table-hover table-bordered border-primary' style='width: 100%'>");
                        out.print("<thead>");
                        out.print("<tr>");
                        out.print(
                                "<th colspan='3' class='header-cell' style='text-align: center; background-color: #c1c1c1'>COPIA NO CONTROLADA</th>");
                        out.print("</tr>");
                        out.print("</thead>");
                        out.print("<tbody class='body-table'>");
                        out.print("<tr>");
                        out.print("<td rowspan='2'class='logo-cell' style='width: 25%; text-align: center'>");
                        out.print(
                                "<img src=\"Interfaz/Contenido/images/Logo.png\" alt=\"Logo\" class='logo-img' style='width: 200px'>");
                        out.print("</td>");
                        out.print(
                                "<td class='text-center title-cell' style='width: 50%; font-weight: bold;'> REGISTRO</td>");
                        out.print(
                                "<td class='text-center code-version-cell' style='width: 25%; text-align: center; font-weight: bold;'>CÓDIGO R-GC-070</td>");
                        out.print("</tr>");
                        out.print("<tr>");
                        out.print(
                                "<td style='width: 50%; text-align: center; font-weight: bold;'>MAESTRO DE RECEPCIONES</td>");
                        out.print("<td style='width: 25%; text-align: center; font-weight: bold;'>VERSIÓN 002</td>");
                        out.print("</tr>");

                        out.print("</tbody>");
                        out.print("</table>");

                        out.print(
                                "<table class='table table-sm table-hover table-bordered border-primary' style='width: 100%'>");
                        out.print("<thead>");
                        out.print("<tr>");
                        out.print("<td colspan='9' align='center' style='background-color: #34495e'>");
                        out.print("<b style='color: white'>REFERENCIA / CÓDIGO: " + obj_mr[3].toString() + "</b>");
                        out.print("</td>");
                        out.print("</tr>");
                        out.print("<tr>");
                        out.print("<th style='width: auto; background-color: #34ace0; color: black'>FECHA</th>");
                        out.print("<th style='width: auto; background-color: #34ace0; color: black'>CODIGO</th>");
                        out.print("<th style='width: auto; background-color: #34ace0; color: black'>REFERENCIA</th>");
                        out.print("<th style='width: auto; background-color: #34ace0; color: black'>PROVEEDOR</th>");
                        out.print("<th style='width: auto; background-color: #34ace0; color: black'>LOTE</th>");
                        out.print("<th style='width: auto; background-color: #34ace0; color: black'>CONSECUTIVO</th>");
                        out.print("<th style='width: auto; background-color: #34ace0; color: black'>CANTIDAD</th>");
                        out.print(
                                "<th style='width: auto; background-color: #34ace0; color: black'>RECEPCIONISTA</th>");
                        out.print("<th style='width: auto; background-color: #34ace0; color: black'>FIRMA D.C</th>");
                        out.print("</tr>");
                        out.print("</thead>");
                        out.print("<tbody style='font-size: 12px;'>");
                        for (int i = 0; i < lst_mr.size(); i++) {
                            obj_mr = (Object[]) lst_mr.get(i);
                            out.print("<tr id='row" + i + "'>");
                            for (int x = 1; x < obj_mr.length; x++) {
                                if (x == 7) {
                                    out.print("<td style='text-transform: none;'>");
                                    out.print(obj_mr[x].toString());
                                    out.print("</td>");
                                } else if (x == 9) {
                                    if (obj_mr[9] == null) {
                                        out.print("<td align='center'>");
                                        out.print(
                                                "<i class='fas fa-signature' style='background-color: #34ace0; cursor: pointer' onclick='Firmar()'></i>");
                                    } else {
                                        out.print("<td valign='top'>");
                                        out.print(obj_mr[9].toString());
                                    }
                                    out.print("</td>");
                                } else {
                                    out.print("<td>");
                                    out.print(obj_mr[x].toString());
                                    out.print("</td>");
                                }
                            }
                            out.print("</tr>");
                        }
                        out.print("</tbody>");
                        out.print("</table>");
                        out.print("</div>");

                        out.print("</div>");
                        out.print("</section>");

                        out.print("</div>");
                        out.print("</div>");
                        out.print("</div>");
                    } else {
                        out.print(
                                "<div class='sweet-local' tabindex='-1' id='Ventana4' style='opacity: 1.03; display: block;'>");
                        out.print("<div class='cont_reg5'>");
                        out.print("<div style='display: flex; justify-content: space-between'>");
                        out.print(
                                "<button class='btn btn-warning' onclick='Imprimir()' style='height: 30px; padding: 3px; width: 30px;' data-toggle='tooltip' data-placement='top' title='Imprimir'><i class='fas fa-print'></i></button>");
                        out.print(
                                "<button class='btn btn-outline-secondary' onclick='mostrarConvencion(4)' style='height: 30px; padding: 3px; width: 30px; margin-bottom: 15px'><i class='fas fa-times'></i></button>");
                        out.print("</div>");
                        out.print("<div class='cont_form_user'>");

                        out.print("<section class='section'>");
                        out.print("<div class='section-body'>");

                        out.print("<div id='Imprimir'>");
                        out.print(
                                "<table class='table table-sm table-hover table-bordered border-primary' style='width: 100%'>");
                        out.print("<thead>");
                        out.print("<tr>");
                        out.print(
                                "<th colspan='3' class='header-cell' style='text-align: center; background-color: #c1c1c1'>COPIA NO CONTROLADA</th>");
                        out.print("</tr>");
                        out.print("</thead>");
                        out.print("<tbody class='body-table'>");
                        out.print("<tr>");
                        out.print("<td rowspan='2'class='logo-cell' style='width: 25%; text-align: center'>");
                        out.print(
                                "<img src=\"Interfaz/Contenido/images/Logo.png\" alt=\"Logo\" class='logo-img' style='width: 200px'>");
                        out.print("</td>");
                        out.print(
                                "<td class='text-center title-cell' style='width: 50%; font-weight: bold;'> REGISTRO</td>");
                        out.print(
                                "<td class='text-center code-version-cell' style='width: 25%; text-align: center; font-weight: bold;'>CÓDIGO R-GC-070</td>");
                        out.print("</tr>");
                        out.print("<tr>");
                        out.print(
                                "<td style='width: 50%; text-align: center; font-weight: bold;'>MAESTRO DE RECEPCIONES</td>");
                        out.print("<td style='width: 25%; text-align: center; font-weight: bold;'>VERSIÓN 002</td>");
                        out.print("</tr>");

                        out.print("</tbody>");
                        out.print("</table>");

                        out.print(
                                "<table class='table table-sm table-hover table-bordered border-primary' style='width: 100%'>");
                        out.print("<thead>");
                        out.print("<tr>");
                        out.print("<td colspan='9' align='center' style='background-color: #34495e'>");
                        out.print("<b style='color: white'>REFERENCIA / CÓDIGO:</b>");
                        out.print("</td>");
                        out.print("</tr>");
                        out.print("<tr>");
                        out.print("<th style='width: auto; background-color: #34ace0; color: black'>FECHA</th>");
                        out.print("<th style='width: auto; background-color: #34ace0; color: black'>CODIGO</th>");
                        out.print("<th style='width: auto; background-color: #34ace0; color: black'>REFERENCIA</th>");
                        out.print("<th style='width: auto; background-color: #34ace0; color: black'>PROVEEDOR</th>");
                        out.print("<th style='width: auto; background-color: #34ace0; color: black'>LOTE</th>");
                        out.print("<th style='width: auto; background-color: #34ace0; color: black'>CONSECUTIVO</th>");
                        out.print("<th style='width: auto; background-color: #34ace0; color: black'>CANTIDAD</th>");
                        out.print(
                                "<th style='width: auto; background-color: #34ace0; color: black'>RECEPCIONISTA</th>");
                        out.print("<th style='width: auto; background-color: #34ace0; color: black'>FIRMA D.C</th>");
                        out.print("</tr>");
                        out.print("</thead>");
                        out.print("<tbody style='font-size: 12px;'>");
                        out.print("<td colspan='9' align='center' style='background-color: #d57200;'>");
                        out.print(
                                "<span style='width: 100px;text-transform: uppercase;color: white;-webkit-text-stroke: thin;text-decoration: underline;'>¡ La referencia no tiene recepciones actualmente...!</span>");
                        out.print("</td>");
                        out.print("</tbody>");
                        out.print("</table>");
                        out.print("</div>");

                        out.print("</div>");
                        out.print("</section>");

                        out.print("</div>");
                        out.print("</div>");
                        out.print("</div>");
                    }
                }
            }
            //// </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="MODAL R-GC-051">
            if (temp == 2) {
                if (idRegistro > 0) {
                    lst_recep = jpac_recepcion.Lista_Recepcion(idRegistro);
                    if (lst_recep != null) {
                        Object[] obj_recepcion = (Object[]) lst_recep.get(0);
                        out.print(
                                "<div class='sweet-local' tabindex='-1' id='Ventana3' style='opacity: 1.03; display: block;'>");
                        out.print("<div class='cont_reg5'>");
                        out.print("<div style='display: flex; justify-content: space-between'>");
                        out.print(
                                "<button class='btn btn-warning' onclick='Imprimir()' style='height: 30px; padding: 3px; width: 30px;' data-toggle='tooltip' data-placement='top' title='Imprimir'><i class='fas fa-print'></i></button>");
                        out.print(
                                "<button class='btn btn-outline-secondary' onclick='mostrarConvencion(3)' style='height: 30px; padding: 3px; width: 30px; margin-bottom: 15px'><i class='fas fa-times'></i></button>");
                        out.print("</div>");
                        out.print("<div class='cont_form_user'>");

                        out.print("<section class='section'>");
                        out.print("<div class='section-body'>");

                        out.print("<div id='Imprimir'>");
                        out.print(
                                "<table class='table table-sm table-hover table-bordered border-primary' style='width: 100%'>");
                        out.print("<thead>");
                        out.print("<tr>");
                        out.print(
                                "<th colspan='3' class='header-cell' style='text-align: center; background-color: #c1c1c1'>COPIA NO CONTROLADA</th>");
                        out.print("</tr>");
                        out.print("</thead>");
                        out.print("<tbody class='body-table'>");
                        out.print("<tr>");
                        out.print("<td rowspan='2'class='logo-cell' style='width: 25%; text-align: center'>");
                        out.print(
                                "<img src=\"Interfaz/Contenido/images/Logo.png\" alt=\"Logo\" class='logo-img' style='width: 200px'>");
                        out.print("</td>");
                        out.print(
                                "<td class='text-center title-cell' style='width: 50%; font-weight: bold;'> REGISTRO</td>");
                        out.print(
                                "<td class='text-center code-version-cell' style='width: 25%; text-align: center; font-weight: bold;'>CÓDIGO R-GC-051</td>");
                        out.print("</tr>");
                        out.print("<tr>");
                        out.print(
                                "<td style='width: 50%; text-align: center; font-weight: bold;'>RECEPCIÓN DE MATERIAS PRIMAS,<br> MATERIAL DE EMPAQUE E INSUMOS</td>");
                        if (idRegistro >= 4677 && (Integer) obj_recepcion[26] == 4) {
                            out.print(
                                    "<td style='width: 25%; text-align: center; font-weight: bold;'>VERSIÓN 008</td>");
                        } else {
                            out.print(
                                    "<td style='width: 25%; text-align: center; font-weight: bold;'>VERSIÓN 007</td>");
                        }

                        out.print("</tr>");
                        out.print("</tbody>");
                        out.print("</table>");
                        // <editor-fold defaultstate="collapsed" desc="DATOS DE LA RECEPCION">
                        out.print(
                                "<table class='table table-sm table-hover table-bordered' style='width: 100%; font-size: 12px;'>");
                        out.print("<thead>");
                        out.print("<tr>");
                        out.print(
                                "<th colspan='6' style='padding: 5px; border-top-left-radius: 5px; border-top-right-radius: 5px; text-align: center;'>");
                        out.print("<b>DATOS DE LA RECEPCION</b>");
                        out.print("</th>");
                        out.print("</tr>");
                        out.print("</thead>");
                        out.print("<tbody>");

                        out.print("<tr>");
                        out.print(
                                "<td align='center' style='padding: 5px; border-top: 1px solid #e8e3e3;'><b>FECHA RECEPCIÓN:</b></td>");
                        out.print(
                                "<td align='center' style='border-left: 1px solid #e8e3e3; padding: 5px; border-top: 1px solid #e8e3e3;'>"
                                        + obj_recepcion[10].toString() + "</td>");

                        out.print(
                                "<td align='center' style='padding: 5px; border-top: 1px solid #e8e3e3;'><b>HORA LLEGADA:</b></td>");
                        out.print(
                                "<td align='center' style='border-left: 1px solid #e8e3e3; padding: 5px; border-top: 1px solid #e8e3e3;'>"
                                        + obj_recepcion[11].toString() + "</td>");

                        out.print(
                                "<td align='center' style='padding: 5px; border-top: 1px solid #e8e3e3;'><b>INICIO DESCARGUE:</b></td>");
                        out.print(
                                "<td align='center' style='border-left: 1px solid #e8e3e3; padding: 5px; border-top: 1px solid #e8e3e3;'>"
                                        + obj_recepcion[12].toString() + "</td>");
                        out.print("</tr>");

                        out.print("<tr>");
                        out.print(
                                "<td align='center' style='padding: 5px; border-top: 1px solid #e8e3e3;'><b>CLASIFICACIÓN:</b></td>");
                        out.print(
                                "<td align='center' style='border-left: 1px solid #e8e3e3; padding: 5px; border-top: 1px solid #e8e3e3;'>"
                                        + obj_recepcion[1].toString() + "</td>");

                        out.print(
                                "<td align='center' style='padding: 5px; border-top: 1px solid #e8e3e3;'><b>PROVEEDOR:</b></td>");
                        out.print(
                                "<td align='center' style='border-left: 1px solid #e8e3e3; padding: 5px; border-top: 1px solid #e8e3e3;'>"
                                        + obj_recepcion[9].toString() + "</td>");

                        out.print(
                                "<td align='center' style='padding: 5px; border-top: 1px solid #e8e3e3;'><b>REFERENCIA PROVEEDOR:</b></td>");
                        out.print(
                                "<td align='center' style='border-left: 1px solid #e8e3e3; padding: 5px; border-top: 1px solid #e8e3e3;'>"
                                        + obj_recepcion[28].toString() + "</td>");
                        out.print("</tr>");

                        out.print("<tr>");
                        out.print(
                                "<td align='center' style='padding: 5px; border-top: 1px solid #e8e3e3;'><b>CÓDIGO / NOMBRE:</b></td>");
                        out.print(
                                "<td align='center' style='border-left: 1px solid #e8e3e3; padding: 5px; border-top: 1px solid #e8e3e3;'>"
                                        + obj_recepcion[2].toString() + " / " + obj_recepcion[3].toString() + "</td>");

                        out.print(
                                "<td align='center' style='padding: 5px; border-top: 1px solid #e8e3e3;'><b>LOTE:</b></td>");
                        out.print(
                                "<td align='center' style='border-left: 1px solid #e8e3e3; padding: 5px; border-top: 1px solid #e8e3e3;'>"
                                        + obj_recepcion[4].toString() + "</td>");

                        out.print(
                                "<td align='center' style='padding: 5px; border-top: 1px solid #e8e3e3;'><b>CONSECUTIVO DE CALIDAD:</b></td>");
                        out.print(
                                "<td align='center' style='border-left: 1px solid #e8e3e3; padding: 5px; border-top: 1px solid #e8e3e3;'>"
                                        + obj_recepcion[5].toString() + "</td>");
                        out.print("</tr>");

                        out.print("<tr>");
                        out.print(
                                "<td align='center' style='padding: 5px; border-top: 1px solid #e8e3e3;'><b>INGRESO:</b></td>");
                        out.print(
                                "<td align='center' style='border-left: 1px solid #e8e3e3; padding: 5px; border-top: 1px solid #e8e3e3;'>"
                                        + obj_recepcion[6].toString() + "</td>");

                        out.print(
                                "<td align='center' style='padding: 5px; border-top: 1px solid #e8e3e3;'><b>CANTIDAD TOTAL:</b></td>");
                        out.print(
                                "<td align='center' style='border-left: 1px solid #e8e3e3; padding: 5px; border-top: 1px solid #e8e3e3;'>"
                                        + (Double) obj_recepcion[7] + "</td>");

                        out.print(
                                "<td align='center' style='padding: 5px; border-top: 1px solid #e8e3e3;'><b>UNIDAD DE MEDIDA <br> CANTIDAD TOTAL:</b></td>");
                        out.print(
                                "<td align='center' style='border-left: 1px solid #e8e3e3; padding: 5px; border-top: 1px solid #e8e3e3;'>"
                                        + obj_recepcion[8].toString() + "</td>");
                        out.print("</tr>");

                        out.print("</tbody>");
                        out.print("</table>");

                        // </editor-fold>
                        out.print("<div style='display: flex; justify-content: space-between;'>");
                        // <editor-fold defaultstate="collapsed" desc="TABLA VERIFICACION">
                        out.print(
                                "<table class='table table-sm table-hover table-bordered border-primary' style='width: 60%'>");
                        out.print("<thead>");
                        out.print("<tr>");
                        out.print(
                                "<th style='width: 52%; background-color: #34ace0; color: black; text-align: center;'>Verificacion</th>");
                        out.print(
                                "<th style='width: 13% !important; background-color: #34ace0; color: black; text-align: center;'>SI</th>");
                        out.print(
                                "<th style='width: 13% !important; background-color: #34ace0; color: black; text-align: center;'>NO</th>");
                        out.print(
                                "<th style='width: 35% !important; background-color: #34ace0; color: black ; text-align: center;'>Observaciones</th>");
                        out.print("</tr>");
                        out.print("</thead>");
                        out.print("<tbody style='font-size: 12px;'>");

                        String[] dataArr = {};
                        dataArr = obj_recepcion[14].toString().replace("],[", "///").replace("[", "").replace("]", "")
                                .split("///");

                        String[] Certi = {};
                        Certi = dataArr[0].toString().split(",");
                        if ((Integer) obj_recepcion[26] == 4) {
                            out.print("<tr>");
                            out.print("<td>SE ANEXA CERTIFICADO DE CALIDAD</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio1' value='1' "
                                    + ((Integer.parseInt(Certi[1].toString()) == 1) ? "checked" : "disabled") + ">");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio1' value='2' "
                                    + ((Integer.parseInt(Certi[1].toString()) == 2) ? "checked" : "disabled") + ">");
                            out.print("</td>");
                            if ("NULL".equals(Certi[2]) || Certi[2].equals("")) {
                                out.print("<td align='center'><span>N/A</span></td>");
                            } else {
                                out.print("<td align='center'><span>'" + Certi[2] + "'</span></td>");
                            }
                            out.print("</tr>");
                        }

                        Certi = dataArr[1].toString().split(",");
                        if ((Integer) obj_recepcion[26] == 4) {
                            out.print("<tr>");
                            out.print("<td>LA CANTIDAD RECIBIDA ES LA REMISIONADA</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio2' value='1' "
                                    + ((Integer.parseInt(Certi[1].toString()) == 1) ? "checked" : "disabled") + ">");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio2' value='2' "
                                    + ((Integer.parseInt(Certi[1].toString()) == 2) ? "checked" : "disabled") + ">");
                            out.print("</td>");
                            if ("NULL".equals(Certi[2]) || Certi[2].equals("")) {
                                out.print("<td align='center'><span>N/A</span></td>");
                            } else {
                                out.print("<td align='center'><span>'" + Certi[2] + "'</span></td>");
                            }
                            out.print("</tr>");
                        }

                        Certi = dataArr[2].toString().split(",");
                        if ((Integer) obj_recepcion[26] == 4) {
                            out.print("<tr>");
                            out.print("<td>LA IDENTIFICACION DEL EMPAQUE ES ADECUADA</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio3' value='1' "
                                    + ((Integer.parseInt(Certi[1].toString()) == 1) ? "checked" : "disabled") + ">");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio3' value='2' "
                                    + ((Integer.parseInt(Certi[1].toString()) == 2) ? "checked" : "disabled") + ">");
                            out.print("</td>");
                            if ("NULL".equals(Certi[2]) || Certi[2].equals("")) {
                                out.print("<td align='center'><span>N/A</span></td>");
                            } else {
                                out.print("<td align='center'><span>'" + Certi[2] + "'</span></td>");
                            }
                            out.print("</tr>");
                        }

                        Certi = dataArr[3].toString().split(",");
                        if ((Integer) obj_recepcion[26] == 4) {
                            out.print("<tr>");
                            out.print("<td>EL MATERIAL RECEPCIONADO COINCIDE CON LA REQUISICION DE COMPRA</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio4' value='1' "
                                    + ((Integer.parseInt(Certi[1].toString()) == 1) ? "checked" : "disabled") + ">");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio4' value='2' "
                                    + ((Integer.parseInt(Certi[1].toString()) == 2) ? "checked" : "disabled") + ">");
                            out.print("</td>");
                            if ("NULL".equals(Certi[2]) || Certi[2].equals("")) {
                                out.print("<td align='center'><span>N/A</span></td>");
                            } else {
                                out.print("<td align='center'><span>'" + Certi[2] + "'</span></td>");
                            }
                            out.print("</tr>");
                        }

                        Certi = dataArr[4].toString().split(",");
                        if ((Integer) obj_recepcion[26] == 4) {
                            out.print("<tr>");
                            out.print("<td>LAS CONDICIONES DE TRANSPORTE Y DESCARGUE SON ACEPTABLES</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio5' value='1' "
                                    + ((Integer.parseInt(Certi[1].toString()) == 1) ? "checked" : "disabled") + ">");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio5' value='2' "
                                    + ((Integer.parseInt(Certi[1].toString()) == 2) ? "checked" : "disabled") + ">");
                            out.print("</td>");
                            if ("NULL".equals(Certi[2]) || Certi[2].equals("")) {
                                out.print("<td align='center'><span>N/A</span></td>");
                            } else {
                                out.print("<td align='center'><span>'" + Certi[2] + "'</span></td>");
                            }
                            out.print("</tr>");
                        }

                        Certi = dataArr[5].toString().split(",");
                        if ((Integer) obj_recepcion[26] == 4) {
                            out.print("<tr>");
                            out.print("<td>EL PESO TOTAL REMISIONADO COINCIDE CON EL RECEPCIONADO</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio6' value='1' "
                                    + ((Integer.parseInt(Certi[1].toString()) == 1) ? "checked" : "disabled") + ">");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio6' value='2' "
                                    + ((Integer.parseInt(Certi[1].toString()) == 2) ? "checked" : "disabled") + ">");
                            out.print("</td>");
                            out.print("</td>");
                            if ("NULL".equals(Certi[2]) || Certi[2].equals("")) {
                                out.print("<td align='center'><span>N/A</span></td>");
                            } else {
                                out.print("<td align='center'><span>'" + Certi[2] + "'</span></td>");
                            }
                            out.print("</tr>");
                        }

                        Certi = dataArr[6].toString().split(",");
                        if ((Integer) obj_recepcion[26] == 4) {
                            out.print("<tr>");
                            out.print("<td>LA IDENTIFICACION DEL MATERIAL CONTIENE ADEMAS EL NUMERO DE LOTE</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio7' value='1' "
                                    + ((Integer.parseInt(Certi[1].toString()) == 1) ? "checked" : "disabled") + ">");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio7' value='2' "
                                    + ((Integer.parseInt(Certi[1].toString()) == 2) ? "checked" : "disabled") + ">");
                            out.print("</td>");
                            if ("NULL".equals(Certi[2]) || Certi[2].equals("")) {
                                out.print("<td align='center'><span>N/A</span></td>");
                            } else {
                                out.print("<td align='center'><span>'" + Certi[2] + "'</span></td>");
                            }
                            out.print("</tr>");
                        }

                        Certi = dataArr[7].toString().split(",");
                        String reempaque = Certi[2].toString().trim();
                        boolean reempaqueNo = reempaque.equals("REEMPAQUE: NO");
                        boolean reempaqueSi = reempaque.equals("REEMPAQUE: SI");

                        if ((Integer) obj_recepcion[26] == 4) {
                            out.print("<tr>");
                            out.print("<td>EL MATERIAL SE ENCUENTRA EN EL EMPAQUE ORIGINAL</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio8' value='1' "
                                    + ((Integer.parseInt(Certi[1].toString()) == 1) ? "checked" : "disabled") + ">");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio8' value='2' "
                                    + ((Integer.parseInt(Certi[1].toString()) == 2) ? "checked" : "disabled") + ">");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            if (reempaqueSi || reempaqueNo) {
                                out.print(
                                        "<label>Reempaque</label> <label>SI</label><input type='radio' name='radio12' value='1' "
                                                + (reempaqueSi ? "checked" : "disabled") + ">");
                                out.print("<label>NO</label><input type='radio' name='radio12' value='2' "
                                        + (reempaqueNo ? "checked" : "disabled") + ">");
                            } else {
                                out.print("<label>SI</label><input type='radio' name='radio12' value='1' "
                                        + ((Integer.parseInt(Certi[2].toString()) == 1) ? "checked" : "disabled")
                                        + ">");
                                out.print("<label>NO</label><input type='radio' name='radio12' value='2' "
                                        + ((Integer.parseInt(Certi[2].toString()) == 2) ? "checked" : "disabled")
                                        + ">");
                            }
                            out.print("</td>");
                            out.print("</tr>");
                        }

                        Certi = dataArr[8].toString().split(",");
                        if ((Integer) obj_recepcion[26] == 4) {
                            out.print("<tr>");
                            out.print("<td>NOVEDADES (OLOR NO CARACTERISTICO, PRESENCIA DE MATERIAL EXTRAÑO)</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio9' value='1' "
                                    + ((Integer.parseInt(Certi[1].toString()) == 1) ? "checked" : "disabled") + ">");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio9' value='2' "
                                    + ((Integer.parseInt(Certi[1].toString()) == 2) ? "checked" : "disabled") + ">");
                            out.print("</td>");
                            if ("NULL".equals(Certi[2]) || Certi[2].equals("")) {
                                out.print("<td align='center'><span>N/A</span></td>");
                            } else {
                                out.print("<td align='center'><span>'" + Certi[2] + "'</span></td>");
                            }
                            out.print("</tr>");
                        }

                        Certi = dataArr[9].toString().split(",");
                        if ((Integer) obj_recepcion[26] == 4) {
                            out.print("<tr>");
                            out.print("<td>SOBRANTES</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio10' value='1' "
                                    + ((Integer.parseInt(Certi[1].toString()) == 1) ? "checked" : "disabled") + ">");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio10' value='2' "
                                    + ((Integer.parseInt(Certi[1].toString()) == 2) ? "checked" : "disabled") + ">");
                            out.print("</td>");
                            if ("NULL".equals(Certi[2]) || Certi[2].equals("")) {
                                out.print("<td align='center'><span>N/A</span></td>");
                            } else {
                                out.print("<td align='center'><span>'" + Certi[2] + "'</span></td>");
                            }
                            out.print("</tr>");
                        }

                        Certi = dataArr[10].toString().split(",");
                        if ((Integer) obj_recepcion[26] == 4) {
                            out.print("<tr>");
                            out.print("<td>FALTANTES</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio11' value='1' "
                                    + ((Integer.parseInt(Certi[1].toString()) == 1) ? "checked" : "disabled") + ">");
                            out.print("</td>");
                            out.print("<td align='center'>");
                            out.print("<input type='radio' name='radio11' value='2' "
                                    + ((Integer.parseInt(Certi[1].toString()) == 2) ? "checked" : "disabled") + ">");
                            out.print("</td>");
                            if ("NULL".equals(Certi[2]) || Certi[2].equals("")) {
                                out.print("<td align='center'><span>N/A</span></td>");
                            } else {
                                out.print("<td align='center'><span>'" + Certi[2] + "'</span></td>");
                            }
                            out.print("</tr>");
                        }
                        if (dataArr.length > 11) {
                            Certi = dataArr[11].toString().split(",");
                            if ((Integer) obj_recepcion[26] == 4) {
                                out.print("<tr>");
                                out.print("<td>INDICE DE REFRACCION (APLICA PLASTIFICANTE)</td>");
                                out.print("<td align='center'>");
                                out.print("<input type='radio' name='radio13' value='1' "
                                        + ((Integer.parseInt(Certi[1].toString()) == 1) ? "checked" : "disabled")
                                        + ">");
                                out.print("</td>");
                                out.print("<td align='center'>");
                                out.print("<input type='radio' name='radio13' value='2' "
                                        + ((Integer.parseInt(Certi[1].toString()) == 2) ? "checked" : "disabled")
                                        + ">");
                                out.print("</td>");
                                out.print("<td align='center'>");
                                out.print("<label>Valor</label> <span>'" + Certi[2] + "'</span> ");
                                out.print("<label>Cumple con el certificado de calidad</label><br>");
                                out.print("<label>SI</label><input type='radio' name='radio14' value='1' "
                                        + ((Integer.parseInt(Certi[3].toString()) == 1) ? "checked" : "disabled")
                                        + ">");
                                out.print("<label>NO</label><input type='radio' name='radio14' value='2' "
                                        + ((Integer.parseInt(Certi[3].toString()) == 2) ? "checked" : "disabled")
                                        + ">");
                                out.print("</td>");
                                out.print("</tr>");
                            }
                        }
                        out.print("</tbody>");
                        out.print("</table>");

                        // </editor-fold>
                        // <editor-fold defaultstate="collapsed" desc="TABLA RESUMEN">
                        out.print("<div style='margin-left: 10px; width: 40%'>");

                        out.print("<table class='table table-sm table-hover table-bordered border-primary'>");
                        out.print("<thead>");
                        out.print("<tr>");
                        out.print(
                                "<th style='background-color: #34ace0; color: black; text-align: center;'>Condicion a Evaluar</th>");
                        out.print(
                                "<th style='background-color: #34ace0; color: black; text-align: center;'>SI o NO</th>");
                        out.print("</tr>");
                        out.print("</thead>");
                        out.print("<tbody style='font-size: 12px;'>");

                        out.print("<tr>");
                        out.print("<td>CUMPLE EL RESULTADO DE LA VERIFICACIÓN</td>");
                        out.print("<td align='center'>");
                        if ((Integer) obj_recepcion[27] == 1) {
                            out.print("<input type='radio' name='resultado' value='1' checked>");
                            out.print("<input type='radio' name='resultado' value='2' disabled>");
                        } else if ((Integer) obj_recepcion[27] == 2) {
                            out.print("<input type='radio' name='resultado' value='1' disabled>");
                            out.print("<input type='radio' name='resultado' value='2' checked>");
                        } else {
                        }
                        out.print("</td>");
                        out.print("</tr>");

                        out.print("<tr>");
                        out.print("<td>EMPAQUES ROTOS O EN MAL ESTADO</td>");
                        out.print("<td align='center'>");
                        if ((Integer) obj_recepcion[15] == 1) {
                            out.print("<input type='radio' name='estadoEmpaques' value='1' checked>");
                            out.print("<input type='radio' name='estadoEmpaques' value='2' disabled>");
                        } else if ((Integer) obj_recepcion[15] == 2) {
                            out.print("<input type='radio' name='estadoEmpaques' value='1' disabled>");
                            out.print("<input type='radio' name='estadoEmpaques' value='2' checked>");
                        } else {
                        }
                        out.print("</td>");
                        out.print("</tr>");

                        out.print("<tr>");
                        out.print("<td>NUCLEOS ROTOS O EN MAL ESTADO</td>");
                        out.print("<td align='center'>");
                        if ((Integer) obj_recepcion[16] == 1) {
                            out.print("<label>SI</label><input type='radio' name='estadoNucleos' value='1' checked>");
                            out.print("<label>NO</label><input type='radio' name='estadoNucleos' value='2' disabled>");
                            out.print("<label>N/A</label><input type='radio' name='estadoNucleos' value='3' disabled>");
                        } else if ((Integer) obj_recepcion[16] == 2) {
                            out.print("<label>SI</label><input type='radio' name='estadoNucleos' value='1' disabled>");
                            out.print("<label>NO</label><input type='radio' name='estadoNucleos' value='2' checked>");
                            out.print("<label>N/A</label><input type='radio' name='estadoNucleos' value='3' disabled>");
                        } else {
                            out.print("<label>SI</label><input type='radio' name='estadoNucleos' value='1' disabled>");
                            out.print("<label>NO</label><input type='radio' name='estadoNucleos' value='2' disabled>");
                            out.print("<label>N/A</label><input type='radio' name='estadoNucleos' value='3' checked>");
                        }
                        out.print("</td>");
                        out.print("</tr>");

                        String pesaje = (obj_recepcion.length > 17 && obj_recepcion[17] != null)
                                ? obj_recepcion[17].toString()
                                : "";
                        out.print("<tr>");
                        out.print("<td>PESAJE ALEATORIO:</td>");
                        out.print("<td align='center'>");
                        if ((Integer) obj_recepcion[26] == 4) {
                            out.print("<span>" + pesaje + "</span>");
                        }
                        out.print("</td>");
                        out.print("</tr>");

                        String unidad = (obj_recepcion.length > 18 && obj_recepcion[18] != null)
                                ? obj_recepcion[18].toString()
                                : "";
                        out.print("<tr>");
                        out.print("<td>UNIDAD MEDIDA PESAJE:</td>");
                        out.print("<td align='center'>");
                        if ((Integer) obj_recepcion[26] == 4) {
                            out.print("<span>" + unidad + "</span>");
                        }
                        out.print("</td>");
                        out.print("</tr>");

                        String repesaje = (obj_recepcion.length > 19 && obj_recepcion[19] != null)
                                ? obj_recepcion[19].toString()
                                : "";
                        out.print("<tr>");
                        out.print("<td>REPESAJE</td>");
                        out.print("<td align='center'>");
                        if ((Integer) obj_recepcion[26] == 4) {
                            out.print("<span>" + repesaje + "</span>");
                        }
                        out.print("</td>");
                        out.print("</tr>");

                        String finalizaDes = (obj_recepcion.length > 13 && obj_recepcion[13] != null)
                                ? obj_recepcion[13].toString()
                                : "";
                        out.print("<tr>");
                        out.print("<td>FINALIZA DESCARGUE</td>");
                        out.print("<td align='center'>");
                        if ((Integer) obj_recepcion[26] == 4) {
                            out.print("<span>" + finalizaDes + "</span>");
                        }
                        out.print("</td>");
                        out.print("</tr>");

                        out.print("<tr>");
                        out.print("<td>FINALIZA DESCARGUE FUERA DE HORARIO LABORAL</td>");
                        out.print("<td align='center'>");
                        if ((Integer) obj_recepcion[20] == 1) {
                            out.print("<input type='radio' name='fueraHorario' value='1' checked>");
                            out.print("<input type='radio' name='fueraHorario' value='2' disabled>");
                        } else if ((Integer) obj_recepcion[20] == 2) {
                            out.print("<input type='radio' name='fueraHorario' value='1' disabled>");
                            out.print("<input type='radio' name='fueraHorario' value='2' checked>");
                        } else {
                        }
                        out.print("</td>");
                        out.print("</tr>");

                        out.print("</tbody>");
                        out.print("</table>");
                        // </editor-fold>
                        // <editor-fold defaultstate="collapsed" desc="TABLA FIRMAS">
                        out.print("<table class='table table-sm table-hover table-bordered border-primary'>");
                        out.print("<thead>");
                        out.print("<tr>");
                        out.print(
                                "<th style='background-color: #34ace0; color: black; width: 40%; text-align: center;'>Accion</th>");
                        out.print(
                                "<th style='background-color: #34ace0; color: black; text-align: center;'>Responsable</th>");
                        out.print("</tr>");
                        out.print("</thead>");
                        out.print("<tbody style='font-size: 12px;'>");

                        String[] datos = {};
                        if (obj_recepcion[21] != null && obj_recepcion[21] instanceof String) {
                            String strValue = obj_recepcion[21].toString();
                            if (strValue.contains("/")) {
                                datos = strValue.replace("[", "").replace("]", "").split("/");
                            } else if (strValue.contains(",")) {
                                datos = strValue.replace("[", "").replace("]", "").split(",");
                            }
                        }

                        out.print("<tr>");
                        out.print("<td>TRANSPORTADOR</td>");
                        out.print("<td align='center'>");
                        if (obj_recepcion[21] != null) {
                            out.print(
                                    "<span style='width: 100px; text-transform: uppercase;' data-toggle='tooltip' data-placement='top' title='Nombre'> "
                                            + datos[0] + " </span>");
                            out.print(
                                    "<span style='width: 100px;' data-toggle='tooltip' data-placement='top' title='Cedula'> "
                                            + datos[1] + " </span>");
                        }
                        out.print("</td>");
                        out.print("</tr>");

                        out.print("<tr>");
                        out.print("<td >");
                        out.print("VERIFICA");
                        out.print("</td>");
                        if ((Integer) obj_recepcion[26] == 4) {
                            out.print("<td align='center'>");
                            out.print("<span>" + ((obj_recepcion[22] == null) ? "" : obj_recepcion[22]) + "</span>");
                            out.print("</td>");
                        }
                        out.print("</tr>");

                        out.print("<tr>");
                        out.print("<td >");
                        out.print("JEFE ALMACEN");
                        out.print("</td>");
                        if ((Integer) obj_recepcion[26] == 4) {
                            out.print("<td align='center'>");
                            out.print("<span>" + ((obj_recepcion[23] == null) ? "" : obj_recepcion[23]) + "</span>");
                            out.print("</td>");
                        }
                        out.print("</tr>");

                        out.print("<tr>");
                        out.print("<td >");
                        out.print("JEFE ASEGURAMIENTO DE CALIDAD");
                        out.print("</td>");
                        if ((Integer) obj_recepcion[26] == 4) {
                            out.print("<td align='center'>");
                            out.print("<span>" + ((obj_recepcion[24] == null) ? "" : obj_recepcion[24]) + "</span>");
                            out.print("</td>");
                        }
                        out.print("</tr>");

                        out.print("</tbody>");
                        out.print("</table>");
                        // </editor-fold>
                        // <editor-fold defaultstate="collapsed" desc="TABLA OBS Y PRESTAMOS">
                        out.print(
                                "<table class='table table-sm table-hover table-bordered border-primary' style='width: 100%'>");
                        out.print("<thead>");
                        out.print("<tr>");
                        out.print(
                                "<th style='background-color: #34ace0; color: black; text-align: center;'>Observaciones</th>");
                        out.print(
                                "<th style='background-color: #34ace0; color: black; text-align: center;'>Prestamos</th>");
                        out.print("</tr>");
                        out.print("</thead>");
                        out.print("<tbody style='font-size: 12px;'>");
                        out.print("<tr>");
                        if (obj_recepcion[32] == null) {
                            out.print("<td align='center'>");
                            out.print("<span>N/A</span>");
                            out.print("</td>");
                        } else {
                            out.print("<td align='center'>");
                            out.print("<span>" + obj_recepcion[32].toString() + "</span>");
                            out.print("</td>");
                        }
                        if (obj_recepcion[36] == null) {
                            out.print("<td align='center'>");
                            out.print("<span>N/A</span>");
                            out.print("</td>");
                        } else {
                            out.print("<td align='center'>");
                            out.print("<span>" + obj_recepcion[36].toString() + "</span>");
                            out.print("</td>");
                        }
                        out.print("</tr>");

                        out.print("</tbody>");
                        out.print("</table>");
                        out.print("</div>");
                        // </editor-fold>
                        out.print("</div>");
                        out.print("</section>");

                        out.print("</div>");
                        out.print("</div>");
                        out.print("</div>");
                    }
                }
            }
            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="MAESTRO DE RECEPCIONES">
            if (opc == 11) {
                out.print("<section class='section'>");
                out.print("<div class='section-header'>");
                out.print("<h1>Modulo Maestro de Recepciones</h1>");
                out.print("</div>");
                out.print("<div class='section-body'>");
                out.print("<div class='row'>");
                out.print("<div class='col-12'>");
                out.print("<div class='card'>");
                out.print("<div class='card-header' style='justify-content: space-between;'>");
                out.print("<h4>Tabla Maestro de Recepciones</h4>");
                out.print("</div>");
                out.print("<div class='card-body'>");
                out.print("<div class='table-responsive'>");
                out.print("<table class='table table-hover table-bordered' id='table-3'>");
                out.print("<thead>");
                out.print("<tr>");
                out.print("<th>Codigo</th>");
                out.print("<th>Referencia</th>");
                out.print("<th>Maestro de recepciones</th>");
                out.print("</tr>");
                out.print("</thead>");
                out.print("<tbody>");
                lst_MR = jpac_recepcion.Maestro_Recepciones();
                if (lst_MR != null) {
                    for (int i = 0; i < lst_MR.size(); i++) {
                        Object[] obj_MR = (Object[]) lst_MR.get(i);
                        String[] a = obj_MR[1].toString().split("/");
                        out.print("<tr>");
                        out.print("<td align='center'>" + a[0] + "</td>");
                        out.print("<td align='center'>" + a[1] + "</td>");
                        out.print("<td align='center'>");
                        if (txtPermisos.contains("[27]")) {
                            out.print(
                                    "<button type='button' onclick=\"javascript:location.href='RecepcionMaterial?opc=11&temp=3&idRegistro="
                                            + obj_MR[0].toString()
                                            + "'\" class='btn btn-success btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='R-GC-070'><i class=\"fas fa-eye\"></i></button>");
                        } else {
                            out.print(
                                    "<button class='btn btn-green btn-sm' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class=\"fas fa-eye\"></i></button>");
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
                out.print("</div>");
                out.print("</section>");

                // </editor-fold>
                // <editor-fold defaultstate="collapsed" desc="RECEPCION DE MATERIAL">
            } else if (opc == 1) {
                out.print("<section class='section'>");
                out.print("<div class='section-header'>");
                out.print("<h1>Modulo Recepcion de Material</h1>");
                out.print("</div>");
                out.print("<div class='section-body'>");
                out.print("<div class='row'>");
                out.print("<div class='col-12'>");
                out.print("<div class='card'>");
                out.print("<div class='card-header' style='justify-content: space-between;'>");
                out.print("<h4>Tabla Recepcion de Material</h4>");
                // <editor-fold defaultstate="collapsed" desc="FILTRO GENERAL">
                out.print(
                        "<form action='RecepcionMaterial?opc=1&tempF=2' method='post' class='needs-validation' novalidate=''>");
                out.print("<div class='d-flex align-items-center'>");
                if (txtPermisos.contains("[23]")) {
                    out.print("<input type='text' class='form-control' name='parametro' placeholder='Buscar' value='"
                            + (parametro != null ? parametro : "") + "' style='width: 190px; margin-right: 10px;'>");
                } else {
                    out.print(
                            "<input type='hidden' class='form-control' name='parametro' style='width: 190px; margin-right: 10px;'>");
                }
                if (txtPermisos.contains("[23]")) {
                    out.print(
                            "<button class='btn btn-green' style='border-radius: 4px;' data-toggle='tooltip' data-placement='top' title='Filtro General'><i class='fas fa-search'></i></button>");
                } else {
                    out.print(
                            "<button class='btn btn-green' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-search'></i></button>");
                }
                out.print("</div>");
                out.print("</form>");
                // <editor-fold defaultstate="collapsed" desc="SCRIPT DATE RANGE">
                // out.print("<script type=\"text/javascript\">");
                // out.print("$(document).ready(function() {");
                // out.print("var storedRange = localStorage.getItem('selectedRange');");
                // out.print("var start, end;");
                // out.print("if (storedRange) {");
                // out.print("var dates = storedRange.split(' - ');");
                // out.print("start = moment(dates[0], 'YYYY-MM-DD');");
                // out.print("end = moment(dates[1], 'YYYY-MM-DD');");
                // out.print("if (!start.isValid() || !end.isValid()) {");
                // out.print("start = moment().subtract(5, 'months').startOf('month');");
                // out.print("end = moment().endOf('month');");
                // out.print("}");
                // out.print("} else {");
                // out.print("start = moment().subtract(5, 'months').startOf('month');");
                // out.print("end = moment().endOf('month');");
                // out.print("}");
                // out.print("function hasRecordsForRange(start, end) {");
                // out.print("var recordsAvailable = true;");
                // out.print("if (start.isSame(moment(), 'day') && end.isSame(moment(), 'day'))
                // {");
                // out.print("recordsAvailable = false;");
                // out.print("}");
                // out.print("return recordsAvailable;");
                // out.print("}");
                // out.print("function cb(start, end, triggerChange = true) {");
                // out.print("var rangeText = start.format('YYYY-MM-DD') + ' / ' +
                // end.format('YYYY-MM-DD');");
                // out.print("$('#dateRangeInput').val(rangeText);");
                // out.print("$('#dateRangeButton').attr('title', rangeText);");
                // out.print("$('#selectedRange').val(rangeText);");
                // out.print("if (triggerChange) {");
                // out.print("$('#dateRangeInput').trigger('change');");
                // out.print("}");
                // out.print("localStorage.setItem('selectedRange', rangeText);");
                // out.print("}");
                // out.print("$('#reportrange').daterangepicker({");
                // out.print("startDate: start,");
                // out.print("endDate: end,");
                // out.print("maxDate: moment(),");
                // out.print("ranges: {");
                // out.print("'Hoy': [moment(), moment()],");
                // out.print("'Ayer': [moment().subtract(1, 'days'), moment().subtract(1,
                // 'days')],");
                // out.print("'Hace 7 Días': [moment().subtract(6, 'days'), moment()],");
                // out.print("'Este Mes': [moment().startOf('month'),
                // moment().endOf('month')],");
                // out.print("'El Mes Pasado': [moment().subtract(1, 'month').startOf('month'),
                // moment().subtract(1, 'month').endOf('month')],");
                // out.print("'Últimos 6 Meses': [moment().subtract(5,
                // 'months').startOf('month'), moment().endOf('month')]");
                // out.print("}");
                // out.print("}, function(start, end) {");
                // out.print("if (hasRecordsForRange(start, end)) {");
                // out.print("cb(start, end, true);");
                // out.print("} else {");
                // out.print("}");
                // out.print("});");
                // out.print("cb(start, end, false);");
                // out.print("$('#reportrange').on('apply.daterangepicker', function(ev, picker)
                // {");
                // out.print("if (hasRecordsForRange(picker.startDate, picker.endDate)) {");
                // out.print("$('#rangoForm').submit();");
                // out.print("} else {");
                // out.print("iziToast.warning({");
                // out.print(" title: 'Atencion',");
                // out.print(" message: 'El rango elegido no tiene registros',");
                // out.print("});");
                // out.print("}");
                // out.print("});");
                // out.print("});");
                // out.print("</script>");
                // </editor-fold>
                // </editor-fold>
                // <editor-fold defaultstate="collapsed" desc="CONVENCIONES">
                out.print("<div class='btn-group dropleft show'>");
                out.print(
                        "<button type='button' class='btn btn-green dropdown-toggle' style='border-radius: 4px;' aria-haspopup='true' aria-expanded='false' data-toggle='tooltip' data-placement='top' title='Convenciones' onclick='toggleDropdown()'>");
                out.print("<i class='fas fa-info'></i>");
                out.print("</button>");

                out.print(
                        "<div id='dropdownMenu' class='dropdown-menu dropleft' x-placement='left-start' style='display: none; position: absolute; transform: translate3d(-521px, 44px, 0px); top: 0px; left: 0px; will-change: transform; width: 1600% !important; border: 1px solid white; box-shadow: 0px 0px 13px 5px #e1e1e1;'>");
                out.print("<a class='dropdown-item'>Convenciones de las fases de la recepcion</a>");
                out.print("<div class='dropdown-divider'></div>");
                out.print(
                        "<a class='btn btn-sm btn-icon' style='background-color: #ffa900; border-radius: 4px; width: 10px; margin-left: 10px; padding-left: 6px; padding-right: 17px'><i style='color: white' class='fas fa-check'></i></a><a> Cuando la recepcion se encuentre en este estado significa que no se ha realizado la verificacion de la recepcion.</a><br>");
                out.print(
                        "<a class='btn btn-sm btn-icon' style='background-color: #9CEC5B; border-radius: 4px; width: 10px; margin-left: 10px; padding-left: 6px; padding-right: 17px'><i style='color: white' class='fas fa-scroll'></i></a><a> Cuando la recepcion se encuentre en este estado significa que no se ha realizado la validacion de la recepcion.</a><br>");
                out.print(
                        "<a class='btn btn-sm btn-icon' style='background-color: #50C5B7; border-radius: 4px; width: 10px; margin-left: 10px; padding-left: 6px; padding-right: 17px'><i style='color: white' class='fas fa-signature'></i></a><a> Cuando la recepcion se encuentre en este estado significa que faltan todas las firmas de los responsables.</a><br>");
                out.print(
                        "<a class='btn btn-sm btn-icon' style='background-color: #6184D8; border-radius: 4px; width: 10px; margin-left: 10px; padding-left: 6px; padding-right: 17px'><i style='color: white' class='fas fa-check-double'></i></a><a> Cuando la recepcion se encuentre en este estado significa que a la recepcion le faltan una(s) firmas.</a><br>");
                out.print(
                        "<a class='btn btn-sm btn-icon' style='background-color: #533A71; border-radius: 4px; width: 10px; margin-left: 10px; padding-left: 6px; padding-right: 17px'><i style='color: white' class='fas fa-lock'></i></a><a> El registro ya completo todas su fases y se encuentra disponible para inprimir como registro 051.</a>");
                out.print("</div>");
                out.print("</div>");
                // </editor-fold>
                // <editor-fold defaultstate="collapsed" desc="FILTRO REGISTROS SIN FIRMAR">
                out.print(
                        "<form action='RecepcionMaterial?opc=1' method='post' class='needs-validation' novalidate='' id='rangoForm'>");
                out.print("<!-- Rol: " + rol + " -->");
                rol_usuario = sesion.getAttribute("Rol/Nombres") != null ? sesion.getAttribute("Rol/Nombres").toString()
                        : "";
                out.print("<!-- Rol/Nombres: " + rol_usuario + " -->");
                try {
                    rol = rol_usuario.toString().split("/")[0];
                } catch (Exception e) {
                    rol = "";
                }
                if ((rol.equals("Jefe_almacen") || rol.equals("Administrador"))) {
                    rol_usuario = rol_usuario;
                }
                if (txtPermisos.contains("[24]")) {
                    out.print("<a href='RecepcionMaterial?opc=1&tempF=1&rol=" + rol
                            + "'  class='btn btn-green' style='border-radius: 4px;' data-toggle='tooltip' data-placement='top' title='Registros por firmar'><i class='fas fa-tasks'></i></a>");
                } else {
                    out.print(
                            "<button class='btn btn-green' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-tasks'></i></button>");
                }
                out.print("</form>");
                // </editor-fold>
                // <editor-fold defaultstate="collapsed" desc="FIRMAR MASIVO">
                out.print("<form action='RecepcionMaterial?opc=12' method='post' id='form_masivo'>");
                out.print("<input type='hidden' id='idrecep' name='idrecep'>");
                out.print("<input type='hidden' id='id_recp' name='id_recp'>");
                out.print("<input type='hidden' value='" + rol_usuario + "' name='firma'>");
                if (txtPermisos.contains("[25]")) {
                    out.print(
                            "<button type='button' onclick='enviar_masivo()' id='inactivarBtn' class='btn btn-green' style='border-radius: 4px;' data-toggle='tooltip' data-placement='top' title='Firmar Masivo'><i class='fas fa-user-edit'></i></button>");
                } else {
                    out.print(
                            "<button class='btn btn-green' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-user-lock'></i></button>");
                }
                out.print("</form>");

                out.print("<style>");
                out.print(".selected-row {");
                out.print("background-color: #c2f6d173;");
                out.print("}");
                out.print("</style>");
                // </editor-fold>
                if (txtPermisos.contains("[15]")) {
                    out.print(
                            "<button class='btn btn-green' style='border-radius: 4px;' onclick='mostrarConvencionR()' data-toggle='tooltip' data-placement='top' title='Registrar Recepcion'><i class='fas fa-plus'></i></button>");
                } else {
                    out.print(
                            "<button class='btn btn-green' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-plus'></i></button>");
                }
                out.print("</div>");
                out.print("<div class='card-body'>");
                out.print("<div class='table-responsive'>");
                out.print("<table class='table table-bordered table-hover' id='table-1'>");
                out.print("<thead>");
                out.print("<tr>");
                out.print("<th>ID</th>");
                out.print("<th>Fecha Recepcion</th>");
                out.print("<th>Clasificación</th>");
                out.print("<th>Referencia / Factory</th>");
                out.print("<th>Cantidad</th>");
                out.print("<th>Lote</th>");
                out.print("<th>CC</th>");
                out.print("<th>Proveedor</th>");
                out.print("<th>Fase</th>");
                out.print("<th>Anexos</th>");
                out.print("<th>R-GC-070</th>");
                out.print("<th>Opciones</th>");
                if (txtPermisos.contains("[90]")) {
                    out.print("<th>Eliminar</th>");
                }
                out.print("</tr>");
                out.print("</thead>");
                out.print("<tbody>");
                Lista_recepcion = jpac_recepcion.Lista_Recepciones();
                if (tempF == 1) {
                    if ((rol.equals("Calidad_despachos") || rol.equals("Administrador"))) {
                        Lista_recepcion = jpac_recepcion.Lista_Recepciones_sin_firmas(rol);
                    } else if ((rol.equals("Jefe_almacen") || rol.equals("Administrador"))) {
                        Lista_recepcion = jpac_recepcion.Lista_Recepciones_sin_firmas(rol);
                    } else if ((rol.equals("Jefe aseguramiento calidad") || rol.equals("Administrador"))) {
                        Lista_recepcion = jpac_recepcion.Lista_Recepciones_sin_firmas(rol);
                    }
                } else if (tempF == 2) {
                    if (parametro != null && !parametro.isEmpty()) {
                        Lista_recepcion = jpac_recepcion.filtro_general(parametro);
                    } else {
                        Lista_recepcion = jpac_recepcion.Lista_Recepciones();
                    }
                } else {
                    Lista_recepcion = jpac_recepcion.Lista_Recepciones();
                }
                if (tempp == 4) {
                    Lista_recepcion = jpac_recepcion.traer_recepcion_id(id_order);
                }
                for (int i = 0; i < Lista_recepcion.size(); i++) {
                    Object[] obj_lista = (Object[]) Lista_recepcion.get(i);
                    out.print("<tr>");
                    out.print("<td align='center' "
                            + (((Integer) obj_lista[13] == 1) ? " style='BACKGROUND-COLOR:#FFEAD9'"
                                    : (((Integer) obj_lista[13] == 2) ? "style='BACKGROUND-COLOR: #c9edff'" : ""))
                            + ">");
                    if (tempF == 1) {
                        out.print("<input type='checkbox' id='cbx_" + obj_lista[0] + "' onclick='Masivo(\""
                                + obj_lista[0] + "\", \"" + obj_lista[0] + "\")'>");
                        out.print("<input type='hidden' value='" + obj_lista[0] + "'>");
                    } else {
                        out.print("<input type='checkbox' id='cbx_" + obj_lista[0] + "' onclick='Masivo(" + obj_lista[0]
                                + ")' disabled>");
                    }
                    out.print("</td>");
                    out.print("<td align='center' "
                            + (((Integer) obj_lista[13] == 1) ? " style='BACKGROUND-COLOR:#FFEAD9'"
                                    : (((Integer) obj_lista[13] == 2) ? "style='BACKGROUND-COLOR:#c9edff'" : ""))
                            + ">" + obj_lista[2] + " - " + obj_lista[3] + "</td>");
                    out.print("<td align='center' "
                            + (((Integer) obj_lista[13] == 1) ? " style='BACKGROUND-COLOR:#FFEAD9'"
                                    : (((Integer) obj_lista[13] == 2) ? "style='BACKGROUND-COLOR:#c9edff'" : ""))
                            + ">" + obj_lista[1] + " - " + obj_lista[23] + "</td>");
                    out.print("<td align='center' "
                            + (((Integer) obj_lista[13] == 1) ? " style='BACKGROUND-COLOR:#FFEAD9'"
                                    : (((Integer) obj_lista[13] == 2) ? "style='BACKGROUND-COLOR:#c9edff'" : ""))
                            + ">" + obj_lista[4] + " / " + obj_lista[5] + "</td>");
                    out.print(
                            "<td align='center' "
                                    + (((Integer) obj_lista[13] == 1) ? " style='BACKGROUND-COLOR:#FFEAD9'"
                                            : (((Integer) obj_lista[13] == 2) ? "style='BACKGROUND-COLOR:#c9edff'"
                                                    : ""))
                                    + ">" + ((Number) obj_lista[15]).intValue() + " - " + obj_lista[16] + "</td>");
                    out.print("<td align='center' "
                            + (((Integer) obj_lista[13] == 1) ? " style='BACKGROUND-COLOR:#FFEAD9'"
                                    : (((Integer) obj_lista[13] == 2) ? "style='BACKGROUND-COLOR:#c9edff'" : ""))
                            + ">" + obj_lista[6] + "</td>");
                    out.print("<td align='center' "
                            + (((Integer) obj_lista[13] == 1) ? " style='BACKGROUND-COLOR:#FFEAD9'"
                                    : (((Integer) obj_lista[13] == 2) ? "style='BACKGROUND-COLOR:#c9edff'" : ""))
                            + ">" + obj_lista[7] + "</td>");
                    out.print("<td align='center' "
                            + (((Integer) obj_lista[13] == 1) ? " style='BACKGROUND-COLOR:#FFEAD9'"
                                    : (((Integer) obj_lista[13] == 2) ? "style='BACKGROUND-COLOR:#c9edff'" : ""))
                            + ">" + obj_lista[8] + "</td>");
                    // <editor-fold defaultstate="collapsed" desc="FASES">
                    fase = Integer.parseInt(obj_lista[11].toString());
                    out.print("<td align='center' "
                            + (((Integer) obj_lista[13] == 1) ? " style='BACKGROUND-COLOR:#FFEAD9'"
                                    : (((Integer) obj_lista[13] == 2) ? "style='BACKGROUND-COLOR:#c9edff'" : ""))
                            + ">");
                    if (fase == 1) {
                        if (txtPermisos.contains("[22]")) {
                            out.print(
                                    "<button type='button' onclick=\"javascript:location.href='RecepcionMaterial?opc=1&temp=1&idRegistro="
                                            + (Integer) obj_lista[0]
                                            + "'\" class='btn btn-sm btn-icon' style='background-color: #ffa900;' data-toggle='tooltip' data-placement='top' title='Fase actual: Verificación'><i style='color: white;' class=\"fas fa-check\"></i></button>");
                        } else {
                            out.print(
                                    "<button class='btn btn-green btn-sm' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i style='color: white;' class=\"fas fa-check\"></i></button>");
                        }
                    } else if (fase == 2) {
                        if (txtPermisos.contains("[22]")) {
                            out.print(
                                    "<button type='button' onclick=\"javascript:location.href='RecepcionMaterial?opc=1&temp=1&idRegistro="
                                            + (Integer) obj_lista[0]
                                            + "'\" class='btn btn-sm btn-icon' style='background-color: #9CEC5B;' data-toggle='tooltip' data-placement='top' title='Fase actual: Resumen'><i style='color: white;' class=\"fas fa-scroll\"></i></button>");
                        } else {
                            out.print(
                                    "<button class='btn btn-green btn-sm' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i style='color: white;' class=\"fas fa-scroll\"></i></button>");
                        }
                    } else if (fase == 3) {
                        String rolNombres = sesion.getAttribute("Rol/Nombres") != null
                                ? sesion.getAttribute("Rol/Nombres").toString()
                                : "";
                        out.print("<!-- Rol: " + rol + " -->");
                        out.print("<!-- Rol/Nombres: " + rolNombres + " -->");
                        try {
                            rol = rolNombres.toString().split("/")[0];
                        } catch (Exception e) {
                            rol = "";
                        }
                        if ((rol.equals("Calidad_despachos") || rol.equals("Administrador"))) {
                            if (txtPermisos.contains("[22]")) {
                                if (obj_lista[17] == null) {
                                    rol_usuario = rolNombres;
                                    out.print(
                                            "<button type='button' onclick=\"javascript:location.href='RecepcionMaterial?opc=1&temp=1&idRegistro="
                                                    + (Integer) obj_lista[0]
                                                    + "'\" class='btn btn-sm btn-icon' style='background-color: #50C5B7;' data-toggle='tooltip' data-placement='top' title='Fase actual: Firmas'><i style='color: white;' class=\"fas fa-signature\"></i></button>");
                                } else {
                                    out.print(
                                            "<button type='button' onclick=\"javascript:location.href='RecepcionMaterial?opc=1&temp=1&idRegistro="
                                                    + (Integer) obj_lista[0]
                                                    + "'\" class='btn btn-sm btn-icon' style='background-color: #6184D8;' data-toggle='tooltip' data-placement='top' title='Fase actual: Firmas incompletas'><i  style='color: white;'class=\"fas fa-check-double\"></i></button>");
                                }
                            } else {
                                out.print(
                                        "<button class='btn btn-green btn-sm' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i style='color: white;' class=\"fas fa-check-double\"></i></button>");
                            }
                        } else if ((rol.equals("Jefe_almacen") || rol.equals("Administrador"))) {
                            if (txtPermisos.contains("[22]")) {
                                if (obj_lista[17] == null) {
                                    rol_usuario = rolNombres;
                                    out.print(
                                            "<button type='button' onclick=\"javascript:location.href='RecepcionMaterial?opc=1&temp=1&idRegistro="
                                                    + (Integer) obj_lista[0]
                                                    + "'\" class='btn btn-sm btn-icon' style='background-color: #50C5B7;' data-toggle='tooltip' data-placement='top' title='Fase actual: Firmas'><i style='color: white;' class=\"fas fa-signature\"></i></button>");
                                } else {
                                    out.print(
                                            "<button type='button' onclick=\"javascript:location.href='RecepcionMaterial?opc=1&temp=1&idRegistro="
                                                    + (Integer) obj_lista[0]
                                                    + "'\" class='btn btn-sm btn-icon' style='background-color: #6184D8;' data-toggle='tooltip' data-placement='top' title='Fase actual: Firmas incompletas'><i  style='color: white;'class=\"fas fa-check-double\"></i></button>");
                                }
                            } else {
                                out.print(
                                        "<button class='btn btn-green btn-sm' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i style='color: white;' class=\"fas fa-check-double\"></i></button>");
                            }
                        } else if ((rol.equals("Jefe aseguramiento calidad") || rol.equals("Direccion_calidad")
                                || rol.equals("Administrador"))) {
                            if (txtPermisos.contains("[22]")) {
                                if (obj_lista[18] == null) {
                                    rol_usuario = rolNombres;
                                    out.print(
                                            "<button type='button' onclick=\"javascript:location.href='RecepcionMaterial?opc=1&temp=1&idRegistro="
                                                    + (Integer) obj_lista[0]
                                                    + "'\" class='btn btn-sm btn-icon' style='background-color: #50C5B7;' data-toggle='tooltip' data-placement='top' title='Fase actual: Firmas'><i style='color: white;' class=\"fas fa-signature\"></i></button>");
                                } else {
                                    out.print(
                                            "<button type='button' onclick=\"javascript:location.href='RecepcionMaterial?opc=1&temp=1&idRegistro="
                                                    + (Integer) obj_lista[0]
                                                    + "'\" class='btn btn-sm btn-icon' style='background-color: #6184D8;' data-toggle='tooltip' data-placement='top' title='Fase actual: Firmas incompletas'><i  style='color: white;'class=\"fas fa-check-double\"></i></button>");
                                }
                            } else {
                                out.print(
                                        "<button class='btn btn-green btn-sm' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i style='color: white;' class=\"fas fa-check-double\"></i></button>");
                            }
                        } else {
                            out.print(
                                    "<button class='btn btn-green btn-sm' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i style='color: white;' class=\"fas fa-check-double\"></i></button>");
                        }

                    } else if (fase >= 4) {
                        out.print(
                                "<button type='button' onclick=\"javascript:location.href='RecepcionMaterial?opc=1&temp=2&idRegistro="
                                        + (Integer) obj_lista[0]
                                        + "'\" class='btn btn-sm btn-icon' style='background-color: #533A71;' data-toggle='tooltip' data-placement='top' title='Fases Finalizadas: Imprimir R-GC-051'><i style='color: white;' class=\"fas fa-lock\"></i></button>");
                    } else {
                    }
                    out.print("</td>");
                    // </editor-fold>
                    // <editor-fold defaultstate="collapsed" desc="ANEXOS">
                    out.print("<td align='center' "
                            + (((Integer) obj_lista[13] == 1) ? " style='BACKGROUND-COLOR:#FFEAD9'"
                                    : (((Integer) obj_lista[13] == 2) ? "style='BACKGROUND-COLOR:#c9edff'" : ""))
                            + ">");
                    out.print("<div class='btn-container'>");
                    if (txtPermisos.contains("[21]")) {
                        out.print(
                                "<button type='button' onclick=\"javascript:location.href='RecepcionMaterial?opc=1&id_add_anexo="
                                        + obj_lista[0]
                                        + "'\" class='btn btn-dark btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Anexar documento'><i class='fas fa-paperclip'></i></button>");
                    } else {
                        out.print(
                                "<button class='btn btn-green btn-sm' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-paperclip'></i></button>");
                    }
                    List lst_contador = jpac_recepcion.Consulta_anexo_union(Integer.parseInt(obj_lista[0].toString()),
                            "R-GC-051");
                    String spanClass = "btn btn-primary btn-xs";
                    out.print("<span class='" + spanClass + "'>" + lst_contador.size() + "</span>");
                    out.print("</div>");
                    out.print("</td>");
                    // </editor-fold>
                    // <editor-fold defaultstate="collapsed" desc="R-GC-070">
                    out.print("<td align='center' "
                            + (((Integer) obj_lista[13] == 1) ? " style='BACKGROUND-COLOR:#FFEAD9'"
                                    : (((Integer) obj_lista[13] == 2) ? "style='BACKGROUND-COLOR:#c9edff'" : ""))
                            + ">");
                    if (txtPermisos.contains("[20]")) {
                        if (fase == 1 || fase == 2 || fase == 3) {
                            out.print("<button href='#' class='btn btn-"
                                    + ((fase == 1) ? "secondary"
                                            : (fase == 2) ? "secondary" : (fase == 3) ? "secondary" : "danger")
                                    + " info btn-sm btn-icon' style='cursor: not-allowed;' data-toggle='tooltip' data-placement='top' title='En proceso'><i class=\"fas fa-eye\"></i></button>");
                        } else if (fase >= 4) {
                            if (obj_lista[14].equals(1)) {
                                out.print(
                                        "<button type='button' onclick=\"javascript:location.href='RecepcionMaterial?opc=1&temp=3&idRegistro="
                                                + (Integer) obj_lista[0]
                                                + "'\" class='btn btn-success info btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='R-GC-070'><i class=\"fas fa-eye\"></i></button>");
                            } else {
                                out.print(
                                        "<button href='#' class='btn btn-secondary info btn-sm btn-icon' style='cursor: not-allowed;' data-toggle='tooltip' data-placement='top' title='No aplica'><i class=\"fas fa-eye\"></i></button>");
                            }
                        }
                    } else {
                        out.print(
                                "<button class='btn btn-green btn-sm' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class=\"fas fa-eye\"></i></button>");
                    }
                    out.print("</td>");
                    // </editor-fold>
                    // <editor-fold defaultstate="collapsed" desc="OPCIONES">
                    out.print("<td align='center' "
                            + (((Integer) obj_lista[13] == 1) ? " style='BACKGROUND-COLOR:#FFEAD9'"
                                    : (((Integer) obj_lista[13] == 2) ? "style='BACKGROUND-COLOR:#c9edff'" : ""))
                            + ">");
                    out.print("<div style='margin-left:5px;' class='dropdown d-inline'>\n");
                    out.print(
                            "<button class='btn btn-primary btn-sm dropdown-toggle' type='button' id='opciones' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>\n");
                    out.print("<i class='fas fa-cog'></i>");
                    out.print("</a><div class='dropdown-menu menuDropdow'>");
                    if (txtPermisos.contains("[19]")) {
                        if (!sesion.getAttribute("Nombre_rol").equals("Jefe_almacen")
                                || !sesion.getAttribute("Nombre_rol").equals("Coordinacion")) {
                            if ((Integer) obj_lista[11] < 4) {
                                out.print("<a class='dropdown-item has-icon' onclick=\"EliminarRecepcionEstado("
                                        + obj_lista[0]
                                        + ")\" style='color:#34495e;' data-toggle='tooltip' data-placement='right' title='Eliminar recepcion' ><i class=\"far fa-trash-alt fa-size_small\"></i> Eliminar</a>");
                            } else {
                                out.print(
                                        "<a class='dropdown-item has-icon' style='color:#c1c1c1;cursor: no-drop;opacity:0.5;' data-toggle='tooltip' data-placement='right' title='No tiene permisos' ><i style='color:#c1c1c1;' class=\"far fa-trash-alt fa-size_small\"></i> Eliminar</a>");
                            }
                        } else {
                            out.print(
                                    "<a class='dropdown-item has-icon' style='color:#c1c1c1;cursor: no-drop;opacity:0.5;' data-toggle='tooltip' data-placement='right' title='No tiene permisos' ><i style='color:#c1c1c1;' class=\"far fa-trash-alt fa-size_small\"></i> Eliminar</a>");
                        }
                    }
                    if (txtPermisos.contains("[18]")) {
                        if (!sesion.getAttribute("Nombre_rol").equals("Jefe_almacen")
                                && !sesion.getAttribute("Nombre_rol").equals("Coordinacion")) {
                            out.print("<a class='dropdown-item has-icon' onclick=\"PrestamoRecepcion(" + obj_lista[0]
                                    + ",'" + obj_lista[16]
                                    + "')\" style='color:#34495e;' data-toggle='tooltip' data-placement='right' title='Prestamo' ><i class=\"fas fa-hands-helping\"></i> Prestamo</a>");
                        } else {
                            out.print(
                                    "<a class='dropdown-item has-icon' style='color:#c1c1c1;cursor: no-drop;opacity:0.5;' data-toggle='tooltip' data-placement='right' title='No tiene permisos' ><i style='color:#c1c1c1;' class=\"fas fa-hands-helping\"></i> Prestamo</a>");
                        }
                    }
                    if (txtPermisos.contains("[17]")) {
                        if (!sesion.getAttribute("Nombre_rol").equals("Coordinacion")) {
                            out.print("<input type='text' value='" + obj_lista[20] + "' id='OBS" + obj_lista[0]
                                    + "' hidden>");
                            out.print("<a class='dropdown-item has-icon' onclick=\"RegistrarObservacion(" + obj_lista[0]
                                    + ")\" style='color:#34495e;' data-toggle='tooltip' data-placement='right' title='Observacion' ><i class=\"fas fa-sticky-note fa-size_small\"></i> Observacion</a>");
                        } else {
                            out.print(
                                    "<a class='dropdown-item has-icon' style='color:#c1c1c1;cursor: no-drop;opacity:0.5;' data-toggle='tooltip' data-placement='right' title='No tiene permisos' ><i style='color:#c1c1c1;' class=\"fas fa-sticky-note fa-size_small\"></i> Observacion</a>");
                        }
                    }
                    if (txtPermisos.contains("[16]")) {
                        if (!sesion.getAttribute("Nombre_rol").equals("Coordinacion")) {
                            if ((Integer) obj_lista[11] < 4) {
                                out.print(
                                        "<a onclick=\"javascript:location.href='RecepcionMaterial?opc=1&temp=0&idRegistro="
                                                + obj_lista[0]
                                                + "'\" class='dropdown-item has-icon' style='color:#34495e;' data-toggle='tooltip' data-placement='right' title='Modificar'><i class='fas fa-pencil-alt'></i> Modificar</a>");
                            } else {
                                out.print(
                                        "<a class='dropdown-item has-icon' style='color:#c1c1c1;cursor: no-drop;opacity:0.5;' data-toggle='tooltip' data-placement='right' title='No tiene permisos' ><i style='color:#c1c1c1;' class=\"fas fa-sticky-note fa-size_small\"></i> Modificar</a>");
                            }
                        } else {
                            out.print(
                                    "<a class='dropdown-item has-icon' style='color:#c1c1c1;cursor: no-drop;opacity:0.5;' data-toggle='tooltip' data-placement='right' title='No tiene permisos' ><i style='color:#c1c1c1;' class=\"fas fa-sticky-note fa-size_small\"></i> Modificar</a>");
                        }
                    }
                    out.print("</div>");
                    out.print("</div>");
                    out.print("</td>");
                    // </editor-fold>
                    // <editor-fold defaultstate="collapsed" desc="ELIMINAR">
                    if (txtPermisos.contains("[90]")) {
                        out.print("<td align='center' "
                                + (((Integer) obj_lista[13] == 1) ? " style='BACKGROUND-COLOR:#FFEAD9'"
                                        : (((Integer) obj_lista[13] == 2) ? "style='BACKGROUND-COLOR:#c9edff'" : ""))
                                + ">");
                        out.print("<button onclick='EliminarRegistro(" + obj_lista[0]
                                + ")' class='btn btn-danger btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Eliminar registro'><i class='far fa-trash-alt fa-size_small text-white'></i></button>");
                        out.print("</td>");
                    }
                    // </editor-fold>
                    out.print("</tr>");
                }
                out.print("</tbody>");
                out.print("</table>");
                out.print("</div>");
                out.print("</section>");
            }
            // </editor-fold>
        } catch (IOException ex) {
            Logger.getLogger(RecepcionMaterial.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }
}
