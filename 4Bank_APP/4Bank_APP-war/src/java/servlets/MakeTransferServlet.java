/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import ejb.AccountFacade;
import ejb.MovementFacade;
import java.io.IOException;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistence.Account;
import persistence.Movement;

/**
 *
 * @author sjuradoq
 */
@WebServlet(name = "MakeTransferServlet", urlPatterns = {"/MakeTransferServlet"})
public class MakeTransferServlet extends HttpServlet {

    @EJB
    private MovementFacade movementsFacade;

    @EJB
    private AccountFacade accountFacade;

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

        String senderAccountNumber = request.getParameter("senderAccount");
        String receiverAccountNumber = request.getParameter("receiverAccount");
        float amount = Float.parseFloat(request.getParameter("amount"));
        String remarks = request.getParameter("remarks");

        Account senderAccount = accountFacade.queryAccountById(senderAccountNumber);
        Account receiverAccount = accountFacade.queryAccountById(receiverAccountNumber);

        if (receiverAccount == null) {
            //TODO Error, account does not exist, back to transfer
        } else if (senderAccount.getBalance() < 0) {
            //TODO Error, you don't have money
        } else if (amount == 0) {
            //TODO Error, you can't make a transfer without transfering money
        } else if (receiverAccount.equals(senderAccount)) {
            //TODO Error, you can't make a transfer to the same account you are sending from
        } else {
            float newBalanceSender = senderAccount.getBalance() - amount;
            float newBalanceReceiver = receiverAccount.getBalance() + amount;
            senderAccount.setBalance(newBalanceSender);
            receiverAccount.setBalance(newBalanceReceiver);

            // Save sender and receiver account data in db
            accountFacade.edit(senderAccount);
            accountFacade.edit(receiverAccount);

            // Generate new movement and save it into the db
            Movement newMovement = new Movement(0);
            newMovement.setIdACCOUNT(senderAccount);
            newMovement.setIdACCOUNTreceptor(receiverAccount);
            newMovement.setConcept(remarks);
            newMovement.setAmount(amount);
            newMovement.setNewBalanceSender(newBalanceSender);
            newMovement.setNewBalanceReceiver(newBalanceReceiver);
            newMovement.setDate(new Date());

            movementsFacade.create(newMovement);
        }
        response.sendRedirect("transfer.jsp");
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
