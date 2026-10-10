<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Order Confirmed - Campus Bites</title>

    <link rel="stylesheet" href="css/style.css">

</head>

<body>

<nav class="navbar">

    <h2>🍔 Campus Bites</h2>

    <div>

        <a href="index.jsp">Home</a>
        <a href="menu">Menu</a>
        <a href="cart.jsp">🛒 Cart</a>

    </div>

</nav>

<%
    Integer orderId =
        (Integer) session.getAttribute("orderId");
%>

<%
    if (orderId != null) {
%>

<div class="confirmation">

    <h1>
        🎉 Order Placed Successfully!
    </h1>

    <p>
        Thank you for ordering from Campus Bites.
    </p>

    <div class="order-id">
        Order #<%= orderId %>
    </div>

    <p>
        <strong>Status:</strong> Pending
    </p>

    <br>

    <p>
        Please collect your order from the
        college canteen when it is ready.
    </p>

    <br>

    <a href="menu" class="btn">
        🍔 Order More Food
    </a>

    <a href="index.jsp" class="btn btn-secondary">
        Home
    </a>

</div>

<%
    } else {
%>

<div class="confirmation">

    <h1>Order Information Not Found</h1>

    <p>
        We couldn't find your order information.
    </p>

    <br>

    <a href="menu" class="btn">
        Go to Menu
    </a>

</div>

<%
    }
%>

</body>

</html>