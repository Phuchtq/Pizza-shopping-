<%-- 
    Document   : AddToProductCart
    Created on : Mar 10, 2024, 12:47:02 AM
    Author     : pc
--%>

<%@page import="java.util.HashMap"%>
<%@page import="Models.Entities.Product"%>
<%@page import="java.util.List"%>
<%@page import="Models.DAO.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Product To Cart</title>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/> 
        <h1>Extra information</h1>
        <h4>Format for date: day/Month/Year</h4>
        <%
            String productId = request.getParameter("productId");
            ProductDAO pDao = new ProductDAO();
            List<Product> list = pDao.searchById(productId);
            String accountId = (String)session.getAttribute("accountId");
            HashMap<String, Product> pCart = (HashMap<String, Product>) session.getAttribute("productCart" + accountId);
            HashMap<String, String> dateList = (HashMap<String, String>) session.getAttribute("dateList" + accountId);
            HashMap<String, String> freightList = (HashMap<String, String>) session.getAttribute("freightList" + accountId);
            Product p = list.get(0);
            boolean status = false;
            if (pCart.containsKey(p.getProductId()) && dateList.containsKey(p.getProductId()) && freightList.containsKey(p.getProductId())) {
                status = true;
        %>
        <h3>This product has already been added to your cart. Fill the Required Date, Shipped Date and Freight if you want to change ship information</h3>
        <% }%>
        <form action="AddProductToCartController" method="post">
            Quantity<br/>
            <input type="number" name="quantity" value="1" min="1" max="<%=p.getQuantity()%>"/><br/>
            <%
                if (status) {
                    String dateValue = dateList.get(p.getProductId());
                    String component[] = dateValue.split("-");
                    String freight = freightList.get(p.getProductId());
            %>
            Required Date<br/>
            <input type="text" name="requiredDate" value="<%=component[0]%>"/><br/><br/>
            Shipped Date<br/>
            <input type="text" name="shippedDate" value="<%=component[1]%>"/><br/><br/>
            Freight<br/>
            <input type="text" name="freight" value="<%=freight%>"/><br/><br/>
            <%
            } else {
            %>
            Required Date<br/>
            <input type="text" name="requiredDate"/><br/><br/>
            Shipped Date<br/>
            <input type="text" name="shippedDate"/><br/><br/>
            Freight<br/>
            <input type="text" name="freight"/><br/><br/>
            <% }%>
            <button style="background: greenyellow; color: white" type="submit" name="productId" value="<%=productId%>">Add to my cart</button>
        </form>
        <%
            String msg = (String) request.getAttribute("msg");
            if (msg != null) {
        %>
        <h3 style="color: red"><%=msg%></h3>
        <% }%>
    </body>
</html>
