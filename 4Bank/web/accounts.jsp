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
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <h1>Accounts</h1>
        <br/>
        <div class="container align-items-center">
            <div class="row">
                <div class="col-md-5">
                    <h3>Current Account</h3>
                </div>
            </div>
            <div class="row">
                <div class="well well-sm">
                    <div class="radio">
                        <label><input type="radio" name="optradio">GB29 NWBK 6016 1331 9268 19</label>
                    </div>
                    <div class="radio">
                        <label><input type="radio" name="optradio" checked>GB29 NWBK 6016 1331 3214 25</label>
                    </div>
                    <div class="radio">
                        <label><input type="radio" name="optradio">GB29 NWBK 6016 1331 4920 12</label>
                    </div>
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
