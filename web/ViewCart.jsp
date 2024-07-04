<%-- 
    Document   : ViewCart
    Created on : Mar 10, 2024, 5:48:15 PM
    Author     : pc
--%>

<%@page import="Controllers.CartUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.LinkedList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="Models.Entities.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Cart</title>
    </head>
    <style>
        a {
            text-decoration: none;
            color: salmon;
        }
    </style>
    <body>
        <jsp:include page="navbar.jsp"/> 
        <div style="height: 70%; width: 80%; margin: 70px auto">
            <h1 style="text-align: center">MY PIZZA CART</h1>
            <%
                String accountId = (String) session.getAttribute("accountId");
                String sessionName = "productCart" + accountId;
                HashMap<String, Product> sessionCart = (HashMap<String, Product>) session.getAttribute(sessionName);
                HashMap<String, Product> cookieCart = null;
                CartUtil util = new CartUtil();
                Cookie c = util.getCookieByName(request, sessionName);
                float total = 0;
                if (c != null) {
                    cookieCart = util.getCartFromCookie(c);
                }
                HashMap<String, Product> pCart = new HashMap<String, Product>();
                if (sessionCart != null && sessionCart.size() > 0) {
                    pCart = sessionCart;
                } else if (cookieCart != null && cookieCart.size() > 0) {
                    pCart = cookieCart;
                }
                if (pCart == null || pCart.size() == 0) {
            %>
            <h2 style="text-align: center">Your cart is empty</h2>
            <%
            } else {
            %>
            <table style="border: 1px solid black; border-collapse: collapse; width: 100%; margin-left: auto; margin-right: auto; font-size: 24px; background-color: #F0FFFF; text-align: center">
                <thead>
                    <tr style="background-color: #EED2EE">
                        <th style="border: 1px solid black">NO</th>
                        <th style="border: 1px solid black">Name</th>
                        <th style="border: 1px solid black">Quantity</th>
                        <th style="border: 1px solid black">Price</th>
                        <th style="border: 1px solid black">Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int count = 0;
                        for (Product p : pCart.values()) {
                            String key = p.getProductId();
                            count += 1;
                            float tmp = p.getQuantity() * p.getPrice() + 5;
                            total += tmp;
                    %> 
                    <tr>
                        <td style="border: 1px solid black"><%=count%></td>
                <form action="CartUpdateController" method="post">
                    <input type="hidden" value="<%=p.getProductId()%>" name="productId"/>
                    <td style="border: 1px solid black"> <%= p.getProductName()%></td>
                    <td style="border: 1px solid black"><input type="number" name="quantity" value="<%= p.getQuantity()%>" min="1" style="height: 40px; width: 97%; font-size: 22px"/></td>
                    <td style="border: 1px solid black"> $<%= p.getPrice() * p.getQuantity()%></td>
                    <td style="border: 1px solid black">
                        <a href="RemoveProductFromCartController?productId=<%=key%>">Remove</a><br/>
                        <input type="submit" value="Update" style="height: 97%; width: 99%; font-size: 22px; color: salmon; background-color: #FEEBD0;"/>
                    </td>
                </form>  
                </tr>
                <%
                        }
                    }
                %>   
                <tr>
                    <td rowspan="2"colspan="1"style="font-size: 30px">TOTAL</td>
                    <td colspan="2" rowspan="2" style="font-size: 30px"><%=String.format("%.2f", total)%>$</td>
                    <td rowspan="1" colspan="2" style="border: 1px solid black"><a href="PaymentController" style="border: 1px solid black; border-style: none;">Settle up my bill</a></td>
                </tr>
                <tr>
                    <td rowspan="1" colspan="2" style="border: 1px solid black"><form action="SaveCartController" method="post"><input type="submit" value="Save my cart" style="height: 97%; width: 99%; font-size: 22px; color: salmon; background-color: #FEEBD0;"/></form> </td>
                </tr>
                </tbody>
            </table>  
            <%
                String msg = (String) request.getAttribute("msg");
                if (msg != null) {
            %>
            <h4 style="color: green"><%=msg%></h4>
            <% }%>
        </div>
        <jsp:include page="Footer.html"/>
    </body>
</html>
<!---                    <td rowspan="2"><form action="SaveCartController" method="post"><button type="submit">Save my cart</button></form> </td>
