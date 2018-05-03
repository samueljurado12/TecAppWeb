/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import ejb.AccountFacade;
import ejb.MovementFacade;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
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
@WebServlet(name = "ListMovement", urlPatterns = {"/ListMovement"})
public class ListMovement extends HttpServlet {

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
        User user = (User) session.getAttribute("user");
        Account selectedAccount = null;
        List<Movement> movementList = null;
        Map<Integer, String> receptors = new HashMap<>();
        User otherAccount = null;

        if (request.getParameter("selectedAccount") == null) {
            selectedAccount = user.getAccountList().get(0);
        } else {
            selectedAccount = accountFacade.queryAccountById(Integer.parseInt(request.getParameter("selectedAccount")));
        }

        movementList = selectedAccount.getMovementList();
        movementList.addAll(selectedAccount.getMovementList1());
        movementList.sort((Movement o1, Movement o2) -> o2.getDate().compareTo(o1.getDate()));
        for (Movement mov : movementList) {
            otherAccount = getUser(mov, selectedAccount);
            receptors.put(otherAccount.getIdUSER(), otherAccount.getName() + " " + otherAccount.getSurname());
        }

        session.setAttribute("selectedAccount", selectedAccount);
        session.setAttribute("movementList", movementList);
        session.setAttribute("receptors", receptors);
        response.sendRedirect("accounts.jsp");
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

    private User getUser(Movement mov, Account selectedAccount) {
        if (mov.getIdACCOUNT().equals(selectedAccount)) {
            return mov.getIdACCOUNTreceptor().getIdUSER();
        } else {
            return mov.getIdACCOUNTreceptor().getIdUSER();
        }
    }

}
