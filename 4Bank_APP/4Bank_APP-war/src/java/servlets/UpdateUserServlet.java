/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import ejb.UserFacade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistence.User;

/**
 *
 * @author sjuradoq
 */
@WebServlet(name = "UpdateUser", urlPatterns = {"/UpdateUser"})
public class UpdateUserServlet extends HttpServlet {

    @EJB
    private UserFacade userFacade;

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
        int idUser = Integer.parseInt(request.getParameter("idUSER"));
        User user = userFacade.find(idUser);

        String newName = request.getParameter("name");
        user.setName(newName.equals("") ? user.getName() : newName);

        String newSurname = request.getParameter("surname");
        user.setSurname(newSurname.equals("") ? user.getSurname(): newSurname);

        String newEmail = request.getParameter("email");
        user.setEmail(newEmail.equals("") ? user.getEmail(): newEmail);
        
        String newNIF = request.getParameter("nif");
        user.setNif(newNIF.equals("") ? user.getNif(): newNIF);
        
        String newUsername = request.getParameter("username");
        user.setUsername(newUsername.equals("") ? user.getNif(): newUsername);
        
        String newPhone = request.getParameter("phone");
        user.setPhoneNumber(newPhone.equals("") ? user.getPhoneNumber() : Integer.parseInt(newPhone));
        
        String newAddr = request.getParameter("address");
        user.setAddress(newAddr.equals("") ? user.getAddress() : newAddr);
        
        String newPass = request.getParameter("password");
        user.setPassword(newPass.equals("") ? user.getPassword() : newPass);
        
        userFacade.edit(user);
        
        response.sendRedirect("EditUser?idUser="+idUser);
        
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
