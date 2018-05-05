<%-- 
    Document   : editUser
    Created on : 04-may-2018, 16:31:39
    Author     : sjuradoq
--%>

<%@page import="persistence.Account"%>
<%@page import="java.util.List"%>
<%@page import="persistence.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    User selectedUser = (User) request.getAttribute("selectedUser");
    List<Account> accountList = (List<Account>) request.getAttribute("accountList");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>4Bank - Edit User</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        <%@include file="header.jsp" %>

        <div class="row">

            <div class="col-md-6">
                <h1>Update User:</h1>
                <form action="UpdateUser" method="POST">

                    <div class="form-group row">
                        <label class="form-check-label col-md-4 col-form-label" for="username">
                            Username:
                        </label>
                        <h5 class="col-md-4"><%= selectedUser.getUsername()%></h5>
                        <input class="col-md-4 form-control" name="username" type="text">
                    </div>

                    <div class="form-group row">
                        <label class="form-check-label col-md-4 col-form-label" for="name">
                            Name:
                        </label>
                        <h5 class="col-md-4"><%= selectedUser.getName()%></h5>
                        <input class="col-md-4 form-control" name="name" type="text">
                    </div>

                    <div class="form-group row">
                        <label class="form-check-label col-md-4 col-form-label"for="surname">
                            Surname:
                        </label>
                        <h5 class="col-md-4"><%= selectedUser.getSurname()%></h5>
                        <input class="col-md-4 form-control" name="surname" type="text">
                    </div>

                    <div class="form-group row">
                        <label class="form-check-label col-md-4 col-form-label" for="email">
                            Email:
                        </label>
                        <h5 class="col-md-4"><%= selectedUser.getEmail()%></h5>
                        <input class="col-md-4 form-control" name="email" type="text">
                    </div>

                    <div class="form-group row">
                        <label class="form-check-label col-md-4 col-form-label" for="nif">
                            NIF:
                        </label>
                        <h5 class="col-md-4"><%= selectedUser.getNif()%></h5>
                        <input class="col-md-4 form-control" name="nif" type="text">
                    </div>

                    <div class="form-group row">
                        <label class="form-check-label col-md-4 col-form-label" for="phone">
                            Phone:
                        </label>
                        <h5 class="col-md-4"><%= selectedUser.getPhoneNumber()%></h5>
                        <input class="col-md-4 form-control" name="phone" type="text">
                    </div>

                    <div class="form-group row">
                        <label class="form-check-label col-md-4 col-form-label" for="address">
                            Address:
                        </label>
                        <h5 class="col-md-4"><%= selectedUser.getAddress()%></h5>
                        <input class="col-md-4 form-control" name="address" type="text">
                    </div>

                    <div class="form-group row">
                        <label class="form-check-label col-md-4 col-form-label" for="password">
                            Password:
                        </label>
                        <span class="col-md-4"></span>
                        <input class="col-md-4 form-control" type="password">
                    </div>

                    <input type="hidden" name="idUSER" value="<%= selectedUser.getIdUSER()%>" >  

                    <button type="submit" class="btn btn-primary">Update User</button>     

                </form>

            </div>


            <div class="col-md-6">
                <h1>User Accounts</h1>
                <form action="CreateAccount" method="POST">
                    <input hidden name="idUSER" value="<%= selectedUser.getIdUSER()%>">
                    <div class="form-group row">
                        <label for="initialBalance" class="form-check-label col-form-label col-md-3">
                            Initial balance:</label>
                        <input type="number" name="initialBalance" class="form-control col-md-4">
                        <div class="col-md-1"></div>
                        <button type="submit" class="btn btn-warning" class="col-md-4">New Account</button>
                    </div>
                </form>
                </br>
                <table class="table table-hover">
                    <thead class="thead-light">
                        <tr>
                            <th scope="col">Account number</th>
                            <th scope="col">Balance</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%                                if (accountList != null) {
                        %>

                        <%
                            for (Account account : accountList) {
                        %>
                        <tr>
                            <th scope="row"><%= account.getIdACCOUNT()%></th>
                            <td><%= account.getBalance()%></td>
                            <td>
                                <form action="ListMovementEmployee" method="GET">
                                    <button type="submit" class="btn btn-warning">
                                        <i class="fa fa-pencil fa-lg"></i>
                                    </button>
                                    <input type="hidden" name="idAcc" value="<%= account.getIdACCOUNT()%>"/>
                                    <input type="hidden" name="idUser" value="<%= selectedUser.getIdUSER() %>"/>
                                </form>
                                <form action='DeleteAccount' method='GET'>
                                    <button type="submit" class="btn btn-danger">
                                        <i class="fa fa-times fa-lg"></i>
                                    </button>

                                    <input type="hidden" name="idAcc" value="<%= account.getIdACCOUNT()%>"/>
                                    <input type="hidden" name="idUser" value="<%= selectedUser.getIdUSER()%>"/>
                                </form>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                        <%
                            }
                        %>

                    </tbody>
                </table>
            </div>
        </div>
        <br>

    </body>
</html>
