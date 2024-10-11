package Tag;

import Controladores.ControlConsecutivosJpaController;
import Controladores.ProcesosJpaController;
import Controladores.PruebasJpaController;
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

public class Controlconsecutivos extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            //<editor-fold defaultstate="collapsed" desc="JPAS">
            ControlConsecutivosJpaController ctrlcons = new ControlConsecutivosJpaController();
            ProcesosJpaController jpapro = new ProcesosJpaController();
            PruebasJpaController jpapruebas = new PruebasJpaController();
            ReferenciasInv refinv = new ReferenciasInv();
            RolJpaController jpacrol = new RolJpaController();
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="VARIABLES">
            List lst_lotes = null;
            Date fecha = new Date();
            double hora_minuto = Double.parseDouble(fecha.getHours() + "." + fecha.getMinutes());
            String anio = (fecha.getYear() + 1900) + "";
            String mes = fecha.getMonth() + 1 + "";
            String dia = (fecha.getDate() < 10 ? "0" : "") + "" + fecha.getDate() + "";
            String fecha_actual = anio + "-" + mes + "-" + dia;
            int cns, id_ctrcons;
            String consecutivo = "";
            List lst_lotess = null;
            List lst_enlace = null;
            List lst_prueba = null;
            List lst_cons;
            int filtro, temp, id_order = 0;
            int id_anexos;
            String nombre_proceso;
            String formula = "";
            String clase = "";
            int id_registro;
            int id_add_anexo;
            String ultimolote;
            List anexoNew;
            int year;
            Object obj_rango;
            List lista_rango;
            Calendar c = Calendar.getInstance();
            List lst_rol_id = null;
            int id_rol, id_rol_permission, idRol = 0;
            String txtPermisos = "";
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="VARIABLES DE CONTEXTO">
            String rol = pageContext.getSession().getAttribute("Nombre_rol").toString();
            try {
                id_ctrcons = Integer.parseInt(pageContext.getRequest().getAttribute("id_ctrcons").toString());
            } catch (Exception e) {
                id_ctrcons = 0;
            }
            try {
                filtro = Integer.parseInt(pageContext.getRequest().getAttribute("Flt_Id_proceso").toString());
            } catch (Exception e) {
                filtro = 0;
            }
            try {
                nombre_proceso = pageContext.getRequest().getAttribute("Nombre_proceso").toString();
            } catch (Exception e) {
                nombre_proceso = "";
            }
            try {
                year = Integer.parseInt(pageContext.getRequest().getAttribute("year").toString());
            } catch (Exception e) {
                year = c.get(Calendar.YEAR);
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
                formula = pageContext.getRequest().getAttribute("Formula").toString();
            } catch (Exception e) {
                formula = "";
            }
            try {
                id_registro = Integer.parseInt(pageContext.getRequest().getAttribute("id_registro").toString());
            } catch (Exception e) {
                id_registro = 0;
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
            //<editor-fold defaultstate="collapsed" desc="ANEXOS">
            if (id_add_anexo > 0) {
                out.print("<div class='sweet-local' tabindex='-1' id='Ventana4' style='opacity: 1.03; display:block;'>");
                out.print("<div class='cont_reg3'>");
                out.print("<div style='display: flex; justify-content: space-between'>");
                out.print("<h2>ANEXOS</h2>");
                out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(4)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
                out.print("</div>");
                out.print("<div class='cont_form_user' style='display: flex;'>");
                out.print("<div class='col-lg-4' style='border-right: 1px solid #00281b63;'>");
                if (id_anexos == 0) {
                    //<editor-fold defaultstate="collapsed" desc="REGISTRAR ANEXO">
                    out.print("<form action='Documentos.jsp' method='post' enctype='multipart/form-data' class='needs-validation' novalidate=''>");
                    out.print("<h6 align='center'>Registrar Anexos</h6>");

                    out.print("<input type='hidden' name='id_add_anexo' id='idRegistro_anexo' value='" + (id_add_anexo != 0 ? id_add_anexo : "null") + "'>");
                    out.print("<input type='hidden' name='id_m_anexo' value='" + id_anexos + "'>");
                    out.print("<input type='hidden' name='Flt_Id_proceso' value='" + filtro + "'>");
                    out.print("<input type='hidden' name='nombre_proceso' value='" + nombre_proceso + "'>");
                    out.print("<input type='hidden' name='year' value='" + year + "'>");

                    out.print("<div class='col-12' style='margin-top:8%; margin-left:4%; width:101%;'>");
                    out.print("<textarea class='form-control' name='Txt_descripcion' id='Txt_descripcion' placeholder='Descripcion' required data-toggle='tooltip' data-placemente='top' title='Descripcion'></textarea>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");
                    out.print("<br>");
                    out.print("<div class='col-12'>");
                    out.print("<input type='hidden' class='form-control' name='IdVisit' id='IdVisit' value='0'>");
                    out.print("<div style='position: relative; width: 100%; height: 40px; margin-left:4%;'>");
                    out.print("<input type='file' class='custom-file-input' name='file_name' id='IdFile' style='position: absolute; width: 100%; height: 100%; margin: 0; opacity: 0; cursor: pointer;' placeholder='Seleccione archivo' required data-toggle='tooltip' data-placement='top' title='Seleccione archivo' onchange='updateFileName()'>");
                    out.print("<label class='custom-file-label' for='IdFile' style='position: absolute; top: 0; left: 0; width: 100%; height: 42px; line-height: 40px; padding: 0 15px; background-color: #fff; border: 1px solid #ced4da; border-radius: 4px; cursor: pointer;'>Seleccione un archivo</label>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
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

                    out.print("<script>");
                    out.print("document.getElementById('IdFile').addEventListener('change', function() { ");
                    out.print("var input = this; ");
                    out.print("var NameFile = input.files[0].name; ");
                    out.print("var DownloadFile = document.getElementById('DownloadFile'); ");
                    out.print("DownloadFile.innerHTML = '<a class=\"btn btn-info\" href=\"' + URL.createObjectURL(input.files[0]) + '\" download=\"' + NameFile + '\">Ver archivo <i class=\"fas fa-download\"></i></a>'; ");
                    out.print("});");
                    out.print("</script>");

                    out.print("<div class='' style='width: 100%; text-align:center; margin-top: 50px;'>");
                    out.print("<button class='btn btn-green btn-lg'>Registrar</button>");
                    out.print("</div>");

                    out.print("</form>");
                    //</editor-fold>
                } else {
                    //<editor-fold defaultstate="collapsed" desc="MODIFICAR ANEXO">
                    out.print("<form action='Documentos.jsp' method='post' enctype='multipart/form-data' class='needs-validation' novalidate=''>");
                    out.print("<h6 align='center'>Modificar Anexos</h6>");

                    out.print("<input type='hidden' name='id_add_anexo' id='idRegistro_anexo' value='" + (id_add_anexo != 0 ? id_add_anexo : "null") + "'>");
                    out.print("<input type='hidden' name='id_m_anexo' value='" + id_anexos + "'>");
                    out.print("<input type='hidden' name='Flt_Id_proceso' value='" + filtro + "'>");
                    out.print("<input type='hidden' name='nombre_proceso' value='" + nombre_proceso + "'>");
                    out.print("<input type='hidden' name='year' value='" + year + "'>");

                    anexoNew = ctrlcons.Consulta_anexo_id_new(id_anexos);
                    for (int n = 0; n < anexoNew.size(); n++) {
                        Object[] obje_anexos = (Object[]) anexoNew.get(n);

                        out.print("<div class='col-12'>");
                        out.print("<div style='position: relative; width: 100%; height: 40px; margin-left:4%;'>");
                        out.print("<input type='file' class='custom-file-input' name='file_name' id='IdFile' value='" + obje_anexos[3] + "' style='position: absolute; width: 100%; height: 100%; margin: 0; opacity: 0; cursor: pointer;' placeholder='Seleccione archivo' required data-toggle='tooltip' data-placement='top' title='Seleccione archivo' onchange='updateFileName()'>");
                        out.print("<label class='custom-file-label' for='IdFile' style='position: absolute; top: 0; left: 0; width: 100%; height: 42px; line-height: 40px; padding: 0 15px; background-color: #fff; border: 1px solid #ced4da; border-radius: 4px; cursor: pointer;'>Seleccione un archivo</label>");
                        out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
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
                        out.print("<textarea type='text' class='form-control' name='Txt_descripcion' id='' required data-toggle='tooltip' data-placemente='top' title='Descripcion'>" + obje_anexos[4].toString().trim() + "</textarea>");
                        out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                        out.print("</div>");
                        out.print("<br>");

                        out.print("<input type='hidden' name='conin' value='" + obje_anexos[3] + "'>");

                        out.print("<script>");
                        out.print("document.getElementById('IdFile').addEventListener('change', function() { ");
                        out.print("var input = this; ");
                        out.print("var NameFile = input.files[0].name; ");
                        out.print("var DownloadFile = document.getElementById('DownloadFile'); ");
                        out.print("DownloadFile.innerHTML = '<a class=\"btn btn-info\" href=\"' + URL.createObjectURL(input.files[0]) + '\" download=\"' + NameFile + '\">Ver archivo <i class=\"fas fa-download\"></i></a>'; ");
                        out.print("});");
                        out.print("</script>");

                        out.print("<div class='' style='width: 100%; text-align:center;'>");
                        out.print("<button class='btn btn-green btn-lg'>Modificar</button>");
                        out.print("</div>");
                    }
                    out.print("</form>");

                    //</editor-fold>
                }
                out.print("</div>");
                out.print("<div class='col-lg-8'>");
                out.print("<h6 align='center'>Historial de Adjuntos</h6>");
                out.print("<div class='card-body'>");
                out.print("<div class='table-responsive'>");
                out.print("<table class='table table-bordered' id='table-2'>");
                out.print("<thead>");
                out.print("<tr>");
                out.print("<th>Adjunto</th>");
                out.print("<th>Fecha</th>");
                out.print("<th>Observaciones</th>");
                out.print("<th>Modificar</th>");
                out.print("</tr>");
                out.print("</thead>");
                out.print("<tbody>");
                
                anexoNew = ctrlcons.Consulta_anexo_id(id_add_anexo, "R-GC-059");
                if (anexoNew == null || anexoNew.isEmpty()) {
                    out.print("<tr>");
                    out.print("<td colspan='4' align='center'>");
                    out.print("<b style='color: orange;'>No hay archivos adjuntos.</b>");
                    out.print("</td>");
                    out.print("</tr>");
                } else {
                    for (int i = 0; i < anexoNew.size(); i++) {
                        Object[] obj_anexos = (Object[]) anexoNew.get(i);
                        out.print("<tr>");
                        out.print("<td align='center'>");
                        if ((Integer) obj_anexos[6] == 0) {
                            out.print(obj_anexos[3]);
                        } else if ((Integer) obj_anexos[6] == 1) {
                            out.print("<a href='Descargar?file_name=" + obj_anexos[3] + "&carpeta=" + obj_anexos[2] + "'  target='_blank' >" + obj_anexos[3] + "</a>");
                        } else {
                            out.print("<button type='button' onclick='window.location.href=\"Download?File_name=" + obj_anexos[3] + "\"' class='btn btn-primary btn-sm btn-icon'>Ver archivo <i class='fas fa-file-download'></i></button>");
                        }
                        out.print("</td>");
                        out.print("<td align='center'>" + obj_anexos[5] + "</td>");
                        out.print("<td align='center'><b>" + obj_anexos[4] + "</b><br /></td>");
                        out.print("<td align='center'>");
                        if ((Integer) obj_anexos[6] == 1) {
                            out.print("<button href='#' class='btn btn-secondary info btn-sm btn-icon' style='cursor: not-allowed;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-pencil-alt'></i></button>");
                        } else {
                            out.print("<a href='cc?opc=1&id_add_anexo=" + id_add_anexo + "&id_m_anexo=" + obj_anexos[0] + "&Flt_Id_proceso=" + filtro + "&nombre_proceso=" + nombre_proceso + "&year=" + year + "'><i class='btn btn-primary btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Modificar Anexo'><i class='fas fa-pencil-alt'></i></a>");
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
            }
            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="MODIFICAR">
            if (id_ctrcons > 0) {
                List lst_concecutivo = ctrlcons.Traer_Controlconsecutivos(id_ctrcons);
                Object[] obj_consc = (Object[]) lst_concecutivo.get(0);
                out.print("<div class='sweet-local' tabindex='-1' id='Ventana3' style='opacity: 1.03; display:block;'>");
                out.print("<div class='cont_reg'>");
                out.print("<div style='display: flex; justify-content: space-between'>");
                out.print("<h2>Modificar Control Concecutivo</h2>");
                out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(3)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
                out.print("</div>");
                out.print("<div class='cont_form_user'>");
                out.print("<form action='cc?opc=2&id_ctrcons=" + id_ctrcons + "&Flt_Id_proceso=" + filtro + "&Nombre_proceso=" + nombre_proceso + "&year=" + year + "' id='form' method='post' class='needs-validation' novalidate=''>");

                out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                out.print("<div class='col-12'>");
                out.print("<input type='text' class='form-control' name='' placeholder='Referencia Producto' value='" + obj_consc[2] + "' required data-toggle='tooltip' data-placement='top' title='Referencia Producto' disabled='disabled'>");
                out.print("<input type='hidden' name='conin' value='" + obj_consc[2] + "'>");
                out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                out.print("</div>");

                out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-left:3%; margin-right:-3%;'>");
                out.print("<select class='select2' name='pruebas'>");
                out.print("<option value='" + obj_consc[17] + "' style='display:none;'>" + obj_consc[17] + "</option>");
                List lst_pruebas = jpapruebas.Pruebas();
                for (int j = 0; j < lst_pruebas.size(); j++) {
                    Object[] obj_pruebas = (Object[]) lst_pruebas.get(j);
                    out.print("<option>" + obj_pruebas[1] + "</option>");
                }
                out.print("</select>");
                out.print("</div>");
                out.print("</div>");

                String lote = obj_consc[3].toString();
                String[] jj = lote.split("-");
                String lote1 = jj[0];
                String lote2 = jj[1];
                out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                out.print("<div class='col-12'>");
                out.print("<input type='text' class='form-control' name='lote_1' placeholder='Lote 1' value='" + lote1 + "' onchange=\"javascript:this.value=this.value.toUpperCase();\" required data-toggle='tooltip' data-placement='top' title='Lote 1'>");
                out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                out.print("</div>");

                out.print("<div class='col-12'>");
                out.print("<input type='text' class='form-control' name='lote_2' placeholder='Lote 2' value='" + lote2 + "' onchange=\"javascript:this.value=this.value.toUpperCase();\" required data-toggle='tooltip' data-placement='top' title='Lote 2'>");
                out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                out.print("</div>");
                out.print("</div>");

                out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                out.print("<div class='col-12'>");
                out.print("<input type='date' class='form-control' id='datepicker' name='fecha' placeholder='Fecha' value='" + obj_consc[4] + "' required data-toggle='tooltip' data-placement='top' title='Fecha'>");
                out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                out.print("</div>");

                out.print("<div class='col-12' style='margin-top:2.5%; margin-left:2.7%; width:94%;'>");
                out.print("<textarea class='form-control' name='obs' placeholder='Observaciones' required data-toggle='tooltip' data-placement='top' title='Observaciones'>" + obj_consc[7] + "</textarea>");
                out.print("<div class='invalid-feedback'><i class='fas fa-exclamation-circle'></i> Debe ingresar un valor!</div>");
                out.print("</div>");
                out.print("</div>");

                out.print("<div class='' style='width: 100%; text-align:center;'>");
                out.print("<button class='btn btn-green btn-lg'>Editar</button>");
                out.print("</div>");

                out.print("</form>");
                out.print("</div>");
                out.print("</div>");
                out.print("</div>");
            }
            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="REGISTRAR">
            if (id_registro > 0) {
                out.print("<div class='sweet-local' tabindex='-1' id='Ventana1' style='opacity: 1.03; display:block;'>");
                out.print("<div class='cont_reg2'>");
                out.print("<div style='display: flex; justify-content: space-between'>");
                out.print("<h2>Ingresar Formula</h2>");
                out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(1)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
                out.print("</div>");
                out.print("<div class='cont_form_user'>");
                out.print("<form action='cc?opc=1&id_registro=" + filtro + "&Nombre_proceso=" + nombre_proceso + "&Flt_Id_proceso=" + filtro + "&year=" + year + "' method='post' class='needs-validation' novalidate='' id='form_registro'>");
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
                    out.print("<div class='cont_reg' style='width: 40%'>");
                    out.print("<div style='display: flex; justify-content: space-between'>");
                    out.print("<h2>Registrar</h2>");
                    out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(2);CerrarModalFormula();' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
                    out.print("</div>");
                    out.print("<div class='cont_form_user'>");
                    out.print("<form action='cc?opc=2&Formula=" + formula + "&Flt_Id_proceso=" + filtro + "&Nombre_proceso=" + nombre_proceso + "&year=" + year + "' method='post' class='needs-validation' novalidate=''>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='Formula' id='Formula' placeholder='Formula' value='" + formula + "' required='' data-toggle='tooltip' data-placemente='top' title='Formula' disabled='disabled'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-left:3%; margin-right:-3%;'>");
                    out.print("<select class='select2' name='Nombre' data-toggle='tooltip' data-placement='top' style='width: 303px !important'>");
                    out.print("<option value='empty'>Seleccione una formula</option>");
                    lst_lotes = refinv.Productos(formula);
                    if (lst_lotes != null) {
                        for (int b = 0; b < lst_lotes.size(); b++) {
                            out.print("<option value='" + lst_lotes.get(b) + "' > " + lst_lotes.get(b) + "</option>");
                        }
                    }
                    out.print("</select>");
                    out.print("</div>");
                    out.print("</div>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-left:3%; margin-right:-3%;'>");
                    out.print("<select class='select2' name='slc_prueba' data-toggle='tooltip' data-placement='top'>");
                    out.print("<option value='empty'>Seleccione una prueba</option>");
                    List lst_pruebas = jpapruebas.Pruebas();
                    if (lst_pruebas != null) {
                        for (int i = 0; i < lst_pruebas.size(); i++) {
                            Object[] obj_Pruebas = (Object[]) lst_pruebas.get(i);
                            out.print("<option value='" + obj_Pruebas[1] + "' > " + obj_Pruebas[1] + "</option>");
                        }
                    }
                    out.print("</select>");
                    out.print("</div>");

                    out.print("<div class='col-12'>");
                    out.print("<input type='date' class='form-control' name='fecha' id='datepicker' placeholder='Fecha' value='" + fecha_actual + "' required data-toggle='tooltip' data-placemente='top' title='Fecha'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");
                    out.print("</div>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");
                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='lote_1' id='lote_1' placeholder='Lote 1' value='" + formula + "' onchange=\"javascript:this.value=this.value.toUpperCase();\" required data-toggle='tooltip' data-placemente='top' title='Lote 1' disabled='disabled'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='lote_2' id='lote_2' placeholder='Lote 2' required data-toggle='tooltip' data-placemente='top' title='Lote 2' onchange='javascript:this.value=this.value.toUpperCase();' onkeyup=\"validar();Fecha();\">");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");
                    out.print("</div>");

                    out.print("<div class='col-12' style='margin-top:1%; margin-left:2.7%; width:94%;'>");
                    out.print("<textarea class='form-control' name='obs' id='obs' placeholder='Observaciones' required data-toggle='tooltip' data-placemente='top' title='Observaciones'></textarea>");
                    out.print("<div class='invalid-feedback'><i class='fas fa-exclamation-circle'></i> Debe ingresar un valor!</div>");
                    out.print("</div>");
                    out.print("<br>");

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
            //<editor-fold defaultstate="collapsed" desc="TABLA">
            out.print("<section class='section'>");
            out.print("<div class='section-header'>");
            out.print("<h1>Modulo Control Consecutivos</h1>");
            out.print("</div>");
            out.print("<div class='section-body'>");
            out.print("<div class='row'>");
            out.print("<div class='col-12'>");
            out.print("<div class='card'>");
            out.print("<div class='card-header' style='justify-content: space-between;'>");
            out.print("<h4>Tabla Control Consecutivos</h4>");
            out.print("</div>");
            out.print("<div class='card-body'>");
            out.print("<div class='table-responsive'>");
            //<editor-fold defaultstate="collapsed" desc="FILTRO PROCESO">
            if (filtro == 0) {
                lst_cons = ctrlcons.Traer_consecutivofinal(year);
            } else {
                lst_cons = ctrlcons.Control_consecutivos(filtro, year);
            }
            List lst_procesos = jpapro.Procesos_tipo("Materia_Prima");

            out.print("<div style='display: flex; align-items: center; justify-content: space-between;'>");

            out.print("<div style='display: flex; align-items: center;'>");

            out.print("<div style='margin-right: 20px;'>");
            out.print("<form action='cc?opc=1' method='post' name='filtro'>");
            out.print("<select class='form-control select2' name='slc_proceso' id='slc_proceso' title='Procesos' onchange='document.filtro.submit();' style='width: 180px'>");
            out.print("<option value='empty'>Seleccionar Proceso</option>");
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
            out.print("</select>");
            out.print("</form>");
            out.print("</div>");

            if (!rol.equals("Consulta")) {
                if (filtro != 0) {
                    out.print("<div style='margin-right: 20px;'>");
                    if (txtPermisos.contains("[36]")) {
                        out.print("<a class='btn btn-green' style='border-radius: 4px;' href='cc?opc=1&id_registro=" + filtro + "&Nombre_proceso=" + nombre_proceso + "&Flt_Id_proceso=" + filtro + "&year=" + year + "' data-toggle='tooltip' data-placement='top' title='Registrar Consecutivo'><i class='fas fa-plus'></i></a>");
                    } else {
                        out.print("<button class='btn btn-green' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-plus'></i></button>");
                    }
                    out.print("</div>");
                }
            }
            out.print("</div>");
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="FINALIZAR MASIVO">
            out.print("<form action='cc?opc=7' method='post' id='form_masivo'>");
            out.print("<input type='hidden' id='id_cons' name='id_cons'>");
            out.print("<input type='hidden' id='cc_cons' name='cc_cons'>");
            out.print("<input type='hidden' value='1' name='est'>");
            if (txtPermisos.contains("[44]")) {
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
            out.print("<form action='cc?opc=6' method='post' id='FormAnio' onsubmit='updateYear()'>");
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
            out.print("<td class='text-center title-cell' style='width: 50%; font-weight: bold;'> REGISTRO CONTROL DE CONSECUTIVOS</td>");
            out.print("<td class='text-center code-version-cell' style='width: 25%; text-align: center; font-weight: bold;'>CÓDIGO R-GC-059</td>");
            out.print("</tr>");
            out.print("<tr>");
            out.print("<td style='width: 50%; text-align: center; font-weight: bold;'>TODOS</td>");
            out.print("<td style='width: 25%; text-align: center; font-weight: bold;'>VERSIÓN 002</td>");
            out.print("</tr>");
            out.print("</tbody>");
            out.print("</table>");

            out.print("<table class='table table-bordered table-hover' id='table-1'>");
            out.print("<thead>");
            out.print("<tr>");
            out.print("<th>ID</th>");
            out.print("<th>Consecutivo</th>");
            out.print("<th>Lote</th>");
            out.print("<th>Pruebas</th>");
            out.print("<th>Entregado</th>");
            out.print("<th>Observaciones</th>");
            out.print("<th>Responsable</th>");
            if (filtro == 0) {
                out.print("<th>Proceso</th>");
            }
            out.print("<th>Anexos</th>");
            out.print("<th>Enlace</th>");
            if (!rol.equals("Consulta")) {
                if (filtro != 0) {
                    out.print("<th>Modificar</th>");
                }
            }
            if (!rol.equals("Consulta") && !rol.equals("Calidad")) {
                out.print("<th>Estado</th>");
            }
            if (txtPermisos.contains("[88]")) {
                out.print("<th>Eliminar</th>");
            }
            out.print("</tr>");
            out.print("</thead>");
            out.print("<tbody>");
            if (temp == 4) {
                lst_cons = ctrlcons.Traer_Controlconsecutivos(id_order);
            }
            if (lst_cons != null) {
                for (int i = 0; i < lst_cons.size(); i++) {
                    Object[] obj_cons = (Object[]) lst_cons.get(i);
                    int entrega = Integer.parseInt(obj_cons[6].toString());
                    out.print("<tr>");
                    //<editor-fold defaultstate="collapsed" desc="ID">
                    if (obj_cons[8] != null && obj_cons[8] instanceof Integer) {
                        int estadoRegistro = (Integer) obj_cons[8];

                        out.print("<td align='center'>");

                        if (estadoRegistro == 0) {
                            out.print("<input type='checkbox' id='cbx_" + obj_cons[0] + "' onclick='Masivo(" + obj_cons[0] + ", \"" + obj_cons[1] + "\")'>");
                        } else if (estadoRegistro == 1) {
                            out.print("<input type='checkbox' id='cbx_" + obj_cons[0] + "' onclick='Masivo(" + obj_cons[0] + ", \"" + obj_cons[1] + "\")' disabled>");
                        }

                        out.print("<input type='hidden' value='" + obj_cons[0] + "'>");
                        out.print("</td>");
                    } else {
                        out.print("<td align='center'>Error en el estado del registro</td>");
                    }
//</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="COSECUTIVO">
                    out.print("<td align='center' ><B>" + obj_cons[4] + "</B><BR>" + obj_cons[14] + "" + obj_cons[1] + "</td>");
//</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="LOTE">
                    out.print("<td align='center'  title='" + obj_cons[2] + "'> " + obj_cons[3] + "</td>");//Fila del lote
//</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="PRUEBAS">
                    int aplica = Integer.parseInt(obj_cons[5].toString());
                    if (aplica == 0) {
                        out.print("<td align='center'>" + obj_cons[17] + "</td>");
                    } else {
                        out.print("<td align='center'>No Aplica</td>");
                    }
//</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="ENTREGADO">
                    if (!rol.equals("Calidad") && !rol.equals("Consulta")) {
                        if (entrega == 0) {

                            if (txtPermisos.contains("[41]") && txtPermisos.contains("[42]")) {
                                out.print("<td align='center'><b>APLICA</b></BR>"
                                        + "<a onclick = mostrarprueba_" + i + "(); href='#' class='btn btn-info btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Si Entrega'>SI</a>"
                                        + "&nbsp;&nbsp;&nbsp;&nbsp;"
                                        + "<a href='cc?opc=3&idpro=" + obj_cons[0] + "&Flt_Id_proceso=" + filtro + "&year=" + year + "' class='btn btn-danger btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='No Entrega'>No</a></td>");
                            } else {
                                out.print("<td align='center'><b>APLICA</b></BR>"
                                        + "<a class='btn btn-secondary btn-sm' data-toggle='tooltip' data-placement='top' title='No tiene permisos'>SI</a>"
                                        + "&nbsp;&nbsp;&nbsp;&nbsp;"
                                        + "<a class='btn btn-secondary btn-sm' data-toggle='tooltip' data-placement='top' title='No tiene permisos'>No</a></td>");
                            }

                            out.print("<script type='text/javascript'>"
                                    + "function mostrarprueba_" + i + "(){"
                                    + "document.getElementById('ocultoprueba_" + i + "').style.display = 'block';}"
                                    + "</script>");
                            out.print("<script type='text/javascript'>"
                                    + "function ocultarprueba_" + i + "(){"
                                    + "document.getElementById('ocultoprueba_" + i + "').style.display = 'none';}"
                                    + "</script>");
                            out.print("<div id='ocultoprueba_" + i + "' style='display:none;'>"
                                    + "<div style='float:left;'>");

                            out.print("<div class='sweet-local' tabindex='-1' id='Ventana1' style='opacity: 1.03; display:block;'>");
                            out.print("<div class='cont_reg2'>");
                            out.print("<div style='display: flex; justify-content: space-between'>");
                            out.print("<h2>Seleccione Prueba</h2>");
                            out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(1)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
                            out.print("</div>");
                            out.print("<div class='cont_form_user'>");
                            out.print("<form action='cc?opc=3&idpro=" + obj_cons[0] + "&Flt_Id_proceso=" + filtro + "&year=" + year + "' method='post' name='prueba' class='needs-validation' novalidate=''>");

                            out.print("<div class='col-lg-12' style='margin-top:2.9%; margin-left:3%; margin-right:-3%;'>");
                            out.print("<select class='select2' name='slc_prueba' data-toggle='tooltip' data-placement='top' title='Pruebas'>");
                            out.print("<option value='empty'>Seleccione prueba</option>");
                            lst_prueba = jpapruebas.Pruebas();
                            for (int b = 0; b < lst_prueba.size(); b++) {
                                Object[] obj_prueb = (Object[]) lst_prueba.get(b);
                                if (obj_cons[17].equals(obj_prueb[1])) {
                                    out.print("<option selected value='0/" + obj_cons[17] + "'  >" + obj_prueb[1] + "</option>");
                                } else {
                                    out.print("<option value='0/" + obj_prueb[1] + "' >" + obj_prueb[1] + "</option>");
                                }
                            }
                            out.print("</select>");
                            out.print("</div>");
                            out.print("<br>");

                            out.print("<div class='' style='width: 100%; text-align:center;'>");
                            out.print("<button class='btn btn-green btn-lg'>Guardar</button>");
                            out.print("</div>");

                            out.print("</form>");
                            out.print("</div>");
                            out.print("</div>");
                            out.print("</div>");

                        } else if (entrega == 1) {
                            if (txtPermisos.contains("[43]")) {
                                out.print("<td align='center'><a style='font-size:11px;' class='btn btn-warning btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Entregar' href='cc?opc=3&idpro=" + obj_cons[0] + "&ent=2&Flt_Id_proceso=" + filtro + "&year=" + year + "'>PENDIENTE</a></td>");
                            } else {
                                out.print("<td align='center'><a style='font-size:11px;' class='btn btn-secondary btn-sm' data-toggle='tooltip' data-placement='top' title='No tiene permisos'>PENDIENTE</a></td>");
                            }
                        } else if (entrega == 2) {
                            out.print("<td align='center'><a style='font-size:11px; cursor: not-allowed;' class='btn btn-success btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title=''>ENTREGADO</a></td>");
                        } else {
                            out.print("<td align='center'><a style='font-size:11px; cursor: not-allowed;' class='btn btn-secondary btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title=''>No Aplica</a></td>");
                        }
                    } else if (entrega == 0) {
                        out.print("<td align='center'>En Revision</td>");
                    } else if (entrega == 1) {
                        out.print("<td align='center'>Pendiente</td>");
                    } else if (entrega == 2) {
                        out.print("<td align='center'>Entregado</td>");
                    } else {
                        out.print("<td align='center'>No Aplica</td>");
                    }
//</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="OBSERVACIONES">
                    out.print("<td align='left'>" + obj_cons[7] + "</td>");
//</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="RESPONSABLE">
                    String[] responsable = obj_cons[10].toString().split("/");
                    out.print("<td  align='center' >" + responsable[1] + "</td>");
//</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="PROCESO">
                    if (filtro == 0) {
                        out.print("<td  align='center' >" + obj_cons[12] + "</td>");
                    }
//</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="ANEXOS">
                    out.print("<td align='center'>");
                    out.print("<div class='btn-container'>");
                    if (txtPermisos.contains("[40]")) {
                        out.print("<button type='button' onclick=\"javascript:location.href='cc?opc=1&id_add_anexo=" + obj_cons[0] + "'\" class='btn btn-dark btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Anexar documento'><i class='fas fa-paperclip'></i></button>");
                    } else {
                        out.print("<button class='btn btn-green btn-sm' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-paperclip'></i></button>");
                    }
                    List lst_contador = ctrlcons.Consulta_anexo_id(Integer.parseInt(obj_cons[0].toString()), "R-GC-059");
                    String spanClass = "btn btn-primary btn-xs";
                    if (!rol.equals("Calidad") && !rol.equals("Consulta")) {
                        if (aplica != 1) {
                            if (lst_contador.size() == 0) {
                                spanClass += " contadoranexored";
                            } else {
                                spanClass += " contadoranexo";
                            }
                        } else {
                            spanClass += " contadoranexo";
                        }
                    } else {
                        spanClass += " contadoranexo";
                    }

                    out.print("<span class='" + spanClass + "'>" + lst_contador.size() + "</span>");
                    out.print("</div>");
                    out.print("</td>");
//</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="ENLACE">
                    if (Integer.parseInt(obj_cons[13].toString()) > 0) {
                        String loteee = obj_cons[3].toString().replace("-", "_");
                        out.print("<td align='center'>");
                        if (txtPermisos.contains("[39]")) {
                            out.print("<button type='button' onclick='EnlaceFormulas(\"" + loteee + "\")' class='btn btn-primary btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Enlace'><i class='fas fa-external-link-alt'></i></button>");
                        } else {
                            out.print("<button class='btn btn-green btn-sm' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-external-link-alt'></i></button>");
                        }
                        out.print("</td>");
                    } else {
                        out.print("<td align='center'>");
                        if (txtPermisos.contains("[39]")) {
                            out.print("<button type='button' class='btn btn-secondary btn-sm btn-icon' style='cursor: not-allowed;' data-toggle='tooltip' data-placement='top' title='Este lote no existe en el App control formulas'><i class='fas fa-external-link-alt'></i></button>");
                        } else {
                            out.print("<button class='btn btn-green btn-sm' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-external-link-alt'></i></button>");
                        }
                        out.print("</td>");
                    }
//</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="MODIFICAR">
                    if (!rol.equals("Consulta")) {
                        if (filtro != 0) {
                            if (obj_cons[8].equals(0)) {
                                out.print("<td align='center'>");
                                if (txtPermisos.contains("[37]")) {
                                    out.print("<button type='button' onclick=\"javascript:location.href='cc?opc=1&id_ctrcons=" + obj_cons[0] + "&Flt_Id_proceso=" + filtro + "&Nombre_proceso=" + nombre_proceso + "&year=" + year + "'\" class='btn btn-primary btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Modificar Consecutivo'><i class='fas fa-pencil-alt'></i></button>");
                                } else {
                                    out.print("<button class='btn btn-green btn-sm' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-pencil-alt'></i></button>");
                                }
                                out.print("</td>");
                            } else {
                                out.print("<td align='center'>");
                                if (txtPermisos.contains("[37]")) {
                                    out.print("<button href='#' class='btn btn-secondary btn-sm btn-icon' style='cursor: not-allowed;' data-toggle='tooltip' data-placement='top'><i class='fas fa-pencil-alt'></i></button>");
                                } else {
                                    out.print("<button class='btn btn-green btn-sm' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-pencil-alt'></i></button>");
                                }
                                out.print("</td>");
                            }
                        }
                    }
//</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="ESTADO">
                    if (!rol.equals("Calidad") && !rol.equals("Consulta")) {
                        if (Integer.parseInt(obj_cons[8].toString()) != 0) {
                            out.print("<td align='center'>");
                            if (txtPermisos.contains("[38]")) {
                                out.print("<button href='#' class='btn btn-secondary btn-sm btn-icon' style='cursor: not-allowed;' data-toggle='tooltip' data-placement='top' title='Registro Finalizado'><i class='fas fa-lock'></i></button>");
                            } else {
                                out.print("<button class='btn btn-green btn-sm' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-lock'></i></button>");
                            }
                            out.print("</td>");
                        } else {
                            out.print("<td align='center'>");
                            if (txtPermisos.contains("[38]")) {
                                out.print("<button type='button' onclick='FinalizarLote(" + obj_cons[0] + "," + filtro + "," + year + ")' class='btn btn-success btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Finalizar Lote'><i class='fas fa-check'></i></type='button'>");
                            } else {
                                out.print("<button class='btn btn-green btn-sm' style='border-radius: 4px;opacity: 0.5;' data-toggle='tooltip' data-placement='top' title='No tiene permisos'><i class='fas fa-check'></i></button>");
                            }
                            out.print("</td>");
                        }
                    }
//</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="ELIMINAR">
                    if (txtPermisos.contains("[88]")) {
                        out.print("<td align='center'>");
                        out.print("<button type='button' onclick='EliminarRegistro(" + obj_cons[0] + ")' class='btn btn-danger btn-sm btn-icon' data-toggle='tooltip' data-placement='top' title='Eliminar registro'><i class='far fa-trash-alt fa-size_small text-white'></i></button>");
                        out.print("</td>");
                    }
//</editor-fold>
                    out.print("</tr>");
                }
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
        } catch (IOException ex) {
            Logger.getLogger(Controlconsecutivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.doStartTag();
    }
}
