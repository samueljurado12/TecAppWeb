/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import ejb.AccountFacade;
import ejb.MovementsFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistence.Account;
import persistence.Movements;

/**
 *
 * @author sjuradoq
 */
@WebServlet(name = "Transfer", urlPatterns = {"/Transfer"})
public class Transfer extends HttpServlet {

    @EJB private MovementsFacade movementsFacade;

    @EJB private AccountFacade accountFacade;
    

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

        int senderAccountNumber = Integer.parseInt(request.getParameter("senderAccount"));
        int receiverAccountNumber = Integer.parseInt(request.getParameter("receiverAccount"));
        float amount = Float.parseFloat(request.getParameter("receiverAccount"));
        String remarks = request.getParameter("remarks");

        Account senderAccount = accountFacade.queryAccountById(senderAccountNumber);
        Account receiverAccount = accountFacade.queryAccountById(receiverAccountNumber);
        
        if(receiverAccount == null){
            // Error, account does not exist, back to transfer
        } else if(senderAccount.getBalance() < 0 && amount < 0) {
            // Error, you don't have money
        } else {
           float newBalance = senderAccount.getBalance()+amount;
           senderAccount.setBalance(newBalance);
           LocalDateTime date = LocalDateTime.now();
           movementsFacade.createMovement(senderAccount.getUsers().getIdUSERS(), senderAccountNumber, 
                   receiverAccountNumber, remarks, amount, newBalance, date);
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
