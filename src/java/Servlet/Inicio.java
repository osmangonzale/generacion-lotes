package Servlet;

import Controladores.InyeccionJpaController;
import Controladores.RolJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Inicio extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try {
            InyeccionJpaController jpainyeccion = new InyeccionJpaController();
            RolJpaController jpac_rol = new RolJpaController();
            //<editor-fold defaultstate="collapsed" desc="SESION">
            HttpSession sesion = request.getSession();
            String rol_usuario = sesion.getAttribute("Rol/Nombres").toString();
            String UserRol = sesion.getAttribute("Id_rol").toString();
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="VARIABLES">
            int opc = Integer.parseInt(request.getParameter("opc").toString());
            int id_rol, id_rol_permission = 0;
            boolean result = false;
//</editor-fold>
            switch (opc) {
                //<editor-fold defaultstate="collapsed" desc="CASO 1 ID USUARIO">
                case 1:
                    try {
                        id_rol_permission = Integer.parseInt(request.getParameter("id_rol_permission"));
                    } catch (Exception e) {
                        id_rol_permission = 0;
                    }
                    request.setAttribute("Rol/Nombres", rol_usuario);
                    request.setAttribute("Id_rol", UserRol);
                    request.setAttribute("id_rol_permission", id_rol_permission);
                    request.getRequestDispatcher("Inicio.jsp").forward(request, response);
                    break;
//</editor-fold>
            }
        } catch (Exception ex) {
            request.setAttribute("Alerta", "Error_sesion");
            request.getRequestDispatcher("Usuario.jsp").forward(request, response);
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
