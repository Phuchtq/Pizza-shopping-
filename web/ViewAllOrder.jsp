<%-- 
    Document   : ViewAllOrder
    Created on : Mar 12, 2024, 2:11:13 AM
    Author     : pc
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Models.Entities.OrderDetail"%>
<%@page import="Models.DAO.OrderDetailDAO"%>
<%@page import="Models.Entities.Order"%>
<%@page import="java.util.List"%>
<%@page import="Models.DAO.OrderDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View All Orders From System</title>
    </head>
    <style>
        a {
            text-decoration: none;
            color: Salmon;
        }
        .orderManagement {
            margin: 70px auto;
            font-size: 24px;
        }
    </style>
    <body>
        <jsp:include page="navbar.jsp"/> 
        <div class="orderManagement">
            <h1 style="text-align: center; font-size: 50px">ORDERS MANAGEMENT</h1>
            <form action="SearchOrderByDateController" method="post">
                <p style="text-align: center">Search by date: From <input type="date" name="startDate"/> to <input type="date" name="endDate"/>  <input type="submit" value="Search"/></p>
            </form><br/>
            <%
                List<Order> searchList = (List<Order>) request.getAttribute("list");
            %>
            <form action="OrderSearchController" method="post" style="text-align: center">
                <input type="text" name="searchValue" placeholder="What do you want to find?" style="width: 700px; height: 25px"/> <input type="submit" value="Find"/>
            </form><br/>
            <%
                OrderDAO oDao = new OrderDAO();
                List<Order> fullOrderList = oDao.getList();
                List<Order> oList = new ArrayList<>();
                if (searchList != null) {
                    oList = searchList;
                } else {
                    oList = fullOrderList;
                }
                OrderDetailDAO odDao = new OrderDetailDAO();
                List<OrderDetail> odList = odDao.getList();
                if (oList != null && odList != null) {
            %>
            <table style="border: 1px solid black; border-collapse: collapse; width: 83%; margin-left: auto; margin-right: auto; background-color: #F0FFFF; text-align: center">
                <thead>
                    <tr style="background-color: #EED2EE"> 
                        <th style="border: 1px solid black;">NO</th>
                        <th style="border: 1px solid black;">Order ID</th>
                        <th style="border: 1px solid black;">Product ID</th>
                        <th style="border: 1px solid black;">Customer ID</th>
                        <th style="border: 1px solid black;">Quantity</th>
                        <th style="border: 1px solid black;">Price in total</th>
                        <th style="border: 1px solid black;">Order Date</th>
                        <th style="border: 1px solid black;">Required Date</th>
                        <th style="border: 1px solid black;">Shipped Date</th>
                        <th style="border: 1px solid black;">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 0;
                        for (int idx = 0; idx < oList.size(); idx++) {
                            Order o = oList.get(idx);
                            for (OrderDetail od : odList) {
                                if (o.getOrderId().equals(od.getOrderId())) {
                                    count += 1;
                    %>
                    <tr>
                        <td style="border: 1px solid black;"><%=count%></td>
                        <td style="border: 1px solid black;"><%=od.getOrderId()%></td>
                        <td style="border: 1px solid black;"><%=od.getProductId()%></td>
                        <td style="border: 1px solid black;"><%=o.getCustomerId()%></td>
                        <td style="border: 1px solid black;"><%=od.getQuantity()%></td>
                        <td style="border: 1px solid black;">$<%=od.getQuantity() * od.getPrice()%></td>
                        <td style="border: 1px solid black;"><%=o.getOrderDate()%></td>
                        <td style="border: 1px solid black;"><%=o.getRequiredDate()%></td>
                        <td style="border: 1px solid black;"><%=o.getShippedDate()%></td>
                        <td style="text-align: center; border: 1px solid black;">
                            <a href="DeleteOrderController?orderId=<%=o.getOrderId()%>">Delete</a><br/>
                            <a href="UpdateOrder.jsp?orderId=<%=o.getOrderId()%>">Update</a>
                        </td>
                    </tr>
                    <%
                                        break;
                                    }
                                }
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
        <jsp:include page="Footer.html"/>
    </body>
</html>
