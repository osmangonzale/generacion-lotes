package Tag;

import Controladores.ControlConsecutivosJpaController;
import Controladores.InyeccionJpaController;
import Controladores.RecepcionMaterialJpaController;
import Controladores.RolJpaController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Inicio extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            //<editor-fold defaultstate="collapsed" desc="JPAS">
            ControlConsecutivosJpaController ctrlcons = new ControlConsecutivosJpaController();
            InyeccionJpaController jpainyeccion = new InyeccionJpaController();
            RecepcionMaterialJpaController jpac_recepcion = new RecepcionMaterialJpaController();
            RolJpaController jpacrol = new RolJpaController();
            HttpSession sesion = pageContext.getSession();
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="SESSION">
            String rol_usuario = sesion.getAttribute("Rol/Nombres").toString();
            String rolUsuario = sesion.getAttribute("Rol/Nombres") != null ? sesion.getAttribute("Rol/Nombres").toString() : "";
            String rol = "";
            String usuario = "";
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="VARIABLES">
            List lst_recepciones = null;
            List lst_referencias = null;
            List lst_top3 = null;
            List lst_lotes = null;
            List lst_inyeccion_responsables = null;
            List lst_consecutivo_responsables = null;
            List lst_recepcion_responsables = null;
            int bienvenido = 0;
            int modulo = 0;
            List lst_rol_id = null;
            int id_rol, id_rol_permission, idRol = 0;
            String txtPermisos = "";
//</editor-fold>
            try {
                bienvenido = Integer.parseInt(pageContext.getRequest().getAttribute("bienvenido").toString());
            } catch (Exception e) {
                bienvenido = 0;
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
            out.print("<section class='section'>");
            out.print("<div class='section-header'>");
            out.print("<h1>Inicio</h1>");
            out.print("<div class='section-header-breadcrumb'>");
            out.print("<div class='breadcrumb-item'>Bienvenido</div>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='section-body'>");
            out.print("<div class='btn-group btn-group-lg' role='group' style='margin-left: 43%; margin-bottom: 1%;'>");
            out.print("<button type='button' name='inicio' class='btn btn-lg' style='color: #fff; border-color: #34495e; background-color: #34495e' onclick='ChangeDiv(1)'>GRAFICOS</button>");
            out.print("<button type='button' name='inicio' class='btn btn-lg' style='color: #34495e; border-color: #34495e;' onclick='ChangeDiv(2)'>ACCESO RAPIDO</button>");
            out.print("</div>");

            out.print("<div id='div_start_access' style='display:none;'>");
            //<editor-fold defaultstate="collapsed" desc="ACCESOS DIRECTOS">
            out.print("<div class='card'>");
            out.print("<div class='card-header'>");
            out.print("<h4>Registros en gestión</h4>");
            out.print("</div>");
            out.print("<div class='card-body'>");
            out.print("<ul class='nav nav-tabs' id='myTab2' role='tablist'>");

            if (rol_usuario.contains("Calidad_despachos") || rol_usuario.contains("Administrador")) {
                out.print("<li class='nav-item'>");
                out.print("<a class='nav-link active show' id='recepciones' data-toggle='tab' href='#contact2' role='tab' aria-controls='contact' aria-selected='true'>Recepciones</a>");
                out.print("</li>");
            }

            if (rol_usuario.contains("Coordinacion") || rol_usuario.contains("Calidad") || rol_usuario.contains("Administrador")) {
                out.print("<li class='nav-item'>");
                out.print("<a class='nav-link' id='Consecutivos' data-toggle='tab' href='#home2' role='tab' aria-controls='home' aria-selected='true'>Consecutivos</a>");
                out.print("</li>");
            }

            if (rol_usuario.contains("Calidad") || rol_usuario.contains("Administrador")) {
                out.print("<li class='nav-item'>");
                out.print("<a class='nav-link' id='Inyecciones' data-toggle='tab' href='#profile2' role='tab' aria-controls='profile' aria-selected='false'>Inyecciones</a>");
                out.print("</li>");
            }

            out.print("</ul>");
            out.print("<div class='tab-content tab-bordered' id='myTab3Content'>");
            //<editor-fold defaultstate="collapsed" desc="CCESO DIRECTO CONSECUTIVOS">
            if (rol_usuario.contains("Calidad") || rol_usuario.contains("Coordinacion")) {
                out.print("<div class='tab-pane fade' id='home2' role='tabpanel' aria-labelledby='Consecutivos'>");
                lst_consecutivo_responsables = ctrlcons.Lista_consecutivo_responsable(rol_usuario);
                if (lst_consecutivo_responsables != null && !lst_consecutivo_responsables.isEmpty()) {
                    for (int i = 0; i < lst_consecutivo_responsables.size(); i++) {
                        Object[] cons = (Object[]) lst_consecutivo_responsables.get(i);
                        out.print("<div class='div_acces_priv'>");
                        out.print("<div class='div_acces'>");
                        out.print("<div class='dsn_acces'><b>Consecutivo: " + cons[1] + "</b></div>");
                        out.print("<div class='dsn_acces'><b>Producto: " + cons[2] + "</b></div>");
                        out.print("<div class='dsn_acces'><b>Lote C: " + cons[3] + "</b></div>");
                        out.print("</div>");
                        out.print("<div class='div_acces'>");
                        out.print("<div class='dsn_acces'><b>Lote P: " + cons[4] + "</b> V()</div>");
                        out.print("<div class='dsn_acces'><b>Fecha: " + cons[6] + "</b></div>");
                        out.print("<div class='dsn_acces'><b>Molde: " + cons[8] + "</b></div>");
                        out.print("</div>");
                        out.print("<div class='div_acces'>");
                        out.print("<div class='dsn_acces'><b>Prestamo: " + cons[12] + "</b></div>");
                        out.print("<div class='dsn_acces'><b>Contramuestras: " + cons[9] + "</b></div>");
                        out.print("<div class='dsn_acces'><b>Observaciones: " + cons[7] + "</b> - </div>");
                        out.print("</div>");
                        out.print("<div class='div_acces_bottom'>");
                        out.print("<a style='line-height: 18px;' onclick=\"javascript:location.href='cc?opc=1&temp=4&id_order=" + cons[0] + "'\" class='btn btn-white'><i class='fas fa-eye'></i> | Ver Registro</a>");
                        out.print("</div>");
                        out.print("</div>");
                    }
                } else {
                    out.print("<div class='div_acces_priv'>");
                    out.print("<h1 style='text-align: center;'>No se han encontrado registros de consecutivos en estado activo!</h1>");
                    out.print("</div>");
                }
                out.print("</div>");
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="ACCESO DIRECTO INYECCIONES">
            if (rol_usuario.contains("Calidad")) {
                out.print("<div class='tab-pane fade' id='profile2' role='tabpanel' aria-labelledby='Inyecciones'>");
                lst_inyeccion_responsables = jpainyeccion.Lista_inyeccion_responsable(rol_usuario);
                if (lst_inyeccion_responsables != null && !lst_inyeccion_responsables.isEmpty()) {
                    for (int i = 0; i < lst_inyeccion_responsables.size(); i++) {
                        Object[] iny = (Object[]) lst_inyeccion_responsables.get(i);
                        out.print("<div class='div_acces_priv'>");
                        out.print("<div class='div_acces'>");
                        out.print("<div class='dsn_acces'><b>Consecutivo: " + iny[1] + "</b></div>");
                        out.print("<div class='dsn_acces'><b>Producto: " + iny[2] + "</b></div>");
                        out.print("<div class='dsn_acces'><b>Lote C: " + iny[3] + "</b></div>");
                        out.print("</div>");
                        out.print("<div class='div_acces'>");
                        out.print("<div class='dsn_acces'><b>Lote P: " + iny[4] + "</b> V()</div>");
                        out.print("<div class='dsn_acces'><b>Fecha: " + iny[6] + "</b></div>");
                        out.print("<div class='dsn_acces'><b>Molde: " + iny[8] + "</b></div>");
                        out.print("</div>");
                        out.print("<div class='div_acces'>");
                        out.print("<div class='dsn_acces'><b>Prestamo: " + iny[12] + "</b></div>");
                        out.print("<div class='dsn_acces'><b>Contramuestras: " + iny[9] + "</b></div>");
                        out.print("<div class='dsn_acces'><b>Observaciones: " + iny[7] + "</b> - </div>");
                        out.print("</div>");
                        out.print("<div class='div_acces_bottom'>");
                        out.print("<a style='line-height: 18px;' onclick=\"javascript:location.href='in?opc=1&temp=4&id_order=" + iny[0] + "'\" class='btn btn-white'><i class='fas fa-eye'></i> | Ver Registro</a>");
                        out.print("</div>");
                        out.print("</div>");
                    }
                } else {
                    out.print("<div class='div_acces_priv'>");
                    out.print("<h1 style='text-align: center;'>No se han encontrado registros de inyecciones en estado activo!</h1>");
                    out.print("</div>");
                }
                out.print("</div>");
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="ACCESO DIRECTO RECEPCIONES">
            out.print("<div class='tab-pane fade active show' id='contact2' role='tabpanel' aria-labelledby='recepciones'>");
            if (rol_usuario.contains("Calidad_despachos")) {
                lst_recepcion_responsables = jpac_recepcion.Lista_recepcion_responsable(rol_usuario);
                if (lst_recepcion_responsables != null && !lst_recepcion_responsables.isEmpty()) {
                    for (int i = 0; i < lst_recepcion_responsables.size(); i++) {
                        Object[] rec = (Object[]) lst_recepcion_responsables.get(i);
                        out.print("<div class='div_acces_priv'>");
                        out.print("<div class='div_acces'>");
                        out.print("<div class='dsn_acces'><b>Fecha recepcion: " + rec[2] + "</b></div>");
                        out.print("<div class='dsn_acces'><b>Codigo: " + rec[6] + "</b></div>");
                        out.print("<div class='dsn_acces'><b>Referencia: " + rec[7] + "</b></div>");
                        out.print("</div>");
                        out.print("<div class='div_acces'>");
                        out.print("<div class='dsn_acces'><b>Producto: " + rec[4] + "</b></div>");
                        out.print("<div class='dsn_acces'><b>Cantidad: " + rec[5] + "</b></div>");
                        out.print("<div class='dsn_acces'><b>Entregado: " + rec[9] + "</b></div>");
                        out.print("</div>");
                        out.print("<div class='div_acces_bottom'>");
                        out.print("<a style='line-height: 18px;' onclick=\"javascript:location.href='RecepcionMaterial?opc=1&tempp=4&id_order=" + rec[0] + "'\" class='btn btn-white'><i class='fas fa-eye'></i> | Ver Registro</a>");
                        out.print("</div>");
                        out.print("</div>");
                    }
                } else {
                    out.print("<div class='div_acces_priv'>");
                    out.print("<h1 style='text-align: center;'>No se han encontrado registros de recepciones en estado activo!</h1>");
                    out.print("</div>");
                }
            }
            out.print("</div>");
//</editor-fold>

            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
//</editor-fold>

            out.print("<div id='div_start_calendar' style='display:block;'>");
            //<editor-fold defaultstate="collapsed" desc="GRAFICO DE BARRAS RECEPCIONES">
            List<Integer> recepcionCounts = new ArrayList<>();
            List<String> dayOfWeeks = new ArrayList<>();
            lst_recepciones = jpac_recepcion.recepciones_5dias();
            for (int i = 0; i < lst_recepciones.size(); i++) {
                Object[] recepciones = (Object[]) lst_recepciones.get(i);
                System.out.println("Recepciones[" + i + "]: " + Arrays.toString(recepciones));
                try {
                    recepcionCounts.add(Integer.parseInt(recepciones[0].toString()));
                    dayOfWeeks.add(recepciones[1].toString());
                } catch (NumberFormatException e) {
                    System.err.println("Error al convertir recepciones[0] a entero: " + recepciones[0]);
                    e.printStackTrace();
                } catch (Exception e) {
                    System.err.println("Error general al procesar recepciones: " + Arrays.toString(recepciones));
                    e.printStackTrace();
                }
            }
            System.out.println("recepcionCounts: " + recepcionCounts);
            System.out.println("dayOfWeeks: " + dayOfWeeks);
            out.print("<div class='row'>");
            out.print("<div class='col-12 col-md-6 col-lg-6'>");
            out.print("<div class='card'>");
            out.print("<div class='card-header'>");
            out.print("<h4>Recepciones de los últimos 5 días | Modulo Recepcion de materiales</h4>");
            out.print("</div>");
            out.print("<div class='card-body'>");
            out.print("<div class='sparkline-inline'>");
            out.print("<canvas id='recepcionesbar' width='400' height='200'></canvas>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("<script>");
            out.print("var ctx = document.getElementById('recepcionesbar').getContext('2d');");
            String recepcionCountsJs = recepcionCounts.stream().map(String::valueOf).collect(Collectors.joining(","));
            String dayOfWeeksJs = dayOfWeeks.stream().map(day -> "'" + day + "'").collect(Collectors.joining(","));
            out.print("var recepcionCounts = [" + recepcionCountsJs + "];");
            out.print("var dayOfWeeks = [" + dayOfWeeksJs + "];");
            out.print("console.log('recepcionCounts:', recepcionCounts);");
            out.print("console.log('dayOfWeeks:', dayOfWeeks);");
            out.print("var myChart = new Chart(ctx, {");
            out.print("type: 'bar',");
            out.print("data: {");
            out.print("labels: dayOfWeeks,");
            out.print("datasets: [{");
            out.print("label: 'Recepciones del día',");
            out.print("data: recepcionCounts,");
            out.print("backgroundColor: 'rgba(75, 192, 192, 0.2)',");
            out.print("borderColor: 'rgba(75, 192, 192, 1)',");
            out.print("borderWidth: 1");
            out.print("}]");
            out.print("},");
            out.print("options: {");
            out.print("scales: {");
            out.print("yAxes: [{");
            out.print("ticks: {");
            out.print("beginAtZero: true");
            out.print("}");
            out.print("}]");
            out.print("}");
            out.print("}");
            out.print("});");
            out.print("</script>");
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="GRAFICO DE DONA REFERENCIAS">
            List<String> referenciasmas = new ArrayList<>();
            List<String> porcentajesmas = new ArrayList<>();
            List<Integer> entradas = new ArrayList<>();

            lst_referencias = jpac_recepcion.mas_referencias_30_dias();
            if (lst_referencias == null || lst_referencias.isEmpty()) {
                System.err.println("lst_referencias está vacío o es nulo");
            } else {
                for (int i = 0; i < lst_referencias.size(); i++) {
                    Object[] referencia = (Object[]) lst_referencias.get(i);
                    try {
                        if (referencia[0] != null && referencia[1] != null && referencia[2] != null) {
                            referenciasmas.add(referencia[0].toString());
                            entradas.add(Integer.parseInt(referencia[1].toString()));
                            porcentajesmas.add(referencia[2].toString());
                        } else {
                            System.err.println("referencia[0], referencia[1] o referencia[2] son nulos");
                        }
                    } catch (Exception e) {
                        System.err.println("Error general al procesar recepciones: " + Arrays.toString(referencia));
                        e.printStackTrace();
                    }
                }
            }

            System.out.println("referencia: " + referenciasmas);
            System.out.println("entradas: " + entradas);
            System.out.println("porcentaje: " + porcentajesmas);

            out.print("<div class='col-12 col-md-6 col-lg-6'>");
            out.print("<div class='card'>");
            out.print("<div class='card-header'>");
            out.print("<h4>Referencias que más entraron en los últimos 30 días | Modulo Recepcion de materiales</h4>");
            out.print("</div>");
            out.print("<div class='card-body'>");
            out.print("<div class='sparkline-bar'>");
            out.print("<canvas id='referenciasdonut' width='400' height='200'></canvas>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");

            out.print("<script>");
            out.print("var ctx = document.getElementById('referenciasdonut').getContext('2d');");

            String referenciasString = referenciasmas.stream().map(s -> "'" + s + "'").collect(Collectors.joining(","));
            String porcentajesString = porcentajesmas.stream().map(String::valueOf).collect(Collectors.joining(","));
            String entradasString = entradas.stream().map(String::valueOf).collect(Collectors.joining(","));

            out.print("var referencia = [" + referenciasString + "];");
            out.print("var porcentaje = [" + porcentajesString + "];");
            out.print("var entradas = [" + entradasString + "];");

            out.print("console.log('referencia:', referencia);");
            out.print("console.log('porcentaje:', porcentaje);");
            out.print("console.log('entradas:', entradas);");

            out.print("var myChart = new Chart(ctx, {");
            out.print("type: 'doughnut',");
            out.print("data: {");
            out.print("labels: referencia,");
            out.print("datasets: [{");
            out.print("label: 'Recepciones del día',");
            out.print("data: porcentaje,");
            out.print("backgroundColor: [");
            out.print("'rgba(75, 192, 192, 0.2)',");
            out.print("'rgba(255, 99, 132, 0.2)',");
            out.print("'rgba(54, 162, 235, 0.2)',");
            out.print("'rgba(255, 206, 86, 0.2)',");
            out.print("'rgba(153, 102, 255, 0.2)',");
            out.print("'rgba(255, 159, 64, 0.2)'");
            out.print("],");
            out.print("borderColor: [");
            out.print("'rgba(75, 192, 192, 1)',");
            out.print("'rgba(255, 99, 132, 1)',");
            out.print("'rgba(54, 162, 235, 1)',");
            out.print("'rgba(255, 206, 86, 1)',");
            out.print("'rgba(153, 102, 255, 1)',");
            out.print("'rgba(255, 159, 64, 1)'");
            out.print("],");
            out.print("borderWidth: 1");
            out.print("}]");
            out.print("},");
            out.print("options: {");
            out.print("responsive: true,");
            out.print("maintainAspectRatio: false,");
            out.print("tooltips: {");
            out.print("callbacks: {");
            out.print("label: function(tooltipItem, data) {");
            out.print("var label = data.labels[tooltipItem.index];");
            out.print("var value = data.datasets[0].data[tooltipItem.index];");
            out.print("var entrada = entradas[tooltipItem.index];");
            out.print("return label + ': ' + entrada + ' = ' + value + ' %';");
            out.print("}");
            out.print("}");
            out.print("}");
            out.print("}");
            out.print("});");
            out.print("</script>");
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="GRAFICO DE BARRAS PRODUCTOS">
            List<String> productomas = new ArrayList<>();
            List<String> meses = new ArrayList<>();
            List<Integer> cantidades = new ArrayList<>();

            lst_top3 = jpainyeccion.top3_ultimos6_meses();
            if (lst_top3 == null || lst_top3.isEmpty()) {
                System.err.println("lst_top3 está vacío o es nulo");
            } else {
                for (int i = 0; i < lst_top3.size(); i++) {
                    Object[] producto = (Object[]) lst_top3.get(i);
                    try {
                        if (producto[0] != null && producto[1] != null && producto[3] != null) {
                            productomas.add(producto[0].toString());
                            meses.add(producto[1].toString());
                            cantidades.add(Integer.parseInt(producto[3].toString()));
                        } else {
                            System.err.println("Algunos datos son nulos para el producto: " + Arrays.toString(producto));
                        }
                    } catch (Exception e) {
                        System.err.println("Error al procesar los productos: " + Arrays.toString(producto));
                        e.printStackTrace();
                    }
                }
            }

            System.out.println("Productos: " + productomas);
            System.out.println("Meses: " + meses);
            System.out.println("Cantidades: " + cantidades);

            out.print("<div class='row'>");
            out.print("<div class='col-12 col-md-6 col-lg-6'>");
            out.print("<div class='card'>");
            out.print("<div class='card-header'>");
            out.print("<h4>Productos más usados en los últimos 6 meses | Modulo Inyeccion</h4>");
            out.print("</div>");
            out.print("<div class='card-body'>");
            out.print("<div class='sparkline-line'>");
            out.print("<canvas id='productosbar' width='400' height='200'></canvas>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");

            out.print("<script>");
            out.print("var ctx = document.getElementById('productosbar').getContext('2d');");

            Set<String> productosUnicos = new HashSet<>(productomas);

            Map<String, Map<String, Integer>> productMonthData = new HashMap<>();

            for (String producto : productosUnicos) {
                productMonthData.put(producto, new HashMap<>());
            }

            for (int i = 0; i < productomas.size(); i++) {
                String producto = productomas.get(i);
                String mes = meses.get(i);
                Integer cantidad = cantidades.get(i);

                Map<String, Integer> monthData = productMonthData.get(producto);
                monthData.put(mes, cantidad);
            }

            Set<String> mesesUnicosSet = new LinkedHashSet<>(meses);
            List<String> mesesUnicos = new ArrayList<>(mesesUnicosSet);

            String[] coloresPredefinidos = {
                "rgba(75, 192, 192, 1)",
                "rgba(255, 99, 132, 1)",
                "rgba(54, 162, 235, 1)",
                "rgba(255, 206, 86, 1)",
                "rgba(153, 102, 255, 1)",
                "rgba(255, 159, 64, 1)"
            };

            List<String> datasets = new ArrayList<>();
            int colorIndex = 0;

            for (String producto : productosUnicos) {
                List<Integer> cantidadesPorProducto = new ArrayList<>();
                Map<String, Integer> monthData = productMonthData.get(producto);

                for (String mes : mesesUnicos) {
                    cantidadesPorProducto.add(monthData.getOrDefault(mes, 0));
                }

                String dataset = "{";
                dataset += "label: '" + producto + "',";
                dataset += "data: [" + cantidadesPorProducto.stream().map(String::valueOf).collect(Collectors.joining(",")) + "],";
                dataset += "backgroundColor: '" + coloresPredefinidos[colorIndex % coloresPredefinidos.length] + "',";
                dataset += "borderColor: '" + coloresPredefinidos[colorIndex % coloresPredefinidos.length] + "',";
                dataset += "borderWidth: 1";
                dataset += "}";

                datasets.add(dataset);
                colorIndex++;
            }

            String mesString = mesesUnicos.stream().map(mes -> "'" + mes + "'").collect(Collectors.joining(","));
            out.print("var meses = [" + mesString + "];");
            out.print("var datasets = [" + String.join(",", datasets) + "];");

            out.print("var myChart = new Chart(ctx, {");
            out.print("type: 'bar',");
            out.print("data: {");
            out.print("labels: meses,");
            out.print("datasets: datasets");
            out.print("},");
            out.print("options: {");
            out.print("responsive: true,");
            out.print("maintainAspectRatio: false,");
            out.print("scales: {");
            out.print("y: {");
            out.print("beginAtZero: true");
            out.print("}");
            out.print("}");
            out.print("}");
            out.print("});");
            out.print("</script>");
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="GRAFICO DE LINEA LOTES">
            List<Integer> lotesGenerados = new ArrayList<>();
            List<String> tiempos = new ArrayList<>();

            lst_lotes = ctrlcons.lotes_generados_6meses();

            if (lst_lotes != null) {
                for (int i = 0; i < lst_lotes.size(); i++) {
                    Object[] lotes = (Object[]) lst_lotes.get(i);
                    System.out.println("Lotes[" + i + "]: " + Arrays.toString(lotes));
                    try {
                        if (lotes.length >= 2 && lotes[0] != null && lotes[1] != null) {
                            tiempos.add(lotes[0].toString());
                            lotesGenerados.add(Integer.parseInt(lotes[1].toString()));
                        } else {
                            System.err.println("Lote[" + i + "] tiene valores nulos o insuficientes: " + Arrays.toString(lotes));
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Error al convertir lotes[1] a entero: " + lotes[1]);
                        e.printStackTrace();
                    } catch (Exception e) {
                        System.err.println("Error general al procesar lotes: " + Arrays.toString(lotes));
                        e.printStackTrace();
                    }
                }
            } else {
                System.err.println("lst_lotes es null.");
            }

            System.out.println("Tiempos: " + tiempos);
            System.out.println("LotesGenerados: " + lotesGenerados);

            out.print("<div class='col-12 col-md-6 col-lg-6'>");
            out.print("<div class='card'>");
            out.print("<div class='card-header'>");
            out.print("<h4>Lotes generados en los últimos 6 meses | Modulo Control consecutivos</h4>");
            out.print("</div>");
            out.print("<div class='card-body'>");
            out.print("<div class='sparkline-line'>");
            out.print("<canvas id='lotesline' width='400' height='200'></canvas>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");

            out.print("<script>");
            out.print("var ctx = document.getElementById('lotesline').getContext('2d');");

            String lotesCounts = lotesGenerados.stream().map(String::valueOf).collect(Collectors.joining(","));
            String mesesCounts = tiempos.stream().map(mes -> "'" + mes + "'").collect(Collectors.joining(","));

            out.print("var lotesCounts = [" + lotesCounts + "];");
            out.print("var mesesCounts = [" + mesesCounts + "];");

            out.print("console.log('lotesCounts:', lotesCounts);");
            out.print("console.log('mesesCounts:', mesesCounts);");

            out.print("var myChart = new Chart(ctx, {");
            out.print("type: 'line',");
            out.print("data: {");
            out.print("labels: mesesCounts,");
            out.print("datasets: [{");
            out.print("label: 'Lotes generados por mes',");
            out.print("data: lotesCounts,");
            out.print("backgroundColor: 'rgba(75, 192, 192, 0.2)',");
            out.print("borderColor: 'rgba(75, 192, 192, 1)',");
            out.print("borderWidth: 1");
            out.print("}]");
            out.print("},");
            out.print("options: {");
            out.print("scales: {");
            out.print("yAxes: [{");
            out.print("ticks: {");
            out.print("beginAtZero: true");
            out.print("}");
            out.print("}]");
            out.print("}");
            out.print("}");
            out.print("});");
            out.print("</script>");
//</editor-fold>
            out.print("</div>");

            out.print("<script type='text/javascript'>");
            out.print("function ChangeDiv(number) {");
            out.print("    var buttons = document.getElementsByName('inicio');");
            out.print("    for (var i = 0; i < buttons.length; i++) {");
            out.print("        buttons[i].style.backgroundColor = '#fff';");
            out.print("        buttons[i].style.borderColor = '#34495e';");
            out.print("        buttons[i].style.color = '#34495e';");
            out.print("    }");
            out.print("    document.getElementById('div_start_access').style.display = (number === 1) ? 'none' : 'block';");
            out.print("    document.getElementById('div_start_calendar').style.display = (number === 1) ? 'block' : 'none';");
            out.print("    buttons[number - 1].style.backgroundColor = '#34495e';");
            out.print("    buttons[number - 1].style.borderColor = '#34495e';");
            out.print("    buttons[number - 1].style.color = '#fff';");
            out.print("}");
            out.print("</script>");

            out.print("</div>");
            out.print("</section>");

            if (bienvenido == 1) {
                out.print("<script type='text/javascript'>");
                out.print("$(document).ready(function() {");
                out.print("iziToast.info({");
                out.print("title: 'Bienvenido',");
                out.print("message: '¡Al aplicativo Generacion de lotes!',");
                out.print("position: 'bottomRight',");
                out.print("icon: 'fas fa-handshake'");
                out.print("});");
                out.print("});");
                out.print("</script>");
            } else {
                out.print("");
            }
        } catch (IOException ex) {
            Logger.getLogger(Inicio.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return super.doStartTag();
    }
}
