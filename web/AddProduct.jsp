<%-- 
    Document   : AddProduct
    Created on : Mar 8, 2024, 2:56:47 PM
    Author     : pc
--%>

<%@page import="Models.Entities.Category"%>
<%@page import="Models.DAO.CategoryDAO"%>
<%@page import="Models.Entities.Supplier"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="Models.DAO.SupplierDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Product</title>
    </head>
    <style>
        .pizzaForm {
            width: 45%;
            height: 68%;
            font-size: 30px;
            background-color: AliceBlue;
            margin-left: auto;
            margin-right: auto;
            margin-bottom: 30px;
        }
        input[type=submit] {
            height: 26px
        }
    </style>
    <body>
        <jsp:include page="navbar.jsp"/> 
        <h1 style="font-size: 55px; margin-top: 15px; margin-bottom: 0">PIZZA INFORMATION</h1>
        <%
            String msg = (String) request.getAttribute("msg");
            if (msg != null) {
        %>
        <h3 style="color: red;"><%=msg%></h3>
        <% }%>
        <div class="pizzaForm">
            <div style="width: 100%; text-align: center; background-color: #FFE7BA; color: #000080;"><h2 style="font-size: 47px">New Pizza</h2></div>
            <form action="AddProductController" method="post">
                Product ID <input type="text" name="productId" placeholder="PRODUCT ID" style="width: 50%; height: 30px;"/><br><br>
                Product Name <input type='text' name='productName' placeholder="PRODUCT NAME" style="width: 50%; height: 30px;"/><br><br>
                Supplier <select name="supplierId" style="height: 30px; width: 80%">
                    <%
                        SupplierDAO sDao = new SupplierDAO();
                        List<Supplier> sList = sDao.getList();
                        for (Supplier s : sList) {
                    %>
                    <option value="<%=s.getSupplierId()%>" style="font-size: 21px;">Company: <%=s.getCompanyName()%> | Address: <%=s.getAddress()%> | Contact phone number: <%=s.getPhone()%></option>
                    <% } %>
                </select><br/><br/> 
                Category <select name="categoryId" style="height: 30px; width: 80%" >
                    <%
                        CategoryDAO cDao = new CategoryDAO();
                        List<Category> cList = cDao.getList();
                        for (Category c : cList) {
                    %>
                    <option value="<%=c.getCategoryId()%>" style="font-size: 21px;">Category: <%=c.getCategoryName()%> | Description: <%=c.getDescription()%></option>
                    <% } %>
                </select> <br> <br/>           
                Quantity <input type="number" name="quantity" min="1" placeholder="QUANTITY style="width: 50%; height: 30px;"/><br><br>
                Price <input type='number' name='price' min="0" step="0.001" placeholder="PRICE" style="width: 50%; height: 30px;"/><br><br>
                Product Image URL <input type='text' name='productImage' placeholder="IMAGE URL" style="width: 50%; height: 30px;"/><br><br>
                <input type="submit" value="CREATE" style="background: #FFE7BA; color: #000080; width: 100%; height: 47px; border-style: none"/>
            </form>
        </div>
        <jsp:include page="Footer.html"/>
    </body>
</html>
