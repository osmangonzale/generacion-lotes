<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/RecepcionMaterial.tld" prefix="recepcionmaterial"%>
<%@taglib uri="/WEB-INF/tlds/Alertas.tld" prefix="Alertas"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recepcion de Material | GL</title>

        <link rel="stylesheet" href="Interfaz/Contenido/assets/modules/datatables/datatables.min.css">
        <link rel="stylesheet" href="Interfaz/Contenido/assets/modules/datatables/DataTables-1.10.16/css/dataTables.bootstrap4.min.css">
        <link rel="stylesheet" href="Interfaz/Contenido/assets/modules/datatables/Select-1.2.4/css/select.bootstrap4.min.css">
        <link rel="stylesheet" href="Interfaz/Contenido/assets/css/main.css">
        <link rel="stylesheet" href="Interfaz/Contenido/assets/modules/izitoast/css/iziToast.min.css">
        <link rel="stylesheet" href="Interfaz/Contenido/assets/modules/select2/dist/css/select2.min.css">
        <link rel="stylesheet" href="Interfaz/Contenido/assets/modules/bootstrap-daterangepicker/daterangepicker.css">
        <link rel="shortcut icon" href="Interfaz/Contenido/images/favicon.ico" type="image/x-icon" />

    </head>
    <body class='sidebar-mini'>
        <div id="app">
            <div class="main-wrapper main-wrapper-1">
                <jsp:include page="Menu.jsp"></jsp:include>
                    <div class="main-content" style="min-height: 694px;">
                    <recepcionmaterial:recepcionmaterial/>
                </div>
            </div>
        </div>
        <Alertas:alertas/>
        <script type="text/javascript">
            function validateFormR(formId) {
                const form = document.getElementById(formId);
                if (!form) {
                    console.error("Formulario no encontrado:", formId);
                    return false;
                }
                let valid = true;
                const inputs = form.querySelectorAll('input[required]');
                inputs.forEach(input => {
                    if (input.type === 'radio') {
                        const name = input.name;
                        const checked = form.querySelector('input[name="' + name + '"]:checked');

                        if (!checked) {
                            valid = false;
                        }
                    }
                });
                if (!valid) {
                    iziToast.warning({
                        title: 'Atención',
                        message: 'Por favor, complete todos los campos requeridos antes de enviar el formulario.',
                        position: 'bottomRight',
                        icon: 'fas fa-exclamation-triangle'
                    });
                    return false;
                }
                const obs12 = form.querySelector('#obs12');
                if (obs12) {
                    const regex = /^(N\/A|[0-9.]+)$/;
                    if (obs12.value.trim() === '') {
                        valid = false;
                        iziToast.warning({
                            title: 'Atención',
                            message: 'El campo Valor no puede estar vacío.',
                            position: 'bottomRight',
                            icon: 'fas fa-exclamation-triangle'
                        });
                    } else if (!regex.test(obs12.value)) {
                        valid = false;
                        iziToast.warning({
                            title: 'Atención',
                            message: 'El campo Valor solo permite números y puntos.',
                            position: 'bottomRight',
                            icon: 'fas fa-exclamation-triangle'
                        });
                    }
                }
                if (!valid) {
                    return false;
                }
                return true;
            }
            function cambioradioR() {
                const radio1 = document.getElementById('radio13_1');
                const radio2 = document.getElementById('radio13_2');
                const obs12 = document.getElementById('obs12');

                if (radio1.checked) {
                    obs12.value = '';
                    obs12.readOnly = false;
                    document.getElementById('radio14_1').checked = true;
                } else if (radio2.checked) {
                    obs12.value = 'N/A';
                    obs12.readOnly = true;
                    document.getElementById('radio14_2').checked = true;
                }
            }
        </script>
        <script type="text/javascript">
            function validateFormM(formId) {
                const form = document.getElementById(formId);
                if (!form) {
                    console.error("Formulario no encontrado:", formId);
                    return false;
                }
                let valid = true;
                const obser12 = form.querySelector('#obser12');
                if (obser12) {
                    const regex = /^(N\/A|[0-9.]+)$/;
                    if (obser12.value.trim() === '') {
                        valid = false;
                        console.log("Campo obser12 vacío");
                        iziToast.warning({
                            title: 'Atención',
                            message: 'El campo Valor no puede estar vacío.',
                            position: 'bottomRight',
                            icon: 'fas fa-exclamation-triangle'
                        });
                    } else if (!regex.test(obser12.value)) {
                        valid = false;
                        console.log("Campo obser12 contiene caracteres no permitidos:", obser12.value);
                        iziToast.warning({
                            title: 'Atención',
                            message: 'El campo Valor solo permite números y puntos.',
                            position: 'bottomRight',
                            icon: 'fas fa-exclamation-triangle'
                        });
                    }
                }
                if (!valid) {
                    return false;
                }
                return true;
            }
            function cambioradioM() {
                const radio1 = document.getElementById('radio13_1M');
                const radio2 = document.getElementById('radio13_2M');
                const obs12 = document.getElementById('obser12');

                if (radio1.checked) {
                    obs12.value = '';
                    obs12.readOnly = false;
                    document.getElementById('radio14_1M').checked = true;
                } else if (radio2.checked) {
                    obs12.value = 'N/A';
                    obs12.readOnly = true;
                    document.getElementById('radio14_2M').checked = true;
                }
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
                var tables = ['#table-1', '#table-3'];
                tables.forEach(function (tableId) {
                    var table = $(tableId).DataTable();
                    reinicializarTooltips();
                    table.on('draw', function () {
                        reinicializarTooltips();
                    });
                });
            });
            $(document).ready(function () {
                $('#opciones').tooltip({placement: 'top', title: 'Opciones'});
            });
        </script>
        <script type="text/javascript">
            function obtenerConsecutivo() {
                var referenciaCompleta = document.querySelector("select[name='referencia']").value;
                var lote = document.querySelector("input[name='lote']").value;

                var codigo = referenciaCompleta.split('/')[0].trim();

                console.log("Código:", codigo);
                console.log("Lote:", lote);

                if (codigo !== "" && lote !== "") {
                    var xhr = new XMLHttpRequest();
                    xhr.open("POST", "RecepcionMaterial?opc=14", true);
                    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4) {
                            console.log("Estado de la solicitud:", xhr.readyState);

                            if (xhr.status === 200) {
                                console.log("Respuesta del servidor:", xhr.responseText);
                                document.querySelector("input[name='consecutivo']").value = xhr.responseText;
                            } else {
                                console.log("Error en la solicitud. Código de estado:", xhr.status);
                            }
                        }
                    };
                    console.log("Datos enviados:", "codigo=" + encodeURIComponent(codigo) + "&lote=" + encodeURIComponent(lote));
                    xhr.send("codigo=" + encodeURIComponent(codigo) + "&lote=" + encodeURIComponent(lote));
                } else {
                    console.log("Código o lote vacío. No se enviarán los datos.");
                }
            }
        </script>

        <script type="text/javascript">
            function EliminarRegistro(id_registro) {
                swal({
                    title: "Eliminar Registro",
                    text: "Seguro que desea Eliminar esta recepcion...!",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "red",
                    confirmButtonText: "Aceptar",
                    cancelButtonText: "Cancelar",
                    closeOnConfirm: false,
                },
                        function () {
                            location.href = 'RecepcionMaterial?opc=13&idRegistro=' + id_registro + '';
                        });
            }
        </script>
        <script type="text/javascript">
            function enviar() {
                document.getElementById("formcodigo").submit();
            }
            function rango() {
                var rango = document.getElementsByName("rango")[0].value;
                if (rango) {
                    location.href = 'RecepcionMaterial?opc=12&rango=' + encodeURIComponent(rango);
                } else {
                    console.log('El campo de rango está vacío.');
                }
            }
            function mostrarUsuario(buttonId, spanId) {
                var button = document.getElementById(buttonId);
                var usuario = document.getElementById(spanId);
                button.style.display = "none";
                usuario.style.display = "inline";
            }
            function Imprimir() {
                var contenedor = document.getElementById("Imprimir").innerHTML;
                var frame = document.createElement("iframe");
                frame.name = "frame1";
                frame.style.position = "absolute";
                frame.style.top = "-1000000px";
                document.body.appendChild(frame);
                var frameDoc = frame.contentWindow ? frame.contentWindow : frame.contentDocument.document ? frame.contentDocument.document : frame.contentDocument;
                frameDoc.document.open();
                frameDoc.document.write('<link href="Interfaz/Contenido/assets/css/Imprimir.css" rel="stylesheet" type="text/css"/>');
                frameDoc.document.write('</head><body>');
                frameDoc.document.write(contenedor);
                frameDoc.document.write('</body></html>');
                frameDoc.document.close();
                setTimeout(function () {
                    window.frames["frame1"].focus();
                    window.frames["frame1"].print();
                    document.body.removeChild(frame);
                }, 300);
                return false;
            }
            function toggleDropdown() {
                var dropdown = document.getElementById('dropdownMenu');
                if (dropdown.style.display === 'none') {
                    dropdown.style.display = 'block';
                } else {
                    dropdown.style.display = 'none';
                }
            }
        </script>
        <script type="text/javascript">
            function CerrarRegistroUlt(id_recepcion) {
                swal({
                    title: "Seguro?",
                    text: "Si cierra el registro no se podra modificar",
                    type: "warning",
                    showCancelButton: true,
                    textCancelButton: "Cancelar",
                    textConfirmButton: "Cerrar",
                    closeOnCancel: true
                },
                        function () {
                            location.href = 'Recepcion?opc=8&idRegistro=' + id_recepcion + '&update=5';
                        });
            }
        </script>
        <script type="text/javascript">
            function EliminarRecepcion(id_recepcion) {
                swal({
                    title: "Eliminar Recepción",
                    text: "Seguro que desea Eliminar la recepción de materiales...!",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "red",
                    confirmButtonText: "Aceptar",
                    cancelButtonText: "Cancelar",
                    closeOnConfirm: false,
                },
                        function () {
                            location.href = 'Recepcion?opc=15&idRegistro=' + id_recepcion + '';
                        });
            }
        </script>
        <script type="text/javascript">
            function EliminarRecepcionEstado(id_recepcion) {
                Swal.fire({
                    title: "Eliminar Recepción?",
                    text: "Proporcione una justificación para eliminar la recepción.",
                    input: 'textarea',
                    inputPlaceholder: 'Justificar Eliminación de Recepción del material',
                    inputAttributes: {
                        'aria-label': 'Justificación de eliminación'
                    },
                    showCancelButton: true,
                    confirmButtonText: 'Eliminar',
                    cancelButtonText: 'Cancelar',
                    inputValidator: (value) => {
                        if (!value || !value.trim()) {
                            return 'Debes proporcionar una justificación para eliminar la recepción.';
                        }
                    }
                }).then((result) => {
                    if (result.isConfirmed) {
                        console.log('Justificación:', result.value);
                        const justificacion = result.value;
                        const form = document.createElement('form');
                        form.action = 'RecepcionMaterial?opc=4&idRegistro=' + id_recepcion;
                        form.method = 'post';
                        const textarea = document.createElement('textarea');
                        textarea.name = 'Txt_justificacion';
                        textarea.value = justificacion;
                        textarea.style.display = 'none';
                        form.appendChild(textarea);
                        document.body.appendChild(form);
                        form.submit();
                    }
                }).catch((error) => {
                    console.error('Error en la alerta:', error);
                });
            }
        </script>
        <script>
            function multiplicarCampos() {
                var cantidad1 = parseFloat(document.getElementById('cantidad1').value) || 0;
                var cantidad2 = parseFloat(document.getElementById('cantidad2').value) || 0;
                var resultado = cantidad1 * cantidad2;
                if (resultado % 1 === 0) {
                    resultado = parseInt(resultado);
                } else {
                    resultado = resultado.toFixed(2);
                }
                var labelResultado = document.getElementById('labelResultado');
                var inputResultado = document.getElementById('canres');
                inputResultado.value = resultado;
                labelResultado.textContent = 'Cantidad Total Remisionada: ' + resultado;
                labelResultado.style.color = resultado > 0 ? 'green' : 'red';
            }
        </script>
        <script type="text/javascript">
            function RegistrarObservacion(id_registro) {
                var observacionElement = document.getElementById('OBS' + id_registro);
                var observacion = observacionElement && observacionElement.value !== null && observacionElement.value !== 'null' ? observacionElement.value : 'N/A';
                swal({
                    title: "Añadir observación",
                    text: "<form action='RecepcionMaterial?opc=5&idRegistro=" + id_registro + "' id='FormObservacion' method='post'><center><textarea name='Txt_observacion' style='width:350px;height:100px' placeholder='Observaciones' required onfocus>" + observacion + "</textarea></center></form><button type='submit' form='FormObservacion'>Guardar</button><a href='#' id='formVolver' method='post'><button type='submit' required form='formVolver'>Volver</button></a>",
                    type: "warning",
                    showConfirmButton: false,
                    html: true,
                });
            }

        </script>
        <script type="text/javascript">
            function PrestamoRecepcion(id_recepcion, unidad) {
                Swal.fire({
                    title: "Préstamo Recepción",
                    html: `
                <form id="FormPrestamo">
                    <div style="margin-bottom: 10px;">
                        <input type="number" id="Txt_cantidad" placeholder="Cantidad dada en ${unidad}" style="display:block;width: 100%;background: #fff;border: 1px solid #34ace0;padding: 10px;font-size: 14px;color: #34495e;border-radius: 11px;" required>
                    </div>
                    <div>
                        <textarea id="Txt_justificacion" placeholder="Observaciones del préstamo" style="width:100%;height:100px;background: #fff;border: 1px solid #34ace0;padding: 10px;font-size: 14px;color: #34495e;border-radius: 11px;" required></textarea>
                    </div>
                </form>
            `,
                    showCancelButton: true,
                    confirmButtonText: 'Prestar',
                    cancelButtonText: 'Cancelar',
                    preConfirm: () => {
                        const cantidad = document.getElementById('Txt_cantidad').value.trim();
                        const justificacion = document.getElementById('Txt_justificacion').value.trim();
                        if (!cantidad || !justificacion) {
                            return Swal.showValidationMessage('Debes proporcionar tanto la cantidad como las observaciones.');
                        }

                        return {cantidad, justificacion};
                    }
                }).then((result) => {
                    if (result.isConfirmed) {
                        const justificacion = result.value.justificacion;
                        const cantidad = result.value.cantidad;
                        // Crear el formulario y enviar los datos
                        const form = document.createElement('form');
                        form.action = 'RecepcionMaterial?opc=6&idRegistro=' + id_recepcion + '&und=' + unidad;
                        form.method = 'post';
                        const cantidadInput = document.createElement('input');
                        cantidadInput.type = 'hidden';
                        cantidadInput.name = 'Txt_cantidad';
                        cantidadInput.value = cantidad;
                        form.appendChild(cantidadInput);
                        const justificacionInput = document.createElement('input');
                        justificacionInput.type = 'hidden';
                        justificacionInput.name = 'Txt_justificacion';
                        justificacionInput.value = justificacion;
                        form.appendChild(justificacionInput);
                        document.body.appendChild(form);
                        form.submit();
                    }
                }).catch((error) => {
                    console.error('Error en la alerta:', error);
                });
            }
        </script>
        <script type='text/javascript'>
            function Masivo(id, cc) {
                var checkbox = document.getElementById("cbx_" + id);
                var idInyInput = document.getElementById("idrecep");
                var ccInyInput = document.getElementById("id_recp");
                var idString = "[" + id + "]";
                var ccString = cc + ", ";
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
                var ccInyInput = document.getElementById("id_recp").value;
                return confirm('¿Está seguro que desea firmar estos registros: ' + ccInyInput + '?');
            }

            function enviar_masivo() {
                var ccInyInput = document.getElementById("id_recp").value;
                swal({
                    title: "Firmar Registros?",
                    text: "Seguro que desea firmar estos registros: (" + ccInyInput + ")!",
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

        <script src="Interfaz/Contenido/assets/js/Sweetalert2.js"></script>
        <script src="Interfaz/Contenido/assets/modules/datatables/datatables.min.js"></script>
        <script src="Interfaz/Contenido/assets/modules/datatables/DataTables-1.10.16/js/dataTables.bootstrap4.min.js"></script>
        <script src="Interfaz/Contenido/assets/modules/datatables/Select-1.2.4/js/dataTables.select.min.js"></script>
        <script src="Interfaz/Contenido/assets/js/page/modules-datatables.js"></script>
        <script src="Interfaz/Contenido/assets/modules/izitoast/js/iziToast.min.js"></script>
        <script src="Interfaz/Contenido/assets/js/page/modules-toastr.js"></script>

        <script src="Interfaz/Contenido/assets/modules/bootstrap-daterangepicker/daterangepicker.js"></script>

        <script type="text/javascript" src="Interfaz/Alertas/dist/sweetalert.min.js"></script>
        <link href="Interfaz/Alertas/dist/sweetalert.css" rel="stylesheet" type="text/css"/>
        <script src="Interfaz/Contenido/assets/modules/select2/dist/js/select2.full.min.js"></script>
    </body>
</html>
