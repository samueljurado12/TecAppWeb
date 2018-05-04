/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import ejb.UserFacade;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistence.User;

/**
 *
 * @author ubuntie
 */
@WebServlet(name = "CreateUser", urlPatterns = {"/CreateUser"})
public class CreateUserServlet extends HttpServlet {


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
        List<User> userList = userFacade.findAll();
        
        String username = request.getParameter("nif");
        String name = request.getParameter("username");
        String usermail = request.getParameter("mail");
        int userphone = Integer.parseInt(request.getParameter("phone"));
        String usernif = request.getParameter("nif");
        String address = request.getParameter("adress");
        String surname = request.getParameter("surname");
        String userpass =request.getParameter("username");
        
        //generacion de password
        userpass = userpass.substring(0,3);
        userpass =userpass.concat(usernif.substring(0,3));
        
        if(! nifExists(userList, usernif)){
            User newUser = new User();
            
            newUser.setUsername(username);
            newUser.setEmail(usermail);
            newUser.setName(name);
            newUser.setPhoneNumber(userphone);
            newUser.setPassword(userpass);
            newUser.setAddress(address);
            newUser.setSurname(surname);
            newUser.setNif(usernif);
            
            userFacade.create(newUser);
        }
        
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/employee");
        dispatcher.forward(request, response);
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

    private boolean nifExists(List<User> userList, String usernif) {
        boolean exists = false;
        int i = 0;
        
        while (!exists && i < userList.size()){
            String addedUserNif = userList.get(i).getNif();
            exists = addedUserNif.equals(usernif);
            i++;
        }
        
        return exists;
    }

}
