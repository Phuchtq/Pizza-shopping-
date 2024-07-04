<%-- 
    Document   : Register
    Created on : Mar 8, 2024, 1:15:07 AM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f7f7f7;
        }
        .signup-container {
            width: 95%;
            height: 70%;
            display: flex;
            margin: 70px auto;
        }
        .left-section {
            width: 50%;
            background: url('Images/AccoutRegister.jpg') no-repeat center center;
            background-size: cover;
        }
        .right-section {
            width: 50%;
            background-color: white;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            height: 93%;
        }
        h2 {
            margin-top: 0;
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
        select {
            width: 20%;
        }
    </style>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="signup-container">
            <div class="left-section">
            </div>
            <div class="right-section">
                <div style="background-color: rgb(156, 221, 247); padding-left: 0px; padding-right: 0px;"><h2>Sign Up</h2></div>
                <form action="RegistrationController" method="post">
                    Account ID <input type="text" name="accountId" placeholder="Account ID"/> <br/>
                    User name <input type="text" name="username" placeholder="Username"/> <br/>
                    Password <input type="text" name="password" placeholder="Password"/> <br/>
                    Full name <input type="text" name="fullName" placeholder="Full name"/> <br/>
                    Type <select name="type">
                        <option value="0">User</option>
                        <option value="1">Staff</option>
                    </select> <br/> <br/>
                    <input type="submit" value="Sign Up"/><br/>
                </form>
                <%
                    String msg = request.getParameter("msg");
                    if (msg != null) {
                %>
                <h2 style="color: red"><%=msg%></h2>
                <% }%>
            </div>
        </div>
        <jsp:include page="Footer.html"/>
    </body>
</html>
