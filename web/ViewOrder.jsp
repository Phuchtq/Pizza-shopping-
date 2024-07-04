<%-- 
    Document   : ViewOrder
    Created on : Mar 19, 2024, 1:38:35 AM
    Author     : pc
--%>

<%@page import="Models.Entities.Product"%>
<%@page import="Models.DAO.ProductDAO"%>
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
        <title>JSP Page</title>
    </head>
    <style>
        .detail {
            margin: 70px auto;  
            width: 95%;
            height: 75%;
            background-color: AliceBlue;
        }
        .le {
            background-color: Cornsilk;
        }
        .chan {
            background-color: #EED2EE;
        }
        table, td, th {
            border-collapse: collapse;
        }
    </style>
    <body>
        <%
            String orderId = request.getParameter("orderId");
            //-------------------------------------
            OrderDAO oDao = new OrderDAO();
            List<Order> oList = oDao.searchByOrderId(orderId);
            Order o = oList.get(0);
            //-------------------------------------
            OrderDetailDAO odDao = new OrderDetailDAO();
            List<OrderDetail> odList = odDao.searchByOrderId(orderId);
            OrderDetail od = odList.get(0);
            float totalPrice = od.getQuantity() * od.getPrice() + o.getFreight();
            //-------------------------------------
            String productId = od.getProductId();
            ProductDAO pDao = new ProductDAO();
            List<Product> pList = pDao.searchById(productId);
            Product p = pList.get(0);
        %>
        <jsp:include page="navbar.jsp"/> 
        <div class="detail">
            <img src="<%=p.getProductImage()%>" style="float: left; height: 100%; width: 58%;"/>
            <h1 style="text-align: center; height: 100px; background-color: #FEEBD0; padding-top: 40px">ORDER INFORMATION</h1>
            <table style="float: right; font-size: 30px; width: 740px; margin-top: 15px; margin-right: 50px ">
                <tr class="chan">
                    <td></td>
                    <td></td>
                </tr>
                <tr class="chan">
                    <td>Tracking Code</td>
                    <td style="width: 58%"><%=o.getOrderId()%></td>
                </tr>
                <tr class="chan">
                    <td></td>
                    <td></td>
                </tr>
                <tr class="le">
                    <td></td>
                    <td></td>
                </tr>
                <tr class="le">
                    <td>Pizza</td>
                    <td><%=p.getProductName()%></td>
                </tr>
                <tr class="le">
                    <td></td>
                    <td></td>
                </tr>
                <tr class="chan">
                    <td></td>
                    <td></td>
                </tr>
                <tr class="chan">
                    <td>Order Date</td>
                    <td><%=o.getOrderDate()%></td>
                </tr>
                <tr class="chan">
                    <td></td>
                    <td></td>
                </tr>
                <tr class="le">
                    <td></td>
                    <td></td>
                </tr>
                <tr class="le">
                    <td >Required Date</td>
                    <td><%=o.getRequiredDate()%></td>
                </tr>
                <tr class="le">
                    <td></td>
                    <td></td>
                </tr>
                <tr class="chan">
                    <td></td>
                    <td></td>
                </tr>
                <tr class="chan">
                    <td>Shipped Date</td>
                    <td><%=o.getShippedDate()%></td>
                </tr>
                <tr class="chan">
                    <td></td>
                    <td></td>
                </tr>
                <tr class="le">
                    <td></td>
                    <td></td>
                </tr>
                <tr class="le">
                    <td>My Address</td>
                    <td><%=o.getShipAddress()%></td>
                </tr>
                <tr class="le">
                    <td></td>
                    <td></td>
                </tr>
                <tr class="chan">
                    <td></td>
                    <td></td>
                </tr>
                <tr class="chan">
                    <td>Quantity</td>
                    <td><%=od.getQuantity()%></td>
                </tr>
                <tr class="chan">
                    <td></td>
                    <td></td>
                </tr>
                <tr class="le">
                    <td></td>
                    <td></td>
                </tr>
                <tr class="le">
                    <td>Price</td>
                    <td><%=od.getPrice()%>$</td>
                </tr>
                <tr class="le">
                    <td></td>
                    <td></td>
                </tr>
                <tr class="chan">
                    <td></td>
                    <td></td>
                </tr>
                <tr class="chan">
                    <td>Freight</td>
                    <td><%=o.getFreight()%>$</td>
                </tr>
                <tr class="chan">
                    <td></td>
                    <td></td>
                </tr>
            </table>
            <table style="border: 1px solid #EED2EE; float: right; font-size: 30px; width: 740px; padding-right: 30px; margin-right: 50px">
                <tr class="le" style="height: 120px">
                    <th style="width: 500px; border: 1px solid #EED2EE">Total in Price</th>
                    <th><%=totalPrice%>$</th>
                </tr>
            </table>
        </div>
        <jsp:include page="Footer.html"/>
    </body>
</html>
