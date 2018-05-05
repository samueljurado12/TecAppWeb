<%@page import="persistence.User"%>
<%-- 
    Document   : header
    Created on : 09-abr-2018, 10:55:56
    Author     : sjuradoq
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    User userHeader = (User) session.getAttribute("user");
    
    if(userHeader == null){
        response.sendRedirect("");
        return;
    }
    
    boolean isEmployee = ((Boolean) session.getAttribute("isEmployee")).booleanValue();
%>
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<div class='row'>
    <div class="col-lg-3">
        <a> <img  src="resources/4bank.png" class="img-fluid" alt="logo"></a>
    </div>
    <div class="col-lg-4">
        <h2 
            style="color: graytext; margin-top: 1em">Your bank and everyday the more people's one
        </h2>
    </div>
</div>

<nav class="navbar navbar-expand-xl navbar-dark bg-dark sticky-top">
    <ul class="navbar-nav">
          <%
            if (!isEmployee) {
        %>
        <li class="nav-item">
            <a class="nav-link" href="ListMovement">Accounts</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="transfer.jsp">Transfer</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="contact_us.jsp">Contact Us</a>
        </li>
        <%
            }
            if (isEmployee) {
        %>
        <li class="nav-item">
            <a class="nav-link" href="employee">Employee</a>
        </li>
        <%
            }
        %>
    </ul>
    <ul class="navbar-nav ml-auto">
        <li class="nav-item">
            <a class="nav-link" href="user_info.jsp"><%= userHeader.getName() + " " + userHeader.getSurname()%>      </a>
        </li>
        <form method="post" action="LogoutServlet">
            <button class="btn btn-secondary" type="submit"><i class="fa fa-sign-out"></i> Logout</button>
        </form>
    </ul>
</nav>