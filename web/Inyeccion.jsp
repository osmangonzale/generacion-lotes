<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/Inyeccion.tld" prefix="inyeccion"%>
<%@taglib uri="/WEB-INF/tlds/Alertas.tld" prefix="Alertas"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Iny/Ext | GL</title>
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
                    <inyeccion:inyeccion/>
                </div>
            </div>
        </div>
        <Alertas:alertas/>
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
        <script type="text/javascript">
            function EliminarRegistro(id_registro) {
                swal({
                    title: "Eliminar Registro",
                    text: "Seguro que desea Eliminar esta Inyeccion...!",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "red",
                    confirmButtonText: "Aceptar",
                    cancelButtonText: "Cancelar",
                    closeOnConfirm: false,
                },
                        function () {
                            location.href = 'in?opc=8&idRegistro=' + id_registro + '';
                        });
            }
        </script>
        <script type='text/javascript'>
            function Masivo(id, cc) {
                var checkbox = document.getElementById("cbx_" + id);
                var idInyInput = document.getElementById("id_iny");
                var ccInyInput = document.getElementById("cc_iny");
                var idString = "[" + id + "]";
                var ccString = "CC " + cc + ", ";
                var row = checkbox.closest("tr");

                if (checkbox.checked) {
                    if (!idInyInput.value.includes(idString)) {
                        idInyInput.value += idString;
                        ccInyInput.value += ccString;
                    }
                    row.classList.add("selected-row");
                } else {
                    idInyInput.value = idInyInput.value.replace(idString, "");
                    ccInyInput.value = ccInyInput.value.replace(ccString, "");
                    row.classList.remove("selected-row");
                }
            }

            function confirmarInactivacion() {
                var ccInyInput = document.getElementById("cc_iny").value;
                return confirm('¿Está seguro que desea inactivar estos consecutivos: ' + ccInyInput + '?');
            }

            function enviar_masivo() {
                var ccInyInput = document.getElementById("cc_iny").value;

                swal({
                    title: "Finalizar Inyecciones?",
                    text: "Seguro que desea finalizar la inyeccion de estos consecutivos: (" + ccInyInput + ")!",
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
            function mostrarmodalIdiny(id, idIny) {
                if (document.getElementById("Ventana" + id).style.display === "none") {
                    document.getElementById("Ventana" + id).style.display = "block";
                } else if (document.getElementById("Ventana" + id).style.display === "block") {
                    document.getElementById("Ventana" + id).style.display = "none";
                }
                document.getElementById("idIny").value = idIny;
            }
        </script>
        <script>
            function FinalizarInyeccion(id_iny, flt, nom_pro, est) {
                swal({
                    title: "Finalizar Inyeccion?",
                    text: "Seguro que desea finalizar la inyeccion...!",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "green",
                    confirmButtonText: "Aceptar",
                    cancelButtonText: "Cancelar",
                    closeOnConfirm: false,
                },
                        function () {
                            location.href = 'in?opc=3&est=1&id_iny=' + id_iny + '&Flt_Id_proceso=' + flt + '&Nombre_proceso=' + nom_pro + '&est=' + est;
                        });
            }
            function CerrarModalFormula() {
                document.getElementById("Ventana1").style.display = "none";
            }
            function Changeyear() {
                var valor = document.getElementById("select_range").value;
                document.getElementById("anioF").value = valor;
                document.getElementById("FormAnio").submit();
            }
            function fecha_inyeccion() {
                var fechap = document.getElementById('txtlotec').value;
                fechap = ultimo_lote.replace("A", "01").replace("a", "01");
                fechap = ultimo_lote.replace("B", "02").replace("b", "02");
                fechap = ultimo_lote.replace("C", "03").replace("c", "03");
                fechap = ultimo_lote.replace("D", "04").replace("d", "04");
                fechap = ultimo_lote.replace("E", "05").replace("e", "05");
                fechap = ultimo_lote.replace("F", "06").replace("f", "06");
                fechap = ultimo_lote.replace("G", "07").replace("g", "07");
                fechap = ultimo_lote.replace("H", "08").replace("h", "08");
                fechap = ultimo_lote.replace("I", "09").replace("i", "09");
                fechap = ultimo_lote.replace("J", "10").replace("j", "10");
                fechap = ultimo_lote.replace("K", "11").replace("k", "11");
                fechap = ultimo_lote.replace("L", "12").replace("l", "12");
            }

            function CerrarModalRegistro() {
                document.getElementById("modal_Registra").style.display = "none";
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
            function CerrarModalModificar() {
                document.getElementById("modal_modificar_I").style.display = "none";
            }
            function validar_boton() {
                var lotet = document.getElementById("txtlotec").value;
                if (!lotet.includes(" / ")) {
                    document.getElementById("btn_inyeccion").style.display = "none";
                }
            }
            function LoteC_autocompletar(lote_id) {
                document.getElementById("lote_c").value = "";
                document.getElementById("txtlotec").value = lote_id;
                document.getElementById("txtlotec").value = lote_id;
                if (lote_id.includes(" / ")) {
                    document.getElementById("btn_inyeccion").style.display = "block";
                } else {
                    document.getElementById("btn_inyeccion").style.display = "none";
                }
            }
        </script>       

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
