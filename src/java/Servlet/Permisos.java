package Servlet;

import Controladores.PermisosJpaController;
import Controladores.RolJpaController;
import Controladores.UsuarioJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Permisos extends HttpServlet {

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
            PermisosJpaController jpapermisos = new PermisosJpaController();
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="VARIABLES">
            int opc = Integer.parseInt(request.getParameter("opc").toString());
            int id_permiso, estado = 0;
            String modulo, opcion, descripcion = "";
            int id_rol, id_rol_permission = 0;
            boolean result = false;
//</editor-fold>
            switch (opc) {
                //<editor-fold defaultstate="collapsed" desc="CASO 1 ATRIBUTOS">
                case 1:
                    try {
                        id_permiso = Integer.parseInt(request.getParameter("id_permiso").toString());
                    } catch (Exception e) {
                        id_permiso = 0;
                    }
                    try {
                        id_rol_permission = Integer.parseInt(request.getParameter("id_rol_permission"));
                    } catch (Exception e) {
                        id_rol_permission = 0;
                    }
                    request.setAttribute("id_permiso", id_permiso);
                    request.setAttribute("Id_rol", UserRol);
                    request.setAttribute("id_rol_permission", id_rol_permission);
                    request.getRequestDispatcher("Permisos.jsp").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 2 CAMBIAR ESTADO">
                case 2:
                    try {
                        id_permiso = Integer.parseInt(request.getParameter("id_permiso"));
                    } catch (Exception e) {
                        id_permiso = 0;
                    }
                    try {
                        estado = Integer.parseInt(request.getParameter("estado"));
                    } catch (Exception e) {
                        estado = 0;
                    }
                    estado = (estado == 1) ? 0 : 1;

                    result = jpapermisos.estado_permiso(id_permiso, estado);
                    if (result) {
                        request.setAttribute("estado_permiso", result);
                        request.getRequestDispatcher("Permisos?opc=1&id_permiso=0").forward(request, response);
                    } else {
                        request.setAttribute("Error_app", result);
                        request.getRequestDispatcher("Permisos?opc=1&id_permiso=0").forward(request, response);
                    }
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 3 MODIFICAR Y REGISTRAR PERMISO">
                case 3:
                    try {
                        id_permiso = Integer.parseInt(request.getParameter("id_permiso"));
                    } catch (Exception e) {
                        id_permiso = 0;
                    }
                    try {
                        modulo = request.getParameter("modulo").toString();
                    } catch (Exception e) {
                        modulo = "";
                    }
                    try {
                        opcion = request.getParameter("opcion").toString();
                    } catch (Exception e) {
                        opcion = "";
                    }
                    try {
                        descripcion = request.getParameter("descripcion").toString();
                    } catch (Exception e) {
                        descripcion = "";
                    }

                    if (id_permiso <= 0) {
                        result = jpapermisos.registrar_permiso(modulo, opcion, descripcion, rol_usuario);
                        if (result) {
                            request.setAttribute("registro_permiso", result);
                            request.getRequestDispatcher("Permisos?opc=1&id_permiso=0").forward(request, response);
                        } else {
                            request.setAttribute("Error_app", result);
                            request.getRequestDispatcher("Permisos?opc=1&id_permiso=0").forward(request, response);
                        }
                    } else {
                        try {
                            estado = Integer.parseInt(request.getParameter("estado"));
                        } catch (Exception e) {
                            estado = 0;
                        }
                        result = jpapermisos.editar_permiso(id_permiso, modulo, opcion, descripcion, estado);
                        if (result) {
                            request.setAttribute("modificar_permiso", result);
                            request.getRequestDispatcher("Permisos?opc=1&id_permiso=0").forward(request, response);
                        } else {
                            request.setAttribute("Error_app", result);
                            request.getRequestDispatcher("Permisos?opc=1&id_permiso=0").forward(request, response);
                        };
                    }
                    request.getRequestDispatcher("Permisos?opc=1&id_permiso=0").forward(request, response);
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
