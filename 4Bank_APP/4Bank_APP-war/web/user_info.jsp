<%-- 
    Document   : user_management
    Created on : 10-abr-2018, 13:46:56
    Author     : JavierVazquez
--%>

<%@page import="persistence.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    User userActive = (User) session.getAttribute("user");
    
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>4Bank- User Info</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
        <%@include file="header.jsp" %>
        <h1>User Info</h1>
        <br/>
        <div class="container "> 
            <div class="row  d-flex justify-content-center">
                <div class="col-xs-12 col-sm-6 col-md-6">
                    <div class="well well-sm">
                        <div class="row">
                            <div class="col-sm-6 col-md-4">
                                <img src="resources/4bank-user.png" alt="user-image" class="img-fluid"/>
                            </div>
                            <div class="col-sm-6 col-md-8">
                                <h4>
                                    <%= userActive.getName() + " " + userActive.getSurname()%> </h4>
                                <small><cite title="localizacion"><%= userActive.getAddress()%> <i class="glyphicon glyphicon-map-marker">
                                        </i></cite></small>
                                <p>
                                    <i class="glyphicon glyphicon-envelope"></i><%= userActive.getEmail()%>
                                    <br />
                                    <i class="glyphicon glyphicon-earphone"></i><%= "+34" + userActive.getPhoneNumber()%>
                                    <br />
                                    <i class="glyphicon glyphicon-gift"></i><%= userActive.getNif()%></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
