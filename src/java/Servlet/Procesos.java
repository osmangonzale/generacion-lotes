package Servlet;

import Controladores.ProcesosJpaController;
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

public class Procesos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //<editor-fold defaultstate="collapsed" desc="SESION">
            HttpSession sesion = request.getSession();
            String rol_usuario = sesion.getAttribute("Rol/Nombres").toString();
            String UserRol = sesion.getAttribute("Id_rol").toString();
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="JPAS">
            ProcesosJpaController jpapro = new ProcesosJpaController();
            UsuarioJpaController jpausu = new UsuarioJpaController();
            RolJpaController jpac_rol = new RolJpaController();
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="VARIABLES">
            int opc = Integer.parseInt(request.getParameter("opc").toString());
            int Id_proceso = 0;
            String tipo = "";
            String nombre = "";
            String actual = "";
            String sigla = "";
            int id_rol, estado, id_rol_permission = 0;
            String permissions = "";
            boolean result = false;
            List lista_registro;
//</editor-fold>
            switch (opc) {
                //<editor-fold defaultstate="collapsed" desc="CASO 1 ID PROCESO">
                case 1:
                    tipo = "Modulo_Procesos";
                    try {
                        Id_proceso = Integer.parseInt(request.getParameter("Id_proceso").toString());
                    } catch (Exception e) {
                        Id_proceso = 0;
                    }
                    try {
                        id_rol_permission = Integer.parseInt(request.getParameter("id_rol_permission"));
                    } catch (Exception e) {
                        id_rol_permission = 0;
                    }
                    request.setAttribute("Proceso", tipo);
                    request.setAttribute("Id_proceso", Id_proceso);
                    request.setAttribute("Id_rol", UserRol);
                    request.setAttribute("id_rol_permission", id_rol_permission);
                    request.getRequestDispatcher("Procesos.jsp").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 2 REGISTRAR PROCESO">
                case 2:
                    sigla = request.getParameter("txtsigla").toString();
                    nombre = request.getParameter("txtnombre").toString();
                    actual = request.getParameter("txtactual").toString();
                    tipo = request.getParameter("slc_proceso");
                    result = jpapro.Registrar_proceso(nombre, sigla, actual, rol_usuario, tipo);
                    if (result) {
                        request.setAttribute("Proceso_registro", result);
                        request.getRequestDispatcher("Procesos?opc=1").forward(request, response);
                    } else {
                        request.setAttribute("Alerta", "Error_proceso");
                        request.getRequestDispatcher("Procesos?opc=1").forward(request, response);
                    }
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 3 ACTIVAR PROCESO">
                case 3:
                    Id_proceso = Integer.parseInt(request.getParameter("id_proceso"));
                    result = jpapro.Activar_proceso(Id_proceso);
                    if (result) {
                        request.setAttribute("Proceso_inactivo", result);
                        request.getRequestDispatcher("Procesos?opc=1").forward(request, response);
                    } else {
                        request.getRequestDispatcher("Procesos?opc=1").forward(request, response);
                    }
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 4 DESACTIVAR PROCESO">
                case 4:
                    Id_proceso = Integer.parseInt(request.getParameter("id_proceso"));
                    result = jpapro.Desactivar_proceso(Id_proceso);
                    if (result) {
                        request.setAttribute("Proceso_activo", result);
                        request.getRequestDispatcher("Procesos?opc=1").forward(request, response);
                    } else {
                        request.getRequestDispatcher("Procesos?opc=1").forward(request, response);
                    }
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 5 MODIFICAR PROCESO">
                case 5:
                    nombre = request.getParameter("txtnombre");
                    sigla = request.getParameter("txtsigla");
                    actual = request.getParameter("txtactual");
                    Id_proceso = Integer.parseInt(request.getParameter("Id_proceso"));
                    tipo = request.getParameter("slc_proceso");
                    result = jpapro.Modificar_proceso(nombre, sigla, actual, Id_proceso, tipo);
                    if (result) {
                        request.setAttribute("proceso_modificado", result);
                    } else {
                        request.setAttribute("Mensaje", "Hubo un problema al intentar modificar el proceso.");
                    }
                    request.getRequestDispatcher("Procesos?opc=1&Id_proceso=0").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 6 MODIFICAR PROCESO USUARIO">
                case 6:
//                    Id_proceso = Integer.parseInt(request.getParameter("slc_pro"));
//                    registro = jpausu.Modificar_proceso_usu(id_proceso, id_usuario);
//                    if (registro) {
//                        request.setAttribute("Alerta", "Modificar_proceso");
//                        request.getRequestDispatcher("Sesion?opc=2").forward(request, response);
//                    } else {
//                        request.setAttribute("Alerta", "Error_proceso");
//                        request.getRequestDispatcher("Sesion?opc=2").forward(request, response);
//                    }
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 7 CONSULTA ID PROCESO">
                case 7:
                    Id_proceso = Integer.parseInt(request.getParameter("idRegistro"));
                    lista_registro = jpapro.Consulta_idProceso(Id_proceso);
                    if (lista_registro != null && !lista_registro.isEmpty()) {
                        request.setAttribute("lista_registro", lista_registro);
                        request.getRequestDispatcher("Procesos.jsp").forward(request, response);
                    } else {
                        request.setAttribute("Alerta", "Error_proceso");
                        request.getRequestDispatcher("Sesion?opc=2").forward(request, response);
                    }
                    break;
//</editor-fold>
            }
        } catch (Exception ex) {
            request.getRequestDispatcher("Procesos.jsp").forward(request, response);
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
