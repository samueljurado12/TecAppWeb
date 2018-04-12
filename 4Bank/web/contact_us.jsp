<%-- 
    Document   : contact_us
    Created on : 11-abr-2018, 12:19:28
    Author     : Roberto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>4Bank- Contact Us</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
        <%@include file="header.jsp" %>
        <h1>Contact Us</h1>
        <br/>
        <div class="container">
            <div class="row">
                <div class="col-md-5">
                    <div class="container">
                        <div class="col-sm-4">
                            <img src="resources/4bank-phone.png" alt="user-image" class="img-fluid"/>
                        </div>
                        <div class="col-sm-8">
                            <h3>For customer support:</h3>
                            <h4>8344-182-3312</h4>
                        </div>
                    </div>
                    <br/>
                    <br/>
                    <div class="container">
                        <div class="col-sm-4">
                            <img src="resources/4bank-mail.png" alt="user-image" class="img-fluid"/>
                        </div>
                        <div class="col-sm-8">
                            <h3>Postal Mail:</h3>
                            <br/>
                            <p>When mailing to us, please include</p>
                            <ul>
                                <li>Customer name</li>
                                <li>Telephone number</li>
                                <li>Account number</li>
                            </ul>
                            <p>
                                KSOPHT0101-Z4300<br/>
                                6391 Sprint Parkway<br/>
                                Overland Park KS 66251-4300
                            </p>
                        </div>
                    </div>
                    <br/>
                <br/>
                </div>
                <div class="col-md-7">
                    <div class="container">
                        <div class="col-sm-4">
                            <img src="resources/4bank-email.png" alt="user-image" class="img-fluid"/>
                        </div>
                        <div class="col-sm-8">
                            <form action="contact_us.servlet" method="post">
                                <h3>Contact Form</h3>
                                <div class="form-group row">
                                    <label for="from" 
                                           class="form-check-label col-sm-3 col-form-label">
                                        <h4>From:</h4>
                                    </label>
                                    <div class="col-sm-9">
                                        <input class="form-control" 
                                               name="from" 
                                               id="from"
                                               type="text"
                                               placeholder="Email">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="subject" 
                                           class="form-check-label col-sm-3 col-form-label">
                                        <h4>Subject:</h4>
                                    </label>
                                    <div class="col-sm-9">
                                        <input class="form-control" 
                                                name="subject" 
                                                id="subject"
                                                type="text"
                                                placeholder="Subject">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="textarea"><h4>Text:</h4></label>
                                    <textarea class="form-control"
                                              id="textarea"
                                            rows="6">
                                    </textarea>
                                </div>
                                
                                <button type="submit" class="btn btn-primary">Submit</button>
                                <button type="reset" class="btn btn-danger">Reset</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
