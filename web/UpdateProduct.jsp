<%-- 
    Document   : UpdateProduct
    Created on : Mar 8, 2024, 2:06:41 PM
    Author     : pc
--%>

<%@page import="Models.DAO.CategoryDAO"%>
<%@page import="Models.Entities.Category"%>
<%@page import="Models.Entities.Supplier"%>
<%@page import="Models.DAO.SupplierDAO"%>
<%@page import="Models.Entities.Product"%>
<%@page import="java.util.List"%>
<%@page import="Models.DAO.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Product</title>
    </head>
    <style>
        .pizzaForm {
            width: 45%;
            height: 68%;
            font-size: 30px;
            background-color: AliceBlue;
            margin: 70px auto;
            }
        input[type=submit] {
            height: 26px
        }
    </style>
    <body>
        <jsp:include page="navbar.jsp"/> 
        <h1 style="font-size: 55px; margin-top: 15px; margin-bottom: 0">PIZZA INFORMATION</h1>
        <%
            String productId = request.getParameter("productId");
            ProductDAO pDao = new ProductDAO();
            List<Product> list = pDao.searchById(productId);
            Product p = list.get(0);

            //-------------------------------------//
            SupplierDAO sDao = new SupplierDAO();
            List<Supplier> sList = sDao.getList();
            Supplier s = new Supplier();
            for (int idx = 0; idx < sList.size(); idx++) {
                Supplier supplier = sList.get(idx);
                if (p.getSupplierId().equals(supplier.getSupplierId())) {
                    s = supplier;
                    sList.remove(idx);
                    break;
                }
            }

            //--------------------------------------//
            CategoryDAO cDao = new CategoryDAO();
            List<Category> cList = cDao.getList();
            Category c = new Category();
            for (int idx = 0; idx < cList.size(); idx++) {
                Category category = cList.get(idx);
                if (p.getCategoryId().equals(category.getCategoryId())) {
                    c = category;
                    cList.remove(idx);
                    break;
                }
            }
        %> 
        <div class="pizzaForm">
            <div style="width: 100%; text-align: center; background-color: #FFE7BA; color: #000080;"><h2 style="font-size: 47px">UPDATE INFORMATION</h2></div>
            <form action="UpdateProductController" method="post">
                Product ID <input type="text" name="productId" value="<%=p.getProductId()%>" style="width: 50%; height: 30px;" readonly=""/><br><br>
                Product Name <input type='text' name='productName' value="<%=p.getProductName()%>" style="width: 50%; height: 30px;"/><br><br>
                Supplier <select name="supplierId" style="height: 30px; width: 80%">
                    <option value="<%=s.getSupplierId()%>" style="font-size: 21px;">Company: <%=s.getCompanyName()%> | Address: <%=s.getAddress()%> | Contact phone number: <%=s.getPhone()%></option>
                    <%
                        for (Supplier supplier : sList) {
                    %>
                    <option value="<%=supplier.getSupplierId()%>" style="font-size: 21px;">Company: <%=supplier.getCompanyName()%> | Address: <%=supplier.getAddress()%> | Contact phone number: <%=supplier.getPhone()%></option>
                    <% }%>
                </select><br/><br/> 
                Category <select name="categoryId" style="height: 30px; width: 80%">
                    <option value="<%=c.getCategoryId()%>" style="font-size: 21px;">Category: <%=c.getCategoryName()%> | Description: <%=c.getDescription()%></option>
                    <%
                        for (Category category : cList) {
                    %>
                    <option value="<%=category.getCategoryId()%>" style="font-size: 21px;">Category: <%=category.getCategoryName()%> | Description: <%=category.getDescription()%></option>
                    <% }%>
                </select> <br> <br/>           
                Quantity <input type="number" name="quantity" min="1" value="<%=p.getQuantity()%>" style="width: 50%; height: 30px;"/><br><br>
                Price <input type='number' name='price' min="0" step="0.001" value="<%=p.getPrice()%>" style="width: 50%; height: 30px;"/><br><br>
                Product Image URL <input type='text' name='productImage' value="<%=p.getProductImage()%>" style="width: 50%; height: 30px;"/><br><br>
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
