<%-- 
    Document   : ViewMyOrder
    Created on : Mar 12, 2024, 1:39:44 AM
    Author     : pc
--%>

<%@page import="java.sql.Date"%>
<%@page import="Models.Entities.Order"%>
<%@page import="Models.DAO.OrderDAO"%>
<%@page import="Models.Entities.OrderDetail"%>
<%@page import="java.util.List"%>
<%@page import="Models.DAO.OrderDetailDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Order</title>
    </head>
    <style>
        a {
            text-decoration: none;
            color: Salmon;
        }
    </style>
    <body>
        <jsp:include page="navbar.jsp"/> 
        <div style="margin: 70px auto; font-size: 24px;">
            <h1 style="text-align: center">ORDER INFORMATION</h1>
            <%
                OrderDAO oDao = new OrderDAO();
                List<Order> oList = oDao.getList();
                String accountId = (String) session.getAttribute("accountId");
                boolean status = false;
                for (Order o : oList) {
                    if (o.getCustomerId().equals(accountId)) {
                        status = true;
                        break;
                    }
                }
                if (status) {
                    OrderDetailDAO odDao = new OrderDetailDAO();
                    List<OrderDetail> odList = odDao.getList();
            %>
            <table style="border: 1px solid black; border-collapse: collapse; width: 88%; margin-left: auto; margin-right: auto; background-color: #F0FFFF; font-size: 22px; text-align: center">
                <thead>
                    <tr style="background-color: #EED2EE">
                        <th style="border: 1px solid black">NO</th>
                        <th style="border: 1px solid black">Order ID</th>
                        <th style="border: 1px solid black">Product ID</th>
                        <th style="border: 1px solid black">Quantity</th>
                        <th style="border: 1px solid black">Price in total</th>
                        <th style="border: 1px solid black">Order Date</th>
                        <th style="border: 1px solid black">Required Date</th>
                        <th style="border: 1px solid black">Shipped Date</th>
                        <th style="border: 1px solid black">Status</th>
                        <th style="border: 1px solid black">Service</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 0;
                        for (int idx = 0; idx < oList.size(); idx++) {
                            Order o = oList.get(idx);
                            if (o.getCustomerId().equals(accountId)) {
                                for (OrderDetail od : odList) {
                                    if (o.getOrderId().equals(od.getOrderId())) {
                                        count += 1;
                                        long current = System.currentTimeMillis();
                                        long raw = o.getOrderDate().getTime();
                                        long range = current - raw;
                                        String state = "Processing";
                                        if (range > 1800000) {
                                            state = "Successfully Delivered";
                                        }
                                        // 0.001 x 1000 x 60 x 30 = 30 mins
                                        // Quay lại trường hợp xét tình trạng xử lí đơn từ cái servlet thanh toán
                                        // Nếu thời gian thực hiện hành động k quá 30' sau khi đặt đơn thì sẽ có action hủy đơn 
                                        // Còn k thì chỉ có mục xem đơn
                                        // Ở đây t có 1 điểm chưa hoàn thiện là thiếu action chỉnh đơn (cụ thể là số lượng lên xuống)
                                        // Cũng cho k quá 30' sau khi order 
                                        // Có thể tự thêm vào cái này t lười :))
%>
                    <tr>
                        <td style="border: 1px solid black"><%=count%></td>
                        <td style="border: 1px solid black"><%=od.getOrderId()%></td>
                        <td style="border: 1px solid black"><%=od.getProductId()%></td>
                        <td style="border: 1px solid black"><%=od.getQuantity()%></td>
                        <td style="border: 1px solid black">$<%=od.getQuantity() * od.getPrice()%></td>
                        <td style="border: 1px solid black"><%=o.getOrderDate()%></td>
                        <td style="border: 1px solid black"><%=o.getRequiredDate()%></td>
                        <td style="border: 1px solid black"><%=o.getShippedDate()%></td>
                        <td style="border: 1px solid black"><%=state%></td>
                        <td style="text-align: center; border: 1px solid black">
                            <%
                                if (range <= 1800000) {
                            %>
                            <a href="#?orderId=<%=o.getOrderId()%>">Cancel</a><br/>
                            <% }%>
                            <a href="ViewOrder.jsp?orderId=<%=o.getOrderId()%>">Detail</a>
                        </td>
                    </tr>
                    <%
                                            break;
                                        }
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
