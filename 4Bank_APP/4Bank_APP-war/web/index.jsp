<%-- 
    Document   : index
    Created on : 05-abr-2018, 17:49:44
    Author     : sjuradoq
--%>

<%@page import="persistence.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    User usuario = (User) session.getAttribute("user");

    if (usuario != null) {
        if (usuario.getIsEmployee()) {
            response.sendRedirect("employee");
        } else {
            response.sendRedirect("accounts.jsp");
        }
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>4Bank</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <img class="img-fluid" src="resources/4bank.png">
                </div>
                <div class="col-md-8 text-right">
                    <h2 style="color: DarkGray;">Your Bank and each day <br> more people one's</h2>
                </div>
            </div>
        </div>
        <hr/>
        <div class="container">
            <div class="row">
                <div class="col-md-8">
                    <img class="img-fluid" src="resources/4bank-promocion.png">
                </div>
                <div class="col-md-4">
                    <p>Sign in</p>
                    <hr/>
                    <form method="post" action="LoginServlet">
                        <div class="form-group">
                            <label for="username">Username:</label>
                            <input type="username" class="form-control" name="username">
                        </div>
                        <div class="form-group">
                            <label for="pwd">Password:</label>
                            <input type="password" class="form-control" name="pwd">
                        </div>
                        <%
                            if (request.getAttribute("wrong") != null && request.getAttribute("wrong").equals("wrong")) {
                        %>        
                        <div class="form-group">
                            <p class="text-danger">Wrong password/username</label>
                        </div>
                        <%
                            }
                        %> 

                        <hr/>
                        <div>
                            <button type="submit" class="btn btn-primary">Log in</button>
                        </div>
                        </a>
                    </form> 
                </div>
            </div>
        </div>
    </body>
</html>
