<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/Alertas.tld" prefix="alertas" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="Interfaz/Contenido/assets/css/login.css">
        <link rel="stylesheet" href="Interfaz/Contenido/assets/css/alerta_bloqueo.css">
        <link rel="stylesheet" href="Interfaz/Contenido/assets/modules/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="Interfaz/Contenido/assets/modules/fontawesome/css/all.min.css">
        <link rel="shortcut icon" href="Interfaz/Contenido/images/favicon.ico" type="image/x-icon" />

        <script src="Interfaz/Contenido/assets/modules/jquery.min.js"></script>
        <title>Login | GL</title>
    </head>
    <body>
        <jsp:include page="Principal.jsp"></jsp:include>
            <div class="sweet-local" tabindex="-1" id="Ventana1" style="opacity: 1.03; display:none;">
                <div class="cont_reg">
                    <div style="display: flex; justify-content: space-between">
                        <h2>Generacion de Lotes</h2>
                        <button class="btn btn-outline-secondary" onclick="mostrarConvencion(1)" style="height: 30px;padding: 3px;width: 30px;"><i class="fas fa-times"></i></button>
                    </div>
                    <div class="cont_form_user">
                        <p>Este sistema de informacion, es el encargado de generar, almacenar y controlar el manejo de los elementos entrantes que serán <b style="color:#00281b">tratados</b> y <b style="color:#00281b">procesados</b>. Tambien gestiona y brinda informacion sobre los lotes que se generan en la produccion. </br> El sistema como ayuda virtual, permite al usuario acceder a la información de manera <b style="color:#00281b">segura, rapida</b> y <b style="color:#00281b">confiable</b> para poder realizar en cada uno de los procesos del registro una adecuada manipulación.</p>
                    </div>
                </div>
            </div>

            <div id="main">
                <div class="img_logo" id="cont_img">
                    <img id="data_img" class="cls_img" src="Interfaz/Contenido/images/Generacion_lotes3.png" width="150">
                </div>
                <div>
                    <div class="cont_icon" onclick="mostrarConvencion(1)">
                        <i class="fas fa-question"></i>
                    </div>
                    <h1>GENERACION DE LOTES</h1><br>
                    <form action="Sesion?opc=1" method="post" autocomplete="off" onsubmit="return validarFormulario()">
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <div class="input-group-text">
                                        <i class="fas fa-user"></i>
                                    </div>
                                </div>
                                <input type="text" class="form-control" name="Txt_user" id="Txt_user" placeholder="Usuario" autocomplete="off">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <div class="input-group-text">
                                        <i class="fas fa-key"></i>
                                    </div>
                                </div>
                                <input type="password" class="form-control" name="Txt_password" id="txtPassword" placeholder="Contraseña" autocomplete="off">
                                <div class="input-group-text" onclick="mostrarPass()" id="show_password" style="cursor: pointer;">
                                    <i id="icon" class="fas fa-eye"></i>
                                </div>
                            </div>
                        </div>
                        <button type="submit" class="btn" style="margin-top: 25px; box-shadow: 1px 2px 5px 0px #959595;">
                            <i class="fas fa-sign-in-alt"></i>
                        </button><br>
                    </form>

<!--                    <script type='text/javascript'>
                        function validarFormulario() {
                            var usuario = document.getElementById('Txt_user').value;
                            var contraseña = document.getElementById('txtPassword').value;

                            if (usuario === "" || contraseña === "") {
                                iziToast.show({
                                    title: 'Error',
                                    message: 'Por favor, complete ambos campos antes de enviar el formulario.',
                                    position: 'bottomRight',
                                    icon: 'fas fa-exclamation-triangle',
                                    color: 'red'
                                });
                                return false;
                            }
                            return true;
                        }
                    </script>-->
                    <div style="float: right; bottom: 0;"><b style="font-size:15px; color:#03291d; ">VA</b><b style="font-size: 15px; color:#03291d"> 08.27.13</b><br></div>
                    <div><p class="text-center" style="margin-left: 73px">© PLASTITEC.</p></div>
                </div>
            </div>
        <alertas:alertas/>

        <script type="text/javascript">
            function mostrarPass() {
                var password = document.getElementById("txtPassword");
                var eye = document.getElementById("icon");
                if (password && eye) {
                    if (password.type === "password") {
                        password.type = "text";
                        eye.className = "fas fa-eye-slash";
                    } else {
                        password.type = "password";
                        eye.className = "fas fa-eye";
                    }
                }
            }
        </script>
        <script type="text/javascript" language="javascript">
            function mostrarConvencion(id) {
                if (document.getElementById("Ventana" + id).style.display === "none") {
                    document.getElementById("Ventana" + id).style.display = "block";
                } else if (document.getElementById("Ventana" + id).style.display === "block") {
                    document.getElementById("Ventana" + id).style.display = "none";
                }
            }
        </script>
        <script>
            setTimeout(function () {
                document.getElementById("cont_img").classList.remove("img_logo_tr");
                document.getElementById("cont_img").classList.add("img_logo");
                document.getElementById("data_img").classList.remove("cls_img");
                document.getElementById("main").style.opacity = 1;
            }, 40);
        </script>
        <%
            HttpSession sesion = request.getSession(false);
            String logout = request.getParameter("logout");
            String showAlert = request.getParameter("showAlert");

            if (sesion != null && "true".equals(logout)) {
                sesion.invalidate();
                request.setAttribute("showAlert", "true");
                response.setContentType("text/html");
                response.getWriter().print("<script>setTimeout(function() { window.location.href = 'index.jsp?showAlert=true'; }, 100);</script>");
            } else if ("true".equals(showAlert)) {
        %>
        <script type='text/javascript'>
            $(document).ready(function () {
                iziToast.show({
                    title: 'Hasta luego',
                    message: '¡Te estaremos esperando!',
                    position: 'bottomRight',
                    icon: 'far fa-handshake'
                });
            });
        </script>
        <%
            }
        %>
        <script src="Interfaz/Contenido/assets/js/Sweetalert2.js"></script>
    </body>
</html>
