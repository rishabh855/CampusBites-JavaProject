<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.campusbites.model.CartItem" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Cart - Campus Bites</title>

    <link rel="stylesheet" href="css/style.css">

</head>

<body>

<nav class="navbar">

    <h2>🍔 Campus Bites</h2>

    <div>

        <a href="index.jsp">Home</a>
        <a href="menu">Menu</a>
        <a href="cart.jsp">🛒 Cart</a>
        <a href="view-orders">📋 Orders</a>

    </div>

</nav>

<div class="container">

    <h1>🛒 Your Cart</h1>

<%
    List<CartItem> cart =
        (List<CartItem>) session.getAttribute("cart");

    double total = 0;

    if (cart != null && !cart.isEmpty()) {

        for (CartItem item : cart) {

            double subtotal =
                item.getSubtotal();

            total += subtotal;
%>

    <div class="cart-item">

        <h2>
            <%= item.getFood().getName() %>
        </h2>

        <p>
            Price:
            ₹<%= item.getFood().getPrice() %>
        </p>

        <div class="quantity">

            <strong>Quantity:</strong>

            <a
                href="update-cart?id=<%= item.getFood().getId() %>&action=decrease">
                ➖
            </a>

            <strong>
                <%= item.getQuantity() %>
            </strong>

            <a
                href="update-cart?id=<%= item.getFood().getId() %>&action=increase">
                ➕
            </a>

        </div>

        <p>
            Subtotal:
            <strong>₹<%= subtotal %></strong>
        </p>

        <br>

        <a
            href="update-cart?id=<%= item.getFood().getId() %>&action=remove"
            class="btn btn-danger"
            onclick="return confirm('Remove this item from your cart?');">
            ❌ Remove
        </a>

    </div>

<%
        }
%>

    <div class="cart-total">

        <h2>
            Total: ₹<%= total %>
        </h2>

        <a href="menu" class="btn btn-secondary">
            ← Continue Shopping
        </a>

        <a href="checkout.jsp" class="btn">
            Proceed to Checkout →
        </a>

    </div>

<%
    } else {
%>

    <div class="empty-state">

        <h2>Your cart is empty 🛒</h2>

        <p>
            Add some delicious food from the menu.
        </p>

        <br>

        <a href="menu" class="btn">
            🍔 Browse Menu
        </a>

    </div>

<%
    }
%>

</div>

<div class="footer">
    Campus Bites © 2026
</div>

</body>

</html>