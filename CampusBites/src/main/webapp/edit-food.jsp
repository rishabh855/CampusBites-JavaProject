<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.campusbites.model.FoodItem" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Edit Food - Campus Bites</title>

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

<%
    FoodItem food =
        (FoodItem) request.getAttribute("food");
%>

<div class="form-container">

    <h1>✏️ Edit Food</h1>

    <form action="edit-food" method="post">

        <input
            type="hidden"
            name="id"
            value="<%= food.getId() %>">

        <div class="form-group">

            <label>Food Name</label>

            <input
                type="text"
                name="name"
                value="<%= food.getName() %>"
                required>

        </div>

        <div class="form-group">

            <label>Category</label>

            <input
                type="text"
                name="category"
                value="<%= food.getCategory() %>"
                required>

        </div>

        <div class="form-group">

            <label>Price</label>

            <input
                type="number"
                name="price"
                step="0.01"
                min="0"
                value="<%= food.getPrice() %>"
                required>

        </div>

        <div class="form-group">

            <label>Description</label>

            <textarea name="description"><%= food.getDescription() %></textarea>

        </div>

        <div class="form-group">

            <label>

                <input
                    type="checkbox"
                    name="availability"
                    <%= food.isAvailability()
                        ? "checked" : "" %>>

                Available

            </label>

        </div>

        <button type="submit">
            Update Food
        </button>

        <a href="menu" class="btn btn-secondary">
            Cancel
        </a>

    </form>

</div>

</body>

</html>