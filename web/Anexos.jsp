<%@page import="org.apache.commons.fileupload.*,org.apache.commons.fileupload.servlet.ServletFileUpload,org.apache.commons.fileupload.disk.DiskFileItemFactory,org.apache.commons.io.FilenameUtils,java.io.File, java.io.*,java.util.*,javax.servlet.*" %>
<%
//FECHA
           Calendar cal = Calendar.getInstance();
           String ano = cal.get(Calendar.YEAR) + "";
           String mes = "";
           if ((cal.get(Calendar.MONTH) + 1) < 10) {
               mes = "0" + (cal.get(Calendar.MONTH) + 1);
           } else {
               mes = (cal.get(Calendar.MONTH) + 1) + "";
           }
           String dia = "";
           if ((cal.get(Calendar.DAY_OF_MONTH)) < 10) {
               dia = "0" + cal.get(Calendar.DAY_OF_MONTH);
           } else {
               dia = cal.get(Calendar.DAY_OF_MONTH) + "";
           }
           int hora = cal.get(Calendar.HOUR_OF_DAY);
           int minuto = cal.get(Calendar.MINUTE);
           int segundo = cal.get(Calendar.SECOND);
           List hidden = new ArrayList();
           int id_registro = 0;
           String registro = "";
           String etapa = "";
           String fase = "";
           String observacion = "";
           String[] adjunto = null;
           String extension = "";
           if (ServletFileUpload.isMultipartContent(request)) {
               ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
               List fileItemsList = servletFileUpload.parseRequest(request);
               String file_name = "";
               FileItem fileItem = null;
               Iterator it = fileItemsList.iterator();
               while (it.hasNext()) {
                   FileItem fileItemTemp = (FileItem) it.next();
                   if (fileItemTemp.isFormField()) {
                       hidden.add(fileItemTemp.getString());
                   } else {
                       fileItem = fileItemTemp;
                       try {
                           id_registro = Integer.parseInt(hidden.get(0).toString());
                           if (fileItem.getSize() != 0) {
                               file_name = fileItem.getName().toString().toUpperCase().replace("Á", "A").replace("É", "E").replace("Í", "I").replace("Ó", "O").replace("Ú", "U").replace("Ñ", "N").replace(" ", "_");
                               String dir_name = "\\\\172.16.2.117\\Sistemas de informacion\\Generacion_lotes\\Adjuntos\\"+hidden.get(1)+"\\";
                     
//172.16.2.117Sistemas de informacionGeneracion_lotesAdjuntos
adjunto = file_name.replace(".", "/").split("/");
                               for (int i = 0; i < adjunto.length - 1; i++) {
                                   if (i == 0) {
                                       file_name = adjunto[0].toString();
                                   } else {
                                       file_name = file_name + "_" + adjunto[i].toString();
                                   }
                               }
                               extension = adjunto[(adjunto.length - 1)].toString();
            file_name = file_name + "_" + ano + mes + dia + "_" + hora + minuto + "." + extension;
                                                  File saveTo = new File(dir_name + file_name);
                               fileItem.write(saveTo);
                           } else {
                               request.getRequestDispatcher("ControlConsecutivos?opc=1").forward(request, response);
                               break;
                           }
                       } catch (Exception e) {
                           request.getRequestDispatcher("ControlConsecutivos?opc=1").forward(request, response);
                           break;
                       }
                   }
                   //request.getRequestDispatcher("Proyecto?var=14&id_proyecto=1").forward(request, response);
               }
               registro = hidden.get(1).toString();
               observacion = hidden.get(2).toString();
               int filtro = Integer.parseInt(hidden.get(3).toString());
               String nombre_proceso = hidden.get(4).toString();
               request.getRequestDispatcher("ControlConsecutivos?opc=9&id_registro=" + id_registro + "&file_name=" + file_name + "&registro=" + registro + "&observacion=" + observacion + "&flt=" + filtro + "&nombre_pro=" + nombre_proceso + "").forward(request, response);
           }
%>
