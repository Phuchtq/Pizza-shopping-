
<%@page import="Models.Entities.Category"%>
<%@page import="Models.DAO.CategoryDAO"%>
<%@page import="Models.Entities.Account"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        height: 100vh;
        background-color: #f7f7f7;
    }
    h1 {
        text-align: center; 
        color: lightgreen; 
        font-size: 40px;
        font-family: Montserrat;
    }
    .navbar {
        font-family: Playfair Display;
        margin-top: 0;
        width: 100%;
    }   
    .navbar ul{
        list-style-type: none;
        background-color: deepskyblue;
        color: white;
        padding: 0px;
        margin: 0px 0px 0px 0px;
        overflow: hidden;
        font-size: 40px;
        height: 140px;
    }
    .navbar-right {
        font-size: 37px;
        color: white;
        text-decoration: none;
        text-align: center;
        padding: 50px 20px;
        float: right;
        margin: 0; 
    }
    .navbar-left {
        font-size: 37px;
        padding: 50px 20px;
        color: white;
        text-decoration: none;
        text-align: center;
        margin: 0;
        float: left;
    }
    .navbar a:hover {
        background-color: #00CED1
    }
</style>
<nav class="navbar">
    <ul>
        <li><a href="PizzaStore.jsp" class="navbar-left"><img src="Images/PizzaIcon.png" height="30px" width="30px" style="margin-left: 10px;"/>Pizza Store</a></li>
        <li>
            <select name="categoryId" onchange="window.location.href = this.value;" class="navbar-left" style="background-color: deepskyblue; border-style:  none" >
                <option value="#">Cateogries</option>
                <%
                    CategoryDAO cDao = new CategoryDAO();
                    List<Category> cList = cDao.getList();
                    for (Category c : cList) {
                %>
                <option value="GetProductByCategoryIdController?categoryId=<%=c.getCategoryId()%>"><%=c.getCategoryName()%></option>
                <% } %>
            </select>
        </li>
        <%
            Account staff = (Account) session.getAttribute("staff");
            if (staff != null) {
        %>
        <li><a href="StaffInterface.jsp" class="navbar-left">Pizzas</a></li>
        <li><p class="navbar-left">Welcome, Admin</p></li>
        <li><a href="ViewAllOrder.jsp" class="navbar-right">Order Management</a></li>
        <li><a href="ViewAllCustomer.jsp" class="navbar-right">Customer Management</a></li>
            <%
                }
            %>
        <li><a href="LogOutController" class="navbar-right">Log Out</a></li>
            <%
                String fullName = (String) session.getAttribute("fullName");
                if (staff == null && fullName != null) {
            %>
        <li><a href="UserInterface.jsp" class="navbar-left">Pizzas</a></li>
        <li><p class="navbar-left">Welcome, <%=fullName%></p></li>
        <li><a href="ViewMyOrder.jsp"class="navbar-right">My orders</a></li>
        <li><a href="ViewCart.jsp" class="navbar-right">My cart</a></li>
            <%
            } else if (fullName == null && staff == null) {
            %>
        <li><a href="Register.jsp" class="navbar-right">Register</a></li>
        <li><a href="Login.jsp" class="navbar-right">Log In</a></li>
            <% }%>
    </ul>
</nav>
