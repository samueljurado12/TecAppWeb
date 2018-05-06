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
import javax.servlet.RequestDispatcher;
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
        String amountParam = request.getParameter("amount");
        float amount = amountParam.equals("") ? -1 : Float.parseFloat(amountParam);
        String remarks = request.getParameter("remarks");

        Account senderAccount = accountFacade.queryAccountById(senderAccountNumber);
        Account receiverAccount = accountFacade.queryAccountById(receiverAccountNumber);

        char[] dst = new char[8];
        String id = "";
        if (receiverAccount != null && receiverAccount.getIdACCOUNT().length() == 24) {
            receiverAccount.getIdACCOUNT().getChars(0, 8, dst, 0);
            id = new String(dst);
        }
        if (receiverAccount == null) {
            request.setAttribute("success", "wrong");
        } else if (senderAccount.getBalance() - amount < 0) {
            request.setAttribute("success", "wrong");
        } else if (amount <= 0) {
            request.setAttribute("success", "wrong");
        } else if (receiverAccount.equals(senderAccount)) {
            request.setAttribute("success", "wrong");
        } else if (id.equals("ES013003")) {
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
            request.setAttribute("success", "success");
        }
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/transfer.jsp");
        rd.forward(request, response);
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
