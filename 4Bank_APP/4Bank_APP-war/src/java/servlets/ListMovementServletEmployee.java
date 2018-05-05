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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistence.Account;
import persistence.Movement;
import persistence.User;

/**
 *
 * @author JavierVazquez
 */
@WebServlet(name = "ListMovementEmployee", urlPatterns = {"/ListMovementEmployee"})
public class ListMovementServletEmployee extends HttpServlet {

    @EJB
    private UserFacade userFacade;

    @EJB
    private MovementFacade movementFacade;

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
        
        String idAccount = request.getParameter("idAcc");
        String idUser = request.getParameter("idUser");
        Account selectedAccount = accountFacade.find(idAccount);
        User selectedUser = userFacade.find(Integer.parseInt(idUser));
        List <Movement> movementList = movementFacade.queryAllMovementsFromAndToAccount(selectedAccount);
        Map<Integer, String> recept = new HashMap<>();
         User otherAccount = null;
         
        for (Movement mov : movementList) {
            otherAccount = getUser(mov, selectedAccount);
            recept.put(otherAccount.getIdUSER(), otherAccount.getName() + " " + otherAccount.getSurname());
        }
        
        request.setAttribute("movementList", movementList);
        request.setAttribute("selectedAccount", selectedAccount);
        request.setAttribute("recept", recept);
        request.setAttribute("selectedUser", selectedUser);
        
        RequestDispatcher rd = request.getRequestDispatcher("/addMovement.jsp");
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
     
    private User getUser(Movement mov, Account selectedAccount) {
        if (mov.getIdACCOUNT().equals(selectedAccount)) {
            return mov.getIdACCOUNTreceptor().getIdUSER();
        } else {
            return mov.getIdACCOUNT().getIdUSER();
        }
    }
}
