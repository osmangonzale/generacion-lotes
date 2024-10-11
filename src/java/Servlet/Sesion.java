package Servlet;

import Controladores.UsuarioJpaController;
import Metodos.Control_encriptacion;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Sesion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //<editor-fold defaultstate="collapsed" desc="SESSION">
            HttpSession sesion = request.getSession();
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="JPAS">
            UsuarioJpaController jpacusa = new UsuarioJpaController();
            Control_encriptacion md5 = new Control_encriptacion();
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="VARIABLES GLOBALES">
            int bienvenido = 1;
            int opc = Integer.parseInt(request.getParameter("opc").toString());
            int id_usuario = 0;
            String user, password, password_encript = null;
            List lst_usuario = null;
            boolean result = false;
            int intentos_maximos = 3;
//</editor-fold>
            switch (opc) {
                //<editor-fold defaultstate="collapsed" desc="CASO 1 ATRIBUTOS">
                case 1:
                    user = request.getParameter("Txt_user").toString();
                    password = request.getParameter("Txt_password").toString();
                    if (password.length() >= 8) {
                        password_encript = md5.md5(password);
                        lst_usuario = jpacusa.Usuario_sesion(user, password_encript);
                        if (lst_usuario == null) {
                            lst_usuario = jpacusa.Usuario_sesion(user, password);
                        }
                    } else {
                        lst_usuario = jpacusa.Usuario_sesion(user, password);
                    }
                    if (lst_usuario == null) {
                        request.setAttribute("Usuario_no_existe", result);
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    } else {
                        Object[] obj_sesion = (Object[]) lst_usuario.get(0);
                        if ((Integer) obj_sesion[5] == 0) {
                            request.setAttribute("Usuario_desactivado", result);
                            request.setAttribute("var1", obj_sesion[1]);
                            request.getRequestDispatcher("index.jsp").forward(request, response);
                        } else if (obj_sesion[9].toString().equals("Si")) {
                            request.setAttribute("Cambio_contraseña", true);
                            request.setAttribute("idUsuario", obj_sesion[0]);
                            request.getRequestDispatcher("index.jsp").forward(request, response);
                        } else {
                            try {
                                bienvenido = Integer.parseInt(request.getParameter("bienvenido"));
                            } catch (Exception e) {
                                bienvenido = 0;
                            }
                            sesion.setAttribute("Id_usuario", obj_sesion[0]);
                            sesion.setAttribute("Nombres", obj_sesion[1]);
                            sesion.setAttribute("Rol/Nombres", obj_sesion[7] + "/" + obj_sesion[1]);
                            sesion.setAttribute("Codigo", obj_sesion[2]);
                            sesion.setAttribute("Usuario", obj_sesion[3]);
                            sesion.setAttribute("Password", obj_sesion[4]);
                            sesion.setAttribute("Estado", obj_sesion[5]);
                            sesion.setAttribute("Id_rol", obj_sesion[6]);
                            sesion.setAttribute("Nombre_rol", obj_sesion[7]);
                            sesion.setAttribute("Fecha_registro", obj_sesion[8]);
                            sesion.setAttribute("Documento", obj_sesion[10]);
                            sesion.setAttribute("Menu", obj_sesion[0]);
                            bienvenido = 1;
                            request.setAttribute("bienvenido", bienvenido);
                            request.getRequestDispatcher("Inicio.jsp").forward(request, response);
                        }
                    }
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 2 REDIRECCION">
                case 2:
                    request.getRequestDispatcher("Inicio.jsp").forward(request, response);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="CASO 3 PASSWORD ACTUALIZADO">
                case 3:
                    if (request.getParameter("pass_sesion") != null) {
                        id_usuario = (Integer) sesion.getAttribute("Id_usuario");
                        jpacusa.Restablecer_password(id_usuario);
                        sesion.invalidate();
                        request.setAttribute("Password_actualizado", true);
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    } else {
                        id_usuario = Integer.parseInt(request.getParameter("Id_usuario"));
                        password = request.getParameter("Txt_password");
                        password_encript = md5.md5(password);
                        jpacusa.Cambiar_password(id_usuario, password_encript);
                        request.setAttribute("Password_actualizado", true);
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                    break;
//</editor-fold>
            }
        } catch (Exception ex) {
            Logger.getLogger(Orden.class.getName()).log(Level.SEVERE, null, ex);
            out.print(ex);
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

    private static class Orden {

        public Orden() {
        }
    }
}
