<%-- 
    Document   : ViewAllCustomer
    Created on : Mar 13, 2024, 7:56:03 AM
    Author     : pc
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Models.DAO.CustomerDAO"%>
<%@page import="Models.Entities.Customer"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <style>
        .customerManage {
            margin: 70px auto;
            font-size: 24px
        }
        a {
            text-decoration: none;
            color: Salmon;
        }

    </style>
    <body>
        <jsp:include page="navbar.jsp"/> 
        <div class="customerManage">
            <h1 style="text-align: center; font-size: 50px">CUSTOMER MANAGEMENT</h1>
            <form action="CustomerSearchController" method="post" style="text-align: center">
                <input type="text" name="searchValue" placeholder="What do you want to find?" style="width: 700px; height: 25px"/> <input type="submit" value="Find"/>
            </form><br/>
            <table style="border: 1px solid black; border-collapse: collapse;  width: 88%; margin-left: auto; margin-right: auto; background-color: #F0FFFF">
                <thead >
                    <tr style="background-color: #EED2EE">
                        <th style="border: 1px solid black">NO</th>
                        <th style="border: 1px solid black">Customer ID</th>
                        <th style="border: 1px solid black">Password</th>
                        <th style="border: 1px solid black">Contact Name</th>
                        <th style="border: 1px solid black">Address</th>
                        <th style="border: 1px solid black">Phone Number</th>
                        <th style="border: 1px solid black">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Customer> list = new ArrayList<Customer>();
                        CustomerDAO cDao = new CustomerDAO();
                        List<Customer> searchList = (List<Customer>) request.getAttribute("list");
                        if (searchList != null) {
                            list = searchList;
                        } else {
                            list = cDao.getList();
                        }
                        int count = 0;
                        for (Customer c : list) {
                            count += 1;
                    %>
                    <tr>
                        <td style="border: 1px solid black"><%=count%></td>
                        <td style="border: 1px solid black"><%=c.getCustomerId()%></td>
                        <td style="border: 1px solid black"><%=c.getPassword()%></td>
                        <td style="border: 1px solid black"><%=c.getContactName()%></td>
                        <td style="border: 1px solid black"><%=c.getAddress()%></td>
                        <td style="border: 1px solid black"><%=c.getPhone()%></td>
                        <td style="text-align: center; border: 1px solid black">
                            <a href="UpdateCustomer.jsp?customerId=<%=c.getCustomerId()%>">Edit </a><br/>
                            <a href="DeleteCustomerController?customerId=<%=c.getCustomerId()%>">Delete</a>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
        <jsp:include page="Footer.html"/>
    </body>
</html>
