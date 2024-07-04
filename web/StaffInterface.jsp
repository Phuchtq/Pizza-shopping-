<%-- 
    Document   : StaffInterface
    Created on : Mar 8, 2024, 1:45:33 AM
    Author     : pc
--%>

<%@page import="Models.Entities.Category"%>
<%@page import="Models.DAO.CategoryDAO"%>
<%@page import="Models.Entities.Product"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Models.DAO.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style> 
            body {
                margin: 0;
                padding: 0;
                background-color: #E4ADC4;
            }
            .mainPage {
                margin: 70px auto;
            }
            a {
                text-decoration: none;
                color: Salmon;
            }
        </style>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/> 
        <div class="mainPage">
            <h1 style="text-align: center">PIZZA MANAGEMENT</h1>
            <form action="AddProduct.jsp"><input type="submit" value="Create New" style="background-color: blue; color: white; margin-left: 100px"/></form><br/>
            <table style="margin-left: auto; margin-right: auto; width: 95%; background-color: #F0FFFF; font-size: 23px; border: 1px solid black; border-collapse: collapse;">
                <thead>
                    <tr style="background-color: #EED2EE">
                        <th style="border: 1px solid black;">NO</th>
                        <th style="border: 1px solid black;">Name</th>
                        <th style="border: 1px solid black;">Price</th>
                        <th style="border: 1px solid black;">Description</th>
                        <th style="border: 1px solid black;">Image URL</th>
                        <th style="border: 1px solid black;">Cateogy</th>
                        <th style="border: 1px solid black;">Service</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ProductDAO pDao = new ProductDAO();
                        List<Product> pList = new ArrayList();
                        List<Product> searchList = (List<Product>) request.getAttribute("list");
                        if (searchList != null) {
                            pList = searchList;
                        } else {
                            pList = pDao.getList();
                        }
                        CategoryDAO cDao = new CategoryDAO();
                        List<Category> cList = cDao.getList();
                        int count = 0;
                        for (Product p : pList) {
                            for (int idx = 0; idx < cList.size(); idx++) {
                                Category c = cList.get(idx);
                                if (p.getCategoryId().equals(c.getCategoryId())) {
                                    count += 1;
                    %>
                    <tr>
                        <th style="border: 1px solid black;"><%=count%></th>
                        <td style="border: 1px solid black;"> <%= p.getProductName()%></td>
                        <td style="border: 1px solid black;"> $<%= p.getPrice()%></td>
                        <td style="border: 1px solid black;"> <%= c.getDescription()%></td>
                        <td style="border: 1px solid black;"> <%= p.getProductImage()%></td>
                        <td style="border: 1px solid black;"><%=c.getCategoryName()%></td>
                        <td style="text-align: center; border: 1px solid black">
                            <a href="UpdateProduct.jsp?productId=<%=p.getProductId()%>">Edit </a><br/>
                            <a href="ViewProduct.jsp?productId=<%=p.getProductId()%>">Details </a><br/>
                            <a href="DeleteProductController?productId=<%=p.getProductId()%>">Delete</a>
                        </td>
                    </tr>
                    <%
                                    break;
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


