package Servlet;

import Controladores.RolJpaController;
import Controladores.UsuarioJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Usuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try {
            // <editor-fold defaultstate="collapsed" desc="SESION">
            HttpSession sesion = request.getSession();
            String rol_usuario = sesion.getAttribute("Rol/Nombres").toString();
            String UserRol = sesion.getAttribute("Id_rol").toString();
            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="JPAS">
            UsuarioJpaController jpacusa = new UsuarioJpaController();
            RolJpaController jpac_rol = new RolJpaController();
            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="VARIABLES">
            int opc = Integer.parseInt(request.getParameter("opc").toString());
            String nombre, apellido, usuario, tipo, cadena = "";
            int documento, codigo, id_usuario, id_registro, id_rol, id_rol_permission, menu = 0;
            List lst_usuario = null;
            boolean result = false;
            // </editor-fold>
            switch (opc) {
                // <editor-fold defaultstate="collapsed" desc="CASO 1 ID USUARIO">
                case 1:
                    tipo = "Modulo_usuario";
                    try {
                        id_usuario = Integer.parseInt(request.getParameter("Id_usuario").toString());
                    } catch (Exception e) {
                        id_usuario = 0;
                    }
                    try {
                        id_rol_permission = Integer.parseInt(request.getParameter("id_rol_permission"));
                    } catch (Exception e) {
                        id_rol_permission = 0;
                    }
                    request.setAttribute("Usuario", tipo);
                    request.setAttribute("Id_usuario", id_usuario);
                    request.setAttribute("Id_rol", UserRol);
                    request.setAttribute("id_rol_permission", id_rol_permission);
                    request.getRequestDispatcher("Usuario.jsp").forward(request, response);
                    break;
                // </editor-fold>
                // <editor-fold defaultstate="collapsed" desc="CASO 2 REGISTRAR USURAIO">
                case 2:
                    nombre = request.getParameter("Txt_nombre");
                    apellido = request.getParameter("Txt_apellido");
                    documento = Integer.parseInt(request.getParameter("Txt_documento").toString());
                    codigo = Integer.parseInt(request.getParameter("Txt_codigo").toString());
                    usuario = request.getParameter("Txt_usuario");
                    id_rol = Integer.parseInt(request.getParameter("Cbx_rol").toString());
                    result = jpacusa.Registrar_usuario(nombre, apellido, documento, codigo, usuario, id_rol,
                            rol_usuario);
                    if (result) {
                        request.setAttribute("User_register", result);
                        request.setAttribute("var1", nombre + " " + apellido);
                        request.getRequestDispatcher("Usuario?opc=1").forward(request, response);
                    } else {
                        request.setAttribute("Alerta", "Error_usuario");
                        request.setAttribute("var1", nombre + " " + apellido);
                        request.getRequestDispatcher("Usuario?opc=1").forward(request, response);
                    }
                    break;
                // </editor-fold>
                // <editor-fold defaultstate="collapsed" desc="CASO 3 TRAER USUARIO">
                case 3:
                    id_registro = Integer.parseInt(request.getParameter("idRegistro"));
                    lst_usuario = jpacusa.Traer_usuario(id_registro);
                    out.print(cadena);
                    break;
                // </editor-fold>
                // <editor-fold defaultstate="collapsed" desc="CASO 4 MODIFICAR USUARIO">
                case 4:
                    id_usuario = Integer.parseInt(request.getParameter("Id_usuario").toString());
                    nombre = request.getParameter("Txt_nombre");
                    apellido = request.getParameter("Txt_apellido");
                    documento = Integer.parseInt(request.getParameter("Txt_documento").toString());
                    codigo = Integer.parseInt(request.getParameter("Txt_codigo").toString());
                    usuario = request.getParameter("Txt_usuario");
                    id_rol = Integer.parseInt(request.getParameter("Cbx_rol").toString());
                    result = jpacusa.Modificar_usuario(id_usuario, nombre, apellido, documento, codigo, usuario, id_rol,
                            rol_usuario);
                    if (result) {
                        request.setAttribute("User_update", result);
                        request.setAttribute("var1", nombre + " " + apellido);
//                        request.getRequestDispatcher("Usuario?opc=1&Id_usuario=0").forward(request, response);
                    } else {
                        request.setAttribute("Alerta", "Error_usuario_modificar");
                        request.setAttribute("var1", nombre + " " + apellido);
                    }
                    request.getRequestDispatcher("Usuario?opc=1&Id_usuario=0").forward(request, response);
                    break;
                // </editor-fold>
                // <editor-fold defaultstate="collapsed" desc="CAS0 5 DESACTIVAR USUARIO">
                case 5:
                    id_usuario = Integer.parseInt(request.getParameter("Id_usuario_etd").toString());
                    result = jpacusa.Desactivar_usuario(id_usuario);
                    if (result) {
                        request.setAttribute("Usuario_desactivado", result);
                    } else {
                        request.setAttribute("Error_app", result);
                    }
                    request.getRequestDispatcher("Usuario?opc=1").forward(request, response);
                    break;
                // </editor-fold>
                // <editor-fold defaultstate="collapsed" desc="CASO 6 ACTIVAR USUARIO">
                case 6:
                    id_usuario = Integer.parseInt(request.getParameter("Id_usuario_etd").toString());
                    result = jpacusa.Activar_usuario(id_usuario);
                    if (result) {
                        request.setAttribute("Usuario_activado", result);
                    } else {
                        request.setAttribute("Error_app", result);
                    }
                    request.getRequestDispatcher("Usuario?opc=1").forward(request, response);
                    break;
                // </editor-fold>
                // <editor-fold defaultstate="collapsed" desc="CASO 7 REESTABLECER PASSWORD">
                case 7:
                    try {
                        menu = Integer.parseInt(request.getParameter("menu").toString());
                    } catch (Exception e) {
                        menu = 0;
                    }
                    if (menu == 1) {
                        id_usuario = Integer.parseInt(request.getParameter("Id_usuario").toString());
                        result = jpacusa.Restablecer_password(id_usuario);
                        if (result) {
                            request.setAttribute("Password_actualizado", result);
                        } else {
                            request.setAttribute("Error_app", result);
                        }
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    } else {
                        id_usuario = Integer.parseInt(request.getParameter("Id_usuario").toString());
                        result = jpacusa.Restablecer_password(id_usuario);
                        if (result) {
                            request.setAttribute("Password_actualizado", result);
                        } else {
                            request.setAttribute("Error_app", result);
                        }
                        request.getRequestDispatcher("Usuario?opc=1&Id_usuario=0").forward(request, response);
                    }
                    break;
                // </editor-fold>
            }
        } catch (Exception ex) {
            request.setAttribute("Alerta", "Error_sesion");
            request.getRequestDispatcher("Usuario.jsp").forward(request, response);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    // + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
