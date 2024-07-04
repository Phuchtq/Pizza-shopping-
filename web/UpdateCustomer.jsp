<%-- 
    Document   : UpdateCustomer
    Created on : Mar 13, 2024, 8:15:05 AM
    Author     : pc
--%>

<%@page import="Models.Entities.Customer"%>
<%@page import="java.util.List"%>
<%@page import="Models.DAO.CustomerDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <style>
        .updateForm {
            width: 45%;
            height: 50%;
            font-size: 30px;
            background-color: AliceBlue;
            margin: 50px auto;
        }
        input[type=submit] {
            height: 26px
        }
    </style>
    <body>
        <jsp:include page="navbar.jsp"/> 
        <h1 style="font-size: 55px; margin-top: 15px; margin-bottom: 0">CUSTOMER INFORMATION</h1>     
        <%
            String customerId = request.getParameter("customerId");
            CustomerDAO cDao = new CustomerDAO();
            List<Customer> list = cDao.searchById(customerId);
            Customer c = list.get(0);
        %>
        <div class="updateForm">
            <div style="width: 100%; text-align: center; background-color: #FFE7BA; color: #000080;"><h2 style="font-size: 47px">UPDATE INFORMATION</h2></div>
            <form action="CustomerUpdateController" method="post">
                Customer ID <input type="text" name="customerId" value="<%=c.getCustomerId()%>" style="width: 50%; height: 30px;" readonly=""/><br><br>
                Password <input type='text' name='password' value="<%=c.getPassword()%>" style="width: 50%; height: 30px;"/><br><br>
                Contact Name <input type="text" name="contactName" value="<%=c.getContactName()%>" style="width: 50%; height: 30px;"/><br/><br/>
                Address <input type="text" name="address" value="<%=c.getAddress()%>" style="width: 50%; height: 30px;"/><br/><br/>          
                Phone number <input type="text" name="phone" value="<%=c.getPhone()%>" style="width: 50%; height: 30px;"/><br/><br/>
                <input type="submit" value="UPDATE" style="background: #FFE7BA; color: #000080; width: 100%; height: 47px; border-style: none"/>
            </form>
        </div>
        <%
            String msg = (String) request.getAttribute("msg");
            if (msg != null) {
        %>
        <h1 style="color: red; text-align: center"><%=msg%></h1>   
        <% }%>
        <jsp:include page="Footer.html"/>
    </body>
</html>
