<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Controladores.ControlConsecutivosJpaController"%>
<%@taglib uri="/WEB-INF/tlds/Controlconsecutivos.tld" prefix="Controlconsecutivos"%>
<%@taglib uri="/WEB-INF/tlds/Alertas.tld" prefix="Alertas"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Control Consecutivos | GL</title>
        <link rel="stylesheet" href="Interfaz/Contenido/assets/modules/datatables/datatables.min.css">
        <link rel="stylesheet" href="Interfaz/Contenido/assets/modules/datatables/DataTables-1.10.16/css/dataTables.bootstrap4.min.css">
        <link rel="stylesheet" href="Interfaz/Contenido/assets/modules/datatables/Select-1.2.4/css/select.bootstrap4.min.css">
        <link rel="stylesheet" href="Interfaz/Contenido/assets/css/main.css">
        <link rel="stylesheet" href="Interfaz/Contenido/assets/modules/izitoast/css/iziToast.min.css">
        <link rel="stylesheet" href="Interfaz/Contenido/assets/modules/select2/dist/css/select2.min.css">
        <link rel="shortcut icon" href="Interfaz/Contenido/images/favicon.ico" type="image/x-icon" />

    </head>
    <body style="overflow-x: hidden">
        <div id="app">
            <div class="main-wrapper main-wrapper-1">
                <jsp:include page="Menu.jsp"></jsp:include>
                    <div class="main-content" style="min-height: 694px;">
                    <Controlconsecutivos:Controlconsecutivos/>
                </div>
            </div>
        </div>
        <Alertas:alertas/>
        <script src="Interfaz/Calendarios/Js_normal.js"></script>
        <script type="text/javascript" src="Interfaz/Tabs/tabs.js"></script>
        <script type="text/javascript">
            function EliminarRegistro(id_registro) {
                swal({
                    title: "Eliminar Registro",
                    text: "Seguro que desea Eliminar este consecutivo...!",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "red",
                    confirmButtonText: "Aceptar",
                    cancelButtonText: "Cancelar",
                    closeOnConfirm: false,
                },
                        function () {
                            location.href = 'cc?opc=8&idRegistro=' + id_registro + '';
                        });
            }
        </script>
        <script type="text/javascript">
            function reinicializarTooltips() {
                if (typeof $ !== 'undefined' && typeof $.fn.tooltip !== 'undefined') {
                    $('[data-toggle="tooltip"]').tooltip();
                } else {
                    console.error("jQuery o Bootstrap no están cargados");
                }
            }

            $(document).ready(function () {
                var table = $('#table-1').DataTable();
                reinicializarTooltips();
                table.on('draw', function () {
                    reinicializarTooltips();
                });
            });
        </script>
        <script type='text/javascript'>
            function Masivo(id, cc) {
                var checkbox = document.getElementById("cbx_" + id);
                var idConsInput = document.getElementById("id_cons");
                var ccConsInput = document.getElementById("cc_cons");
                var idString = "[" + id + "]";
                var ccString = "CC " + cc + ", ";
                var row = checkbox.closest("tr");

                if (checkbox.checked) {
                    if (!idConsInput.value.includes(idString)) {
                        idConsInput.value += idString;
                        ccConsInput.value += ccString;
                    }
                    row.classList.add("selected-row");
                } else {
                    idConsInput.value = idConsInput.value.replace(idString, "");
                    ccConsInput.value = ccConsInput.value.replace(ccString, "");
                    row.classList.remove("selected-row");
                }
            }

            function confirmarInactivacion() {
                var ccConsInput = document.getElementById("cc_cons").value;
                return confirm('¿Está seguro que desea inactivar estos consecutivos: ' + ccConsInput + '?');
            }

            function enviar_masivo() {
                var ccConsInput = document.getElementById("cc_cons").value;

                swal({
                    title: "Finalizar Consecutivos?",
                    text: "Seguro que desea finalizar estos consecutivos: (" + ccConsInput + ")!",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "green",
                    confirmButtonText: "Aceptar",
                    cancelButtonText: "Cancelar",
                    closeOnConfirm: false,
                },
                        function () {
                            document.getElementById("form_masivo").submit();
                        });
            }
        </script>
        <script>
            function anexar(id, lote) {
                var div = document.getElementById('toggleM');
                if (div.style.display === 'none') {
                    div.style.display = 'block';
                    document.getElementById('nombre').innerHTML = ('Anexar documentos' + id);
                    alert(lote);
                } else {
                    div.style.display = 'none';
                }
            }
            function CerrarModalFormula() {
                document.getElementById("Ventana1").style.display = "none";
            }
            function entrega(id) {
                var div = document.getElementById('toggleslc');
                if (div.style.display === 'none') {
                    div.style.display = 'block';
                } else {
                    div.style.display = 'none';
                }
            }
            function Changeyear() {
                var selectedYear = document.getElementById("select_range").value;
                document.getElementById("anioF").value = selectedYear;
                var select = document.getElementById("select_range");
                for (var i = 0; i < select.options.length; i++) {
                    if (select.options[i].value === selectedYear) {
                        select.selectedIndex = i;
                        break;
                    }
                }
                document.getElementById("FormAnio").submit();
            }
        </script>
        <script>
            function comprobar(i) {
                var file = $('#Txt_adjunto' + i).val();
                if (file === '') {
                    swal(
                            'Error',
                            'Debe seleccionar un archivo',
                            'error'
                            )
                } else {
                    document.getElementById("anexo" + i).submit();
                }
            }
            function FinalizarLote(id_con, flt, year) {
                swal({
                    title: "Finalizar Lote?",
                    text: "Seguro que desea finalizar el lote...!",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "green",
                    confirmButtonText: "Aceptar",
                    cancelButtonText: "Cancelar",
                    closeOnConfirm: false,
                },
                        function () {
                            location.href = 'cc?opc=5&id_cons=' + id_con + '&Flt_Id_proceso=' + flt + '&year=' + year + '';
                        });
            }
            function validarlote(lote1, cnlote, conslote) {
                var lote = lote1 + '-' + cnlote;
                document.getElementById('txtlotecm').value = lote;
                var lote2 = conslote;
                var remp = lote.replace("-", ".");
                cnlote = cnlote.replace("A", "01").replace("a", "01");
                cnlote = cnlote.replace("B", "02").replace("b", "02");
                cnlote = cnlote.replace("C", "03").replace("c", "03");
                cnlote = cnlote.replace("D", "04").replace("d", "04");
                cnlote = cnlote.replace("E", "05").replace("e", "05");
                cnlote = cnlote.replace("F", "06").replace("f", "06");
                cnlote = cnlote.replace("G", "07").replace("g", "07");
                cnlote = cnlote.replace("H", "08").replace("h", "08");
                cnlote = cnlote.replace("I", "09").replace("i", "09");
                cnlote = cnlote.replace("J", "10").replace("j", "10");
                cnlote = cnlote.replace("K", "11").replace("k", "11");
                cnlote = cnlote.replace("L", "12").replace("l", "12");
                var lote = remp.split(".")[1];

                var remp2 = lote2.replace("-", ".");
                remp2 = remp2.replace("A", "01").replace("a", "01");
                remp2 = remp2.replace("B", "02").replace("b", "02");
                remp2 = remp2.replace("C", "03").replace("c", "03");
                remp2 = remp2.replace("D", "04").replace("d", "04");
                remp2 = remp2.replace("E", "05").replace("e", "05");
                remp2 = remp2.replace("F", "06").replace("f", "06");
                remp2 = remp2.replace("G", "07").replace("g", "07");
                remp2 = remp2.replace("H", "08").replace("h", "08");
                remp2 = remp2.replace("I", "09").replace("i", "09");
                remp2 = remp2.replace("J", "10").replace("j", "10");
                remp2 = remp2.replace("K", "11").replace("k", "11");
                remp2 = remp2.replace("L", "12").replace("l", "12");


                var utl_rango = remp2.length;
                var utl_resta = utl_rango - 6;
                var ult_lote = remp2.substring(utl_resta, utl_rango);

                if (cnlote > ult_lote) {
                    //                    document.getElementById("Div_boton").style.display = "block";
                    //                    document.getElementById("Div_alerta").style.display = "none";
                    $(".td_save").css({
                        display: 'block'
                    });
                    $("#Div_alerta").css({
                        display: 'none'
                    });
                } else {
                    $(".td_save").css({
                        display: 'none'
                    });
                    $("#Div_alerta").css({
                        display: 'block'
                    });
                    //                    document.getElementById("Div_alerta").style.display = "block";
                    //                    document.getElementById("Div_boton").style.display = "none";
                }
            }
            function Ocultar_lotes() {
                var producto = document.getElementById("slcfactory").value;
                if (producto == "MANUAL") {
                    document.getElementById('Lotes_seleccion').style.display = 'none';
                    document.getElementById('Lotes_manual').style.display = 'block';
                }
            }
            function Mostrar_lotes() {
                var producto = document.getElementById("Chk_lotes").value;
                if (producto == "SELECCION") {
                    document.getElementById('Lotes_seleccion').style.display = 'none';
                    document.getElementById('Lotes_manual').style.display = 'none';
                    document.getElementById("Chk_lotes").checked = 0;
                    document.getElementById("Cbx_lotes").value = "";
                }
            }

            function CerrarModalModificar() {
                document.getElementById("modal_modificar").style.display = "none";
            }
            function CerrarModalRegistro() {
                document.getElementById("modal_Registra").style.display = "none";
            }
            function CerrarModal5() {
                document.getElementById("modal").style.display = "block";
            }


            function validar() {
                var lote = document.getElementById('lote_2').value;
                console.log(lote);
                if (lote != "") {
                    $(".oculto").css({
                        display: 'block'
                    });
                } else {
                    $(".oculto").css({
                        display: 'none'
                    });
                }
            }

        </script> 
        <%
            ControlConsecutivosJpaController jpacc = new ControlConsecutivosJpaController();
            List lst_link = jpacc.ConsultarParametros_xCategoria("ConexionLinkFormula");
            String link = ""; // Inicializa la variable link
            if (lst_link != null && !lst_link.isEmpty()) {
                Object[] obj_link = (Object[]) lst_link.get(0);
                link = obj_link[3].toString(); // Asigna el valor a la variable link
            }
        %>

        <script>
            var link = '<%= link%>';

            function EnlaceFormulas(lote) {
                window.open(link + lote, '', 'width=1024,height=720,left=50,top=50,toolbar=yes');
            }
        </script> 
        <script type="text/javascript" src="Interfaz/Contenido/Scripts/JS_ControlConsecutivos.js"></script>

        <script src="Interfaz/Contenido/assets/modules/datatables/datatables.min.js"></script>
        <script src="Interfaz/Contenido/assets/modules/datatables/DataTables-1.10.16/js/dataTables.bootstrap4.min.js"></script>
        <script src="Interfaz/Contenido/assets/modules/datatables/Select-1.2.4/js/dataTables.select.min.js"></script>
        <script src="Interfaz/Contenido/assets/js/page/modules-datatables.js"></script>
        <script src="Interfaz/Contenido/assets/modules/izitoast/js/iziToast.min.js"></script>
        <script src="Interfaz/Contenido/assets/js/page/modules-toastr.js"></script>

        <script type="text/javascript" src="Interfaz/Alertas/dist/sweetalert.min.js"></script>
        <link href="Interfaz/Alertas/dist/sweetalert.css" rel="stylesheet" type="text/css"/> 
        <script src="Interfaz/Contenido/assets/modules/select2/dist/js/select2.full.min.js"></script>
    </body>
</html>
