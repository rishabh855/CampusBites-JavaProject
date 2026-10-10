<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Add Food - Campus Bites</title>

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

<div class="form-container">

    <h1>➕ Add Food Item</h1>

    <form action="add-food" method="post">

        <div class="form-group">

            <label>Food Name</label>

            <input
                type="text"
                name="name"
                required>

        </div>

        <div class="form-group">

            <label>Category</label>

            <input
                type="text"
                name="category"
                required>

        </div>

        <div class="form-group">

            <label>Price</label>

            <input
                type="number"
                name="price"
                step="0.01"
                min="0"
                required>

        </div>

        <div class="form-group">

            <label>Description</label>

            <textarea
                name="description"></textarea>

        </div>

        <button type="submit">
            Add Food
        </button>

        <a href="menu" class="btn btn-secondary">
            Cancel
        </a>

    </form>

</div>

</body>

</html>