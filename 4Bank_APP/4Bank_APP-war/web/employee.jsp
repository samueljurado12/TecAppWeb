<%@page import="persistence.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>4Bank - Employee</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>

    <%
        List<User> userList = (List<User>) request.getAttribute("users");
    %>

    <body>
        <%@include file="header.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-4" style="padding-right:20px; padding-left:20px; border-right: 1px solid #ccc;">
                    <h1>Create user:</h1>
                    </br>

                    <form action="CreateUser" method="post">
                        <div class="form-group row">
                            <label for="username" class="form-check-label col-md-3 col-form-label">Name:</label>
                            <input type="text" class="form-control col-md-6" name="username">
                        </div>
                        <div class="form-group row">
                            <label for="surname" class="form-check-label col-md-3 col-form-label">Surname: </label>
                            <input type="text" class="form-control col-md-6" name="surname">
                        </div>
                        <div class="form-group row">
                            <label for="mail" class="form-check-label col-md-3 col-form-label">Mail:</label>
                            <input type="email" class="form-control col-md-6" name="mail">
                        </div>
                        <div class="form-group row">
                            <label for="phone" class="form-check-label col-md-3 col-form-label">Phone:</label>
                            <input type="text" class="form-control col-md-6" name="phone">
                        </div>
                        <div class="form-group row">
                            <label for="nif" class="form-check-label col-md-3 col-form-label">NIF: </label>
                            <input type="text" class="form-control col-md-6" name="nif">
                        </div>
                        <div class="form-group row">
                            <label for="Address" class="form-check-label col-md-3 col-form-label">Address: </label>
                            <input type="text" class="form-control col-md-6" name="adress">
                        </div>
                         
                        <button type="submit" class="btn btn-primary">Create User</button>   
                    </form>
                </div>
                <div class="col-md-8">
                    <h1>Update User:</h1>
                    </br>
                    <table class="table table-hover">
                        <thead class="thead-light">
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Username</th>
                                <th scope="col">Full Name</th>
                                <th scope="col">Email</th>
                                <th scope="col">Options</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%                                if (userList != null) {
                            %>

                            <%
                                for (User user : userList) {
                            %>
                            <tr>
                                <th scope="row"><%= user.getNif()%></th>
                                <td><%= user.getUsername()%>
                                </td>
                                <td><%= user.getName() + " " + user.getSurname()%></td>
                                <td><%= user.getEmail()%></td>
                                <td>
                                    <form action="EditUser" method="GET">
                                        <button type="submit" class="btn btn-warning">
                                            <i class="fa fa-pencil fa-lg"></i>
                                        </button>
                                        <input type="hidden" name="idUser" value="<%= user.getIdUSER()%>"/>
                                    </form>
                                    <form action='RemoveUser' method='post'>
                                        <button type="submit" class="btn btn-danger"> 
                                            <i class="fa fa-times fa-lg"></i> 
                                        </button>

                                        <input type="hidden" name="id" value="<%= user.getIdUSER()%>"/>
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
        </div>
    </body>
</html>
