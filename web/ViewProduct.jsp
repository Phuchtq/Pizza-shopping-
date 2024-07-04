<%-- 
    Document   : ViewProduct
    Created on : Mar 8, 2024, 2:40:51 PM
    Author     : pc
--%>

<%@page import="Models.Entities.Product"%>
<%@page import="java.util.List"%>
<%@page import="Models.DAO.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Detail</title>
    </head>
    <style>
        .detail {
            margin: 50px auto;
            width: 80%;
            height: 70%;
            background-color: AliceBlue;
        }
        .le {
            background-color: Cornsilk;
        }
        .chan {
            background-color: #EED2EE;
        }
        table, td {
            border: 1px #EED2EE
        }
    </style>
    <body>
        <%
            String productId = request.getParameter("productId");
            ProductDAO pDao = new ProductDAO();
            List<Product> list = pDao.searchById(productId);
            Product p = list.get(0);
        %>
        <jsp:include page="navbar.jsp"/> 
        <div class="detail">
            <img src="<%=p.getProductImage()%>" style="float: left; height: 100%; width: 60%; "/>
            <h1 style="text-align: center; padding-top: 20px; padding-bottom: 20px; background-color: #FEEBD0">PIZZA INFORMATION</h1>
            <table style="float: right; font-size: 35px; width: 580px; height: 68%; padding-top: 0px;">
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr  class="chan">
                    <td rowspan="3">Product ID</td>
                    <td colspan="2" rowspan="3"><%=p.getProductId()%></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr class="le">
                    <td rowspan="3">Product Name</td>
                    <td colspan="2" rowspan="3"><%=p.getProductName()%></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr class="chan">
                    <td rowspan="3">Supplier ID</td>
                    <td colspan="2" rowspan="3"><%=p.getSupplierId()%></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr class="le">
                    <td rowspan="3">Category ID</td>
                    <td colspan="2" rowspan="3"><%=p.getCategoryId()%></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr  class="chan">
                    <td rowspan="3">Quantity</td>
                    <td colspan="2" rowspan="3"><%=p.getQuantity()%></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr class="le">
                    <td rowspan="3">Price</td>
                    <td colspan="2" rowspan="3" ><%=p.getPrice()%>$</td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </table>
        </div>    
        <jsp:include page="Footer.html"/>
    </body>
</html>
