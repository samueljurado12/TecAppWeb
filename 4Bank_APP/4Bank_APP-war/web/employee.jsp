<%@page import="persistence.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>4Bank - employee</title>
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
                <div class="col-md-6" style="padding-right:20px; padding-left:20px; border-right: 1px solid #ccc;">
                    <h1>Create user:</h1>
                    </br>
                    <form action="CreateUserServlet" method="post">
                        <div class="form-group row">
                            <label for="username" class="form-check-label col-md-3 col-form-label">Name:</label>
                            <input type="username" class="form-control col-md-6" id="username">
                        </div>
                        <div class="form-group row">
                            <label for="mail" class="form-check-label col-md-3 col-form-label">Mail:</label>
                            <input type="mail" class="form-control col-md-6" id="mail">
                        </div>
                        <div class="form-group row">
                            <label for="phone" class="form-check-label col-md-3 col-form-label">Phone:</label>
                            <input type="phone" class="form-control col-md-6" id="phone">
                        </div>
                        <div class="form-group row">
                            <label for="password" class="form-check-label col-md-3 col-form-label">Autogenerated password: </label>
                            <div class="col-md-6">
                                <input type="text" readonly class="form-control-plaintext" id="defaultPassword" value="aa1234567890">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                        <button type="reset" class="btn btn-danger">Reset</button>
                    </form>
                </div>
                <div class="col-md-6">
                    <h1>Update User:</h1>
                    </br>
                    <%-- TODO change defaults for real actions --%>
                    <table class="table table-hover">
                        <thead class="thead-light">
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Username</th>
                                <th scope="col">Full Name</th>
                                <th scope="col">Email</th>
                                <th scope="col">Options</th>                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                if (userList != null) {
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
                                    <button type="submit" class="btn btn-warning">
                                        <i class="fa fa-pencil fa-lg"></i>
                                    </button>
                                    <button type="submit" class="btn btn-danger">
                                            <i class="fa fa-times fa-lg"></i>
                                        </button>
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