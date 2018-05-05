<%-- 
    Document   : addMovement
    Created on : 04-may-2018, 17:11:17
    Author     : JavierVazquez
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
    User selectedUser = (User) request.getAttribute("selectedUser");
    Account selectedAccount = (Account) request.getAttribute("selectedAccount");
    List<Movement> movementsList = (List<Movement>) request.getAttribute("movementList");
    Map<Integer, String> recept = (Map) request.getAttribute("recept");
    SimpleDateFormat dateFormat = new SimpleDateFormat("EE dd/MM/YY HH:mm");
%>
<html>
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>4Bank- New Transaction</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        <%@include file="header.jsp"%>
        <div class="container">
            <div class="row">
                <div class="col-md-6" style="padding-right:20px; padding-left:20px; border-right: 1px solid #ccc;">
                    <h1>Add Movement:</h1>
                    </br>
                    <form action="CreateMovement" method="post">
                        <div class="form-group row">
                            <label for="receptorAccount" class="form-check-label col-md-3 col-form-label">Receptor Account Id:</label>
                            <input type="text" class="form-control col-md-6" name="idAccount_receptor">
                        </div>
                        <div class="form-group row">
                            <label for="concept" class="form-check-label col-md-3 col-form-label">Concept: </label>
                            <input type="text" class="form-control col-md-6" name="concept">
                        </div>
                        <div class="form-group row">
                            <label for="amount" class="form-check-label col-md-3 col-form-label">Amount:</label>
                            <input type="text" class="form-control col-md-6" name="amount">
                        </div>
                         <input type="hidden" name="selectedAccount" value="<%= selectedAccount.getIdACCOUNT()%>" > 
                         <input type="hidden" name="selectedUser" value="<%= selectedUser.getIdUSER() %>" > 
                         
                        <button type="submit" class="btn btn-primary">Add Movement</button>   
                    </form>
                </div>
                <div class="col-md-6">
                    <h1>        Accounts: <%= selectedAccount.getIdACCOUNT()%> </h1>
                    <br/>
                    <div class="container">
                        <table class="table table-hover">
                            <thead class="thead-light">
                                <tr>
                                    <th>Entity</th>
                                    <th>Concept</th>
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
                                    <td><%= recept.get(movement.getIdACCOUNTreceptor().getIdUSER().getIdUSER())%></td>
                                    <td><%= movement.getConcept() %></td>
                                    <td style="color: red">-<%= movement.getAmount()%>€</td>
                                    <td><%= movement.getNewBalanceSender()%>€</td>
                                    <td><%= dateFormat.format(movement.getDate())%></td>
                                </tr>
                                <%
                                } else if (movement.getIdACCOUNTreceptor().equals(selectedAccount)) {
                                %>
                                <tr>
                                    <td><%= recept.get(movement.getIdACCOUNT().getIdUSER().getIdUSER())%></td>
                                    <td><%= movement.getConcept() %></td>
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

                </div>
            </div>
        </div>
    </body>
</html>
</html>
