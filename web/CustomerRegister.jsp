<%-- 
    Document   : CustomerRegister
    Created on : Mar 9, 2024, 8:15:02 AM
    Author     : pc
--%>

<%@page import="Models.Entities.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Registration</title>
    </head>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #E4ADC4;
        }
        .signup-container {
            margin-top: 50px;
            width: 95%;
            height: 70%;
            display: flex;
            margin-left: auto;
            margin-right: auto;
        }
        .left-section {
            width: 50%;
            background: url('Images/CustomerRegister.jpg') no-repeat center center;
            background-size: cover;
        }
        .right-section {
            width: 50%;
            background-color: white;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            height: 93%;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        input[type=text], input[type=password] {
            margin-bottom: 10px;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            width: 72%;
        }
        input[type=submit] {
            padding: 10px 20px;
            color: white;
            background-color: rgb(127, 209, 241);
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
        }
        input[type=submit]:hover {
            background-color: #4cae4c;
        }
    </style>
    <body>
        <jsp:include page="navbar.jsp"/> 
        <%
            Account a = (Account) request.getAttribute("customer account");
        %>
        <div class="signup-container">
            <div class="left-section">
            </div>
            <div class="right-section">
                <div style="background-color: rgb(156, 221, 247); padding-left: 0px; padding-right: 0px;"><h2>Sign Up</h2></div>
                <form action="CustomerRegistrationController" method="post">
                    Customer ID <input type="text" name="customerId" value="<%=a.getAccountId()%>" readonly=""/> <br/>
                    Password <input type="text" name="password" value="<%=a.getPassword()%>" readonly=""/>  <br/>
                    Contact Name <input type="text" name="contactName" value="<%=a.getFullName()%>" readonly=""/>  <br/>
                    Address <input type="text" name="address"/>  <br/>
                    Phone number <input type="text" name="phone"/><br/><br/>
                    <input type="submit" value="Register"/>
                </form>
            </div>
        </div>
        <%
            String msg = (String) request.getAttribute("msg");
            if (msg != null) {
        %>
        <h3 style="color: red"><%=msg%></h3>
        <% }%>
    </body>
</html>
