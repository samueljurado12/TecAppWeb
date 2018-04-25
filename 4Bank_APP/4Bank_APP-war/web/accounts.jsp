<%-- 
    Document   : contact_us
    Created on : 11-abr-2018, 12:19:28
    Author     : Roberto
--%>

<%@page import="persistence.Account"%>
<%@page import="java.util.List"%>
<%@page import="persistence.Users"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Users userActive = (Users) session.getAttribute("user");
    List<Account> accountsList = userActive.getAccountList();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>4Bank- Contact Us</title>
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
                    <tr>
                        <td>Apple Store</td>
                        <td>-349.99</td>
                        <td>649.87</td>
                        <td>20 Oct, 8:56 AM (2013)</td>
                    </tr>
                    <tr>
                        <td>British Cut</td>
                        <td>-34.99</td>
                        <td>632.87</td>
                        <td>19 Oct, 8:36 PM (2013)</td>
                    </tr>
                    <tr>
                        <td>100 Little Rides</td>
                        <td>-4.56</td>
                        <td>1039.43</td>
                        <td>17 Oct, 3:43 PM (2013)</td>
                    </tr>
                    <tr>
                        <td>Paypal</td>
                        <td>239.43</td>
                        <td>1239.43</td>
                        <td>14 Oct, 6:01 PM (2013)</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </body>
</html>
