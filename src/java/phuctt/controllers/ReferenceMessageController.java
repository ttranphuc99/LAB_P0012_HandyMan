/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuctt.controllers;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import phuctt.daos.ReferenceMessageDAO;
import phuctt.dtos.ReferenceMessageDTO;

/**
 *
 * @author Thien Phuc
 */
public class ReferenceMessageController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "ReferencesController";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);

            if (isMultipart) {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);

                List items = null;
                items = upload.parseRequest(request);

                Iterator iter = items.iterator();
                Hashtable params = new Hashtable();
                String filename;
                FileItem item = null;
                FileItem uploadFile = null;

                while (iter.hasNext()) {
                    item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        params.put(item.getFieldName(), item.getString());
                    } else {
                        uploadFile = item;
                    }
                }

                String name = (String) params.get("txtName");
                String email = (String) params.get("txtEmail");
                String website = (String) params.get("txtWebsite");
                String message = (String) params.get("txtMessage");

                if (website.trim().isEmpty()) {
                    website = null;
                } else if (!website.contains("http://") && !website.contains("https://")) {
                    website = "http://" + website;
                }
                
                ReferenceMessageDTO dto = new ReferenceMessageDTO(0, name, email, website, message, email, null);

                int id = (new ReferenceMessageDAO()).insert(dto);

                String itemName = uploadFile.getName();
                if (!itemName.trim().isEmpty()) {
                    filename = id + itemName.substring(itemName.lastIndexOf("."));
                    String realPath = getServletContext().getRealPath("/") + "resource\\reference\\" + filename;

                    File savedFile = new File(realPath);
                    uploadFile.write(savedFile);

                    if ((new ReferenceMessageDAO()).updateImg(filename, id)) {
                        url = SUCCESS;
                    }
                } else {
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            log("Error at ReferenceMessageController: " + e.getMessage());
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
