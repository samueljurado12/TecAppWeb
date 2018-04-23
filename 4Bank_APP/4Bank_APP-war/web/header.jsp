    <%-- 
    Document   : header
    Created on : 09-abr-2018, 10:55:56
    Author     : sjuradoq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class='row'>
    <div class="col-lg-3">
        <a href="/TecAppWeb/index.jsp"> <img  src="resources/4bank.png" class="img-fluid" alt="logo"></a>
    </div>
    <div class="col-lg-4">
        <h2 
            style="color: graytext; margin-top: 1em">Your bank and everyday the more people's one
        </h2>
     <button type = "button" class="btn btn-primary" type="submit" data-toggle="modal" data-target="ejemplo">Logout</button>
    </div>
</div>

  <nav class="navbar navbar-expand-md navbar-dark bg-dark justify-content-center">
      <ul class="navbar-nav">
          <li class="nav-item">
              <a class="nav-link" href="accounts.jsp">Accounts</a>
          </li>
          <li class="nav-item active">
              <a class="nav-link" href="transfer.jsp">Transfer</a>
          </li>
          <li class="nav-item">
              <a class="nav-link" href="user_management.jsp">User Management</a>
          </li>
          <li class="nav-item">
              <a class="nav-link" href="contact_us.jsp">Contact Us</a>
          </li>
          <li class="nav-item">
          <a class="nav-link" href="#">Help</a>
          </li>
          <li class="nav-item">
          <a class="nav-link" href="#">Employee</a>
          </li>
      </ul>
  </nav>