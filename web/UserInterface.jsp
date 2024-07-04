<%-- 
    Document   : UserInterface
    Created on : Mar 8, 2024, 1:45:18 AM
    Author     : pc
--%>

<%@page import="Models.Entities.Category"%>
<%@page import="Models.DAO.CategoryDAO"%>
<%@page import="Models.DAO.ProductDAO"%>
<%@page import="Models.Entities.Product"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <style>
        .menu {
            height: 2900px;
            margin-left: auto;
            margin-right: auto;
            margin-bottom: 400px;
            font-size: 24px;
        }    
        .pizzaContainer {
            margin-bottom: 1000px   
        }
    </style>
    <body>
        <%
            String accountId = (String) session.getAttribute("accountId");
        %>
        <jsp:include page="navbar.jsp"/> 
        <div class="menu">
            <h1 style="font-size: 50px">PIZZA MENU</h1>
            <form action="SearchInRangeController" method="post" style="text-align: center">
                PRICE INTERVAL - From <input type="number" min="1" step="0.001" name="min" placeholder="MINIMUM PRICE" style="height: 30px"/> to <input type="number" min="1" step="0.001" name="max" placeholder="MAX PRICE" style="height: 30px"/> <button type="submit" style="height: 30px; width: 40px">Find</button>
            </form> <br/>
            <form action="SearchByUserController" style="text-align: center">
                <input type="text" name="searchValue" placeholder="What do you want to find?" style="width: 700px; height: 40px"/> <input type="submit" value="Find" style="height: 40px; width: 50px"/>
            </form><br/>
            <div class="pizzaContainer">
                <form action="AddProductToCartController">
                    <%
                        CategoryDAO cDao = new CategoryDAO();
                        List<Category> cateoryDisplayList = cDao.getList();
                        ProductDAO pDao = new ProductDAO();
                        List<Product> searchList = (List<Product>) request.getAttribute("list");
                        List<Product> pList = new ArrayList();
                        if (searchList != null) {
                            pList = searchList;
                        } else {
                            pList = pDao.getList();
                        }
                        List<Category> cList = cDao.getList();
                        for (int idx = 0; idx < pList.size(); idx++) {
                            Product p = pList.get(idx);
                            for (Category c : cList) {
                                if (p.getCategoryId().equals(c.getCategoryId())) {
                    %>
                    <div class="pizza"style="background-color: #F0FFFF; float: left; width: 31%; height: 700px; margin: 25px 20px">
                        <img src="<%=p.getProductImage()%>" style="width: 100%; height: 67%"/>
                        <h2 style="color: #FF69B4"><%=p.getProductName()%> <p style="float: right; margin-top: 1px; margin-right: 10px">$<%=p.getPrice()%></p> </h2>
                        <p style="font-weight: bold">Category: <%=c.getCategoryName()%> <br/> <%=c.getDescription()%></p>
                        <button style="background-color: #9ACD32; color: white; width: 100px; height: 35px; font-size: 18px " type="submit" name="productId" value="<%=p.getProductId()%>">Add Pizza</button>
                    </div>
                    <%
                                    break;
                                }
                            }
                        }
                    %>
                </form>
            </div>
        </div>
        <jsp:include page="Footer.html"/>
    </body>
</html>

