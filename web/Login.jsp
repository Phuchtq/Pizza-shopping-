<%-- 
    Document   : Login
    Created on : Mar 8, 2024, 1:14:38 AM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
                margin-top: 50px;
                width: 95%;
                height: 87%;
                display: flex;
                margin: 70px auto;
            }
            .left-section {
                width: 70%;
                background: url('Images/Login.jpg') no-repeat center center;
                background-size: cover;
            }
            .right-section {
                width: 50%;
                background-color: lightcyan;
                padding: 20px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
                height: 95%;
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
                background-color: lightpink;
            }
        </style>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="signup-container">
            <div class="left-section">
            </div>
            <div class="right-section">
                <div style="background-color: rgb(156, 221, 247); padding-left: 0px; padding-right: 0px;"><h2>Log in</h2></div>
                <form action="LoginProcessController" method="post">
                    Username <input type="text" name="username" placeholder="Username"/> <br/>
                    Password <input type="password" name="password" placeholder="Password"/> <br/>
                    <input type="submit" value="Log In"/>
                </form>
                <%
                    String msg = (String) request.getAttribute("msg");
                    if (msg != null) {
                %>
                <h2 style="color: red"><%=msg%></h2>
                <% }%>
            </div>
        </div>
        <jsp:include page="Footer.html"/>
    </body>
</html>
