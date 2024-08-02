package Servlet;

import Controladores.RolJpaController;
import Controladores.UsuarioJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Rol extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //<editor-fold defaultstate="collapsed" desc="SESSION">
            HttpSession sesion = request.getSession();
            String rol_usuario = sesion.getAttribute("Rol/Nombres").toString();
            String UserRol = sesion.getAttribute("Id_rol").toString();
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="JPAS">
            UsuarioJpaController jpacusa = new UsuarioJpaController();
            RolJpaController jpac_rol = new RolJpaController();
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="VARIABLES">
            int opc = Integer.parseInt(request.getParameter("opc").toString());
            int id_rol, estado, id_rol_permission = 0;
            String nombre, permissions = "";
            boolean result = false;
//</editor-fold>
            switch (opc) {
                //<editor-fold defaultstate="collapsed" desc="CASO 1 ATRIBUTOS">
                case 1:
                    try {
                        id_rol = Integer.parseInt(request.getParameter("id_rol").toString());
                    } catch (Exception e) {
                        id_rol = 0;
                    }
                    try {
                        id_rol_permission = Integer.parseInt(request.getParameter("id_rol_permission"));
                    } catch (Exception e) {
                        id_rol_permission = 0;
                    }
                    request.setAttribute("id_rol", id_rol);
                    request.setAttribute("Id_rol", UserRol);
                    request.setAttribute("id_rol_permission", id_rol_permission);
                    request.getRequestDispatcher("Rol.jsp").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 2 CAMBIAR ESTADO">
                case 2:
                    try {
                        id_rol = Integer.parseInt(request.getParameter("id_rol"));
                    } catch (Exception e) {
                        id_rol = 0;
                    }
                    try {
                        estado = Integer.parseInt(request.getParameter("estado"));
                    } catch (Exception e) {
                        estado = 0;
                    }
                    estado = (estado == 1) ? 0 : 1;

                    result = jpac_rol.estado_rol(id_rol, estado);
                    if (result) {
                        request.setAttribute("estado_rol", result);
                        request.getRequestDispatcher("Rol?opc=1&id_rol=0").forward(request, response);
                    } else {
                        request.setAttribute("Error_app", result);
                        request.getRequestDispatcher("Rol?opc=1&id_rol=0").forward(request, response);
                    }
                    break;
                //</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 3 REGISTRAR Y MODIFICAR ROL">
                case 3:
                    try {
                        id_rol = Integer.parseInt(request.getParameter("id_rol"));
                    } catch (Exception e) {
                        id_rol = 0;
                    }
                    try {
                        nombre = request.getParameter("nombre").toString();
                    } catch (Exception e) {
                        nombre = "";
                    }

                    if (id_rol <= 0) {
                        result = jpac_rol.registrar_rol(nombre, rol_usuario);
                        if (result) {
                            request.setAttribute("registrar_rol", result);
                            request.getRequestDispatcher("Rol?opc=1&id_rol=0").forward(request, response);
                        } else {
                            request.setAttribute("Error_app", result);
                            request.getRequestDispatcher("Rol?opc=1&id_rol=0").forward(request, response);
                        }
                    } else {
                        try {
                            estado = Integer.parseInt(request.getParameter("estado"));
                        } catch (Exception e) {
                            estado = 0;
                        }
                        result = jpac_rol.editar_rol(id_rol, nombre, estado);
                        if (result) {
                            request.setAttribute("modificar_rol", result);
                            request.getRequestDispatcher("Rol?opc=1&id_rol=0").forward(request, response);
                        } else {
                            request.setAttribute("Error_app", result);
                            request.getRequestDispatcher("Rol?opc=1&id_rol=0").forward(request, response);
                        };
                    }
                    request.getRequestDispatcher("Rol?opc=1&id_rol=0").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 4 ASIGNAR PERMISO">
                case 4:
                    try {
                        id_rol = Integer.parseInt(request.getParameter("id_rol"));
                    } catch (Exception e) {
                        id_rol = 0;
                    }
                    permissions = request.getParameter("Cbx_permission");

                    result = jpac_rol.actualizar_permisos(id_rol, permissions);
                    if (result) {
                        request.setAttribute("actualizar_permisos", result);
                        request.getRequestDispatcher("Rol?opc=1&id_rol=0").forward(request, response);
                    } else {
                        request.setAttribute("Error_app", result);
                        request.getRequestDispatcher("Rol?opc=1&id_rol=0").forward(request, response);
                    }
                    request.getRequestDispatcher("Rol?opc=1&id_rol=0").forward(request, response);
                    break;
//</editor-fold>
            }
        } catch (Exception e) {

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
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
