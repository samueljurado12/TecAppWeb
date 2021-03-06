<%-- 
    Document   : transfer
    Created on : 09-abr-2018, 10:38:49
    Author     : sjuradoq
--%>

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
    if (selectedAccount == null) {
        selectedAccount = accountsList.get(0);
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>4Bank - Transfer</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
        <%@include file="header.jsp" %>
<%--        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>--%>
        <h1>Transfer</h1>
        <br/>
        <div class="container align-items-center">
            <form action="MakeTransferServlet" method="post">
                <div class="form-group row">
                    <label  for ="selectAccount" class="form-check-label col-sm-3 col-form-label"><h4>1.Select account:</h4></label>
                    <%--Insert for loop to insert account numbers--%>
                    <div class="col-sm-5">
                        <select class="form-control" name="senderAccount">
                            <%
                                for (Account account : accountsList) {
                            %>
                            <option><%=account.getIdACCOUNT()%></option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="receiverAccount" 
                           class="form-check-label col-sm-3 col-form-label">
                        <h4>2.Payment to:</h4>
                    </label>
                    <div class="col-sm-5">
                        <input class="form-control" 
                               name="receiverAccount" 
                               id="receiverAccount"
                               type="text"
                               placeholder="Receptor account number">
                    </div>
                </div>

                <div class="form-group row">
                    <label for="amount" 
                           class="form-check-label col-sm-3 col-form-label">
                        <h4>3.Amount(€):</h4>
                    </label>
                    <div class="col-sm-5">
                        <input class="form-control" 
                               name="amount" 
                               id="amount"
                               type="number"
                               placeholder="Amount">
                    </div>
                    <div class="col-sm-4">
                        <p style="margin-top: 0.5em">(Fee amount): 0.00€</p>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="remarks" 
                           class="form-check-label col-sm-3 col-form-label">
                        <h4>4.Transfer remarks:</h4>
                    </label>
                    <div class="col-sm-5">
                        <input class="form-control" 
                               name="remarks" 
                               id="remarks"
                               type="text"
                               placeholder="Insert transfer remarks here">
                    </div>
                </div>
                        
                <%
                    if (request.getAttribute("success") != null && request.getAttribute("success").equals("success")) {
                %>        
                <div class="form-group">
                    <p class="text-success">Success!</label>
                </div>
                <%
                    } else if (request.getAttribute("success") != null && request.getAttribute("success").equals("wrong")) {
                %>        
                <div class="form-group">
                    <p class="text-danger">Something went wrong...</label>
                </div>
                <%
                    }
                %> 
                

                <hr/>

                <button type="submit" class="btn btn-primary">Submit</button>
                <button type="reset" class="btn btn-danger">Reset</button>

            </form>
        </div>
    </body>
</html>
