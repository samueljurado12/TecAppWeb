/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import ejb.AccountFacade;
import ejb.MovementFacade;
import ejb.UserFacade;
import java.io.IOException;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persistence.Account;
import persistence.Movement;
import persistence.User;

/**
 *
 * @author JavierVazquez
 */
@WebServlet(name = "CreateMovement", urlPatterns = {"/CreateMovement"})
public class CreateMovementServlet extends HttpServlet {

    @EJB
    private UserFacade userFacade;

    @EJB
    private AccountFacade accountFacade;

    @EJB
    private MovementFacade movementFacade;
    
    

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
         
        HttpSession session = request.getSession();
        String selected_account = request.getParameter("selectedAccount");
        String idAccount_receptor = request.getParameter("idAccount_receptor");
        String concept = request.getParameter("concept");
        String amount = request.getParameter("amount");
        
        Account senderAccount = accountFacade.queryAccountById(selected_account);
        Account receptorAccount = accountFacade.queryAccountById(idAccount_receptor);
        
      
        
        Movement mov = new Movement();
        mov.setIdACCOUNT(senderAccount);
        mov.setIdACCOUNTreceptor(receptorAccount);
        mov.setConcept(concept);
        mov.setAmount(Float.parseFloat(amount));
        mov.setDate(new Date());
        Float balanceSender = senderAccount.getBalance() - Float.parseFloat(amount);
        mov.setNewBalanceSender(balanceSender);
        Float balanceReceiver = receptorAccount.getBalance() + Float.parseFloat(amount);
        mov.setNewBalanceReceiver(balanceReceiver);
        User employee = (User) session.getAttribute("user");
        mov.setIdEmployee(employee);
        
        movementFacade.create(mov);

        
          RequestDispatcher rd = this.getServletContext()
                .getRequestDispatcher("/EditUser?idUser="
                        +request.getParameter("selectedUser"));
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
