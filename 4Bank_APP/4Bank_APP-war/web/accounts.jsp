<%-- 
    Document   : contact_us
    Created on : 11-abr-2018, 12:19:28
    Author     : Roberto
--%>

<%@page import="java.util.Map"%>
<%@page import="persistence.Movements"%>
<%@page import="persistence.Account"%>
<%@page import="java.util.List"%>
<%@page import="persistence.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Users userActive = (Users) session.getAttribute("user");
    List<Account> accountsList = userActive.getAccountList();
    Account selectedAccount = (Account)session.getAttribute("selectedAccount");
    if (selectedAccount == null) {
        selectedAccount = accountsList.get(0);
    }
    List<Movements> movementsList = selectedAccount.getMovementsList();
    Map<Integer, String> receptors = (Map)session.getAttribute("receptors");
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
            <div class="form-group row">
                <label  for ="selectAccount" class="form-check-label col-sm-3 col-form-label"><h4>Current Account</h4></label>
                <%--Insert for loop to insert account numbers--%>
                <div class="col-sm-5">
                    <select class="form-control">
                        <%
                            for (Account account : accountsList) {
                        %>
                        <option><%=account.getAccountPK().getIdACCOUNT()%></option>
                        <%
                            }
                        %>
                    </select>
                </div>
            </div>
        </div>
        <div class="container">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Entity</th>
                        <th>Paid</th>
                        <th>Balance</th>
                        <th>Date</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for(Movements movement : movementsList){
                    %>
                    <tr>
                        <td><%= receptors.get(movement.getIdUSERSreceptor()) %></td>
                        <td><%= movement.getAmount() %>€</td>
                        <td><%= movement.getNewBalance()%>€</td>
                        <td><%= movement.getDate()%></td>
                    </tr>
                    <%
                        }
                    %>

                </tbody>
            </table>
        </div>
    </body>
</html>
