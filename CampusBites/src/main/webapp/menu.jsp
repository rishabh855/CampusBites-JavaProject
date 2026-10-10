<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.campusbites.model.FoodItem" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Menu - Campus Bites</title>

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

    <h1>🍽️ Our Menu</h1>

    <div class="search-box">

        <form action="menu" method="get">

            <input
                type="text"
                name="keyword"
                placeholder="Search food or category"
            >

            <button type="submit">
                🔎 Search
            </button>

        </form>

        <br>

        <a href="menu" class="btn btn-secondary">
            Show All Food
        </a>

    </div>

    <%
        List<FoodItem> foodList =
            (List<FoodItem>) request.getAttribute("foodList");

        if (foodList != null && !foodList.isEmpty()) {
    %>

    <div class="food-grid">

    <%
        for (FoodItem food : foodList) {
    %>

        <div class="food-card">

            <h2>
                <%= food.getName() %>
            </h2>

            <p class="food-category">
                <%= food.getCategory() %>
            </p>

            <p class="price">
                ₹<%= food.getPrice() %>
            </p>

            <p class="food-description">
                <%= food.getDescription() %>
            </p>

            <%
                if (food.isAvailability()) {
            %>

                <p class="status-available">
                    ● Available
                </p>

            <%
                } else {
            %>

                <p class="status-unavailable">
                    ● Not Available
                </p>

            <%
                }
            %>

            <div class="card-actions">

            <%
                if (food.isAvailability()) {
            %>

                <a
                    href="add-to-cart?id=<%= food.getId() %>"
                    class="btn">
                    🛒 Add to Cart
                </a>

            <%
                }
            %>

                <a
                    href="edit-food?id=<%= food.getId() %>"
                    class="btn btn-secondary">
                    ✏️ Edit
                </a>

                <a
                    href="delete-food?id=<%= food.getId() %>"
                    class="btn btn-danger"
                    onclick="return confirm('Are you sure you want to delete this item?');">
                    🗑️ Delete
                </a>

            </div>

        </div>

    <%
        }
    %>

    </div>

    <%
        } else {
    %>

        <div class="empty-state">

            <h2>No food items found.</h2>

            <p>
                Try another food name or category.
            </p>

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