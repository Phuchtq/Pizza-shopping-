<%-- 
    Document   : PizzaStore
    Created on : Mar 15, 2024, 12:50:32 AM
    Author     : pc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pizza Store</title>
    </head>
    <style>
        body {
            background-color: #E4ADC4;
        }
        .Intro {
            display: flex;
            flex-direction: column;
            margin: 70px auto;
        }
        .para {
            width: 78%;
            margin-left: auto;
            margin-right: auto;
        }
    </style>
    <body>
        <jsp:include page="navbar.jsp"/>
        <div class="Intro">
            <div class="para">
                <h1 style="font-size: 200px; margin: 30px auto">WELCOME</h1>
                <h2 style="font-family: 'Georgia'; font-size: 2em; color: #8B795E ">Our shop is a place where the aroma of freshly baked pizza fills the air and the ambiance is always warm and inviting. We take pride in serving you the finest pizzas, crafted with hand-picked ingredients and baked to perfection. Whether you’re a fan of classic Margherita, love a good Pepperoni, or want to explore our gourmet selections, we have something for everyone. So sit back, relax, and let us take your taste buds on a delightful journey. Enjoy your time at our pizza shop, where every slice tells a story!</h2>
            </div>
            <img src="Images/Welcome.jpg" width="1800px" height="1400px" style="margin: 20px auto"/><br/>  
            <img src="Images/PizzaIntro.jpg" width="1800px" height="1400px" style="margin: 20px auto"/>     
        </div>
        <jsp:include page="Footer.html"/>
    </body>
</html>
