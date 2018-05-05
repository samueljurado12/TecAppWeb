<%-- 
    Document   : accounts
    Created on : 11-abr-2018, 12:19:28
    Author     : samueljurado12
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Map"%>
<%@page import="persistence.Movement"%>
<%@page import="persistence.Account"%>
<%@page import="java.util.List"%>
<%@page import="persistence.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    
    if(session.getAttribute("user") == null){
        response.sendRedirect("");
        return;
    }
    
    User userActive = (User) session.getAttribute("user");
    
    List<Account> accountsList = userActive.getAccountList();
    Account selectedAccount = (Account) session.getAttribute("selectedAccount");

    List<Movement> movementsList = (List<Movement>)session.getAttribute("movementList");
    Map<Integer, String> receptors = (Map) session.getAttribute("receptors");
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("EE dd/MM/YY HH:mm");
%>
<html>
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>4Bank- Accounts</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
        <%@include file="header.jsp"%>
        <h1>        Accounts</h1>
        <br/>
        <div class="container align-items-center">
            <form action="ListMovement" method="post">
                <div class="form-group row">
                    <label  for ="selectedAccount" class="form-check-label col-sm-3 col-form-label"><h4>Current Account</h4></label>
                    <div class="col-sm-4">
                        <select class="form-control" name="selectedAccount">
                            <%  for (Account account : accountsList) {
                                    if (selectedAccount.equals(account)) {
                            %>
                            <option selected><%=account.getIdACCOUNT()%></option>
                            <%
                            } else {
                            %>
                            <option><%=account.getIdACCOUNT()%></option>
                            <% }
                                }
                            %>
                        </select>
                    </div>
                    <div class="col-sm-3">
                        <h4><%= selectedAccount.getBalance()%>€</h4>
                    </div>
                    <div class="col-sm-2">
                        <button class="btn btn-primary" type="submit">Refrescar</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="container">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Entity</th>
                        <th>Amount</th>
                        <th>Balance</th>
                        <th>Date</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (Movement movement : movementsList) {
                            if (movement.getIdACCOUNT().equals(selectedAccount)) {
                    %>
                    <tr>
                        <td><%= receptors.get(movement.getIdACCOUNTreceptor().getIdUSER().getIdUSER())%></td>
                        <td style="color: red">-<%= movement.getAmount()%>€</td>
                        <td><%= movement.getNewBalanceSender()%>€</td>
                        <td><%= dateFormat.format(movement.getDate())%></td>
                    </tr>
                    <%
                    } else if (movement.getIdACCOUNTreceptor().equals(selectedAccount)) {
                    %>
                    <tr>
                        <td><%= receptors.get(movement.getIdACCOUNT().getIdUSER().getIdUSER())%></td>
                        <td style="color: green"><%= movement.getAmount()%>€</td>
                        <td><%= movement.getNewBalanceReceiver()%>€</td>
                        <td><%= dateFormat.format(movement.getDate())%></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
    </body>
</html>
