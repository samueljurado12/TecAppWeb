<%-- 
    Document   : transfer
    Created on : 09-abr-2018, 10:38:49
    Author     : sjuradoq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>4Bank - Transfer</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container align-items-center">
            <form action="transfer.servlet" method="post">
                <label class="form-check-label"><h4>1.Select account:</h4></label>
                <%--Insert for loop to insert account numbers--%>
                <div class="form-group">
                    <input class="form-check-input" 
                           type="radio" 
                           value="<%--Account number--%>"
                           id="accountX"
                           name="accountNumber">
                    <label class="form-check-label" for="accountX">
                        ACCOUNT #<%--Account number--%>
                    </label>
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
                        <textarea rows="4" id="remarks" class="form-control"></textarea>
                    </div>
                </div>
                
                <button type="submit" class="btn btn-primary">Submit</button>
                <button type="reset" class="btn btn-danger">Reset</button>

            </form>
        </div>
    </body>
</html>
