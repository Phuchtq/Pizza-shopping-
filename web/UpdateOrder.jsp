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
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f7f7f7;
        }
        .update-container {
            
            width: 97%;
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
            height: 95%;
        }
        h2 {
            margin-top: 0;
        }
        form {
            display: flex;
            flex-direction: column;
        }
        input[type=text], input[type=password], input[type=date] {
            margin-bottom: 10px;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            width: 100%;
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
            String rawId1 = request.getParameter("orderId");
            String rawId2 = (String) request.getAttribute("orderId");
            String orderId = "";
            if (rawId1 != null) {
                orderId = rawId1;
            } else if (rawId2 != null) {
                orderId = rawId2;
            }
            OrderDAO oDao = new OrderDAO();
            List<Order> list = oDao.searchByOrderId(orderId);
            Order o = list.get(0);
        %>
        <div class="update-container">
            <div class="left-section">
            </div>
            <div class="right-section">
                <div style="background-color: rgb(156, 221, 247); padding-left: 0px; padding-right: 0px; text-align: left"><h2>Order Information</h2></div>
                <form action="UpdateOrderController" method="post">
                    Order ID <input type="text" name="orderId" value="<%=o.getOrderId()%>" readonly=""/> <br/>
                    Customer ID <input type="text" name="customerId" value="<%=o.getCustomerId()%>"  readonly=""/> <br/>
                    Order Date <input type="text" name="orderDate" value="<%=o.getOrderDate()%>" readonly=""/> <br/>
                    Required Date <input type="text" name="requiredDate" value="<%=o.getRequiredDate()%>" readonly=""/> <br/>
                    Shipped Date <input type="date" name="shippedDate"/> <br/>
                    Freight fee <input type="text" name="freight" placeholder="Freight"/> <br/>
                    Shipped Address <input type="text" name="shippedAddress" value="<%=o.getShipAddress()%>" placeholder="Shipped Address" readonly=""/> <br/>
                    <input type="submit" value="UPDATE"/><br/>
                    <%
                        String msg = (String) request.getAttribute("msg");
                        if (msg != null) {
                    %>
                    <h2 style="color: red"><%=msg%></h2>
                    <% }%>
                </form>
            </div>
        </div>
    </body>
</html>
