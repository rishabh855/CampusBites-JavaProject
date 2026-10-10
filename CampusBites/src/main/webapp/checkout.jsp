<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Checkout - Campus Bites</title>

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

<div class="form-container">

    <h1>📋 Checkout</h1>

    <p>
        Enter your details to place the order.
    </p>

    <br>

    <form action="place-order" method="post">

        <div class="form-group">

            <label>Customer Name</label>

            <input
                type="text"
                name="customerName"
                required>

        </div>

        <div class="form-group">

            <label>Phone Number</label>

            <input
                type="tel"
                name="phone"
                pattern="[0-9]{10}"
                placeholder="10 digit mobile number"
                required>

        </div>

        <button type="submit">
            🍔 Place Order
        </button>

        <a href="cart.jsp" class="btn btn-secondary">
            ← Back to Cart
        </a>

    </form>

</div>

</body>

</html>